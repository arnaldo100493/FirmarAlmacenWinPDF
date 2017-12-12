// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SigPolicyQualifiers.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            SigPolicyQualifierInfo

public class SigPolicyQualifiers extends ASN1Object
{

    public static SigPolicyQualifiers getInstance(Object obj)
    {
        if(obj instanceof SigPolicyQualifiers)
            return (SigPolicyQualifiers)obj;
        if(obj instanceof ASN1Sequence)
            return new SigPolicyQualifiers(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SigPolicyQualifiers(ASN1Sequence seq)
    {
        qualifiers = seq;
    }

    public SigPolicyQualifiers(SigPolicyQualifierInfo qualifierInfos[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < qualifierInfos.length; i++)
            v.add(qualifierInfos[i]);

        qualifiers = new DERSequence(v);
    }

    public int size()
    {
        return qualifiers.size();
    }

    public SigPolicyQualifierInfo getInfoAt(int i)
    {
        return SigPolicyQualifierInfo.getInstance(qualifiers.getObjectAt(i));
    }

    public ASN1Primitive toASN1Primitive()
    {
        return qualifiers;
    }

    ASN1Sequence qualifiers;
}
