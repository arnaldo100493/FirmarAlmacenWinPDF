// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampResp.java

package co.org.bouncy.asn1.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.cms.ContentInfo;
import java.util.Enumeration;

public class TimeStampResp extends ASN1Object
{

    public static TimeStampResp getInstance(Object o)
    {
        if(o instanceof TimeStampResp)
            return (TimeStampResp)o;
        if(o != null)
            return new TimeStampResp(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private TimeStampResp(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        pkiStatusInfo = PKIStatusInfo.getInstance(e.nextElement());
        if(e.hasMoreElements())
            timeStampToken = ContentInfo.getInstance(e.nextElement());
    }

    public TimeStampResp(PKIStatusInfo pkiStatusInfo, ContentInfo timeStampToken)
    {
        this.pkiStatusInfo = pkiStatusInfo;
        this.timeStampToken = timeStampToken;
    }

    public PKIStatusInfo getStatus()
    {
        return pkiStatusInfo;
    }

    public ContentInfo getTimeStampToken()
    {
        return timeStampToken;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(pkiStatusInfo);
        if(timeStampToken != null)
            v.add(timeStampToken);
        return new DERSequence(v);
    }

    PKIStatusInfo pkiStatusInfo;
    ContentInfo timeStampToken;
}
