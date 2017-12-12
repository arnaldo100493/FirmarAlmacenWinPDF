// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaddedBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;

/**
 * @deprecated Class PaddedBlockCipher is deprecated
 */

public class PaddedBlockCipher extends BufferedBlockCipher
{

    public PaddedBlockCipher(BlockCipher cipher)
    {
        this.cipher = cipher;
        buf = new byte[cipher.getBlockSize()];
        bufOff = 0;
    }

    public int getOutputSize(int len)
    {
        int total = len + bufOff;
        int leftOver = total % buf.length;
        if(leftOver == 0)
        {
            if(forEncryption)
                return total + buf.length;
            else
                return total;
        } else
        {
            return (total - leftOver) + buf.length;
        }
    }

    public int getUpdateOutputSize(int len)
    {
        int total = len + bufOff;
        int leftOver = total % buf.length;
        if(leftOver == 0)
            return total - buf.length;
        else
            return total - leftOver;
    }

    public int processByte(byte in, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        int resultLen = 0;
        if(bufOff == buf.length)
        {
            resultLen = cipher.processBlock(buf, 0, out, outOff);
            bufOff = 0;
        }
        buf[bufOff++] = in;
        return resultLen;
    }

    public int processBytes(byte in[], int inOff, int len, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        if(len < 0)
            throw new IllegalArgumentException("Can't have a negative input length!");
        int blockSize = getBlockSize();
        int length = getUpdateOutputSize(len);
        if(length > 0 && outOff + length > out.length)
            throw new DataLengthException("output buffer too short");
        int resultLen = 0;
        int gapLen = buf.length - bufOff;
        if(len > gapLen)
        {
            System.arraycopy(in, inOff, buf, bufOff, gapLen);
            resultLen += cipher.processBlock(buf, 0, out, outOff);
            bufOff = 0;
            len -= gapLen;
            for(inOff += gapLen; len > buf.length; inOff += blockSize)
            {
                resultLen += cipher.processBlock(in, inOff, out, outOff + resultLen);
                len -= blockSize;
            }

        }
        System.arraycopy(in, inOff, buf, bufOff, len);
        bufOff += len;
        return resultLen;
    }

    public int doFinal(byte out[], int outOff)
        throws DataLengthException, IllegalStateException, InvalidCipherTextException
    {
        int blockSize = cipher.getBlockSize();
        int resultLen = 0;
        if(forEncryption)
        {
            if(bufOff == blockSize)
            {
                if(outOff + 2 * blockSize > out.length)
                    throw new DataLengthException("output buffer too short");
                resultLen = cipher.processBlock(buf, 0, out, outOff);
                bufOff = 0;
            }
            byte code = (byte)(blockSize - bufOff);
            for(; bufOff < blockSize; bufOff++)
                buf[bufOff] = code;

            resultLen += cipher.processBlock(buf, 0, out, outOff + resultLen);
        } else
        {
            if(bufOff == blockSize)
            {
                resultLen = cipher.processBlock(buf, 0, buf, 0);
                bufOff = 0;
            } else
            {
                throw new DataLengthException("last block incomplete in decryption");
            }
            int count = buf[blockSize - 1] & 0xff;
            if(count < 0 || count > blockSize)
                throw new InvalidCipherTextException("pad block corrupted");
            resultLen -= count;
            System.arraycopy(buf, 0, out, outOff, resultLen);
        }
        reset();
        return resultLen;
    }
}
