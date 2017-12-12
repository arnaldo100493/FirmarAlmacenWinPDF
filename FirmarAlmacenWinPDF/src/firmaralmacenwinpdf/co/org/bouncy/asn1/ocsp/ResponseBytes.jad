// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseBytes.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;

public class ResponseBytes extends ASN1Object
{

    public ResponseBytes(ASN1ObjectIdentifier responseType, ASN1OctetString response)
    {
        this.responseType = responseType;
        this.response = response;
    }

    public ResponseBytes(ASN1Sequence seq)
    {
        responseType = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        response = (ASN1OctetString)seq.getObjectAt(1);
    }

    public static ResponseBytes getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ResponseBytes getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ResponseBytes))
            return (ResponseBytes)obj;
        if(obj instanceof ASN1Sequence)
            return new ResponseBytes((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass().getName()).toString());
    }

    public ASN1ObjectIdentifier getResponseType()
    {
        return responseType;
    }

    public ASN1OctetString getResponse()
    {
        return response;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(responseType);
        v.add(response);
        return new DERSequence(v);
    }

    ASN1ObjectIdentifier responseType;
    ASN1OctetString response;
}
