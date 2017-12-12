// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentVerifierProviderBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.RawContentVerifier;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.security.Signature;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            JcaContentVerifierProviderBuilder

private class JcaContentVerifierProviderBuilder$RawSigVerifier extends JcaContentVerifierProviderBuilder.SigVerifier
    implements RawContentVerifier
{

    public boolean verify(byte digest[], byte expected[])
    {
        try
        {
            rawSignature.update(digest);
            return rawSignature.verify(expected);
        }
        catch(SignatureException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining raw signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private Signature rawSignature;
    final JcaContentVerifierProviderBuilder this$0;

    JcaContentVerifierProviderBuilder$RawSigVerifier(AlgorithmIdentifier algorithm, Stream stream, Signature rawSignature)
    {
        this$0 = JcaContentVerifierProviderBuilder.this;
        super(JcaContentVerifierProviderBuilder.this, algorithm, stream);
        this.rawSignature = rawSignature;
    }
}
