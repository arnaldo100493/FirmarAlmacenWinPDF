// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPUtils.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.cert.X509CertificateHolder;
import java.util.*;

class OCSPUtils
{

    OCSPUtils()
    {
    }

    static Date extractDate(ASN1GeneralizedTime time)
    {
        try
        {
            return time.getDate();
        }
        catch(Exception e)
        {
            throw new IllegalStateException((new StringBuilder()).append("exception processing GeneralizedTime: ").append(e.getMessage()).toString());
        }
    }

    static Set getCriticalExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    static Set getNonCriticalExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }

    static List getExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_LIST;
        else
            return Collections.unmodifiableList(Arrays.asList(extensions.getExtensionOIDs()));
    }

    static final X509CertificateHolder EMPTY_CERTS[] = new X509CertificateHolder[0];
    static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    static List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());

}
