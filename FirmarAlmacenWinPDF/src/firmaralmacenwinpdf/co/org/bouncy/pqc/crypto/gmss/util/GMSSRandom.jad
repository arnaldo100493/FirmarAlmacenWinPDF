// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSRandom.java

package co.org.bouncy.pqc.crypto.gmss.util;

import co.org.bouncy.crypto.Digest;

public class GMSSRandom
{

    public GMSSRandom(Digest messDigestTree2)
    {
        messDigestTree = messDigestTree2;
    }

    public byte[] nextSeed(byte outseed[])
    {
        byte rand[] = new byte[outseed.length];
        messDigestTree.update(outseed, 0, outseed.length);
        rand = new byte[messDigestTree.getDigestSize()];
        messDigestTree.doFinal(rand, 0);
        addByteArrays(outseed, rand);
        addOne(outseed);
        return rand;
    }

    private void addByteArrays(byte a[], byte b[])
    {
        byte overflow = 0;
        for(int i = 0; i < a.length; i++)
        {
            int temp = (0xff & a[i]) + (0xff & b[i]) + overflow;
            a[i] = (byte)temp;
            overflow = (byte)(temp >> 8);
        }

    }

    private void addOne(byte a[])
    {
        byte overflow = 1;
        for(int i = 0; i < a.length; i++)
        {
            int temp = (0xff & a[i]) + overflow;
            a[i] = (byte)temp;
            overflow = (byte)(temp >> 8);
        }

    }

    private Digest messDigestTree;
}
