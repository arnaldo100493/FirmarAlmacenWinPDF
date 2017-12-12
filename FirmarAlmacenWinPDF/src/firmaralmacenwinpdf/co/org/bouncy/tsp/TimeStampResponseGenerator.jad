// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampResponseGenerator.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIFreeText;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.tsp.TimeStampResp;
import co.org.bouncy.cms.CMSSignedData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;

// Referenced classes of package co.org.bouncy.tsp:
//            TSPValidationException, TSPException, TimeStampResponse, TimeStampRequest, 
//            TimeStampTokenGenerator, TimeStampToken

public class TimeStampResponseGenerator
{
    class FailInfo extends DERBitString
    {

        final TimeStampResponseGenerator this$0;

        FailInfo(int failInfoValue)
        {
            this$0 = TimeStampResponseGenerator.this;
            super(getBytes(failInfoValue), getPadBits(failInfoValue));
        }
    }


    public TimeStampResponseGenerator(TimeStampTokenGenerator tokenGenerator, Set acceptedAlgorithms)
    {
        this(tokenGenerator, acceptedAlgorithms, null, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator tokenGenerator, Set acceptedAlgorithms, Set acceptedPolicies)
    {
        this(tokenGenerator, acceptedAlgorithms, acceptedPolicies, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator tokenGenerator, Set acceptedAlgorithms, Set acceptedPolicies, Set acceptedExtensions)
    {
        this.tokenGenerator = tokenGenerator;
        this.acceptedAlgorithms = convert(acceptedAlgorithms);
        this.acceptedPolicies = convert(acceptedPolicies);
        this.acceptedExtensions = convert(acceptedExtensions);
        statusStrings = new ASN1EncodableVector();
    }

    private void addStatusString(String statusString)
    {
        statusStrings.add(new DERUTF8String(statusString));
    }

    private void setFailInfoField(int field)
    {
        failInfo = failInfo | field;
    }

    private PKIStatusInfo getPKIStatusInfo()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new DERInteger(status));
        if(statusStrings.size() > 0)
            v.add(PKIFreeText.getInstance(new DERSequence(statusStrings)));
        if(failInfo != 0)
        {
            DERBitString failInfoBitString = new FailInfo(failInfo);
            v.add(failInfoBitString);
        }
        return PKIStatusInfo.getInstance(new DERSequence(v));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public TimeStampResponse generate(TimeStampRequest request, BigInteger serialNumber, Date genTime, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, TSPException
    {
        TimeStampResp resp;
        try
        {
            if(genTime == null)
                throw new TSPValidationException("The time source is not available.", 512);
            request.validate(acceptedAlgorithms, acceptedPolicies, acceptedExtensions, provider);
            status = 0;
            addStatusString("Operation Okay");
            PKIStatusInfo pkiStatusInfo = getPKIStatusInfo();
            ContentInfo tstTokenContentInfo = null;
            try
            {
                ByteArrayInputStream bIn = new ByteArrayInputStream(tokenGenerator.generate(request, serialNumber, genTime, provider).toCMSSignedData().getEncoded());
                ASN1InputStream aIn = new ASN1InputStream(bIn);
                tstTokenContentInfo = ContentInfo.getInstance(aIn.readObject());
            }
            catch(IOException ioEx)
            {
                throw new TSPException("Timestamp token received cannot be converted to ContentInfo", ioEx);
            }
            resp = new TimeStampResp(pkiStatusInfo, tstTokenContentInfo);
        }
        catch(TSPValidationException e)
        {
            status = 2;
            setFailInfoField(e.getFailureCode());
            addStatusString(e.getMessage());
            PKIStatusInfo pkiStatusInfo = getPKIStatusInfo();
            resp = new TimeStampResp(pkiStatusInfo, null);
        }
        try
        {
            return new TimeStampResponse(resp);
        }
        catch(IOException e)
        {
            throw new TSPException("created badly formatted response!");
        }
    }

    public TimeStampResponse generate(TimeStampRequest request, BigInteger serialNumber, Date genTime)
        throws TSPException
    {
        try
        {
            return generateGrantedResponse(request, serialNumber, genTime, "Operation Okay");
        }
        catch(Exception e)
        {
            return generateRejectedResponse(e);
        }
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest request, BigInteger serialNumber, Date genTime)
        throws TSPException
    {
        return generateGrantedResponse(request, serialNumber, genTime, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest request, BigInteger serialNumber, Date genTime, String statusString)
        throws TSPException
    {
        if(genTime == null)
            throw new TSPValidationException("The time source is not available.", 512);
        request.validate(acceptedAlgorithms, acceptedPolicies, acceptedExtensions);
        status = 0;
        statusStrings = new ASN1EncodableVector();
        if(statusString != null)
            addStatusString(statusString);
        PKIStatusInfo pkiStatusInfo = getPKIStatusInfo();
        ContentInfo tstTokenContentInfo;
        try
        {
            tstTokenContentInfo = tokenGenerator.generate(request, serialNumber, genTime).toCMSSignedData().toASN1Structure();
        }
        catch(TSPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new TSPException("Timestamp token received cannot be converted to ContentInfo", e);
        }
        TimeStampResp resp = new TimeStampResp(pkiStatusInfo, tstTokenContentInfo);
        try
        {
            return new TimeStampResponse(resp);
        }
        catch(IOException e)
        {
            throw new TSPException("created badly formatted response!");
        }
    }

    public TimeStampResponse generateRejectedResponse(Exception exception)
        throws TSPException
    {
        if(exception instanceof TSPValidationException)
            return generateFailResponse(2, ((TSPValidationException)exception).getFailureCode(), exception.getMessage());
        else
            return generateFailResponse(2, 0x40000000, exception.getMessage());
    }

    public TimeStampResponse generateFailResponse(int status, int failInfoField, String statusString)
        throws TSPException
    {
        this.status = status;
        statusStrings = new ASN1EncodableVector();
        setFailInfoField(failInfoField);
        if(statusString != null)
            addStatusString(statusString);
        PKIStatusInfo pkiStatusInfo = getPKIStatusInfo();
        TimeStampResp resp = new TimeStampResp(pkiStatusInfo, null);
        try
        {
            return new TimeStampResponse(resp);
        }
        catch(IOException e)
        {
            throw new TSPException("created badly formatted response!");
        }
    }

    private Set convert(Set orig)
    {
        if(orig == null)
            return orig;
        Set con = new HashSet(orig.size());
        for(Iterator it = orig.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(o instanceof String)
                con.add(new ASN1ObjectIdentifier((String)o));
            else
                con.add(o);
        }

        return con;
    }

    int status;
    ASN1EncodableVector statusStrings;
    int failInfo;
    private TimeStampTokenGenerator tokenGenerator;
    private Set acceptedAlgorithms;
    private Set acceptedPolicies;
    private Set acceptedExtensions;
}
