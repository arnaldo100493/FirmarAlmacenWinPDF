// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceSymmetricKeyWrapper.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.kisa.KISAObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            OperatorHelper, OperatorUtils

public class JceSymmetricKeyWrapper extends SymmetricKeyWrapper
{

    public JceSymmetricKeyWrapper(SecretKey wrappingKey)
    {
        super(determineKeyEncAlg(wrappingKey));
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.wrappingKey = wrappingKey;
    }

    public JceSymmetricKeyWrapper setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyWrapper setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JceSymmetricKeyWrapper setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey encryptionKey)
        throws OperatorException
    {
        Key contentEncryptionKeySpec = OperatorUtils.getJceKey(encryptionKey);
        Cipher keyEncryptionCipher = helper.createSymmetricWrapper(getAlgorithmIdentifier().getAlgorithm());
        try
        {
            keyEncryptionCipher.init(3, wrappingKey, random);
            return keyEncryptionCipher.wrap(contentEncryptionKeySpec);
        }
        catch(GeneralSecurityException e)
        {
            throw new OperatorException((new StringBuilder()).append("cannot wrap key: ").append(e.getMessage()).toString(), e);
        }
    }

    private static AlgorithmIdentifier determineKeyEncAlg(SecretKey key)
    {
        String algorithm = key.getAlgorithm();
        if(algorithm.startsWith("DES"))
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.6"), DERNull.INSTANCE);
        if(algorithm.startsWith("RC2"))
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.7"), new ASN1Integer(58L));
        if(algorithm.startsWith("AES"))
        {
            int length = key.getEncoded().length * 8;
            ASN1ObjectIdentifier wrapOid;
            if(length == 128)
                wrapOid = NISTObjectIdentifiers.id_aes128_wrap;
            else
            if(length == 192)
                wrapOid = NISTObjectIdentifiers.id_aes192_wrap;
            else
            if(length == 256)
                wrapOid = NISTObjectIdentifiers.id_aes256_wrap;
            else
                throw new IllegalArgumentException("illegal keysize in AES");
            return new AlgorithmIdentifier(wrapOid);
        }
        if(algorithm.startsWith("SEED"))
            return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
        if(algorithm.startsWith("Camellia"))
        {
            int length = key.getEncoded().length * 8;
            ASN1ObjectIdentifier wrapOid;
            if(length == 128)
                wrapOid = NTTObjectIdentifiers.id_camellia128_wrap;
            else
            if(length == 192)
                wrapOid = NTTObjectIdentifiers.id_camellia192_wrap;
            else
            if(length == 256)
                wrapOid = NTTObjectIdentifiers.id_camellia256_wrap;
            else
                throw new IllegalArgumentException("illegal keysize in Camellia");
            return new AlgorithmIdentifier(wrapOid);
        } else
        {
            throw new IllegalArgumentException("unknown algorithm");
        }
    }

    private OperatorHelper helper;
    private SecureRandom random;
    private SecretKey wrappingKey;
}
