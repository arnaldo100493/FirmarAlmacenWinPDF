// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD4.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.MD4Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, MD4

public static class MD4$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        MD4$Digest d = (MD4$Digest)super.clone();
        d.digest = new MD4Digest((MD4Digest)digest);
        return d;
    }

    public MD4$Digest()
    {
        super(new MD4Digest());
    }
}
