// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultTlsSignerCredentials.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsRSASigner, TlsDSSSigner, TlsECDSASigner, TlsFatalAlert, 
//            TlsSignerCredentials, TlsContext, Certificate, TlsSigner

public class DefaultTlsSignerCredentials
    implements TlsSignerCredentials
{

    public DefaultTlsSignerCredentials(TlsContext context, Certificate certificate, AsymmetricKeyParameter privateKey)
    {
        if(certificate == null)
            throw new IllegalArgumentException("'certificate' cannot be null");
        if(certificate.isEmpty())
            throw new IllegalArgumentException("'certificate' cannot be empty");
        if(privateKey == null)
            throw new IllegalArgumentException("'privateKey' cannot be null");
        if(!privateKey.isPrivate())
            throw new IllegalArgumentException("'privateKey' must be private");
        if(privateKey instanceof RSAKeyParameters)
            signer = new TlsRSASigner();
        else
        if(privateKey instanceof DSAPrivateKeyParameters)
            signer = new TlsDSSSigner();
        else
        if(privateKey instanceof ECPrivateKeyParameters)
            signer = new TlsECDSASigner();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("'privateKey' type not supported: ").append(privateKey.getClass().getName()).toString());
        signer.init(context);
        this.context = context;
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    public Certificate getCertificate()
    {
        return certificate;
    }

    public byte[] generateCertificateSignature(byte md5andsha1[])
        throws IOException
    {
        try
        {
            return signer.generateRawSignature(privateKey, md5andsha1);
        }
        catch(CryptoException e)
        {
            throw new TlsFatalAlert((short)80);
        }
    }

    protected TlsContext context;
    protected Certificate certificate;
    protected AsymmetricKeyParameter privateKey;
    protected TlsSigner signer;
}
