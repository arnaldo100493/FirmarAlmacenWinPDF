// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCBBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.Arrays;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.modes:
//            AEADBlockCipher

public class OCBBlockCipher
    implements AEADBlockCipher
{

    public OCBBlockCipher(BlockCipher hashCipher, BlockCipher mainCipher)
    {
        if(hashCipher == null)
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        if(hashCipher.getBlockSize() != 16)
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        if(mainCipher == null)
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        if(mainCipher.getBlockSize() != 16)
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        if(!hashCipher.getAlgorithmName().equals(mainCipher.getAlgorithmName()))
        {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        } else
        {
            this.hashCipher = hashCipher;
            this.mainCipher = mainCipher;
            return;
        }
    }

    public BlockCipher getUnderlyingCipher()
    {
        return mainCipher;
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(mainCipher.getAlgorithmName()).append("/OCB").toString();
    }

    public void init(boolean forEncryption, CipherParameters parameters)
        throws IllegalArgumentException
    {
        this.forEncryption = forEncryption;
        macBlock = null;
        KeyParameter keyParameter;
        byte N[];
        if(parameters instanceof AEADParameters)
        {
            AEADParameters aeadParameters = (AEADParameters)parameters;
            N = aeadParameters.getNonce();
            initialAssociatedText = aeadParameters.getAssociatedText();
            int macSizeBits = aeadParameters.getMacSize();
            if(macSizeBits < 64 || macSizeBits > 128 || macSizeBits % 8 != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid value for MAC size: ").append(macSizeBits).toString());
            macSize = macSizeBits / 8;
            keyParameter = aeadParameters.getKey();
        } else
        if(parameters instanceof ParametersWithIV)
        {
            ParametersWithIV parametersWithIV = (ParametersWithIV)parameters;
            N = parametersWithIV.getIV();
            initialAssociatedText = null;
            macSize = 16;
            keyParameter = (KeyParameter)parametersWithIV.getParameters();
        } else
        {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        }
        hashBlock = new byte[16];
        mainBlock = new byte[forEncryption ? 16 : 16 + macSize];
        if(N == null)
            N = new byte[0];
        if(N.length > 16 || N.length == 16 && (N[0] & 0x80) != 0)
            throw new IllegalArgumentException("IV must be no more than 127 bits");
        if(keyParameter == null);
        hashCipher.init(true, keyParameter);
        mainCipher.init(forEncryption, keyParameter);
        L_Asterisk = new byte[16];
        hashCipher.processBlock(L_Asterisk, 0, L_Asterisk, 0);
        L_Dollar = OCB_double(L_Asterisk);
        L = new Vector();
        L.addElement(OCB_double(L_Dollar));
        byte nonce[] = new byte[16];
        System.arraycopy(N, 0, nonce, nonce.length - N.length, N.length);
        if(N.length == 16)
            nonce[0] &= 0x80;
        else
            nonce[15 - N.length] = 1;
        int bottom = nonce[15] & 0x3f;
        byte Ktop[] = new byte[16];
        nonce[15] &= 0xc0;
        hashCipher.processBlock(nonce, 0, Ktop, 0);
        byte Stretch[] = new byte[24];
        System.arraycopy(Ktop, 0, Stretch, 0, 16);
        for(int i = 0; i < 8; i++)
            Stretch[16 + i] = (byte)(Ktop[i] ^ Ktop[i + 1]);

        OffsetMAIN_0 = new byte[16];
        int bits = bottom % 8;
        int bytes = bottom / 8;
        if(bits == 0)
        {
            System.arraycopy(Stretch, bytes, OffsetMAIN_0, 0, 16);
        } else
        {
            for(int i = 0; i < 16; i++)
            {
                int b1 = Stretch[bytes] & 0xff;
                int b2 = Stretch[++bytes] & 0xff;
                OffsetMAIN_0[i] = (byte)(b1 << bits | b2 >>> 8 - bits);
            }

        }
        hashBlockPos = 0;
        mainBlockPos = 0;
        hashBlockCount = 0L;
        mainBlockCount = 0L;
        OffsetHASH = new byte[16];
        Sum = new byte[16];
        OffsetMAIN = Arrays.clone(OffsetMAIN_0);
        Checksum = new byte[16];
        if(initialAssociatedText != null)
            processAADBytes(initialAssociatedText, 0, initialAssociatedText.length);
    }

    public byte[] getMac()
    {
        return Arrays.clone(macBlock);
    }

    public int getOutputSize(int len)
    {
        int totalData = len + mainBlockPos;
        if(forEncryption)
            return totalData + macSize;
        else
            return totalData >= macSize ? totalData - macSize : 0;
    }

    public int getUpdateOutputSize(int len)
    {
        int totalData = len + mainBlockPos;
        if(!forEncryption)
        {
            if(totalData < macSize)
                return 0;
            totalData -= macSize;
        }
        return totalData - totalData % 16;
    }

    public void processAADByte(byte input)
    {
        hashBlock[hashBlockPos] = input;
        if(++hashBlockPos == hashBlock.length)
            processHashBlock();
    }

    public void processAADBytes(byte input[], int off, int len)
    {
        for(int i = 0; i < len; i++)
        {
            hashBlock[hashBlockPos] = input[off + i];
            if(++hashBlockPos == hashBlock.length)
                processHashBlock();
        }

    }

    public int processByte(byte input, byte output[], int outOff)
        throws DataLengthException
    {
        mainBlock[mainBlockPos] = input;
        if(++mainBlockPos == mainBlock.length)
        {
            processMainBlock(output, outOff);
            return 16;
        } else
        {
            return 0;
        }
    }

    public int processBytes(byte input[], int inOff, int len, byte output[], int outOff)
        throws DataLengthException
    {
        int resultLen = 0;
        for(int i = 0; i < len; i++)
        {
            mainBlock[mainBlockPos] = input[inOff + i];
            if(++mainBlockPos == mainBlock.length)
            {
                processMainBlock(output, outOff + resultLen);
                resultLen += 16;
            }
        }

        return resultLen;
    }

    public int doFinal(byte output[], int outOff)
        throws IllegalStateException, InvalidCipherTextException
    {
        byte tag[] = null;
        if(!forEncryption)
        {
            if(mainBlockPos < macSize)
                throw new InvalidCipherTextException("data too short");
            mainBlockPos -= macSize;
            tag = new byte[macSize];
            System.arraycopy(mainBlock, mainBlockPos, tag, 0, macSize);
        }
        if(hashBlockPos > 0)
        {
            OCB_extend(hashBlock, hashBlockPos);
            updateHASH(L_Asterisk);
        }
        if(mainBlockPos > 0)
        {
            if(forEncryption)
            {
                OCB_extend(mainBlock, mainBlockPos);
                xor(Checksum, mainBlock);
            }
            xor(OffsetMAIN, L_Asterisk);
            byte Pad[] = new byte[16];
            hashCipher.processBlock(OffsetMAIN, 0, Pad, 0);
            xor(mainBlock, Pad);
            System.arraycopy(mainBlock, 0, output, outOff, mainBlockPos);
            if(!forEncryption)
            {
                OCB_extend(mainBlock, mainBlockPos);
                xor(Checksum, mainBlock);
            }
        }
        xor(Checksum, OffsetMAIN);
        xor(Checksum, L_Dollar);
        hashCipher.processBlock(Checksum, 0, Checksum, 0);
        xor(Checksum, Sum);
        macBlock = new byte[macSize];
        System.arraycopy(Checksum, 0, macBlock, 0, macSize);
        int resultLen = mainBlockPos;
        if(forEncryption)
        {
            System.arraycopy(macBlock, 0, output, outOff + resultLen, macSize);
            resultLen += macSize;
        } else
        if(!Arrays.constantTimeAreEqual(macBlock, tag))
            throw new InvalidCipherTextException("mac check in OCB failed");
        reset(false);
        return resultLen;
    }

    public void reset()
    {
        reset(true);
    }

    protected void clear(byte bs[])
    {
        if(bs != null)
            Arrays.fill(bs, (byte)0);
    }

    protected byte[] getLSub(int n)
    {
        for(; n >= L.size(); L.addElement(OCB_double((byte[])(byte[])L.lastElement())));
        return (byte[])(byte[])L.elementAt(n);
    }

    protected void processHashBlock()
    {
        updateHASH(getLSub(OCB_ntz(++hashBlockCount)));
        hashBlockPos = 0;
    }

    protected void processMainBlock(byte output[], int outOff)
    {
        if(forEncryption)
        {
            xor(Checksum, mainBlock);
            mainBlockPos = 0;
        }
        xor(OffsetMAIN, getLSub(OCB_ntz(++mainBlockCount)));
        xor(mainBlock, OffsetMAIN);
        mainCipher.processBlock(mainBlock, 0, mainBlock, 0);
        xor(mainBlock, OffsetMAIN);
        System.arraycopy(mainBlock, 0, output, outOff, 16);
        if(!forEncryption)
        {
            xor(Checksum, mainBlock);
            System.arraycopy(mainBlock, 16, mainBlock, 0, macSize);
            mainBlockPos = macSize;
        }
    }

    protected void reset(boolean clearMac)
    {
        hashCipher.reset();
        mainCipher.reset();
        clear(hashBlock);
        clear(mainBlock);
        hashBlockPos = 0;
        mainBlockPos = 0;
        hashBlockCount = 0L;
        mainBlockCount = 0L;
        clear(OffsetHASH);
        clear(Sum);
        System.arraycopy(OffsetMAIN_0, 0, OffsetMAIN, 0, 16);
        clear(Checksum);
        if(clearMac)
            macBlock = null;
        if(initialAssociatedText != null)
            processAADBytes(initialAssociatedText, 0, initialAssociatedText.length);
    }

    protected void updateHASH(byte LSub[])
    {
        xor(OffsetHASH, LSub);
        xor(hashBlock, OffsetHASH);
        hashCipher.processBlock(hashBlock, 0, hashBlock, 0);
        xor(Sum, hashBlock);
    }

    protected static byte[] OCB_double(byte block[])
    {
        byte result[] = new byte[16];
        int carry = shiftLeft(block, result);
        result[15] ^= 135 >>> (1 - carry << 3);
        return result;
    }

    protected static void OCB_extend(byte block[], int pos)
    {
        for(block[pos] = -128; ++pos < 16; block[pos] = 0);
    }

    protected static int OCB_ntz(long x)
    {
        if(x == 0L)
            return 64;
        int n = 0;
        for(; (x & 1L) == 0L; x >>= 1)
            n++;

        return n;
    }

    protected static int shiftLeft(byte block[], byte output[])
    {
        int i = 16;
        int bit;
        int b;
        for(bit = 0; --i >= 0; bit = b >>> 7 & 1)
        {
            b = block[i] & 0xff;
            output[i] = (byte)(b << 1 | bit);
        }

        return bit;
    }

    protected static void xor(byte block[], byte val[])
    {
        for(int i = 15; i >= 0; i--)
            block[i] ^= val[i];

    }

    private static final int BLOCK_SIZE = 16;
    private BlockCipher hashCipher;
    private BlockCipher mainCipher;
    private boolean forEncryption;
    private int macSize;
    private byte initialAssociatedText[];
    private Vector L;
    private byte L_Asterisk[];
    private byte L_Dollar[];
    private byte OffsetMAIN_0[];
    private byte hashBlock[];
    private byte mainBlock[];
    private int hashBlockPos;
    private int mainBlockPos;
    private long hashBlockCount;
    private long mainBlockCount;
    private byte OffsetHASH[];
    private byte Sum[];
    private byte OffsetMAIN[];
    private byte Checksum[];
    private byte macBlock[];
}
