// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFBBlockCipherMac.java

package co.org.bouncy.crypto.macs;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.paddings.BlockCipherPadding;

// Referenced classes of package co.org.bouncy.crypto.macs:
//            MacCFBBlockCipher

public class CFBBlockCipherMac
    implements Mac
{

    public CFBBlockCipherMac(BlockCipher cipher)
    {
        this(cipher, 8, (cipher.getBlockSize() * 8) / 2, null);
    }

    public CFBBlockCipherMac(BlockCipher cipher, BlockCipherPadding padding)
    {
        this(cipher, 8, (cipher.getBlockSize() * 8) / 2, padding);
    }

    public CFBBlockCipherMac(BlockCipher cipher, int cfbBitSize, int macSizeInBits)
    {
        this(cipher, cfbBitSize, macSizeInBits, null);
    }

    public CFBBlockCipherMac(BlockCipher cipher, int cfbBitSize, int macSizeInBits, BlockCipherPadding padding)
    {
        this.padding = null;
        if(macSizeInBits % 8 != 0)
        {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        } else
        {
            mac = new byte[cipher.getBlockSize()];
            this.cipher = new MacCFBBlockCipher(cipher, cfbBitSize);
            this.padding = padding;
            macSize = macSizeInBits / 8;
            buf = new byte[this.cipher.getBlockSize()];
            bufOff = 0;
            return;
        }
    }

    public String getAlgorithmName()
    {
        return cipher.getAlgorithmName();
    }

    public void init(CipherParameters params)
    {
        reset();
        cipher.init(params);
    }

    public int getMacSize()
    {
        return macSize;
    }

    public void update(byte in)
    {
        if(bufOff == buf.length)
        {
            cipher.processBlock(buf, 0, mac, 0);
            bufOff = 0;
        }
        buf[bufOff++] = in;
    }

    public void update(byte in[], int inOff, int len)
    {
        if(len < 0)
            throw new IllegalArgumentException("Can't have a negative input length!");
        int blockSize = cipher.getBlockSize();
        int resultLen = 0;
        int gapLen = blockSize - bufOff;
        if(len > gapLen)
        {
            System.arraycopy(in, inOff, buf, bufOff, gapLen);
            resultLen += cipher.processBlock(buf, 0, mac, 0);
            bufOff = 0;
            len -= gapLen;
            for(inOff += gapLen; len > blockSize; inOff += blockSize)
            {
                resultLen += cipher.processBlock(in, inOff, mac, 0);
                len -= blockSize;
            }

        }
        System.arraycopy(in, inOff, buf, bufOff, len);
        bufOff += len;
    }

    public int doFinal(byte out[], int outOff)
    {
        int blockSize = cipher.getBlockSize();
        if(padding == null)
            for(; bufOff < blockSize; bufOff++)
                buf[bufOff] = 0;

        else
            padding.addPadding(buf, bufOff);
        cipher.processBlock(buf, 0, mac, 0);
        cipher.getMacBlock(mac);
        System.arraycopy(mac, 0, out, outOff, macSize);
        reset();
        return macSize;
    }

    public void reset()
    {
        for(int i = 0; i < buf.length; i++)
            buf[i] = 0;

        bufOff = 0;
        cipher.reset();
    }

    private byte mac[];
    private byte buf[];
    private int bufOff;
    private MacCFBBlockCipher cipher;
    private BlockCipherPadding padding;
    private int macSize;
}
