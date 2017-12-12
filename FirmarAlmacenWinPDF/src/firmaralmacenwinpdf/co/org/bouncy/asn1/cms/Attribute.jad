// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Attribute.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

public class Attribute extends ASN1Object
{

    public static Attribute getInstance(Object o)
    {
        if(o instanceof Attribute)
            return (Attribute)o;
        if(o != null)
            return new Attribute(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private Attribute(ASN1Sequence seq)
    {
        attrType = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        attrValues = (ASN1Set)seq.getObjectAt(1);
    }

    /**
     * @deprecated Method Attribute is deprecated
     */

    public Attribute(DERObjectIdentifier attrType, ASN1Set attrValues)
    {
        this.attrType = new ASN1ObjectIdentifier(attrType.getId());
        this.attrValues = attrValues;
    }

    public Attribute(ASN1ObjectIdentifier attrType, ASN1Set attrValues)
    {
        this.attrType = attrType;
        this.attrValues = attrValues;
    }

    public ASN1ObjectIdentifier getAttrType()
    {
        return attrType;
    }

    public ASN1Set getAttrValues()
    {
        return attrValues;
    }

    public ASN1Encodable[] getAttributeValues()
    {
        return attrValues.toArray();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(attrType);
        v.add(attrValues);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier attrType;
    private ASN1Set attrValues;
}
