// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertHash.java

package co.org.bouncy.asn1.isismtt.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class CertHash extends ASN1Object
{

    public static CertHash getInstance(Object obj)
    {
        if(obj == null || (obj instanceof CertHash))
            return (CertHash)obj;
        if(obj instanceof ASN1Sequence)
            return new CertHash((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private CertHash(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            hashAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
            certificateHash = DEROctetString.getInstance(seq.getObjectAt(1)).getOctets();
            return;
        }
    }

    public CertHash(AlgorithmIdentifier hashAlgorithm, byte certificateHash[])
    {
        this.hashAlgorithm = hashAlgorithm;
        this.certificateHash = new byte[certificateHash.length];
        System.arraycopy(certificateHash, 0, this.certificateHash, 0, certificateHash.length);
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public byte[] getCertificateHash()
    {
        return certificateHash;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        vec.add(hashAlgorithm);
        vec.add(new DEROctetString(certificateHash));
        return new DERSequence(vec);
    }

    private AlgorithmIdentifier hashAlgorithm;
    private byte certificateHash[];
}
