// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertStatus.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIStatusInfo

public class CertStatus extends ASN1Object
{

    private CertStatus(ASN1Sequence seq)
    {
        certHash = ASN1OctetString.getInstance(seq.getObjectAt(0));
        certReqId = ASN1Integer.getInstance(seq.getObjectAt(1));
        if(seq.size() > 2)
            statusInfo = PKIStatusInfo.getInstance(seq.getObjectAt(2));
    }

    public CertStatus(byte certHash[], BigInteger certReqId)
    {
        this.certHash = new DEROctetString(certHash);
        this.certReqId = new ASN1Integer(certReqId);
    }

    public CertStatus(byte certHash[], BigInteger certReqId, PKIStatusInfo statusInfo)
    {
        this.certHash = new DEROctetString(certHash);
        this.certReqId = new ASN1Integer(certReqId);
        this.statusInfo = statusInfo;
    }

    public static CertStatus getInstance(Object o)
    {
        if(o instanceof CertStatus)
            return (CertStatus)o;
        if(o != null)
            return new CertStatus(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ASN1OctetString getCertHash()
    {
        return certHash;
    }

    public ASN1Integer getCertReqId()
    {
        return certReqId;
    }

    public PKIStatusInfo getStatusInfo()
    {
        return statusInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certHash);
        v.add(certReqId);
        if(statusInfo != null)
            v.add(statusInfo);
        return new DERSequence(v);
    }

    private ASN1OctetString certHash;
    private ASN1Integer certReqId;
    private PKIStatusInfo statusInfo;
}
