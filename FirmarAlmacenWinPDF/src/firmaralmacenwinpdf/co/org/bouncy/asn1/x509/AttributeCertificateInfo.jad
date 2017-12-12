// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttributeCertificateInfo.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extensions, Holder, AttCertIssuer, AlgorithmIdentifier, 
//            AttCertValidityPeriod

public class AttributeCertificateInfo extends ASN1Object
{

    public static AttributeCertificateInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AttributeCertificateInfo getInstance(Object obj)
    {
        if(obj instanceof AttributeCertificateInfo)
            return (AttributeCertificateInfo)obj;
        if(obj != null)
            return new AttributeCertificateInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private AttributeCertificateInfo(ASN1Sequence seq)
    {
        if(seq.size() < 7 || seq.size() > 9)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        version = ASN1Integer.getInstance(seq.getObjectAt(0));
        holder = Holder.getInstance(seq.getObjectAt(1));
        issuer = AttCertIssuer.getInstance(seq.getObjectAt(2));
        signature = AlgorithmIdentifier.getInstance(seq.getObjectAt(3));
        serialNumber = ASN1Integer.getInstance(seq.getObjectAt(4));
        attrCertValidityPeriod = AttCertValidityPeriod.getInstance(seq.getObjectAt(5));
        attributes = ASN1Sequence.getInstance(seq.getObjectAt(6));
        for(int i = 7; i < seq.size(); i++)
        {
            ASN1Encodable obj = seq.getObjectAt(i);
            if(obj instanceof DERBitString)
            {
                issuerUniqueID = DERBitString.getInstance(seq.getObjectAt(i));
                continue;
            }
            if((obj instanceof ASN1Sequence) || (obj instanceof Extensions))
                extensions = Extensions.getInstance(seq.getObjectAt(i));
        }

    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public Holder getHolder()
    {
        return holder;
    }

    public AttCertIssuer getIssuer()
    {
        return issuer;
    }

    public AlgorithmIdentifier getSignature()
    {
        return signature;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public AttCertValidityPeriod getAttrCertValidityPeriod()
    {
        return attrCertValidityPeriod;
    }

    public ASN1Sequence getAttributes()
    {
        return attributes;
    }

    public DERBitString getIssuerUniqueID()
    {
        return issuerUniqueID;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(holder);
        v.add(issuer);
        v.add(signature);
        v.add(serialNumber);
        v.add(attrCertValidityPeriod);
        v.add(attributes);
        if(issuerUniqueID != null)
            v.add(issuerUniqueID);
        if(extensions != null)
            v.add(extensions);
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private Holder holder;
    private AttCertIssuer issuer;
    private AlgorithmIdentifier signature;
    private ASN1Integer serialNumber;
    private AttCertValidityPeriod attrCertValidityPeriod;
    private ASN1Sequence attributes;
    private DERBitString issuerUniqueID;
    private Extensions extensions;
}
