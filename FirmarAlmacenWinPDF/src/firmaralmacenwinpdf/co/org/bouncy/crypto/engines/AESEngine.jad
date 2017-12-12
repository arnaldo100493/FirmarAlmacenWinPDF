// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AESEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;

public class AESEngine
    implements BlockCipher
{

    private static int shift(int r, int shift)
    {
        return r >>> shift | r << -shift;
    }

    private static int FFmulX(int x)
    {
        return (x & 0x7f7f7f7f) << 1 ^ ((x & 0x80808080) >>> 7) * 27;
    }

    private static int inv_mcol(int x)
    {
        int f2 = FFmulX(x);
        int f4 = FFmulX(f2);
        int f8 = FFmulX(f4);
        int f9 = x ^ f8;
        return f2 ^ f4 ^ f8 ^ shift(f2 ^ f9, 8) ^ shift(f4 ^ f9, 16) ^ shift(f9, 24);
    }

    private static int subWord(int x)
    {
        return S[x & 0xff] & 0xff | (S[x >> 8 & 0xff] & 0xff) << 8 | (S[x >> 16 & 0xff] & 0xff) << 16 | S[x >> 24 & 0xff] << 24;
    }

    private int[][] generateWorkingKey(byte key[], boolean forEncryption)
    {
        int KC = key.length / 4;
        if(KC != 4 && KC != 6 && KC != 8 || KC * 4 != key.length)
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        ROUNDS = KC + 6;
        int W[][] = new int[ROUNDS + 1][4];
        int t = 0;
        for(int i = 0; i < key.length;)
        {
            W[t >> 2][t & 3] = key[i] & 0xff | (key[i + 1] & 0xff) << 8 | (key[i + 2] & 0xff) << 16 | key[i + 3] << 24;
            i += 4;
            t++;
        }

        int k = ROUNDS + 1 << 2;
        for(int i = KC; i < k; i++)
        {
            int temp = W[i - 1 >> 2][i - 1 & 3];
            if(i % KC == 0)
                temp = subWord(shift(temp, 8)) ^ rcon[i / KC - 1];
            else
            if(KC > 6 && i % KC == 4)
                temp = subWord(temp);
            W[i >> 2][i & 3] = W[i - KC >> 2][i - KC & 3] ^ temp;
        }

        if(!forEncryption)
        {
            for(int j = 1; j < ROUNDS; j++)
            {
                for(int i = 0; i < 4; i++)
                    W[j][i] = inv_mcol(W[j][i]);

            }

        }
        return W;
    }

    public AESEngine()
    {
        WorkingKey = (int[][])null;
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        if(params instanceof KeyParameter)
        {
            WorkingKey = generateWorkingKey(((KeyParameter)params).getKey(), forEncryption);
            this.forEncryption = forEncryption;
            return;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid parameter passed to AES init - ").append(params.getClass().getName()).toString());
        }
    }

    public String getAlgorithmName()
    {
        return "AES";
    }

    public int getBlockSize()
    {
        return 16;
    }

    public int processBlock(byte in[], int inOff, byte out[], int outOff)
    {
        if(WorkingKey == null)
            throw new IllegalStateException("AES engine not initialised");
        if(inOff + 16 > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + 16 > out.length)
            throw new OutputLengthException("output buffer too short");
        if(forEncryption)
        {
            unpackBlock(in, inOff);
            encryptBlock(WorkingKey);
            packBlock(out, outOff);
        } else
        {
            unpackBlock(in, inOff);
            decryptBlock(WorkingKey);
            packBlock(out, outOff);
        }
        return 16;
    }

    public void reset()
    {
    }

    private void unpackBlock(byte bytes[], int off)
    {
        int index = off;
        C0 = bytes[index++] & 0xff;
        C0 |= (bytes[index++] & 0xff) << 8;
        C0 |= (bytes[index++] & 0xff) << 16;
        C0 |= bytes[index++] << 24;
        C1 = bytes[index++] & 0xff;
        C1 |= (bytes[index++] & 0xff) << 8;
        C1 |= (bytes[index++] & 0xff) << 16;
        C1 |= bytes[index++] << 24;
        C2 = bytes[index++] & 0xff;
        C2 |= (bytes[index++] & 0xff) << 8;
        C2 |= (bytes[index++] & 0xff) << 16;
        C2 |= bytes[index++] << 24;
        C3 = bytes[index++] & 0xff;
        C3 |= (bytes[index++] & 0xff) << 8;
        C3 |= (bytes[index++] & 0xff) << 16;
        C3 |= bytes[index++] << 24;
    }

    private void packBlock(byte bytes[], int off)
    {
        int index = off;
        bytes[index++] = (byte)C0;
        bytes[index++] = (byte)(C0 >> 8);
        bytes[index++] = (byte)(C0 >> 16);
        bytes[index++] = (byte)(C0 >> 24);
        bytes[index++] = (byte)C1;
        bytes[index++] = (byte)(C1 >> 8);
        bytes[index++] = (byte)(C1 >> 16);
        bytes[index++] = (byte)(C1 >> 24);
        bytes[index++] = (byte)C2;
        bytes[index++] = (byte)(C2 >> 8);
        bytes[index++] = (byte)(C2 >> 16);
        bytes[index++] = (byte)(C2 >> 24);
        bytes[index++] = (byte)C3;
        bytes[index++] = (byte)(C3 >> 8);
        bytes[index++] = (byte)(C3 >> 16);
        bytes[index++] = (byte)(C3 >> 24);
    }

    private void encryptBlock(int KW[][])
    {
        C0 ^= KW[0][0];
        C1 ^= KW[0][1];
        C2 ^= KW[0][2];
        C3 ^= KW[0][3];
        int r;
        int r0;
        int r1;
        int r2;
        int r3;
        for(r = 1; r < ROUNDS - 1;)
        {
            r0 = T0[C0 & 0xff] ^ shift(T0[C1 >> 8 & 0xff], 24) ^ shift(T0[C2 >> 16 & 0xff], 16) ^ shift(T0[C3 >> 24 & 0xff], 8) ^ KW[r][0];
            r1 = T0[C1 & 0xff] ^ shift(T0[C2 >> 8 & 0xff], 24) ^ shift(T0[C3 >> 16 & 0xff], 16) ^ shift(T0[C0 >> 24 & 0xff], 8) ^ KW[r][1];
            r2 = T0[C2 & 0xff] ^ shift(T0[C3 >> 8 & 0xff], 24) ^ shift(T0[C0 >> 16 & 0xff], 16) ^ shift(T0[C1 >> 24 & 0xff], 8) ^ KW[r][2];
            r3 = T0[C3 & 0xff] ^ shift(T0[C0 >> 8 & 0xff], 24) ^ shift(T0[C1 >> 16 & 0xff], 16) ^ shift(T0[C2 >> 24 & 0xff], 8) ^ KW[r++][3];
            C0 = T0[r0 & 0xff] ^ shift(T0[r1 >> 8 & 0xff], 24) ^ shift(T0[r2 >> 16 & 0xff], 16) ^ shift(T0[r3 >> 24 & 0xff], 8) ^ KW[r][0];
            C1 = T0[r1 & 0xff] ^ shift(T0[r2 >> 8 & 0xff], 24) ^ shift(T0[r3 >> 16 & 0xff], 16) ^ shift(T0[r0 >> 24 & 0xff], 8) ^ KW[r][1];
            C2 = T0[r2 & 0xff] ^ shift(T0[r3 >> 8 & 0xff], 24) ^ shift(T0[r0 >> 16 & 0xff], 16) ^ shift(T0[r1 >> 24 & 0xff], 8) ^ KW[r][2];
            C3 = T0[r3 & 0xff] ^ shift(T0[r0 >> 8 & 0xff], 24) ^ shift(T0[r1 >> 16 & 0xff], 16) ^ shift(T0[r2 >> 24 & 0xff], 8) ^ KW[r++][3];
        }

        r0 = T0[C0 & 0xff] ^ shift(T0[C1 >> 8 & 0xff], 24) ^ shift(T0[C2 >> 16 & 0xff], 16) ^ shift(T0[C3 >> 24 & 0xff], 8) ^ KW[r][0];
        r1 = T0[C1 & 0xff] ^ shift(T0[C2 >> 8 & 0xff], 24) ^ shift(T0[C3 >> 16 & 0xff], 16) ^ shift(T0[C0 >> 24 & 0xff], 8) ^ KW[r][1];
        r2 = T0[C2 & 0xff] ^ shift(T0[C3 >> 8 & 0xff], 24) ^ shift(T0[C0 >> 16 & 0xff], 16) ^ shift(T0[C1 >> 24 & 0xff], 8) ^ KW[r][2];
        r3 = T0[C3 & 0xff] ^ shift(T0[C0 >> 8 & 0xff], 24) ^ shift(T0[C1 >> 16 & 0xff], 16) ^ shift(T0[C2 >> 24 & 0xff], 8) ^ KW[r++][3];
        C0 = S[r0 & 0xff] & 0xff ^ (S[r1 >> 8 & 0xff] & 0xff) << 8 ^ (S[r2 >> 16 & 0xff] & 0xff) << 16 ^ S[r3 >> 24 & 0xff] << 24 ^ KW[r][0];
        C1 = S[r1 & 0xff] & 0xff ^ (S[r2 >> 8 & 0xff] & 0xff) << 8 ^ (S[r3 >> 16 & 0xff] & 0xff) << 16 ^ S[r0 >> 24 & 0xff] << 24 ^ KW[r][1];
        C2 = S[r2 & 0xff] & 0xff ^ (S[r3 >> 8 & 0xff] & 0xff) << 8 ^ (S[r0 >> 16 & 0xff] & 0xff) << 16 ^ S[r1 >> 24 & 0xff] << 24 ^ KW[r][2];
        C3 = S[r3 & 0xff] & 0xff ^ (S[r0 >> 8 & 0xff] & 0xff) << 8 ^ (S[r1 >> 16 & 0xff] & 0xff) << 16 ^ S[r2 >> 24 & 0xff] << 24 ^ KW[r][3];
    }

    private void decryptBlock(int KW[][])
    {
        C0 ^= KW[ROUNDS][0];
        C1 ^= KW[ROUNDS][1];
        C2 ^= KW[ROUNDS][2];
        C3 ^= KW[ROUNDS][3];
        int r;
        int r0;
        int r1;
        int r2;
        int r3;
        for(r = ROUNDS - 1; r > 1;)
        {
            r0 = Tinv0[C0 & 0xff] ^ shift(Tinv0[C3 >> 8 & 0xff], 24) ^ shift(Tinv0[C2 >> 16 & 0xff], 16) ^ shift(Tinv0[C1 >> 24 & 0xff], 8) ^ KW[r][0];
            r1 = Tinv0[C1 & 0xff] ^ shift(Tinv0[C0 >> 8 & 0xff], 24) ^ shift(Tinv0[C3 >> 16 & 0xff], 16) ^ shift(Tinv0[C2 >> 24 & 0xff], 8) ^ KW[r][1];
            r2 = Tinv0[C2 & 0xff] ^ shift(Tinv0[C1 >> 8 & 0xff], 24) ^ shift(Tinv0[C0 >> 16 & 0xff], 16) ^ shift(Tinv0[C3 >> 24 & 0xff], 8) ^ KW[r][2];
            r3 = Tinv0[C3 & 0xff] ^ shift(Tinv0[C2 >> 8 & 0xff], 24) ^ shift(Tinv0[C1 >> 16 & 0xff], 16) ^ shift(Tinv0[C0 >> 24 & 0xff], 8) ^ KW[r--][3];
            C0 = Tinv0[r0 & 0xff] ^ shift(Tinv0[r3 >> 8 & 0xff], 24) ^ shift(Tinv0[r2 >> 16 & 0xff], 16) ^ shift(Tinv0[r1 >> 24 & 0xff], 8) ^ KW[r][0];
            C1 = Tinv0[r1 & 0xff] ^ shift(Tinv0[r0 >> 8 & 0xff], 24) ^ shift(Tinv0[r3 >> 16 & 0xff], 16) ^ shift(Tinv0[r2 >> 24 & 0xff], 8) ^ KW[r][1];
            C2 = Tinv0[r2 & 0xff] ^ shift(Tinv0[r1 >> 8 & 0xff], 24) ^ shift(Tinv0[r0 >> 16 & 0xff], 16) ^ shift(Tinv0[r3 >> 24 & 0xff], 8) ^ KW[r][2];
            C3 = Tinv0[r3 & 0xff] ^ shift(Tinv0[r2 >> 8 & 0xff], 24) ^ shift(Tinv0[r1 >> 16 & 0xff], 16) ^ shift(Tinv0[r0 >> 24 & 0xff], 8) ^ KW[r--][3];
        }

        r0 = Tinv0[C0 & 0xff] ^ shift(Tinv0[C3 >> 8 & 0xff], 24) ^ shift(Tinv0[C2 >> 16 & 0xff], 16) ^ shift(Tinv0[C1 >> 24 & 0xff], 8) ^ KW[r][0];
        r1 = Tinv0[C1 & 0xff] ^ shift(Tinv0[C0 >> 8 & 0xff], 24) ^ shift(Tinv0[C3 >> 16 & 0xff], 16) ^ shift(Tinv0[C2 >> 24 & 0xff], 8) ^ KW[r][1];
        r2 = Tinv0[C2 & 0xff] ^ shift(Tinv0[C1 >> 8 & 0xff], 24) ^ shift(Tinv0[C0 >> 16 & 0xff], 16) ^ shift(Tinv0[C3 >> 24 & 0xff], 8) ^ KW[r][2];
        r3 = Tinv0[C3 & 0xff] ^ shift(Tinv0[C2 >> 8 & 0xff], 24) ^ shift(Tinv0[C1 >> 16 & 0xff], 16) ^ shift(Tinv0[C0 >> 24 & 0xff], 8) ^ KW[r][3];
        C0 = Si[r0 & 0xff] & 0xff ^ (Si[r3 >> 8 & 0xff] & 0xff) << 8 ^ (Si[r2 >> 16 & 0xff] & 0xff) << 16 ^ Si[r1 >> 24 & 0xff] << 24 ^ KW[0][0];
        C1 = Si[r1 & 0xff] & 0xff ^ (Si[r0 >> 8 & 0xff] & 0xff) << 8 ^ (Si[r3 >> 16 & 0xff] & 0xff) << 16 ^ Si[r2 >> 24 & 0xff] << 24 ^ KW[0][1];
        C2 = Si[r2 & 0xff] & 0xff ^ (Si[r1 >> 8 & 0xff] & 0xff) << 8 ^ (Si[r0 >> 16 & 0xff] & 0xff) << 16 ^ Si[r3 >> 24 & 0xff] << 24 ^ KW[0][2];
        C3 = Si[r3 & 0xff] & 0xff ^ (Si[r2 >> 8 & 0xff] & 0xff) << 8 ^ (Si[r1 >> 16 & 0xff] & 0xff) << 16 ^ Si[r0 >> 24 & 0xff] << 24 ^ KW[0][3];
    }

    private static final byte S[] = {
        99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 
        103, 43, -2, -41, -85, 118, -54, -126, -55, 125, 
        -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 
        114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 
        52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 
        35, -61, 24, -106, 5, -102, 7, 18, -128, -30, 
        -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 
        90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 
        83, -47, 0, -19, 32, -4, -79, 91, 106, -53, 
        -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 
        67, 77, 51, -123, 69, -7, 2, 127, 80, 60, 
        -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, 
        -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 
        19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 
        100, 93, 25, 115, 96, -127, 79, -36, 34, 42, 
        -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, 
        -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, 
        -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, 
        -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, 
        -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, 
        -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, 
        -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, 
        -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, 
        -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, 
        -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 
        45, 15, -80, 84, -69, 22
    };
    private static final byte Si[] = {
        82, 9, 106, -43, 48, 54, -91, 56, -65, 64, 
        -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, 
        -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, 
        -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, 
        -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, 
        -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 
        109, -117, -47, 37, 114, -8, -10, 100, -122, 104, 
        -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 
        108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 
        70, 87, -89, -115, -99, -124, -112, -40, -85, 0, 
        -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 
        69, 6, -48, 44, 30, -113, -54, 63, 15, 2, 
        -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 
        17, 65, 79, 103, -36, -22, -105, -14, -49, -50, 
        -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 
        53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 
        71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 
        98, 14, -86, 24, -66, 27, -4, 86, 62, 75, 
        -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 
        90, -12, 31, -35, -88, 51, -120, 7, -57, 49, 
        -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 
        127, -87, 25, -75, 74, 13, 45, -27, 122, -97, 
        -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, 
        -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 
        23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 
        20, 99, 85, 33, 12, 125
    };
    private static final int rcon[] = {
        1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 
        108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 
        151, 53, 106, 212, 179, 125, 250, 239, 197, 145
    };
    private static final int T0[] = {
        0xa56363c6, 0x847c7cf8, 0x997777ee, 0x8d7b7bf6, 0xdf2f2ff, 0xbd6b6bd6, 0xb16f6fde, 0x54c5c591, 0x50303060, 0x3010102, 
        0xa96767ce, 0x7d2b2b56, 0x19fefee7, 0x62d7d7b5, 0xe6abab4d, 0x9a7676ec, 0x45caca8f, 0x9d82821f, 0x40c9c989, 0x877d7dfa, 
        0x15fafaef, 0xeb5959b2, 0xc947478e, 0xbf0f0fb, 0xecadad41, 0x67d4d4b3, 0xfda2a25f, 0xeaafaf45, 0xbf9c9c23, 0xf7a4a453, 
        0x967272e4, 0x5bc0c09b, 0xc2b7b775, 0x1cfdfde1, 0xae93933d, 0x6a26264c, 0x5a36366c, 0x413f3f7e, 0x2f7f7f5, 0x4fcccc83, 
        0x5c343468, 0xf4a5a551, 0x34e5e5d1, 0x8f1f1f9, 0x937171e2, 0x73d8d8ab, 0x53313162, 0x3f15152a, 0xc040408, 0x52c7c795, 
        0x65232346, 0x5ec3c39d, 0x28181830, 0xa1969637, 0xf05050a, 0xb59a9a2f, 0x907070e, 0x36121224, 0x9b80801b, 0x3de2e2df, 
        0x26ebebcd, 0x6927274e, 0xcdb2b27f, 0x9f7575ea, 0x1b090912, 0x9e83831d, 0x742c2c58, 0x2e1a1a34, 0x2d1b1b36, 0xb26e6edc, 
        0xee5a5ab4, 0xfba0a05b, 0xf65252a4, 0x4d3b3b76, 0x61d6d6b7, 0xceb3b37d, 0x7b292952, 0x3ee3e3dd, 0x712f2f5e, 0x97848413, 
        0xf55353a6, 0x68d1d1b9, 0, 0x2cededc1, 0x60202040, 0x1ffcfce3, 0xc8b1b179, 0xed5b5bb6, 0xbe6a6ad4, 0x46cbcb8d, 
        0xd9bebe67, 0x4b393972, 0xde4a4a94, 0xd44c4c98, 0xe85858b0, 0x4acfcf85, 0x6bd0d0bb, 0x2aefefc5, 0xe5aaaa4f, 0x16fbfbed, 
        0xc5434386, 0xd74d4d9a, 0x55333366, 0x94858511, 0xcf45458a, 0x10f9f9e9, 0x6020204, 0x817f7ffe, 0xf05050a0, 0x443c3c78, 
        0xba9f9f25, 0xe3a8a84b, 0xf35151a2, 0xfea3a35d, 0xc0404080, 0x8a8f8f05, 0xad92923f, 0xbc9d9d21, 0x48383870, 0x4f5f5f1, 
        0xdfbcbc63, 0xc1b6b677, 0x75dadaaf, 0x63212142, 0x30101020, 0x1affffe5, 0xef3f3fd, 0x6dd2d2bf, 0x4ccdcd81, 0x140c0c18, 
        0x35131326, 0x2fececc3, 0xe15f5fbe, 0xa2979735, 0xcc444488, 0x3917172e, 0x57c4c493, 0xf2a7a755, 0x827e7efc, 0x473d3d7a, 
        0xac6464c8, 0xe75d5dba, 0x2b191932, 0x957373e6, 0xa06060c0, 0x98818119, 0xd14f4f9e, 0x7fdcdca3, 0x66222244, 0x7e2a2a54, 
        0xab90903b, 0x8388880b, 0xca46468c, 0x29eeeec7, 0xd3b8b86b, 0x3c141428, 0x79dedea7, 0xe25e5ebc, 0x1d0b0b16, 0x76dbdbad, 
        0x3be0e0db, 0x56323264, 0x4e3a3a74, 0x1e0a0a14, 0xdb494992, 0xa06060c, 0x6c242448, 0xe45c5cb8, 0x5dc2c29f, 0x6ed3d3bd, 
        0xefacac43, 0xa66262c4, 0xa8919139, 0xa4959531, 0x37e4e4d3, 0x8b7979f2, 0x32e7e7d5, 0x43c8c88b, 0x5937376e, 0xb76d6dda, 
        0x8c8d8d01, 0x64d5d5b1, 0xd24e4e9c, 0xe0a9a949, 0xb46c6cd8, 0xfa5656ac, 0x7f4f4f3, 0x25eaeacf, 0xaf6565ca, 0x8e7a7af4, 
        0xe9aeae47, 0x18080810, 0xd5baba6f, 0x887878f0, 0x6f25254a, 0x722e2e5c, 0x241c1c38, 0xf1a6a657, 0xc7b4b473, 0x51c6c697, 
        0x23e8e8cb, 0x7cdddda1, 0x9c7474e8, 0x211f1f3e, 0xdd4b4b96, 0xdcbdbd61, 0x868b8b0d, 0x858a8a0f, 0x907070e0, 0x423e3e7c, 
        0xc4b5b571, 0xaa6666cc, 0xd8484890, 0x5030306, 0x1f6f6f7, 0x120e0e1c, 0xa36161c2, 0x5f35356a, 0xf95757ae, 0xd0b9b969, 
        0x91868617, 0x58c1c199, 0x271d1d3a, 0xb99e9e27, 0x38e1e1d9, 0x13f8f8eb, 0xb398982b, 0x33111122, 0xbb6969d2, 0x70d9d9a9, 
        0x898e8e07, 0xa7949433, 0xb69b9b2d, 0x221e1e3c, 0x92878715, 0x20e9e9c9, 0x49cece87, 0xff5555aa, 0x78282850, 0x7adfdfa5, 
        0x8f8c8c03, 0xf8a1a159, 0x80898909, 0x170d0d1a, 0xdabfbf65, 0x31e6e6d7, 0xc6424284, 0xb86868d0, 0xc3414182, 0xb0999929, 
        0x772d2d5a, 0x110f0f1e, 0xcbb0b07b, 0xfc5454a8, 0xd6bbbb6d, 0x3a16162c
    };
    private static final int Tinv0[] = {
        0x50a7f451, 0x5365417e, 0xc3a4171a, 0x965e273a, 0xcb6bab3b, 0xf1459d1f, 0xab58faac, 0x9303e34b, 0x55fa3020, 0xf66d76ad, 
        0x9176cc88, 0x254c02f5, 0xfcd7e54f, 0xd7cb2ac5, 0x80443526, 0x8fa362b5, 0x495ab1de, 0x671bba25, 0x980eea45, 0xe1c0fe5d, 
        0x2752fc3, 0x12f04c81, 0xa397468d, 0xc6f9d36b, 0xe75f8f03, 0x959c9215, 0xeb7a6dbf, 0xda595295, 0x2d83bed4, 0xd3217458, 
        0x2969e049, 0x44c8c98e, 0x6a89c275, 0x78798ef4, 0x6b3e5899, 0xdd71b927, 0xb64fe1be, 0x17ad88f0, 0x66ac20c9, 0xb43ace7d, 
        0x184adf63, 0x82311ae5, 0x60335197, 0x457f5362, 0xe07764b1, 0x84ae6bbb, 0x1ca081fe, 0x942b08f9, 0x58684870, 0x19fd458f, 
        0x876cde94, 0xb7f87b52, 0x23d373ab, 0xe2024b72, 0x578f1fe3, 0x2aab5566, 0x728ebb2, 0x3c2b52f, 0x9a7bc586, 0xa50837d3, 
        0xf2872830, 0xb2a5bf23, 0xba6a0302, 0x5c8216ed, 0x2b1ccf8a, 0x92b479a7, 0xf0f207f3, 0xa1e2694e, 0xcdf4da65, 0xd5be0506, 
        0x1f6234d1, 0x8afea6c4, 0x9d532e34, 0xa055f3a2, 0x32e18a05, 0x75ebf6a4, 0x39ec830b, 0xaaef6040, 0x69f715e, 0x51106ebd, 
        0xf98a213e, 0x3d06dd96, 0xae053edd, 0x46bde64d, 0xb58d5491, 0x55dc471, 0x6fd40604, 0xff155060, 0x24fb9819, 0x97e9bdd6, 
        0xcc434089, 0x779ed967, 0xbd42e8b0, 0x888b8907, 0x385b19e7, 0xdbeec879, 0x470a7ca1, 0xe90f427c, 0xc91e84f8, 0, 
        0x83868009, 0x48ed2b32, 0xac70111e, 0x4e725a6c, 0xfbff0efd, 0x5638850f, 0x1ed5ae3d, 0x27392d36, 0x64d90f0a, 0x21a65c68, 
        0xd1545b9b, 0x3a2e3624, 0xb1670a0c, 0xfe75793, 0xd296eeb4, 0x9e919b1b, 0x4fc5c080, 0xa220dc61, 0x694b775a, 0x161a121c, 
        0xaba93e2, 0xe52aa0c0, 0x43e0223c, 0x1d171b12, 0xb0d090e, 0xadc78bf2, 0xb9a8b62d, 0xc8a91e14, 0x8519f157, 0x4c0775af, 
        0xbbdd99ee, 0xfd607fa3, 0x9f2601f7, 0xbcf5725c, 0xc53b6644, 0x347efb5b, 0x7629438b, 0xdcc623cb, 0x68fcedb6, 0x63f1e4b8, 
        0xcadc31d7, 0x10856342, 0x40229713, 0x2011c684, 0x7d244a85, 0xf83dbbd2, 0x1132f9ae, 0x6da129c7, 0x4b2f9e1d, 0xf330b2dc, 
        0xec52860d, 0xd0e3c177, 0x6c16b32b, 0x99b970a9, 0xfa489411, 0x2264e947, 0xc48cfca8, 0x1a3ff0a0, 0xd82c7d56, 0xef903322, 
        0xc74e4987, 0xc1d138d9, 0xfea2ca8c, 0x360bd498, 0xcf81f5a6, 0x28de7aa5, 0x268eb7da, 0xa4bfad3f, 0xe49d3a2c, 0xd927850, 
        0x9bcc5f6a, 0x62467e54, 0xc2138df6, 0xe8b8d890, 0x5ef7392e, 0xf5afc382, 0xbe805d9f, 0x7c93d069, 0xa92dd56f, 0xb31225cf, 
        0x3b99acc8, 0xa77d1810, 0x6e639ce8, 0x7bbb3bdb, 0x97826cd, 0xf418596e, 0x1b79aec, 0xa89a4f83, 0x656e95e6, 0x7ee6ffaa, 
        0x8cfbc21, 0xe6e815ef, 0xd99be7ba, 0xce366f4a, 0xd4099fea, 0xd67cb029, 0xafb2a431, 0x31233f2a, 0x3094a5c6, 0xc066a235, 
        0x37bc4e74, 0xa6ca82fc, 0xb0d090e0, 0x15d8a733, 0x4a9804f1, 0xf7daec41, 0xe50cd7f, 0x2ff69117, 0x8dd64d76, 0x4db0ef43, 
        0x544daacc, 0xdf0496e4, 0xe3b5d19e, 0x1b886a4c, 0xb81f2cc1, 0x7f516546, 0x4ea5e9d, 0x5d358c01, 0x737487fa, 0x2e410bfb, 
        0x5a1d67b3, 0x52d2db92, 0x335610e9, 0x1347d66d, 0x8c61d79a, 0x7a0ca137, 0x8e14f859, 0x893c13eb, 0xee27a9ce, 0x35c961b7, 
        0xede51ce1, 0x3cb1477a, 0x59dfd29c, 0x3f73f255, 0x79ce1418, 0xbf37c773, 0xeacdf753, 0x5baafd5f, 0x146f3ddf, 0x86db4478, 
        0x81f3afca, 0x3ec468b9, 0x2c342438, 0x5f40a3c2, 0x72c31d16, 0xc25e2bc, 0x8b493c28, 0x41950dff, 0x7101a839, 0xdeb30c08, 
        0x9ce4b4d8, 0x90c15664, 0x6184cb7b, 0x70b632d5, 0x745c6c48, 0x4257b8d0
    };
    private static final int m1 = 0x80808080;
    private static final int m2 = 0x7f7f7f7f;
    private static final int m3 = 27;
    private int ROUNDS;
    private int WorkingKey[][];
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private boolean forEncryption;
    private static final int BLOCK_SIZE = 16;

}
