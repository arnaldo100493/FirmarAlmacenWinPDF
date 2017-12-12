// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricBlockCipher.java

package co.org.bouncy.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.util:
//            CipherSpiExt

public abstract class AsymmetricBlockCipher extends CipherSpiExt
{

    public AsymmetricBlockCipher()
    {
        buf = new ByteArrayOutputStream();
    }

    public final int getBlockSize()
    {
        return opMode != 1 ? cipherTextSize : maxPlainTextSize;
    }

    public final byte[] getIV()
    {
        return null;
    }

    public final int getOutputSize(int inLen)
    {
        int totalLen = inLen + buf.size();
        int maxLen = getBlockSize();
        if(totalLen > maxLen)
            return 0;
        else
            return maxLen;
    }

    public final AlgorithmParameterSpec getParameters()
    {
        return paramSpec;
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

    public final void initEncrypt(Key key, AlgorithmParameterSpec params, SecureRandom secureRandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        opMode = 1;
        initCipherEncrypt(key, params, secureRandom);
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

    public final byte[] update(byte input[], int inOff, int inLen)
    {
        if(inLen != 0)
            buf.write(input, inOff, inLen);
        return new byte[0];
    }

    public final int update(byte input[], int inOff, int inLen, byte output[], int outOff)
    {
        update(input, inOff, inLen);
        return 0;
    }

    public final byte[] doFinal(byte input[], int inOff, int inLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        checkLength(inLen);
        update(input, inOff, inLen);
        byte mBytes[] = buf.toByteArray();
        buf.reset();
        switch(opMode)
        {
        case 1: // '\001'
            return messageEncrypt(mBytes);

        case 2: // '\002'
            return messageDecrypt(mBytes);
        }
        return null;
    }

    public final int doFinal(byte input[], int inOff, int inLen, byte output[], int outOff)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException
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

    protected final void setMode(String s)
    {
    }

    protected final void setPadding(String s)
    {
    }

    protected void checkLength(int inLen)
        throws IllegalBlockSizeException
    {
        int inLength = inLen + buf.size();
        if(opMode == 1)
        {
            if(inLength > maxPlainTextSize)
                throw new IllegalBlockSizeException((new StringBuilder()).append("The length of the plaintext (").append(inLength).append(" bytes) is not supported by ").append("the cipher (max. ").append(maxPlainTextSize).append(" bytes).").toString());
        } else
        if(opMode == 2 && inLength != cipherTextSize)
            throw new IllegalBlockSizeException((new StringBuilder()).append("Illegal ciphertext length (expected ").append(cipherTextSize).append(" bytes, was ").append(inLength).append(" bytes).").toString());
    }

    protected abstract void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmparameterspec)
        throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract byte[] messageEncrypt(byte abyte0[])
        throws IllegalBlockSizeException, BadPaddingException;

    protected abstract byte[] messageDecrypt(byte abyte0[])
        throws IllegalBlockSizeException, BadPaddingException;

    protected AlgorithmParameterSpec paramSpec;
    protected ByteArrayOutputStream buf;
    protected int maxPlainTextSize;
    protected int cipherTextSize;
}
