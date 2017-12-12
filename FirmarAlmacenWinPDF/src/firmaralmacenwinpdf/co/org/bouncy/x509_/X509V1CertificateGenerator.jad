// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509V1CertificateGenerator.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.jce.provider.X509CertificateObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.Date;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.x509_:
//            ExtCertificateEncodingException, X509Util

/**
 * @deprecated Class X509V1CertificateGenerator is deprecated
 */

public class X509V1CertificateGenerator
{

    public X509V1CertificateGenerator()
    {
        tbsGen = new V1TBSCertificateGenerator();
    }

    public void reset()
    {
        tbsGen = new V1TBSCertificateGenerator();
    }

    public void setSerialNumber(BigInteger serialNumber)
    {
        if(serialNumber.compareTo(BigInteger.ZERO) <= 0)
        {
            throw new IllegalArgumentException("serial number must be a positive integer");
        } else
        {
            tbsGen.setSerialNumber(new ASN1Integer(serialNumber));
            return;
        }
    }

    public void setIssuerDN(X500Principal issuer)
    {
        try
        {
            tbsGen.setIssuer(new X509Principal(issuer.getEncoded()));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("can't process principal: ").append(e).toString());
        }
    }

    public void setIssuerDN(X509Name issuer)
    {
        tbsGen.setIssuer(issuer);
    }

    public void setNotBefore(Date date)
    {
        tbsGen.setStartDate(new Time(date));
    }

    public void setNotAfter(Date date)
    {
        tbsGen.setEndDate(new Time(date));
    }

    public void setSubjectDN(X500Principal subject)
    {
        try
        {
            tbsGen.setSubject(new X509Principal(subject.getEncoded()));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("can't process principal: ").append(e).toString());
        }
    }

    public void setSubjectDN(X509Name subject)
    {
        tbsGen.setSubject(subject);
    }

    public void setPublicKey(PublicKey key)
    {
        try
        {
            tbsGen.setSubjectPublicKeyInfo(new SubjectPublicKeyInfo((ASN1Sequence)(new ASN1InputStream(new ByteArrayInputStream(key.getEncoded()))).readObject()));
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unable to process key - ").append(e.toString()).toString());
        }
    }

    public void setSignatureAlgorithm(String signatureAlgorithm)
    {
        this.signatureAlgorithm = signatureAlgorithm;
        try
        {
            sigOID = X509Util.getAlgorithmOID(signatureAlgorithm);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
        sigAlgId = X509Util.getSigAlgID(sigOID, signatureAlgorithm);
        tbsGen.setSignature(sigAlgId);
    }

    /**
     * @deprecated Method generateX509Certificate is deprecated
     */

    public X509Certificate generateX509Certificate(PrivateKey key)
        throws SecurityException, SignatureException, InvalidKeyException
    {
        try
        {
            return generateX509Certificate(key, "BC", null);
        }
        catch(NoSuchProviderException e)
        {
            throw new SecurityException("BC provider not installed!");
        }
    }

    /**
     * @deprecated Method generateX509Certificate is deprecated
     */

    public X509Certificate generateX509Certificate(PrivateKey key, SecureRandom random)
        throws SecurityException, SignatureException, InvalidKeyException
    {
        try
        {
            return generateX509Certificate(key, "BC", random);
        }
        catch(NoSuchProviderException e)
        {
            throw new SecurityException("BC provider not installed!");
        }
    }

    /**
     * @deprecated Method generateX509Certificate is deprecated
     */

    public X509Certificate generateX509Certificate(PrivateKey key, String provider)
        throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException
    {
        return generateX509Certificate(key, provider, null);
    }

    /**
     * @deprecated Method generateX509Certificate is deprecated
     */

    public X509Certificate generateX509Certificate(PrivateKey key, String provider, SecureRandom random)
        throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException
    {
        try
        {
            return generate(key, provider, random);
        }
        catch(NoSuchProviderException e)
        {
            throw e;
        }
        catch(SignatureException e)
        {
            throw e;
        }
        catch(InvalidKeyException e)
        {
            throw e;
        }
        catch(GeneralSecurityException e)
        {
            throw new SecurityException((new StringBuilder()).append("exception: ").append(e).toString());
        }
    }

    public X509Certificate generate(PrivateKey key)
        throws CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        return generate(key, (SecureRandom)null);
    }

    public X509Certificate generate(PrivateKey key, SecureRandom random)
        throws CertificateEncodingException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        TBSCertificate tbsCert = tbsGen.generateTBSCertificate();
        byte signature[];
        try
        {
            signature = X509Util.calculateSignature(sigOID, signatureAlgorithm, key, random, tbsCert);
        }
        catch(IOException e)
        {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", e);
        }
        return generateJcaObject(tbsCert, signature);
    }

    public X509Certificate generate(PrivateKey key, String provider)
        throws CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        return generate(key, provider, null);
    }

    public X509Certificate generate(PrivateKey key, String provider, SecureRandom random)
        throws CertificateEncodingException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        TBSCertificate tbsCert = tbsGen.generateTBSCertificate();
        byte signature[];
        try
        {
            signature = X509Util.calculateSignature(sigOID, signatureAlgorithm, provider, key, random, tbsCert);
        }
        catch(IOException e)
        {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", e);
        }
        return generateJcaObject(tbsCert, signature);
    }

    private X509Certificate generateJcaObject(TBSCertificate tbsCert, byte signature[])
        throws CertificateEncodingException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsCert);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
        try
        {
            return new X509CertificateObject(Certificate.getInstance(new DERSequence(v)));
        }
        catch(CertificateParsingException e)
        {
            throw new ExtCertificateEncodingException("exception producing certificate object", e);
        }
    }

    public Iterator getSignatureAlgNames()
    {
        return X509Util.getAlgNames();
    }

    private V1TBSCertificateGenerator tbsGen;
    private DERObjectIdentifier sigOID;
    private AlgorithmIdentifier sigAlgId;
    private String signatureAlgorithm;
}
