// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcCMSContentEncryptorBuilder.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcCMSContentEncryptorBuilder, EnvelopedDataHelper

private class BcCMSContentEncryptorBuilder$CMSOutputEncryptor
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

    BcCMSContentEncryptorBuilder$CMSOutputEncryptor(ASN1ObjectIdentifier encryptionOID, int keySize, SecureRandom random)
        throws CMSException
    {
        this$0 = BcCMSContentEncryptorBuilder.this;
        super();
        if(random == null)
            random = new SecureRandom();
        CipherKeyGenerator keyGen = BcCMSContentEncryptorBuilder.access$000(BcCMSContentEncryptorBuilder.this).createKeyGenerator(encryptionOID, random);
        encKey = new KeyParameter(keyGen.generateKey());
        algorithmIdentifier = BcCMSContentEncryptorBuilder.access$000(BcCMSContentEncryptorBuilder.this).generateAlgorithmIdentifier(encryptionOID, encKey, random);
        cipher = BcCMSContentEncryptorBuilder.access$000(BcCMSContentEncryptorBuilder.this).createContentCipher(true, encKey, algorithmIdentifier);
    }
}
