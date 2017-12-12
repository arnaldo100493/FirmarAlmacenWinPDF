// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ESSCertID.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.IssuerSerial;

public class ESSCertID extends ASN1Object
{

    public static ESSCertID getInstance(Object o)
    {
        if(o instanceof ESSCertID)
            return (ESSCertID)o;
        if(o != null)
            return new ESSCertID(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private ESSCertID(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        certHash = ASN1OctetString.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            issuerSerial = IssuerSerial.getInstance(seq.getObjectAt(1));
    }

    public ESSCertID(byte hash[])
    {
        certHash = new DEROctetString(hash);
    }

    public ESSCertID(byte hash[], IssuerSerial issuerSerial)
    {
        certHash = new DEROctetString(hash);
        this.issuerSerial = issuerSerial;
    }

    public byte[] getCertHash()
    {
        return certHash.getOctets();
    }

    public IssuerSerial getIssuerSerial()
    {
        return issuerSerial;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certHash);
        if(issuerSerial != null)
            v.add(issuerSerial);
        return new DERSequence(v);
    }

    private ASN1OctetString certHash;
    private IssuerSerial issuerSerial;
}
