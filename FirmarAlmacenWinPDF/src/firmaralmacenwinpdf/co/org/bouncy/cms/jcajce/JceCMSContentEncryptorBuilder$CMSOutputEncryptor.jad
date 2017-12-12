// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceCMSContentEncryptorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceCMSContentEncryptorBuilder, EnvelopedDataHelper

private class JceCMSContentEncryptorBuilder$CMSOutputEncryptor
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

    JceCMSContentEncryptorBuilder$CMSOutputEncryptor(ASN1ObjectIdentifier encryptionOID, int keySize, SecureRandom random)
        throws CMSException
    {
        this$0 = JceCMSContentEncryptorBuilder.this;
        super();
        KeyGenerator keyGen = JceCMSContentEncryptorBuilder.access$000(JceCMSContentEncryptorBuilder.this).createKeyGenerator(encryptionOID);
        if(random == null)
            random = new SecureRandom();
        if(keySize < 0)
            keyGen.init(random);
        else
            keyGen.init(keySize, random);
        cipher = JceCMSContentEncryptorBuilder.access$000(JceCMSContentEncryptorBuilder.this).createCipher(encryptionOID);
        encKey = keyGen.generateKey();
        AlgorithmParameters params = JceCMSContentEncryptorBuilder.access$000(JceCMSContentEncryptorBuilder.this).generateParameters(encryptionOID, encKey, random);
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
        algorithmIdentifier = JceCMSContentEncryptorBuilder.access$000(JceCMSContentEncryptorBuilder.this).getAlgorithmIdentifier(encryptionOID, params);
    }
}
