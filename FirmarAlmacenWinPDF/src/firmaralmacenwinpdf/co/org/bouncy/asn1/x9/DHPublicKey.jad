// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHPublicKey.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;

public class DHPublicKey extends ASN1Object
{

    public static DHPublicKey getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Integer.getInstance(obj, explicit));
    }

    public static DHPublicKey getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DHPublicKey))
            return (DHPublicKey)obj;
        if(obj instanceof ASN1Integer)
            return new DHPublicKey((ASN1Integer)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid DHPublicKey: ").append(obj.getClass().getName()).toString());
    }

    public DHPublicKey(ASN1Integer y)
    {
        if(y == null)
        {
            throw new IllegalArgumentException("'y' cannot be null");
        } else
        {
            this.y = y;
            return;
        }
    }

    public ASN1Integer getY()
    {
        return y;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return y;
    }

    private ASN1Integer y;
}
