// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SingleResp.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            CertificateID, RevokedStatus, UnknownStatus, OCSPUtils, 
//            CertificateStatus

public class SingleResp
{

    public SingleResp(SingleResponse resp)
    {
        this.resp = resp;
        extensions = resp.getSingleExtensions();
    }

    public CertificateID getCertID()
    {
        return new CertificateID(resp.getCertID());
    }

    public CertificateStatus getCertStatus()
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
        return OCSPUtils.extractDate(resp.getThisUpdate());
    }

    public Date getNextUpdate()
    {
        if(resp.getNextUpdate() == null)
            return null;
        else
            return OCSPUtils.extractDate(resp.getNextUpdate());
    }

    public boolean hasExtensions()
    {
        return extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        if(extensions != null)
            return extensions.getExtension(oid);
        else
            return null;
    }

    public List getExtensionOIDs()
    {
        return OCSPUtils.getExtensionOIDs(extensions);
    }

    public Set getCriticalExtensionOIDs()
    {
        return OCSPUtils.getCriticalExtensionOIDs(extensions);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return OCSPUtils.getNonCriticalExtensionOIDs(extensions);
    }

    private SingleResponse resp;
    private Extensions extensions;
}
