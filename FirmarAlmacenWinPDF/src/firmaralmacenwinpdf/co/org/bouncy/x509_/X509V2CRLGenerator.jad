// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509V2CRLGenerator.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.jce.provider.X509CRLObject;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.x509_:
//            X509Util

/**
 * @deprecated Class X509V2CRLGenerator is deprecated
 */

public class X509V2CRLGenerator
{
    private static class ExtCRLException extends CRLException
    {

        public Throwable getCause()
        {
            return cause;
        }

        Throwable cause;

        ExtCRLException(String message, Throwable cause)
        {
            super(message);
            this.cause = cause;
        }
    }


    public X509V2CRLGenerator()
    {
        tbsGen = new V2TBSCertListGenerator();
        extGenerator = new X509ExtensionsGenerator();
    }

    public void reset()
    {
        tbsGen = new V2TBSCertListGenerator();
        extGenerator.reset();
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

    public void setThisUpdate(Date date)
    {
        tbsGen.setThisUpdate(new Time(date));
    }

    public void setNextUpdate(Date date)
    {
        tbsGen.setNextUpdate(new Time(date));
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, int reason)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), reason);
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, int reason, Date invalidityDate)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), reason, new ASN1GeneralizedTime(invalidityDate));
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, X509Extensions extensions)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), Extensions.getInstance(extensions));
    }

    public void addCRL(X509CRL other)
        throws CRLException
    {
        Set revocations = other.getRevokedCertificates();
        if(revocations != null)
        {
            for(Iterator it = revocations.iterator(); it.hasNext();)
            {
                X509CRLEntry entry = (X509CRLEntry)it.next();
                ASN1InputStream aIn = new ASN1InputStream(entry.getEncoded());
                try
                {
                    tbsGen.addCRLEntry(ASN1Sequence.getInstance(aIn.readObject()));
                }
                catch(IOException e)
                {
                    throw new CRLException((new StringBuilder()).append("exception processing encoding of CRL: ").append(e.toString()).toString());
                }
            }

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

    public void addExtension(String oid, boolean critical, ASN1Encodable value)
    {
        addExtension(new DERObjectIdentifier(oid), critical, value);
    }

    public void addExtension(DERObjectIdentifier oid, boolean critical, ASN1Encodable value)
    {
        extGenerator.addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    public void addExtension(String oid, boolean critical, byte value[])
    {
        addExtension(new DERObjectIdentifier(oid), critical, value);
    }

    public void addExtension(DERObjectIdentifier oid, boolean critical, byte value[])
    {
        extGenerator.addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    /**
     * @deprecated Method generateX509CRL is deprecated
     */

    public X509CRL generateX509CRL(PrivateKey key)
        throws SecurityException, SignatureException, InvalidKeyException
    {
        try
        {
            return generateX509CRL(key, "BC", null);
        }
        catch(NoSuchProviderException e)
        {
            throw new SecurityException("BC provider not installed!");
        }
    }

    /**
     * @deprecated Method generateX509CRL is deprecated
     */

    public X509CRL generateX509CRL(PrivateKey key, SecureRandom random)
        throws SecurityException, SignatureException, InvalidKeyException
    {
        try
        {
            return generateX509CRL(key, "BC", random);
        }
        catch(NoSuchProviderException e)
        {
            throw new SecurityException("BC provider not installed!");
        }
    }

    /**
     * @deprecated Method generateX509CRL is deprecated
     */

    public X509CRL generateX509CRL(PrivateKey key, String provider)
        throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException
    {
        return generateX509CRL(key, provider, null);
    }

    /**
     * @deprecated Method generateX509CRL is deprecated
     */

    public X509CRL generateX509CRL(PrivateKey key, String provider, SecureRandom random)
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

    public X509CRL generate(PrivateKey key)
        throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        return generate(key, (SecureRandom)null);
    }

    public X509CRL generate(PrivateKey key, SecureRandom random)
        throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        TBSCertList tbsCrl = generateCertList();
        byte signature[];
        try
        {
            signature = X509Util.calculateSignature(sigOID, signatureAlgorithm, key, random, tbsCrl);
        }
        catch(IOException e)
        {
            throw new ExtCRLException("cannot generate CRL encoding", e);
        }
        return generateJcaObject(tbsCrl, signature);
    }

    public X509CRL generate(PrivateKey key, String provider)
        throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        return generate(key, provider, null);
    }

    public X509CRL generate(PrivateKey key, String provider, SecureRandom random)
        throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        TBSCertList tbsCrl = generateCertList();
        byte signature[];
        try
        {
            signature = X509Util.calculateSignature(sigOID, signatureAlgorithm, provider, key, random, tbsCrl);
        }
        catch(IOException e)
        {
            throw new ExtCRLException("cannot generate CRL encoding", e);
        }
        return generateJcaObject(tbsCrl, signature);
    }

    private TBSCertList generateCertList()
    {
        if(!extGenerator.isEmpty())
            tbsGen.setExtensions(extGenerator.generate());
        return tbsGen.generateTBSCertList();
    }

    private X509CRL generateJcaObject(TBSCertList tbsCrl, byte signature[])
        throws CRLException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsCrl);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
        return new X509CRLObject(new CertificateList(new DERSequence(v)));
    }

    public Iterator getSignatureAlgNames()
    {
        return X509Util.getAlgNames();
    }

    private V2TBSCertListGenerator tbsGen;
    private DERObjectIdentifier sigOID;
    private AlgorithmIdentifier sigAlgId;
    private String signatureAlgorithm;
    private X509ExtensionsGenerator extGenerator;
}
