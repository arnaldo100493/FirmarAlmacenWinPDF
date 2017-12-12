// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespData.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.ocsp.ResponseData;
import co.org.bouncy.asn1.ocsp.SingleResponse;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.math.BigInteger;
import java.security.cert.X509Extension;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package co.org.bouncy.ocsp:
//            RespID, SingleResp

public class RespData
    implements X509Extension
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
        try
        {
            return data.getProducedAt().getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("ParseException:").append(e.getMessage()).toString());
        }
    }

    public SingleResp[] getResponses()
    {
        ASN1Sequence s = data.getResponses();
        SingleResp rs[] = new SingleResp[s.size()];
        for(int i = 0; i != rs.length; i++)
            rs[i] = new SingleResp(SingleResponse.getInstance(s.getObjectAt(i)));

        return rs;
    }

    public X509Extensions getResponseExtensions()
    {
        return X509Extensions.getInstance(data.getResponseExtensions());
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Set set = new HashSet();
        X509Extensions extensions = getResponseExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            do
            {
                if(!e.hasMoreElements())
                    break;
                DERObjectIdentifier oid = (DERObjectIdentifier)e.nextElement();
                co.org.bouncy.asn1.x509.X509Extension ext = extensions.getExtension(oid);
                if(critical == ext.isCritical())
                    set.add(oid.getId());
            } while(true);
        }
        return set;
    }

    public Set getCriticalExtensionOIDs()
    {
        return getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return getExtensionOIDs(false);
    }

    public byte[] getExtensionValue(String oid)
    {
        X509Extensions exts = getResponseExtensions();
        if(exts != null)
        {
            co.org.bouncy.asn1.x509.X509Extension ext = exts.getExtension(new DERObjectIdentifier(oid));
            if(ext != null)
                try
                {
                    return ext.getValue().getEncoded("DER");
                }
                catch(Exception e)
                {
                    throw new RuntimeException((new StringBuilder()).append("error encoding ").append(e.toString()).toString());
                }
        }
        return null;
    }

    ResponseData data;
}
