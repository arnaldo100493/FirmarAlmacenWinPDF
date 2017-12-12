// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD2.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.digests.MD2Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            BCMessageDigest, MD2

public static class MD2$Digest extends BCMessageDigest
    implements Cloneable
{

    public Object clone()
        throws CloneNotSupportedException
    {
        MD2$Digest d = (MD2$Digest)super.clone();
        d.digest = new MD2Digest((MD2Digest)digest);
        return d;
    }

    public MD2$Digest()
    {
        super(new MD2Digest());
    }
}
