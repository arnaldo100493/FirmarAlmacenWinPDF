// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespData.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ocsp.ResponseData;
import co.org.bouncy.asn1.ocsp.SingleResponse;
import co.org.bouncy.asn1.x509.Extensions;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            RespID, SingleResp, OCSPUtils

public class RespData
{

    public RespData(ResponseData data)
    {
        this.data = data;
    }

    public int getVersion()
    {
        return data.getVersion().getValue().intValue() + 1;
    }

    public RespID getResponderId()
    {
        return new RespID(data.getResponderID());
    }

    public Date getProducedAt()
    {
        return OCSPUtils.extractDate(data.getProducedAt());
    }

    public SingleResp[] getResponses()
    {
        ASN1Sequence s = data.getResponses();
        SingleResp rs[] = new SingleResp[s.size()];
        for(int i = 0; i != rs.length; i++)
            rs[i] = new SingleResp(SingleResponse.getInstance(s.getObjectAt(i)));

        return rs;
    }

    public Extensions getResponseExtensions()
    {
        return data.getResponseExtensions();
    }

    private ResponseData data;
}
