/*
 *  This file is part of MxUpdate <http://www.mxupdate.org>.
 *
 *  MxUpdate is a deployment tool for a PLM platform to handle
 *  administration objects as single update files (configuration item).
 *
 *  Copyright (C) 2008-2016 The MxUpdate Team - All Rights Reserved
 *
 *  You may use, distribute and modify MxUpdate under the terms of the
 *  MxUpdate license. You should have received a copy of the MxUpdate
 *  license with this file. If not, please write to <info@mxupdate.org>,
 *  or visit <www.mxupdate.org>.
 *
 */

package org.mxupdate.update.datamodel;

import java.io.File;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.mxupdate.typedef.TypeDef_mxJPO;
import org.mxupdate.update.AbstractAdminObject_mxJPO;
import org.mxupdate.update.datamodel.helper.LocalAttributeList_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.DeltaUtil_mxJPO;
import org.mxupdate.update.util.MqlBuilder_mxJPO.MultiLineMqlBuilder;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO.ValueKeys;
import org.mxupdate.update.util.UpdateBuilder_mxJPO;
import org.mxupdate.update.util.UpdateException_mxJPO;
import org.mxupdate.update.util.UpdateException_mxJPO.ErrorKey;

/**
 * Data model interface class.
 *
 * @author The MxUpdate Team
 */
