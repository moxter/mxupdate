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

package org.mxupdate.test.data.program;

import matrix.util.MatrixException;

import org.mxupdate.test.AbstractTest;
import org.mxupdate.test.ExportParser;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The class is used to define all MQL program objects used to create / update
 * and to export.
 *
 * @author The MxUpdate Team
 */
@Test()
public class MQLProgramData
    extends AbstractProgramData<MQLProgramData>
{
    /**
     * Start marker for the update code.
     */
    private static final String MARK_START
            = "################################################################################\n"
            + "# START NEEDED MQL UPDATE FOR THIS PROGRAM                                     #\n"
            + "################################################################################";

    /**
     * End marker for the update code.
     */
    private static final String MARK_END
            = "################################################################################\n"
            + "# END NEEDED MQL UPDATE FOR THIS PROGRAM                                       #\n"
            + "################################################################################";

    /**
     * Initializes this MQL program.
     *
     * @param _test     related test instance
     * @param _name     name of the MQL program
     */
    public MQLProgramData(final AbstractTest _test,
                          final String _name)
    {
        super(_test, AbstractTest.CI.PRG_MQL_PROGRAM, _name);
    }

    /**
     * Creates this MQL program within MX.
     *
     * @return this MQL program instance
     * @throws MatrixException if create of the MQL program failed
     */
    @Override()
    public MQLProgramData create()
        throws MatrixException
    {
        if (!this.isCreated())  {
            final StringBuilder cmd = new StringBuilder()
                    .append("escape add program \"").append(AbstractTest.convertMql(this.getName()))
                    .append("\" mql ")
                    .append("code \"").append(AbstractTest.convertMql(this.getCode())).append("\"");
            this.append4Create(cmd);
            cmd.append(";\n")
               .append("escape add property ").append(this.getSymbolicName())
               .append(" on program eServiceSchemaVariableMapping.tcl")
               .append(" to program \"").append(AbstractTest.convertMql(this.getName())).append("\"");

            this.getTest().mql(cmd);
            this.setCreated(true);
        }
        return this;
    }

    /**
     * Returns the configuration item file name of this MQL program. The
     * configuration item file name of a MQL program excludes the
     * <code>.tcl</code> extension.
     *
     * @return file name of a JPO
     */
    @Override()
    public String getCIFileName()
    {
        final String ciFileName = super.getCIFileName();
        return ciFileName.replaceAll("\\.tcl$", "");
    }

    /**
     * The related configuration item file is the {@link #code} of the program.
     *
     *  @return {@link #code} of the program
     *  @see #code
     */
    @Override()
    public String ciFile()
    {
        final StringBuilder cmd = new StringBuilder()
           .append(MQLProgramData.MARK_START)
           .append("\nmql escape mod program ${NAME} ");
        this.append4CIFileValues(cmd);
        cmd.append("\n")
           .append(MQLProgramData.MARK_END)
           .append("\n");

        final StringBuilder ciFile = new StringBuilder();
        for (final String line : cmd.toString().split("\n"))  {
            if (this.getName().endsWith(".tcl"))  {
                ciFile.append('#');
            }
            ciFile.append(line).append('\n');
        }
        ciFile.append('\n').append(this.getCode());

        return ciFile.toString();
    }

    /**
     * {@inheritDoc}
     * The source code of the MQL program is checked. Because it could be that
     * some properties are defined, the MQL update code within the source code
     * will be removed for the test.
     */
    @Override()
    public void checkExport(final ExportParser _exportParser)
    {
        super.checkExport(_exportParser);

        // check MQL code
        final String markEnd;
        if (this.getName().endsWith(".tcl"))  {
            final StringBuilder markEndBuilder = new StringBuilder();
            for (final String line : MQLProgramData.MARK_END.split("\n"))  {
                markEndBuilder.append('#').append(line).append('\n');
            }
            markEnd = markEndBuilder.toString();
        } else  {
            markEnd = MQLProgramData.MARK_END;
        }
        final int index = _exportParser.getOrigCode().indexOf(markEnd);
        Assert.assertEquals(
                ((index > 0) ? _exportParser.getOrigCode().substring(index + markEnd.length()).trim() : _exportParser.getOrigCode()).trim(),
                this.getCode(),
                "checks MQL program code");
    }
}
