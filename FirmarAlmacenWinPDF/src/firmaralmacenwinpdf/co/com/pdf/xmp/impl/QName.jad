// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QName.java

package co.com.pdf.xmp.impl;


public class QName
{

    public QName(String qname)
    {
        int colon = qname.indexOf(':');
        if(colon >= 0)
        {
            prefix = qname.substring(0, colon);
            localName = qname.substring(colon + 1);
        } else
        {
            prefix = "";
            localName = qname;
        }
    }

    public QName(String prefix, String localName)
    {
        this.prefix = prefix;
        this.localName = localName;
    }

    public boolean hasPrefix()
    {
        return prefix != null && prefix.length() > 0;
    }

    public String getLocalName()
    {
        return localName;
    }

    public String getPrefix()
    {
        return prefix;
    }

    private String prefix;
    private String localName;
}
