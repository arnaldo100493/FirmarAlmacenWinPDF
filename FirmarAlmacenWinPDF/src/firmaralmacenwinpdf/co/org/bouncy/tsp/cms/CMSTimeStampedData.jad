// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSTimeStampedData.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.CMSSignedData;
import co.org.bouncy.operator.*;
import co.org.bouncy.tsp.TimeStampToken;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

// Referenced classes of package co.org.bouncy.tsp.cms:
//            TimeStampDataUtil, ImprintDigestInvalidException

public class CMSTimeStampedData
{

    public CMSTimeStampedData(ContentInfo contentInfo)
    {
        initialize(contentInfo);
    }

    public CMSTimeStampedData(InputStream in)
        throws IOException
    {
        try
        {
            initialize(ContentInfo.getInstance((new ASN1InputStream(in)).readObject()));
        }
        catch(ClassCastException e)
        {
            throw new IOException((new StringBuilder()).append("Malformed content: ").append(e).toString());
        }
        catch(IllegalArgumentException e)
        {
            throw new IOException((new StringBuilder()).append("Malformed content: ").append(e).toString());
        }
    }

    public CMSTimeStampedData(byte baseData[])
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(baseData))));
    }

    private void initialize(ContentInfo contentInfo)
    {
        this.contentInfo = contentInfo;
        if(CMSObjectIdentifiers.timestampedData.equals(contentInfo.getContentType()))
            timeStampedData = TimeStampedData.getInstance(contentInfo.getContent());
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Malformed content - type must be ").append(CMSObjectIdentifiers.timestampedData.getId()).toString());
        util = new TimeStampDataUtil(timeStampedData);
    }

    public byte[] calculateNextHash(DigestCalculator calculator)
        throws CMSException
    {
        return util.calculateNextHash(calculator);
    }

    public CMSTimeStampedData addTimeStamp(TimeStampToken token)
        throws CMSException
    {
        TimeStampAndCRL timeStamps[] = util.getTimeStamps();
        TimeStampAndCRL newTimeStamps[] = new TimeStampAndCRL[timeStamps.length + 1];
        System.arraycopy(timeStamps, 0, newTimeStamps, 0, timeStamps.length);
        newTimeStamps[timeStamps.length] = new TimeStampAndCRL(token.toCMSSignedData().toASN1Structure());
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(timeStampedData.getDataUri(), timeStampedData.getMetaData(), timeStampedData.getContent(), new Evidence(new TimeStampTokenEvidence(newTimeStamps)))));
    }

    public byte[] getContent()
    {
        if(timeStampedData.getContent() != null)
            return timeStampedData.getContent().getOctets();
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

    public TimeStampToken[] getTimeStampTokens()
        throws CMSException
    {
        return util.getTimeStampTokens();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator calculator)
        throws CMSException
    {
        util.initialiseMessageImprintDigestCalculator(calculator);
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider calculatorProvider)
        throws OperatorCreationException
    {
        return util.getMessageImprintDigestCalculator(calculatorProvider);
    }

    public void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[])
        throws ImprintDigestInvalidException, CMSException
    {
        util.validate(calculatorProvider, dataDigest);
    }

    public void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[], TimeStampToken timeStampToken)
        throws ImprintDigestInvalidException, CMSException
    {
        util.validate(calculatorProvider, dataDigest, timeStampToken);
    }

    public byte[] getEncoded()
        throws IOException
    {
        return contentInfo.getEncoded();
    }

    private TimeStampedData timeStampedData;
    private ContentInfo contentInfo;
    private TimeStampDataUtil util;
}
