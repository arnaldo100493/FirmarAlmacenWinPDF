// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD4Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.util.Memoable;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            GeneralDigest

public class MD4Digest extends GeneralDigest
{

    public MD4Digest()
    {
        X = new int[16];
        reset();
    }

    public MD4Digest(MD4Digest t)
    {
        super(t);
        X = new int[16];
        copyIn(t);
    }

    private void copyIn(MD4Digest t)
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
        return "MD4";
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
        return u & v | u & w | v & w;
    }

    private int H(int u, int v, int w)
    {
        return u ^ v ^ w;
    }

    protected void processBlock()
    {
        int a = H1;
        int b = H2;
        int c = H3;
        int d = H4;
        a = rotateLeft(a + F(b, c, d) + X[0], 3);
        d = rotateLeft(d + F(a, b, c) + X[1], 7);
        c = rotateLeft(c + F(d, a, b) + X[2], 11);
        b = rotateLeft(b + F(c, d, a) + X[3], 19);
        a = rotateLeft(a + F(b, c, d) + X[4], 3);
        d = rotateLeft(d + F(a, b, c) + X[5], 7);
        c = rotateLeft(c + F(d, a, b) + X[6], 11);
        b = rotateLeft(b + F(c, d, a) + X[7], 19);
        a = rotateLeft(a + F(b, c, d) + X[8], 3);
        d = rotateLeft(d + F(a, b, c) + X[9], 7);
        c = rotateLeft(c + F(d, a, b) + X[10], 11);
        b = rotateLeft(b + F(c, d, a) + X[11], 19);
        a = rotateLeft(a + F(b, c, d) + X[12], 3);
        d = rotateLeft(d + F(a, b, c) + X[13], 7);
        c = rotateLeft(c + F(d, a, b) + X[14], 11);
        b = rotateLeft(b + F(c, d, a) + X[15], 19);
        a = rotateLeft(a + G(b, c, d) + X[0] + 0x5a827999, 3);
        d = rotateLeft(d + G(a, b, c) + X[4] + 0x5a827999, 5);
        c = rotateLeft(c + G(d, a, b) + X[8] + 0x5a827999, 9);
        b = rotateLeft(b + G(c, d, a) + X[12] + 0x5a827999, 13);
        a = rotateLeft(a + G(b, c, d) + X[1] + 0x5a827999, 3);
        d = rotateLeft(d + G(a, b, c) + X[5] + 0x5a827999, 5);
        c = rotateLeft(c + G(d, a, b) + X[9] + 0x5a827999, 9);
        b = rotateLeft(b + G(c, d, a) + X[13] + 0x5a827999, 13);
        a = rotateLeft(a + G(b, c, d) + X[2] + 0x5a827999, 3);
        d = rotateLeft(d + G(a, b, c) + X[6] + 0x5a827999, 5);
        c = rotateLeft(c + G(d, a, b) + X[10] + 0x5a827999, 9);
        b = rotateLeft(b + G(c, d, a) + X[14] + 0x5a827999, 13);
        a = rotateLeft(a + G(b, c, d) + X[3] + 0x5a827999, 3);
        d = rotateLeft(d + G(a, b, c) + X[7] + 0x5a827999, 5);
        c = rotateLeft(c + G(d, a, b) + X[11] + 0x5a827999, 9);
        b = rotateLeft(b + G(c, d, a) + X[15] + 0x5a827999, 13);
        a = rotateLeft(a + H(b, c, d) + X[0] + 0x6ed9eba1, 3);
        d = rotateLeft(d + H(a, b, c) + X[8] + 0x6ed9eba1, 9);
        c = rotateLeft(c + H(d, a, b) + X[4] + 0x6ed9eba1, 11);
        b = rotateLeft(b + H(c, d, a) + X[12] + 0x6ed9eba1, 15);
        a = rotateLeft(a + H(b, c, d) + X[2] + 0x6ed9eba1, 3);
        d = rotateLeft(d + H(a, b, c) + X[10] + 0x6ed9eba1, 9);
        c = rotateLeft(c + H(d, a, b) + X[6] + 0x6ed9eba1, 11);
        b = rotateLeft(b + H(c, d, a) + X[14] + 0x6ed9eba1, 15);
        a = rotateLeft(a + H(b, c, d) + X[1] + 0x6ed9eba1, 3);
        d = rotateLeft(d + H(a, b, c) + X[9] + 0x6ed9eba1, 9);
        c = rotateLeft(c + H(d, a, b) + X[5] + 0x6ed9eba1, 11);
        b = rotateLeft(b + H(c, d, a) + X[13] + 0x6ed9eba1, 15);
        a = rotateLeft(a + H(b, c, d) + X[3] + 0x6ed9eba1, 3);
        d = rotateLeft(d + H(a, b, c) + X[11] + 0x6ed9eba1, 9);
        c = rotateLeft(c + H(d, a, b) + X[7] + 0x6ed9eba1, 11);
        b = rotateLeft(b + H(c, d, a) + X[15] + 0x6ed9eba1, 15);
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
        return new MD4Digest(this);
    }

    public void reset(Memoable other)
    {
        MD4Digest d = (MD4Digest)other;
        copyIn(d);
    }

    private static final int DIGEST_LENGTH = 16;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int X[];
    private int xOff;
    private static final int S11 = 3;
    private static final int S12 = 7;
    private static final int S13 = 11;
    private static final int S14 = 19;
    private static final int S21 = 3;
    private static final int S22 = 5;
    private static final int S23 = 9;
    private static final int S24 = 13;
    private static final int S31 = 3;
    private static final int S32 = 9;
    private static final int S33 = 11;
    private static final int S34 = 15;
}
