// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSCertificate.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            AlgorithmIdentifier, Time, SubjectPublicKeyInfo, Extensions

public class TBSCertificate extends ASN1Object
{

    public static TBSCertificate getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSCertificate getInstance(Object obj)
    {
        if(obj instanceof TBSCertificate)
            return (TBSCertificate)obj;
        if(obj != null)
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private TBSCertificate(ASN1Sequence seq)
    {
        int seqStart = 0;
        this.seq = seq;
        if(seq.getObjectAt(0) instanceof DERTaggedObject)
        {
            version = ASN1Integer.getInstance((ASN1TaggedObject)seq.getObjectAt(0), true);
        } else
        {
            seqStart = -1;
            version = new ASN1Integer(0L);
        }
        serialNumber = ASN1Integer.getInstance(seq.getObjectAt(seqStart + 1));
        signature = AlgorithmIdentifier.getInstance(seq.getObjectAt(seqStart + 2));
        issuer = X500Name.getInstance(seq.getObjectAt(seqStart + 3));
        ASN1Sequence dates = (ASN1Sequence)seq.getObjectAt(seqStart + 4);
        startDate = Time.getInstance(dates.getObjectAt(0));
        endDate = Time.getInstance(dates.getObjectAt(1));
        subject = X500Name.getInstance(seq.getObjectAt(seqStart + 5));
        subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(seqStart + 6));
        for(int extras = seq.size() - (seqStart + 6) - 1; extras > 0; extras--)
        {
            DERTaggedObject extra = (DERTaggedObject)seq.getObjectAt(seqStart + 6 + extras);
            switch(extra.getTagNo())
            {
            case 1: // '\001'
                issuerUniqueId = DERBitString.getInstance(extra, false);
                break;

            case 2: // '\002'
                subjectUniqueId = DERBitString.getInstance(extra, false);
                break;

            case 3: // '\003'
                extensions = Extensions.getInstance(ASN1Sequence.getInstance(extra, true));
                break;
            }
        }

    }

    public int getVersionNumber()
    {
        return version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public AlgorithmIdentifier getSignature()
    {
        return signature;
    }

    public X500Name getIssuer()
    {
        return issuer;
    }

    public Time getStartDate()
    {
        return startDate;
    }

    public Time getEndDate()
    {
        return endDate;
    }

    public X500Name getSubject()
    {
        return subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return subjectPublicKeyInfo;
    }

    public DERBitString getIssuerUniqueId()
    {
        return issuerUniqueId;
    }

    public DERBitString getSubjectUniqueId()
    {
        return subjectUniqueId;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    ASN1Sequence seq;
    ASN1Integer version;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time startDate;
    Time endDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    DERBitString issuerUniqueId;
    DERBitString subjectUniqueId;
    Extensions extensions;
}
