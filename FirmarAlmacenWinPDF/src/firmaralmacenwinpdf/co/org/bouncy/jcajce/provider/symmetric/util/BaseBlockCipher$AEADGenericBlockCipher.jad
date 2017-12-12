// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseBlockCipher.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.modes.AEADBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BaseBlockCipher

private static class BaseBlockCipher$AEADGenericBlockCipher
    implements BaseBlockCipher.GenericBlockCipher
{

    public void init(boolean forEncryption, CipherParameters params)
        throws IllegalArgumentException
    {
        cipher.init(forEncryption, params);
    }

    public String getAlgorithmName()
    {
        return cipher.getUnderlyingCipher().getAlgorithmName();
    }

    public boolean wrapOnNoPadding()
    {
        return false;
    }

    public BlockCipher getUnderlyingCipher()
    {
        return cipher.getUnderlyingCipher();
    }

    public int getOutputSize(int len)
    {
        return cipher.getOutputSize(len);
    }

    public int getUpdateOutputSize(int len)
    {
        return cipher.getUpdateOutputSize(len);
    }

    public int processByte(byte in, byte out[], int outOff)
        throws DataLengthException
    {
        return cipher.processByte(in, out, outOff);
    }

    public int processBytes(byte in[], int inOff, int len, byte out[], int outOff)
        throws DataLengthException
    {
        return cipher.processBytes(in, inOff, len, out, outOff);
    }

    public int doFinal(byte out[], int outOff)
        throws IllegalStateException, InvalidCipherTextException
    {
        return cipher.doFinal(out, outOff);
    }

    private AEADBlockCipher cipher;

    BaseBlockCipher$AEADGenericBlockCipher(AEADBlockCipher cipher)
    {
        this.cipher = cipher;
    }
}
