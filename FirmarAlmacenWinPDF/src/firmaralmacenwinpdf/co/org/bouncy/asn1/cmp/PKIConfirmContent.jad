// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIConfirmContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

public class PKIConfirmContent extends ASN1Object
{

    private PKIConfirmContent(ASN1Null val)
    {
        this.val = val;
    }

    public static PKIConfirmContent getInstance(Object o)
    {
        if(o == null || (o instanceof PKIConfirmContent))
            return (PKIConfirmContent)o;
        if(o instanceof ASN1Null)
            return new PKIConfirmContent((ASN1Null)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid object: ").append(o.getClass().getName()).toString());
    }

    public PKIConfirmContent()
    {
        val = DERNull.INSTANCE;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return val;
    }

    private ASN1Null val;
}
