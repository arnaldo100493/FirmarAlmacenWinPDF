// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Certificate.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            TBSCertificate, AlgorithmIdentifier, Time, SubjectPublicKeyInfo

public class Certificate extends ASN1Object
{

    public static Certificate getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static Certificate getInstance(Object obj)
    {
        if(obj instanceof Certificate)
            return (Certificate)obj;
        if(obj != null)
            return new Certificate(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private Certificate(ASN1Sequence seq)
    {
        this.seq = seq;
        if(seq.size() == 3)
        {
            tbsCert = TBSCertificate.getInstance(seq.getObjectAt(0));
            sigAlgId = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            sig = DERBitString.getInstance(seq.getObjectAt(2));
        } else
        {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
    }

    public TBSCertificate getTBSCertificate()
    {
        return tbsCert;
    }

    public ASN1Integer getVersion()
    {
        return tbsCert.getVersion();
    }

    public int getVersionNumber()
    {
        return tbsCert.getVersionNumber();
    }

    public ASN1Integer getSerialNumber()
    {
        return tbsCert.getSerialNumber();
    }

    public X500Name getIssuer()
    {
        return tbsCert.getIssuer();
    }

    public Time getStartDate()
    {
        return tbsCert.getStartDate();
    }

    public Time getEndDate()
    {
        return tbsCert.getEndDate();
    }

    public X500Name getSubject()
    {
        return tbsCert.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return tbsCert.getSubjectPublicKeyInfo();
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return sigAlgId;
    }

    public DERBitString getSignature()
    {
        return sig;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    ASN1Sequence seq;
    TBSCertificate tbsCert;
    AlgorithmIdentifier sigAlgId;
    DERBitString sig;
}
