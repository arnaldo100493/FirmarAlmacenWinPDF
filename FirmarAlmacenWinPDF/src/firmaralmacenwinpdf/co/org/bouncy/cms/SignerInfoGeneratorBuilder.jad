// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInfoGeneratorBuilder.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.asn1.cms.SignerIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;

// Referenced classes of package co.org.bouncy.cms:
//            DefaultCMSSignatureEncryptionAlgorithmFinder, SignerInfoGenerator, DefaultSignedAttributeTableGenerator, CMSAttributeTableGenerator, 
//            CMSSignatureEncryptionAlgorithmFinder

public class SignerInfoGeneratorBuilder
{

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestProvider)
    {
        this(digestProvider, ((CMSSignatureEncryptionAlgorithmFinder) (new DefaultCMSSignatureEncryptionAlgorithmFinder())));
    }

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestProvider, CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder)
    {
        this.digestProvider = digestProvider;
        this.sigEncAlgFinder = sigEncAlgFinder;
    }

    public SignerInfoGeneratorBuilder setDirectSignature(boolean hasNoSignedAttributes)
    {
        directSignature = hasNoSignedAttributes;
        return this;
    }

    public SignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator signedGen)
    {
        this.signedGen = signedGen;
        return this;
    }

    public SignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator unsignedGen)
    {
        this.unsignedGen = unsignedGen;
        return this;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509CertificateHolder certHolder)
        throws OperatorCreationException
    {
        SignerIdentifier sigId = new SignerIdentifier(new IssuerAndSerialNumber(certHolder.toASN1Structure()));
        SignerInfoGenerator sigInfoGen = createGenerator(contentSigner, sigId);
        sigInfoGen.setAssociatedCertificate(certHolder);
        return sigInfoGen;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, byte subjectKeyIdentifier[])
        throws OperatorCreationException
    {
        SignerIdentifier sigId = new SignerIdentifier(new DEROctetString(subjectKeyIdentifier));
        return createGenerator(contentSigner, sigId);
    }

    private SignerInfoGenerator createGenerator(ContentSigner contentSigner, SignerIdentifier sigId)
        throws OperatorCreationException
    {
        if(directSignature)
            return new SignerInfoGenerator(sigId, contentSigner, digestProvider, sigEncAlgFinder, true);
        if(signedGen != null || unsignedGen != null)
        {
            if(signedGen == null)
                signedGen = new DefaultSignedAttributeTableGenerator();
            return new SignerInfoGenerator(sigId, contentSigner, digestProvider, sigEncAlgFinder, signedGen, unsignedGen);
        } else
        {
            return new SignerInfoGenerator(sigId, contentSigner, digestProvider, sigEncAlgFinder);
        }
    }

    private DigestCalculatorProvider digestProvider;
    private boolean directSignature;
    private CMSAttributeTableGenerator signedGen;
    private CMSAttributeTableGenerator unsignedGen;
    private CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder;
}
