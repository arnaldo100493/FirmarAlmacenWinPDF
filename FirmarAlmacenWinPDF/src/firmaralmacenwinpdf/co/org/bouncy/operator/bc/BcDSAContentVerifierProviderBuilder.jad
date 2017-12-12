// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcDSAContentVerifierProviderBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.signers.DSADigestSigner;
import co.org.bouncy.crypto.signers.DSASigner;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.operator.DigestAlgorithmIdentifierFinder;
import co.org.bouncy.operator.OperatorCreationException;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcContentVerifierProviderBuilder, BcDigestProvider

public class BcDSAContentVerifierProviderBuilder extends BcContentVerifierProviderBuilder
{

    public BcDSAContentVerifierProviderBuilder(DigestAlgorithmIdentifierFinder digestAlgorithmFinder)
    {
        this.digestAlgorithmFinder = digestAlgorithmFinder;
    }

    protected Signer createSigner(AlgorithmIdentifier sigAlgId)
        throws OperatorCreationException
    {
        AlgorithmIdentifier digAlg = digestAlgorithmFinder.find(sigAlgId);
        co.org.bouncy.crypto.Digest dig = digestProvider.get(digAlg);
        return new DSADigestSigner(new DSASigner(), dig);
    }

    protected AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo publicKeyInfo)
        throws IOException
    {
        return PublicKeyFactory.createKey(publicKeyInfo);
    }

    private DigestAlgorithmIdentifierFinder digestAlgorithmFinder;
}
