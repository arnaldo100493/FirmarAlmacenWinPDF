// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateFactory.java

package co.org.bouncy.jcajce.provider.asymmetric.x509;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.ASN1TaggedObject;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.SignedData;
import co.org.bouncy.asn1.x509.CertificateList;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.x509:
//            X509CertificateObject, X509CRLObject, PKIXCertPath, PEMUtil

public class CertificateFactory extends CertificateFactorySpi
{
    private class ExCertificateException extends CertificateException
    {

        public Throwable getCause()
        {
            return cause;
        }

        private Throwable cause;
        final CertificateFactory this$0;

        public ExCertificateException(Throwable cause)
        {
            this$0 = CertificateFactory.this;
            super();
            this.cause = cause;
        }

        public ExCertificateException(String msg, Throwable cause)
        {
            this$0 = CertificateFactory.this;
            super(msg);
            this.cause = cause;
        }
    }


    public CertificateFactory()
    {
        sData = null;
        sDataObjectCount = 0;
        currentStream = null;
        sCrlData = null;
        sCrlDataObjectCount = 0;
        currentCrlStream = null;
    }

    private Certificate readDERCertificate(ASN1InputStream dIn)
        throws IOException, CertificateParsingException
    {
        ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
        if(seq.size() > 1 && (seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) && seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData))
        {
            sData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true)).getCertificates();
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
        ASN1Sequence seq = PEM_CERT_PARSER.readPEMObject(in);
        if(seq != null)
            return new X509CertificateObject(co.org.bouncy.asn1.x509.Certificate.getInstance(seq));
        else
            return null;
    }

    protected CRL createCRL(CertificateList c)
        throws CRLException
    {
        return new X509CRLObject(c);
    }

    private CRL readPEMCRL(InputStream in)
        throws IOException, CRLException
    {
        ASN1Sequence seq = PEM_CRL_PARSER.readPEMObject(in);
        if(seq != null)
            return createCRL(CertificateList.getInstance(seq));
        else
            return null;
    }

    private CRL readDERCRL(ASN1InputStream aIn)
        throws IOException, CRLException
    {
        ASN1Sequence seq = (ASN1Sequence)aIn.readObject();
        if(seq.size() > 1 && (seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) && seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData))
        {
            sCrlData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true)).getCRLs();
            return getCRL();
        } else
        {
            return createCRL(CertificateList.getInstance(seq));
        }
    }

    private CRL getCRL()
        throws CRLException
    {
        if(sCrlData == null || sCrlDataObjectCount >= sCrlData.size())
            return null;
        else
            return createCRL(CertificateList.getInstance(sCrlData.getObjectAt(sCrlDataObjectCount++)));
    }

    public Certificate engineGenerateCertificate(InputStream in)
        throws CertificateException
    {
        if(currentStream == null)
        {
            currentStream = in;
            sData = null;
            sDataObjectCount = 0;
        } else
        if(currentStream != in)
        {
            currentStream = in;
            sData = null;
            sDataObjectCount = 0;
        }
        if(sData == null)
            break MISSING_BLOCK_LABEL_86;
        if(sDataObjectCount != sData.size())
            return getCertificate();
        PushbackInputStream pis;
        int tag;
        try
        {
            sData = null;
            sDataObjectCount = 0;
            return null;
        }
        catch(Exception e)
        {
            throw new ExCertificateException(e);
        }
        pis = new PushbackInputStream(in);
        tag = pis.read();
        if(tag == -1)
            return null;
        pis.unread(tag);
        if(tag != 48)
            return readPEMCertificate(pis);
        return readDERCertificate(new ASN1InputStream(pis));
    }

    public Collection engineGenerateCertificates(InputStream inStream)
        throws CertificateException
    {
        List certs = new ArrayList();
        Certificate cert;
        while((cert = engineGenerateCertificate(inStream)) != null) 
            certs.add(cert);
        return certs;
    }

    public CRL engineGenerateCRL(InputStream inStream)
        throws CRLException
    {
        if(currentCrlStream == null)
        {
            currentCrlStream = inStream;
            sCrlData = null;
            sCrlDataObjectCount = 0;
        } else
        if(currentCrlStream != inStream)
        {
            currentCrlStream = inStream;
            sCrlData = null;
            sCrlDataObjectCount = 0;
        }
        if(sCrlData == null)
            break MISSING_BLOCK_LABEL_86;
        if(sCrlDataObjectCount != sCrlData.size())
            return getCRL();
        PushbackInputStream pis;
        int tag;
        try
        {
            sCrlData = null;
            sCrlDataObjectCount = 0;
            return null;
        }
        catch(CRLException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new CRLException(e.toString());
        }
        pis = new PushbackInputStream(inStream);
        tag = pis.read();
        if(tag == -1)
            return null;
        pis.unread(tag);
        if(tag != 48)
            return readPEMCRL(pis);
        return readDERCRL(new ASN1InputStream(pis, true));
    }

    public Collection engineGenerateCRLs(InputStream inStream)
        throws CRLException
    {
        List crls = new ArrayList();
        CRL crl;
        while((crl = engineGenerateCRL(inStream)) != null) 
            crls.add(crl);
        return crls;
    }

    public Iterator engineGetCertPathEncodings()
    {
        return PKIXCertPath.certPathEncodings.iterator();
    }

    public CertPath engineGenerateCertPath(InputStream inStream)
        throws CertificateException
    {
        return engineGenerateCertPath(inStream, "PkiPath");
    }

    public CertPath engineGenerateCertPath(InputStream inStream, String encoding)
        throws CertificateException
    {
        return new PKIXCertPath(inStream, encoding);
    }

    public CertPath engineGenerateCertPath(List certificates)
        throws CertificateException
    {
        for(Iterator iter = certificates.iterator(); iter.hasNext();)
        {
            Object obj = iter.next();
            if(obj != null && !(obj instanceof X509Certificate))
                throw new CertificateException((new StringBuilder()).append("list contains non X509Certificate object while creating CertPath\n").append(obj.toString()).toString());
        }

        return new PKIXCertPath(certificates);
    }

    private static final PEMUtil PEM_CERT_PARSER = new PEMUtil("CERTIFICATE");
    private static final PEMUtil PEM_CRL_PARSER = new PEMUtil("CRL");
    private ASN1Set sData;
    private int sDataObjectCount;
    private InputStream currentStream;
    private ASN1Set sCrlData;
    private int sCrlDataObjectCount;
    private InputStream currentCrlStream;

}
