// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA3.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA3Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA3

public static class SHA3$DigestSHA3 extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        BCMessageDigest d = (BCMessageDigest)super.clone();
        d.digest = new SHA3Digest((SHA3Digest)digest);
        return d;
    }

    public SHA3$DigestSHA3(int size)
    {
        super(new SHA3Digest(size));
    }
}
