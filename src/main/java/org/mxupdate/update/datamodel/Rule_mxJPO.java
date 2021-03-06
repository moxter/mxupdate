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

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.mxupdate.mapping.PropertyDef_mxJPO;
import org.mxupdate.typedef.EMxAdmin_mxJPO;
import org.mxupdate.update.AbstractAdminObject_mxJPO;
import org.mxupdate.update.datamodel.helper.AccessList_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.DeltaUtil_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO.ValueKeys;
import org.mxupdate.update.util.UpdateBuilder_mxJPO;
import org.mxupdate.update.zparser.MxParser_mxJPO;
import org.mxupdate.util.MqlBuilderUtil_mxJPO.MultiLineMqlBuilder;

import matrix.util.MatrixException;

/**
 * The class is used to export and import / update rule configuration items.
 * The handles properties are
 * <ul>
 * <li>package</li>
 * <li>uuid<li>
 * <li>symbolic names</li>
 * <li>description</li>
 * <li>{@link #accessList access list}</li>
 * <li>{@link #enforcereserveaccess enforce reserve access}</li>
 * <li>properties</li>
 * </ul>
 *
 * @author The MxUpdate Team
 */
public class Rule_mxJPO
    extends AbstractAdminObject_mxJPO<Rule_mxJPO>
{
    /** Set of all ignored URLs from the XML definition for rules. */
    private static final Set<String> IGNORED_URLS = new HashSet<>();
    static  {
        Rule_mxJPO.IGNORED_URLS.add("/ownerAccess/access");
        Rule_mxJPO.IGNORED_URLS.add("/ownerRevoke/access");
        Rule_mxJPO.IGNORED_URLS.add("/publicAccess/access");
        Rule_mxJPO.IGNORED_URLS.add("/publicRevoke/access");
        Rule_mxJPO.IGNORED_URLS.add("/userAccessList");
        Rule_mxJPO.IGNORED_URLS.add("/userAccessList/userAccess/access");
    }

    /** Access list. */
    private final AccessList_mxJPO accessList = new AccessList_mxJPO();
    /** Enoforcereserveaccess flag */
    private boolean enforcereserveaccess = false;

    /**
     * Constructor used to initialize the type definition enumeration.
     *
     * @param _mxName   MX name of the rule object
     */
    public Rule_mxJPO(final String _mxName)
    {
        super(EMxAdmin_mxJPO.Rule, _mxName);
    }

    @Override
    public void parseUpdate(final String _code)
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
    {
        new MxParser_mxJPO(new StringReader(_code)).parseRule(this);
        this.prepare();
    }

    /**
     * {@inheritDoc}
     * The rule {@link #accessList access} statements are sorted if defined.
     */
    @Override
    public void parse(final ParameterCache_mxJPO _paramCache)
        throws MatrixException, ParseException
    {
        super.parse(_paramCache);

        if (_paramCache.getValueBoolean(ValueKeys.DMRuleAllowExportAccessSorting))  {
            this.accessList.sort();
        }
    }

    /**
     * Parses all rule specific URLs.
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url          URL to parse
     * @param _content      content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     */
    @Override
    public boolean parseAdminXMLExportEvent(final ParameterCache_mxJPO _paramCache,
                                            final String _url,
                                            final String _content)
    {
        final boolean parsed;
        if (Rule_mxJPO.IGNORED_URLS.contains(_url))  {
            parsed = true;
        } else if (this.accessList.parse(_paramCache, _url, _content))  {
            parsed = true;
        } else if ("/enforceReserveAccess".equals(_url))  {
            this.enforcereserveaccess = true;
            parsed = true;
        } else  {
            parsed = super.parseAdminXMLExportEvent(_paramCache, _url, _content);
        }
        return parsed;
    }

    @Override
    protected void prepare()
    {
        super.prepare();
        this.accessList.sort();
    }

    @Override
    public void writeUpdate(final UpdateBuilder_mxJPO _updateBuilder)
    {
        _updateBuilder
                //              tag             | default | value                              | write?
                .stringNotNull( "package",                  this.getPackageRef())
                .stringNotNull( "uuid",                     this.getProperties().getValue4KeyValue(_updateBuilder.getParamCache(), PropertyDef_mxJPO.UUID))
                .list(          "symbolicname",             this.getSymbolicNames())
                .string(        "description",              this.getDescription())
                .flag(          "hidden",           false,  this.isHidden())
                .flagIfTrue("enforcereserveaccess", false,  this.enforcereserveaccess,          _updateBuilder.getParamCache().getValueBoolean(ValueKeys.DMRuleSupportsEnforceReserveAccess))
                .write(this.accessList)
                .properties(this.getProperties());
    }

    @Override
    public void calcDelta(final ParameterCache_mxJPO _paramCache,
                          final MultiLineMqlBuilder _mql,
                          final Rule_mxJPO _current)
    {
        DeltaUtil_mxJPO.calcPackage(_paramCache, _mql, this, _current);
        DeltaUtil_mxJPO.calcSymbNames(_paramCache, _mql, this, _current);
        DeltaUtil_mxJPO.calcValueDelta(_mql, "description",        this.getDescription(),   _current.getDescription());
        DeltaUtil_mxJPO.calcFlagDelta(_mql,  "hidden",      false, this.isHidden(),         _current.isHidden());
        if (_paramCache.getValueBoolean(ValueKeys.DMRuleSupportsEnforceReserveAccess))  {
            DeltaUtil_mxJPO.calcFlagDelta(_mql, "enforcereserveaccess", false, this.enforcereserveaccess, _current.enforcereserveaccess);
        }

        this.accessList     .calcDelta(_mql, _current.accessList);
        this.getProperties().calcDelta(_mql, "", _current.getProperties());
    }
}
