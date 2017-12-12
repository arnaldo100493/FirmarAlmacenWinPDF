// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CRLEntryHolder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.*;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert:
//            CertUtils

public class X509CRLEntryHolder
{

    X509CRLEntryHolder(co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry, boolean isIndirect, GeneralNames previousCA)
    {
        this.entry = entry;
        ca = previousCA;
        if(isIndirect && entry.hasExtensions())
        {
            Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
            if(currentCaName != null)
                ca = GeneralNames.getInstance(currentCaName.getParsedValue());
        }
    }

    public BigInteger getSerialNumber()
    {
        return entry.getUserCertificate().getValue();
    }

    public Date getRevocationDate()
    {
        return entry.getRevocationDate().getDate();
    }

    public boolean hasExtensions()
    {
        return entry.hasExtensions();
    }

    public GeneralNames getCertificateIssuer()
    {
        return ca;
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        Extensions extensions = entry.getExtensions();
        if(extensions != null)
            return extensions.getExtension(oid);
        else
            return null;
    }

    public Extensions getExtensions()
    {
        return entry.getExtensions();
    }

    public List getExtensionOIDs()
    {
        return CertUtils.getExtensionOIDs(entry.getExtensions());
    }

    public Set getCriticalExtensionOIDs()
    {
        return CertUtils.getCriticalExtensionOIDs(entry.getExtensions());
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return CertUtils.getNonCriticalExtensionOIDs(entry.getExtensions());
    }

    private co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry;
    private GeneralNames ca;
}
