// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIStatusInfo.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIFreeText, PKIStatus, PKIFailureInfo

public class PKIStatusInfo extends ASN1Object
{

    public static PKIStatusInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PKIStatusInfo getInstance(Object obj)
    {
        if(obj instanceof PKIStatusInfo)
            return (PKIStatusInfo)obj;
        if(obj != null)
            return new PKIStatusInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private PKIStatusInfo(ASN1Sequence seq)
    {
        status = ASN1Integer.getInstance(seq.getObjectAt(0));
        statusString = null;
        failInfo = null;
        if(seq.size() > 2)
        {
            statusString = PKIFreeText.getInstance(seq.getObjectAt(1));
            failInfo = DERBitString.getInstance(seq.getObjectAt(2));
        } else
        if(seq.size() > 1)
        {
            Object obj = seq.getObjectAt(1);
            if(obj instanceof DERBitString)
                failInfo = DERBitString.getInstance(obj);
            else
                statusString = PKIFreeText.getInstance(obj);
        }
    }

    public PKIStatusInfo(PKIStatus status)
    {
        this.status = ASN1Integer.getInstance(status.toASN1Primitive());
    }

    public PKIStatusInfo(PKIStatus status, PKIFreeText statusString)
    {
        this.status = ASN1Integer.getInstance(status.toASN1Primitive());
        this.statusString = statusString;
    }

    public PKIStatusInfo(PKIStatus status, PKIFreeText statusString, PKIFailureInfo failInfo)
    {
        this.status = ASN1Integer.getInstance(status.toASN1Primitive());
        this.statusString = statusString;
        this.failInfo = failInfo;
    }

    public BigInteger getStatus()
    {
        return status.getValue();
    }

    public PKIFreeText getStatusString()
    {
        return statusString;
    }

    public DERBitString getFailInfo()
    {
        return failInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(status);
        if(statusString != null)
            v.add(statusString);
        if(failInfo != null)
            v.add(failInfo);
        return new DERSequence(v);
    }

    ASN1Integer status;
    PKIFreeText statusString;
    DERBitString failInfo;
}
