// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPRequest.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            TBSRequest, Signature

public class OCSPRequest extends ASN1Object
{

    public OCSPRequest(TBSRequest tbsRequest, Signature optionalSignature)
    {
        this.tbsRequest = tbsRequest;
        this.optionalSignature = optionalSignature;
    }

    private OCSPRequest(ASN1Sequence seq)
    {
        tbsRequest = TBSRequest.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
            optionalSignature = Signature.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true);
    }

    public static OCSPRequest getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OCSPRequest getInstance(Object obj)
    {
        if(obj instanceof OCSPRequest)
            return (OCSPRequest)obj;
        if(obj != null)
            return new OCSPRequest(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public TBSRequest getTbsRequest()
    {
        return tbsRequest;
    }

    public Signature getOptionalSignature()
    {
        return optionalSignature;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsRequest);
        if(optionalSignature != null)
            v.add(new DERTaggedObject(true, 0, optionalSignature));
        return new DERSequence(v);
    }

    TBSRequest tbsRequest;
    Signature optionalSignature;
}
