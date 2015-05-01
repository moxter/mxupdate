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

package org.mxupdate.update.userinterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.mxupdate.mapping.TypeDef_mxJPO;
import org.mxupdate.update.AbstractAdminObject_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.AdminPropertyList_mxJPO.AdminProperty;
import org.mxupdate.update.util.MqlBuilder_mxJPO.MultiLineMqlBuilder;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.StringUtil_mxJPO;
import org.mxupdate.update.util.UpdateException_mxJPO;

/**
 * The class is used to export and import / update inquiry configuration items.
 *
 * @author The MxUpdate Team
 */
public class Inquiry_mxJPO
    extends AbstractAdminObject_mxJPO<Inquiry_mxJPO>
{
    /**
     * Defines the parameter for the comment in front of the separator between
     * TCL update code and the inquiry code.
     *
     * @see #writeEnd(ParameterCache_mxJPO, Appendable)
     */
    private static final String PARAM_SEPARATOR_COMMENT = "UIInquirySeparatorComment";

    /**
     * Defines the parameter for the separator between TCL update code and the
     * inquiry.
     *
     * @see #writeEnd(ParameterCache_mxJPO, Appendable)
     * @see #update(ParameterCache_mxJPO, CharSequence, CharSequence, CharSequence, Map, File)
     */
    private static final String PARAM_SEPARATOR_TEXT = "UIInquirySeparatorText";

    /**
     * Code for the inquiry.
     *
     * @see #parse(ParameterCache_mxJPO, String, String)
     * @see #writeEnd(ParameterCache_mxJPO, Appendable)
     */
    private String code;

    /**
     * Format for the inquiry.
     *
     * @see #parse(ParameterCache_mxJPO, String, String)
     * @see #writeObject(ParameterCache_mxJPO, Appendable)
     */
    private String format;

    /**
     * Pattern for the inquiry.
     *
     * @see #parse(ParameterCache_mxJPO, String, String)
     * @see #writeObject(ParameterCache_mxJPO, Appendable)
     */
    private String pattern;

    /**
     * Constructor used to initialize the inquiry instance.
     *
     * @param _typeDef  defines the related type definition enumeration
     * @param _mxName   MX name of the administration object
     */
    public Inquiry_mxJPO(final TypeDef_mxJPO _typeDef,
                         final String _mxName)
    {
        super(_typeDef, _mxName);
    }

    @Override()
    public void parseUpdate(final String _code)
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
    {
//        new InquiryDefParser_mxJPO(new StringReader(_code)).parse(this);
//        this.prepare();
    }

    /**
     * Parses the {@link #code}, {@@ink #format} and {@link #pattern} of an
     * inquiry.
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url          URL to parse
     * @param _content      related content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     * @see #code
     * @see #format
     * @see #pattern
     */
    @Override()
    protected boolean parse(final ParameterCache_mxJPO _paramCache,
                            final String _url,
                            final String _content)
    {
        final boolean parsed;
        if ("/code".equals(_url))  {
            this.code = _content;
            parsed = true;
        } else if ("/fmt".equals(_url))  {
            this.format = _content;
            parsed = true;
        } else if ("/pattern".equals(_url))  {
            this.pattern = _content;
            parsed = true;
        } else  {
            parsed = super.parse(_paramCache, _url, _content);
        }
        return parsed;
    }

    /**
     * Writes the inquiry specific information to the TCL update write. This
     * includes
     * <ul>
     * <li>hidden flag (only if hidden)</li>
     * <li>{@link #pattern}</li>
     * <li>{@link #format}</li>
     * <li>all arguments (properties starting with &quot;%&quot;)</li>
     * </ul>
     *
     * @param _paramCache   parameter cache
     * @param _out          appendable instance to the TCL update file
     * @throws IOException if the TCL update code for the menu could not be
     *                     written
     * @see #pattern
     * @see #format
     * @see #getPropertiesMap()
     */
    @Override()
    protected void writeObject(final ParameterCache_mxJPO _paramCache,
                               final Appendable _out)
        throws IOException
    {
        if (this.isHidden())  {
            _out.append(" \\\n    hidden");
        }
        _out.append(" \\\n    pattern \"").append(StringUtil_mxJPO.convertTcl(this.pattern)).append("\"")
            .append(" \\\n    format \"").append(StringUtil_mxJPO.convertTcl(this.format)).append("\"")
            .append(" \\\n    file [file join \"${FILE}\"]");
        for (final AdminProperty prop : this.getProperties().getSettings())  {
            _out.append(" \\\n    add argument \"")
                .append(StringUtil_mxJPO.convertTcl(prop.getName().substring(1))).append("\"")
                .append(" \"").append(StringUtil_mxJPO.convertTcl(prop.getValue())).append("\"");
        }
    }

    /**
     * At the end of the TCL update file the inquiry code must be appended.
     *
     * @param _paramCache   parameter cache
     * @param _out          appendable instance to the TCL update file
     * @throws IOException if the extension could not be written
     * @see #code
     * @see #PARAM_SEPARATOR_COMMENT
     * @see #PARAM_SEPARATOR_TEXT
     */
    @Override()
    protected void writeEnd(final ParameterCache_mxJPO _paramCache,
                            final Appendable _out)
        throws IOException
    {
        _out.append("\n\n").append(_paramCache.getValueString(Inquiry_mxJPO.PARAM_SEPARATOR_COMMENT))
            .append('\n').append(_paramCache.getValueString(Inquiry_mxJPO.PARAM_SEPARATOR_TEXT)).append("\n\n");
        if (this.code != null)  {
            _out.append(this.code);
        }
    }

    /**
     * Updates this inquiry. Because the TCL source code of an inquiry includes
     * also the inquiry code itself, this inquiry code must be separated and
     * written in a temporary file. This temporary file is used while the
     * update is running (defined via TCL variable <code>FILE</code>). After
     * the update, the temporary file is removed (because not needed anymore).
     * Also the MQL statements to reset this inquiry are appended to the
     * statements in <code>_preMQLCode</code> to:
     * <ul>
     * <li>reset the description, {@link #pattern}, {@link #format} and
     *     {@link #code}</li>
     * <li>set to not hidden</li>
     * <li>remove all arguments</li>
     * </ul>
     *
     * @param _paramCache       parameter cache
     * @param _preMQLCode       MQL statements which must be called before the
     *                          TCL code is executed
     * @param _postMQLCode      MQL statements which must be called after the
     *                          TCL code is executed
     * @param _preTCLCode       TCL code which is defined before the source
     *                          file is sourced
     * @param _tclVariables     map of all TCL variables where the key is the
     *                          name and the value is value of the TCL variable
     *                          (the value is automatically converted to TCL
     *                          syntax!)
     * @param _sourceFile       souce file with the TCL code to update
     * @throws Exception if the update from derived class failed
     * @see #PARAM_SEPARATOR_TEXT
     */
    @Override()
    protected void update(final ParameterCache_mxJPO _paramCache,
                          final CharSequence _preMQLCode,
                          final CharSequence _postMQLCode,
                          final CharSequence _preTCLCode,
                          final Map<String,String> _tclVariables,
                          final File _sourceFile)
        throws Exception
    {
        // reset HRef, description, alt, label and height
        final StringBuilder preMQLCode = new StringBuilder()
                .append("escape mod ").append(this.getTypeDef().getMxAdminName())
                .append(" \"").append(StringUtil_mxJPO.convertMql(this.getName())).append('\"')
                .append(" !hidden description \"\" pattern \"\" format \"\" code \"\"");

        // reset arguments
        for (final AdminProperty prop : this.getProperties().getSettings())  {
            preMQLCode.append(" remove argument \"").append(StringUtil_mxJPO.convertMql(prop.getName().substring(1))).append('\"');
        }

        // append already existing pre MQL code
        preMQLCode.append(";\n")
                  .append(_preMQLCode);

        // separate the inquiry code and the TCL code
        final String sep = _paramCache.getValueString(Inquiry_mxJPO.PARAM_SEPARATOR_TEXT);
        final StringBuilder orgCode = this.getCode(_sourceFile);
        final int idx = orgCode.lastIndexOf(sep);
        final CharSequence code = (idx >= 0)
                                  ? orgCode.subSequence(0, idx)
                                  : orgCode;
        final CharSequence inqu = (idx >= 0)
                                  ? orgCode.subSequence(idx + sep.length() + 1,
                                                        orgCode.length())
                                  : "";

        final File tmpTclFile = File.createTempFile("TMP_", ".tcl");
        try  {
            final File tmpInqFile = File.createTempFile("TMP_", ".inquiry");
            try  {
                // write TCL code
                final Writer outTCL = new FileWriter(tmpTclFile);
                try {
                    outTCL.append(code.toString().trim());
                } finally  {
                    outTCL.close();
                }

                // write inquiry code
                final Writer outInq = new FileWriter(tmpInqFile);
                try {
                    outInq.append(inqu.toString().trim());
                } finally  {
                    outInq.close();
                }

                // define TCL variable for the file
                final Map<String,String> tclVariables = new HashMap<String,String>();
                tclVariables.putAll(_tclVariables);
                tclVariables.put("FILE", tmpInqFile.getPath());

                // and update
                super.update(_paramCache, preMQLCode, _postMQLCode, code, tclVariables, tmpTclFile);
            } finally  {
                tmpInqFile.delete();
            }
        } finally  {
            tmpTclFile.delete();
        }
    }

    @Override()
    protected void calcDelta(final ParameterCache_mxJPO _paramCache,
                             final MultiLineMqlBuilder _mql,
                             final Inquiry_mxJPO _current)
        throws UpdateException_mxJPO
    {
    }
}
