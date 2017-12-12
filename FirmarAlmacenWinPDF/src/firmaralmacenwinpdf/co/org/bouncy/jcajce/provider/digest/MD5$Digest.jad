// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.MD5Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, MD5

public static class MD5$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        MD5$Digest d = (MD5$Digest)super.clone();
        d.digest = new MD5Digest((MD5Digest)digest);
        return d;
    }

    public MD5$Digest()
    {
        super(new MD5Digest());
    }
}
