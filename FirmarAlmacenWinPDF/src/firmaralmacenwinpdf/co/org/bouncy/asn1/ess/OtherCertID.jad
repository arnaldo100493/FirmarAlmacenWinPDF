// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherCertID.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;

public class OtherCertID extends ASN1Object
{

    public static OtherCertID getInstance(Object o)
    {
        if(o instanceof OtherCertID)
            return (OtherCertID)o;
        if(o != null)
            return new OtherCertID(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private OtherCertID(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        if(seq.getObjectAt(0).toASN1Primitive() instanceof ASN1OctetString)
            otherCertHash = ASN1OctetString.getInstance(seq.getObjectAt(0));
        else
            otherCertHash = DigestInfo.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            issuerSerial = IssuerSerial.getInstance(seq.getObjectAt(1));
    }

    public OtherCertID(AlgorithmIdentifier algId, byte digest[])
    {
        otherCertHash = new DigestInfo(algId, digest);
    }

    public OtherCertID(AlgorithmIdentifier algId, byte digest[], IssuerSerial issuerSerial)
    {
        otherCertHash = new DigestInfo(algId, digest);
        this.issuerSerial = issuerSerial;
    }

    public AlgorithmIdentifier getAlgorithmHash()
    {
        if(otherCertHash.toASN1Primitive() instanceof ASN1OctetString)
            return new AlgorithmIdentifier("1.3.14.3.2.26");
        else
            return DigestInfo.getInstance(otherCertHash).getAlgorithmId();
    }

    public byte[] getCertHash()
    {
        if(otherCertHash.toASN1Primitive() instanceof ASN1OctetString)
            return ((ASN1OctetString)otherCertHash.toASN1Primitive()).getOctets();
        else
            return DigestInfo.getInstance(otherCertHash).getDigest();
    }

    public IssuerSerial getIssuerSerial()
    {
        return issuerSerial;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(otherCertHash);
        if(issuerSerial != null)
            v.add(issuerSerial);
        return new DERSequence(v);
    }

    private ASN1Encodable otherCertHash;
    private IssuerSerial issuerSerial;
}
