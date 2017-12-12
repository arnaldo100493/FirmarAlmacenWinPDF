// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.KEKIdentifier;
import co.org.bouncy.asn1.cms.OriginatorInfo;
import co.org.bouncy.asn1.kisa.KISAObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cms.jcajce.JceKEKRecipientInfoGenerator;
import co.org.bouncy.cms.jcajce.JceKeyAgreeRecipientInfoGenerator;
import co.org.bouncy.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import co.org.bouncy.cms.jcajce.JcePasswordRecipientInfoGenerator;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.cms:
//            PKCS5Scheme2UTF8PBEKey, KEKRecipientInfoGenerator, CMSPBEKey, PasswordRecipientInfoGenerator, 
//            OriginatorInformation, CMSUtils, CMSAttributeTableGenerator, RecipientInfoGenerator

public class CMSEnvelopedGenerator
{

    public CMSEnvelopedGenerator()
    {
        this(new SecureRandom());
    }

    public CMSEnvelopedGenerator(SecureRandom rand)
    {
        oldRecipientInfoGenerators = new ArrayList();
        recipientInfoGenerators = new ArrayList();
        unprotectedAttributeGenerator = null;
        this.rand = rand;
    }

    public void setUnprotectedAttributeGenerator(CMSAttributeTableGenerator unprotectedAttributeGenerator)
    {
        this.unprotectedAttributeGenerator = unprotectedAttributeGenerator;
    }

    public void setOriginatorInfo(OriginatorInformation originatorInfo)
    {
        this.originatorInfo = originatorInfo.toASN1Structure();
    }

    /**
     * @deprecated Method addKeyTransRecipient is deprecated
     */

    public void addKeyTransRecipient(X509Certificate cert)
        throws IllegalArgumentException
    {
        try
        {
            oldRecipientInfoGenerators.add(new JceKeyTransRecipientInfoGenerator(cert));
        }
        catch(CertificateEncodingException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unable to encode certificate: ").append(e.getMessage()).toString());
        }
    }

    /**
     * @deprecated Method addKeyTransRecipient is deprecated
     */

    public void addKeyTransRecipient(PublicKey key, byte subKeyId[])
        throws IllegalArgumentException
    {
        oldRecipientInfoGenerators.add(new JceKeyTransRecipientInfoGenerator(subKeyId, key));
    }

    /**
     * @deprecated Method addKEKRecipient is deprecated
     */

    public void addKEKRecipient(SecretKey key, byte keyIdentifier[])
    {
        addKEKRecipient(key, new KEKIdentifier(keyIdentifier, null, null));
    }

    /**
     * @deprecated Method addKEKRecipient is deprecated
     */

    public void addKEKRecipient(SecretKey key, KEKIdentifier kekIdentifier)
    {
        oldRecipientInfoGenerators.add(new JceKEKRecipientInfoGenerator(kekIdentifier, key));
    }

    /**
     * @deprecated Method addPasswordRecipient is deprecated
     */

    public void addPasswordRecipient(CMSPBEKey pbeKey, String kekAlgorithmOid)
    {
        oldRecipientInfoGenerators.add((new JcePasswordRecipientInfoGenerator(new ASN1ObjectIdentifier(kekAlgorithmOid), pbeKey.getPassword())).setSaltAndIterationCount(pbeKey.getSalt(), pbeKey.getIterationCount()).setPasswordConversionScheme((pbeKey instanceof PKCS5Scheme2UTF8PBEKey) ? 1 : 0));
    }

    /**
     * @deprecated Method addKeyAgreementRecipient is deprecated
     */

