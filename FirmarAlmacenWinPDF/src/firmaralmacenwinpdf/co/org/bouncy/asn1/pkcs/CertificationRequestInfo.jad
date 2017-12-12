// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificationRequestInfo.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x509.X509Name;

public class CertificationRequestInfo extends ASN1Object
{

    public static CertificationRequestInfo getInstance(Object obj)
    {
        if(obj instanceof CertificationRequestInfo)
            return (CertificationRequestInfo)obj;
        if(obj != null)
            return new CertificationRequestInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public CertificationRequestInfo(X500Name subject, SubjectPublicKeyInfo pkInfo, ASN1Set attributes)
    {
        version = new ASN1Integer(0L);
        this.attributes = null;
        this.subject = subject;
        subjectPKInfo = pkInfo;
        this.attributes = attributes;
        if(subject == null || version == null || subjectPKInfo == null)
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        else
            return;
    }

    /**
     * @deprecated Method CertificationRequestInfo is deprecated
     */

    public CertificationRequestInfo(X509Name subject, SubjectPublicKeyInfo pkInfo, ASN1Set attributes)
    {
        version = new ASN1Integer(0L);
        this.attributes = null;
        this.subject = X500Name.getInstance(subject.toASN1Primitive());
        subjectPKInfo = pkInfo;
        this.attributes = attributes;
        if(subject == null || version == null || subjectPKInfo == null)
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        else
            return;
    }

    /**
     * @deprecated Method CertificationRequestInfo is deprecated
     */

    public CertificationRequestInfo(ASN1Sequence seq)
    {
        version = new ASN1Integer(0L);
        attributes = null;
        version = (ASN1Integer)seq.getObjectAt(0);
        subject = X500Name.getInstance(seq.getObjectAt(1));
        subjectPKInfo = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(2));
        if(seq.size() > 3)
        {
            DERTaggedObject tagobj = (DERTaggedObject)seq.getObjectAt(3);
            attributes = ASN1Set.getInstance(tagobj, false);
        }
        if(subject == null || version == null || subjectPKInfo == null)
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        else
            return;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public X500Name getSubject()
    {
        return subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return subjectPKInfo;
    }

    public ASN1Set getAttributes()
    {
        return attributes;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(subject);
        v.add(subjectPKInfo);
        if(attributes != null)
            v.add(new DERTaggedObject(false, 0, attributes));
        return new DERSequence(v);
    }

    ASN1Integer version;
    X500Name subject;
    SubjectPublicKeyInfo subjectPKInfo;
    ASN1Set attributes;
}
