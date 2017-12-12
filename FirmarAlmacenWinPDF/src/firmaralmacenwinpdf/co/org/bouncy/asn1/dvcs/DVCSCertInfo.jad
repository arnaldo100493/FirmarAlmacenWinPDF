// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSCertInfo.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.x509.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            DVCSRequestInformation, DVCSTime, TargetEtcChain

public class DVCSCertInfo extends ASN1Object
{

    public DVCSCertInfo(DVCSRequestInformation dvReqInfo, DigestInfo messageImprint, ASN1Integer serialNumber, DVCSTime responseTime)
    {
        version = 1;
        this.dvReqInfo = dvReqInfo;
        this.messageImprint = messageImprint;
        this.serialNumber = serialNumber;
        this.responseTime = responseTime;
    }

    private DVCSCertInfo(ASN1Sequence seq)
    {
        version = 1;
        int i = 0;
        ASN1Encodable x = seq.getObjectAt(i++);
        try
        {
            ASN1Integer encVersion = ASN1Integer.getInstance(x);
            version = encVersion.getValue().intValue();
            x = seq.getObjectAt(i++);
        }
        catch(IllegalArgumentException e) { }
        dvReqInfo = DVCSRequestInformation.getInstance(x);
        x = seq.getObjectAt(i++);
        messageImprint = DigestInfo.getInstance(x);
        x = seq.getObjectAt(i++);
        serialNumber = ASN1Integer.getInstance(x);
        x = seq.getObjectAt(i++);
        responseTime = DVCSTime.getInstance(x);
        do
        {
            if(i >= seq.size())
                break;
            x = seq.getObjectAt(i++);
            try
            {
                ASN1TaggedObject t = ASN1TaggedObject.getInstance(x);
                int tagNo = t.getTagNo();
                switch(tagNo)
                {
                default:
                    if(false)
                        ;
                    break;

                case 0: // '\0'
                    dvStatus = PKIStatusInfo.getInstance(t, false);
                    break;

                case 1: // '\001'
                    policy = PolicyInformation.getInstance(ASN1Sequence.getInstance(t, false));
                    break;

                case 2: // '\002'
                    reqSignature = ASN1Set.getInstance(t, false);
                    break;

                case 3: // '\003'
                    certs = ASN1Sequence.getInstance(t, false);
                    break;
                }
            }
            catch(IllegalArgumentException e)
            {
                try
                {
                    extensions = Extensions.getInstance(x);
                }
                // Misplaced declaration of an exception variable
                catch(IllegalArgumentException e) { }
            }
        } while(true);
    }

    public static DVCSCertInfo getInstance(Object obj)
    {
        if(obj instanceof DVCSCertInfo)
            return (DVCSCertInfo)obj;
        if(obj != null)
            return new DVCSCertInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static DVCSCertInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive()
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
        return new DERSequence(v);
    }

    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("DVCSCertInfo {\n");
        if(version != 1)
            s.append((new StringBuilder()).append("version: ").append(version).append("\n").toString());
        s.append((new StringBuilder()).append("dvReqInfo: ").append(dvReqInfo).append("\n").toString());
        s.append((new StringBuilder()).append("messageImprint: ").append(messageImprint).append("\n").toString());
        s.append((new StringBuilder()).append("serialNumber: ").append(serialNumber).append("\n").toString());
        s.append((new StringBuilder()).append("responseTime: ").append(responseTime).append("\n").toString());
        if(dvStatus != null)
            s.append((new StringBuilder()).append("dvStatus: ").append(dvStatus).append("\n").toString());
        if(policy != null)
            s.append((new StringBuilder()).append("policy: ").append(policy).append("\n").toString());
        if(reqSignature != null)
            s.append((new StringBuilder()).append("reqSignature: ").append(reqSignature).append("\n").toString());
        if(certs != null)
            s.append((new StringBuilder()).append("certs: ").append(certs).append("\n").toString());
        if(extensions != null)
            s.append((new StringBuilder()).append("extensions: ").append(extensions).append("\n").toString());
        s.append("}\n");
        return s.toString();
    }

    public int getVersion()
    {
        return version;
    }

    private void setVersion(int version)
    {
        this.version = version;
    }

    public DVCSRequestInformation getDvReqInfo()
    {
        return dvReqInfo;
    }

    private void setDvReqInfo(DVCSRequestInformation dvReqInfo)
    {
        this.dvReqInfo = dvReqInfo;
    }

    public DigestInfo getMessageImprint()
    {
        return messageImprint;
    }

    private void setMessageImprint(DigestInfo messageImprint)
    {
        this.messageImprint = messageImprint;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public DVCSTime getResponseTime()
    {
        return responseTime;
    }

    public PKIStatusInfo getDvStatus()
    {
        return dvStatus;
    }

    public PolicyInformation getPolicy()
    {
        return policy;
    }

    public ASN1Set getReqSignature()
    {
        return reqSignature;
    }

    public TargetEtcChain[] getCerts()
    {
        if(certs != null)
            return TargetEtcChain.arrayFromSequence(certs);
        else
            return null;
    }

    public Extensions getExtensions()
    {
        return extensions;
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
