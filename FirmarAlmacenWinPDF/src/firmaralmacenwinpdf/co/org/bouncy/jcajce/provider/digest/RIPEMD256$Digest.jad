// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD256.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.RIPEMD256Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, RIPEMD256

public static class RIPEMD256$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        RIPEMD256$Digest d = (RIPEMD256$Digest)super.clone();
        d.digest = new RIPEMD256Digest((RIPEMD256Digest)digest);
        return d;
    }

    public RIPEMD256$Digest()
    {
        super(new RIPEMD256Digest());
    }
}
