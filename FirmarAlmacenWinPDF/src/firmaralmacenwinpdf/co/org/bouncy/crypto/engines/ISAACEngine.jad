// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISAACEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.util.Pack;

public class ISAACEngine
    implements StreamCipher
{

    public ISAACEngine()
    {
        engineState = null;
        results = null;
        a = 0;
        b = 0;
        c = 0;
        index = 0;
        keyStream = new byte[1024];
        workingKey = null;
        initialised = false;
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        if(!(params instanceof KeyParameter))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid parameter passed to ISAAC init - ").append(params.getClass().getName()).toString());
        } else
        {
            KeyParameter p = (KeyParameter)params;
            setKey(p.getKey());
            return;
        }
    }

    public byte returnByte(byte in)
    {
        if(index == 0)
        {
            isaac();
            keyStream = Pack.intToBigEndian(results);
        }
        byte out = (byte)(keyStream[index] ^ in);
        index = index + 1 & 0x3ff;
        return out;
    }

    public void processBytes(byte in[], int inOff, int len, byte out[], int outOff)
    {
        if(!initialised)
            throw new IllegalStateException((new StringBuilder()).append(getAlgorithmName()).append(" not initialised").toString());
        if(inOff + len > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + len > out.length)
            throw new OutputLengthException("output buffer too short");
        for(int i = 0; i < len; i++)
        {
            if(index == 0)
            {
                isaac();
                keyStream = Pack.intToBigEndian(results);
            }
            out[i + outOff] = (byte)(keyStream[index] ^ in[i + inOff]);
            index = index + 1 & 0x3ff;
        }

    }

    public String getAlgorithmName()
    {
        return "ISAAC";
    }

    public void reset()
    {
        setKey(workingKey);
    }

    private void setKey(byte keyBytes[])
    {
        workingKey = keyBytes;
        if(engineState == null)
            engineState = new int[256];
        if(results == null)
            results = new int[256];
        for(int i = 0; i < 256; i++)
            engineState[i] = results[i] = 0;

        a = b = c = 0;
        index = 0;
        byte t[] = new byte[keyBytes.length + (keyBytes.length & 3)];
        System.arraycopy(keyBytes, 0, t, 0, keyBytes.length);
        for(int i = 0; i < t.length; i += 4)
            results[i >>> 2] = Pack.littleEndianToInt(t, i);

        int abcdefgh[] = new int[8];
        for(int i = 0; i < 8; i++)
            abcdefgh[i] = 0x9e3779b9;

        for(int i = 0; i < 4; i++)
            mix(abcdefgh);

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 256; j += 8)
            {
                for(int k = 0; k < 8; k++)
                    abcdefgh[k] += i >= 1 ? engineState[j + k] : results[j + k];

                mix(abcdefgh);
                for(int k = 0; k < 8; k++)
                    engineState[j + k] = abcdefgh[k];

            }

        }

        isaac();
        initialised = true;
    }

    private void isaac()
    {
        b += ++c;
        for(int i = 0; i < 256; i++)
        {
            int x = engineState[i];
            switch(i & 3)
            {
            case 0: // '\0'
                a ^= a << 13;
                break;

            case 1: // '\001'
                a ^= a >>> 6;
                break;

            case 2: // '\002'
                a ^= a << 2;
                break;

            case 3: // '\003'
                a ^= a >>> 16;
                break;
            }
            a += engineState[i + 128 & 0xff];
            int y;
            engineState[i] = y = engineState[x >>> 2 & 0xff] + a + b;
            results[i] = b = engineState[y >>> 10 & 0xff] + x;
        }

    }

    private void mix(int x[])
    {
        x[0] ^= x[1] << 11;
        x[3] += x[0];
        x[1] += x[2];
        x[1] ^= x[2] >>> 2;
        x[4] += x[1];
        x[2] += x[3];
        x[2] ^= x[3] << 8;
        x[5] += x[2];
        x[3] += x[4];
        x[3] ^= x[4] >>> 16;
        x[6] += x[3];
        x[4] += x[5];
        x[4] ^= x[5] << 10;
        x[7] += x[4];
        x[5] += x[6];
        x[5] ^= x[6] >>> 4;
        x[0] += x[5];
        x[6] += x[7];
        x[6] ^= x[7] << 8;
        x[1] += x[6];
        x[7] += x[0];
        x[7] ^= x[0] >>> 9;
        x[2] += x[7];
        x[0] += x[1];
    }

    private final int sizeL = 8;
    private final int stateArraySize = 256;
    private int engineState[];
    private int results[];
    private int a;
    private int b;
    private int c;
    private int index;
    private byte keyStream[];
    private byte workingKey[];
    private boolean initialised;
}
