// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   V2AttributeCertificateInfoGenerator.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Attribute, AttCertValidityPeriod, X509Extensions, Extensions, 
//            AttributeCertificateInfo, Holder, AttCertIssuer, AlgorithmIdentifier

public class V2AttributeCertificateInfoGenerator
{

    public V2AttributeCertificateInfoGenerator()
    {
        version = new ASN1Integer(1L);
        attributes = new ASN1EncodableVector();
    }

    public void setHolder(Holder holder)
    {
        this.holder = holder;
    }

    public void addAttribute(String oid, ASN1Encodable value)
    {
        attributes.add(new Attribute(new ASN1ObjectIdentifier(oid), new DERSet(value)));
    }

    public void addAttribute(Attribute attribute)
    {
        attributes.add(attribute);
    }

    public void setSerialNumber(ASN1Integer serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void setSignature(AlgorithmIdentifier signature)
    {
        this.signature = signature;
    }

    public void setIssuer(AttCertIssuer issuer)
    {
        this.issuer = issuer;
    }

    public void setStartDate(ASN1GeneralizedTime startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(ASN1GeneralizedTime endDate)
    {
        this.endDate = endDate;
    }

    public void setIssuerUniqueID(DERBitString issuerUniqueID)
    {
        this.issuerUniqueID = issuerUniqueID;
    }

    /**
     * @deprecated Method setExtensions is deprecated
     */

    public void setExtensions(X509Extensions extensions)
    {
        this.extensions = Extensions.getInstance(extensions.toASN1Primitive());
    }

    public void setExtensions(Extensions extensions)
    {
        this.extensions = extensions;
    }

    public AttributeCertificateInfo generateAttributeCertificateInfo()
    {
        if(serialNumber == null || signature == null || issuer == null || startDate == null || endDate == null || holder == null || attributes == null)
            throw new IllegalStateException("not all mandatory fields set in V2 AttributeCertificateInfo generator");
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(holder);
        v.add(issuer);
        v.add(signature);
        v.add(serialNumber);
        AttCertValidityPeriod validity = new AttCertValidityPeriod(startDate, endDate);
        v.add(validity);
        v.add(new DERSequence(attributes));
        if(issuerUniqueID != null)
            v.add(issuerUniqueID);
        if(extensions != null)
            v.add(extensions);
        return AttributeCertificateInfo.getInstance(new DERSequence(v));
    }

    private ASN1Integer version;
    private Holder holder;
    private AttCertIssuer issuer;
    private AlgorithmIdentifier signature;
    private ASN1Integer serialNumber;
    private ASN1EncodableVector attributes;
    private DERBitString issuerUniqueID;
    private Extensions extensions;
    private ASN1GeneralizedTime startDate;
    private ASN1GeneralizedTime endDate;
}
