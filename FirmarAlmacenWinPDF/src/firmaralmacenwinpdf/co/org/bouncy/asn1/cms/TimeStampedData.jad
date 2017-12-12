// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            MetaData, Evidence

public class TimeStampedData extends ASN1Object
{

    public TimeStampedData(DERIA5String dataUri, MetaData metaData, ASN1OctetString content, Evidence temporalEvidence)
    {
        version = new ASN1Integer(1L);
        this.dataUri = dataUri;
        this.metaData = metaData;
        this.content = content;
        this.temporalEvidence = temporalEvidence;
    }

    private TimeStampedData(ASN1Sequence seq)
    {
        version = ASN1Integer.getInstance(seq.getObjectAt(0));
        int index = 1;
        if(seq.getObjectAt(index) instanceof DERIA5String)
            dataUri = DERIA5String.getInstance(seq.getObjectAt(index++));
        if((seq.getObjectAt(index) instanceof MetaData) || (seq.getObjectAt(index) instanceof ASN1Sequence))
            metaData = MetaData.getInstance(seq.getObjectAt(index++));
        if(seq.getObjectAt(index) instanceof ASN1OctetString)
            content = ASN1OctetString.getInstance(seq.getObjectAt(index++));
        temporalEvidence = Evidence.getInstance(seq.getObjectAt(index));
    }

    public static TimeStampedData getInstance(Object obj)
    {
        if(obj instanceof TimeStampedData)
            return (TimeStampedData)obj;
        if(obj != null)
            return new TimeStampedData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public DERIA5String getDataUri()
    {
        return dataUri;
    }

    public MetaData getMetaData()
    {
        return metaData;
    }

    public ASN1OctetString getContent()
    {
        return content;
    }

    public Evidence getTemporalEvidence()
    {
        return temporalEvidence;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        if(dataUri != null)
            v.add(dataUri);
        if(metaData != null)
            v.add(metaData);
        if(content != null)
            v.add(content);
        v.add(temporalEvidence);
        return new BERSequence(v);
    }

    private ASN1Integer version;
    private DERIA5String dataUri;
    private MetaData metaData;
    private ASN1OctetString content;
    private Evidence temporalEvidence;
}
