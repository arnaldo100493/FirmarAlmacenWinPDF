// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RDN.java

package co.org.bouncy.asn1.x500;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x500:
//            AttributeTypeAndValue

public class RDN extends ASN1Object
{

    private RDN(ASN1Set values)
    {
        this.values = values;
    }

    public static RDN getInstance(Object obj)
    {
        if(obj instanceof RDN)
            return (RDN)obj;
        if(obj != null)
            return new RDN(ASN1Set.getInstance(obj));
        else
            return null;
    }

    public RDN(ASN1ObjectIdentifier oid, ASN1Encodable value)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oid);
        v.add(value);
        values = new DERSet(new DERSequence(v));
    }

    public RDN(AttributeTypeAndValue attrTAndV)
    {
        values = new DERSet(attrTAndV);
    }

    public RDN(AttributeTypeAndValue aAndVs[])
    {
        values = new DERSet(aAndVs);
    }

    public boolean isMultiValued()
    {
        return values.size() > 1;
    }

    public int size()
    {
        return values.size();
    }

    public AttributeTypeAndValue getFirst()
    {
        if(values.size() == 0)
            return null;
        else
            return AttributeTypeAndValue.getInstance(values.getObjectAt(0));
    }

    public AttributeTypeAndValue[] getTypesAndValues()
    {
        AttributeTypeAndValue tmp[] = new AttributeTypeAndValue[values.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = AttributeTypeAndValue.getInstance(values.getObjectAt(i));

        return tmp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return values;
    }

    private ASN1Set values;
}
