// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlDomWriter.java

package co.com.pdf.text.xml;

import java.io.*;
import org.w3c.dom.*;

public class XmlDomWriter
{

    public XmlDomWriter()
    {
    }

    public XmlDomWriter(boolean canonical)
    {
        fCanonical = canonical;
    }

    public void setCanonical(boolean canonical)
    {
        fCanonical = canonical;
    }

    public void setOutput(OutputStream stream, String encoding)
        throws UnsupportedEncodingException
    {
        if(encoding == null)
            encoding = "UTF8";
        Writer writer = new OutputStreamWriter(stream, encoding);
        fOut = new PrintWriter(writer);
    }

    public void setOutput(Writer writer)
    {
        fOut = (writer instanceof PrintWriter) ? (PrintWriter)writer : new PrintWriter(writer);
    }

    public void write(Node node)
    {
        if(node == null)
            return;
        short type = node.getNodeType();
        switch(type)
        {
        case 9: // '\t'
            Document document = (Document)node;
            fXML11 = false;
            if(!fCanonical)
            {
                if(fXML11)
                    fOut.println("<?xml version=\"1.1\" encoding=\"UTF-8\"?>");
                else
                    fOut.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                fOut.flush();
                write(((Node) (document.getDoctype())));
            }
            write(((Node) (document.getDocumentElement())));
            break;

        case 10: // '\n'
            DocumentType doctype = (DocumentType)node;
            fOut.print("<!DOCTYPE ");
            fOut.print(doctype.getName());
            String publicId = doctype.getPublicId();
            String systemId = doctype.getSystemId();
            if(publicId != null)
            {
                fOut.print(" PUBLIC '");
                fOut.print(publicId);
                fOut.print("' '");
                fOut.print(systemId);
                fOut.print('\'');
            } else
            if(systemId != null)
            {
                fOut.print(" SYSTEM '");
                fOut.print(systemId);
                fOut.print('\'');
            }
            String internalSubset = doctype.getInternalSubset();
            if(internalSubset != null)
            {
                fOut.println(" [");
                fOut.print(internalSubset);
                fOut.print(']');
            }
            fOut.println('>');
            break;

        case 1: // '\001'
            fOut.print('<');
            fOut.print(node.getNodeName());
            Attr attrs[] = sortAttributes(node.getAttributes());
            for(int i = 0; i < attrs.length; i++)
            {
                Attr attr = attrs[i];
                fOut.print(' ');
                fOut.print(attr.getNodeName());
                fOut.print("=\"");
                normalizeAndPrint(attr.getNodeValue(), true);
                fOut.print('"');
            }

            fOut.print('>');
            fOut.flush();
            for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
                write(child);

            break;

        case 5: // '\005'
            if(fCanonical)
            {
                for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
                    write(child);

            } else
            {
                fOut.print('&');
                fOut.print(node.getNodeName());
                fOut.print(';');
                fOut.flush();
            }
            break;

        case 4: // '\004'
            if(fCanonical)
            {
                normalizeAndPrint(node.getNodeValue(), false);
            } else
            {
                fOut.print("<![CDATA[");
                fOut.print(node.getNodeValue());
                fOut.print("]]>");
            }
            fOut.flush();
            break;

        case 3: // '\003'
            normalizeAndPrint(node.getNodeValue(), false);
            fOut.flush();
            break;

        case 7: // '\007'
            fOut.print("<?");
            fOut.print(node.getNodeName());
            String data = node.getNodeValue();
            if(data != null && data.length() > 0)
            {
                fOut.print(' ');
                fOut.print(data);
            }
            fOut.print("?>");
            fOut.flush();
            break;

        case 8: // '\b'
            if(!fCanonical)
            {
                fOut.print("<!--");
                String comment = node.getNodeValue();
                if(comment != null && comment.length() > 0)
                    fOut.print(comment);
                fOut.print("-->");
                fOut.flush();
            }
            break;
        }
        if(type == 1)
        {
            fOut.print("</");
            fOut.print(node.getNodeName());
            fOut.print('>');
            fOut.flush();
        }
    }

    protected Attr[] sortAttributes(NamedNodeMap attrs)
    {
        int len = attrs == null ? 0 : attrs.getLength();
        Attr array[] = new Attr[len];
        for(int i = 0; i < len; i++)
            array[i] = (Attr)attrs.item(i);

        for(int i = 0; i < len - 1; i++)
        {
            String name = array[i].getNodeName();
            int index = i;
            for(int j = i + 1; j < len; j++)
            {
                String curName = array[j].getNodeName();
                if(curName.compareTo(name) < 0)
                {
                    name = curName;
                    index = j;
                }
            }

            if(index != i)
            {
                Attr temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        return array;
    }

    protected void normalizeAndPrint(String s, boolean isAttValue)
    {
        int len = s == null ? 0 : s.length();
        for(int i = 0; i < len; i++)
        {
            char c = s.charAt(i);
            normalizeAndPrint(c, isAttValue);
        }

    }

    protected void normalizeAndPrint(char c, boolean isAttValue)
    {
        switch(c)
        {
        case 60: // '<'
            fOut.print("&lt;");
            break;

        case 62: // '>'
            fOut.print("&gt;");
            break;

        case 38: // '&'
            fOut.print("&amp;");
            break;

        case 34: // '"'
            if(isAttValue)
                fOut.print("&quot;");
            else
                fOut.print("\"");
            break;

        case 13: // '\r'
            fOut.print("&#xD;");
            break;

        case 10: // '\n'
            if(fCanonical)
            {
                fOut.print("&#xA;");
                break;
            }
            // fall through

        default:
            if(fXML11 && (c >= '\001' && c <= '\037' && c != '\t' && c != '\n' || c >= '\177' && c <= '\237' || c == '\u2028') || isAttValue && (c == '\t' || c == '\n'))
            {
                fOut.print("&#x");
                fOut.print(Integer.toHexString(c).toUpperCase());
                fOut.print(";");
            } else
            {
                fOut.print(c);
            }
            break;
        }
    }

    protected PrintWriter fOut;
    protected boolean fCanonical;
    protected boolean fXML11;
}
