// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfo

public class DigestedData extends ASN1Object
{

    public DigestedData(AlgorithmIdentifier digestAlgorithm, ContentInfo encapContentInfo, byte digest[])
    {
        version = new ASN1Integer(0L);
        this.digestAlgorithm = digestAlgorithm;
        this.encapContentInfo = encapContentInfo;
        this.digest = new DEROctetString(digest);
    }

    private DigestedData(ASN1Sequence seq)
    {
        version = (ASN1Integer)seq.getObjectAt(0);
        digestAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        encapContentInfo = ContentInfo.getInstance(seq.getObjectAt(2));
        digest = ASN1OctetString.getInstance(seq.getObjectAt(3));
    }

    public static DigestedData getInstance(ASN1TaggedObject _ato, boolean _explicit)
    {
        return getInstance(ASN1Sequence.getInstance(_ato, _explicit));
    }

    public static DigestedData getInstance(Object obj)
    {
        if(obj instanceof DigestedData)
            return (DigestedData)obj;
        if(obj != null)
            return new DigestedData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        return digestAlgorithm;
    }

    public ContentInfo getEncapContentInfo()
    {
        return encapContentInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(digestAlgorithm);
        v.add(encapContentInfo);
        v.add(digest);
        return new BERSequence(v);
    }

    public byte[] getDigest()
    {
        return digest.getOctets();
    }

    private ASN1Integer version;
    private AlgorithmIdentifier digestAlgorithm;
    private ContentInfo encapContentInfo;
    private ASN1OctetString digest;
}
