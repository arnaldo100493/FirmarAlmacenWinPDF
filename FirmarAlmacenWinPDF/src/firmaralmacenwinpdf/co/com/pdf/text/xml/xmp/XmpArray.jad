// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmpArray.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.text.xml.XMLUtil;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @deprecated Class XmpArray is deprecated
 */

public class XmpArray extends ArrayList
{

    public XmpArray(String type)
    {
        this.type = type;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer("<");
        buf.append(type);
        buf.append('>');
        for(Iterator i$ = iterator(); i$.hasNext(); buf.append("</rdf:li>"))
        {
            String string = (String)i$.next();
            String s = string;
            buf.append("<rdf:li>");
            buf.append(XMLUtil.escapeXML(s, false));
        }

        buf.append("</");
        buf.append(type);
        buf.append('>');
        return buf.toString();
    }

    private static final long serialVersionUID = 0x4f6ba97f7127f846L;
    public static final String UNORDERED = "rdf:Bag";
    public static final String ORDERED = "rdf:Seq";
    public static final String ALTERNATIVE = "rdf:Alt";
    protected String type;
}
