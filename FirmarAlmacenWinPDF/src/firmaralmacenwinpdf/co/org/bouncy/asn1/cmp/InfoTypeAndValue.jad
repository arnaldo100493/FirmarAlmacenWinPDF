// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InfoTypeAndValue.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

public class InfoTypeAndValue extends ASN1Object
{

    private InfoTypeAndValue(ASN1Sequence seq)
    {
        infoType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            infoValue = seq.getObjectAt(1);
    }

    public static InfoTypeAndValue getInstance(Object o)
    {
        if(o instanceof InfoTypeAndValue)
            return (InfoTypeAndValue)o;
        if(o != null)
            return new InfoTypeAndValue(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier infoType)
    {
        this.infoType = infoType;
        infoValue = null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier infoType, ASN1Encodable optionalValue)
    {
        this.infoType = infoType;
        infoValue = optionalValue;
    }

    public ASN1ObjectIdentifier getInfoType()
    {
        return infoType;
    }

    public ASN1Encodable getInfoValue()
    {
        return infoValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(infoType);
        if(infoValue != null)
            v.add(infoValue);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier infoType;
    private ASN1Encodable infoValue;
}
