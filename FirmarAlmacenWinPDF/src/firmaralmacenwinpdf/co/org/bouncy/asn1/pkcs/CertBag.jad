// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertBag.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;

public class CertBag extends ASN1Object
{

    private CertBag(ASN1Sequence seq)
    {
        certId = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        certValue = ((DERTaggedObject)seq.getObjectAt(1)).getObject();
    }

    public static CertBag getInstance(Object o)
    {
        if(o instanceof CertBag)
            return (CertBag)o;
        if(o != null)
            return new CertBag(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertBag(ASN1ObjectIdentifier certId, ASN1Encodable certValue)
    {
        this.certId = certId;
        this.certValue = certValue;
    }

    public ASN1ObjectIdentifier getCertId()
    {
        return certId;
    }

    public ASN1Encodable getCertValue()
    {
        return certValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certId);
        v.add(new DERTaggedObject(0, certValue));
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier certId;
    private ASN1Encodable certValue;
}
