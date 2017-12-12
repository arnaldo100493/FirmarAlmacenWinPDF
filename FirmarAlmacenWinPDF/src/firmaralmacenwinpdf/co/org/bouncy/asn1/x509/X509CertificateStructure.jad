// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertificateStructure.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x500.X500Name;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            X509ObjectIdentifiers, TBSCertificateStructure, AlgorithmIdentifier, Time, 
//            SubjectPublicKeyInfo

/**
 * @deprecated Class X509CertificateStructure is deprecated
 */

public class X509CertificateStructure extends ASN1Object
    implements X509ObjectIdentifiers, PKCSObjectIdentifiers
{

    public static X509CertificateStructure getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static X509CertificateStructure getInstance(Object obj)
    {
        if(obj instanceof X509CertificateStructure)
            return (X509CertificateStructure)obj;
        if(obj != null)
            return new X509CertificateStructure(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public X509CertificateStructure(ASN1Sequence seq)
    {
        this.seq = seq;
        if(seq.size() == 3)
        {
            tbsCert = TBSCertificateStructure.getInstance(seq.getObjectAt(0));
            sigAlgId = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            sig = DERBitString.getInstance(seq.getObjectAt(2));
        } else
        {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
    }

    public TBSCertificateStructure getTBSCertificate()
    {
        return tbsCert;
    }

    public int getVersion()
    {
        return tbsCert.getVersion();
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
    TBSCertificateStructure tbsCert;
    AlgorithmIdentifier sigAlgId;
    DERBitString sig;
}
