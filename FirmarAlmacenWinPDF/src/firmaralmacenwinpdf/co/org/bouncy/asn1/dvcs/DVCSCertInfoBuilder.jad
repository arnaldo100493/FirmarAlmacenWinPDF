// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSCertInfoBuilder.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.x509.*;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            DVCSCertInfo, DVCSRequestInformation, DVCSTime, TargetEtcChain

public class DVCSCertInfoBuilder
{

    public DVCSCertInfoBuilder(DVCSRequestInformation dvReqInfo, DigestInfo messageImprint, ASN1Integer serialNumber, DVCSTime responseTime)
    {
        version = 1;
        this.dvReqInfo = dvReqInfo;
        this.messageImprint = messageImprint;
        this.serialNumber = serialNumber;
        this.responseTime = responseTime;
    }

    public DVCSCertInfo build()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(version != 1)
            v.add(new ASN1Integer(version));
        v.add(dvReqInfo);
        v.add(messageImprint);
        v.add(serialNumber);
        v.add(responseTime);
        if(dvStatus != null)
            v.add(new DERTaggedObject(false, 0, dvStatus));
        if(policy != null)
            v.add(new DERTaggedObject(false, 1, policy));
        if(reqSignature != null)
            v.add(new DERTaggedObject(false, 2, reqSignature));
        if(certs != null)
            v.add(new DERTaggedObject(false, 3, certs));
        if(extensions != null)
            v.add(extensions);
        return DVCSCertInfo.getInstance(new DERSequence(v));
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public void setDvReqInfo(DVCSRequestInformation dvReqInfo)
    {
        this.dvReqInfo = dvReqInfo;
    }

    public void setMessageImprint(DigestInfo messageImprint)
    {
        this.messageImprint = messageImprint;
    }

    public void setSerialNumber(ASN1Integer serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void setResponseTime(DVCSTime responseTime)
    {
        this.responseTime = responseTime;
    }

    public void setDvStatus(PKIStatusInfo dvStatus)
    {
        this.dvStatus = dvStatus;
    }

    public void setPolicy(PolicyInformation policy)
    {
        this.policy = policy;
    }

    public void setReqSignature(ASN1Set reqSignature)
    {
        this.reqSignature = reqSignature;
    }

    public void setCerts(TargetEtcChain certs[])
    {
        this.certs = new DERSequence(certs);
    }

    public void setExtensions(Extensions extensions)
    {
        this.extensions = extensions;
    }

    private int version;
    private DVCSRequestInformation dvReqInfo;
    private DigestInfo messageImprint;
    private ASN1Integer serialNumber;
    private DVCSTime responseTime;
    private PKIStatusInfo dvStatus;
    private PolicyInformation policy;
    private ASN1Set reqSignature;
    private ASN1Sequence certs;
    private Extensions extensions;
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_DV_STATUS = 0;
    private static final int TAG_POLICY = 1;
    private static final int TAG_REQ_SIGNATURE = 2;
    private static final int TAG_CERTS = 3;
}
