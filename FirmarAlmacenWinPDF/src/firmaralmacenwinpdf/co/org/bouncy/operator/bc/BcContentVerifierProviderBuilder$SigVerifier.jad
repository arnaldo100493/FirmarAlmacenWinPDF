// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentVerifierProviderBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.ContentVerifier;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcSignerOutputStream, BcContentVerifierProviderBuilder

private class BcContentVerifierProviderBuilder$SigVerifier
    implements ContentVerifier
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return algorithm;
    }

    public OutputStream getOutputStream()
    {
        if(stream == null)
            throw new IllegalStateException("verifier not initialised");
        else
            return stream;
    }

    public boolean verify(byte expected[])
    {
        return stream.verify(expected);
    }

    private BcSignerOutputStream stream;
    private AlgorithmIdentifier algorithm;
    final BcContentVerifierProviderBuilder this$0;

    BcContentVerifierProviderBuilder$SigVerifier(AlgorithmIdentifier algorithm, BcSignerOutputStream stream)
    {
        this$0 = BcContentVerifierProviderBuilder.this;
        super();
        this.algorithm = algorithm;
        this.stream = stream;
    }
}
