// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD128.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.RIPEMD128Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, RIPEMD128

public static class RIPEMD128$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        RIPEMD128$Digest d = (RIPEMD128$Digest)super.clone();
        d.digest = new RIPEMD128Digest((RIPEMD128Digest)digest);
        return d;
    }

    public RIPEMD128$Digest()
    {
        super(new RIPEMD128Digest());
    }
}
