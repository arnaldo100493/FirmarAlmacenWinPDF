// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevokedStatus.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.ocsp.RevokedInfo;
import co.org.bouncy.asn1.x509.CRLReason;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            CertificateStatus, OCSPUtils

public class RevokedStatus
    implements CertificateStatus
{

    public RevokedStatus(RevokedInfo info)
    {
        this.info = info;
    }

    public RevokedStatus(Date revocationDate, int reason)
    {
        info = new RevokedInfo(new ASN1GeneralizedTime(revocationDate), CRLReason.lookup(reason));
    }

    public Date getRevocationTime()
    {
        return OCSPUtils.extractDate(info.getRevocationTime());
    }

    public boolean hasRevocationReason()
    {
        return info.getRevocationReason() != null;
    }

    public int getRevocationReason()
    {
        if(info.getRevocationReason() == null)
            throw new IllegalStateException("attempt to get a reason where none is available");
        else
            return info.getRevocationReason().getValue().intValue();
    }

    RevokedInfo info;
}
