// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceCMSContentEncryptorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSAlgorithm;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import co.org.bouncy.util.Integers;
import java.io.OutputStream;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public class JceCMSContentEncryptorBuilder
{
    private class CMSOutputEncryptor
        implements OutputEncryptor
    {

        public AlgorithmIdentifier getAlgorithmIdentifier()
        {
            return algorithmIdentifier;
        }

        public OutputStream getOutputStream(OutputStream dOut)
        {
            return new CipherOutputStream(dOut, cipher);
        }

        public GenericKey getKey()
        {
            return new JceGenericKey(algorithmIdentifier, encKey);
        }

        private SecretKey encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Cipher cipher;
        final JceCMSContentEncryptorBuilder this$0;

        CMSOutputEncryptor(ASN1ObjectIdentifier encryptionOID, int keySize, SecureRandom random)
            throws CMSException
        {
            this$0 = JceCMSContentEncryptorBuilder.this;
            super();
            KeyGenerator keyGen = helper.createKeyGenerator(encryptionOID);
            if(random == null)
                random = new SecureRandom();
            if(keySize < 0)
                keyGen.init(random);
            else
                keyGen.init(keySize, random);
            cipher = helper.createCipher(encryptionOID);
            encKey = keyGen.generateKey();
            AlgorithmParameters params = helper.generateParameters(encryptionOID, encKey, random);
            try
            {
                cipher.init(1, encKey, params, random);
            }
            catch(GeneralSecurityException e)
            {
                throw new CMSException((new StringBuilder()).append("unable to initialize cipher: ").append(e.getMessage()).toString(), e);
            }
            if(params == null)
                params = cipher.getParameters();
            algorithmIdentifier = helper.getAlgorithmIdentifier(encryptionOID, params);
        }
    }


    private static int getKeySize(ASN1ObjectIdentifier oid)
    {
        Integer size = (Integer)keySizes.get(oid);
        if(size != null)
            return size.intValue();
        else
            return -1;
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier encryptionOID)
    {
        this(encryptionOID, getKeySize(encryptionOID));
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier encryptionOID, int keySize)
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.encryptionOID = encryptionOID;
        this.keySize = keySize;
    }

    public JceCMSContentEncryptorBuilder setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    public JceCMSContentEncryptorBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public OutputEncryptor build()
        throws CMSException
    {
        return new CMSOutputEncryptor(encryptionOID, keySize, random);
    }

    private static Map keySizes;
    private final ASN1ObjectIdentifier encryptionOID;
    private final int keySize;
    private EnvelopedDataHelper helper;
    private SecureRandom random;

    static 
    {
        keySizes = new HashMap();
        keySizes.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
        keySizes.put(CMSAlgorithm.CAMELLIA128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.CAMELLIA192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.CAMELLIA256_CBC, Integers.valueOf(256));
    }

}
