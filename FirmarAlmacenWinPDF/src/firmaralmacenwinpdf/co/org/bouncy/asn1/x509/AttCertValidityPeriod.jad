// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttCertValidityPeriod.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

public class AttCertValidityPeriod extends ASN1Object
{

    public static AttCertValidityPeriod getInstance(Object obj)
    {
        if(obj instanceof AttCertValidityPeriod)
            return (AttCertValidityPeriod)obj;
        if(obj != null)
            return new AttCertValidityPeriod(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private AttCertValidityPeriod(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            notBeforeTime = ASN1GeneralizedTime.getInstance(seq.getObjectAt(0));
            notAfterTime = ASN1GeneralizedTime.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public AttCertValidityPeriod(ASN1GeneralizedTime notBeforeTime, ASN1GeneralizedTime notAfterTime)
    {
        this.notBeforeTime = notBeforeTime;
        this.notAfterTime = notAfterTime;
    }

    public ASN1GeneralizedTime getNotBeforeTime()
    {
        return notBeforeTime;
    }

    public ASN1GeneralizedTime getNotAfterTime()
    {
        return notAfterTime;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(notBeforeTime);
        v.add(notAfterTime);
        return new DERSequence(v);
    }

    ASN1GeneralizedTime notBeforeTime;
    ASN1GeneralizedTime notAfterTime;
}
