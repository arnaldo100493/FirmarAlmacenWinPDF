// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralDigest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.util.Memoable;

public abstract class GeneralDigest
    implements ExtendedDigest, Memoable
{

    protected GeneralDigest()
    {
        xBuf = new byte[4];
        xBufOff = 0;
    }

    protected GeneralDigest(GeneralDigest t)
    {
        xBuf = new byte[t.xBuf.length];
        copyIn(t);
    }

    protected void copyIn(GeneralDigest t)
    {
        System.arraycopy(t.xBuf, 0, xBuf, 0, t.xBuf.length);
        xBufOff = t.xBufOff;
        byteCount = t.byteCount;
    }

    public void update(byte in)
    {
        xBuf[xBufOff++] = in;
        if(xBufOff == xBuf.length)
        {
            processWord(xBuf, 0);
            xBufOff = 0;
        }
        byteCount++;
    }

    public void update(byte in[], int inOff, int len)
    {
        for(; xBufOff != 0 && len > 0; len--)
        {
            update(in[inOff]);
            inOff++;
        }

        while(len > xBuf.length) 
        {
            processWord(in, inOff);
            inOff += xBuf.length;
            len -= xBuf.length;
            byteCount += xBuf.length;
        }
        for(; len > 0; len--)
        {
            update(in[inOff]);
            inOff++;
        }

    }

    public void finish()
    {
        long bitLength = byteCount << 3;
        update((byte)-128);
        while(xBufOff != 0) 
            update((byte)0);
        processLength(bitLength);
        processBlock();
    }

    public void reset()
    {
        byteCount = 0L;
        xBufOff = 0;
        for(int i = 0; i < xBuf.length; i++)
            xBuf[i] = 0;

    }

    public int getByteLength()
    {
        return 64;
    }

    protected abstract void processWord(byte abyte0[], int i);

    protected abstract void processLength(long l);

    protected abstract void processBlock();

    private static final int BYTE_LENGTH = 64;
    private byte xBuf[];
    private int xBufOff;
    private long byteCount;
}
