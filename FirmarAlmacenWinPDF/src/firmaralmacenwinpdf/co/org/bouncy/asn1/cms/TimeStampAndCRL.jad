// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampAndCRL.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.CertificateList;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfo

public class TimeStampAndCRL extends ASN1Object
{

    public TimeStampAndCRL(ContentInfo timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    private TimeStampAndCRL(ASN1Sequence seq)
    {
        timeStamp = ContentInfo.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
            crl = CertificateList.getInstance(seq.getObjectAt(1));
    }

    public static TimeStampAndCRL getInstance(Object obj)
    {
        if(obj instanceof TimeStampAndCRL)
            return (TimeStampAndCRL)obj;
        if(obj != null)
            return new TimeStampAndCRL(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ContentInfo getTimeStampToken()
    {
        return timeStamp;
    }

    /**
     * @deprecated Method getCertificateList is deprecated
     */

    public CertificateList getCertificateList()
    {
        return crl;
    }

    public CertificateList getCRL()
    {
        return crl;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(timeStamp);
        if(crl != null)
            v.add(crl);
        return new DERSequence(v);
    }

    private ContentInfo timeStamp;
    private CertificateList crl;
}
