// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertPairParser.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.x509.CertificatePair;
import co.org.bouncy.x509.util.StreamParsingException;
import co.org.bouncy.x509_.X509CertificatePair;
import co.org.bouncy.x509_.X509StreamParserSpi;
import java.io.*;
import java.security.cert.CertificateParsingException;
import java.util.*;

public class X509CertPairParser extends X509StreamParserSpi
{

    public X509CertPairParser()
    {
        currentStream = null;
    }

    private X509CertificatePair readDERCrossCertificatePair(InputStream in)
        throws IOException, CertificateParsingException
    {
        ASN1InputStream dIn = new ASN1InputStream(in);
        ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
        CertificatePair pair = CertificatePair.getInstance(seq);
        return new X509CertificatePair(pair);
    }

    public void engineInit(InputStream in)
    {
        currentStream = in;
        if(!currentStream.markSupported())
            currentStream = new BufferedInputStream(currentStream);
    }

    public Object engineRead()
        throws StreamParsingException
    {
        try
        {
            currentStream.mark(10);
            int tag = currentStream.read();
            if(tag == -1)
                return null;
        }
        catch(Exception e)
        {
            throw new StreamParsingException(e.toString(), e);
        }
        currentStream.reset();
        return readDERCrossCertificatePair(currentStream);
    }

    public Collection engineReadAll()
        throws StreamParsingException
    {
        List certs = new ArrayList();
        X509CertificatePair pair;
        while((pair = (X509CertificatePair)engineRead()) != null) 
            certs.add(pair);
        return certs;
    }

    private InputStream currentStream;
}
