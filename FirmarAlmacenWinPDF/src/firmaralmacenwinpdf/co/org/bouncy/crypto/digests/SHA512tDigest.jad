// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512tDigest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.util.Memoable;
import co.org.bouncy.util.MemoableResetException;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            LongDigest

public class SHA512tDigest extends LongDigest
{

    public SHA512tDigest(int bitLength)
    {
        if(bitLength >= 512)
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        if(bitLength % 8 != 0)
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        if(bitLength == 384)
        {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        } else
        {
            digestLength = bitLength / 8;
            tIvGenerate(digestLength * 8);
            reset();
            return;
        }
    }

    public SHA512tDigest(SHA512tDigest t)
    {
        super(t);
        digestLength = t.digestLength;
        reset(t);
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append("SHA-512/").append(Integer.toString(digestLength * 8)).toString();
    }

    public int getDigestSize()
    {
        return digestLength;
    }

    public int doFinal(byte out[], int outOff)
    {
        finish();
        longToBigEndian(H1, out, outOff, digestLength);
        longToBigEndian(H2, out, outOff + 8, digestLength - 8);
        longToBigEndian(H3, out, outOff + 16, digestLength - 16);
        longToBigEndian(H4, out, outOff + 24, digestLength - 24);
        longToBigEndian(H5, out, outOff + 32, digestLength - 32);
        longToBigEndian(H6, out, outOff + 40, digestLength - 40);
        longToBigEndian(H7, out, outOff + 48, digestLength - 48);
        longToBigEndian(H8, out, outOff + 56, digestLength - 56);
        reset();
        return digestLength;
    }

    public void reset()
    {
        super.reset();
        H1 = H1t;
        H2 = H2t;
        H3 = H3t;
        H4 = H4t;
        H5 = H5t;
        H6 = H6t;
        H7 = H7t;
        H8 = H8t;
    }

    private void tIvGenerate(int bitLength)
    {
        H1 = 0xcfac43c256196cadL;
        H2 = 0x1ec20b20216f029eL;
        H3 = 0x99cb56d75b315d8eL;
        H4 = 0xea509ffab89354L;
        H5 = 0xf4abf7da08432774L;
        H6 = 0x3ea0cd298e9bc9baL;
        H7 = 0xba267c0e5ee418ceL;
        H8 = 0xfe4568bcb6db84dcL;
        update((byte)83);
        update((byte)72);
        update((byte)65);
        update((byte)45);
        update((byte)53);
        update((byte)49);
        update((byte)50);
        update((byte)47);
        if(bitLength > 100)
        {
            update((byte)(bitLength / 100 + 48));
            bitLength %= 100;
            update((byte)(bitLength / 10 + 48));
            bitLength %= 10;
            update((byte)(bitLength + 48));
        } else
        if(bitLength > 10)
        {
            update((byte)(bitLength / 10 + 48));
            bitLength %= 10;
            update((byte)(bitLength + 48));
        } else
        {
            update((byte)(bitLength + 48));
        }
        finish();
        H1t = H1;
        H2t = H2;
        H3t = H3;
        H4t = H4;
        H5t = H5;
        H6t = H6;
        H7t = H7;
        H8t = H8;
    }

    private static void longToBigEndian(long n, byte bs[], int off, int max)
    {
        if(max > 0)
        {
            intToBigEndian((int)(n >>> 32), bs, off, max);
            if(max > 4)
                intToBigEndian((int)(n & 0xffffffffL), bs, off + 4, max - 4);
        }
    }

    private static void intToBigEndian(int n, byte bs[], int off, int max)
    {
        for(int num = Math.min(4, max); --num >= 0;)
        {
            int shift = 8 * (3 - num);
            bs[off + num] = (byte)(n >>> shift);
        }

    }

    public Memoable copy()
    {
        return new SHA512tDigest(this);
    }

    public void reset(Memoable other)
    {
        SHA512tDigest t = (SHA512tDigest)other;
        if(digestLength != t.digestLength)
        {
            throw new MemoableResetException("digestLength inappropriate in other");
        } else
        {
            super.copyIn(t);
            H1t = t.H1t;
            H2t = t.H2t;
            H3t = t.H3t;
            H4t = t.H4t;
            H5t = t.H5t;
            H6t = t.H6t;
            H7t = t.H7t;
            H8t = t.H8t;
            return;
        }
    }

    private final int digestLength;
    private long H1t;
    private long H2t;
    private long H3t;
    private long H4t;
    private long H5t;
    private long H6t;
    private long H7t;
    private long H8t;
}
