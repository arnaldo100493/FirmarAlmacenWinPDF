// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   V1TBSCertificateGenerator.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Time, X509Name, TBSCertificate, AlgorithmIdentifier, 
//            SubjectPublicKeyInfo

public class V1TBSCertificateGenerator
{

    public V1TBSCertificateGenerator()
    {
        version = new DERTaggedObject(true, 0, new ASN1Integer(0L));
    }

    public void setSerialNumber(ASN1Integer serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void setSignature(AlgorithmIdentifier signature)
    {
        this.signature = signature;
    }

    /**
     * @deprecated Method setIssuer is deprecated
     */

    public void setIssuer(X509Name issuer)
    {
        this.issuer = X500Name.getInstance(issuer.toASN1Primitive());
    }

    public void setIssuer(X500Name issuer)
    {
        this.issuer = issuer;
    }

    public void setStartDate(Time startDate)
    {
        this.startDate = startDate;
    }

    public void setStartDate(ASN1UTCTime startDate)
    {
        this.startDate = new Time(startDate);
    }

    public void setEndDate(Time endDate)
    {
        this.endDate = endDate;
    }

    public void setEndDate(ASN1UTCTime endDate)
    {
        this.endDate = new Time(endDate);
    }

    /**
     * @deprecated Method setSubject is deprecated
     */

    public void setSubject(X509Name subject)
    {
        this.subject = X500Name.getInstance(subject.toASN1Primitive());
    }

    public void setSubject(X500Name subject)
    {
        this.subject = subject;
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo pubKeyInfo)
    {
        subjectPublicKeyInfo = pubKeyInfo;
    }

    public TBSCertificate generateTBSCertificate()
    {
        if(serialNumber == null || signature == null || issuer == null || startDate == null || endDate == null || subject == null || subjectPublicKeyInfo == null)
        {
            throw new IllegalStateException("not all mandatory fields set in V1 TBScertificate generator");
        } else
        {
            ASN1EncodableVector seq = new ASN1EncodableVector();
            seq.add(serialNumber);
            seq.add(signature);
            seq.add(issuer);
            ASN1EncodableVector validity = new ASN1EncodableVector();
            validity.add(startDate);
            validity.add(endDate);
            seq.add(new DERSequence(validity));
            seq.add(subject);
            seq.add(subjectPublicKeyInfo);
            return TBSCertificate.getInstance(new DERSequence(seq));
        }
    }

    DERTaggedObject version;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time startDate;
    Time endDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
}
