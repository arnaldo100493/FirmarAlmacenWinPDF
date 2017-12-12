// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ESSCertIDv2.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.IssuerSerial;

public class ESSCertIDv2 extends ASN1Object
{

    public static ESSCertIDv2 getInstance(Object o)
    {
        if(o instanceof ESSCertIDv2)
            return (ESSCertIDv2)o;
        if(o != null)
            return new ESSCertIDv2(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private ESSCertIDv2(ASN1Sequence seq)
    {
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        int count = 0;
        if(seq.getObjectAt(0) instanceof ASN1OctetString)
            hashAlgorithm = DEFAULT_ALG_ID;
        else
            hashAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(count++).toASN1Primitive());
        certHash = ASN1OctetString.getInstance(seq.getObjectAt(count++).toASN1Primitive()).getOctets();
        if(seq.size() > count)
            issuerSerial = IssuerSerial.getInstance(seq.getObjectAt(count));
    }

    public ESSCertIDv2(byte certHash[])
    {
        this(null, certHash, null);
    }

    public ESSCertIDv2(AlgorithmIdentifier algId, byte certHash[])
    {
        this(algId, certHash, null);
    }

    public ESSCertIDv2(byte certHash[], IssuerSerial issuerSerial)
    {
        this(null, certHash, issuerSerial);
    }

    public ESSCertIDv2(AlgorithmIdentifier algId, byte certHash[], IssuerSerial issuerSerial)
    {
        if(algId == null)
            hashAlgorithm = DEFAULT_ALG_ID;
        else
            hashAlgorithm = algId;
        this.certHash = certHash;
        this.issuerSerial = issuerSerial;
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public byte[] getCertHash()
    {
        return certHash;
    }

    public IssuerSerial getIssuerSerial()
    {
        return issuerSerial;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(!hashAlgorithm.equals(DEFAULT_ALG_ID))
            v.add(hashAlgorithm);
        v.add((new DEROctetString(certHash)).toASN1Primitive());
        if(issuerSerial != null)
            v.add(issuerSerial);
        return new DERSequence(v);
    }

    private AlgorithmIdentifier hashAlgorithm;
    private byte certHash[];
    private IssuerSerial issuerSerial;
    private static final AlgorithmIdentifier DEFAULT_ALG_ID;

    static 
    {
        DEFAULT_ALG_ID = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    }
}
