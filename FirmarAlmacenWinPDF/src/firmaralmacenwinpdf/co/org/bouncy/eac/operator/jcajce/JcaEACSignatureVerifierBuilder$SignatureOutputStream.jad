// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEACSignatureVerifierBuilder.java

package co.org.bouncy.eac.operator.jcajce;

import co.org.bouncy.operator.OperatorStreamException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            JcaEACSignatureVerifierBuilder

private class JcaEACSignatureVerifierBuilder$SignatureOutputStream extends OutputStream
{

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        try
        {
            sig.update(bytes, off, len);
        }
        catch(SignatureException e)
        {
            throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
        }
    }

    public void write(byte bytes[])
        throws IOException
    {
        try
        {
            sig.update(bytes);
        }
        catch(SignatureException e)
        {
            throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
        }
    }

    public void write(int b)
        throws IOException
    {
        try
        {
            sig.update((byte)b);
        }
        catch(SignatureException e)
        {
            throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
        }
    }

    boolean verify(byte expected[])
        throws SignatureException
    {
        return sig.verify(expected);
    }

    private Signature sig;
    final JcaEACSignatureVerifierBuilder this$0;

    JcaEACSignatureVerifierBuilder$SignatureOutputStream(Signature sig)
    {
        this$0 = JcaEACSignatureVerifierBuilder.this;
        super();
        this.sig = sig;
    }
}
