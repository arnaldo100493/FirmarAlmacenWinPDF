// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LangAlt.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.text.xml.XMLUtil;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @deprecated Class LangAlt is deprecated
 */

public class LangAlt extends Properties
{

    public LangAlt(String defaultValue)
    {
        addLanguage("x-default", defaultValue);
    }

    public LangAlt()
    {
    }

    public void addLanguage(String language, String value)
    {
        setProperty(language, XMLUtil.escapeXML(value, false));
    }

    protected void process(StringBuffer buf, Object lang)
    {
        buf.append("<rdf:li xml:lang=\"");
        buf.append(lang);
        buf.append("\" >");
        buf.append(get(lang));
        buf.append("</rdf:li>");
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<rdf:Alt>");
        for(Enumeration e = propertyNames(); e.hasMoreElements(); process(sb, e.nextElement()));
        sb.append("</rdf:Alt>");
        return sb.toString();
    }

    private static final long serialVersionUID = 0x3d052e457e20955bL;
    public static final String DEFAULT = "x-default";
}
