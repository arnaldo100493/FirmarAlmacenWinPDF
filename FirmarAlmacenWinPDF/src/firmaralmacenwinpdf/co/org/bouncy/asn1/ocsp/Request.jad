// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Request.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Extensions;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            CertID

public class Request extends ASN1Object
{

    public Request(CertID reqCert, Extensions singleRequestExtensions)
    {
        this.reqCert = reqCert;
        this.singleRequestExtensions = singleRequestExtensions;
    }

    private Request(ASN1Sequence seq)
    {
        reqCert = CertID.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
            singleRequestExtensions = Extensions.getInstance((ASN1TaggedObject)seq.getObjectAt(1), true);
    }

    public static Request getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static Request getInstance(Object obj)
    {
        if(obj instanceof Request)
            return (Request)obj;
        if(obj != null)
            return new Request(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public CertID getReqCert()
    {
        return reqCert;
    }

    public Extensions getSingleRequestExtensions()
    {
        return singleRequestExtensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(reqCert);
        if(singleRequestExtensions != null)
            v.add(new DERTaggedObject(true, 0, singleRequestExtensions));
        return new DERSequence(v);
    }

    CertID reqCert;
    Extensions singleRequestExtensions;
}
