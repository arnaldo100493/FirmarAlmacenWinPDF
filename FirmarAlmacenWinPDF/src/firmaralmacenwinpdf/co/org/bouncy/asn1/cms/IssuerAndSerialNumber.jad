// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IssuerAndSerialNumber.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import java.math.BigInteger;

public class IssuerAndSerialNumber extends ASN1Object
{

    public static IssuerAndSerialNumber getInstance(Object obj)
    {
        if(obj instanceof IssuerAndSerialNumber)
            return (IssuerAndSerialNumber)obj;
        if(obj != null)
            return new IssuerAndSerialNumber(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    /**
     * @deprecated Method IssuerAndSerialNumber is deprecated
     */

    public IssuerAndSerialNumber(ASN1Sequence seq)
    {
        name = X500Name.getInstance(seq.getObjectAt(0));
        serialNumber = (ASN1Integer)seq.getObjectAt(1);
    }

    public IssuerAndSerialNumber(Certificate certificate)
    {
        name = certificate.getIssuer();
        serialNumber = certificate.getSerialNumber();
    }

    public IssuerAndSerialNumber(X509CertificateStructure certificate)
    {
        name = certificate.getIssuer();
        serialNumber = certificate.getSerialNumber();
    }

    public IssuerAndSerialNumber(X500Name name, BigInteger serialNumber)
    {
        this.name = name;
        this.serialNumber = new ASN1Integer(serialNumber);
    }

    /**
     * @deprecated Method IssuerAndSerialNumber is deprecated
     */

    public IssuerAndSerialNumber(X509Name name, BigInteger serialNumber)
    {
        this.name = X500Name.getInstance(name);
        this.serialNumber = new ASN1Integer(serialNumber);
    }

    /**
     * @deprecated Method IssuerAndSerialNumber is deprecated
     */

    public IssuerAndSerialNumber(X509Name name, ASN1Integer serialNumber)
    {
        this.name = X500Name.getInstance(name);
        this.serialNumber = serialNumber;
    }

    public X500Name getName()
    {
        return name;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(name);
        v.add(serialNumber);
        return new DERSequence(v);
    }

    private X500Name name;
    private ASN1Integer serialNumber;
}
