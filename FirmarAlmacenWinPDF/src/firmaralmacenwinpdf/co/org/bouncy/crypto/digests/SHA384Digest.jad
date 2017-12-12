// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA384Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Memoable;

// Referenced classes of package co.org.bouncy.crypto.digests:
//            LongDigest

public class SHA384Digest extends LongDigest
{

    public SHA384Digest()
    {
    }

    public SHA384Digest(SHA384Digest t)
    {
        super(t);
    }

    public String getAlgorithmName()
    {
        return "SHA-384";
    }

    public int getDigestSize()
    {
        return 48;
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
        reset();
        return 48;
    }

    public void reset()
    {
        super.reset();
        H1 = 0xcbbb9d5dc1059ed8L;
        H2 = 0x629a292a367cd507L;
        H3 = 0x9159015a3070dd17L;
        H4 = 0x152fecd8f70e5939L;
        H5 = 0x67332667ffc00b31L;
        H6 = 0x8eb44a8768581511L;
        H7 = 0xdb0c2e0d64f98fa7L;
        H8 = 0x47b5481dbefa4fa4L;
    }

    public Memoable copy()
    {
        return new SHA384Digest(this);
    }

    public void reset(Memoable other)
    {
        SHA384Digest d = (SHA384Digest)other;
        super.copyIn(d);
    }

    private static final int DIGEST_LENGTH = 48;
}
