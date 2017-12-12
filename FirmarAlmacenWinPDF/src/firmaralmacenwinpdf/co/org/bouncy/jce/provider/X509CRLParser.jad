// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CRLParser.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.SignedData;
import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.x509.util.StreamParsingException;
import co.org.bouncy.x509_.X509StreamParserSpi;
import java.io.*;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.util.*;

// Referenced classes of package co.org.bouncy.jce.provider:
//            X509CRLObject, PEMUtil

public class X509CRLParser extends X509StreamParserSpi
{

    public X509CRLParser()
    {
        sData = null;
        sDataObjectCount = 0;
        currentStream = null;
    }

    private CRL readDERCRL(InputStream in)
        throws IOException, CRLException
    {
        ASN1InputStream dIn = new ASN1InputStream(in);
        ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
        if(seq.size() > 1 && (seq.getObjectAt(0) instanceof DERObjectIdentifier) && seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData))
        {
            sData = (new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true))).getCRLs();
            return getCRL();
        } else
        {
            return new X509CRLObject(CertificateList.getInstance(seq));
        }
    }

    private CRL getCRL()
        throws CRLException
    {
        if(sData == null || sDataObjectCount >= sData.size())
            return null;
        else
            return new X509CRLObject(CertificateList.getInstance(sData.getObjectAt(sDataObjectCount++)));
    }

    private CRL readPEMCRL(InputStream in)
        throws IOException, CRLException
    {
        ASN1Sequence seq = PEM_PARSER.readPEMObject(in);
        if(seq != null)
            return new X509CRLObject(CertificateList.getInstance(seq));
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
            return getCRL();
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
            return readPEMCRL(currentStream);
        }
        currentStream.reset();
        return readDERCRL(currentStream);
    }

    public Collection engineReadAll()
        throws StreamParsingException
    {
        List certs = new ArrayList();
        CRL crl;
        while((crl = (CRL)engineRead()) != null) 
            certs.add(crl);
        return certs;
    }

    private static final PEMUtil PEM_PARSER = new PEMUtil("CRL");
    private ASN1Set sData;
    private int sDataObjectCount;
    private InputStream currentStream;

}
