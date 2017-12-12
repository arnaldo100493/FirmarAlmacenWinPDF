// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WhirlpoolDigest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Memoable;

public final class WhirlpoolDigest
    implements ExtendedDigest, Memoable
{

    public WhirlpoolDigest()
    {
        _rc = new long[11];
        _buffer = new byte[64];
        _bufferPos = 0;
        _bitCount = new short[32];
        _hash = new long[8];
        _K = new long[8];
        _L = new long[8];
        _block = new long[8];
        _state = new long[8];
        for(int i = 0; i < 256; i++)
        {
            int v1 = SBOX[i];
            int v2 = maskWithReductionPolynomial(v1 << 1);
            int v4 = maskWithReductionPolynomial(v2 << 1);
            int v5 = v4 ^ v1;
            int v8 = maskWithReductionPolynomial(v4 << 1);
            int v9 = v8 ^ v1;
            C0[i] = packIntoLong(v1, v1, v4, v1, v8, v5, v2, v9);
            C1[i] = packIntoLong(v9, v1, v1, v4, v1, v8, v5, v2);
            C2[i] = packIntoLong(v2, v9, v1, v1, v4, v1, v8, v5);
            C3[i] = packIntoLong(v5, v2, v9, v1, v1, v4, v1, v8);
            C4[i] = packIntoLong(v8, v5, v2, v9, v1, v1, v4, v1);
            C5[i] = packIntoLong(v1, v8, v5, v2, v9, v1, v1, v4);
            C6[i] = packIntoLong(v4, v1, v8, v5, v2, v9, v1, v1);
            C7[i] = packIntoLong(v1, v4, v1, v8, v5, v2, v9, v1);
        }

        _rc[0] = 0L;
        for(int r = 1; r <= 10; r++)
        {
            int i = 8 * (r - 1);
            _rc[r] = C0[i] & 0xff00000000000000L ^ C1[i + 1] & 0xff000000000000L ^ C2[i + 2] & 0xff0000000000L ^ C3[i + 3] & 0xff00000000L ^ C4[i + 4] & 0xff000000L ^ C5[i + 5] & 0xff0000L ^ C6[i + 6] & 65280L ^ C7[i + 7] & 255L;
        }

    }

    private long packIntoLong(int b7, int b6, int b5, int b4, int b3, int b2, int b1, 
            int b0)
    {
        return (long)b7 << 56 ^ (long)b6 << 48 ^ (long)b5 << 40 ^ (long)b4 << 32 ^ (long)b3 << 24 ^ (long)b2 << 16 ^ (long)b1 << 8 ^ (long)b0;
    }

    private int maskWithReductionPolynomial(int input)
    {
        int rv = input;
        if((long)rv >= 256L)
            rv ^= 0x11d;
        return rv;
    }

    public WhirlpoolDigest(WhirlpoolDigest originalDigest)
    {
        _rc = new long[11];
        _buffer = new byte[64];
        _bufferPos = 0;
        _bitCount = new short[32];
        _hash = new long[8];
        _K = new long[8];
        _L = new long[8];
        _block = new long[8];
        _state = new long[8];
        reset(originalDigest);
    }

    public String getAlgorithmName()
    {
        return "Whirlpool";
    }

    public int getDigestSize()
    {
        return 64;
    }

    public int doFinal(byte out[], int outOff)
    {
        finish();
        for(int i = 0; i < 8; i++)
            convertLongToByteArray(_hash[i], out, outOff + i * 8);

        reset();
        return getDigestSize();
    }

    public void reset()
    {
        _bufferPos = 0;
        Arrays.fill(_bitCount, (short)0);
        Arrays.fill(_buffer, (byte)0);
        Arrays.fill(_hash, 0L);
        Arrays.fill(_K, 0L);
        Arrays.fill(_L, 0L);
        Arrays.fill(_block, 0L);
        Arrays.fill(_state, 0L);
    }

    private void processFilledBuffer(byte in[], int inOff)
    {
        for(int i = 0; i < _state.length; i++)
            _block[i] = bytesToLongFromBuffer(_buffer, i * 8);

        processBlock();
        _bufferPos = 0;
        Arrays.fill(_buffer, (byte)0);
    }

    private long bytesToLongFromBuffer(byte buffer[], int startPos)
    {
        long rv = ((long)buffer[startPos + 0] & 255L) << 56 | ((long)buffer[startPos + 1] & 255L) << 48 | ((long)buffer[startPos + 2] & 255L) << 40 | ((long)buffer[startPos + 3] & 255L) << 32 | ((long)buffer[startPos + 4] & 255L) << 24 | ((long)buffer[startPos + 5] & 255L) << 16 | ((long)buffer[startPos + 6] & 255L) << 8 | (long)buffer[startPos + 7] & 255L;
        return rv;
    }

    private void convertLongToByteArray(long inputLong, byte outputArray[], int offSet)
    {
        for(int i = 0; i < 8; i++)
            outputArray[offSet + i] = (byte)(int)(inputLong >> 56 - i * 8 & 255L);

    }

    protected void processBlock()
    {
        for(int i = 0; i < 8; i++)
            _state[i] = _block[i] ^ (_K[i] = _hash[i]);

        for(int round = 1; round <= 10; round++)
        {
            for(int i = 0; i < 8; i++)
            {
                _L[i] = 0L;
                _L[i] ^= C0[(int)(_K[i - 0 & 7] >>> 56) & 0xff];
                _L[i] ^= C1[(int)(_K[i - 1 & 7] >>> 48) & 0xff];
                _L[i] ^= C2[(int)(_K[i - 2 & 7] >>> 40) & 0xff];
                _L[i] ^= C3[(int)(_K[i - 3 & 7] >>> 32) & 0xff];
                _L[i] ^= C4[(int)(_K[i - 4 & 7] >>> 24) & 0xff];
                _L[i] ^= C5[(int)(_K[i - 5 & 7] >>> 16) & 0xff];
                _L[i] ^= C6[(int)(_K[i - 6 & 7] >>> 8) & 0xff];
                _L[i] ^= C7[(int)_K[i - 7 & 7] & 0xff];
            }

            System.arraycopy(_L, 0, _K, 0, _K.length);
            _K[0] ^= _rc[round];
            for(int i = 0; i < 8; i++)
            {
                _L[i] = _K[i];
                _L[i] ^= C0[(int)(_state[i - 0 & 7] >>> 56) & 0xff];
                _L[i] ^= C1[(int)(_state[i - 1 & 7] >>> 48) & 0xff];
                _L[i] ^= C2[(int)(_state[i - 2 & 7] >>> 40) & 0xff];
                _L[i] ^= C3[(int)(_state[i - 3 & 7] >>> 32) & 0xff];
                _L[i] ^= C4[(int)(_state[i - 4 & 7] >>> 24) & 0xff];
                _L[i] ^= C5[(int)(_state[i - 5 & 7] >>> 16) & 0xff];
                _L[i] ^= C6[(int)(_state[i - 6 & 7] >>> 8) & 0xff];
                _L[i] ^= C7[(int)_state[i - 7 & 7] & 0xff];
            }

            System.arraycopy(_L, 0, _state, 0, _state.length);
        }

        for(int i = 0; i < 8; i++)
            _hash[i] ^= _state[i] ^ _block[i];

    }

    public void update(byte in)
    {
        _buffer[_bufferPos] = in;
        _bufferPos++;
        if(_bufferPos == _buffer.length)
            processFilledBuffer(_buffer, 0);
        increment();
    }

    private void increment()
    {
        int carry = 0;
        for(int i = _bitCount.length - 1; i >= 0; i--)
        {
            int sum = (_bitCount[i] & 0xff) + EIGHT[i] + carry;
            carry = sum >>> 8;
            _bitCount[i] = (short)(sum & 0xff);
        }

    }

    public void update(byte in[], int inOff, int len)
    {
        for(; len > 0; len--)
        {
            update(in[inOff]);
            inOff++;
        }

    }

    private void finish()
    {
        byte bitLength[] = copyBitLength();
        _buffer[_bufferPos++] |= 0x80;
        if(_bufferPos == _buffer.length)
            processFilledBuffer(_buffer, 0);
        if(_bufferPos > 32)
            while(_bufferPos != 0) 
                update((byte)0);
        while(_bufferPos <= 32) 
            update((byte)0);
        System.arraycopy(bitLength, 0, _buffer, 32, bitLength.length);
        processFilledBuffer(_buffer, 0);
    }

    private byte[] copyBitLength()
    {
        byte rv[] = new byte[32];
        for(int i = 0; i < rv.length; i++)
            rv[i] = (byte)(_bitCount[i] & 0xff);

        return rv;
    }

    public int getByteLength()
    {
        return 64;
    }

    public Memoable copy()
    {
        return new WhirlpoolDigest(this);
    }

    public void reset(Memoable other)
    {
        WhirlpoolDigest originalDigest = (WhirlpoolDigest)other;
        System.arraycopy(originalDigest._rc, 0, _rc, 0, _rc.length);
        System.arraycopy(originalDigest._buffer, 0, _buffer, 0, _buffer.length);
        _bufferPos = originalDigest._bufferPos;
        System.arraycopy(originalDigest._bitCount, 0, _bitCount, 0, _bitCount.length);
        System.arraycopy(originalDigest._hash, 0, _hash, 0, _hash.length);
        System.arraycopy(originalDigest._K, 0, _K, 0, _K.length);
        System.arraycopy(originalDigest._L, 0, _L, 0, _L.length);
        System.arraycopy(originalDigest._block, 0, _block, 0, _block.length);
        System.arraycopy(originalDigest._state, 0, _state, 0, _state.length);
    }

    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final int ROUNDS = 10;
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int SBOX[] = {
        24, 35, 198, 232, 135, 184, 1, 79, 54, 166, 
        210, 245, 121, 111, 145, 82, 96, 188, 155, 142, 
        163, 12, 123, 53, 29, 224, 215, 194, 46, 75, 
        254, 87, 21, 119, 55, 229, 159, 240, 74, 218, 
        88, 201, 41, 10, 177, 160, 107, 133, 189, 93, 
        16, 244, 203, 62, 5, 103, 228, 39, 65, 139, 
        167, 125, 149, 216, 251, 238, 124, 102, 221, 23, 
        71, 158, 202, 45, 191, 7, 173, 90, 131, 51, 
        99, 2, 170, 113, 200, 25, 73, 217, 242, 227, 
        91, 136, 154, 38, 50, 176, 233, 15, 213, 128, 
        190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 
        26, 174, 180, 84, 147, 34, 100, 241, 115, 18, 
        64, 8, 195, 236, 219, 161, 141, 61, 151, 0, 
        207, 43, 118, 130, 214, 27, 181, 175, 106, 80, 
        69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 
        47, 192, 222, 28, 253, 77, 146, 117, 6, 138, 
        178, 230, 14, 31, 98, 212, 168, 150, 249, 197, 
        37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 
        209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 
        252, 4, 81, 153, 109, 13, 250, 223, 126, 36, 
        59, 171, 206, 17, 143, 78, 183, 235, 60, 129, 
        148, 247, 185, 19, 44, 211, 231, 110, 196, 3, 
        86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 
        157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 
        22, 58, 105, 9, 112, 182, 208, 237, 204, 66, 
        152, 164, 40, 92, 248, 134
    };
    private static final long C0[] = new long[256];
    private static final long C1[] = new long[256];
    private static final long C2[] = new long[256];
    private static final long C3[] = new long[256];
    private static final long C4[] = new long[256];
    private static final long C5[] = new long[256];
    private static final long C6[] = new long[256];
    private static final long C7[] = new long[256];
    private final long _rc[];
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private byte _buffer[];
    private int _bufferPos;
    private short _bitCount[];
    private long _hash[];
    private long _K[];
    private long _L[];
    private long _block[];
    private long _state[];
    private static final short EIGHT[];

    static 
    {
        EIGHT = new short[32];
        EIGHT[31] = 8;
    }
}
