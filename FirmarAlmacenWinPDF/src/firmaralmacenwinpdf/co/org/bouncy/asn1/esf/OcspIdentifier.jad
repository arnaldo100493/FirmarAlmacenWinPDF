// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OcspIdentifier.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.ResponderID;

public class OcspIdentifier extends ASN1Object
{

    public static OcspIdentifier getInstance(Object obj)
    {
        if(obj instanceof OcspIdentifier)
            return (OcspIdentifier)obj;
        if(obj != null)
            return new OcspIdentifier(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OcspIdentifier(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            ocspResponderID = ResponderID.getInstance(seq.getObjectAt(0));
            producedAt = (ASN1GeneralizedTime)seq.getObjectAt(1);
            return;
        }
    }

    public OcspIdentifier(ResponderID ocspResponderID, ASN1GeneralizedTime producedAt)
    {
        this.ocspResponderID = ocspResponderID;
        this.producedAt = producedAt;
    }

    public ResponderID getOcspResponderID()
    {
        return ocspResponderID;
    }

    public ASN1GeneralizedTime getProducedAt()
    {
        return producedAt;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(ocspResponderID);
        v.add(producedAt);
        return new DERSequence(v);
    }

    private ResponderID ocspResponderID;
    private ASN1GeneralizedTime producedAt;
}
