// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerOutputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.Signer;
import java.io.IOException;
import java.io.OutputStream;

public class SignerOutputStream extends OutputStream
{

    public SignerOutputStream(Signer Signer)
    {
        signer = Signer;
    }

    public void write(int b)
        throws IOException
    {
        signer.update((byte)b);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        signer.update(b, off, len);
    }

    public Signer getSigner()
    {
        return signer;
    }

    protected Signer signer;
}
