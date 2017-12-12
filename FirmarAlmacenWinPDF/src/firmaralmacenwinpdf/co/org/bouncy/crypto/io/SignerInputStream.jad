// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.Signer;
import java.io.*;

public class SignerInputStream extends FilterInputStream
{

    public SignerInputStream(InputStream stream, Signer signer)
    {
        super(stream);
        this.signer = signer;
    }

    public int read()
        throws IOException
    {
        int b = in.read();
        if(b >= 0)
            signer.update((byte)b);
        return b;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        int n = in.read(b, off, len);
        if(n > 0)
            signer.update(b, off, n);
        return n;
    }

    public Signer getSigner()
    {
        return signer;
    }

    protected Signer signer;
}
