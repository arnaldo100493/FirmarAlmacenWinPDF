// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CTSBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;

// Referenced classes of package co.org.bouncy.crypto.modes:
//            OFBBlockCipher, CFBBlockCipher, CBCBlockCipher

public class CTSBlockCipher extends BufferedBlockCipher
{

    public CTSBlockCipher(BlockCipher cipher)
    {
        if((cipher instanceof OFBBlockCipher) || (cipher instanceof CFBBlockCipher))
        {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        } else
        {
            this.cipher = cipher;
            blockSize = cipher.getBlockSize();
            buf = new byte[blockSize * 2];
            bufOff = 0;
            return;
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

    public int getOutputSize(int len)
    {
        return len + bufOff;
    }

    public int processByte(byte in, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        int resultLen = 0;
        if(bufOff == buf.length)
        {
            resultLen = cipher.processBlock(buf, 0, out, outOff);
            System.arraycopy(buf, blockSize, buf, 0, blockSize);
            bufOff = blockSize;
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
            System.arraycopy(buf, blockSize, buf, 0, blockSize);
            bufOff = blockSize;
            len -= gapLen;
            for(inOff += gapLen; len > blockSize; inOff += blockSize)
            {
                System.arraycopy(in, inOff, buf, bufOff, blockSize);
                resultLen += cipher.processBlock(buf, 0, out, outOff + resultLen);
                System.arraycopy(buf, blockSize, buf, 0, blockSize);
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
        if(bufOff + outOff > out.length)
            throw new DataLengthException("output buffer to small in doFinal");
        int blockSize = cipher.getBlockSize();
        int len = bufOff - blockSize;
        byte block[] = new byte[blockSize];
        if(forEncryption)
        {
            cipher.processBlock(buf, 0, block, 0);
            if(bufOff < blockSize)
                throw new DataLengthException("need at least one block of input for CTS");
            for(int i = bufOff; i != buf.length; i++)
                buf[i] = block[i - blockSize];

            for(int i = blockSize; i != bufOff; i++)
                buf[i] ^= block[i - blockSize];

            if(cipher instanceof CBCBlockCipher)
            {
                BlockCipher c = ((CBCBlockCipher)cipher).getUnderlyingCipher();
                c.processBlock(buf, blockSize, out, outOff);
            } else
            {
                cipher.processBlock(buf, blockSize, out, outOff);
            }
            System.arraycopy(block, 0, out, outOff + blockSize, len);
        } else
        {
            byte lastBlock[] = new byte[blockSize];
            if(cipher instanceof CBCBlockCipher)
            {
                BlockCipher c = ((CBCBlockCipher)cipher).getUnderlyingCipher();
                c.processBlock(buf, 0, block, 0);
            } else
            {
                cipher.processBlock(buf, 0, block, 0);
            }
            for(int i = blockSize; i != bufOff; i++)
                lastBlock[i - blockSize] = (byte)(block[i - blockSize] ^ buf[i]);

            System.arraycopy(buf, blockSize, block, 0, len);
            cipher.processBlock(block, 0, out, outOff);
            System.arraycopy(lastBlock, 0, out, outOff + blockSize, len);
        }
        int offset = bufOff;
        reset();
        return offset;
    }

    private int blockSize;
}
