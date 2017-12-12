// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPRespBuilder.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.CRLReason;
import co.org.bouncy.asn1.x509.Extensions;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            UnknownStatus, RevokedStatus, BasicOCSPRespBuilder, CertificateID, 
//            CertificateStatus

private class BasicOCSPRespBuilder$ResponseObject
{

    public SingleResponse toResponse()
        throws Exception
    {
        return new SingleResponse(certId.toASN1Object(), certStatus, thisUpdate, nextUpdate, extensions);
    }

    CertificateID certId;
    CertStatus certStatus;
    DERGeneralizedTime thisUpdate;
    DERGeneralizedTime nextUpdate;
    Extensions extensions;
    final BasicOCSPRespBuilder this$0;

    public BasicOCSPRespBuilder$ResponseObject(CertificateID certId, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, Extensions extensions)
    {
        this$0 = BasicOCSPRespBuilder.this;
        super();
        this.certId = certId;
        if(certStatus == null)
            this.certStatus = new CertStatus();
        else
        if(certStatus instanceof UnknownStatus)
        {
            this.certStatus = new CertStatus(2, DERNull.INSTANCE);
        } else
        {
            RevokedStatus rs = (RevokedStatus)certStatus;
            if(rs.hasRevocationReason())
                this.certStatus = new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(rs.getRevocationTime()), CRLReason.lookup(rs.getRevocationReason())));
            else
                this.certStatus = new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(rs.getRevocationTime()), null));
        }
        this.thisUpdate = new DERGeneralizedTime(thisUpdate);
        if(nextUpdate != null)
            this.nextUpdate = new DERGeneralizedTime(nextUpdate);
        else
            this.nextUpdate = null;
        this.extensions = extensions;
    }
}
