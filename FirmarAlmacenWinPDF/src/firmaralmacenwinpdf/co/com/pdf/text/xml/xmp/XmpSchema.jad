// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmpSchema.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.text.xml.XMLUtil;
import java.util.Enumeration;
import java.util.Properties;

// Referenced classes of package co.com.pdf.text.xml.xmp:
//            XmpArray, LangAlt

/**
 * @deprecated Class XmpSchema is deprecated
 */

public abstract class XmpSchema extends Properties
{

    public XmpSchema(String xmlns)
    {
        this.xmlns = xmlns;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for(Enumeration e = propertyNames(); e.hasMoreElements(); process(buf, e.nextElement()));
        return buf.toString();
    }

    protected void process(StringBuffer buf, Object p)
    {
        buf.append('<');
        buf.append(p);
        buf.append('>');
        buf.append(get(p));
        buf.append("</");
        buf.append(p);
        buf.append('>');
    }

    public String getXmlns()
    {
        return xmlns;
    }

    public Object addProperty(String key, String value)
    {
        return setProperty(key, value);
    }

    public Object setProperty(String key, String value)
    {
        return super.setProperty(key, XMLUtil.escapeXML(value, false));
    }

    public Object setProperty(String key, XmpArray value)
    {
        return super.setProperty(key, value.toString());
    }

    public Object setProperty(String key, LangAlt value)
    {
        return super.setProperty(key, value.toString());
    }

    /**
     * @deprecated Method escape is deprecated
     */

    public static String escape(String content)
    {
        return XMLUtil.escapeXML(content, false);
    }

    private static final long serialVersionUID = 0xfd8d648326741888L;
    protected String xmlns;
}
