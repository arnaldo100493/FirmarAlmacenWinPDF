// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyAgreeRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.OriginatorPublicKey;
import co.org.bouncy.asn1.cms.ecc.MQVuserKeyingMaterial;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cms.*;
import co.org.bouncy.jce.spec.MQVPrivateKeySpec;
import co.org.bouncy.jce.spec.MQVPublicKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper, 
//            CMSUtils

public abstract class JceKeyAgreeRecipient
    implements KeyAgreeRecipient
{

    public JceKeyAgreeRecipient(PrivateKey recipientKey)
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        contentHelper = helper;
        this.recipientKey = recipientKey;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        contentHelper = helper;
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        contentHelper = helper;
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider)
    {
        contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(String providerName)
    {
        contentHelper = CMSUtils.createContentHelper(providerName);
        return this;
    }

    private SecretKey calculateAgreedWrapKey(AlgorithmIdentifier keyEncAlg, ASN1ObjectIdentifier wrapAlg, PublicKey senderPublicKey, ASN1OctetString userKeyingMaterial, PrivateKey receiverPrivateKey)
        throws CMSException, GeneralSecurityException, IOException
    {
        String agreeAlg = keyEncAlg.getAlgorithm().getId();
        if(agreeAlg.equals(CMSEnvelopedGenerator.ECMQV_SHA1KDF))
        {
            byte ukmEncoding[] = userKeyingMaterial.getOctets();
            MQVuserKeyingMaterial ukm = MQVuserKeyingMaterial.getInstance(ASN1Primitive.fromByteArray(ukmEncoding));
            SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(getPrivateKeyAlgorithmIdentifier(), ukm.getEphemeralPublicKey().getPublicKey().getBytes());
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubInfo.getEncoded());
            KeyFactory fact = helper.createKeyFactory(keyEncAlg.getAlgorithm());
            PublicKey ephemeralKey = fact.generatePublic(pubSpec);
            senderPublicKey = new MQVPublicKeySpec(senderPublicKey, ephemeralKey);
            receiverPrivateKey = new MQVPrivateKeySpec(receiverPrivateKey, receiverPrivateKey);
        }
        KeyAgreement agreement = helper.createKeyAgreement(keyEncAlg.getAlgorithm());
        agreement.init(receiverPrivateKey);
        agreement.doPhase(senderPublicKey, true);
        return agreement.generateSecret(wrapAlg.getId());
    }

    private Key unwrapSessionKey(ASN1ObjectIdentifier wrapAlg, SecretKey agreedKey, ASN1ObjectIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException, InvalidKeyException, NoSuchAlgorithmException
    {
        Cipher keyCipher = helper.createCipher(wrapAlg);
        keyCipher.init(4, agreedKey);
        return keyCipher.unwrap(encryptedContentEncryptionKey, helper.getBaseCipherName(contentEncryptionAlgorithm), 3);
    }

    protected Key extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, SubjectPublicKeyInfo senderKey, ASN1OctetString userKeyingMaterial, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        try
        {
            ASN1ObjectIdentifier wrapAlg = AlgorithmIdentifier.getInstance(keyEncryptionAlgorithm.getParameters()).getAlgorithm();
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(senderKey.getEncoded());
            KeyFactory fact = helper.createKeyFactory(keyEncryptionAlgorithm.getAlgorithm());
            PublicKey senderPublicKey = fact.generatePublic(pubSpec);
            SecretKey agreedWrapKey = calculateAgreedWrapKey(keyEncryptionAlgorithm, wrapAlg, senderPublicKey, userKeyingMaterial, recipientKey);
            return unwrapSessionKey(wrapAlg, agreedWrapKey, contentEncryptionAlgorithm.getAlgorithm(), encryptedContentEncryptionKey);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new CMSException("can't find algorithm.", e);
        }
        catch(InvalidKeyException e)
        {
            throw new CMSException("key invalid in message.", e);
        }
        catch(InvalidKeySpecException e)
        {
            throw new CMSException("originator key spec invalid.", e);
        }
        catch(NoSuchPaddingException e)
        {
            throw new CMSException("required padding not supported.", e);
        }
        catch(Exception e)
        {
            throw new CMSException("originator key invalid.", e);
        }
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier()
    {
        return PrivateKeyInfo.getInstance(recipientKey.getEncoded()).getPrivateKeyAlgorithm();
    }

    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
}
