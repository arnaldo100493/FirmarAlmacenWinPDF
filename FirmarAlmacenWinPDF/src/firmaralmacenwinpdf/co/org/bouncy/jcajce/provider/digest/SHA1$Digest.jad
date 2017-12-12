// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA1Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA1

public static class SHA1$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        SHA1$Digest d = (SHA1$Digest)super.clone();
        d.digest = new SHA1Digest((SHA1Digest)digest);
        return d;
    }

    public SHA1$Digest()
    {
        super(new SHA1Digest());
    }
}
