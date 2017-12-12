// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS10CertificationRequest.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jce:
//            X509Principal

/**
 * @deprecated Class PKCS10CertificationRequest is deprecated
 */

public class PKCS10CertificationRequest extends CertificationRequest
{

    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier hashAlgId, int saltSize)
    {
        return new RSASSAPSSparams(hashAlgId, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, hashAlgId), new ASN1Integer(saltSize), new ASN1Integer(1L));
    }

    private static ASN1Sequence toDERSequence(byte bytes[])
    {
        try
        {
            ASN1InputStream dIn = new ASN1InputStream(bytes);
            return (ASN1Sequence)dIn.readObject();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("badly encoded request");
        }
    }

    public PKCS10CertificationRequest(byte bytes[])
    {
        super(toDERSequence(bytes));
    }

    public PKCS10CertificationRequest(ASN1Sequence sequence)
    {
        super(sequence);
    }

    public PKCS10CertificationRequest(String signatureAlgorithm, X509Name subject, PublicKey key, ASN1Set attributes, PrivateKey signingKey)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        this(signatureAlgorithm, subject, key, attributes, signingKey, "BC");
    }

    private static X509Name convertName(X500Principal name)
    {
        try
        {
            return new X509Principal(name.getEncoded());
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("can't convert name");
        }
    }

    public PKCS10CertificationRequest(String signatureAlgorithm, X500Principal subject, PublicKey key, ASN1Set attributes, PrivateKey signingKey)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        this(signatureAlgorithm, convertName(subject), key, attributes, signingKey, "BC");
    }

    public PKCS10CertificationRequest(String signatureAlgorithm, X500Principal subject, PublicKey key, ASN1Set attributes, PrivateKey signingKey, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        this(signatureAlgorithm, convertName(subject), key, attributes, signingKey, provider);
    }

    public PKCS10CertificationRequest(String signatureAlgorithm, X509Name subject, PublicKey key, ASN1Set attributes, PrivateKey signingKey, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        String algorithmName = Strings.toUpperCase(signatureAlgorithm);
        DERObjectIdentifier sigOID = (DERObjectIdentifier)algorithms.get(algorithmName);
        if(sigOID == null)
            try
            {
                sigOID = new DERObjectIdentifier(algorithmName);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException("Unknown signature type requested");
            }
        if(subject == null)
            throw new IllegalArgumentException("subject must not be null");
        if(key == null)
            throw new IllegalArgumentException("public key must not be null");
        if(noParams.contains(sigOID))
            sigAlgId = new AlgorithmIdentifier(sigOID);
        else
        if(params.containsKey(algorithmName))
            sigAlgId = new AlgorithmIdentifier(sigOID, (ASN1Encodable)params.get(algorithmName));
        else
            sigAlgId = new AlgorithmIdentifier(sigOID, DERNull.INSTANCE);
        try
        {
            ASN1Sequence seq = (ASN1Sequence)ASN1Primitive.fromByteArray(key.getEncoded());
            reqInfo = new CertificationRequestInfo(subject, new SubjectPublicKeyInfo(seq), attributes);
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("can't encode public key");
        }
        Signature sig;
        if(provider == null)
            sig = Signature.getInstance(signatureAlgorithm);
        else
            sig = Signature.getInstance(signatureAlgorithm, provider);
        sig.initSign(signingKey);
        try
        {
            sig.update(reqInfo.getEncoded("DER"));
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("exception encoding TBS cert request - ").append(e).toString());
        }
        sigBits = new DERBitString(sig.sign());
    }

    public PublicKey getPublicKey()
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        return getPublicKey("BC");
    }

    public PublicKey getPublicKey(String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        SubjectPublicKeyInfo subjectPKInfo = reqInfo.getSubjectPublicKeyInfo();
        X509EncodedKeySpec xspec;
        AlgorithmIdentifier keyAlg;
        xspec = new X509EncodedKeySpec((new DERBitString(subjectPKInfo)).getBytes());
        keyAlg = subjectPKInfo.getAlgorithm();
        if(provider == null)
            return KeyFactory.getInstance(keyAlg.getAlgorithm().getId()).generatePublic(xspec);
        return KeyFactory.getInstance(keyAlg.getAlgorithm().getId(), provider).generatePublic(xspec);
        NoSuchAlgorithmException e;
        e;
        String keyAlgorithm;
        if(keyAlgorithms.get(keyAlg.getObjectId()) == null)
            break MISSING_BLOCK_LABEL_127;
        keyAlgorithm = (String)keyAlgorithms.get(keyAlg.getObjectId());
        if(provider == null)
            return KeyFactory.getInstance(keyAlgorithm).generatePublic(xspec);
        try
        {
            return KeyFactory.getInstance(keyAlgorithm, provider).generatePublic(xspec);
        }
        catch(InvalidKeySpecException e)
        {
            throw new InvalidKeyException("error decoding public key");
        }
        catch(IOException e)
        {
            throw new InvalidKeyException("error decoding public key");
        }
        throw e;
    }

    public boolean verify()
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        return verify("BC");
    }

    public boolean verify(String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        return verify(getPublicKey(provider), provider);
    }

    public boolean verify(PublicKey pubKey, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        Signature sig;
        try
        {
            if(provider == null)
                sig = Signature.getInstance(getSignatureName(sigAlgId));
            else
                sig = Signature.getInstance(getSignatureName(sigAlgId), provider);
        }
        catch(NoSuchAlgorithmException e)
        {
            if(oids.get(sigAlgId.getObjectId()) != null)
            {
                String signatureAlgorithm = (String)oids.get(sigAlgId.getObjectId());
                if(provider == null)
                    sig = Signature.getInstance(signatureAlgorithm);
                else
                    sig = Signature.getInstance(signatureAlgorithm, provider);
            } else
            {
                throw e;
            }
        }
        setSignatureParameters(sig, sigAlgId.getParameters());
        sig.initVerify(pubKey);
        try
        {
            sig.update(reqInfo.getEncoded("DER"));
        }
        catch(Exception e)
        {
            throw new SignatureException((new StringBuilder()).append("exception encoding TBS cert request - ").append(e).toString());
        }
        return sig.verify(sigBits.getBytes());
    }

    public byte[] getEncoded()
    {
        try
        {
            return getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.toString());
        }
    }

    private void setSignatureParameters(Signature signature, ASN1Encodable params)
        throws NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        if(params != null && !DERNull.INSTANCE.equals(params))
        {
            AlgorithmParameters sigParams = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try
            {
                sigParams.init(params.toASN1Primitive().getEncoded("DER"));
            }
            catch(IOException e)
            {
                throw new SignatureException((new StringBuilder()).append("IOException decoding parameters: ").append(e.getMessage()).toString());
            }
            if(signature.getAlgorithm().endsWith("MGF1"))
                try
                {
                    signature.setParameter(sigParams.getParameterSpec(java/security/spec/PSSParameterSpec));
                }
                catch(GeneralSecurityException e)
                {
                    throw new SignatureException((new StringBuilder()).append("Exception extracting parameters: ").append(e.getMessage()).toString());
                }
        }
    }

    static String getSignatureName(AlgorithmIdentifier sigAlgId)
    {
        ASN1Encodable params = sigAlgId.getParameters();
        if(params != null && !DERNull.INSTANCE.equals(params) && sigAlgId.getObjectId().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
        {
            RSASSAPSSparams rsaParams = RSASSAPSSparams.getInstance(params);
            return (new StringBuilder()).append(getDigestAlgName(rsaParams.getHashAlgorithm().getObjectId())).append("withRSAandMGF1").toString();
        } else
        {
            return sigAlgId.getObjectId().getId();
        }
    }

    private static String getDigestAlgName(DERObjectIdentifier digestAlgOID)
    {
        if(PKCSObjectIdentifiers.md5.equals(digestAlgOID))
            return "MD5";
        if(OIWObjectIdentifiers.idSHA1.equals(digestAlgOID))
            return "SHA1";
        if(NISTObjectIdentifiers.id_sha224.equals(digestAlgOID))
            return "SHA224";
        if(NISTObjectIdentifiers.id_sha256.equals(digestAlgOID))
            return "SHA256";
        if(NISTObjectIdentifiers.id_sha384.equals(digestAlgOID))
            return "SHA384";
        if(NISTObjectIdentifiers.id_sha512.equals(digestAlgOID))
            return "SHA512";
        if(TeleTrusTObjectIdentifiers.ripemd128.equals(digestAlgOID))
            return "RIPEMD128";
        if(TeleTrusTObjectIdentifiers.ripemd160.equals(digestAlgOID))
            return "RIPEMD160";
        if(TeleTrusTObjectIdentifiers.ripemd256.equals(digestAlgOID))
            return "RIPEMD256";
        if(CryptoProObjectIdentifiers.gostR3411.equals(digestAlgOID))
            return "GOST3411";
        else
            return digestAlgOID.getId();
    }

    private static Hashtable algorithms;
    private static Hashtable params;
    private static Hashtable keyAlgorithms;
    private static Hashtable oids;
    private static Set noParams;

    static 
    {
        algorithms = new Hashtable();
        params = new Hashtable();
        keyAlgorithms = new Hashtable();
        oids = new Hashtable();
        noParams = new HashSet();
        algorithms.put("MD2WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD2WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD5WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("MD5WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("RSAWITHMD5", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("SHA1WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA224WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA224WITHRSA", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA256WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA256WITHRSA", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA384WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA384WITHRSA", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA512WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA512WITHRSA", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA1WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA224WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA256WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA384WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA512WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("RSAWITHSHA1", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("RIPEMD128WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD128WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD160WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD160WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD256WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("RIPEMD256WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("SHA1WITHDSA", new DERObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("DSAWITHSHA1", new DERObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("SHA224WITHDSA", NISTObjectIdentifiers.dsa_with_sha224);
        algorithms.put("SHA256WITHDSA", NISTObjectIdentifiers.dsa_with_sha256);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        algorithms.put("SHA1WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("SHA224WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA224);
        algorithms.put("SHA256WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA256);
        algorithms.put("SHA384WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA384);
        algorithms.put("SHA512WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA512);
        algorithms.put("ECDSAWITHSHA1", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3410WITHGOST3411", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHECGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHECGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA1);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA224);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA256);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA384);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA512);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha224);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha256);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        AlgorithmIdentifier sha1AlgId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        params.put("SHA1WITHRSAANDMGF1", creatPSSParams(sha1AlgId, 20));
        AlgorithmIdentifier sha224AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE);
        params.put("SHA224WITHRSAANDMGF1", creatPSSParams(sha224AlgId, 28));
        AlgorithmIdentifier sha256AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        params.put("SHA256WITHRSAANDMGF1", creatPSSParams(sha256AlgId, 32));
        AlgorithmIdentifier sha384AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        params.put("SHA384WITHRSAANDMGF1", creatPSSParams(sha384AlgId, 48));
        AlgorithmIdentifier sha512AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        params.put("SHA512WITHRSAANDMGF1", creatPSSParams(sha512AlgId, 64));
    }
}
