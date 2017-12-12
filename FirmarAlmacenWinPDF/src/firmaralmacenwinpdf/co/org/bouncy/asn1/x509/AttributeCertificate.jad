// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttributeCertificate.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            AttributeCertificateInfo, AlgorithmIdentifier

public class AttributeCertificate extends ASN1Object
{

    public static AttributeCertificate getInstance(Object obj)
    {
        if(obj instanceof AttributeCertificate)
            return (AttributeCertificate)obj;
        if(obj != null)
            return new AttributeCertificate(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public AttributeCertificate(AttributeCertificateInfo acinfo, AlgorithmIdentifier signatureAlgorithm, DERBitString signatureValue)
    {
        this.acinfo = acinfo;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signatureValue = signatureValue;
    }

    public AttributeCertificate(ASN1Sequence seq)
    {
        if(seq.size() != 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            acinfo = AttributeCertificateInfo.getInstance(seq.getObjectAt(0));
            signatureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            signatureValue = DERBitString.getInstance(seq.getObjectAt(2));
            return;
        }
    }

    public AttributeCertificateInfo getAcinfo()
    {
        return acinfo;
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return signatureAlgorithm;
    }

    public DERBitString getSignatureValue()
    {
        return signatureValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(acinfo);
        v.add(signatureAlgorithm);
        v.add(signatureValue);
        return new DERSequence(v);
    }

    AttributeCertificateInfo acinfo;
    AlgorithmIdentifier signatureAlgorithm;
    DERBitString signatureValue;
}
