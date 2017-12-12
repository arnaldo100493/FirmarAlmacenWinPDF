// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateInfo.java

package co.com.pdf.text.pdf.security;


// Referenced classes of package co.com.pdf.text.pdf.security:
//            CertificateInfo

public static class CertificateInfo$X509NameTokenizer
{

    public boolean hasMoreTokens()
    {
        return index != oid.length();
    }

    public String nextToken()
    {
        if(index == oid.length())
            return null;
        int end = index + 1;
        boolean quoted = false;
        boolean escaped = false;
        buf.setLength(0);
        for(; end != oid.length(); end++)
        {
            char c = oid.charAt(end);
            if(c == '"')
            {
                if(!escaped)
                    quoted = !quoted;
                else
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
                escaped = true;
                continue;
            }
            if(c == ',')
                break;
            buf.append(c);
        }

        index = end;
        return buf.toString().trim();
    }

    private String oid;
    private int index;
    private StringBuffer buf;

    public CertificateInfo$X509NameTokenizer(String oid)
    {
        buf = new StringBuffer();
        this.oid = oid;
        index = -1;
    }
}
