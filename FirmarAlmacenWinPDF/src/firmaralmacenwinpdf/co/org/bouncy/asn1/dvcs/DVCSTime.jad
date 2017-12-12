// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSTime.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.ContentInfo;
import java.util.Date;

public class DVCSTime extends ASN1Object
    implements ASN1Choice
{

    public DVCSTime(Date time)
    {
        this(new ASN1GeneralizedTime(time));
    }

    public DVCSTime(ASN1GeneralizedTime genTime)
    {
        this.genTime = genTime;
    }

    public DVCSTime(ContentInfo timeStampToken)
    {
        this.timeStampToken = timeStampToken;
    }

    public static DVCSTime getInstance(Object obj)
    {
        if(obj instanceof DVCSTime)
            return (DVCSTime)obj;
        if(obj instanceof ASN1GeneralizedTime)
            return new DVCSTime(ASN1GeneralizedTime.getInstance(obj));
        if(obj != null)
            return new DVCSTime(ContentInfo.getInstance(obj));
        else
            return null;
    }

    public static DVCSTime getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public ASN1GeneralizedTime getGenTime()
    {
        return genTime;
    }

    public ContentInfo getTimeStampToken()
    {
        return timeStampToken;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(genTime != null)
            return genTime;
        if(timeStampToken != null)
            return timeStampToken.toASN1Primitive();
        else
            return null;
    }

    public String toString()
    {
        if(genTime != null)
            return genTime.toString();
        if(timeStampToken != null)
            return timeStampToken.toString();
        else
            return null;
    }

    private ASN1GeneralizedTime genTime;
    private ContentInfo timeStampToken;
    private Date time;
}
