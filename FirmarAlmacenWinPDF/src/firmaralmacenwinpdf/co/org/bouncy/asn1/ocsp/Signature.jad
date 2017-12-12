// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Signature.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class Signature extends ASN1Object
{

    public Signature(AlgorithmIdentifier signatureAlgorithm, DERBitString signature)
    {
        this.signatureAlgorithm = signatureAlgorithm;
        this.signature = signature;
    }

    public Signature(AlgorithmIdentifier signatureAlgorithm, DERBitString signature, ASN1Sequence certs)
    {
        this.signatureAlgorithm = signatureAlgorithm;
        this.signature = signature;
        this.certs = certs;
    }

    private Signature(ASN1Sequence seq)
    {
        signatureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        signature = (DERBitString)seq.getObjectAt(1);
        if(seq.size() == 3)
            certs = ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(2), true);
    }

    public static Signature getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static Signature getInstance(Object obj)
    {
        if(obj instanceof Signature)
            return (Signature)obj;
        if(obj != null)
            return new Signature(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return signatureAlgorithm;
    }

    public DERBitString getSignature()
    {
        return signature;
    }

    public ASN1Sequence getCerts()
    {
        return certs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(signatureAlgorithm);
        v.add(signature);
        if(certs != null)
            v.add(new DERTaggedObject(true, 0, certs));
        return new DERSequence(v);
    }

    AlgorithmIdentifier signatureAlgorithm;
    DERBitString signature;
    ASN1Sequence certs;
}