    public void addKeyAgreementRecipient(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, X509Certificate recipientCert, String cekWrapAlgorithm, String provider)
        throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException
    {
        addKeyAgreementRecipient(agreementAlgorithm, senderPrivateKey, senderPublicKey, recipientCert, cekWrapAlgorithm, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method addKeyAgreementRecipient is deprecated
     */

    public void addKeyAgreementRecipient(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, X509Certificate recipientCert, String cekWrapAlgorithm, Provider provider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        List recipients = new ArrayList();
        recipients.add(recipientCert);
        addKeyAgreementRecipients(agreementAlgorithm, senderPrivateKey, senderPublicKey, recipients, cekWrapAlgorithm, provider);
    }

    /**
     * @deprecated Method addKeyAgreementRecipients is deprecated
     */

    public void addKeyAgreementRecipients(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, Collection recipientCerts, String cekWrapAlgorithm, String provider)
        throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException
    {
        addKeyAgreementRecipients(agreementAlgorithm, senderPrivateKey, senderPublicKey, recipientCerts, cekWrapAlgorithm, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method addKeyAgreementRecipients is deprecated
     */

    public void addKeyAgreementRecipients(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, Collection recipientCerts, String cekWrapAlgorithm, Provider provider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        JceKeyAgreeRecipientInfoGenerator recipientInfoGenerator = (new JceKeyAgreeRecipientInfoGenerator(new ASN1ObjectIdentifier(agreementAlgorithm), senderPrivateKey, senderPublicKey, new ASN1ObjectIdentifier(cekWrapAlgorithm))).setProvider(provider);
        for(Iterator it = recipientCerts.iterator(); it.hasNext();)
            try
            {
                recipientInfoGenerator.addRecipient((X509Certificate)it.next());
            }
            catch(CertificateEncodingException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unable to encode certificate: ").append(e.getMessage()).toString());
            }

        oldRecipientInfoGenerators.add(recipientInfoGenerator);
    }

    public void addRecipientInfoGenerator(RecipientInfoGenerator recipientGenerator)
    {
        recipientInfoGenerators.add(recipientGenerator);
    }

    protected AlgorithmIdentifier getAlgorithmIdentifier(String encryptionOID, AlgorithmParameters params)
        throws IOException
    {
        ASN1Encodable asn1Params;
        if(params != null)
            asn1Params = ASN1Primitive.fromByteArray(params.getEncoded("ASN.1"));
        else
            asn1Params = DERNull.INSTANCE;
        return new AlgorithmIdentifier(new ASN1ObjectIdentifier(encryptionOID), asn1Params);
    }

    protected void convertOldRecipients(SecureRandom rand, Provider provider)
    {
        Iterator it = oldRecipientInfoGenerators.iterator();
        do
        {
            if(!it.hasNext())
                break;
            Object recipient = it.next();
            if(recipient instanceof JceKeyTransRecipientInfoGenerator)
            {
                JceKeyTransRecipientInfoGenerator recip = (JceKeyTransRecipientInfoGenerator)recipient;
                if(provider != null)
                    recip.setProvider(provider);
                recipientInfoGenerators.add(recip);
            } else
            if(recipient instanceof KEKRecipientInfoGenerator)
            {
                JceKEKRecipientInfoGenerator recip = (JceKEKRecipientInfoGenerator)recipient;
                if(provider != null)
                    recip.setProvider(provider);
                recip.setSecureRandom(rand);
                recipientInfoGenerators.add(recip);
            } else
            if(recipient instanceof JcePasswordRecipientInfoGenerator)
            {
                JcePasswordRecipientInfoGenerator recip = (JcePasswordRecipientInfoGenerator)recipient;
                if(provider != null)
                    recip.setProvider(provider);
                recip.setSecureRandom(rand);
                recipientInfoGenerators.add(recip);
            } else
            if(recipient instanceof JceKeyAgreeRecipientInfoGenerator)
            {
                JceKeyAgreeRecipientInfoGenerator recip = (JceKeyAgreeRecipientInfoGenerator)recipient;
                if(provider != null)
                    recip.setProvider(provider);
                recip.setSecureRandom(rand);
                recipientInfoGenerators.add(recip);
            }
        } while(true);
        oldRecipientInfoGenerators.clear();
    }

    public static final String DES_EDE3_CBC;
    public static final String RC2_CBC;
    public static final String IDEA_CBC = "1.3.6.1.4.1.188.7.1.1.2";
    public static final String CAST5_CBC = "1.2.840.113533.7.66.10";
    public static final String AES128_CBC;
    public static final String AES192_CBC;
    public static final String AES256_CBC;
    public static final String CAMELLIA128_CBC;
    public static final String CAMELLIA192_CBC;
    public static final String CAMELLIA256_CBC;
    public static final String SEED_CBC;
    public static final String DES_EDE3_WRAP;
    public static final String AES128_WRAP;
    public static final String AES192_WRAP;
    public static final String AES256_WRAP;
    public static final String CAMELLIA128_WRAP;
    public static final String CAMELLIA192_WRAP;
    public static final String CAMELLIA256_WRAP;
    public static final String SEED_WRAP;
    public static final String ECDH_SHA1KDF;
    public static final String ECMQV_SHA1KDF;
    final List oldRecipientInfoGenerators;
    final List recipientInfoGenerators;
    protected CMSAttributeTableGenerator unprotectedAttributeGenerator;
    final SecureRandom rand;
    protected OriginatorInfo originatorInfo;

    static 
    {
        DES_EDE3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC.getId();
        RC2_CBC = PKCSObjectIdentifiers.RC2_CBC.getId();
        AES128_CBC = NISTObjectIdentifiers.id_aes128_CBC.getId();
        AES192_CBC = NISTObjectIdentifiers.id_aes192_CBC.getId();
        AES256_CBC = NISTObjectIdentifiers.id_aes256_CBC.getId();
        CAMELLIA128_CBC = NTTObjectIdentifiers.id_camellia128_cbc.getId();
        CAMELLIA192_CBC = NTTObjectIdentifiers.id_camellia192_cbc.getId();
        CAMELLIA256_CBC = NTTObjectIdentifiers.id_camellia256_cbc.getId();
        SEED_CBC = KISAObjectIdentifiers.id_seedCBC.getId();
        DES_EDE3_WRAP = PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId();
        AES128_WRAP = NISTObjectIdentifiers.id_aes128_wrap.getId();
        AES192_WRAP = NISTObjectIdentifiers.id_aes192_wrap.getId();
        AES256_WRAP = NISTObjectIdentifiers.id_aes256_wrap.getId();
        CAMELLIA128_WRAP = NTTObjectIdentifiers.id_camellia128_wrap.getId();
        CAMELLIA192_WRAP = NTTObjectIdentifiers.id_camellia192_wrap.getId();
        CAMELLIA256_WRAP = NTTObjectIdentifiers.id_camellia256_wrap.getId();
        SEED_WRAP = KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId();
        ECDH_SHA1KDF = X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme.getId();
        ECMQV_SHA1KDF = X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme.getId();
    }
}
