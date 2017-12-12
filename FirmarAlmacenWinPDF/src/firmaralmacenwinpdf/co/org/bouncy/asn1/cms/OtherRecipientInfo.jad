// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherRecipientInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

public class OtherRecipientInfo extends ASN1Object
{

    public OtherRecipientInfo(ASN1ObjectIdentifier oriType, ASN1Encodable oriValue)
    {
        this.oriType = oriType;
        this.oriValue = oriValue;
    }

    /**
     * @deprecated Method OtherRecipientInfo is deprecated
     */

    public OtherRecipientInfo(ASN1Sequence seq)
    {
        oriType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        oriValue = seq.getObjectAt(1);
    }

    public static OtherRecipientInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OtherRecipientInfo getInstance(Object obj)
    {
        if(obj instanceof OtherRecipientInfo)
            return (OtherRecipientInfo)obj;
        if(obj != null)
            return new OtherRecipientInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1ObjectIdentifier getType()
    {
        return oriType;
    }

    public ASN1Encodable getValue()
    {
        return oriValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oriType);
        v.add(oriValue);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier oriType;
    private ASN1Encodable oriValue;
}
