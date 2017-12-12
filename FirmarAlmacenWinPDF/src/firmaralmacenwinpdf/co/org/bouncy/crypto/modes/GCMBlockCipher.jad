// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GCMBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.modes.gcm.GCMExponentiator;
import co.org.bouncy.crypto.modes.gcm.GCMMultiplier;
import co.org.bouncy.crypto.modes.gcm.Tables1kGCMExponentiator;
import co.org.bouncy.crypto.modes.gcm.Tables8kGCMMultiplier;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.modes:
//            AEADBlockCipher

public class GCMBlockCipher
    implements AEADBlockCipher
{

    public GCMBlockCipher(BlockCipher c)
    {
        this(c, null);
    }

    public GCMBlockCipher(BlockCipher c, GCMMultiplier m)
    {
        if(c.getBlockSize() != 16)
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        if(m == null)
            m = new Tables8kGCMMultiplier();
        cipher = c;
        multiplier = m;
    }

    public BlockCipher getUnderlyingCipher()
    {
        return cipher;
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(cipher.getAlgorithmName()).append("/GCM").toString();
    }

    public void init(boolean forEncryption, CipherParameters params)
        throws IllegalArgumentException
    {
        this.forEncryption = forEncryption;
        macBlock = null;
        KeyParameter keyParam;
        if(params instanceof AEADParameters)
        {
            AEADParameters param = (AEADParameters)params;
            nonce = param.getNonce();
            initialAssociatedText = param.getAssociatedText();
            int macSizeBits = param.getMacSize();
            if(macSizeBits < 96 || macSizeBits > 128 || macSizeBits % 8 != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid value for MAC size: ").append(macSizeBits).toString());
            macSize = macSizeBits / 8;
            keyParam = param.getKey();
        } else
        if(params instanceof ParametersWithIV)
        {
            ParametersWithIV param = (ParametersWithIV)params;
            nonce = param.getIV();
            initialAssociatedText = null;
            macSize = 16;
            keyParam = (KeyParameter)param.getParameters();
        } else
        {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        }
        int bufLength = forEncryption ? 16 : 16 + macSize;
        bufBlock = new byte[bufLength];
        if(nonce == null || nonce.length < 1)
            throw new IllegalArgumentException("IV must be at least 1 byte");
        if(keyParam != null)
        {
            cipher.init(true, keyParam);
            H = new byte[16];
            cipher.processBlock(H, 0, H, 0);
            multiplier.init(H);
            exp = null;
        }
        J0 = new byte[16];
        if(nonce.length == 12)
        {
            System.arraycopy(nonce, 0, J0, 0, nonce.length);
            J0[15] = 1;
        } else
        {
            gHASH(J0, nonce, nonce.length);
            byte X[] = new byte[16];
            Pack.longToBigEndian((long)nonce.length * 8L, X, 8);
            gHASHBlock(J0, X);
        }
        S = new byte[16];
        S_at = new byte[16];
        S_atPre = new byte[16];
        atBlock = new byte[16];
        atBlockPos = 0;
        atLength = 0L;
        atLengthPre = 0L;
        counter = Arrays.clone(J0);
        bufOff = 0;
        totalLength = 0L;
        if(initialAssociatedText != null)
            processAADBytes(initialAssociatedText, 0, initialAssociatedText.length);
    }

    public byte[] getMac()
    {
        return Arrays.clone(macBlock);
    }

    public int getOutputSize(int len)
    {
        int totalData = len + bufOff;
        if(forEncryption)
            return totalData + macSize;
        else
            return totalData >= macSize ? totalData - macSize : 0;
    }

    public int getUpdateOutputSize(int len)
    {
        int totalData = len + bufOff;
        if(!forEncryption)
        {
            if(totalData < macSize)
                return 0;
            totalData -= macSize;
        }
        return totalData - totalData % 16;
    }

    public void processAADByte(byte in)
    {
        atBlock[atBlockPos] = in;
        if(++atBlockPos == 16)
        {
            gHASHBlock(S_at, atBlock);
            atBlockPos = 0;
            atLength += 16L;
        }
    }

    public void processAADBytes(byte in[], int inOff, int len)
    {
        for(int i = 0; i < len; i++)
        {
            atBlock[atBlockPos] = in[inOff + i];
            if(++atBlockPos == 16)
            {
                gHASHBlock(S_at, atBlock);
                atBlockPos = 0;
                atLength += 16L;
            }
        }

    }

    private void initCipher()
    {
        if(atLength > 0L)
        {
            System.arraycopy(S_at, 0, S_atPre, 0, 16);
            atLengthPre = atLength;
        }
        if(atBlockPos > 0)
        {
            gHASHPartial(S_atPre, atBlock, 0, atBlockPos);
            atLengthPre += atBlockPos;
        }
        if(atLengthPre > 0L)
            System.arraycopy(S_atPre, 0, S, 0, 16);
    }

    public int processByte(byte in, byte out[], int outOff)
        throws DataLengthException
    {
        bufBlock[bufOff] = in;
        if(++bufOff == bufBlock.length)
        {
            outputBlock(out, outOff);
            return 16;
        } else
        {
            return 0;
        }
    }

    public int processBytes(byte in[], int inOff, int len, byte out[], int outOff)
        throws DataLengthException
    {
        int resultLen = 0;
        for(int i = 0; i < len; i++)
        {
            bufBlock[bufOff] = in[inOff + i];
            if(++bufOff == bufBlock.length)
            {
                outputBlock(out, outOff + resultLen);
                resultLen += 16;
            }
        }

        return resultLen;
    }

    private void outputBlock(byte output[], int offset)
    {
        if(totalLength == 0L)
            initCipher();
        gCTRBlock(bufBlock, output, offset);
        if(forEncryption)
        {
            bufOff = 0;
        } else
        {
            System.arraycopy(bufBlock, 16, bufBlock, 0, macSize);
            bufOff = macSize;
        }
    }

    public int doFinal(byte out[], int outOff)
        throws IllegalStateException, InvalidCipherTextException
    {
        if(totalLength == 0L)
            initCipher();
        int extra = bufOff;
        if(!forEncryption)
        {
            if(extra < macSize)
                throw new InvalidCipherTextException("data too short");
            extra -= macSize;
        }
        if(extra > 0)
            gCTRPartial(bufBlock, 0, extra, out, outOff);
        atLength += atBlockPos;
        if(atLength > atLengthPre)
        {
            if(atBlockPos > 0)
                gHASHPartial(S_at, atBlock, 0, atBlockPos);
            if(atLengthPre > 0L)
                xor(S_at, S_atPre);
            long c = totalLength * 8L + 127L >>> 7;
            byte H_c[] = new byte[16];
            if(exp == null)
            {
                exp = new Tables1kGCMExponentiator();
                exp.init(H);
            }
            exp.exponentiateX(c, H_c);
            multiply(S_at, H_c);
            xor(S, S_at);
        }
        byte X[] = new byte[16];
        Pack.longToBigEndian(atLength * 8L, X, 0);
        Pack.longToBigEndian(totalLength * 8L, X, 8);
        gHASHBlock(S, X);
        byte tag[] = new byte[16];
        cipher.processBlock(J0, 0, tag, 0);
        xor(tag, S);
        int resultLen = extra;
        macBlock = new byte[macSize];
        System.arraycopy(tag, 0, macBlock, 0, macSize);
        if(forEncryption)
        {
            System.arraycopy(macBlock, 0, out, outOff + bufOff, macSize);
            resultLen += macSize;
        } else
        {
            byte msgMac[] = new byte[macSize];
            System.arraycopy(bufBlock, extra, msgMac, 0, macSize);
            if(!Arrays.constantTimeAreEqual(macBlock, msgMac))
                throw new InvalidCipherTextException("mac check in GCM failed");
        }
        reset(false);
        return resultLen;
    }

    public void reset()
    {
        reset(true);
    }

    private void reset(boolean clearMac)
    {
        cipher.reset();
        S = new byte[16];
        S_at = new byte[16];
        S_atPre = new byte[16];
        atBlock = new byte[16];
        atBlockPos = 0;
        atLength = 0L;
        atLengthPre = 0L;
        counter = Arrays.clone(J0);
        bufOff = 0;
        totalLength = 0L;
        if(bufBlock != null)
            Arrays.fill(bufBlock, (byte)0);
        if(clearMac)
            macBlock = null;
        if(initialAssociatedText != null)
            processAADBytes(initialAssociatedText, 0, initialAssociatedText.length);
    }

    private void gCTRBlock(byte block[], byte out[], int outOff)
    {
        byte tmp[] = getNextCounterBlock();
        xor(tmp, block);
        System.arraycopy(tmp, 0, out, outOff, 16);
        gHASHBlock(S, forEncryption ? tmp : block);
        totalLength += 16L;
    }

    private void gCTRPartial(byte buf[], int off, int len, byte out[], int outOff)
    {
        byte tmp[] = getNextCounterBlock();
        xor(tmp, buf, off, len);
        System.arraycopy(tmp, 0, out, outOff, len);
        gHASHPartial(S, forEncryption ? tmp : buf, 0, len);
        totalLength += len;
    }

    private void gHASH(byte Y[], byte b[], int len)
    {
        for(int pos = 0; pos < len; pos += 16)
        {
            int num = Math.min(len - pos, 16);
            gHASHPartial(Y, b, pos, num);
        }

    }

    private void gHASHBlock(byte Y[], byte b[])
    {
        xor(Y, b);
        multiplier.multiplyH(Y);
    }

    private void gHASHPartial(byte Y[], byte b[], int off, int len)
    {
        xor(Y, b, off, len);
        multiplier.multiplyH(Y);
    }

    private byte[] getNextCounterBlock()
    {
        int i = 15;
        do
        {
            if(i < 12)
                break;
            byte b = (byte)(counter[i] + 1 & 0xff);
            counter[i] = b;
            if(b != 0)
                break;
            i--;
        } while(true);
        byte tmp[] = new byte[16];
        cipher.processBlock(counter, 0, tmp, 0);
        return tmp;
    }

    private static void multiply(byte block[], byte val[])
    {
        byte tmp[] = Arrays.clone(block);
        byte c[] = new byte[16];
        for(int i = 0; i < 16; i++)
        {
            byte bits = val[i];
            for(int j = 7; j >= 0; j--)
            {
                if((bits & 1 << j) != 0)
                    xor(c, tmp);
                boolean lsb = (tmp[15] & 1) != 0;
                shiftRight(tmp);
                if(lsb)
                    tmp[0] ^= 0xe1;
            }

        }

        System.arraycopy(c, 0, block, 0, 16);
    }

    private static void shiftRight(byte block[])
    {
        int i = 0;
        int bit = 0;
        do
        {
            int b = block[i] & 0xff;
            block[i] = (byte)(b >>> 1 | bit);
            if(++i != 16)
                bit = (b & 1) << 7;
            else
                return;
        } while(true);
    }

    private static void xor(byte block[], byte val[])
    {
        for(int i = 15; i >= 0; i--)
            block[i] ^= val[i];

    }

    private static void xor(byte block[], byte val[], int off, int len)
    {
        while(len-- > 0) 
            block[len] ^= val[off + len];
    }

    private static final int BLOCK_SIZE = 16;
    private BlockCipher cipher;
    private GCMMultiplier multiplier;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private int macSize;
    private byte nonce[];
    private byte initialAssociatedText[];
    private byte H[];
    private byte J0[];
    private byte bufBlock[];
    private byte macBlock[];
    private byte S[];
    private byte S_at[];
    private byte S_atPre[];
    private byte counter[];
    private int bufOff;
    private long totalLength;
    private byte atBlock[];
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
}
