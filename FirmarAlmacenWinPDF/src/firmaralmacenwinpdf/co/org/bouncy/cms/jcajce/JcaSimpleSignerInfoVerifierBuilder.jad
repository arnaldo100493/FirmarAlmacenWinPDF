// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSimpleSignerInfoVerifierBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import co.org.bouncy.cms.SignerInformationVerifier;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class JcaSimpleSignerInfoVerifierBuilder
{
    private class ProviderHelper extends Helper
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
        final JcaSimpleSignerInfoVerifierBuilder this$0;

        public ProviderHelper(Provider provider)
        {
            this$0 = JcaSimpleSignerInfoVerifierBuilder.this;
            super();
            this.provider = provider;
        }
    }

    private class NamedHelper extends Helper
    {

        ContentVerifierProvider createContentVerifierProvider(PublicKey publicKey)
            throws OperatorCreationException
        {
            return (new JcaContentVerifierProviderBuilder()).setProvider(providerName).build(publicKey);
        }

        ContentVerifierProvider createContentVerifierProvider(X509Certificate certificate)
            throws OperatorCreationException
        {
            return (new JcaContentVerifierProviderBuilder()).setProvider(providerName).build(certificate);
        }

        DigestCalculatorProvider createDigestCalculatorProvider()
            throws OperatorCreationException
        {
            return (new JcaDigestCalculatorProviderBuilder()).setProvider(providerName).build();
        }

        ContentVerifierProvider createContentVerifierProvider(X509CertificateHolder certHolder)
            throws OperatorCreationException, CertificateException
        {
            return (new JcaContentVerifierProviderBuilder()).setProvider(providerName).build(certHolder);
        }

        private final String providerName;
        final JcaSimpleSignerInfoVerifierBuilder this$0;

        public NamedHelper(String providerName)
        {
            this$0 = JcaSimpleSignerInfoVerifierBuilder.this;
            super();
            this.providerName = providerName;
        }
    }

    private class Helper
    {

        ContentVerifierProvider createContentVerifierProvider(PublicKey publicKey)
            throws OperatorCreationException
        {
            return (new JcaContentVerifierProviderBuilder()).build(publicKey);
        }

        ContentVerifierProvider createContentVerifierProvider(X509Certificate certificate)
            throws OperatorCreationException
        {
            return (new JcaContentVerifierProviderBuilder()).build(certificate);
        }

        ContentVerifierProvider createContentVerifierProvider(X509CertificateHolder certHolder)
            throws OperatorCreationException, CertificateException
        {
            return (new JcaContentVerifierProviderBuilder()).build(certHolder);
        }

        DigestCalculatorProvider createDigestCalculatorProvider()
            throws OperatorCreationException
        {
            return (new JcaDigestCalculatorProviderBuilder()).build();
        }

        final JcaSimpleSignerInfoVerifierBuilder this$0;

        private Helper()
        {
            this$0 = JcaSimpleSignerInfoVerifierBuilder.this;
            super();
        }

    }


    public JcaSimpleSignerInfoVerifierBuilder()
    {
        helper = new Helper();
    }

    public JcaSimpleSignerInfoVerifierBuilder setProvider(Provider provider)
    {
        helper = new ProviderHelper(provider);
        return this;
    }

    public JcaSimpleSignerInfoVerifierBuilder setProvider(String providerName)
    {
        helper = new NamedHelper(providerName);
        return this;
    }

    public SignerInformationVerifier build(X509CertificateHolder certHolder)
        throws OperatorCreationException, CertificateException
    {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), helper.createContentVerifierProvider(certHolder), helper.createDigestCalculatorProvider());
    }

    public SignerInformationVerifier build(X509Certificate certificate)
        throws OperatorCreationException
    {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), helper.createContentVerifierProvider(certificate), helper.createDigestCalculatorProvider());
    }

    public SignerInformationVerifier build(PublicKey pubKey)
        throws OperatorCreationException
    {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), helper.createContentVerifierProvider(pubKey), helper.createDigestCalculatorProvider());
    }

    private Helper helper;
}
