// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorHelper.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.kisa.KISAObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSASSAPSSparams;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.operator.OperatorCreationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.*;
import java.security.spec.PSSParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

class OperatorHelper
{
    private static class OpCertificateException extends CertificateException
    {

        public Throwable getCause()
        {
            return cause;
        }

        private Throwable cause;

        public OpCertificateException(String msg, Throwable cause)
        {
            super(msg);
            this.cause = cause;
        }
    }


    OperatorHelper(JcaJceHelper helper)
    {
        this.helper = helper;
    }

    Cipher createAsymmetricWrapper(ASN1ObjectIdentifier algorithm, Map extraAlgNames)
        throws OperatorCreationException
    {
        String cipherName;
        cipherName = null;
        if(!extraAlgNames.isEmpty())
            cipherName = (String)extraAlgNames.get(algorithm);
        if(cipherName == null)
            cipherName = (String)asymmetricWrapperAlgNames.get(algorithm);
        if(cipherName != null)
        {
            try
            {
                return helper.createCipher(cipherName);
            }
            catch(NoSuchAlgorithmException e) { }
            if(cipherName.equals("RSA/ECB/PKCS1Padding"))
                try
                {
                    return helper.createCipher("RSA/NONE/PKCS1Padding");
                }
                catch(NoSuchAlgorithmException ex) { }
        }
        return helper.createCipher(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new OperatorCreationException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
    }

    Cipher createSymmetricWrapper(ASN1ObjectIdentifier algorithm)
        throws OperatorCreationException
    {
        String cipherName = (String)symmetricWrapperAlgNames.get(algorithm);
        if(cipherName != null)
            try
            {
                return helper.createCipher(cipherName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createCipher(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new OperatorCreationException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
    }

    MessageDigest createDigest(AlgorithmIdentifier digAlgId)
        throws GeneralSecurityException
    {
        MessageDigest dig;
        try
        {
            dig = helper.createDigest(getDigestAlgName(digAlgId.getAlgorithm()));
        }
        catch(NoSuchAlgorithmException e)
        {
            if(oids.get(digAlgId.getAlgorithm()) != null)
            {
                String digestAlgorithm = (String)oids.get(digAlgId.getAlgorithm());
                dig = helper.createDigest(digestAlgorithm);
            } else
            {
                throw e;
            }
        }
        return dig;
    }

    Signature createSignature(AlgorithmIdentifier sigAlgId)
        throws GeneralSecurityException
    {
        Signature sig;
        try
        {
            sig = helper.createSignature(getSignatureName(sigAlgId));
        }
        catch(NoSuchAlgorithmException e)
        {
            if(oids.get(sigAlgId.getAlgorithm()) != null)
            {
                String signatureAlgorithm = (String)oids.get(sigAlgId.getAlgorithm());
                sig = helper.createSignature(signatureAlgorithm);
            } else
            {
                throw e;
            }
        }
        return sig;
    }

    public Signature createRawSignature(AlgorithmIdentifier algorithm)
    {
        Signature sig;
        try
        {
            String algName = getSignatureName(algorithm);
            algName = (new StringBuilder()).append("NONE").append(algName.substring(algName.indexOf("WITH"))).toString();
            sig = helper.createSignature(algName);
            if(algorithm.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
            {
                AlgorithmParameters params = helper.createAlgorithmParameters(algName);
                params.init(algorithm.getParameters().toASN1Primitive().getEncoded(), "ASN.1");
                PSSParameterSpec spec = (PSSParameterSpec)params.getParameterSpec(java/security/spec/PSSParameterSpec);
                sig.setParameter(spec);
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return sig;
    }

    private static String getSignatureName(AlgorithmIdentifier sigAlgId)
    {
        ASN1Encodable params = sigAlgId.getParameters();
        if(params != null && !DERNull.INSTANCE.equals(params) && sigAlgId.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
        {
            RSASSAPSSparams rsaParams = RSASSAPSSparams.getInstance(params);
            return (new StringBuilder()).append(getDigestAlgName(rsaParams.getHashAlgorithm().getAlgorithm())).append("WITHRSAANDMGF1").toString();
        }
        if(oids.containsKey(sigAlgId.getAlgorithm()))
            return (String)oids.get(sigAlgId.getAlgorithm());
        else
            return sigAlgId.getAlgorithm().getId();
    }

    private static String getDigestAlgName(ASN1ObjectIdentifier digestAlgOID)
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

    public X509Certificate convertCertificate(X509CertificateHolder certHolder)
        throws CertificateException
    {
        try
        {
            CertificateFactory certFact = helper.createCertificateFactory("X.509");
            return (X509Certificate)certFact.generateCertificate(new ByteArrayInputStream(certHolder.getEncoded()));
        }
        catch(IOException e)
        {
            throw new OpCertificateException((new StringBuilder()).append("cannot get encoded form of certificate: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new OpCertificateException((new StringBuilder()).append("cannot create certificate factory: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchProviderException e)
        {
            throw new OpCertificateException((new StringBuilder()).append("cannot find factory provider: ").append(e.getMessage()).toString(), e);
        }
    }

    String getKeyAlgorithmName(ASN1ObjectIdentifier oid)
    {
        String name = (String)symmetricKeyAlgNames.get(oid);
        if(name != null)
            return name;
        else
            return oid.getId();
    }

    private static final Map oids;
    private static final Map asymmetricWrapperAlgNames;
    private static final Map symmetricWrapperAlgNames;
    private static final Map symmetricKeyAlgNames;
    private JcaJceHelper helper;

    static 
    {
        oids = new HashMap();
        asymmetricWrapperAlgNames = new HashMap();
        symmetricWrapperAlgNames = new HashMap();
        symmetricKeyAlgNames = new HashMap();
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        oids.put(OIWObjectIdentifiers.idSHA1, "SHA-1");
        oids.put(NISTObjectIdentifiers.id_sha224, "SHA-224");
        oids.put(NISTObjectIdentifiers.id_sha256, "SHA-256");
        oids.put(NISTObjectIdentifiers.id_sha384, "SHA-384");
        oids.put(NISTObjectIdentifiers.id_sha512, "SHA-512");
        oids.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD-128");
        oids.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD-160");
        oids.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD-256");
        asymmetricWrapperAlgNames.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, "DESEDEWrap");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap, "RC2Wrap");
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes128_wrap, "AESWrap");
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes192_wrap, "AESWrap");
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes256_wrap, "AESWrap");
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia128_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia192_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia256_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, "SEEDWrap");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.aes, "AES");
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        symmetricKeyAlgNames.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        symmetricKeyAlgNames.put(PKCSObjectIdentifiers.RC2_CBC, "RC2");
    }
}
