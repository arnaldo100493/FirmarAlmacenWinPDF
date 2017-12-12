// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPRespGenerator.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.CRLReason;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.util.Date;

// Referenced classes of package co.org.bouncy.ocsp:
//            UnknownStatus, RevokedStatus, BasicOCSPRespGenerator, CertificateID, 
//            CertificateStatus

private class BasicOCSPRespGenerator$ResponseObject
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
    X509Extensions extensions;
    final BasicOCSPRespGenerator this$0;

    public BasicOCSPRespGenerator$ResponseObject(CertificateID certId, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, X509Extensions extensions)
    {
        this$0 = BasicOCSPRespGenerator.this;
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
