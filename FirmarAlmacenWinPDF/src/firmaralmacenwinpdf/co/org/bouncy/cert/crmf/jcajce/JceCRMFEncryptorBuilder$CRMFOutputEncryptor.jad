// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceCRMFEncryptorBuilder.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.cert.crmf.jcajce:
//            JceCRMFEncryptorBuilder, CRMFHelper

private class JceCRMFEncryptorBuilder$CRMFOutputEncryptor
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
    final JceCRMFEncryptorBuilder this$0;

    JceCRMFEncryptorBuilder$CRMFOutputEncryptor(ASN1ObjectIdentifier encryptionOID, int keySize, SecureRandom random)
        throws CRMFException
    {
        this$0 = JceCRMFEncryptorBuilder.this;
        super();
        KeyGenerator keyGen = JceCRMFEncryptorBuilder.access$000(JceCRMFEncryptorBuilder.this).createKeyGenerator(encryptionOID);
        if(random == null)
            random = new SecureRandom();
        if(keySize < 0)
            keyGen.init(random);
        else
            keyGen.init(keySize, random);
        cipher = JceCRMFEncryptorBuilder.access$000(JceCRMFEncryptorBuilder.this).createCipher(encryptionOID);
        encKey = keyGen.generateKey();
        AlgorithmParameters params = JceCRMFEncryptorBuilder.access$000(JceCRMFEncryptorBuilder.this).generateParameters(encryptionOID, encKey, random);
        try
        {
            cipher.init(1, encKey, params, random);
        }
        catch(GeneralSecurityException e)
        {
            throw new CRMFException((new StringBuilder()).append("unable to initialize cipher: ").append(e.getMessage()).toString(), e);
        }
        if(params == null)
            params = cipher.getParameters();
        algorithmIdentifier = JceCRMFEncryptorBuilder.access$000(JceCRMFEncryptorBuilder.this).getAlgorithmIdentifier(encryptionOID, params);
    }
}
