// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NoekeonEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;

public class NoekeonEngine
    implements BlockCipher
{

    public NoekeonEngine()
    {
        state = new int[4];
        subKeys = new int[4];
        decryptKeys = new int[4];
        _initialised = false;
    }

    public String getAlgorithmName()
    {
        return "Noekeon";
    }

    public int getBlockSize()
    {
        return 16;
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        if(!(params instanceof KeyParameter))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid parameter passed to Noekeon init - ").append(params.getClass().getName()).toString());
        } else
        {
            _forEncryption = forEncryption;
            _initialised = true;
            KeyParameter p = (KeyParameter)params;
            setKey(p.getKey());
            return;
        }
    }

    public int processBlock(byte in[], int inOff, byte out[], int outOff)
    {
        if(!_initialised)
            throw new IllegalStateException((new StringBuilder()).append(getAlgorithmName()).append(" not initialised").toString());
        if(inOff + 16 > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + 16 > out.length)
            throw new OutputLengthException("output buffer too short");
        else
            return _forEncryption ? encryptBlock(in, inOff, out, outOff) : decryptBlock(in, inOff, out, outOff);
    }

    public void reset()
    {
    }

    private void setKey(byte key[])
    {
        subKeys[0] = bytesToIntBig(key, 0);
        subKeys[1] = bytesToIntBig(key, 4);
        subKeys[2] = bytesToIntBig(key, 8);
        subKeys[3] = bytesToIntBig(key, 12);
    }

    private int encryptBlock(byte in[], int inOff, byte out[], int outOff)
    {
        state[0] = bytesToIntBig(in, inOff);
        state[1] = bytesToIntBig(in, inOff + 4);
        state[2] = bytesToIntBig(in, inOff + 8);
        state[3] = bytesToIntBig(in, inOff + 12);
        int i;
        for(i = 0; i < 16; i++)
        {
            state[0] ^= roundConstants[i];
            theta(state, subKeys);
            pi1(state);
            gamma(state);
            pi2(state);
        }

        state[0] ^= roundConstants[i];
        theta(state, subKeys);
        intToBytesBig(state[0], out, outOff);
        intToBytesBig(state[1], out, outOff + 4);
        intToBytesBig(state[2], out, outOff + 8);
        intToBytesBig(state[3], out, outOff + 12);
        return 16;
    }

    private int decryptBlock(byte in[], int inOff, byte out[], int outOff)
    {
        state[0] = bytesToIntBig(in, inOff);
        state[1] = bytesToIntBig(in, inOff + 4);
        state[2] = bytesToIntBig(in, inOff + 8);
        state[3] = bytesToIntBig(in, inOff + 12);
        System.arraycopy(subKeys, 0, decryptKeys, 0, subKeys.length);
        theta(decryptKeys, nullVector);
        int i;
        for(i = 16; i > 0; i--)
        {
            theta(state, decryptKeys);
            state[0] ^= roundConstants[i];
            pi1(state);
            gamma(state);
            pi2(state);
        }

        theta(state, decryptKeys);
        state[0] ^= roundConstants[i];
        intToBytesBig(state[0], out, outOff);
        intToBytesBig(state[1], out, outOff + 4);
        intToBytesBig(state[2], out, outOff + 8);
        intToBytesBig(state[3], out, outOff + 12);
        return 16;
    }

    private void gamma(int a[])
    {
        a[1] ^= ~a[3] & ~a[2];
        a[0] ^= a[2] & a[1];
        int tmp = a[3];
        a[3] = a[0];
        a[0] = tmp;
        a[2] ^= a[0] ^ a[1] ^ a[3];
        a[1] ^= ~a[3] & ~a[2];
        a[0] ^= a[2] & a[1];
    }

    private void theta(int a[], int k[])
    {
        int tmp = a[0] ^ a[2];
        tmp ^= rotl(tmp, 8) ^ rotl(tmp, 24);
        a[1] ^= tmp;
        a[3] ^= tmp;
        for(int i = 0; i < 4; i++)
            a[i] ^= k[i];

        tmp = a[1] ^ a[3];
        tmp ^= rotl(tmp, 8) ^ rotl(tmp, 24);
        a[0] ^= tmp;
        a[2] ^= tmp;
    }

    private void pi1(int a[])
    {
        a[1] = rotl(a[1], 1);
        a[2] = rotl(a[2], 5);
        a[3] = rotl(a[3], 2);
    }

    private void pi2(int a[])
    {
        a[1] = rotl(a[1], 31);
        a[2] = rotl(a[2], 27);
        a[3] = rotl(a[3], 30);
    }

    private int bytesToIntBig(byte in[], int off)
    {
        return in[off++] << 24 | (in[off++] & 0xff) << 16 | (in[off++] & 0xff) << 8 | in[off] & 0xff;
    }

    private void intToBytesBig(int x, byte out[], int off)
    {
        out[off++] = (byte)(x >>> 24);
        out[off++] = (byte)(x >>> 16);
        out[off++] = (byte)(x >>> 8);
        out[off] = (byte)x;
    }

    private int rotl(int x, int y)
    {
        return x << y | x >>> 32 - y;
    }

    private static final int genericSize = 16;
    private static final int nullVector[] = {
        0, 0, 0, 0
    };
    private static final int roundConstants[] = {
        128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 
        188, 99, 198, 151, 53, 106, 212
    };
    private int state[];
    private int subKeys[];
    private int decryptKeys[];
    private boolean _initialised;
    private boolean _forEncryption;

}
