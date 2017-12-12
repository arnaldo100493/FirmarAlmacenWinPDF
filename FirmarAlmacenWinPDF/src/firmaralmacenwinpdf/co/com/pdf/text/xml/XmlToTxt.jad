// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlToTxt.java

package co.com.pdf.text.xml;

import co.com.pdf.text.xml.simpleparser.SimpleXMLDocHandler;
import co.com.pdf.text.xml.simpleparser.SimpleXMLParser;
import java.io.*;
import java.util.Map;

public class XmlToTxt
    implements SimpleXMLDocHandler
{

    public static String parse(InputStream is)
        throws IOException
    {
        XmlToTxt handler = new XmlToTxt();
        SimpleXMLParser.parse(handler, null, new InputStreamReader(is), true);
        return handler.toString();
    }

    protected XmlToTxt()
    {
        buf = new StringBuffer();
    }

    public String toString()
    {
        return buf.toString();
    }

    public void startElement(String s, Map map)
    {
    }

    public void endElement(String s)
    {
    }

    public void startDocument()
    {
    }

    public void endDocument()
    {
    }

    public void text(String str)
    {
        buf.append(str);
    }

    protected StringBuffer buf;
}
