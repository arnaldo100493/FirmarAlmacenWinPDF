// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaddedBufferedBlockCipher.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;

// Referenced classes of package co.org.bouncy.crypto.paddings:
//            PKCS7Padding, BlockCipherPadding

public class PaddedBufferedBlockCipher extends BufferedBlockCipher
{

    public PaddedBufferedBlockCipher(BlockCipher cipher, BlockCipherPadding padding)
    {
        this.cipher = cipher;
        this.padding = padding;
        buf = new byte[cipher.getBlockSize()];
        bufOff = 0;
    }

    public PaddedBufferedBlockCipher(BlockCipher cipher)
    {
        this(cipher, ((BlockCipherPadding) (new PKCS7Padding())));
    }

    public void init(boolean forEncryption, CipherParameters params)
        throws IllegalArgumentException
    {
        this.forEncryption = forEncryption;
        reset();
        if(params instanceof ParametersWithRandom)
        {
            ParametersWithRandom p = (ParametersWithRandom)params;
            padding.init(p.getRandom());
            cipher.init(forEncryption, p.getParameters());
        } else
        {
            padding.init(null);
            cipher.init(forEncryption, params);
        }
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
            throw new OutputLengthException("output buffer too short");
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
        int resultLen;
        int blockSize = cipher.getBlockSize();
        resultLen = 0;
        if(forEncryption)
        {
            if(bufOff == blockSize)
            {
                if(outOff + 2 * blockSize > out.length)
                {
                    reset();
                    throw new OutputLengthException("output buffer too short");
                }
                resultLen = cipher.processBlock(buf, 0, out, outOff);
                bufOff = 0;
            }
            padding.addPadding(buf, bufOff);
            resultLen += cipher.processBlock(buf, 0, out, outOff + resultLen);
            reset();
            break MISSING_BLOCK_LABEL_221;
        }
        if(bufOff == blockSize)
        {
            resultLen = cipher.processBlock(buf, 0, buf, 0);
            bufOff = 0;
        } else
        {
            reset();
            throw new DataLengthException("last block incomplete in decryption");
        }
        resultLen -= padding.padCount(buf);
        System.arraycopy(buf, 0, out, outOff, resultLen);
        reset();
        break MISSING_BLOCK_LABEL_221;
        Exception exception;
        exception;
        reset();
        throw exception;
        return resultLen;
    }

    BlockCipherPadding padding;
}
