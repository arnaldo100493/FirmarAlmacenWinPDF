// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSRequest.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;

public class TBSRequest extends ASN1Object
{

    /**
     * @deprecated Method TBSRequest is deprecated
     */

    public TBSRequest(GeneralName requestorName, ASN1Sequence requestList, X509Extensions requestExtensions)
    {
        version = V1;
        this.requestorName = requestorName;
        this.requestList = requestList;
        this.requestExtensions = Extensions.getInstance(requestExtensions);
    }

    public TBSRequest(GeneralName requestorName, ASN1Sequence requestList, Extensions requestExtensions)
    {
        version = V1;
        this.requestorName = requestorName;
        this.requestList = requestList;
        this.requestExtensions = requestExtensions;
    }

    private TBSRequest(ASN1Sequence seq)
    {
        int index = 0;
        if(seq.getObjectAt(0) instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)seq.getObjectAt(0);
            if(o.getTagNo() == 0)
            {
                versionSet = true;
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
        if(seq.getObjectAt(index) instanceof ASN1TaggedObject)
            requestorName = GeneralName.getInstance((ASN1TaggedObject)seq.getObjectAt(index++), true);
        requestList = (ASN1Sequence)seq.getObjectAt(index++);
        if(seq.size() == index + 1)
            requestExtensions = Extensions.getInstance((ASN1TaggedObject)seq.getObjectAt(index), true);
    }

    public static TBSRequest getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSRequest getInstance(Object obj)
    {
        if(obj instanceof TBSRequest)
            return (TBSRequest)obj;
        if(obj != null)
            return new TBSRequest(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public GeneralName getRequestorName()
    {
        return requestorName;
    }

    public ASN1Sequence getRequestList()
    {
        return requestList;
    }

    public Extensions getRequestExtensions()
    {
        return requestExtensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(!version.equals(V1) || versionSet)
            v.add(new DERTaggedObject(true, 0, version));
        if(requestorName != null)
            v.add(new DERTaggedObject(true, 1, requestorName));
        v.add(requestList);
        if(requestExtensions != null)
            v.add(new DERTaggedObject(true, 2, requestExtensions));
        return new DERSequence(v);
    }

    private static final ASN1Integer V1 = new ASN1Integer(0L);
    ASN1Integer version;
    GeneralName requestorName;
    ASN1Sequence requestList;
    Extensions requestExtensions;
    boolean versionSet;

}
