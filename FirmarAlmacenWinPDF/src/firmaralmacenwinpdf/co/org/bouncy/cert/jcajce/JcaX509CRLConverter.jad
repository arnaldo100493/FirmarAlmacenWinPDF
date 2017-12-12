// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CRLConverter.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.cert.X509CRLHolder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.*;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            DefaultCertHelper, ProviderCertHelper, NamedCertHelper, CertHelper

public class JcaX509CRLConverter
{
    private class ExCRLException extends CRLException
    {

        public Throwable getCause()
        {
            return cause;
        }

        private Throwable cause;
        final JcaX509CRLConverter this$0;

        public ExCRLException(String msg, Throwable cause)
        {
            this$0 = JcaX509CRLConverter.this;
            super(msg);
            this.cause = cause;
        }
    }


    public JcaX509CRLConverter()
    {
        helper = new DefaultCertHelper();
        helper = new DefaultCertHelper();
    }

    public JcaX509CRLConverter setProvider(Provider provider)
    {
        helper = new ProviderCertHelper(provider);
        return this;
    }

    public JcaX509CRLConverter setProvider(String providerName)
    {
        helper = new NamedCertHelper(providerName);
        return this;
    }

    public X509CRL getCRL(X509CRLHolder crlHolder)
        throws CRLException
    {
        try
        {
            CertificateFactory cFact = helper.getCertificateFactory("X.509");
            return (X509CRL)cFact.generateCRL(new ByteArrayInputStream(crlHolder.getEncoded()));
        }
        catch(IOException e)
        {
            throw new ExCRLException((new StringBuilder()).append("exception parsing certificate: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchProviderException e)
        {
            throw new ExCRLException((new StringBuilder()).append("cannot find required provider:").append(e.getMessage()).toString(), e);
        }
        catch(CertificateException e)
        {
            throw new ExCRLException((new StringBuilder()).append("cannot create factory: ").append(e.getMessage()).toString(), e);
        }
    }

    private CertHelper helper;
}
