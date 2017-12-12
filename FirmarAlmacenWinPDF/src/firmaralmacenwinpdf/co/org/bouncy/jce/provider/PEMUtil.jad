// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMUtil.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.encoders.Base64;
import java.io.IOException;
import java.io.InputStream;

public class PEMUtil
{

    PEMUtil(String type)
    {
        _header1 = (new StringBuilder()).append("-----BEGIN ").append(type).append("-----").toString();
        _header2 = (new StringBuilder()).append("-----BEGIN X509 ").append(type).append("-----").toString();
        _footer1 = (new StringBuilder()).append("-----END ").append(type).append("-----").toString();
        _footer2 = (new StringBuilder()).append("-----END X509 ").append(type).append("-----").toString();
    }

    private String readLine(InputStream in)
        throws IOException
    {
        StringBuffer l = new StringBuffer();
        int c;
        do
            do
            {
                if((c = in.read()) == 13 || c == 10 || c < 0)
                    break;
                if(c != 13)
                    l.append((char)c);
            } while(true);
        while(c >= 0 && l.length() == 0);
        if(c < 0)
            return null;
        else
            return l.toString();
    }

    ASN1Sequence readPEMObject(InputStream in)
        throws IOException
    {
        StringBuffer pemBuf = new StringBuffer();
        String line;
        while((line = readLine(in)) != null && !line.startsWith(_header1) && !line.startsWith(_header2)) ;
        for(; (line = readLine(in)) != null && !line.startsWith(_footer1) && !line.startsWith(_footer2); pemBuf.append(line));
        if(pemBuf.length() != 0)
        {
            ASN1Primitive o = (new ASN1InputStream(Base64.decode(pemBuf.toString()))).readObject();
            if(!(o instanceof ASN1Sequence))
                throw new IOException("malformed PEM data encountered");
            else
                return (ASN1Sequence)o;
        } else
        {
            return null;
        }
    }

    private final String _header1;
    private final String _header2;
    private final String _footer1;
    private final String _footer2;
}
