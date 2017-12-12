// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherRevocationInfoFormat.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

public class OtherRevocationInfoFormat extends ASN1Object
{

    public OtherRevocationInfoFormat(ASN1ObjectIdentifier otherRevInfoFormat, ASN1Encodable otherRevInfo)
    {
        this.otherRevInfoFormat = otherRevInfoFormat;
        this.otherRevInfo = otherRevInfo;
    }

    private OtherRevocationInfoFormat(ASN1Sequence seq)
    {
        otherRevInfoFormat = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        otherRevInfo = seq.getObjectAt(1);
    }

    public static OtherRevocationInfoFormat getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OtherRevocationInfoFormat getInstance(Object obj)
    {
        if(obj instanceof OtherRevocationInfoFormat)
            return (OtherRevocationInfoFormat)obj;
        if(obj != null)
            return new OtherRevocationInfoFormat(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1ObjectIdentifier getInfoFormat()
    {
        return otherRevInfoFormat;
    }

    public ASN1Encodable getInfo()
    {
        return otherRevInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(otherRevInfoFormat);
        v.add(otherRevInfo);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier otherRevInfoFormat;
    private ASN1Encodable otherRevInfo;
}
