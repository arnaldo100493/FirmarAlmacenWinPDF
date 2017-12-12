// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509NameTokenizer.java

package co.org.bouncy.asn1.x509;


/**
 * @deprecated Class X509NameTokenizer is deprecated
 */

public class X509NameTokenizer
{

    public X509NameTokenizer(String oid)
    {
        this(oid, ',');
    }

    public X509NameTokenizer(String oid, char separator)
    {
        buf = new StringBuffer();
        value = oid;
        index = -1;
        this.separator = separator;
    }

    public boolean hasMoreTokens()
    {
        return index != value.length();
    }

    public String nextToken()
    {
        if(index == value.length())
            return null;
        int end = index + 1;
        boolean quoted = false;
        boolean escaped = false;
        buf.setLength(0);
        for(; end != value.length(); end++)
        {
            char c = value.charAt(end);
            if(c == '"')
            {
                if(!escaped)
                    quoted = !quoted;
                buf.append(c);
                escaped = false;
                continue;
            }
            if(escaped || quoted)
            {
                buf.append(c);
                escaped = false;
                continue;
            }
            if(c == '\\')
            {
                buf.append(c);
                escaped = true;
                continue;
            }
            if(c == separator)
                break;
            buf.append(c);
        }

        index = end;
        return buf.toString();
    }

    private String value;
    private int index;
    private char separator;
    private StringBuffer buf;
}
