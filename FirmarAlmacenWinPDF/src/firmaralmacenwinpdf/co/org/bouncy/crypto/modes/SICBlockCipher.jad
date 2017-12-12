// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SICBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class SICBlockCipher
    implements BlockCipher
{

    public SICBlockCipher(BlockCipher c)
    {
        cipher = c;
        blockSize = cipher.getBlockSize();
        IV = new byte[blockSize];
        counter = new byte[blockSize];
        counterOut = new byte[blockSize];
    }

    public BlockCipher getUnderlyingCipher()
    {
        return cipher;
    }

    public void init(boolean forEncryption, CipherParameters params)
        throws IllegalArgumentException
    {
        if(params instanceof ParametersWithIV)
        {
            ParametersWithIV ivParam = (ParametersWithIV)params;
            byte iv[] = ivParam.getIV();
            System.arraycopy(iv, 0, IV, 0, IV.length);
            reset();
            if(ivParam.getParameters() != null)
                cipher.init(true, ivParam.getParameters());
        } else
        {
            throw new IllegalArgumentException("SIC mode requires ParametersWithIV");
        }
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(cipher.getAlgorithmName()).append("/SIC").toString();
    }

    public int getBlockSize()
    {
        return cipher.getBlockSize();
    }

    public int processBlock(byte in[], int inOff, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        cipher.processBlock(counter, 0, counterOut, 0);
        for(int i = 0; i < counterOut.length; i++)
            out[outOff + i] = (byte)(counterOut[i] ^ in[inOff + i]);

        for(int i = counter.length - 1; i >= 0 && ++counter[i] == 0; i--);
        return counter.length;
    }

    public void reset()
    {
        System.arraycopy(IV, 0, counter, 0, counter.length);
        cipher.reset();
    }

    private final BlockCipher cipher;
    private final int blockSize;
    private byte IV[];
    private byte counter[];
    private byte counterOut[];
}
