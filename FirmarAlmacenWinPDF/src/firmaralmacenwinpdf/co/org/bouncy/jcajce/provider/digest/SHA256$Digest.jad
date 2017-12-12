// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA256.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA256Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA256

public static class SHA256$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        SHA256$Digest d = (SHA256$Digest)super.clone();
        d.digest = new SHA256Digest((SHA256Digest)digest);
        return d;
    }

    public SHA256$Digest()
    {
        super(new SHA256Digest());
    }
}
