// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricHybridCipher.java

package co.org.bouncy.pqc.jcajce.provider.util;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.ShortBufferException;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.util:
//            CipherSpiExt

public abstract class AsymmetricHybridCipher extends CipherSpiExt
{

    public AsymmetricHybridCipher()
    {
    }

    protected final void setMode(String s)
    {
    }

    protected final void setPadding(String s)
    {
    }

    public final byte[] getIV()
    {
        return null;
    }

    public final int getBlockSize()
    {
        return 0;
    }

    public final AlgorithmParameterSpec getParameters()
    {
        return paramSpec;
    }

    public final int getOutputSize(int inLen)
    {
        return opMode != 1 ? decryptOutputSize(inLen) : encryptOutputSize(inLen);
    }

    public final void initEncrypt(Key key)
        throws InvalidKeyException
    {
        try
        {
            initEncrypt(key, null, new SecureRandom());
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom random)
        throws InvalidKeyException
    {
        try
        {
            initEncrypt(key, null, random);
        }
        catch(InvalidAlgorithmParameterException iape)
        {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        initEncrypt(key, params, new SecureRandom());
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        opMode = 1;
        initCipherEncrypt(key, params, random);
    }

    public final void initDecrypt(Key key)
        throws InvalidKeyException
    {
        try
        {
            initDecrypt(key, null);
        }
        catch(InvalidAlgorithmParameterException iape)
        {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initDecrypt(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        opMode = 2;
        initCipherDecrypt(key, params);
    }

    public abstract byte[] update(byte abyte0[], int i, int j);

    public final int update(byte input[], int inOff, int inLen, byte output[], int outOff)
        throws ShortBufferException
    {
        if(output.length < getOutputSize(inLen))
        {
            throw new ShortBufferException("output");
        } else
        {
            byte out[] = update(input, inOff, inLen);
            System.arraycopy(out, 0, output, outOff, out.length);
            return out.length;
        }
    }

    public abstract byte[] doFinal(byte abyte0[], int i, int j)
        throws BadPaddingException;

    public final int doFinal(byte input[], int inOff, int inLen, byte output[], int outOff)
        throws ShortBufferException, BadPaddingException
    {
        if(output.length < getOutputSize(inLen))
        {
            throw new ShortBufferException("Output buffer too short.");
        } else
        {
            byte out[] = doFinal(input, inOff, inLen);
            System.arraycopy(out, 0, output, outOff, out.length);
            return out.length;
        }
    }

    protected abstract int encryptOutputSize(int i);

    protected abstract int decryptOutputSize(int i);

    protected abstract void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected AlgorithmParameterSpec paramSpec;
}
