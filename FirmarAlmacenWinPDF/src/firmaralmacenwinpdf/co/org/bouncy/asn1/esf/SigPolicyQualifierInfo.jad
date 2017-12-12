// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SigPolicyQualifierInfo.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

public class SigPolicyQualifierInfo extends ASN1Object
{

    public SigPolicyQualifierInfo(ASN1ObjectIdentifier sigPolicyQualifierId, ASN1Encodable sigQualifier)
    {
        this.sigPolicyQualifierId = sigPolicyQualifierId;
        this.sigQualifier = sigQualifier;
    }

    private SigPolicyQualifierInfo(ASN1Sequence seq)
    {
        sigPolicyQualifierId = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        sigQualifier = seq.getObjectAt(1);
    }

    public static SigPolicyQualifierInfo getInstance(Object obj)
    {
        if(obj instanceof SigPolicyQualifierInfo)
            return (SigPolicyQualifierInfo)obj;
        if(obj != null)
            return new SigPolicyQualifierInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1ObjectIdentifier getSigPolicyQualifierId()
    {
        return new ASN1ObjectIdentifier(sigPolicyQualifierId.getId());
    }

    public ASN1Encodable getSigQualifier()
    {
        return sigQualifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(sigPolicyQualifierId);
        v.add(sigQualifier);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier sigPolicyQualifierId;
    private ASN1Encodable sigQualifier;
}
