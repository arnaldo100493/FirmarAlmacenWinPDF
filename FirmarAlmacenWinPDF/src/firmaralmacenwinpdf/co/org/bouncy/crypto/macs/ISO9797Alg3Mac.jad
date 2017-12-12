// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO9797Alg3Mac.java

package co.org.bouncy.crypto.macs;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.DESEngine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.paddings.BlockCipherPadding;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class ISO9797Alg3Mac
    implements Mac
{

    public ISO9797Alg3Mac(BlockCipher cipher)
    {
        this(cipher, cipher.getBlockSize() * 8, null);
    }

    public ISO9797Alg3Mac(BlockCipher cipher, BlockCipherPadding padding)
    {
        this(cipher, cipher.getBlockSize() * 8, padding);
    }

    public ISO9797Alg3Mac(BlockCipher cipher, int macSizeInBits)
    {
        this(cipher, macSizeInBits, null);
    }

    public ISO9797Alg3Mac(BlockCipher cipher, int macSizeInBits, BlockCipherPadding padding)
    {
        if(macSizeInBits % 8 != 0)
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        if(!(cipher instanceof DESEngine))
        {
            throw new IllegalArgumentException("cipher must be instance of DESEngine");
        } else
        {
            this.cipher = new CBCBlockCipher(cipher);
            this.padding = padding;
            macSize = macSizeInBits / 8;
            mac = new byte[cipher.getBlockSize()];
            buf = new byte[cipher.getBlockSize()];
            bufOff = 0;
            return;
        }
    }

    public String getAlgorithmName()
    {
        return "ISO9797Alg3";
    }

    public void init(CipherParameters params)
    {
        reset();
        if(!(params instanceof KeyParameter) && !(params instanceof ParametersWithIV))
            throw new IllegalArgumentException("params must be an instance of KeyParameter or ParametersWithIV");
        KeyParameter kp;
        if(params instanceof KeyParameter)
            kp = (KeyParameter)params;
        else
            kp = (KeyParameter)((ParametersWithIV)params).getParameters();
        byte keyvalue[] = kp.getKey();
        KeyParameter key1;
        if(keyvalue.length == 16)
        {
            key1 = new KeyParameter(keyvalue, 0, 8);
            lastKey2 = new KeyParameter(keyvalue, 8, 8);
            lastKey3 = key1;
        } else
        if(keyvalue.length == 24)
        {
            key1 = new KeyParameter(keyvalue, 0, 8);
            lastKey2 = new KeyParameter(keyvalue, 8, 8);
            lastKey3 = new KeyParameter(keyvalue, 16, 8);
        } else
        {
            throw new IllegalArgumentException("Key must be either 112 or 168 bit long");
        }
        if(params instanceof ParametersWithIV)
            cipher.init(true, new ParametersWithIV(key1, ((ParametersWithIV)params).getIV()));
        else
            cipher.init(true, key1);
    }

    public int getMacSize()
    {
        return macSize;
    }

    public void update(byte in)
    {
        if(bufOff == buf.length)
        {
            cipher.processBlock(buf, 0, mac, 0);
            bufOff = 0;
        }
        buf[bufOff++] = in;
    }

    public void update(byte in[], int inOff, int len)
    {
        if(len < 0)
            throw new IllegalArgumentException("Can't have a negative input length!");
        int blockSize = cipher.getBlockSize();
        int resultLen = 0;
        int gapLen = blockSize - bufOff;
        if(len > gapLen)
        {
            System.arraycopy(in, inOff, buf, bufOff, gapLen);
            resultLen += cipher.processBlock(buf, 0, mac, 0);
            bufOff = 0;
            len -= gapLen;
            for(inOff += gapLen; len > blockSize; inOff += blockSize)
            {
                resultLen += cipher.processBlock(in, inOff, mac, 0);
                len -= blockSize;
            }

        }
        System.arraycopy(in, inOff, buf, bufOff, len);
        bufOff += len;
    }

    public int doFinal(byte out[], int outOff)
    {
        int blockSize = cipher.getBlockSize();
        if(padding == null)
        {
            for(; bufOff < blockSize; bufOff++)
                buf[bufOff] = 0;

        } else
        {
            if(bufOff == blockSize)
            {
                cipher.processBlock(buf, 0, mac, 0);
                bufOff = 0;
            }
            padding.addPadding(buf, bufOff);
        }
        cipher.processBlock(buf, 0, mac, 0);
        DESEngine deseng = new DESEngine();
        deseng.init(false, lastKey2);
        deseng.processBlock(mac, 0, mac, 0);
        deseng.init(true, lastKey3);
        deseng.processBlock(mac, 0, mac, 0);
        System.arraycopy(mac, 0, out, outOff, macSize);
        reset();
        return macSize;
    }

    public void reset()
    {
        for(int i = 0; i < buf.length; i++)
            buf[i] = 0;

        bufOff = 0;
        cipher.reset();
    }

    private byte mac[];
    private byte buf[];
    private int bufOff;
    private BlockCipher cipher;
    private BlockCipherPadding padding;
    private int macSize;
    private KeyParameter lastKey2;
    private KeyParameter lastKey3;
}
