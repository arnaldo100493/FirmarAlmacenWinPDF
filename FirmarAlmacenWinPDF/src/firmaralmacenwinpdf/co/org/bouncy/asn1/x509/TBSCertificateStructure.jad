// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSCertificateStructure.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x500.X500Name;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            X509ObjectIdentifiers, AlgorithmIdentifier, Time, SubjectPublicKeyInfo, 
//            X509Extensions

public class TBSCertificateStructure extends ASN1Object
    implements X509ObjectIdentifiers, PKCSObjectIdentifiers
{

    public static TBSCertificateStructure getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSCertificateStructure getInstance(Object obj)
    {
        if(obj instanceof TBSCertificateStructure)
            return (TBSCertificateStructure)obj;
        if(obj != null)
            return new TBSCertificateStructure(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public TBSCertificateStructure(ASN1Sequence seq)
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
                extensions = X509Extensions.getInstance(extra);
                break;
            }
        }

    }

    public int getVersion()
    {
        return version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersionNumber()
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

    public X509Extensions getExtensions()
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
    X509Extensions extensions;
}
