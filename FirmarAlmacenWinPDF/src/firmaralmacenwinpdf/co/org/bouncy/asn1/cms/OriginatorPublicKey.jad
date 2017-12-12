// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OriginatorPublicKey.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class OriginatorPublicKey extends ASN1Object
{

    public OriginatorPublicKey(AlgorithmIdentifier algorithm, byte publicKey[])
    {
        this.algorithm = algorithm;
        this.publicKey = new DERBitString(publicKey);
    }

    public OriginatorPublicKey(ASN1Sequence seq)
    {
        algorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        publicKey = (DERBitString)seq.getObjectAt(1);
    }

    public static OriginatorPublicKey getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OriginatorPublicKey getInstance(Object obj)
    {
        if(obj == null || (obj instanceof OriginatorPublicKey))
            return (OriginatorPublicKey)obj;
        if(obj instanceof ASN1Sequence)
            return new OriginatorPublicKey((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid OriginatorPublicKey: ").append(obj.getClass().getName()).toString());
    }

    public AlgorithmIdentifier getAlgorithm()
    {
        return algorithm;
    }

    public DERBitString getPublicKey()
    {
        return publicKey;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(algorithm);
        v.add(publicKey);
        return new DERSequence(v);
    }

    private AlgorithmIdentifier algorithm;
    private DERBitString publicKey;
}
