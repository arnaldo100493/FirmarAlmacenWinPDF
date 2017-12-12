// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OcspResponsesID.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OcspIdentifier, OtherHash

public class OcspResponsesID extends ASN1Object
{

    public static OcspResponsesID getInstance(Object obj)
    {
        if(obj instanceof OcspResponsesID)
            return (OcspResponsesID)obj;
        if(obj != null)
            return new OcspResponsesID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OcspResponsesID(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        ocspIdentifier = OcspIdentifier.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            ocspRepHash = OtherHash.getInstance(seq.getObjectAt(1));
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier)
    {
        this(ocspIdentifier, null);
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier, OtherHash ocspRepHash)
    {
        this.ocspIdentifier = ocspIdentifier;
        this.ocspRepHash = ocspRepHash;
    }

    public OcspIdentifier getOcspIdentifier()
    {
        return ocspIdentifier;
    }

    public OtherHash getOcspRepHash()
    {
        return ocspRepHash;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(ocspIdentifier);
        if(null != ocspRepHash)
            v.add(ocspRepHash);
        return new DERSequence(v);
    }

    private OcspIdentifier ocspIdentifier;
    private OtherHash ocspRepHash;
}
