// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CRLHolder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.cert.X509CRLHolder;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;

public class JcaX509CRLHolder extends X509CRLHolder
{

    public JcaX509CRLHolder(X509CRL crl)
        throws CRLException
    {
        super(CertificateList.getInstance(crl.getEncoded()));
    }
}
