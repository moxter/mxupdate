/* Generated By:JavaCC: Do not edit this line. AttributeDefParser.java */
package org.mxupdate.update.datamodel.attribute;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mxupdate.mapping.TypeDef_mxJPO;
import org.mxupdate.update.datamodel.AbstractAttribute_mxJPO;
import org.mxupdate.update.datamodel.AbstractAttribute_mxJPO.Range;
import org.mxupdate.update.datamodel.AbstractDMWithTriggers_mxJPO.Trigger;
import org.mxupdate.update.util.AbstractParser_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;

public class AttributeDefParser_mxJPO
    extends AbstractParser_mxJPO implements AttributeDefParserConstants_mxJPO {

/**
 * Parses one complete policy definition.
 *
 * @param _paramCache   parameter cache
 * @param _typeDef      type definition of the policy (to instantiate the
 *                      policy)
 * @param _mxName       MX name of the policy
 */
  final public AbstractAttribute_mxJPO attribute(final ParameterCache_mxJPO _paramCache,
                                  final TypeDef_mxJPO _typeDef,
                                  final String _mxName) throws ParseException_mxJPO, SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final AbstractAttribute_mxJPO attr = (AbstractAttribute_mxJPO) _typeDef.newTypeInstance(_mxName);
    Token_mxJPO tmp;
    Trigger trigger;
    Range range;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DESCRIPTION:
      case DEFAULTVALUE:
      case HIDDEN_TRUE:
      case HIDDEN_FALSE:
      case RESETONCLONE_TRUE:
      case RESETONCLONE_FALSE:
      case RESETONREVISION_TRUE:
      case RESETONREVISION_FALSE:
      case RANGEVALUE_TRUE:
      case RANGEVALUE_FALSE:
      case MULTILINE_TRUE:
      case MULTILINE_FALSE:
      case MAXLENGTH:
      case RULE:
      case TRIG_MODIFY_ACTION:
      case TRIG_MODIFY_CHECK:
      case TRIG_MODIFY_OVERRIDE:
      case RANGE_EQUAL:
      case RANGE_GREATERTHAN:
      case RANGE_GREATERTHANEQUAL:
      case RANGE_LESSTHAN:
      case RANGE_LESSTHANEQUAL:
      case RANGE_NOTEQUAL:
      case RANGE_MATCH:
      case RANGE_NOTMATCH:
      case RANGE_SMATCH:
      case RANGE_NOTSMATCH:
      case RANGE_PROGRAM:
      case RANGE_BETWEEN:
        ;
        break;
      default:
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DESCRIPTION:
        jj_consume_token(DESCRIPTION);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(attr, "description", this.getString(tmp.image));
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(attr, "description", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case DEFAULTVALUE:
        jj_consume_token(DEFAULTVALUE);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(attr, "defaultValue", this.getString(tmp.image));
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(attr, "defaultValue", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case HIDDEN_TRUE:
        jj_consume_token(HIDDEN_TRUE);
                                                                this.setValue(attr, "hidden", true);
        break;
      case HIDDEN_FALSE:
        jj_consume_token(HIDDEN_FALSE);
                                                                this.setValue(attr, "hidden", false);
        break;
      case RESETONCLONE_TRUE:
        jj_consume_token(RESETONCLONE_TRUE);
                                                                this.setValue(attr, "resetOnClone", true);
        break;
      case RESETONCLONE_FALSE:
        jj_consume_token(RESETONCLONE_FALSE);
                                                                this.setValue(attr, "resetOnClone", false);
        break;
      case RESETONREVISION_TRUE:
        jj_consume_token(RESETONREVISION_TRUE);
                                                                this.setValue(attr, "resetOnRevision", true);
        break;
      case RESETONREVISION_FALSE:
        jj_consume_token(RESETONREVISION_FALSE);
                                                                this.setValue(attr, "resetOnRevision", false);
        break;
      case RANGEVALUE_TRUE:
        jj_consume_token(RANGEVALUE_TRUE);
                                                                this.setValue(attr, "rangeValue", true);
        break;
      case RANGEVALUE_FALSE:
        jj_consume_token(RANGEVALUE_FALSE);
                                                                this.setValue(attr, "rangeValue", false);
        break;
      case MULTILINE_TRUE:
        jj_consume_token(MULTILINE_TRUE);
                                                                this.setValue(attr, "multiline", true);
        break;
      case MULTILINE_FALSE:
        jj_consume_token(MULTILINE_FALSE);
                                                                this.setValue(attr, "multiline", false);
        break;
      case MAXLENGTH:
        jj_consume_token(MAXLENGTH);
        tmp = jj_consume_token(MAXLENGTH_NUMBER);
                                                                this.setValue(attr, "maxLength", this.getSingle(tmp.image));
        break;
      case RULE:
        jj_consume_token(RULE);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(attr, "rules", Arrays.asList(new String[]{this.getString(tmp.image)}));
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(attr, "rules", Arrays.asList(new String[]{this.getSingle(tmp.image)}));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case TRIG_MODIFY_ACTION:
        jj_consume_token(TRIG_MODIFY_ACTION);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TRIGPROG_STRING:
          tmp = jj_consume_token(TRIGPROG_STRING);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyAction");this.setValue(trigger, "program", this.getString(tmp.image));
          break;
        case TRIGPROG_SINGLE:
          tmp = jj_consume_token(TRIGPROG_SINGLE);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyAction");this.setValue(trigger, "program", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        jj_consume_token(TRIGGERINPUT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(trigger, "arguments", this.getString(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(trigger, "arguments", this.getSingle(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case TRIG_MODIFY_CHECK:
        jj_consume_token(TRIG_MODIFY_CHECK);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TRIGPROG_STRING:
          tmp = jj_consume_token(TRIGPROG_STRING);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyCheck");this.setValue(trigger, "program", this.getString(tmp.image));
          break;
        case TRIGPROG_SINGLE:
          tmp = jj_consume_token(TRIGPROG_SINGLE);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyCheck");this.setValue(trigger, "program", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        jj_consume_token(TRIGGERINPUT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(trigger, "arguments", this.getString(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(trigger, "arguments", this.getSingle(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case TRIG_MODIFY_OVERRIDE:
        jj_consume_token(TRIG_MODIFY_OVERRIDE);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TRIGPROG_STRING:
          tmp = jj_consume_token(TRIGPROG_STRING);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyOverride");this.setValue(trigger, "program", this.getString(tmp.image));
          break;
        case TRIGPROG_SINGLE:
          tmp = jj_consume_token(TRIGPROG_SINGLE);
                                                                trigger = new Trigger();this.setValue(trigger, "name", "ModifyOverride");this.setValue(trigger, "program", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        jj_consume_token(TRIGGERINPUT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                this.setValue(trigger, "arguments", this.getString(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                this.setValue(trigger, "arguments", this.getSingle(tmp.image));this.appendValue(attr, "triggersStack", trigger);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_EQUAL:
        jj_consume_token(RANGE_EQUAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "=");      this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "=");      this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_GREATERTHAN:
        jj_consume_token(RANGE_GREATERTHAN);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", ">");      this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", ">");      this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_GREATERTHANEQUAL:
        jj_consume_token(RANGE_GREATERTHANEQUAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", ">=");     this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", ">=");     this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_LESSTHAN:
        jj_consume_token(RANGE_LESSTHAN);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "<");      this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "<");      this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_LESSTHANEQUAL:
        jj_consume_token(RANGE_LESSTHANEQUAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "<=");     this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "<=");     this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_NOTEQUAL:
        jj_consume_token(RANGE_NOTEQUAL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "!=");     this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "!=");     this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_MATCH:
        jj_consume_token(RANGE_MATCH);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "match");  this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "match");  this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_NOTMATCH:
        jj_consume_token(RANGE_NOTMATCH);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "!match"); this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "!match"); this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_SMATCH:
        jj_consume_token(RANGE_SMATCH);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "smatch"); this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "smatch"); this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_NOTSMATCH:
        jj_consume_token(RANGE_NOTSMATCH);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "!smatch");this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "!smatch");this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      case RANGE_PROGRAM:
        jj_consume_token(RANGE_PROGRAM);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tmp = jj_consume_token(STRING);
                                                                range = new Range();this.setValue(range, "type", "program");this.setValue(range, "value1", this.getString(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        case SINGLE:
          tmp = jj_consume_token(SINGLE);
                                                                range = new Range();this.setValue(range, "type", "program");this.setValue(range, "value1", this.getSingle(tmp.image));this.appendValue(attr, "rangesStack", range);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INPUT:
          jj_consume_token(INPUT);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case STRING:
            tmp = jj_consume_token(STRING);
                                                                this.setValue(range, "value2", this.getString(tmp.image));
            break;
          case SINGLE:
            tmp = jj_consume_token(SINGLE);
                                                                this.setValue(range, "value2", this.getSingle(tmp.image));
            break;
          default:
            jj_consume_token(-1);
            throw new ParseException_mxJPO();
          }
          break;
        default:
          ;
        }
        break;
      case RANGE_BETWEEN:
        jj_consume_token(RANGE_BETWEEN);
                                                                range = new Range();this.setValue(range, "type", "between");this.appendValue(attr, "rangesStack", range);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BETW1_STRING:
          tmp = jj_consume_token(BETW1_STRING);
                                                                this.setValue(range, "value1", this.getString(tmp.image));
          break;
        case BETW1_SINGLE:
          tmp = jj_consume_token(BETW1_SINGLE);
                                                                this.setValue(range, "value1", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BETW2_INCLUSIVE:
          jj_consume_token(BETW2_INCLUSIVE);
                                                                this.setValue(range, "include1", true);
          break;
        case BETW2_EXCLUSIVE:
          jj_consume_token(BETW2_EXCLUSIVE);
                                                                this.setValue(range, "include1", false);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BETW3_STRING:
          tmp = jj_consume_token(BETW3_STRING);
                                                                this.setValue(range, "value2", this.getString(tmp.image));
          break;
        case BETW3_SINGLE:
          tmp = jj_consume_token(BETW3_SINGLE);
                                                                this.setValue(range, "value2", this.getSingle(tmp.image));
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BETW4_INCLUSIVE:
          jj_consume_token(BETW4_INCLUSIVE);
                                                                this.setValue(range, "include2", true);
          break;
        case BETW4_EXCLUSIVE:
          jj_consume_token(BETW4_EXCLUSIVE);
                                                                this.setValue(range, "include2", false);
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException_mxJPO();
        }
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException_mxJPO();
      }
    }
        this.prepareObject(_paramCache, attr);
        {if (true) return attr;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public AttributeDefParserTokenManager_mxJPO token_source;
  SimpleCharStream_mxJPO jj_input_stream;
  /** Current token. */
  public Token_mxJPO token;
  /** Next token. */
  public Token_mxJPO jj_nt;
  private int jj_ntk;

  /** Constructor with InputStream. */
  public AttributeDefParser_mxJPO(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AttributeDefParser_mxJPO(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream_mxJPO(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AttributeDefParserTokenManager_mxJPO(jj_input_stream);
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  /** Constructor. */
  public AttributeDefParser_mxJPO(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream_mxJPO(stream, 1, 1);
    token_source = new AttributeDefParserTokenManager_mxJPO(jj_input_stream);
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  /** Constructor with generated Token Manager. */
  public AttributeDefParser_mxJPO(AttributeDefParserTokenManager_mxJPO tm) {
    token_source = tm;
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(AttributeDefParserTokenManager_mxJPO tm) {
    token_source = tm;
    token = new Token_mxJPO();
    jj_ntk = -1;
  }

  private Token_mxJPO jj_consume_token(int kind) throws ParseException_mxJPO {
    Token_mxJPO oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      return token;
    }
    token = oldToken;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token_mxJPO getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    return token;
  }

/** Get the specific Token. */
  final public Token_mxJPO getToken(int index) {
    Token_mxJPO t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /** Generate ParseException. */
  public ParseException_mxJPO generateParseException() {
    Token_mxJPO errortok = token.next;
    int line = errortok.beginLine, column = errortok.beginColumn;
    String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
    return new ParseException_mxJPO("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
