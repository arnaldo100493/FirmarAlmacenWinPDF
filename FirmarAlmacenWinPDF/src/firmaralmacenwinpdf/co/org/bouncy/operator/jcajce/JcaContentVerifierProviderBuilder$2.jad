// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentVerifierProviderBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import java.security.PublicKey;
import java.security.Signature;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            JcaContentVerifierProviderBuilder

class JcaContentVerifierProviderBuilder$2
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
        gnatureOutputStream stream = JcaContentVerifierProviderBuilder.access$200(JcaContentVerifierProviderBuilder.this, algorithm, val$publicKey);
        Signature rawSig = JcaContentVerifierProviderBuilder.access$100(JcaContentVerifierProviderBuilder.this, algorithm, val$publicKey);
        if(rawSig != null)
            return new wSigVerifier(JcaContentVerifierProviderBuilder.this, algorithm, stream, rawSig);
        else
            return new gVerifier(JcaContentVerifierProviderBuilder.this, algorithm, stream);
    }

    final PublicKey val$publicKey;
    final JcaContentVerifierProviderBuilder this$0;

    JcaContentVerifierProviderBuilder$2()
    {
        this$0 = final_jcacontentverifierproviderbuilder;
        val$publicKey = PublicKey.this;
        super();
    }
}
