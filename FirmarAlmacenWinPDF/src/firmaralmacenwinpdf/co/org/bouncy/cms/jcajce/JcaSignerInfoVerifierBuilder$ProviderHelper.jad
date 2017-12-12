// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSignerInfoVerifierBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JcaSignerInfoVerifierBuilder

private class JcaSignerInfoVerifierBuilder$ProviderHelper extends JcaSignerInfoVerifierBuilder.Helper
{

    ContentVerifierProvider createContentVerifierProvider(PublicKey publicKey)
        throws OperatorCreationException
    {
        return (new JcaContentVerifierProviderBuilder()).setProvider(provider).build(publicKey);
    }

    ContentVerifierProvider createContentVerifierProvider(X509Certificate certificate)
        throws OperatorCreationException
    {
        return (new JcaContentVerifierProviderBuilder()).setProvider(provider).build(certificate);
    }

    DigestCalculatorProvider createDigestCalculatorProvider()
        throws OperatorCreationException
    {
        return (new JcaDigestCalculatorProviderBuilder()).setProvider(provider).build();
    }

    ContentVerifierProvider createContentVerifierProvider(X509CertificateHolder certHolder)
        throws OperatorCreationException, CertificateException
    {
        return (new JcaContentVerifierProviderBuilder()).setProvider(provider).build(certHolder);
    }

    private final Provider provider;
    final JcaSignerInfoVerifierBuilder this$0;

    public JcaSignerInfoVerifierBuilder$ProviderHelper(Provider provider)
    {
        this$0 = JcaSignerInfoVerifierBuilder.this;
        super(JcaSignerInfoVerifierBuilder.this, null);
        this.provider = provider;
    }
}
