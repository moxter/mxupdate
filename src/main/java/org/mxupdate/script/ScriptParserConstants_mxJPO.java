/* Generated By:JavaCC: Do not edit this line. ScriptParserConstants.java */
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

package org.mxupdate.script;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
interface ScriptParserConstants_mxJPO {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int MXUPDATE = 7;
  /** RegularExpression Id. */
  int MXUPDATE_STRING = 8;
  /** RegularExpression Id. */
  int MXUPDATE_SINGLE = 9;
  /** RegularExpression Id. */
  int MXUPDATE_CODE_START = 10;
  /** RegularExpression Id. */
  int MXUPDATE_CODE_BRACE_START = 11;
  /** RegularExpression Id. */
  int MXUPDATE_CODE_BRACE_END = 12;
  /** RegularExpression Id. */
  int MXUPDATE_CODE_TEXT = 13;
  /** RegularExpression Id. */
  int MXUPDATE_CODE_STRING = 14;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int MXUPDATE_EXPECTED = 1;
  /** Lexical state. */
  int MXUPDATE_CODE_EXPECTED = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "<token of kind 4>",
    "\" \"",
    "\"\\t\"",
    "\"mxUpdate\"",
    "<MXUPDATE_STRING>",
    "<MXUPDATE_SINGLE>",
    "\"{\"",
    "\"{\"",
    "\"}\"",
    "<MXUPDATE_CODE_TEXT>",
    "<MXUPDATE_CODE_STRING>",
  };

}