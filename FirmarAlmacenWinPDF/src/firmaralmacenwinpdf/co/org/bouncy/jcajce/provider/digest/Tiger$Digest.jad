// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tiger.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.TigerDigest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, Tiger

public static class Tiger$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        Tiger$Digest d = (Tiger$Digest)super.clone();
        d.digest = new TigerDigest((TigerDigest)digest);
        return d;
    }

    public Tiger$Digest()
    {
        super(new TigerDigest());
    }
}
