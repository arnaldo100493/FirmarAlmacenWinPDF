// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSTimeStampedDataParser.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.cms.CMSContentInfoParser;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.*;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.util.io.Streams;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

// Referenced classes of package co.org.bouncy.tsp.cms:
//            TimeStampDataUtil, ImprintDigestInvalidException

public class CMSTimeStampedDataParser extends CMSContentInfoParser
{

    public CMSTimeStampedDataParser(InputStream in)
        throws CMSException
    {
        super(in);
        initialize(_contentInfo);
    }

    public CMSTimeStampedDataParser(byte baseData[])
        throws CMSException
    {
        this(((InputStream) (new ByteArrayInputStream(baseData))));
    }

    private void initialize(ContentInfoParser contentInfo)
        throws CMSException
    {
        try
        {
            if(CMSObjectIdentifiers.timestampedData.equals(contentInfo.getContentType()))
                timeStampedData = TimeStampedDataParser.getInstance(contentInfo.getContent(16));
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Malformed content - type must be ").append(CMSObjectIdentifiers.timestampedData.getId()).toString());
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("parsing exception: ").append(e.getMessage()).toString(), e);
        }
    }

    public byte[] calculateNextHash(DigestCalculator calculator)
        throws CMSException
    {
        return util.calculateNextHash(calculator);
    }

    public InputStream getContent()
    {
        if(timeStampedData.getContent() != null)
            return timeStampedData.getContent().getOctetStream();
        else
            return null;
    }

    public URI getDataUri()
        throws URISyntaxException
    {
        DERIA5String dataURI = timeStampedData.getDataUri();
        if(dataURI != null)
            return new URI(dataURI.getString());
        else
            return null;
    }

    public String getFileName()
    {
        return util.getFileName();
    }

    public String getMediaType()
    {
        return util.getMediaType();
    }

    public AttributeTable getOtherMetaData()
    {
        return util.getOtherMetaData();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator calculator)
        throws CMSException
    {
        util.initialiseMessageImprintDigestCalculator(calculator);
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider calculatorProvider)
        throws OperatorCreationException
    {
        try
        {
            parseTimeStamps();
        }
        catch(CMSException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to extract algorithm ID: ").append(e.getMessage()).toString(), e);
        }
        return util.getMessageImprintDigestCalculator(calculatorProvider);
    }

    public TimeStampToken[] getTimeStampTokens()
        throws CMSException
    {
        parseTimeStamps();
        return util.getTimeStampTokens();
    }

    public void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[])
        throws ImprintDigestInvalidException, CMSException
    {
        parseTimeStamps();
        util.validate(calculatorProvider, dataDigest);
    }

    public void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[], TimeStampToken timeStampToken)
        throws ImprintDigestInvalidException, CMSException
    {
        parseTimeStamps();
        util.validate(calculatorProvider, dataDigest, timeStampToken);
    }

    private void parseTimeStamps()
        throws CMSException
    {
        try
        {
            if(util == null)
            {
                InputStream cont = getContent();
                if(cont != null)
                    Streams.drain(cont);
                util = new TimeStampDataUtil(timeStampedData);
            }
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to parse evidence block: ").append(e.getMessage()).toString(), e);
        }
    }

    private TimeStampedDataParser timeStampedData;
    private TimeStampDataUtil util;
}
