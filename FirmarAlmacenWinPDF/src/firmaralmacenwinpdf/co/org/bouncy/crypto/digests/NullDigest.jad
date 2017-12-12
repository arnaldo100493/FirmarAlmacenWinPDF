// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NullDigest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.Digest;
import java.io.ByteArrayOutputStream;

public class NullDigest
    implements Digest
{

    public NullDigest()
    {
        bOut = new ByteArrayOutputStream();
    }

    public String getAlgorithmName()
    {
        return "NULL";
    }

    public int getDigestSize()
    {
        return bOut.size();
    }

    public void update(byte in)
    {
        bOut.write(in);
    }

    public void update(byte in[], int inOff, int len)
    {
        bOut.write(in, inOff, len);
    }

    public int doFinal(byte out[], int outOff)
    {
        byte res[] = bOut.toByteArray();
        System.arraycopy(res, 0, out, outOff, res.length);
        reset();
        return res.length;
    }

    public void reset()
    {
        bOut.reset();
    }

    private ByteArrayOutputStream bOut;
}
