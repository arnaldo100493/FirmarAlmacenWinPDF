// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampDataUtil.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.*;
import co.org.bouncy.tsp.*;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.tsp.cms:
//            MetaDataUtil, ImprintDigestInvalidException

class TimeStampDataUtil
{

    TimeStampDataUtil(TimeStampedData timeStampedData)
    {
        metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        Evidence evidence = timeStampedData.getTemporalEvidence();
        timeStamps = evidence.getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampDataUtil(TimeStampedDataParser timeStampedData)
        throws IOException
    {
        metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        Evidence evidence = timeStampedData.getTemporalEvidence();
        timeStamps = evidence.getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampToken getTimeStampToken(TimeStampAndCRL timeStampAndCRL)
        throws CMSException
    {
        ContentInfo timeStampToken = timeStampAndCRL.getTimeStampToken();
        try
        {
            TimeStampToken token = new TimeStampToken(timeStampToken);
            return token;
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to parse token data: ").append(e.getMessage()).toString(), e);
        }
        catch(TSPException e)
        {
            if(e.getCause() instanceof CMSException)
                throw (CMSException)e.getCause();
            else
                throw new CMSException((new StringBuilder()).append("token data invalid: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException((new StringBuilder()).append("token data invalid: ").append(e.getMessage()).toString(), e);
        }
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator calculator)
        throws CMSException
    {
        metaDataUtil.initialiseMessageImprintDigestCalculator(calculator);
    }

    DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider calculatorProvider)
        throws OperatorCreationException
    {
        try
        {
            TimeStampToken token = getTimeStampToken(timeStamps[0]);
            TimeStampTokenInfo info = token.getTimeStampInfo();
            co.org.bouncy.asn1.ASN1ObjectIdentifier algOID = info.getMessageImprintAlgOID();
            DigestCalculator calc = calculatorProvider.get(new AlgorithmIdentifier(algOID));
            initialiseMessageImprintDigestCalculator(calc);
            return calc;
        }
        catch(CMSException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to extract algorithm ID: ").append(e.getMessage()).toString(), e);
        }
    }

    TimeStampToken[] getTimeStampTokens()
        throws CMSException
    {
        TimeStampToken tokens[] = new TimeStampToken[timeStamps.length];
        for(int i = 0; i < timeStamps.length; i++)
            tokens[i] = getTimeStampToken(timeStamps[i]);

        return tokens;
    }

    TimeStampAndCRL[] getTimeStamps()
    {
        return timeStamps;
    }

    byte[] calculateNextHash(DigestCalculator calculator)
        throws CMSException
    {
        TimeStampAndCRL tspToken = timeStamps[timeStamps.length - 1];
        OutputStream out = calculator.getOutputStream();
        try
        {
            out.write(tspToken.getEncoded("DER"));
            out.close();
            return calculator.getDigest();
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("exception calculating hash: ").append(e.getMessage()).toString(), e);
        }
    }

    void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[])
        throws ImprintDigestInvalidException, CMSException
    {
        byte currentDigest[] = dataDigest;
        for(int i = 0; i < timeStamps.length; i++)
            try
            {
                TimeStampToken token = getTimeStampToken(timeStamps[i]);
                if(i > 0)
                {
                    TimeStampTokenInfo info = token.getTimeStampInfo();
                    DigestCalculator calculator = calculatorProvider.get(info.getHashAlgorithm());
                    calculator.getOutputStream().write(timeStamps[i - 1].getEncoded("DER"));
                    currentDigest = calculator.getDigest();
                }
                compareDigest(token, currentDigest);
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("exception calculating hash: ").append(e.getMessage()).toString(), e);
            }
            catch(OperatorCreationException e)
            {
                throw new CMSException((new StringBuilder()).append("cannot create digest: ").append(e.getMessage()).toString(), e);
            }

    }

    void validate(DigestCalculatorProvider calculatorProvider, byte dataDigest[], TimeStampToken timeStampToken)
        throws ImprintDigestInvalidException, CMSException
    {
        byte currentDigest[] = dataDigest;
        byte encToken[];
        try
        {
            encToken = timeStampToken.getEncoded();
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("exception encoding timeStampToken: ").append(e.getMessage()).toString(), e);
        }
        for(int i = 0; i < timeStamps.length; i++)
            try
            {
                TimeStampToken token = getTimeStampToken(timeStamps[i]);
                if(i > 0)
                {
                    TimeStampTokenInfo info = token.getTimeStampInfo();
                    DigestCalculator calculator = calculatorProvider.get(info.getHashAlgorithm());
                    calculator.getOutputStream().write(timeStamps[i - 1].getEncoded("DER"));
                    currentDigest = calculator.getDigest();
                }
                compareDigest(token, currentDigest);
                if(Arrays.areEqual(token.getEncoded(), encToken))
                    return;
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("exception calculating hash: ").append(e.getMessage()).toString(), e);
            }
            catch(OperatorCreationException e)
            {
                throw new CMSException((new StringBuilder()).append("cannot create digest: ").append(e.getMessage()).toString(), e);
            }

        throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
    }

    private void compareDigest(TimeStampToken timeStampToken, byte digest[])
        throws ImprintDigestInvalidException
    {
        TimeStampTokenInfo info = timeStampToken.getTimeStampInfo();
        byte tsrMessageDigest[] = info.getMessageImprintDigest();
        if(!Arrays.areEqual(digest, tsrMessageDigest))
            throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
        else
            return;
    }

    String getFileName()
    {
        return metaDataUtil.getFileName();
    }

    String getMediaType()
    {
        return metaDataUtil.getMediaType();
    }

    AttributeTable getOtherMetaData()
    {
        return new AttributeTable(metaDataUtil.getOtherMetaData());
    }

    private final TimeStampAndCRL timeStamps[];
    private final MetaDataUtil metaDataUtil;
}
