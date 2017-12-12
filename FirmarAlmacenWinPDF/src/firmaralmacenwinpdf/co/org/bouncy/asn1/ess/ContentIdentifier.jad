// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentIdentifier.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;

public class ContentIdentifier extends ASN1Object
{

    public static ContentIdentifier getInstance(Object o)
    {
        if(o instanceof ContentIdentifier)
            return (ContentIdentifier)o;
        if(o != null)
            return new ContentIdentifier(ASN1OctetString.getInstance(o));
        else
            return null;
    }

    private ContentIdentifier(ASN1OctetString value)
    {
        this.value = value;
    }

    public ContentIdentifier(byte value[])
    {
        this(((ASN1OctetString) (new DEROctetString(value))));
    }

    public ASN1OctetString getValue()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return value;
    }

    ASN1OctetString value;
}
