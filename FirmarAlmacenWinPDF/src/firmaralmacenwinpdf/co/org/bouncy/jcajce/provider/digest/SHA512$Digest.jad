// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA512Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA512

public static class SHA512$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        SHA512$Digest d = (SHA512$Digest)super.clone();
        d.digest = new SHA512Digest((SHA512Digest)digest);
        return d;
    }

    public SHA512$Digest()
    {
        super(new SHA512Digest());
    }
}
