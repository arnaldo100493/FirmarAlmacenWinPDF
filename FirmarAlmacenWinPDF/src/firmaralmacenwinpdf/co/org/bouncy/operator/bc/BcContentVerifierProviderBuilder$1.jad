// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentVerifierProviderBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcContentVerifierProviderBuilder

class BcContentVerifierProviderBuilder$1
    implements ContentVerifierProvider
{

    public boolean hasAssociatedCertificate()
    {
        return true;
    }

    public X509CertificateHolder getAssociatedCertificate()
    {
        return val$certHolder;
    }

    public ContentVerifier get(AlgorithmIdentifier algorithm)
        throws OperatorCreationException
    {
        try
        {
            co.org.bouncy.crypto.params.AsymmetricKeyParameter publicKey = extractKeyParameters(val$certHolder.getSubjectPublicKeyInfo());
            BcSignerOutputStream stream = BcContentVerifierProviderBuilder.access$000(BcContentVerifierProviderBuilder.this, algorithm, publicKey);
            return new gVerifier(BcContentVerifierProviderBuilder.this, algorithm, stream);
        }
        catch(IOException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("exception on setup: ").append(e).toString(), e);
        }
    }

    final X509CertificateHolder val$certHolder;
    final BcContentVerifierProviderBuilder this$0;

    BcContentVerifierProviderBuilder$1()
    {
        this$0 = final_bccontentverifierproviderbuilder;
        val$certHolder = X509CertificateHolder.this;
        super();
    }
}
