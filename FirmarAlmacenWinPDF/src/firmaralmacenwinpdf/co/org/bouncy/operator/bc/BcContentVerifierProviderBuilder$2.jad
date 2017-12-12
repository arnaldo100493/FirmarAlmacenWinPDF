// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentVerifierProviderBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.*;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcContentVerifierProviderBuilder

class BcContentVerifierProviderBuilder$2
    implements ContentVerifierProvider
{

    public boolean hasAssociatedCertificate()
    {
        return false;
    }

    public X509CertificateHolder getAssociatedCertificate()
    {
        return null;
    }

    public ContentVerifier get(AlgorithmIdentifier algorithm)
        throws OperatorCreationException
    {
        BcSignerOutputStream stream = BcContentVerifierProviderBuilder.access$000(BcContentVerifierProviderBuilder.this, algorithm, val$publicKey);
        return new gVerifier(BcContentVerifierProviderBuilder.this, algorithm, stream);
    }

    final AsymmetricKeyParameter val$publicKey;
    final BcContentVerifierProviderBuilder this$0;

    BcContentVerifierProviderBuilder$2()
    {
        this$0 = final_bccontentverifierproviderbuilder;
        val$publicKey = AsymmetricKeyParameter.this;
        super();
    }
}
