// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherKeyAttribute.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

public class OtherKeyAttribute extends ASN1Object
{

    public static OtherKeyAttribute getInstance(Object o)
    {
        if(o == null || (o instanceof OtherKeyAttribute))
            return (OtherKeyAttribute)o;
        if(o instanceof ASN1Sequence)
            return new OtherKeyAttribute((ASN1Sequence)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(o.getClass().getName()).toString());
    }

    public OtherKeyAttribute(ASN1Sequence seq)
    {
        keyAttrId = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        keyAttr = seq.getObjectAt(1);
    }

    public OtherKeyAttribute(ASN1ObjectIdentifier keyAttrId, ASN1Encodable keyAttr)
    {
        this.keyAttrId = keyAttrId;
        this.keyAttr = keyAttr;
    }

    public ASN1ObjectIdentifier getKeyAttrId()
    {
        return keyAttrId;
    }

    public ASN1Encodable getKeyAttr()
    {
        return keyAttr;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(keyAttrId);
        v.add(keyAttr);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier keyAttrId;
    private ASN1Encodable keyAttr;
}
