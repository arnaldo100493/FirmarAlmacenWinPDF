// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaMiscPEMGenerator.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.jcajce.JcaX509AttributeCertificateHolder;
import co.org.bouncy.cert.jcajce.JcaX509CRLHolder;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.jce.PKCS10CertificationRequest;
import co.org.bouncy.openssl.MiscPEMGenerator;
import co.org.bouncy.openssl.PEMEncryptor;
import co.org.bouncy.x509_.X509AttributeCertificate;
import co.org.bouncy.x509_.X509V2AttributeCertificate;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

public class JcaMiscPEMGenerator extends MiscPEMGenerator
{

    public JcaMiscPEMGenerator(Object o)
        throws IOException
    {
        super(convertObject(o));
    }

    public JcaMiscPEMGenerator(Object o, PEMEncryptor encryptor)
        throws IOException
    {
        super(convertObject(o), encryptor);
    }

    private static Object convertObject(Object o)
        throws IOException
    {
        if(o instanceof X509Certificate)
            try
            {
                return new JcaX509CertificateHolder((X509Certificate)o);
            }
            catch(CertificateEncodingException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot encode object: ").append(e.toString()).toString());
            }
        if(o instanceof X509CRL)
            try
            {
                return new JcaX509CRLHolder((X509CRL)o);
            }
            catch(CRLException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot encode object: ").append(e.toString()).toString());
            }
        if(o instanceof KeyPair)
            return convertObject(((KeyPair)o).getPrivate());
        if(o instanceof PrivateKey)
            return PrivateKeyInfo.getInstance(((Key)o).getEncoded());
        if(o instanceof PublicKey)
            return SubjectPublicKeyInfo.getInstance(((PublicKey)o).getEncoded());
        if(o instanceof X509AttributeCertificate)
            return new JcaX509AttributeCertificateHolder((X509V2AttributeCertificate)o);
        if(o instanceof PKCS10CertificationRequest)
            return new co.org.bouncy.pkcs.PKCS10CertificationRequest(((PKCS10CertificationRequest)o).getEncoded());
        else
            return o;
    }

    private Object obj;
    private String algorithm;
    private char password[];
    private SecureRandom random;
    private Provider provider;
}
