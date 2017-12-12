// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseData.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.X509Extensions;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            ResponderID

public class ResponseData extends ASN1Object
{

    public ResponseData(ASN1Integer version, ResponderID responderID, ASN1GeneralizedTime producedAt, ASN1Sequence responses, Extensions responseExtensions)
    {
        this.version = version;
        this.responderID = responderID;
        this.producedAt = producedAt;
        this.responses = responses;
        this.responseExtensions = responseExtensions;
    }

    /**
     * @deprecated Method ResponseData is deprecated
     */

    public ResponseData(ResponderID responderID, DERGeneralizedTime producedAt, ASN1Sequence responses, X509Extensions responseExtensions)
    {
        this(V1, responderID, ASN1GeneralizedTime.getInstance(producedAt), responses, Extensions.getInstance(responseExtensions));
    }

    public ResponseData(ResponderID responderID, ASN1GeneralizedTime producedAt, ASN1Sequence responses, Extensions responseExtensions)
    {
        this(V1, responderID, producedAt, responses, responseExtensions);
    }

    private ResponseData(ASN1Sequence seq)
    {
        int index = 0;
        if(seq.getObjectAt(0) instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)seq.getObjectAt(0);
            if(o.getTagNo() == 0)
            {
                versionPresent = true;
                version = ASN1Integer.getInstance((ASN1TaggedObject)seq.getObjectAt(0), true);
                index++;
            } else
            {
                version = V1;
            }
        } else
        {
            version = V1;
        }
        responderID = ResponderID.getInstance(seq.getObjectAt(index++));
        producedAt = ASN1GeneralizedTime.getInstance(seq.getObjectAt(index++));
        responses = (ASN1Sequence)seq.getObjectAt(index++);
        if(seq.size() > index)
            responseExtensions = Extensions.getInstance((ASN1TaggedObject)seq.getObjectAt(index), true);
    }

    public static ResponseData getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ResponseData getInstance(Object obj)
    {
        if(obj instanceof ResponseData)
            return (ResponseData)obj;
        if(obj != null)
            return new ResponseData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public ResponderID getResponderID()
    {
        return responderID;
    }

    public ASN1GeneralizedTime getProducedAt()
    {
        return producedAt;
    }

    public ASN1Sequence getResponses()
    {
        return responses;
    }

    public Extensions getResponseExtensions()
    {
        return responseExtensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(versionPresent || !version.equals(V1))
            v.add(new DERTaggedObject(true, 0, version));
        v.add(responderID);
        v.add(producedAt);
        v.add(responses);
        if(responseExtensions != null)
            v.add(new DERTaggedObject(true, 1, responseExtensions));
        return new DERSequence(v);
    }

    private static final ASN1Integer V1 = new ASN1Integer(0L);
    private boolean versionPresent;
    private ASN1Integer version;
    private ResponderID responderID;
    private ASN1GeneralizedTime producedAt;
    private ASN1Sequence responses;
    private Extensions responseExtensions;

}
