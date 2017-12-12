// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProviderCertHelper.java

package co.org.bouncy.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            CertHelper

class ProviderCertHelper extends CertHelper
{

    ProviderCertHelper(Provider provider)
    {
        this.provider = provider;
    }

    protected CertificateFactory createCertificateFactory(String type)
        throws CertificateException
    {
        return CertificateFactory.getInstance(type, provider);
    }

    private final Provider provider;
}
