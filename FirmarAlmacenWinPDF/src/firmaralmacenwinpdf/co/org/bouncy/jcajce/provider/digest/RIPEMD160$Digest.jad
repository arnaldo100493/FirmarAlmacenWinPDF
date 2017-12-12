// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD160.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.RIPEMD160Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, RIPEMD160

public static class RIPEMD160$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        RIPEMD160$Digest d = (RIPEMD160$Digest)super.clone();
        d.digest = new RIPEMD160Digest((RIPEMD160Digest)digest);
        return d;
    }

    public RIPEMD160$Digest()
    {
        super(new RIPEMD160Digest());
    }
}
