// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentVerifierProviderBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.io.OutputStream;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            JcaContentVerifierProviderBuilder

private class JcaContentVerifierProviderBuilder$SigVerifier
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
        try
        {
            return stream.verify(expected);
        }
        catch(SignatureException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private putStream stream;
    private AlgorithmIdentifier algorithm;
    final JcaContentVerifierProviderBuilder this$0;

    JcaContentVerifierProviderBuilder$SigVerifier(AlgorithmIdentifier algorithm, putStream stream)
    {
        this$0 = JcaContentVerifierProviderBuilder.this;
        super();
        this.algorithm = algorithm;
        this.stream = stream;
    }
}
