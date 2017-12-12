// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedCertHelper.java

package co.org.bouncy.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            CertHelper

class NamedCertHelper extends CertHelper
{

    NamedCertHelper(String providerName)
    {
        this.providerName = providerName;
    }

    protected CertificateFactory createCertificateFactory(String type)
        throws CertificateException, NoSuchProviderException
    {
        return CertificateFactory.getInstance(type, providerName);
    }

    private final String providerName;
}
