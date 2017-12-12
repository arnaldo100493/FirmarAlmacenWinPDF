// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRMFHelper.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cms.CMSAlgorithm;
import co.org.bouncy.jcajce.JcaJceHelper;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;

class CRMFHelper
{
    static interface JCECallback
    {

        public abstract Object doInJCE()
            throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;
    }


    CRMFHelper(JcaJceHelper helper)
    {
        this.helper = helper;
    }

    PublicKey toPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo)
        throws CRMFException
    {
        try
        {
            X509EncodedKeySpec xspec = new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded());
            AlgorithmIdentifier keyAlg = subjectPublicKeyInfo.getAlgorithm();
            return createKeyFactory(keyAlg.getAlgorithm()).generatePublic(xspec);
        }
        catch(Exception e)
        {
            throw new CRMFException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
        }
    }

    Cipher createCipher(ASN1ObjectIdentifier algorithm)
        throws CRMFException
    {
        String cipherName = (String)CIPHER_ALG_NAMES.get(algorithm);
        if(cipherName != null)
            try
            {
                return helper.createCipher(cipherName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createCipher(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new CRMFException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
    }

    public KeyGenerator createKeyGenerator(ASN1ObjectIdentifier algorithm)
        throws CRMFException
    {
        String cipherName = (String)BASE_CIPHER_NAMES.get(algorithm);
        if(cipherName != null)
            try
            {
                return helper.createKeyGenerator(cipherName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createKeyGenerator(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new CRMFException((new StringBuilder()).append("cannot create key generator: ").append(e.getMessage()).toString(), e);
    }

    Cipher createContentCipher(final Key sKey, final AlgorithmIdentifier encryptionAlgID)
        throws CRMFException
    {
        return (Cipher)execute(new JCECallback() {

            public Object doInJCE()
                throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
            {
                Cipher cipher = createCipher(encryptionAlgID.getAlgorithm());
                ASN1Primitive sParams = (ASN1Primitive)encryptionAlgID.getParameters();
                ASN1ObjectIdentifier encAlg = encryptionAlgID.getAlgorithm();
                if(sParams != null && !(sParams instanceof ASN1Null))
                    try
                    {
                        AlgorithmParameters params = createAlgorithmParameters(encryptionAlgID.getAlgorithm());
                        try
                        {
                            params.init(sParams.getEncoded(), "ASN.1");
                        }
                        catch(IOException e)
                        {
                            throw new CRMFException("error decoding algorithm parameters.", e);
                        }
                        cipher.init(2, sKey, params);
                    }
                    catch(NoSuchAlgorithmException e)
                    {
                        if(encAlg.equals(CMSAlgorithm.DES_EDE3_CBC) || encAlg.equals(CMSAlgorithm.IDEA_CBC) || encAlg.equals(CMSAlgorithm.AES128_CBC) || encAlg.equals(CMSAlgorithm.AES192_CBC) || encAlg.equals(CMSAlgorithm.AES256_CBC))
                            cipher.init(2, sKey, new IvParameterSpec(ASN1OctetString.getInstance(sParams).getOctets()));
                        else
                            throw e;
                    }
                else
                if(encAlg.equals(CMSAlgorithm.DES_EDE3_CBC) || encAlg.equals(CMSAlgorithm.IDEA_CBC) || encAlg.equals(CMSAlgorithm.CAST5_CBC))
                    cipher.init(2, sKey, new IvParameterSpec(new byte[8]));
                else
                    cipher.init(2, sKey);
                return cipher;
            }

            final AlgorithmIdentifier val$encryptionAlgID;
            final Key val$sKey;
            final CRMFHelper this$0;

            
            {
                this$0 = CRMFHelper.this;
                encryptionAlgID = algorithmidentifier;
                sKey = key;
                super();
            }
        }
);
    }

    AlgorithmParameters createAlgorithmParameters(ASN1ObjectIdentifier algorithm)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        String algorithmName = (String)BASE_CIPHER_NAMES.get(algorithm);
        if(algorithmName != null)
            try
            {
                return helper.createAlgorithmParameters(algorithmName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createAlgorithmParameters(algorithm.getId());
    }

    KeyFactory createKeyFactory(ASN1ObjectIdentifier algorithm)
        throws CRMFException
    {
        String algName = (String)KEY_ALG_NAMES.get(algorithm);
        if(algName != null)
            try
            {
                return helper.createKeyFactory(algName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createKeyFactory(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new CRMFException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
    }

    MessageDigest createDigest(ASN1ObjectIdentifier algorithm)
        throws CRMFException
    {
        String digestName = (String)DIGEST_ALG_NAMES.get(algorithm);
        if(digestName != null)
            try
            {
                return helper.createDigest(digestName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createDigest(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new CRMFException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
    }

    Mac createMac(ASN1ObjectIdentifier algorithm)
        throws CRMFException
    {
        String macName = (String)MAC_ALG_NAMES.get(algorithm);
        if(macName != null)
            try
            {
                return helper.createMac(macName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createMac(algorithm.getId());
        GeneralSecurityException e;
        e;
        throw new CRMFException((new StringBuilder()).append("cannot create mac: ").append(e.getMessage()).toString(), e);
    }

    AlgorithmParameterGenerator createAlgorithmParameterGenerator(ASN1ObjectIdentifier algorithm)
        throws GeneralSecurityException
    {
        String algorithmName = (String)BASE_CIPHER_NAMES.get(algorithm);
        if(algorithmName != null)
            try
            {
                return helper.createAlgorithmParameterGenerator(algorithmName);
            }
            catch(NoSuchAlgorithmException e) { }
        return helper.createAlgorithmParameterGenerator(algorithm.getId());
    }

    AlgorithmParameters generateParameters(ASN1ObjectIdentifier encryptionOID, SecretKey encKey, SecureRandom rand)
        throws CRMFException
    {
        try
        {
            AlgorithmParameterGenerator pGen = createAlgorithmParameterGenerator(encryptionOID);
            if(encryptionOID.equals(CMSAlgorithm.RC2_CBC))
            {
                byte iv[] = new byte[8];
                rand.nextBytes(iv);
                try
                {
                    pGen.init(new RC2ParameterSpec(encKey.getEncoded().length * 8, iv), rand);
                }
                catch(InvalidAlgorithmParameterException e)
                {
                    throw new CRMFException((new StringBuilder()).append("parameters generation error: ").append(e).toString(), e);
                }
            }
            return pGen.generateParameters();
        }
        catch(NoSuchAlgorithmException e)
        {
            return null;
        }
        catch(GeneralSecurityException e)
        {
            throw new CRMFException((new StringBuilder()).append("exception creating algorithm parameter generator: ").append(e).toString(), e);
        }
    }

    AlgorithmIdentifier getAlgorithmIdentifier(ASN1ObjectIdentifier encryptionOID, AlgorithmParameters params)
        throws CRMFException
    {
        ASN1Encodable asn1Params;
        if(params != null)
            try
            {
                asn1Params = ASN1Primitive.fromByteArray(params.getEncoded("ASN.1"));
            }
            catch(IOException e)
            {
                throw new CRMFException((new StringBuilder()).append("cannot encode parameters: ").append(e.getMessage()).toString(), e);
            }
        else
            asn1Params = DERNull.INSTANCE;
        return new AlgorithmIdentifier(encryptionOID, asn1Params);
    }

    static Object execute(JCECallback callback)
        throws CRMFException
    {
        try
        {
            return callback.doInJCE();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new CRMFException("can't find algorithm.", e);
        }
        catch(InvalidKeyException e)
        {
            throw new CRMFException("key invalid in message.", e);
        }
        catch(NoSuchProviderException e)
        {
            throw new CRMFException("can't find provider.", e);
        }
        catch(NoSuchPaddingException e)
        {
            throw new CRMFException("required padding not supported.", e);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new CRMFException("algorithm parameters invalid.", e);
        }
        catch(InvalidParameterSpecException e)
        {
            throw new CRMFException("MAC algorithm parameter spec invalid.", e);
        }
    }

    protected static final Map BASE_CIPHER_NAMES;
    protected static final Map CIPHER_ALG_NAMES;
    protected static final Map DIGEST_ALG_NAMES;
    protected static final Map KEY_ALG_NAMES;
    protected static final Map MAC_ALG_NAMES;
    private JcaJceHelper helper;

    static 
    {
        BASE_CIPHER_NAMES = new HashMap();
        CIPHER_ALG_NAMES = new HashMap();
        DIGEST_ALG_NAMES = new HashMap();
        KEY_ALG_NAMES = new HashMap();
        MAC_ALG_NAMES = new HashMap();
        BASE_CIPHER_NAMES.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        BASE_CIPHER_NAMES.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()), "RSA/ECB/PKCS1Padding");
        DIGEST_ALG_NAMES.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        DIGEST_ALG_NAMES.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        MAC_ALG_NAMES.put(IANAObjectIdentifiers.hmacSHA1, "HMACSHA1");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "HMACSHA1");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "HMACSHA224");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "HMACSHA256");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "HMACSHA384");
        MAC_ALG_NAMES.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "HMACSHA512");
        KEY_ALG_NAMES.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        KEY_ALG_NAMES.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }
}
