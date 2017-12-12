// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SipHash.java

package co.org.bouncy.crypto.macs;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Arrays;

public class SipHash
    implements Mac
{

    public SipHash()
    {
        buf = new byte[8];
        bufPos = 0;
        wordCount = 0;
        c = 2;
        d = 4;
    }

    public SipHash(int c, int d)
    {
        buf = new byte[8];
        bufPos = 0;
        wordCount = 0;
        this.c = c;
        this.d = d;
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append("SipHash-").append(c).append("-").append(d).toString();
    }

    public int getMacSize()
    {
        return 8;
    }

    public void init(CipherParameters params)
        throws IllegalArgumentException
    {
        if(!(params instanceof KeyParameter))
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        KeyParameter keyParameter = (KeyParameter)params;
        byte key[] = keyParameter.getKey();
        if(key.length != 16)
        {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        } else
        {
            k0 = Pack.littleEndianToLong(key, 0);
            k1 = Pack.littleEndianToLong(key, 8);
            reset();
            return;
        }
    }

    public void update(byte input)
        throws IllegalStateException
    {
        buf[bufPos] = input;
        if(++bufPos == buf.length)
        {
            processMessageWord();
            bufPos = 0;
        }
    }

    public void update(byte input[], int offset, int length)
        throws DataLengthException, IllegalStateException
    {
        for(int i = 0; i < length; i++)
        {
            buf[bufPos] = input[offset + i];
            if(++bufPos == buf.length)
            {
                processMessageWord();
                bufPos = 0;
            }
        }

    }

    public long doFinal()
        throws DataLengthException, IllegalStateException
    {
        for(buf[7] = (byte)((wordCount << 3) + bufPos & 0xff); bufPos < 7; buf[bufPos++] = 0);
        processMessageWord();
        v2 ^= 255L;
        applySipRounds(d);
        long result = v0 ^ v1 ^ v2 ^ v3;
        reset();
        return result;
    }

    public int doFinal(byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        long result = doFinal();
        Pack.longToLittleEndian(result, out, outOff);
        return 8;
    }

    public void reset()
    {
        v0 = k0 ^ 0x736f6d6570736575L;
        v1 = k1 ^ 0x646f72616e646f6dL;
        v2 = k0 ^ 0x6c7967656e657261L;
        v3 = k1 ^ 0x7465646279746573L;
        Arrays.fill(buf, (byte)0);
        bufPos = 0;
        wordCount = 0;
    }

    protected void processMessageWord()
    {
        wordCount++;
        long m = Pack.littleEndianToLong(buf, 0);
        v3 ^= m;
        applySipRounds(c);
        v0 ^= m;
    }

    protected void applySipRounds(int n)
    {
        for(int r = 0; r < n; r++)
        {
            v0 += v1;
            v2 += v3;
            v1 = rotateLeft(v1, 13);
            v3 = rotateLeft(v3, 16);
            v1 ^= v0;
            v3 ^= v2;
            v0 = rotateLeft(v0, 32);
            v2 += v1;
            v0 += v3;
            v1 = rotateLeft(v1, 17);
            v3 = rotateLeft(v3, 21);
            v1 ^= v2;
            v3 ^= v0;
            v2 = rotateLeft(v2, 32);
        }

    }

    protected static long rotateLeft(long x, int n)
    {
        return x << n | x >>> 64 - n;
    }

    protected final int c;
    protected final int d;
    protected long k0;
    protected long k1;
    protected long v0;
    protected long v1;
    protected long v2;
    protected long v3;
    protected long v4;
    protected byte buf[];
    protected int bufPos;
    protected int wordCount;
}
