// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInfo.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            IssuerAndSerialNumber

public class SignerInfo extends ASN1Object
{

    public static SignerInfo getInstance(Object o)
    {
        if(o instanceof SignerInfo)
            return (SignerInfo)o;
        if(o instanceof ASN1Sequence)
            return new SignerInfo((ASN1Sequence)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(o.getClass().getName()).toString());
    }

    public SignerInfo(ASN1Integer version, IssuerAndSerialNumber issuerAndSerialNumber, AlgorithmIdentifier digAlgorithm, ASN1Set authenticatedAttributes, AlgorithmIdentifier digEncryptionAlgorithm, ASN1OctetString encryptedDigest, ASN1Set unauthenticatedAttributes)
    {
        this.version = version;
        this.issuerAndSerialNumber = issuerAndSerialNumber;
        this.digAlgorithm = digAlgorithm;
        this.authenticatedAttributes = authenticatedAttributes;
        this.digEncryptionAlgorithm = digEncryptionAlgorithm;
        this.encryptedDigest = encryptedDigest;
        this.unauthenticatedAttributes = unauthenticatedAttributes;
    }

    public SignerInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        version = (ASN1Integer)e.nextElement();
        issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(e.nextElement());
        digAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        Object obj = e.nextElement();
        if(obj instanceof ASN1TaggedObject)
        {
            authenticatedAttributes = ASN1Set.getInstance((ASN1TaggedObject)obj, false);
            digEncryptionAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        } else
        {
            authenticatedAttributes = null;
            digEncryptionAlgorithm = AlgorithmIdentifier.getInstance(obj);
        }
        encryptedDigest = DEROctetString.getInstance(e.nextElement());
        if(e.hasMoreElements())
            unauthenticatedAttributes = ASN1Set.getInstance((ASN1TaggedObject)e.nextElement(), false);
        else
            unauthenticatedAttributes = null;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber()
    {
        return issuerAndSerialNumber;
    }

    public ASN1Set getAuthenticatedAttributes()
    {
        return authenticatedAttributes;
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        return digAlgorithm;
    }

    public ASN1OctetString getEncryptedDigest()
    {
        return encryptedDigest;
    }

    public AlgorithmIdentifier getDigestEncryptionAlgorithm()
    {
        return digEncryptionAlgorithm;
    }

    public ASN1Set getUnauthenticatedAttributes()
    {
        return unauthenticatedAttributes;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(issuerAndSerialNumber);
        v.add(digAlgorithm);
        if(authenticatedAttributes != null)
            v.add(new DERTaggedObject(false, 0, authenticatedAttributes));
        v.add(digEncryptionAlgorithm);
        v.add(encryptedDigest);
        if(unauthenticatedAttributes != null)
            v.add(new DERTaggedObject(false, 1, unauthenticatedAttributes));
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private IssuerAndSerialNumber issuerAndSerialNumber;
    private AlgorithmIdentifier digAlgorithm;
    private ASN1Set authenticatedAttributes;
    private AlgorithmIdentifier digEncryptionAlgorithm;
    private ASN1OctetString encryptedDigest;
    private ASN1Set unauthenticatedAttributes;
}
