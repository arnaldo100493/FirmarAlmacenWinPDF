// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Whirlpool.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.WhirlpoolDigest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, Whirlpool

public static class Whirlpool$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        Whirlpool$Digest d = (Whirlpool$Digest)super.clone();
        d.digest = new WhirlpoolDigest((WhirlpoolDigest)digest);
        return d;
    }

    public Whirlpool$Digest()
    {
        super(new WhirlpoolDigest());
    }
}
