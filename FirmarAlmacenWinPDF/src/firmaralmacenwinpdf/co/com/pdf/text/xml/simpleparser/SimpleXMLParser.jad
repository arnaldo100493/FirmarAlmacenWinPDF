// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleXMLParser.java

package co.com.pdf.text.xml.simpleparser;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.xml.XMLUtil;
import co.com.pdf.text.xml.simpleparser.handler.HTMLNewLineHandler;
import co.com.pdf.text.xml.simpleparser.handler.NeverNewLineHandler;
import java.io.*;
import java.util.HashMap;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.xml.simpleparser:
//            SimpleXMLDocHandler, SimpleXMLDocHandlerComment, EntitiesToUnicode, NewLineHandler, 
//            IanaEncodings

public final class SimpleXMLParser
{

    private SimpleXMLParser(SimpleXMLDocHandler doc, SimpleXMLDocHandlerComment comment, boolean html)
    {
        character = 0;
        previousCharacter = -1;
        lines = 1;
        columns = 0;
        eol = false;
        nowhite = false;
        tag = null;
        attributes = null;
        nested = 0;
        quoteCharacter = 34;
        attributekey = null;
        attributevalue = null;
        this.doc = doc;
        this.comment = comment;
        this.html = html;
        if(html)
            newLineHandler = new HTMLNewLineHandler();
        else
            newLineHandler = new NeverNewLineHandler();
        state = html ? 1 : 0;
    }

