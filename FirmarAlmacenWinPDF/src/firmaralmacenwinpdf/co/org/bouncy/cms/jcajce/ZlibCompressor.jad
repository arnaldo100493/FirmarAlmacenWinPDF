// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ZlibCompressor.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.OutputCompressor;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

public class ZlibCompressor
    implements OutputCompressor
{

    public ZlibCompressor()
    {
    }

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.8"));
    }

    public OutputStream getOutputStream(OutputStream comOut)
    {
        return new DeflaterOutputStream(comOut);
    }

    private static final String ZLIB = "1.2.840.113549.1.9.16.3.8";
}
