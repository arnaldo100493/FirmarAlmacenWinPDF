// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPResponse.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            ResponseData

public class BasicOCSPResponse extends ASN1Object
{

    public BasicOCSPResponse(ResponseData tbsResponseData, AlgorithmIdentifier signatureAlgorithm, DERBitString signature, ASN1Sequence certs)
    {
        this.tbsResponseData = tbsResponseData;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signature = signature;
        this.certs = certs;
    }

    private BasicOCSPResponse(ASN1Sequence seq)
    {
        tbsResponseData = ResponseData.getInstance(seq.getObjectAt(0));
        signatureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        signature = (DERBitString)seq.getObjectAt(2);
        if(seq.size() > 3)
            certs = ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(3), true);
    }

    public static BasicOCSPResponse getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static BasicOCSPResponse getInstance(Object obj)
    {
        if(obj instanceof BasicOCSPResponse)
            return (BasicOCSPResponse)obj;
        if(obj != null)
            return new BasicOCSPResponse(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ResponseData getTbsResponseData()
    {
        return tbsResponseData;
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
        v.add(tbsResponseData);
        v.add(signatureAlgorithm);
        v.add(signature);
        if(certs != null)
            v.add(new DERTaggedObject(true, 0, certs));
        return new DERSequence(v);
    }

    private ResponseData tbsResponseData;
    private AlgorithmIdentifier signatureAlgorithm;
    private DERBitString signature;
    private ASN1Sequence certs;
}
