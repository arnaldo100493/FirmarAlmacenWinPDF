// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSimpleSignerInfoGeneratorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cms.*;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaSimpleSignerInfoGeneratorBuilder
{
    private class ProviderHelper extends Helper
    {

        ContentSigner createContentSigner(String algorithm, PrivateKey privateKey)
            throws OperatorCreationException
        {
            return (new JcaContentSignerBuilder(algorithm)).setProvider(provider).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider()
            throws OperatorCreationException
        {
            return (new JcaDigestCalculatorProviderBuilder()).setProvider(provider).build();
        }

        private final Provider provider;
        final JcaSimpleSignerInfoGeneratorBuilder this$0;

        public ProviderHelper(Provider provider)
        {
            this$0 = JcaSimpleSignerInfoGeneratorBuilder.this;
            super();
            this.provider = provider;
        }
    }

    private class NamedHelper extends Helper
    {

        ContentSigner createContentSigner(String algorithm, PrivateKey privateKey)
            throws OperatorCreationException
        {
            return (new JcaContentSignerBuilder(algorithm)).setProvider(providerName).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider()
            throws OperatorCreationException
        {
            return (new JcaDigestCalculatorProviderBuilder()).setProvider(providerName).build();
        }

        private final String providerName;
        final JcaSimpleSignerInfoGeneratorBuilder this$0;

        public NamedHelper(String providerName)
        {
            this$0 = JcaSimpleSignerInfoGeneratorBuilder.this;
            super();
            this.providerName = providerName;
        }
    }

    private class Helper
    {

        ContentSigner createContentSigner(String algorithm, PrivateKey privateKey)
            throws OperatorCreationException
        {
            return (new JcaContentSignerBuilder(algorithm)).build(privateKey);
        }

        DigestCalculatorProvider createDigestCalculatorProvider()
            throws OperatorCreationException
        {
            return (new JcaDigestCalculatorProviderBuilder()).build();
        }

        final JcaSimpleSignerInfoGeneratorBuilder this$0;

        private Helper()
        {
            this$0 = JcaSimpleSignerInfoGeneratorBuilder.this;
            super();
        }

    }


    public JcaSimpleSignerInfoGeneratorBuilder()
        throws OperatorCreationException
    {
        helper = new Helper();
    }

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(String providerName)
        throws OperatorCreationException
    {
        helper = new NamedHelper(providerName);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(Provider provider)
        throws OperatorCreationException
    {
        helper = new ProviderHelper(provider);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setDirectSignature(boolean hasNoSignedAttributes)
    {
        this.hasNoSignedAttributes = hasNoSignedAttributes;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator signedGen)
    {
        this.signedGen = signedGen;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(AttributeTable attrTable)
    {
        signedGen = new DefaultSignedAttributeTableGenerator(attrTable);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator unsignedGen)
    {
        this.unsignedGen = unsignedGen;
        return this;
    }

    public SignerInfoGenerator build(String algorithmName, PrivateKey privateKey, X509Certificate certificate)
        throws OperatorCreationException, CertificateEncodingException
    {
        ContentSigner contentSigner = helper.createContentSigner(algorithmName, privateKey);
        return configureAndBuild().build(contentSigner, new JcaX509CertificateHolder(certificate));
    }

    public SignerInfoGenerator build(String algorithmName, PrivateKey privateKey, byte keyIdentifier[])
        throws OperatorCreationException, CertificateEncodingException
    {
        ContentSigner contentSigner = helper.createContentSigner(algorithmName, privateKey);
        return configureAndBuild().build(contentSigner, keyIdentifier);
    }

    private SignerInfoGeneratorBuilder configureAndBuild()
        throws OperatorCreationException
    {
        SignerInfoGeneratorBuilder infoGeneratorBuilder = new SignerInfoGeneratorBuilder(helper.createDigestCalculatorProvider());
        infoGeneratorBuilder.setDirectSignature(hasNoSignedAttributes);
        infoGeneratorBuilder.setSignedAttributeGenerator(signedGen);
        infoGeneratorBuilder.setUnsignedAttributeGenerator(unsignedGen);
        return infoGeneratorBuilder;
    }

    private Helper helper;
    private boolean hasNoSignedAttributes;
    private CMSAttributeTableGenerator signedGen;
    private CMSAttributeTableGenerator unsignedGen;
}
