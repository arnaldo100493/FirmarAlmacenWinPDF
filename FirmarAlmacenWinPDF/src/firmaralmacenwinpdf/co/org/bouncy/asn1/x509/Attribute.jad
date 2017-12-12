// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Attribute.java

package co.org.bouncy.asn1.x509;

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
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            attrType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
            attrValues = ASN1Set.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public Attribute(ASN1ObjectIdentifier attrType, ASN1Set attrValues)
    {
        this.attrType = attrType;
        this.attrValues = attrValues;
    }

    public ASN1ObjectIdentifier getAttrType()
    {
        return new ASN1ObjectIdentifier(attrType.getId());
    }

    public ASN1Encodable[] getAttributeValues()
    {
        return attrValues.toArray();
    }

    public ASN1Set getAttrValues()
    {
        return attrValues;
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
