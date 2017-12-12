// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerOutputStream.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Signer;
import java.io.IOException;
import java.io.OutputStream;

class SignerOutputStream extends OutputStream
{

    SignerOutputStream(Signer sig)
    {
        this.sig = sig;
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        sig.update(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        sig.update(bytes, 0, bytes.length);
    }

    public void write(int b)
        throws IOException
    {
        sig.update((byte)b);
    }

    private Signer sig;
}
