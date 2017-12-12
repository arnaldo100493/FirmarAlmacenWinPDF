// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertResponse.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIStatusInfo, CertifiedKeyPair

public class CertResponse extends ASN1Object
{

    private CertResponse(ASN1Sequence seq)
    {
        certReqId = ASN1Integer.getInstance(seq.getObjectAt(0));
        status = PKIStatusInfo.getInstance(seq.getObjectAt(1));
        if(seq.size() >= 3)
            if(seq.size() == 3)
            {
                ASN1Encodable o = seq.getObjectAt(2);
                if(o instanceof ASN1OctetString)
                    rspInfo = ASN1OctetString.getInstance(o);
                else
                    certifiedKeyPair = CertifiedKeyPair.getInstance(o);
            } else
            {
                certifiedKeyPair = CertifiedKeyPair.getInstance(seq.getObjectAt(2));
                rspInfo = ASN1OctetString.getInstance(seq.getObjectAt(3));
            }
    }

    public static CertResponse getInstance(Object o)
    {
        if(o instanceof CertResponse)
            return (CertResponse)o;
        if(o != null)
            return new CertResponse(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertResponse(ASN1Integer certReqId, PKIStatusInfo status)
    {
        this(certReqId, status, null, null);
    }

    public CertResponse(ASN1Integer certReqId, PKIStatusInfo status, CertifiedKeyPair certifiedKeyPair, ASN1OctetString rspInfo)
    {
        if(certReqId == null)
            throw new IllegalArgumentException("'certReqId' cannot be null");
        if(status == null)
        {
            throw new IllegalArgumentException("'status' cannot be null");
        } else
        {
            this.certReqId = certReqId;
            this.status = status;
            this.certifiedKeyPair = certifiedKeyPair;
            this.rspInfo = rspInfo;
            return;
        }
    }

    public ASN1Integer getCertReqId()
    {
        return certReqId;
    }

    public PKIStatusInfo getStatus()
    {
        return status;
    }

    public CertifiedKeyPair getCertifiedKeyPair()
    {
        return certifiedKeyPair;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certReqId);
        v.add(status);
        if(certifiedKeyPair != null)
            v.add(certifiedKeyPair);
        if(rspInfo != null)
            v.add(rspInfo);
        return new DERSequence(v);
    }

    private ASN1Integer certReqId;
    private PKIStatusInfo status;
    private CertifiedKeyPair certifiedKeyPair;
    private ASN1OctetString rspInfo;
}
