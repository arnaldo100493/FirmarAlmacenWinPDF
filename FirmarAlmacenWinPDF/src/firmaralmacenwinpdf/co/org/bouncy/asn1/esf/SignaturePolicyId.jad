// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignaturePolicyId.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OtherHashAlgAndValue, SigPolicyQualifiers

public class SignaturePolicyId extends ASN1Object
{

    public static SignaturePolicyId getInstance(Object obj)
    {
        if(obj instanceof SignaturePolicyId)
            return (SignaturePolicyId)obj;
        if(obj != null)
            return new SignaturePolicyId(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SignaturePolicyId(ASN1Sequence seq)
    {
        if(seq.size() != 2 && seq.size() != 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        sigPolicyId = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        sigPolicyHash = OtherHashAlgAndValue.getInstance(seq.getObjectAt(1));
        if(seq.size() == 3)
            sigPolicyQualifiers = SigPolicyQualifiers.getInstance(seq.getObjectAt(2));
    }

    public SignaturePolicyId(ASN1ObjectIdentifier sigPolicyIdentifier, OtherHashAlgAndValue sigPolicyHash)
    {
        this(sigPolicyIdentifier, sigPolicyHash, null);
    }

    public SignaturePolicyId(ASN1ObjectIdentifier sigPolicyId, OtherHashAlgAndValue sigPolicyHash, SigPolicyQualifiers sigPolicyQualifiers)
    {
        this.sigPolicyId = sigPolicyId;
        this.sigPolicyHash = sigPolicyHash;
        this.sigPolicyQualifiers = sigPolicyQualifiers;
    }

    public ASN1ObjectIdentifier getSigPolicyId()
    {
        return new ASN1ObjectIdentifier(sigPolicyId.getId());
    }

    public OtherHashAlgAndValue getSigPolicyHash()
    {
        return sigPolicyHash;
    }

    public SigPolicyQualifiers getSigPolicyQualifiers()
    {
        return sigPolicyQualifiers;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(sigPolicyId);
        v.add(sigPolicyHash);
        if(sigPolicyQualifiers != null)
            v.add(sigPolicyQualifiers);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier sigPolicyId;
    private OtherHashAlgAndValue sigPolicyHash;
    private SigPolicyQualifiers sigPolicyQualifiers;
}