    private void go(Reader r)
        throws IOException
    {
        BufferedReader reader;
        if(r instanceof BufferedReader)
            reader = (BufferedReader)r;
        else
            reader = new BufferedReader(r);
        doc.startDocument();
        do
        {
            if(previousCharacter == -1)
            {
                character = reader.read();
            } else
            {
                character = previousCharacter;
                previousCharacter = -1;
            }
            if(character == -1)
            {
                if(html)
                {
                    if(html && state == 1)
                        flush();
                    doc.endDocument();
                } else
                {
                    throwException(MessageLocalization.getComposedMessage("missing.end.tag", new Object[0]));
                }
                return;
            }
            if(character == 10 && eol)
            {
                eol = false;
            } else
            {
                if(eol)
                    eol = false;
                else
                if(character == 10)
                {
                    lines++;
                    columns = 0;
                } else
                if(character == 13)
                {
                    eol = true;
                    character = 10;
                    lines++;
                    columns = 0;
                } else
                {
                    columns++;
                }
                switch(state)
                {
                default:
                    break;

                case 0: // '\0'
                    if(character == 60)
                    {
                        saveState(1);
                        state = 2;
                    }
                    break;

                case 1: // '\001'
                    if(character == 60)
                    {
                        flush();
                        saveState(state);
                        state = 2;
                        break;
                    }
                    if(character == 38)
                    {
                        saveState(state);
                        entity.setLength(0);
                        state = 10;
                        nowhite = true;
                        break;
                    }
                    if(character == 32)
                    {
                        if(html && nowhite)
                        {
                            text.append(' ');
                            nowhite = false;
                        } else
                        {
                            if(nowhite)
                                text.append((char)character);
                            nowhite = false;
                        }
                        break;
                    }
                    if(Character.isWhitespace((char)character))
                    {
                        if(!html)
                        {
                            if(nowhite)
                                text.append((char)character);
                            nowhite = false;
                        }
                    } else
                    {
                        text.append((char)character);
                        nowhite = true;
                    }
                    break;

                case 2: // '\002'
                    initTag();
                    if(character == 47)
                        state = 5;
                    else
                    if(character == 63)
                    {
                        restoreState();
                        state = 9;
                    } else
                    {
                        text.append((char)character);
                        state = 3;
                    }
                    break;

                case 3: // '\003'
                    if(character == 62)
                    {
                        doTag();
                        processTag(true);
                        initTag();
                        state = restoreState();
                    } else
                    if(character == 47)
                        state = 6;
                    else
                    if(character == 45 && text.toString().equals("!-"))
                    {
                        flush();
                        state = 8;
                    } else
                    if(character == 91 && text.toString().equals("![CDATA"))
                    {
                        flush();
                        state = 7;
                    } else
                    if(character == 69 && text.toString().equals("!DOCTYP"))
                    {
                        flush();
                        state = 9;
                    } else
                    if(Character.isWhitespace((char)character))
                    {
                        doTag();
                        state = 4;
                    } else
                    {
                        text.append((char)character);
                    }
                    break;

                case 4: // '\004'
                    if(character == 62)
                    {
                        processTag(true);
                        initTag();
                        state = restoreState();
                        break;
                    }
                    if(character == 47)
                    {
                        state = 6;
                        break;
                    }
                    if(!Character.isWhitespace((char)character))
                    {
                        text.append((char)character);
                        state = 12;
                    }
                    break;

                case 5: // '\005'
                    if(character == 62)
                    {
                        doTag();
                        processTag(false);
                        if(!html && nested == 0)
                            return;
                        state = restoreState();
                        break;
                    }
                    if(!Character.isWhitespace((char)character))
                        text.append((char)character);
                    break;

                case 6: // '\006'
                    if(character != 62)
                        throwException(MessageLocalization.getComposedMessage("expected.gt.for.tag.lt.1.gt", new Object[] {
                            tag
                        }));
                    doTag();
                    processTag(true);
                    processTag(false);
                    initTag();
                    if(!html && nested == 0)
                    {
                        doc.endDocument();
                        return;
                    }
                    state = restoreState();
                    break;

                case 7: // '\007'
                    if(character == 62 && text.toString().endsWith("]]"))
                    {
                        text.setLength(text.length() - 2);
                        flush();
                        state = restoreState();
                    } else
                    {
                        text.append((char)character);
                    }
                    break;

                case 8: // '\b'
                    if(character == 62 && text.toString().endsWith("--"))
                    {
                        text.setLength(text.length() - 2);
                        flush();
                        state = restoreState();
                    } else
                    {
                        text.append((char)character);
                    }
                    break;

                case 9: // '\t'
                    if(character != 62)
                        break;
                    state = restoreState();
                    if(state == 1)
                        state = 0;
                    break;

                case 10: // '\n'
                    if(character == 59)
                    {
                        state = restoreState();
                        String cent = entity.toString();
                        entity.setLength(0);
                        char ce = EntitiesToUnicode.decodeEntity(cent);
                        if(ce == 0)
                            text.append('&').append(cent).append(';');
                        else
                            text.append(ce);
                    } else
                    if(character != 35 && (character < 48 || character > 57) && (character < 97 || character > 122) && (character < 65 || character > 90) || entity.length() >= 7)
                    {
                        state = restoreState();
                        previousCharacter = character;
                        text.append('&').append(entity.toString());
                        entity.setLength(0);
                    } else
                    {
                        entity.append((char)character);
                    }
                    break;

                case 11: // '\013'
                    if(html && quoteCharacter == 32 && character == 62)
                    {
                        flush();
                        processTag(true);
                        initTag();
                        state = restoreState();
                    } else
                    if(html && quoteCharacter == 32 && Character.isWhitespace((char)character))
                    {
                        flush();
                        state = 4;
                    } else
                    if(html && quoteCharacter == 32)
                        text.append((char)character);
                    else
                    if(character == quoteCharacter)
                    {
                        flush();
                        state = 4;
                    } else
                    if(" \r\n\t".indexOf(character) >= 0)
                        text.append(' ');
                    else
                    if(character == 38)
                    {
                        saveState(state);
                        state = 10;
                        entity.setLength(0);
                    } else
                    {
                        text.append((char)character);
                    }
                    break;

                case 12: // '\f'
                    if(Character.isWhitespace((char)character))
                    {
                        flush();
                        state = 13;
                    } else
                    if(character == 61)
                    {
                        flush();
                        state = 14;
                    } else
                    if(html && character == 62)
                    {
                        text.setLength(0);
                        processTag(true);
                        initTag();
                        state = restoreState();
                    } else
                    {
                        text.append((char)character);
                    }
                    break;

                case 13: // '\r'
                    if(character == 61)
                    {
                        state = 14;
                        break;
                    }
                    if(!Character.isWhitespace((char)character))
                        if(html && character == 62)
                        {
                            text.setLength(0);
                            processTag(true);
                            initTag();
                            state = restoreState();
                        } else
                        if(html && character == 47)
                        {
                            flush();
                            state = 6;
                        } else
                        if(html)
                        {
                            flush();
                            text.append((char)character);
                            state = 12;
                        } else
                        {
                            throwException(MessageLocalization.getComposedMessage("error.in.attribute.processing", new Object[0]));
                        }
                    break;

                case 14: // '\016'
                    if(character == 34 || character == 39)
                    {
                        quoteCharacter = character;
                        state = 11;
                        break;
                    }
                    if(!Character.isWhitespace((char)character))
                        if(html && character == 62)
                        {
                            flush();
                            processTag(true);
                            initTag();
                            state = restoreState();
                        } else
                        if(html)
                        {
                            text.append((char)character);
                            quoteCharacter = 32;
                            state = 11;
                        } else
                        {
                            throwException(MessageLocalization.getComposedMessage("error.in.attribute.processing", new Object[0]));
                        }
                    break;
                }
            }
        } while(true);
    }

    private int restoreState()
    {
        if(!stack.empty())
            return ((Integer)stack.pop()).intValue();
        else
            return 0;
    }

    private void saveState(int s)
    {
        stack.push(Integer.valueOf(s));
    }

    private void flush()
    {
        switch(state)
        {
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 9: // '\t'
        case 10: // '\n'
        case 13: // '\r'
        default:
            break;

        case 1: // '\001'
        case 7: // '\007'
            if(text.length() > 0)
                doc.text(text.toString());
            break;

        case 8: // '\b'
            if(comment != null)
                comment.comment(text.toString());
            break;

        case 12: // '\f'
            attributekey = text.toString();
            if(html)
                attributekey = attributekey.toLowerCase();
            break;

        case 11: // '\013'
        case 14: // '\016'
            attributevalue = text.toString();
            attributes.put(attributekey, attributevalue);
            break;
        }
        text.setLength(0);
    }

