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

package org.mxupdate.test.test.update.datamodel.typeci;

import org.mxupdate.test.AbstractTest;
import org.mxupdate.test.data.datamodel.AttributeData;
import org.mxupdate.test.data.datamodel.PathTypeData;
import org.mxupdate.test.data.datamodel.TypeData;
import org.mxupdate.test.data.system.PackageData;
import org.mxupdate.test.data.util.FlagList.Create;
import org.mxupdate.test.test.update.AbstractDeltaCalculationTest;
import org.mxupdate.update.datamodel.Type_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import matrix.util.MatrixException;

/**
 * Tests the {@link Type_mxJPO type CI} delta calculation.
 *
 * @author The MxUpdate Team
 */
@Test
public class TypeCI_2DeltaCalculationTest
    extends AbstractDeltaCalculationTest<Type_mxJPO,TypeData>
{
    @Override
    @DataProvider(name = "data")
    public Object[][] getData()
    {
        return new Object[][] {
            // package
            {"1a) new package",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test").defData("package", new PackageData(this, "TestPackage"))},
            {"1b) update package",
                    new TypeData(this, "Test").defData("package", new PackageData(this, "TestPackage1")),
                    new TypeData(this, "Test").defData("package", new PackageData(this, "TestPackage2"))},
            {"1c) remove package",
                    new TypeData(this, "Test").defData("package", new PackageData(this, "TestPackage")),
                    new TypeData(this, "Test").defKeyNotDefined("package")},
            // uuid
            {"1) uuid",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test").setValue("uuid", "FDA75674979211E6AE2256B6B6499611")},
            {"2a) symbolic name",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test").setValue("symbolicname", "expression_123")},
            {"2b) two symbolic name",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test").setValue("symbolicname", "expression_123").setValue("symbolicname", "expression_345")},
            {"3) description",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test").setValue("description", "abc def")},

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // local attribute

            {"101) binary attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "binary")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"102) boolean attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "boolean")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"103) date attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "date")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setFlag("rangevalue", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"104) integer attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "integer")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setFlag("rangevalue", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"105) real attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "real")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setFlag("rangevalue", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"106) string attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "string")
                                        .setValue("description", "abc def")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setFlag("multiline", false, Create.ViaFlag)
                                        .setSingle("maxlength", "0")
                                        .setValue("default", "")) },
            {"107a) local attribute with new package",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .defData("package", new PackageData(this, "TestPackage"))
                                        .setSingle("kind", "boolean")
                                        .setValue("description", "")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", ""))},
            {"107b) local attribute with package update",
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .defData("package", new PackageData(this, "TestPackage1"))
                                        .setSingle("kind", "boolean")
                                        .setValue("description", "")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", "")),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .defData("package", new PackageData(this, "TestPackage2"))
                                        .setSingle("kind", "boolean")
                                        .setValue("description", "")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", ""))},
            {"108) local attribute with uuid",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "boolean")
                                        .setValue("uuid", "UUID")
                                        .setValue("description", "")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", "")) },
            {"109) attribute with symbolic names",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalAttribute(
                                    new AttributeData(this, "ATTR1")
                                        .setSingle("kind", "boolean")
                                        .setValue("symbolicname", "attribute_" + AbstractTest.PREFIX + "Test." + AbstractTest.PREFIX + "ATTR1")
                                        .setValue("description", "")
                                        .setFlag("hidden", false, Create.ViaFlag)
                                        .setFlag("multivalue", false, Create.ViaFlag)
                                        .setFlag("resetonclone", false, Create.ViaFlag)
                                        .setFlag("resetonrevision", false, Create.ViaFlag)
                                        .setValue("default", ""))},

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // local path type

            {"301) simple local path type",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType").def4FromSingle("cardinality", "many")) },
            {"302) local path type with uuid",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType").setValue("uuid", "UUID").def4FromSingle("cardinality", "many")) },
            {"303) local path type with global attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .defData("attribute", new AttributeData(this, "PathType Attribute").setSingle("kind", "string"))) },
            {"304) local path type with embedded local attribute",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "PathType Attribute")
                                            .setSingle("kind", "string")))},
            {"305a) local path type with embedded local attribute with new package",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "ATTR1")
                                            .defData("package", new PackageData(this, "TestPackage"))
                                            .setSingle("kind", "boolean")
                                            .setValue("description", "")
                                            .setFlag("hidden", false, Create.ViaFlag)
                                            .setFlag("multivalue", false, Create.ViaFlag)
                                            .setFlag("resetonclone", false, Create.ViaFlag)
                                            .setFlag("resetonrevision", false, Create.ViaFlag)
                                            .setValue("default", "")))},
            {"305b) local path type with embedded local attribute with package update",
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "ATTR1")
                                            .defData("package", new PackageData(this, "TestPackage1"))
                                            .setSingle("kind", "boolean")
                                            .setValue("description", "")
                                            .setFlag("hidden", false, Create.ViaFlag)
                                            .setFlag("multivalue", false, Create.ViaFlag)
                                            .setFlag("resetonclone", false, Create.ViaFlag)
                                            .setFlag("resetonrevision", false, Create.ViaFlag)
                                            .setValue("default", ""))),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "ATTR1")
                                            .defData("package", new PackageData(this, "TestPackage2"))
                                            .setSingle("kind", "boolean")
                                            .setValue("description", "")
                                            .setFlag("hidden", false, Create.ViaFlag)
                                            .setFlag("multivalue", false, Create.ViaFlag)
                                            .setFlag("resetonclone", false, Create.ViaFlag)
                                            .setFlag("resetonrevision", false, Create.ViaFlag)
                                            .setValue("default", "")))},
            {"306) local path type with embedded local attribute with uuid",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "PathType Attribute")
                                            .setSingle("kind", "string")
                                            .setValue("uuid", "UUID")))},
            {"307) local path type with embedded local attribute with symbolic name",
                    new TypeData(this, "Test"),
                    new TypeData(this, "Test")
                            .addLocalPathType(new PathTypeData(this, "PathType")
                                    .def4FromSingle("cardinality", "many")
                                    .addLocalAttribute(new AttributeData(this, "PathType Attribute")
                                            .setSingle("kind", "string")
                                            .setValue("symbolicname", "attribute_" + AbstractTest.PREFIX + "Test." + AbstractTest.PREFIX + "Test." + AbstractTest.PREFIX + "PathType_Attribute")))},
       };
    }

    @Override
    @BeforeMethod
    @AfterClass
    public void cleanup()
        throws MatrixException
    {
        this.cleanup(AbstractTest.CI.DM_PATHTYPE);  // as first, so that local attributes of path types are deleted!
        this.cleanup(AbstractTest.CI.DM_ATTRIBUTE);
        this.cleanup(AbstractTest.CI.DM_TYPE);
        this.cleanup(AbstractTest.CI.SYS_PACKAGE);
    }

    @Override
    protected Type_mxJPO createNewData(final ParameterCache_mxJPO _paramCache,
                                       final String _name)
    {
        return new Type_mxJPO(_name);
    }
}
