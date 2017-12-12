// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestOutputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.Digest;
import java.io.IOException;
import java.io.OutputStream;

public class DigestOutputStream extends OutputStream
{

    public DigestOutputStream(Digest Digest)
    {
        digest = Digest;
    }

    public void write(int b)
        throws IOException
    {
        digest.update((byte)b);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        digest.update(b, off, len);
    }

    public byte[] getDigest()
    {
        byte res[] = new byte[digest.getDigestSize()];
        digest.doFinal(res, 0);
        return res;
    }

    protected Digest digest;
}
