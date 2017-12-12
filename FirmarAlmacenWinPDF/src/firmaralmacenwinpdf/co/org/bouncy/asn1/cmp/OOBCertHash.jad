// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OOBCertHash.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.CertId;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class OOBCertHash extends ASN1Object
{

    private OOBCertHash(ASN1Sequence seq)
    {
        int index = seq.size() - 1;
        hashVal = DERBitString.getInstance(seq.getObjectAt(index--));
        for(int i = index; i >= 0; i--)
        {
            ASN1TaggedObject tObj = (ASN1TaggedObject)seq.getObjectAt(i);
            if(tObj.getTagNo() == 0)
                hashAlg = AlgorithmIdentifier.getInstance(tObj, true);
            else
                certId = CertId.getInstance(tObj, true);
        }

    }

    public static OOBCertHash getInstance(Object o)
    {
        if(o instanceof OOBCertHash)
            return (OOBCertHash)o;
        if(o != null)
            return new OOBCertHash(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public OOBCertHash(AlgorithmIdentifier hashAlg, CertId certId, byte hashVal[])
    {
        this(hashAlg, certId, new DERBitString(hashVal));
    }

    public OOBCertHash(AlgorithmIdentifier hashAlg, CertId certId, DERBitString hashVal)
    {
        this.hashAlg = hashAlg;
        this.certId = certId;
        this.hashVal = hashVal;
    }

    public AlgorithmIdentifier getHashAlg()
    {
        return hashAlg;
    }

    public CertId getCertId()
    {
        return certId;
    }

    public DERBitString getHashVal()
    {
        return hashVal;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, 0, hashAlg);
        addOptional(v, 1, certId);
        v.add(hashVal);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(new DERTaggedObject(true, tagNo, obj));
    }

    private AlgorithmIdentifier hashAlg;
    private CertId certId;
    private DERBitString hashVal;
}
