// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PolicyInformation.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

public class PolicyInformation extends ASN1Object
{

    private PolicyInformation(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        policyIdentifier = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            policyQualifiers = ASN1Sequence.getInstance(seq.getObjectAt(1));
    }

    public PolicyInformation(ASN1ObjectIdentifier policyIdentifier)
    {
        this.policyIdentifier = policyIdentifier;
    }

    public PolicyInformation(ASN1ObjectIdentifier policyIdentifier, ASN1Sequence policyQualifiers)
    {
        this.policyIdentifier = policyIdentifier;
        this.policyQualifiers = policyQualifiers;
    }

    public static PolicyInformation getInstance(Object obj)
    {
        if(obj == null || (obj instanceof PolicyInformation))
            return (PolicyInformation)obj;
        else
            return new PolicyInformation(ASN1Sequence.getInstance(obj));
    }

    public ASN1ObjectIdentifier getPolicyIdentifier()
    {
        return policyIdentifier;
    }

    public ASN1Sequence getPolicyQualifiers()
    {
        return policyQualifiers;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(policyIdentifier);
        if(policyQualifiers != null)
            v.add(policyQualifiers);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier policyIdentifier;
    private ASN1Sequence policyQualifiers;
}
