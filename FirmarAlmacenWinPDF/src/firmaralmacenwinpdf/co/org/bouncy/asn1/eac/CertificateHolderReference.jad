// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateHolderReference.java

package co.org.bouncy.asn1.eac;

import java.io.UnsupportedEncodingException;

public class CertificateHolderReference
{

    public CertificateHolderReference(String countryCode, String holderMnemonic, String sequenceNumber)
    {
        this.countryCode = countryCode;
        this.holderMnemonic = holderMnemonic;
        this.sequenceNumber = sequenceNumber;
    }

    CertificateHolderReference(byte contents[])
    {
        try
        {
            String concat = new String(contents, "ISO-8859-1");
            countryCode = concat.substring(0, 2);
            holderMnemonic = concat.substring(2, concat.length() - 5);
            sequenceNumber = concat.substring(concat.length() - 5);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new IllegalStateException(e.toString());
        }
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getHolderMnemonic()
    {
        return holderMnemonic;
    }

    public String getSequenceNumber()
    {
        return sequenceNumber;
    }

    public byte[] getEncoded()
    {
        String ref = (new StringBuilder()).append(countryCode).append(holderMnemonic).append(sequenceNumber).toString();
        try
        {
            return ref.getBytes("ISO-8859-1");
        }
        catch(UnsupportedEncodingException e)
        {
            throw new IllegalStateException(e.toString());
        }
    }

    private static final String ReferenceEncoding = "ISO-8859-1";
    private String countryCode;
    private String holderMnemonic;
    private String sequenceNumber;
}
