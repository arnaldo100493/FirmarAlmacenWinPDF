// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcCMSContentEncryptorBuilder.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSAlgorithm;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.util.Integers;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms.bc:
//            EnvelopedDataHelper

public class BcCMSContentEncryptorBuilder
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
            if(cipher instanceof BufferedBlockCipher)
                return new CipherOutputStream(dOut, (BufferedBlockCipher)cipher);
            else
                return new CipherOutputStream(dOut, (StreamCipher)cipher);
        }

        public GenericKey getKey()
        {
            return new GenericKey(algorithmIdentifier, encKey.getKey());
        }

        private KeyParameter encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Object cipher;
        final BcCMSContentEncryptorBuilder this$0;

        CMSOutputEncryptor(ASN1ObjectIdentifier encryptionOID, int keySize, SecureRandom random)
            throws CMSException
        {
            this$0 = BcCMSContentEncryptorBuilder.this;
            super();
            if(random == null)
                random = new SecureRandom();
            CipherKeyGenerator keyGen = helper.createKeyGenerator(encryptionOID, random);
            encKey = new KeyParameter(keyGen.generateKey());
            algorithmIdentifier = helper.generateAlgorithmIdentifier(encryptionOID, encKey, random);
            cipher = helper.createContentCipher(true, encKey, algorithmIdentifier);
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

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier encryptionOID)
    {
        this(encryptionOID, getKeySize(encryptionOID));
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier encryptionOID, int keySize)
    {
        helper = new EnvelopedDataHelper();
        this.encryptionOID = encryptionOID;
        this.keySize = keySize;
    }

    public BcCMSContentEncryptorBuilder setSecureRandom(SecureRandom random)
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
