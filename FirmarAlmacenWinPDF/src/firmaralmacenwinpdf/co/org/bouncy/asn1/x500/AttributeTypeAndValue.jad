// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttributeTypeAndValue.java

package co.org.bouncy.asn1.x500;

import co.org.bouncy.asn1.*;

public class AttributeTypeAndValue extends ASN1Object
{

    private AttributeTypeAndValue(ASN1Sequence seq)
    {
        type = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        value = seq.getObjectAt(1);
    }

    public static AttributeTypeAndValue getInstance(Object o)
    {
        if(o instanceof AttributeTypeAndValue)
            return (AttributeTypeAndValue)o;
        if(o != null)
            return new AttributeTypeAndValue(ASN1Sequence.getInstance(o));
        else
            throw new IllegalArgumentException("null value in getInstance()");
    }

    public AttributeTypeAndValue(ASN1ObjectIdentifier type, ASN1Encodable value)
    {
        this.type = type;
        this.value = value;
    }

    public ASN1ObjectIdentifier getType()
    {
        return type;
    }

    public ASN1Encodable getValue()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(type);
        v.add(value);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier type;
    private ASN1Encodable value;
}
