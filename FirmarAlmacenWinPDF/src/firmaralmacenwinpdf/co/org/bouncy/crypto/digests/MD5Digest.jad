// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.util.Memoable;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            GeneralDigest

public class MD5Digest extends GeneralDigest
{

    public MD5Digest()
    {
        X = new int[16];
        reset();
    }

    public MD5Digest(MD5Digest t)
    {
        super(t);
        X = new int[16];
        copyIn(t);
    }

    private void copyIn(MD5Digest t)
    {
        super.copyIn(t);
        H1 = t.H1;
        H2 = t.H2;
        H3 = t.H3;
        H4 = t.H4;
        System.arraycopy(t.X, 0, X, 0, t.X.length);
        xOff = t.xOff;
    }

    public String getAlgorithmName()
    {
        return "MD5";
    }

    public int getDigestSize()
    {
        return 16;
    }

    protected void processWord(byte in[], int inOff)
    {
        X[xOff++] = in[inOff] & 0xff | (in[inOff + 1] & 0xff) << 8 | (in[inOff + 2] & 0xff) << 16 | (in[inOff + 3] & 0xff) << 24;
        if(xOff == 16)
            processBlock();
    }

    protected void processLength(long bitLength)
    {
        if(xOff > 14)
            processBlock();
        X[14] = (int)(bitLength & -1L);
        X[15] = (int)(bitLength >>> 32);
    }

    private void unpackWord(int word, byte out[], int outOff)
    {
        out[outOff] = (byte)word;
        out[outOff + 1] = (byte)(word >>> 8);
        out[outOff + 2] = (byte)(word >>> 16);
        out[outOff + 3] = (byte)(word >>> 24);
    }

    public int doFinal(byte out[], int outOff)
    {
        finish();
        unpackWord(H1, out, outOff);
        unpackWord(H2, out, outOff + 4);
        unpackWord(H3, out, outOff + 8);
        unpackWord(H4, out, outOff + 12);
        reset();
        return 16;
    }

    public void reset()
    {
        super.reset();
        H1 = 0x67452301;
        H2 = 0xefcdab89;
        H3 = 0x98badcfe;
        H4 = 0x10325476;
        xOff = 0;
        for(int i = 0; i != X.length; i++)
            X[i] = 0;

    }

    private int rotateLeft(int x, int n)
    {
        return x << n | x >>> 32 - n;
    }

    private int F(int u, int v, int w)
    {
        return u & v | ~u & w;
    }

    private int G(int u, int v, int w)
    {
        return u & w | v & ~w;
    }

    private int H(int u, int v, int w)
    {
        return u ^ v ^ w;
    }

    private int K(int u, int v, int w)
    {
        return v ^ (u | ~w);
    }

