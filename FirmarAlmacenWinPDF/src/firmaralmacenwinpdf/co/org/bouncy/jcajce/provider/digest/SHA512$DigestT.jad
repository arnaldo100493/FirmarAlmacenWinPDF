// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA512tDigest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA512

public static class SHA512$DigestT extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        SHA512$DigestT d = (SHA512$DigestT)super.clone();
        d.digest = new SHA512tDigest((SHA512tDigest)digest);
        return d;
    }

    public SHA512$DigestT(int bitLength)
    {
        super(new SHA512tDigest(bitLength));
    }
}
