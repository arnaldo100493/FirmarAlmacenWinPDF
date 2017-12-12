// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMac.java

package co.org.bouncy.crypto.macs;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.crypto.params.*;

public class GMac
    implements Mac
{

    public GMac(GCMBlockCipher cipher)
    {
        this.cipher = cipher;
        macSizeBits = 128;
    }

    public GMac(GCMBlockCipher cipher, int macSizeBits)
    {
        this.cipher = cipher;
        this.macSizeBits = macSizeBits;
    }

    public void init(CipherParameters params)
        throws IllegalArgumentException
    {
        if(params instanceof ParametersWithIV)
        {
            ParametersWithIV param = (ParametersWithIV)params;
            byte iv[] = param.getIV();
            KeyParameter keyParam = (KeyParameter)param.getParameters();
            cipher.init(true, new AEADParameters(keyParam, macSizeBits, iv));
        } else
        {
            throw new IllegalArgumentException("GMAC requires ParametersWithIV");
        }
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(cipher.getUnderlyingCipher().getAlgorithmName()).append("-GMAC").toString();
    }

    public int getMacSize()
    {
        return macSizeBits / 8;
    }

    public void update(byte in)
        throws IllegalStateException
    {
        cipher.processAADByte(in);
    }

    public void update(byte in[], int inOff, int len)
        throws DataLengthException, IllegalStateException
    {
        cipher.processAADBytes(in, inOff, len);
    }

    public int doFinal(byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        try
        {
            return cipher.doFinal(out, outOff);
        }
        catch(InvalidCipherTextException e)
        {
            throw new IllegalStateException(e.toString());
        }
    }

    public void reset()
    {
        cipher.reset();
    }

    private final GCMBlockCipher cipher;
    private final int macSizeBits;
}
