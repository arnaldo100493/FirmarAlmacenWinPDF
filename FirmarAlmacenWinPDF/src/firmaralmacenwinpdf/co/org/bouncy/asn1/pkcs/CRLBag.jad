// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLBag.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;

public class CRLBag extends ASN1Object
{

    private CRLBag(ASN1Sequence seq)
    {
        crlId = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        crlValue = ((DERTaggedObject)seq.getObjectAt(1)).getObject();
    }

    public static CRLBag getInstance(Object o)
    {
        if(o instanceof CRLBag)
            return (CRLBag)o;
        if(o != null)
            return new CRLBag(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CRLBag(ASN1ObjectIdentifier crlId, ASN1Encodable crlValue)
    {
        this.crlId = crlId;
        this.crlValue = crlValue;
    }

    public ASN1ObjectIdentifier getcrlId()
    {
        return crlId;
    }

    public ASN1Encodable getCRLValue()
    {
        return crlValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(crlId);
        v.add(new DERTaggedObject(0, crlValue));
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier crlId;
    private ASN1Encodable crlValue;
}
