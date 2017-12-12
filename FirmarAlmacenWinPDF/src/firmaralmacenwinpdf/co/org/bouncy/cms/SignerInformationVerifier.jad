// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInformationVerifier.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSignatureAlgorithmNameGenerator

public class SignerInformationVerifier
{

    public SignerInformationVerifier(CMSSignatureAlgorithmNameGenerator sigNameGenerator, SignatureAlgorithmIdentifierFinder sigAlgorithmFinder, ContentVerifierProvider verifierProvider, DigestCalculatorProvider digestProvider)
    {
        this.sigNameGenerator = sigNameGenerator;
        this.sigAlgorithmFinder = sigAlgorithmFinder;
        this.verifierProvider = verifierProvider;
        this.digestProvider = digestProvider;
    }

    public boolean hasAssociatedCertificate()
    {
        return verifierProvider.hasAssociatedCertificate();
    }

    public X509CertificateHolder getAssociatedCertificate()
    {
        return verifierProvider.getAssociatedCertificate();
    }

    public ContentVerifier getContentVerifier(AlgorithmIdentifier signingAlgorithm, AlgorithmIdentifier digestAlgorithm)
        throws OperatorCreationException
    {
        String signatureName = sigNameGenerator.getSignatureName(digestAlgorithm, signingAlgorithm);
        return verifierProvider.get(sigAlgorithmFinder.find(signatureName));
    }

    public DigestCalculator getDigestCalculator(AlgorithmIdentifier algorithmIdentifier)
        throws OperatorCreationException
    {
        return digestProvider.get(algorithmIdentifier);
    }

    private ContentVerifierProvider verifierProvider;
    private DigestCalculatorProvider digestProvider;
    private SignatureAlgorithmIdentifierFinder sigAlgorithmFinder;
    private CMSSignatureAlgorithmNameGenerator sigNameGenerator;
}
