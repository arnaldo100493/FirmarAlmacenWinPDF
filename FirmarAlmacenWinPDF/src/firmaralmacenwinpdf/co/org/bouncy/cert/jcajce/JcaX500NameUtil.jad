// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX500NameUtil.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x500.X500NameStyle;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class JcaX500NameUtil
{

    public JcaX500NameUtil()
    {
    }

    public static X500Name getIssuer(X509Certificate certificate)
    {
        return X500Name.getInstance(certificate.getIssuerX500Principal().getEncoded());
    }

    public static X500Name getSubject(X509Certificate certificate)
    {
        return X500Name.getInstance(certificate.getSubjectX500Principal().getEncoded());
    }

    public static X500Name getIssuer(X500NameStyle style, X509Certificate certificate)
    {
        return X500Name.getInstance(style, certificate.getIssuerX500Principal().getEncoded());
    }

    public static X500Name getSubject(X500NameStyle style, X509Certificate certificate)
    {
        return X500Name.getInstance(style, certificate.getSubjectX500Principal().getEncoded());
    }
}
