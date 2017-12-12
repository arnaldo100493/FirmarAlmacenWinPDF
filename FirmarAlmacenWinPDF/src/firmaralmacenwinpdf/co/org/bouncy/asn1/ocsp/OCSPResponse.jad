// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPResponse.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            OCSPResponseStatus, ResponseBytes

public class OCSPResponse extends ASN1Object
{

    public OCSPResponse(OCSPResponseStatus responseStatus, ResponseBytes responseBytes)
    {
        this.responseStatus = responseStatus;
        this.responseBytes = responseBytes;
    }

    private OCSPResponse(ASN1Sequence seq)
    {
        responseStatus = OCSPResponseStatus.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
            responseBytes = ResponseBytes.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true);
    }

    public static OCSPResponse getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OCSPResponse getInstance(Object obj)
    {
        if(obj instanceof OCSPResponse)
            return (OCSPResponse)obj;
        if(obj != null)
            return new OCSPResponse(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public OCSPResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public ResponseBytes getResponseBytes()
    {
        return responseBytes;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(responseStatus);
        if(responseBytes != null)
            v.add(new DERTaggedObject(true, 0, responseBytes));
        return new DERSequence(v);
    }

    OCSPResponseStatus responseStatus;
    ResponseBytes responseBytes;
}
