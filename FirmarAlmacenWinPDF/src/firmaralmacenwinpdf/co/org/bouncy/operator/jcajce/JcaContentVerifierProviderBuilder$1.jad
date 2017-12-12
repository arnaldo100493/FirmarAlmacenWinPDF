// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentVerifierProviderBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            JcaContentVerifierProviderBuilder, OperatorHelper

class JcaContentVerifierProviderBuilder$1
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
            Signature sig = JcaContentVerifierProviderBuilder.access$000(JcaContentVerifierProviderBuilder.this).createSignature(algorithm);
            sig.initVerify(val$certificate.getPublicKey());
            stream = new gnatureOutputStream(JcaContentVerifierProviderBuilder.this, sig);
        }
        catch(GeneralSecurityException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("exception on setup: ").append(e).toString(), e);
        }
        Signature rawSig = JcaContentVerifierProviderBuilder.access$100(JcaContentVerifierProviderBuilder.this, algorithm, val$certificate.getPublicKey());
        if(rawSig != null)
            return new wSigVerifier(JcaContentVerifierProviderBuilder.this, algorithm, stream, rawSig);
        else
            return new gVerifier(JcaContentVerifierProviderBuilder.this, algorithm, stream);
    }

    private gnatureOutputStream stream;
    final X509CertificateHolder val$certHolder;
    final X509Certificate val$certificate;
    final JcaContentVerifierProviderBuilder this$0;

    JcaContentVerifierProviderBuilder$1()
    {
        this$0 = final_jcacontentverifierproviderbuilder;
        val$certHolder = x509certificateholder;
        val$certificate = X509Certificate.this;
        super();
    }
}
