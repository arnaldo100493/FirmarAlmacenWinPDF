// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectDigestInfo.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            AlgorithmIdentifier

public class ObjectDigestInfo extends ASN1Object
{

    public static ObjectDigestInfo getInstance(Object obj)
    {
        if(obj instanceof ObjectDigestInfo)
            return (ObjectDigestInfo)obj;
        if(obj != null)
            return new ObjectDigestInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static ObjectDigestInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ObjectDigestInfo(int digestedObjectType, ASN1ObjectIdentifier otherObjectTypeID, AlgorithmIdentifier digestAlgorithm, byte objectDigest[])
    {
        this.digestedObjectType = new ASN1Enumerated(digestedObjectType);
        if(digestedObjectType == 2)
            this.otherObjectTypeID = otherObjectTypeID;
        this.digestAlgorithm = digestAlgorithm;
        this.objectDigest = new DERBitString(objectDigest);
    }

    private ObjectDigestInfo(ASN1Sequence seq)
    {
        if(seq.size() > 4 || seq.size() < 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        digestedObjectType = ASN1Enumerated.getInstance(seq.getObjectAt(0));
        int offset = 0;
        if(seq.size() == 4)
        {
            otherObjectTypeID = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(1));
            offset++;
        }
        digestAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1 + offset));
        objectDigest = DERBitString.getInstance(seq.getObjectAt(2 + offset));
    }

    public ASN1Enumerated getDigestedObjectType()
    {
        return digestedObjectType;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID()
    {
        return otherObjectTypeID;
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        return digestAlgorithm;
    }

    public DERBitString getObjectDigest()
    {
        return objectDigest;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(digestedObjectType);
        if(otherObjectTypeID != null)
            v.add(otherObjectTypeID);
        v.add(digestAlgorithm);
        v.add(objectDigest);
        return new DERSequence(v);
    }

    public static final int publicKey = 0;
    public static final int publicKeyCert = 1;
    public static final int otherObjectDigest = 2;
    ASN1Enumerated digestedObjectType;
    ASN1ObjectIdentifier otherObjectTypeID;
    AlgorithmIdentifier digestAlgorithm;
    DERBitString objectDigest;
}
