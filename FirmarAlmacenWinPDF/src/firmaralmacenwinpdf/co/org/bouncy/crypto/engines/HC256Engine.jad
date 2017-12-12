// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HC256Engine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class HC256Engine
    implements StreamCipher
{

    public HC256Engine()
    {
        p = new int[1024];
        q = new int[1024];
        cnt = 0;
        buf = new byte[4];
        idx = 0;
    }

    private int step()
    {
        int j = cnt & 0x3ff;
        int ret;
        if(cnt < 1024)
        {
            int x = p[j - 3 & 0x3ff];
            int y = p[j - 1023 & 0x3ff];
            p[j] += p[j - 10 & 0x3ff] + (rotateRight(x, 10) ^ rotateRight(y, 23)) + q[(x ^ y) & 0x3ff];
            x = p[j - 12 & 0x3ff];
            ret = q[x & 0xff] + q[(x >> 8 & 0xff) + 256] + q[(x >> 16 & 0xff) + 512] + q[(x >> 24 & 0xff) + 768] ^ p[j];
        } else
        {
            int x = q[j - 3 & 0x3ff];
            int y = q[j - 1023 & 0x3ff];
            q[j] += q[j - 10 & 0x3ff] + (rotateRight(x, 10) ^ rotateRight(y, 23)) + p[(x ^ y) & 0x3ff];
            x = q[j - 12 & 0x3ff];
            ret = p[x & 0xff] + p[(x >> 8 & 0xff) + 256] + p[(x >> 16 & 0xff) + 512] + p[(x >> 24 & 0xff) + 768] ^ q[j];
        }
        cnt = cnt + 1 & 0x7ff;
        return ret;
    }

    private void init()
    {
        if(key.length != 32 && key.length != 16)
            throw new IllegalArgumentException("The key must be 128/256 bits long");
        if(iv.length < 16)
            throw new IllegalArgumentException("The IV must be at least 128 bits long");
        if(key.length != 32)
        {
            byte k[] = new byte[32];
            System.arraycopy(key, 0, k, 0, key.length);
            System.arraycopy(key, 0, k, 16, key.length);
            key = k;
        }
        if(iv.length < 32)
        {
            byte newIV[] = new byte[32];
            System.arraycopy(iv, 0, newIV, 0, iv.length);
            System.arraycopy(iv, 0, newIV, iv.length, newIV.length - iv.length);
            iv = newIV;
        }
        cnt = 0;
        int w[] = new int[2560];
        for(int i = 0; i < 32; i++)
            w[i >> 2] |= (key[i] & 0xff) << 8 * (i & 3);

        for(int i = 0; i < 32; i++)
            w[(i >> 2) + 8] |= (iv[i] & 0xff) << 8 * (i & 3);

        for(int i = 16; i < 2560; i++)
        {
            int x = w[i - 2];
            int y = w[i - 15];
            w[i] = (rotateRight(x, 17) ^ rotateRight(x, 19) ^ x >>> 10) + w[i - 7] + (rotateRight(y, 7) ^ rotateRight(y, 18) ^ y >>> 3) + w[i - 16] + i;
        }

        System.arraycopy(w, 512, p, 0, 1024);
        System.arraycopy(w, 1536, q, 0, 1024);
        for(int i = 0; i < 4096; i++)
            step();

        cnt = 0;
    }

    public String getAlgorithmName()
    {
        return "HC-256";
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
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid parameter passed to HC256 init - ").append(params.getClass().getName()).toString());
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

    private static int rotateRight(int x, int bits)
    {
        return x >>> bits | x << -bits;
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
