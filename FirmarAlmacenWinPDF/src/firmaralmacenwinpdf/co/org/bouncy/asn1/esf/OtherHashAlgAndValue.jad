// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherHashAlgAndValue.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class OtherHashAlgAndValue extends ASN1Object
{

    public static OtherHashAlgAndValue getInstance(Object obj)
    {
        if(obj instanceof OtherHashAlgAndValue)
            return (OtherHashAlgAndValue)obj;
        if(obj != null)
            return new OtherHashAlgAndValue(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OtherHashAlgAndValue(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            hashAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
            hashValue = ASN1OctetString.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public OtherHashAlgAndValue(AlgorithmIdentifier hashAlgorithm, ASN1OctetString hashValue)
    {
        this.hashAlgorithm = hashAlgorithm;
        this.hashValue = hashValue;
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public ASN1OctetString getHashValue()
    {
        return hashValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(hashAlgorithm);
        v.add(hashValue);
        return new DERSequence(v);
    }

    private AlgorithmIdentifier hashAlgorithm;
    private ASN1OctetString hashValue;
}
