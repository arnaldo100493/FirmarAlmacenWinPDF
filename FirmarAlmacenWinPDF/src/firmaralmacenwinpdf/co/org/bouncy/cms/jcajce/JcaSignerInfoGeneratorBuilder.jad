// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSignerInfoGeneratorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cms.*;
import co.org.bouncy.operator.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaSignerInfoGeneratorBuilder
{

    public JcaSignerInfoGeneratorBuilder(DigestCalculatorProvider digestProvider)
    {
        builder = new SignerInfoGeneratorBuilder(digestProvider);
    }

    public JcaSignerInfoGeneratorBuilder setDirectSignature(boolean hasNoSignedAttributes)
    {
        builder.setDirectSignature(hasNoSignedAttributes);
        return this;
    }

    public JcaSignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator signedGen)
    {
        builder.setSignedAttributeGenerator(signedGen);
        return this;
    }

    public JcaSignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator unsignedGen)
    {
        builder.setUnsignedAttributeGenerator(unsignedGen);
        return this;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509CertificateHolder certHolder)
        throws OperatorCreationException
    {
        return builder.build(contentSigner, certHolder);
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, byte keyIdentifier[])
        throws OperatorCreationException
    {
        return builder.build(contentSigner, keyIdentifier);
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509Certificate certificate)
        throws OperatorCreationException, CertificateEncodingException
    {
        return build(contentSigner, ((X509CertificateHolder) (new JcaX509CertificateHolder(certificate))));
    }

    private SignerInfoGeneratorBuilder builder;
}
