// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3411.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.GOST3411Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, GOST3411

public static class GOST3411$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        GOST3411$Digest d = (GOST3411$Digest)super.clone();
        d.digest = new GOST3411Digest((GOST3411Digest)digest);
        return d;
    }

    public GOST3411$Digest()
    {
        super(new GOST3411Digest());
    }
}
