// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPKCS12SafeBagBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.pkcs.PKCS12SafeBagBuilder;
import co.org.bouncy.pkcs.PKCSIOException;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaPKCS12SafeBagBuilder extends PKCS12SafeBagBuilder
{

    public JcaPKCS12SafeBagBuilder(X509Certificate certificate)
        throws IOException
    {
        super(convertCert(certificate));
    }

    private static Certificate convertCert(X509Certificate certificate)
        throws IOException
    {
        try
        {
            return Certificate.getInstance(certificate.getEncoded());
        }
        catch(CertificateEncodingException e)
        {
            throw new PKCSIOException((new StringBuilder()).append("cannot encode certificate: ").append(e.getMessage()).toString(), e);
        }
    }

    public JcaPKCS12SafeBagBuilder(PrivateKey privateKey, OutputEncryptor encryptor)
    {
        super(PrivateKeyInfo.getInstance(privateKey.getEncoded()), encryptor);
    }

    public JcaPKCS12SafeBagBuilder(PrivateKey privateKey)
    {
        super(PrivateKeyInfo.getInstance(privateKey.getEncoded()));
    }
}
