// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509AttributeCertificateHolder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.x509.AttributeCertificate;
import co.org.bouncy.cert.X509AttributeCertificateHolder;
import co.org.bouncy.x509_.X509AttributeCertificate;
import java.io.IOException;

public class JcaX509AttributeCertificateHolder extends X509AttributeCertificateHolder
{

    public JcaX509AttributeCertificateHolder(X509AttributeCertificate cert)
        throws IOException
    {
        super(AttributeCertificate.getInstance(cert.getEncoded()));
    }
}
