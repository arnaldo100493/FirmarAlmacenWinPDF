// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Req.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.security.cert.X509Extension;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package co.org.bouncy.ocsp:
//            CertificateID

public class Req
    implements X509Extension
{

    public Req(Request req)
    {
        this.req = req;
    }

    public CertificateID getCertID()
    {
        return new CertificateID(req.getReqCert());
    }

    public X509Extensions getSingleRequestExtensions()
    {
        return X509Extensions.getInstance(req.getSingleRequestExtensions());
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Set set = new HashSet();
        X509Extensions extensions = getSingleRequestExtensions();
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
        X509Extensions exts = getSingleRequestExtensions();
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

    private Request req;
}
