// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertParser.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.ASN1TaggedObject;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.SignedData;
import co.org.bouncy.x509.util.StreamParsingException;
import co.org.bouncy.x509_.X509StreamParserSpi;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Referenced classes of package co.org.bouncy.jce.provider:
//            X509CertificateObject, PEMUtil

public class X509CertParser extends X509StreamParserSpi
{

    public X509CertParser()
    {
        sData = null;
        sDataObjectCount = 0;
        currentStream = null;
    }

    private Certificate readDERCertificate(InputStream in)
        throws IOException, CertificateParsingException
    {
        ASN1InputStream dIn = new ASN1InputStream(in);
        ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
        if(seq.size() > 1 && (seq.getObjectAt(0) instanceof DERObjectIdentifier) && seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData))
        {
            sData = (new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true))).getCertificates();
            return getCertificate();
        } else
        {
            return new X509CertificateObject(co.org.bouncy.asn1.x509.Certificate.getInstance(seq));
        }
    }

    private Certificate getCertificate()
        throws CertificateParsingException
    {
        if(sData != null)
            while(sDataObjectCount < sData.size()) 
            {
                Object obj = sData.getObjectAt(sDataObjectCount++);
                if(obj instanceof ASN1Sequence)
                    return new X509CertificateObject(co.org.bouncy.asn1.x509.Certificate.getInstance(obj));
            }
        return null;
    }

    private Certificate readPEMCertificate(InputStream in)
        throws IOException, CertificateParsingException
    {
        ASN1Sequence seq = PEM_PARSER.readPEMObject(in);
        if(seq != null)
            return new X509CertificateObject(co.org.bouncy.asn1.x509.Certificate.getInstance(seq));
        else
            return null;
    }

    public void engineInit(InputStream in)
    {
        currentStream = in;
        sData = null;
        sDataObjectCount = 0;
        if(!currentStream.markSupported())
            currentStream = new BufferedInputStream(currentStream);
    }

    public Object engineRead()
        throws StreamParsingException
    {
        if(sData == null)
            break MISSING_BLOCK_LABEL_38;
        if(sDataObjectCount != sData.size())
            return getCertificate();
        int tag;
        try
        {
            sData = null;
            sDataObjectCount = 0;
            return null;
        }
        catch(Exception e)
        {
            throw new StreamParsingException(e.toString(), e);
        }
        currentStream.mark(10);
        tag = currentStream.read();
        if(tag == -1)
            return null;
        if(tag != 48)
        {
            currentStream.reset();
            return readPEMCertificate(currentStream);
        }
        currentStream.reset();
        return readDERCertificate(currentStream);
    }

    public Collection engineReadAll()
        throws StreamParsingException
    {
        List certs = new ArrayList();
        Certificate cert;
        while((cert = (Certificate)engineRead()) != null) 
            certs.add(cert);
        return certs;
    }

    private static final PEMUtil PEM_PARSER = new PEMUtil("CERTIFICATE");
    private ASN1Set sData;
    private int sDataObjectCount;
    private InputStream currentStream;

}