public class Interface_mxJPO
    extends AbstractAdminObject_mxJPO<Interface_mxJPO>
{
    /**
     * Set of all ignored URLs from the XML definition for interfaces.
     *
     * @see #parseAdminXMLExportEvent(ParameterCache_mxJPO, String, String)
     */
    private static final Set<String> IGNORED_URLS = new HashSet<>();
    static  {
        Interface_mxJPO.IGNORED_URLS.add("/attributeDefRefList");
        Interface_mxJPO.IGNORED_URLS.add("/derivedFromInterface");
        Interface_mxJPO.IGNORED_URLS.add("/derivedFromInterface/interfaceTypeRefList");
        Interface_mxJPO.IGNORED_URLS.add("/relationshipDefRefList");
        Interface_mxJPO.IGNORED_URLS.add("/typeRefList");
        Interface_mxJPO.IGNORED_URLS.add("/localAttributes");
        Interface_mxJPO.IGNORED_URLS.add("/localAttributes/attributeDefList");
    }

    /** Is the interface abstract? */
    private Boolean abstractFlag;
    /** From which interfaces is this interface derived? */
    private final SortedSet<String> derived = new TreeSet<>();

    /** Global attributes. */
    private final SortedSet<String> attributes = new TreeSet<>();
    /** Local attributes. */
    private final LocalAttributeList_mxJPO localAttributes = new LocalAttributeList_mxJPO();

    /** Are all types allowed for this interface? */
    private boolean typeAll;
    /** Information about all allowed types for this interface. */
    private final SortedSet<String> types = new TreeSet<>();

    /** Are all relationships allowed for this interface? */
    private boolean relationAll;
    /** Information about all allowed relationships for this interface. */
    private final SortedSet<String> relations = new TreeSet<>();

    /**
     * Constructor used to initialize the interface class instance.
     *
     * @param _typeDef  defines the related type definition enumeration
     * @param _mxName   MX name of the interface object
     */
    public Interface_mxJPO(final TypeDef_mxJPO _typeDef,
                           final String _mxName)
    {
        super(_typeDef, _mxName);
    }

    @Override()
    public void parseUpdate(final File _file,
                            final String _code)
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
    {
        new InterfaceParser_mxJPO(new StringReader(_code)).parse(this);
        this.prepare();
    }

    /**
     * Parses the interface specific XML export URL.
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url          URL to parse
     * @param _content      content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     */
    @Override()
    public boolean parseAdminXMLExportEvent(final ParameterCache_mxJPO _paramCache,
                                            final String _url,
                                            final String _content)
    {
        final boolean parsed;
        if (Interface_mxJPO.IGNORED_URLS.contains(_url))  {
            parsed = true;
        } else if ("/abstract".equals(_url))  {
            this.abstractFlag = true;
            parsed = true;
        } else if ("/allowAllRelationships".equals(_url))  {
            this.relationAll = true;
            parsed = true;
        } else if ("/allowAllTypes".equals(_url))  {
            this.typeAll = true;
            parsed = true;
        } else if (_url.startsWith("/attributeDefRefList/attributeDefRef"))  {
            this.attributes.add(_content);
            parsed = true;
        } else if ("/derivedFromInterface/interfaceTypeRefList/interfaceTypeRef".equals(_url))  {
            this.derived.add(_content);
            parsed = true;
        } else if (_url.startsWith("/localAttributes/attributeDefList/attributeDef"))  {
            parsed = this.localAttributes.parseAdminXMLExportEvent(_paramCache, _url.substring(46), _content);
        } else if ("/relationshipDefRefList/relationshipDefRef".equals(_url))  {
            this.relations.add(_content);
            parsed = true;
        } else if ("/typeRefList/typeRef".equals(_url))  {
            this.types.add(_content);
            parsed = true;
        } else  {
            parsed = super.parseAdminXMLExportEvent(_paramCache, _url, _content);
        }
        return parsed;
    }

    /**
     * After the interface is parsed, the interface and local attributes must be
     * prepared.
     */
    @Override()
    protected void prepare()
    {
        super.prepare();
        this.localAttributes.prepare();
    }

    @Override()
    protected void writeUpdate(final UpdateBuilder_mxJPO _updateBuilder)
    {
        _updateBuilder
                .list(          "symbolicname",             this.getSymbolicNames())
                .string(        "description",              this.getDescription())
                .flagIfTrue(    "abstract",         false,  this.abstractFlag,      (this.abstractFlag != null) && this.abstractFlag)
                .list(          "derived",                  this.derived)
                .flag(          "hidden",           false,  this.isHidden())
                .list(          "attribute",                this.attributes)
                .write(this.localAttributes)
                .singleIfTrue(  "for relationship",         "all",                  this.relationAll)
                .listIfTrue(    "for relationship",         this.relations,         !this.relationAll)
                .singleIfTrue(  "for type",                 "all",                  this.typeAll)
                .listIfTrue(    "for type",                 this.types,             !this.typeAll)
                .properties(this.getProperties());
    }

    @Override()
    protected void calcDelta(final ParameterCache_mxJPO _paramCache,
                             final MultiLineMqlBuilder _mql,
                             final Interface_mxJPO _current)
        throws UpdateException_mxJPO
    {
        DeltaUtil_mxJPO.calcSymbNames(_paramCache, _mql, this.getTypeDef(), this.getName(), this.getSymbolicNames(), _current.getSymbolicNames());
        DeltaUtil_mxJPO.calcValueDelta(            _mql, "description",                 this.getDescription(),              _current.getDescription());
        DeltaUtil_mxJPO.calcFlagDelta(             _mql, "hidden",              false,  this.isHidden(),                    _current.isHidden());
        DeltaUtil_mxJPO.calcValFlgDelta(           _mql, "abstract",            false,  this.abstractFlag,                  _current.abstractFlag);
        DeltaUtil_mxJPO.calcListDelta(             _mql, "type",                        this.typeAll, this.types,           _current.typeAll, _current.types);
        DeltaUtil_mxJPO.calcListDelta(             _mql, "relationship",                this.relationAll, this.relations,   _current.relationAll, _current.relations);
        DeltaUtil_mxJPO.calcListDelta(_paramCache, _mql, "attribute",
                ErrorKey.DM_INTERFACE_REMOVE_ATTRIBUTE, this.getName(),
                ValueKeys.DMInterfaceAttrIgnore, ValueKeys.DMInterfaceAttrRemove,       this.attributes,                    _current.attributes);
        DeltaUtil_mxJPO.calcLstOneCallDelta(_paramCache, _mql, "derived",
                ErrorKey.DM_INTERFACE_REMOVE_PARENT, this.getName(),
                ValueKeys.DMInterfaceParentIgnore, ValueKeys.DMInterfaceParentRemove,   this.derived,                       _current.derived);

        this.localAttributes.calcDelta(_paramCache, _mql, this, _current.localAttributes);
        this.getProperties().calcDelta(_mql, "", _current.getProperties());
    }
}
