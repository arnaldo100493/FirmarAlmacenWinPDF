// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultTlsEncryptionCredentials.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsEncryptionCredentials, TlsContext, Certificate

public class DefaultTlsEncryptionCredentials
    implements TlsEncryptionCredentials
{

    public DefaultTlsEncryptionCredentials(TlsContext context, Certificate certificate, AsymmetricKeyParameter privateKey)
    {
        if(certificate == null)
            throw new IllegalArgumentException("'certificate' cannot be null");
        if(certificate.isEmpty())
            throw new IllegalArgumentException("'certificate' cannot be empty");
        if(privateKey == null)
            throw new IllegalArgumentException("'privateKey' cannot be null");
        if(!privateKey.isPrivate())
            throw new IllegalArgumentException("'privateKey' must be private");
        if(!(privateKey instanceof RSAKeyParameters))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("'privateKey' type not supported: ").append(privateKey.getClass().getName()).toString());
        } else
        {
            this.context = context;
            this.certificate = certificate;
            this.privateKey = privateKey;
            return;
        }
    }

    public Certificate getCertificate()
    {
        return certificate;
    }

    public byte[] decryptPreMasterSecret(byte encryptedPreMasterSecret[])
        throws IOException
    {
        PKCS1Encoding encoding = new PKCS1Encoding(new RSABlindedEngine());
        encoding.init(false, new ParametersWithRandom(privateKey, context.getSecureRandom()));
        try
        {
            return encoding.processBlock(encryptedPreMasterSecret, 0, encryptedPreMasterSecret.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new TlsFatalAlert((short)47);
        }
    }

    protected TlsContext context;
    protected Certificate certificate;
    protected AsymmetricKeyParameter privateKey;
}
