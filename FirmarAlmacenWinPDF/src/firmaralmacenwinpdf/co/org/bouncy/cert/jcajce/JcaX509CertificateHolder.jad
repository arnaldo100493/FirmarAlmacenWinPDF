// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CertificateHolder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.cert.X509CertificateHolder;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaX509CertificateHolder extends X509CertificateHolder
{

    public JcaX509CertificateHolder(X509Certificate cert)
        throws CertificateEncodingException
    {
        super(Certificate.getInstance(cert.getEncoded()));
    }
}
