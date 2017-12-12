// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSTimeStampedDataGenerator.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSSignedData;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.util.io.Streams;
import java.io.*;
import java.net.URI;

// Referenced classes of package co.org.bouncy.tsp.cms:
//            CMSTimeStampedGenerator, CMSTimeStampedData

public class CMSTimeStampedDataGenerator extends CMSTimeStampedGenerator
{

    public CMSTimeStampedDataGenerator()
    {
    }

    public CMSTimeStampedData generate(TimeStampToken timeStamp)
        throws CMSException
    {
        return generate(timeStamp, (InputStream)null);
    }

    public CMSTimeStampedData generate(TimeStampToken timeStamp, byte content[])
        throws CMSException
    {
        return generate(timeStamp, ((InputStream) (new ByteArrayInputStream(content))));
    }

    public CMSTimeStampedData generate(TimeStampToken timeStamp, InputStream content)
        throws CMSException
    {
        ByteArrayOutputStream contentOut = new ByteArrayOutputStream();
        if(content != null)
            try
            {
                Streams.pipeAll(content, contentOut);
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("exception encapsulating content: ").append(e.getMessage()).toString(), e);
            }
        ASN1OctetString encContent = null;
        if(contentOut.size() != 0)
            encContent = new BEROctetString(contentOut.toByteArray());
        TimeStampAndCRL stamp = new TimeStampAndCRL(timeStamp.toCMSSignedData().toASN1Structure());
        DERIA5String asn1DataUri = null;
        if(dataUri != null)
            asn1DataUri = new DERIA5String(dataUri.toString());
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(asn1DataUri, metaData, encContent, new Evidence(new TimeStampTokenEvidence(stamp)))));
    }
}