    protected void processBlock()
    {
        int a = H1;
        int b = H2;
        int c = H3;
        int d = H4;
        a = rotateLeft(a + F(b, c, d) + X[0] + 0xd76aa478, 7) + b;
        d = rotateLeft(d + F(a, b, c) + X[1] + 0xe8c7b756, 12) + a;
        c = rotateLeft(c + F(d, a, b) + X[2] + 0x242070db, 17) + d;
        b = rotateLeft(b + F(c, d, a) + X[3] + 0xc1bdceee, 22) + c;
        a = rotateLeft(a + F(b, c, d) + X[4] + 0xf57c0faf, 7) + b;
        d = rotateLeft(d + F(a, b, c) + X[5] + 0x4787c62a, 12) + a;
        c = rotateLeft(c + F(d, a, b) + X[6] + 0xa8304613, 17) + d;
        b = rotateLeft(b + F(c, d, a) + X[7] + 0xfd469501, 22) + c;
        a = rotateLeft(a + F(b, c, d) + X[8] + 0x698098d8, 7) + b;
        d = rotateLeft(d + F(a, b, c) + X[9] + 0x8b44f7af, 12) + a;
        c = rotateLeft(c + F(d, a, b) + X[10] + -42063, 17) + d;
        b = rotateLeft(b + F(c, d, a) + X[11] + 0x895cd7be, 22) + c;
        a = rotateLeft(a + F(b, c, d) + X[12] + 0x6b901122, 7) + b;
        d = rotateLeft(d + F(a, b, c) + X[13] + 0xfd987193, 12) + a;
        c = rotateLeft(c + F(d, a, b) + X[14] + 0xa679438e, 17) + d;
        b = rotateLeft(b + F(c, d, a) + X[15] + 0x49b40821, 22) + c;
        a = rotateLeft(a + G(b, c, d) + X[1] + 0xf61e2562, 5) + b;
        d = rotateLeft(d + G(a, b, c) + X[6] + 0xc040b340, 9) + a;
        c = rotateLeft(c + G(d, a, b) + X[11] + 0x265e5a51, 14) + d;
        b = rotateLeft(b + G(c, d, a) + X[0] + 0xe9b6c7aa, 20) + c;
        a = rotateLeft(a + G(b, c, d) + X[5] + 0xd62f105d, 5) + b;
        d = rotateLeft(d + G(a, b, c) + X[10] + 0x2441453, 9) + a;
        c = rotateLeft(c + G(d, a, b) + X[15] + 0xd8a1e681, 14) + d;
        b = rotateLeft(b + G(c, d, a) + X[4] + 0xe7d3fbc8, 20) + c;
        a = rotateLeft(a + G(b, c, d) + X[9] + 0x21e1cde6, 5) + b;
        d = rotateLeft(d + G(a, b, c) + X[14] + 0xc33707d6, 9) + a;
        c = rotateLeft(c + G(d, a, b) + X[3] + 0xf4d50d87, 14) + d;
        b = rotateLeft(b + G(c, d, a) + X[8] + 0x455a14ed, 20) + c;
        a = rotateLeft(a + G(b, c, d) + X[13] + 0xa9e3e905, 5) + b;
        d = rotateLeft(d + G(a, b, c) + X[2] + 0xfcefa3f8, 9) + a;
        c = rotateLeft(c + G(d, a, b) + X[7] + 0x676f02d9, 14) + d;
        b = rotateLeft(b + G(c, d, a) + X[12] + 0x8d2a4c8a, 20) + c;
        a = rotateLeft(a + H(b, c, d) + X[5] + 0xfffa3942, 4) + b;
        d = rotateLeft(d + H(a, b, c) + X[8] + 0x8771f681, 11) + a;
        c = rotateLeft(c + H(d, a, b) + X[11] + 0x6d9d6122, 16) + d;
        b = rotateLeft(b + H(c, d, a) + X[14] + 0xfde5380c, 23) + c;
        a = rotateLeft(a + H(b, c, d) + X[1] + 0xa4beea44, 4) + b;
        d = rotateLeft(d + H(a, b, c) + X[4] + 0x4bdecfa9, 11) + a;
        c = rotateLeft(c + H(d, a, b) + X[7] + 0xf6bb4b60, 16) + d;
        b = rotateLeft(b + H(c, d, a) + X[10] + 0xbebfbc70, 23) + c;
        a = rotateLeft(a + H(b, c, d) + X[13] + 0x289b7ec6, 4) + b;
        d = rotateLeft(d + H(a, b, c) + X[0] + 0xeaa127fa, 11) + a;
        c = rotateLeft(c + H(d, a, b) + X[3] + 0xd4ef3085, 16) + d;
        b = rotateLeft(b + H(c, d, a) + X[6] + 0x4881d05, 23) + c;
        a = rotateLeft(a + H(b, c, d) + X[9] + 0xd9d4d039, 4) + b;
        d = rotateLeft(d + H(a, b, c) + X[12] + 0xe6db99e5, 11) + a;
        c = rotateLeft(c + H(d, a, b) + X[15] + 0x1fa27cf8, 16) + d;
        b = rotateLeft(b + H(c, d, a) + X[2] + 0xc4ac5665, 23) + c;
        a = rotateLeft(a + K(b, c, d) + X[0] + 0xf4292244, 6) + b;
        d = rotateLeft(d + K(a, b, c) + X[7] + 0x432aff97, 10) + a;
        c = rotateLeft(c + K(d, a, b) + X[14] + 0xab9423a7, 15) + d;
        b = rotateLeft(b + K(c, d, a) + X[5] + 0xfc93a039, 21) + c;
        a = rotateLeft(a + K(b, c, d) + X[12] + 0x655b59c3, 6) + b;
        d = rotateLeft(d + K(a, b, c) + X[3] + 0x8f0ccc92, 10) + a;
        c = rotateLeft(c + K(d, a, b) + X[10] + 0xffeff47d, 15) + d;
        b = rotateLeft(b + K(c, d, a) + X[1] + 0x85845dd1, 21) + c;
        a = rotateLeft(a + K(b, c, d) + X[8] + 0x6fa87e4f, 6) + b;
        d = rotateLeft(d + K(a, b, c) + X[15] + 0xfe2ce6e0, 10) + a;
        c = rotateLeft(c + K(d, a, b) + X[6] + 0xa3014314, 15) + d;
        b = rotateLeft(b + K(c, d, a) + X[13] + 0x4e0811a1, 21) + c;
        a = rotateLeft(a + K(b, c, d) + X[4] + 0xf7537e82, 6) + b;
        d = rotateLeft(d + K(a, b, c) + X[11] + 0xbd3af235, 10) + a;
        c = rotateLeft(c + K(d, a, b) + X[2] + 0x2ad7d2bb, 15) + d;
        b = rotateLeft(b + K(c, d, a) + X[9] + 0xeb86d391, 21) + c;
        H1 += a;
        H2 += b;
        H3 += c;
        H4 += d;
        xOff = 0;
        for(int i = 0; i != X.length; i++)
            X[i] = 0;

    }

    public Memoable copy()
    {
        return new MD5Digest(this);
    }

    public void reset(Memoable other)
    {
        MD5Digest d = (MD5Digest)other;
        copyIn(d);
    }

    private static final int DIGEST_LENGTH = 16;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int X[];
    private int xOff;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;
}
