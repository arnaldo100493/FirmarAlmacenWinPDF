// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureOutputStream.java

package co.org.bouncy.openpgp.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

class SignatureOutputStream extends OutputStream
{

    SignatureOutputStream(Signature sig)
    {
        this.sig = sig;
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        try
        {
            sig.update(bytes, off, len);
        }
        catch(SignatureException e)
        {
            throw new IOException((new StringBuilder()).append("signature update caused exception: ").append(e.getMessage()).toString());
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
            throw new IOException((new StringBuilder()).append("signature update caused exception: ").append(e.getMessage()).toString());
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
            throw new IOException((new StringBuilder()).append("signature update caused exception: ").append(e.getMessage()).toString());
        }
    }

    private Signature sig;
}
