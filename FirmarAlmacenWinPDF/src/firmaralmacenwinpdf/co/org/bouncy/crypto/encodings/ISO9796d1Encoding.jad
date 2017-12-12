// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO9796d1Encoding.java

package co.org.bouncy.crypto.encodings;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import java.math.BigInteger;

public class ISO9796d1Encoding
    implements AsymmetricBlockCipher
{

    public ISO9796d1Encoding(AsymmetricBlockCipher cipher)
    {
        padBits = 0;
        engine = cipher;
    }

    public AsymmetricBlockCipher getUnderlyingCipher()
    {
        return engine;
    }

    public void init(boolean forEncryption, CipherParameters param)
    {
        RSAKeyParameters kParam = null;
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom rParam = (ParametersWithRandom)param;
            kParam = (RSAKeyParameters)rParam.getParameters();
        } else
        {
            kParam = (RSAKeyParameters)param;
        }
        engine.init(forEncryption, param);
        modulus = kParam.getModulus();
        bitSize = modulus.bitLength();
        this.forEncryption = forEncryption;
    }

    public int getInputBlockSize()
    {
        int baseBlockSize = engine.getInputBlockSize();
        if(forEncryption)
            return (baseBlockSize + 1) / 2;
        else
            return baseBlockSize;
    }

    public int getOutputBlockSize()
    {
        int baseBlockSize = engine.getOutputBlockSize();
        if(forEncryption)
            return baseBlockSize;
        else
            return (baseBlockSize + 1) / 2;
    }

    public void setPadBits(int padBits)
    {
        if(padBits > 7)
        {
            throw new IllegalArgumentException("padBits > 7");
        } else
        {
            this.padBits = padBits;
            return;
        }
    }

    public int getPadBits()
    {
        return padBits;
    }

    public byte[] processBlock(byte in[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        if(forEncryption)
            return encodeBlock(in, inOff, inLen);
        else
            return decodeBlock(in, inOff, inLen);
    }

    private byte[] encodeBlock(byte in[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        byte block[] = new byte[(bitSize + 7) / 8];
        int r = padBits + 1;
        int z = inLen;
        int t = (bitSize + 13) / 16;
        for(int i = 0; i < t; i += z)
            if(i > t - z)
                System.arraycopy(in, (inOff + inLen) - (t - i), block, block.length - t, t - i);
            else
                System.arraycopy(in, inOff, block, block.length - (i + z), z);

        for(int i = block.length - 2 * t; i != block.length; i += 2)
        {
            byte val = block[(block.length - t) + i / 2];
            block[i] = (byte)(shadows[(val & 0xff) >>> 4] << 4 | shadows[val & 0xf]);
            block[i + 1] = val;
        }

        block[block.length - 2 * z] ^= r;
        block[block.length - 1] = (byte)(block[block.length - 1] << 4 | 6);
        int maxBit = 8 - (bitSize - 1) % 8;
        int offSet = 0;
        if(maxBit != 8)
        {
            block[0] &= 255 >>> maxBit;
            block[0] |= 128 >>> maxBit;
        } else
        {
            block[0] = 0;
            block[1] |= 0x80;
            offSet = 1;
        }
        return engine.processBlock(block, offSet, block.length - offSet);
    }

    private byte[] decodeBlock(byte in[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        byte block[] = engine.processBlock(in, inOff, inLen);
        int r = 1;
        int t = (bitSize + 13) / 16;
        BigInteger iS = new BigInteger(1, block);
        BigInteger iR;
        if(iS.mod(SIXTEEN).equals(SIX))
            iR = iS;
        else
        if(modulus.subtract(iS).mod(SIXTEEN).equals(SIX))
            iR = modulus.subtract(iS);
        else
            throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
        block = convertOutputDecryptOnly(iR);
        if((block[block.length - 1] & 0xf) != 6)
            throw new InvalidCipherTextException("invalid forcing byte in block");
        block[block.length - 1] = (byte)((block[block.length - 1] & 0xff) >>> 4 | inverse[(block[block.length - 2] & 0xff) >> 4] << 4);
        block[0] = (byte)(shadows[(block[1] & 0xff) >>> 4] << 4 | shadows[block[1] & 0xf]);
        boolean boundaryFound = false;
        int boundary = 0;
        for(int i = block.length - 1; i >= block.length - 2 * t; i -= 2)
        {
            int val = shadows[(block[i] & 0xff) >>> 4] << 4 | shadows[block[i] & 0xf];
            if(((block[i - 1] ^ val) & 0xff) == 0)
                continue;
            if(!boundaryFound)
            {
                boundaryFound = true;
                r = (block[i - 1] ^ val) & 0xff;
                boundary = i - 1;
            } else
            {
                throw new InvalidCipherTextException("invalid tsums in block");
            }
        }

        block[boundary] = 0;
        byte nblock[] = new byte[(block.length - boundary) / 2];
        for(int i = 0; i < nblock.length; i++)
            nblock[i] = block[2 * i + boundary + 1];

        padBits = r - 1;
        return nblock;
    }

    private static byte[] convertOutputDecryptOnly(BigInteger result)
    {
        byte output[] = result.toByteArray();
        if(output[0] == 0)
        {
            byte tmp[] = new byte[output.length - 1];
            System.arraycopy(output, 1, tmp, 0, tmp.length);
            return tmp;
        } else
        {
            return output;
        }
    }

    private static final BigInteger SIXTEEN = BigInteger.valueOf(16L);
    private static final BigInteger SIX = BigInteger.valueOf(6L);
    private static byte shadows[] = {
        14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 
        11, 6, 7, 10, 12, 1
    };
    private static byte inverse[] = {
        8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 
        13, 10, 14, 9, 0, 7
    };
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private int bitSize;
    private int padBits;
    private BigInteger modulus;

}
