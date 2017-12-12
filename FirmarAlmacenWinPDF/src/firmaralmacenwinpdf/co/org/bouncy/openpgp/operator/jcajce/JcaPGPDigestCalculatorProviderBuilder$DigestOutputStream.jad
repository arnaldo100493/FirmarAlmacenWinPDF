// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPDigestCalculatorProviderBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcaPGPDigestCalculatorProviderBuilder

private class JcaPGPDigestCalculatorProviderBuilder$DigestOutputStream extends OutputStream
{

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        dig.update(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        dig.update(bytes);
    }

    public void write(int b)
        throws IOException
    {
        dig.update((byte)b);
    }

    byte[] getDigest()
    {
        return dig.digest();
    }

    private MessageDigest dig;
    final JcaPGPDigestCalculatorProviderBuilder this$0;

    JcaPGPDigestCalculatorProviderBuilder$DigestOutputStream(MessageDigest dig)
    {
        this$0 = JcaPGPDigestCalculatorProviderBuilder.this;
        super();
        this.dig = dig;
    }
}