    private void initTag()
    {
        tag = null;
        attributes = new HashMap();
    }

    private void doTag()
    {
        if(tag == null)
            tag = text.toString();
        if(html)
            tag = tag.toLowerCase();
        text.setLength(0);
    }

    private void processTag(boolean start)
    {
        if(start)
        {
            nested++;
            doc.startElement(tag, attributes);
        } else
        {
            if(newLineHandler.isNewLineTag(tag))
                nowhite = false;
            nested--;
            doc.endElement(tag);
        }
    }

    private void throwException(String s)
        throws IOException
    {
        throw new IOException(MessageLocalization.getComposedMessage("1.near.line.2.column.3", new Object[] {
            s, String.valueOf(lines), String.valueOf(columns)
        }));
    }

    public static void parse(SimpleXMLDocHandler doc, SimpleXMLDocHandlerComment comment, Reader r, boolean html)
        throws IOException
    {
        SimpleXMLParser parser = new SimpleXMLParser(doc, comment, html);
        parser.go(r);
    }

    public static void parse(SimpleXMLDocHandler doc, InputStream in)
        throws IOException
    {
        byte b4[] = new byte[4];
        int count = in.read(b4);
        if(count != 4)
            throw new IOException(MessageLocalization.getComposedMessage("insufficient.length", new Object[0]));
        String encoding = XMLUtil.getEncodingName(b4);
        String decl = null;
        if(encoding.equals("UTF-8"))
        {
            StringBuffer sb = new StringBuffer();
            int c;
            while((c = in.read()) != -1 && c != 62) 
                sb.append((char)c);
            decl = sb.toString();
        } else
        if(encoding.equals("CP037"))
        {
            ByteArrayOutputStream bi = new ByteArrayOutputStream();
            int c;
            while((c = in.read()) != -1 && c != 110) 
                bi.write(c);
            decl = new String(bi.toByteArray(), "CP037");
        }
        if(decl != null)
        {
            decl = getDeclaredEncoding(decl);
            if(decl != null)
                encoding = decl;
        }
        parse(doc, ((Reader) (new InputStreamReader(in, IanaEncodings.getJavaEncoding(encoding)))));
    }

    private static String getDeclaredEncoding(String decl)
    {
        if(decl == null)
            return null;
        int idx = decl.indexOf("encoding");
        if(idx < 0)
            return null;
        int idx1 = decl.indexOf('"', idx);
        int idx2 = decl.indexOf('\'', idx);
        if(idx1 == idx2)
            return null;
        if(idx1 < 0 && idx2 > 0 || idx2 > 0 && idx2 < idx1)
        {
            int idx3 = decl.indexOf('\'', idx2 + 1);
            if(idx3 < 0)
                return null;
            else
                return decl.substring(idx2 + 1, idx3);
        }
        if(idx2 < 0 && idx1 > 0 || idx1 > 0 && idx1 < idx2)
        {
            int idx3 = decl.indexOf('"', idx1 + 1);
            if(idx3 < 0)
                return null;
            else
                return decl.substring(idx1 + 1, idx3);
        } else
        {
            return null;
        }
    }

    public static void parse(SimpleXMLDocHandler doc, Reader r)
        throws IOException
    {
        parse(doc, null, r, false);
    }

    /**
     * @deprecated Method escapeXML is deprecated
     */

    public static String escapeXML(String s, boolean onlyASCII)
    {
        return XMLUtil.escapeXML(s, onlyASCII);
    }

    private static final int UNKNOWN = 0;
    private static final int TEXT = 1;
    private static final int TAG_ENCOUNTERED = 2;
    private static final int EXAMIN_TAG = 3;
    private static final int TAG_EXAMINED = 4;
    private static final int IN_CLOSETAG = 5;
    private static final int SINGLE_TAG = 6;
    private static final int CDATA = 7;
    private static final int COMMENT = 8;
    private static final int PI = 9;
    private static final int ENTITY = 10;
    private static final int QUOTE = 11;
    private static final int ATTRIBUTE_KEY = 12;
    private static final int ATTRIBUTE_EQUAL = 13;
    private static final int ATTRIBUTE_VALUE = 14;
    private final Stack stack = new Stack();
    private int character;
    private int previousCharacter;
    private int lines;
    private int columns;
    private boolean eol;
    private boolean nowhite;
    private int state;
    private final boolean html;
    private final StringBuffer text = new StringBuffer();
    private final StringBuffer entity = new StringBuffer();
    private String tag;
    private HashMap attributes;
    private final SimpleXMLDocHandler doc;
    private final SimpleXMLDocHandlerComment comment;
    private int nested;
    private int quoteCharacter;
    private String attributekey;
    private String attributevalue;
    private NewLineHandler newLineHandler;
}
