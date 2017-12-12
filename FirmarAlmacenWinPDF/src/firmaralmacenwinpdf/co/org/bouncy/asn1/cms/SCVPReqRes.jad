// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SCVPReqRes.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfo

public class SCVPReqRes extends ASN1Object
{

    public static SCVPReqRes getInstance(Object obj)
    {
        if(obj instanceof SCVPReqRes)
            return (SCVPReqRes)obj;
        if(obj != null)
            return new SCVPReqRes(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SCVPReqRes(ASN1Sequence seq)
    {
        if(seq.getObjectAt(0) instanceof ASN1TaggedObject)
        {
            request = ContentInfo.getInstance(ASN1TaggedObject.getInstance(seq.getObjectAt(0)), true);
            response = ContentInfo.getInstance(seq.getObjectAt(1));
        } else
        {
            request = null;
            response = ContentInfo.getInstance(seq.getObjectAt(0));
        }
    }

    public SCVPReqRes(ContentInfo response)
    {
        request = null;
        this.response = response;
    }

    public SCVPReqRes(ContentInfo request, ContentInfo response)
    {
        this.request = request;
        this.response = response;
    }

    public ContentInfo getRequest()
    {
        return request;
    }

    public ContentInfo getResponse()
    {
        return response;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(request != null)
            v.add(new DERTaggedObject(true, 0, request));
        v.add(response);
        return new DERSequence(v);
    }

    private final ContentInfo request;
    private final ContentInfo response;
}
