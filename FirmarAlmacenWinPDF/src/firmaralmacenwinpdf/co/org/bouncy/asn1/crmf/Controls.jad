// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Controls.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            AttributeTypeAndValue

public class Controls extends ASN1Object
{

    private Controls(ASN1Sequence seq)
    {
        content = seq;
    }

    public static Controls getInstance(Object o)
    {
        if(o instanceof Controls)
            return (Controls)o;
        if(o != null)
            return new Controls(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public Controls(AttributeTypeAndValue atv)
    {
        content = new DERSequence(atv);
    }

    public Controls(AttributeTypeAndValue atvs[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < atvs.length; i++)
            v.add(atvs[i]);

        content = new DERSequence(v);
    }

    public AttributeTypeAndValue[] toAttributeTypeAndValueArray()
    {
        AttributeTypeAndValue result[] = new AttributeTypeAndValue[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = AttributeTypeAndValue.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
