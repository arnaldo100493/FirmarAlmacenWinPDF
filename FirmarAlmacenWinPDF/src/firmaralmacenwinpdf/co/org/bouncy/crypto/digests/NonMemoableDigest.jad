// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NonMemoableDigest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.ExtendedDigest;

public class NonMemoableDigest
    implements ExtendedDigest
{

    public NonMemoableDigest(ExtendedDigest baseDigest)
    {
        if(baseDigest == null)
        {
            throw new IllegalArgumentException("baseDigest must not be null");
        } else
        {
            this.baseDigest = baseDigest;
            return;
        }
    }

    public String getAlgorithmName()
    {
        return baseDigest.getAlgorithmName();
    }

    public int getDigestSize()
    {
        return baseDigest.getDigestSize();
    }

    public void update(byte in)
    {
        baseDigest.update(in);
    }

    public void update(byte in[], int inOff, int len)
    {
        baseDigest.update(in, inOff, len);
    }

    public int doFinal(byte out[], int outOff)
    {
        return baseDigest.doFinal(out, outOff);
    }

    public void reset()
    {
        baseDigest.reset();
    }

    public int getByteLength()
    {
        return baseDigest.getByteLength();
    }

    private ExtendedDigest baseDigest;
}
