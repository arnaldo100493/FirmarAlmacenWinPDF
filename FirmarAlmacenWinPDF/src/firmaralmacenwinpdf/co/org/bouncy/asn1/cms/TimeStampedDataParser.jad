// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampedDataParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            MetaData, Evidence

public class TimeStampedDataParser
{

    private TimeStampedDataParser(ASN1SequenceParser parser)
        throws IOException
    {
        this.parser = parser;
        version = ASN1Integer.getInstance(parser.readObject());
        ASN1Encodable obj = parser.readObject();
        if(obj instanceof DERIA5String)
        {
            dataUri = DERIA5String.getInstance(obj);
            obj = parser.readObject();
        }
        if((obj instanceof MetaData) || (obj instanceof ASN1SequenceParser))
        {
            metaData = MetaData.getInstance(obj.toASN1Primitive());
            obj = parser.readObject();
        }
        if(obj instanceof ASN1OctetStringParser)
            content = (ASN1OctetStringParser)obj;
    }

    public static TimeStampedDataParser getInstance(Object obj)
        throws IOException
    {
        if(obj instanceof ASN1Sequence)
            return new TimeStampedDataParser(((ASN1Sequence)obj).parser());
        if(obj instanceof ASN1SequenceParser)
            return new TimeStampedDataParser((ASN1SequenceParser)obj);
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

    public ASN1OctetStringParser getContent()
    {
        return content;
    }

    public Evidence getTemporalEvidence()
        throws IOException
    {
        if(temporalEvidence == null)
            temporalEvidence = Evidence.getInstance(parser.readObject().toASN1Primitive());
        return temporalEvidence;
    }

    /**
     * @deprecated Method toASN1Primitive is deprecated
     */

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
    private ASN1OctetStringParser content;
    private Evidence temporalEvidence;
    private ASN1SequenceParser parser;
}
