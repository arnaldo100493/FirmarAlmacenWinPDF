// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD320.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.RIPEMD320Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, RIPEMD320

public static class RIPEMD320$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        RIPEMD320$Digest d = (RIPEMD320$Digest)super.clone();
        d.digest = new RIPEMD320Digest((RIPEMD320Digest)digest);
        return d;
    }

    public RIPEMD320$Digest()
    {
        super(new RIPEMD320Digest());
    }
}
