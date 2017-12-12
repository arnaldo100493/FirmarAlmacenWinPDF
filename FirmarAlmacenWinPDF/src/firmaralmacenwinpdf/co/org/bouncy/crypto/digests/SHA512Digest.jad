// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Memoable;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            LongDigest

public class SHA512Digest extends LongDigest
{

    public SHA512Digest()
    {
    }

    public SHA512Digest(SHA512Digest t)
    {
        super(t);
    }

    public String getAlgorithmName()
    {
        return "SHA-512";
    }

    public int getDigestSize()
    {
        return 64;
    }

    public int doFinal(byte out[], int outOff)
    {
        finish();
        Pack.longToBigEndian(H1, out, outOff);
        Pack.longToBigEndian(H2, out, outOff + 8);
        Pack.longToBigEndian(H3, out, outOff + 16);
        Pack.longToBigEndian(H4, out, outOff + 24);
        Pack.longToBigEndian(H5, out, outOff + 32);
        Pack.longToBigEndian(H6, out, outOff + 40);
        Pack.longToBigEndian(H7, out, outOff + 48);
        Pack.longToBigEndian(H8, out, outOff + 56);
        reset();
        return 64;
    }

    public void reset()
    {
        super.reset();
        H1 = 0x6a09e667f3bcc908L;
        H2 = 0xbb67ae8584caa73bL;
        H3 = 0x3c6ef372fe94f82bL;
        H4 = 0xa54ff53a5f1d36f1L;
        H5 = 0x510e527fade682d1L;
        H6 = 0x9b05688c2b3e6c1fL;
        H7 = 0x1f83d9abfb41bd6bL;
        H8 = 0x5be0cd19137e2179L;
    }

    public Memoable copy()
    {
        return new SHA512Digest(this);
    }

    public void reset(Memoable other)
    {
        SHA512Digest d = (SHA512Digest)other;
        copyIn(d);
    }

    private static final int DIGEST_LENGTH = 64;
}
