// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevokedInfo.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.CRLReason;

public class RevokedInfo extends ASN1Object
{

    public RevokedInfo(ASN1GeneralizedTime revocationTime, CRLReason revocationReason)
    {
        this.revocationTime = revocationTime;
        this.revocationReason = revocationReason;
    }

    private RevokedInfo(ASN1Sequence seq)
    {
        revocationTime = ASN1GeneralizedTime.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            revocationReason = CRLReason.getInstance(DEREnumerated.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true));
    }

    public static RevokedInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static RevokedInfo getInstance(Object obj)
    {
        if(obj instanceof RevokedInfo)
            return (RevokedInfo)obj;
        if(obj != null)
            return new RevokedInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1GeneralizedTime getRevocationTime()
    {
        return revocationTime;
    }

    public CRLReason getRevocationReason()
    {
        return revocationReason;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(revocationTime);
        if(revocationReason != null)
            v.add(new DERTaggedObject(true, 0, revocationReason));
        return new DERSequence(v);
    }

    private ASN1GeneralizedTime revocationTime;
    private CRLReason revocationReason;
}
