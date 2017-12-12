// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OriginatorInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.cms.OriginatorInfo;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.util.Store;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.cms:
//            OriginatorInformation, CMSException, CMSUtils

public class OriginatorInfoGenerator
{

    public OriginatorInfoGenerator(X509CertificateHolder origCert)
    {
        origCerts = new ArrayList(1);
        origCRLs = null;
        origCerts.add(origCert.toASN1Structure());
    }

    public OriginatorInfoGenerator(Store origCerts)
        throws CMSException
    {
        this(origCerts, null);
    }

    public OriginatorInfoGenerator(Store origCerts, Store origCRLs)
        throws CMSException
    {
        this.origCerts = CMSUtils.getCertificatesFromStore(origCerts);
        if(origCRLs != null)
            this.origCRLs = CMSUtils.getCRLsFromStore(origCRLs);
        else
            this.origCRLs = null;
    }

    public OriginatorInformation generate()
    {
        if(origCRLs != null)
            return new OriginatorInformation(new OriginatorInfo(CMSUtils.createDerSetFromList(origCerts), CMSUtils.createDerSetFromList(origCRLs)));
        else
            return new OriginatorInformation(new OriginatorInfo(CMSUtils.createDerSetFromList(origCerts), null));
    }

    private final List origCerts;
    private final List origCRLs;
}
