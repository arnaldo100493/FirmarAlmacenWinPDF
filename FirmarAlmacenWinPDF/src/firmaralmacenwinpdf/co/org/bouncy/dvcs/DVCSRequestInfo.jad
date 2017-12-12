// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSRequestInfo.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.dvcs.*;
import co.org.bouncy.asn1.x509.GeneralNames;
import co.org.bouncy.asn1.x509.PolicyInformation;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.tsp.TimeStampTokenInfo;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSParsingException

public class DVCSRequestInfo
{

    public DVCSRequestInfo(byte in[])
    {
        this(DVCSRequestInformation.getInstance(in));
    }

    public DVCSRequestInfo(DVCSRequestInformation data)
    {
        this.data = data;
    }

    public DVCSRequestInformation toASN1Structure()
    {
        return data;
    }

    public int getVersion()
    {
        return data.getVersion();
    }

    public int getServiceType()
    {
        return data.getService().getValue().intValue();
    }

    public BigInteger getNonce()
    {
        return data.getNonce();
    }

    public Date getRequestTime()
        throws DVCSParsingException
    {
        DVCSTime time = data.getRequestTime();
        if(time == null)
            return null;
        TimeStampToken token;
        try
        {
            if(time.getGenTime() != null)
                return time.getGenTime().getDate();
        }
        catch(Exception e)
        {
            throw new DVCSParsingException((new StringBuilder()).append("unable to extract time: ").append(e.getMessage()).toString(), e);
        }
        token = new TimeStampToken(time.getTimeStampToken());
        return token.getTimeStampInfo().getGenTime();
    }

    public GeneralNames getRequester()
    {
        return data.getRequester();
    }

    public PolicyInformation getRequestPolicy()
    {
        if(data.getRequestPolicy() != null)
            return data.getRequestPolicy();
        else
            return null;
    }

    public GeneralNames getDVCSNames()
    {
        return data.getDVCS();
    }

    public GeneralNames getDataLocations()
    {
        return data.getDataLocations();
    }

    public static boolean validate(DVCSRequestInfo requestInfo, DVCSRequestInfo responseInfo)
    {
        DVCSRequestInformation clientInfo = requestInfo.data;
        DVCSRequestInformation serverInfo = responseInfo.data;
        if(clientInfo.getVersion() != serverInfo.getVersion())
            return false;
        if(!clientEqualsServer(clientInfo.getService(), serverInfo.getService()))
            return false;
        if(!clientEqualsServer(clientInfo.getRequestTime(), serverInfo.getRequestTime()))
            return false;
        if(!clientEqualsServer(clientInfo.getRequestPolicy(), serverInfo.getRequestPolicy()))
            return false;
        if(!clientEqualsServer(clientInfo.getExtensions(), serverInfo.getExtensions()))
            return false;
        if(clientInfo.getNonce() != null)
        {
            if(serverInfo.getNonce() == null)
                return false;
            byte clientNonce[] = clientInfo.getNonce().toByteArray();
            byte serverNonce[] = serverInfo.getNonce().toByteArray();
            if(serverNonce.length < clientNonce.length)
                return false;
            if(!Arrays.areEqual(clientNonce, Arrays.copyOfRange(serverNonce, 0, clientNonce.length)))
                return false;
        }
        return true;
    }

    private static boolean clientEqualsServer(Object client, Object server)
    {
        return client == null && server == null || client != null && client.equals(server);
    }

    private DVCSRequestInformation data;
}
