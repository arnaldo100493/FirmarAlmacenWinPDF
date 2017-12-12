// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA224.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.SHA224Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, SHA224

public static class SHA224$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        SHA224$Digest d = (SHA224$Digest)super.clone();
        d.digest = new SHA224Digest((SHA224Digest)digest);
        return d;
    }

    public SHA224$Digest()
    {
        super(new SHA224Digest());
    }
}
