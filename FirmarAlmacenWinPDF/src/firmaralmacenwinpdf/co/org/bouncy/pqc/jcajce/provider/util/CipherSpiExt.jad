// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherSpiExt.java

package co.org.bouncy.pqc.jcajce.provider.util;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;

public abstract class CipherSpiExt extends CipherSpi
{

    public CipherSpiExt()
    {
    }

    protected final void engineInit(int opMode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        try
        {
            engineInit(opMode, key, (AlgorithmParameterSpec)null, random);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    protected final void engineInit(int opMode, Key key, AlgorithmParameters algParams, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(algParams == null)
        {
            engineInit(opMode, key, random);
            return;
        } else
        {
            AlgorithmParameterSpec paramSpec = null;
            engineInit(opMode, key, paramSpec, random);
            return;
        }
    }

    protected void engineInit(int opMode, Key key, AlgorithmParameterSpec params, SecureRandom javaRand)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(params != null && !(params instanceof AlgorithmParameterSpec))
            throw new InvalidAlgorithmParameterException();
        if(key == null || !(key instanceof Key))
            throw new InvalidKeyException();
        this.opMode = opMode;
        if(opMode == 1)
        {
            SecureRandom flexiRand = javaRand;
            initEncrypt(key, params, flexiRand);
        } else
        if(opMode == 2)
            initDecrypt(key, params);
    }

    protected final byte[] engineDoFinal(byte input[], int inOff, int inLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        return doFinal(input, inOff, inLen);
    }

    protected final int engineDoFinal(byte input[], int inOff, int inLen, byte output[], int outOff)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException
    {
        return doFinal(input, inOff, inLen, output, outOff);
    }

    protected final int engineGetBlockSize()
    {
        return getBlockSize();
    }

    protected final int engineGetKeySize(Key key)
        throws InvalidKeyException
    {
        if(!(key instanceof Key))
            throw new InvalidKeyException("Unsupported key.");
        else
            return getKeySize(key);
    }

    protected final byte[] engineGetIV()
    {
        return getIV();
    }

    protected final int engineGetOutputSize(int inLen)
    {
        return getOutputSize(inLen);
    }

    protected final AlgorithmParameters engineGetParameters()
    {
        return null;
    }

    protected final void engineSetMode(String modeName)
        throws NoSuchAlgorithmException
    {
        setMode(modeName);
    }

    protected final void engineSetPadding(String paddingName)
        throws NoSuchPaddingException
    {
        setPadding(paddingName);
    }

    protected final byte[] engineUpdate(byte input[], int inOff, int inLen)
    {
        return update(input, inOff, inLen);
    }

    protected final int engineUpdate(byte input[], int inOff, int inLen, byte output[], int outOff)
        throws ShortBufferException
    {
        return update(input, inOff, inLen, output, outOff);
    }

    public abstract void initEncrypt(Key key, AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    public abstract void initDecrypt(Key key, AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    public abstract String getName();

    public abstract int getBlockSize();

    public abstract int getOutputSize(int i);

    public abstract int getKeySize(Key key)
        throws InvalidKeyException;

    public abstract AlgorithmParameterSpec getParameters();

    public abstract byte[] getIV();

    protected abstract void setMode(String s)
        throws NoSuchAlgorithmException;

    protected abstract void setPadding(String s)
        throws NoSuchPaddingException;

    public final byte[] update(byte input[])
    {
        return update(input, 0, input.length);
    }

    public abstract byte[] update(byte abyte0[], int i, int j);

    public abstract int update(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws ShortBufferException;

    public final byte[] doFinal()
        throws IllegalBlockSizeException, BadPaddingException
    {
        return doFinal(null, 0, 0);
    }

    public final byte[] doFinal(byte input[])
        throws IllegalBlockSizeException, BadPaddingException
    {
        return doFinal(input, 0, input.length);
    }

    public abstract byte[] doFinal(byte abyte0[], int i, int j)
        throws IllegalBlockSizeException, BadPaddingException;

    public abstract int doFinal(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

    public static final int ENCRYPT_MODE = 1;
    public static final int DECRYPT_MODE = 2;
    protected int opMode;
}
