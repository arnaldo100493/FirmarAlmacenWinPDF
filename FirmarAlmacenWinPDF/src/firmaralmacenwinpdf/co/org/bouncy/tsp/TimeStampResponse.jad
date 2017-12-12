// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampResponse.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.*;
import co.org.bouncy.asn1.cms.Attribute;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.tsp.TimeStampResp;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampToken, TSPException, TSPValidationException, TimeStampTokenInfo, 
//            TimeStampRequest

public class TimeStampResponse
{

    public TimeStampResponse(TimeStampResp resp)
        throws TSPException, IOException
    {
        this.resp = resp;
        if(resp.getTimeStampToken() != null)
            timeStampToken = new TimeStampToken(resp.getTimeStampToken());
    }

    public TimeStampResponse(byte resp[])
        throws TSPException, IOException
    {
        this(((InputStream) (new ByteArrayInputStream(resp))));
    }

    public TimeStampResponse(InputStream in)
        throws TSPException, IOException
    {
        this(readTimeStampResp(in));
    }

    private static TimeStampResp readTimeStampResp(InputStream in)
        throws IOException, TSPException
    {
        try
        {
            return TimeStampResp.getInstance((new ASN1InputStream(in)).readObject());
        }
        catch(IllegalArgumentException e)
        {
            throw new TSPException((new StringBuilder()).append("malformed timestamp response: ").append(e).toString(), e);
        }
        catch(ClassCastException e)
        {
            throw new TSPException((new StringBuilder()).append("malformed timestamp response: ").append(e).toString(), e);
        }
    }

    public int getStatus()
    {
        return resp.getStatus().getStatus().intValue();
    }

    public String getStatusString()
    {
        if(resp.getStatus().getStatusString() != null)
        {
            StringBuffer statusStringBuf = new StringBuffer();
            PKIFreeText text = resp.getStatus().getStatusString();
            for(int i = 0; i != text.size(); i++)
                statusStringBuf.append(text.getStringAt(i).getString());

            return statusStringBuf.toString();
        } else
        {
            return null;
        }
    }

    public PKIFailureInfo getFailInfo()
    {
        if(resp.getStatus().getFailInfo() != null)
            return new PKIFailureInfo(resp.getStatus().getFailInfo());
        else
            return null;
    }

    public TimeStampToken getTimeStampToken()
    {
        return timeStampToken;
    }

    public void validate(TimeStampRequest request)
        throws TSPException
    {
        TimeStampToken tok = getTimeStampToken();
        if(tok != null)
        {
            TimeStampTokenInfo tstInfo = tok.getTimeStampInfo();
            if(request.getNonce() != null && !request.getNonce().equals(tstInfo.getNonce()))
                throw new TSPValidationException("response contains wrong nonce value.");
            if(getStatus() != 0 && getStatus() != 1)
                throw new TSPValidationException("time stamp token found in failed request.");
            if(!Arrays.constantTimeAreEqual(request.getMessageImprintDigest(), tstInfo.getMessageImprintDigest()))
                throw new TSPValidationException("response for different message imprint digest.");
            if(!tstInfo.getMessageImprintAlgOID().equals(request.getMessageImprintAlgOID()))
                throw new TSPValidationException("response for different message imprint algorithm.");
            Attribute scV1 = tok.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            Attribute scV2 = tok.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
            if(scV1 == null && scV2 == null)
                throw new TSPValidationException("no signing certificate attribute present.");
            if(scV1 != null)
                if(scV2 == null);
            if(request.getReqPolicy() != null && !request.getReqPolicy().equals(tstInfo.getPolicy()))
                throw new TSPValidationException("TSA policy wrong for request.");
        } else
        if(getStatus() == 0 || getStatus() == 1)
            throw new TSPValidationException("no time stamp token found and one expected.");
    }

    public byte[] getEncoded()
        throws IOException
    {
        return resp.getEncoded();
    }

    TimeStampResp resp;
    TimeStampToken timeStampToken;
}
