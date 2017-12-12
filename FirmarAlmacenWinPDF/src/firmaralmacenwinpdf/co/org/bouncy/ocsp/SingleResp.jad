// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SingleResp.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.ocsp.CertStatus;
import co.org.bouncy.asn1.ocsp.RevokedInfo;
import co.org.bouncy.asn1.ocsp.SingleResponse;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.security.cert.X509Extension;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package co.org.bouncy.ocsp:
//            CertificateID, RevokedStatus, UnknownStatus

public class SingleResp
    implements X509Extension
{

    public SingleResp(SingleResponse resp)
    {
        this.resp = resp;
    }

    public CertificateID getCertID()
    {
        return new CertificateID(resp.getCertID());
    }

    public Object getCertStatus()
    {
        CertStatus s = resp.getCertStatus();
        if(s.getTagNo() == 0)
            return null;
        if(s.getTagNo() == 1)
            return new RevokedStatus(RevokedInfo.getInstance(s.getStatus()));
        else
            return new UnknownStatus();
    }

    public Date getThisUpdate()
    {
        try
        {
            return resp.getThisUpdate().getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("ParseException: ").append(e.getMessage()).toString());
        }
    }

    public Date getNextUpdate()
    {
        if(resp.getNextUpdate() == null)
            return null;
        try
        {
            return resp.getNextUpdate().getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("ParseException: ").append(e.getMessage()).toString());
        }
    }

    public X509Extensions getSingleExtensions()
    {
        return X509Extensions.getInstance(resp.getSingleExtensions());
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Set set = new HashSet();
        X509Extensions extensions = getSingleExtensions();
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
        X509Extensions exts = getSingleExtensions();
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

    SingleResponse resp;
}
