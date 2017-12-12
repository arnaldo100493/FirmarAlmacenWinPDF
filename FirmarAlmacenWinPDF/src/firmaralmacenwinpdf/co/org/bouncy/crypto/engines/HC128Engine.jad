// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HC128Engine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class HC128Engine
    implements StreamCipher
{

    public HC128Engine()
    {
        p = new int[512];
        q = new int[512];
        cnt = 0;
        buf = new byte[4];
        idx = 0;
    }

    private static int f1(int x)
    {
        return rotateRight(x, 7) ^ rotateRight(x, 18) ^ x >>> 3;
    }

    private static int f2(int x)
    {
        return rotateRight(x, 17) ^ rotateRight(x, 19) ^ x >>> 10;
    }

    private int g1(int x, int y, int z)
    {
        return (rotateRight(x, 10) ^ rotateRight(z, 23)) + rotateRight(y, 8);
    }

    private int g2(int x, int y, int z)
    {
        return (rotateLeft(x, 10) ^ rotateLeft(z, 23)) + rotateLeft(y, 8);
    }

    private static int rotateLeft(int x, int bits)
    {
        return x << bits | x >>> -bits;
    }

    private static int rotateRight(int x, int bits)
    {
        return x >>> bits | x << -bits;
    }

    private int h1(int x)
    {
        return q[x & 0xff] + q[(x >> 16 & 0xff) + 256];
    }

    private int h2(int x)
    {
        return p[x & 0xff] + p[(x >> 16 & 0xff) + 256];
    }

    private static int mod1024(int x)
    {
        return x & 0x3ff;
    }

    private static int mod512(int x)
    {
        return x & 0x1ff;
    }

    private static int dim(int x, int y)
    {
        return mod512(x - y);
    }

    private int step()
    {
        int j = mod512(cnt);
        int ret;
        if(cnt < 512)
        {
            p[j] += g1(p[dim(j, 3)], p[dim(j, 10)], p[dim(j, 511)]);
            ret = h1(p[dim(j, 12)]) ^ p[j];
        } else
        {
            q[j] += g2(q[dim(j, 3)], q[dim(j, 10)], q[dim(j, 511)]);
            ret = h2(q[dim(j, 12)]) ^ q[j];
        }
        cnt = mod1024(cnt + 1);
        return ret;
    }

    private void init()
    {
        if(key.length != 16)
            throw new IllegalArgumentException("The key must be 128 bits long");
        cnt = 0;
        int w[] = new int[1280];
        for(int i = 0; i < 16; i++)
            w[i >> 2] |= (key[i] & 0xff) << 8 * (i & 3);

        System.arraycopy(w, 0, w, 4, 4);
        for(int i = 0; i < iv.length && i < 16; i++)
            w[(i >> 2) + 8] |= (iv[i] & 0xff) << 8 * (i & 3);

        System.arraycopy(w, 8, w, 12, 4);
        for(int i = 16; i < 1280; i++)
            w[i] = f2(w[i - 2]) + w[i - 7] + f1(w[i - 15]) + w[i - 16] + i;

        System.arraycopy(w, 256, p, 0, 512);
        System.arraycopy(w, 768, q, 0, 512);
        for(int i = 0; i < 512; i++)
            p[i] = step();

        for(int i = 0; i < 512; i++)
            q[i] = step();

        cnt = 0;
    }

    public String getAlgorithmName()
    {
        return "HC-128";
    }

    public void init(boolean forEncryption, CipherParameters params)
        throws IllegalArgumentException
    {
        CipherParameters keyParam = params;
        if(params instanceof ParametersWithIV)
        {
            iv = ((ParametersWithIV)params).getIV();
            keyParam = ((ParametersWithIV)params).getParameters();
        } else
        {
            iv = new byte[0];
        }
        if(keyParam instanceof KeyParameter)
        {
            key = ((KeyParameter)keyParam).getKey();
            init();
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid parameter passed to HC128 init - ").append(params.getClass().getName()).toString());
        }
        initialised = true;
    }

    private byte getByte()
    {
        if(idx == 0)
        {
            int step = step();
            buf[0] = (byte)(step & 0xff);
            step >>= 8;
            buf[1] = (byte)(step & 0xff);
            step >>= 8;
            buf[2] = (byte)(step & 0xff);
            step >>= 8;
            buf[3] = (byte)(step & 0xff);
        }
        byte ret = buf[idx];
        idx = idx + 1 & 3;
        return ret;
    }

    public void processBytes(byte in[], int inOff, int len, byte out[], int outOff)
        throws DataLengthException
    {
        if(!initialised)
            throw new IllegalStateException((new StringBuilder()).append(getAlgorithmName()).append(" not initialised").toString());
        if(inOff + len > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + len > out.length)
            throw new OutputLengthException("output buffer too short");
        for(int i = 0; i < len; i++)
            out[outOff + i] = (byte)(in[inOff + i] ^ getByte());

    }

    public void reset()
    {
        idx = 0;
        init();
    }

    public byte returnByte(byte in)
    {
        return (byte)(in ^ getByte());
    }

    private int p[];
    private int q[];
    private int cnt;
    private byte key[];
    private byte iv[];
    private boolean initialised;
    private byte buf[];
    private int idx;
}
