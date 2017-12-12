// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompressedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfo

public class CompressedData extends ASN1Object
{

    public CompressedData(AlgorithmIdentifier compressionAlgorithm, ContentInfo encapContentInfo)
    {
        version = new ASN1Integer(0L);
        this.compressionAlgorithm = compressionAlgorithm;
        this.encapContentInfo = encapContentInfo;
    }

    private CompressedData(ASN1Sequence seq)
    {
        version = (ASN1Integer)seq.getObjectAt(0);
        compressionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        encapContentInfo = ContentInfo.getInstance(seq.getObjectAt(2));
    }

    public static CompressedData getInstance(ASN1TaggedObject _ato, boolean _explicit)
    {
        return getInstance(ASN1Sequence.getInstance(_ato, _explicit));
    }

    public static CompressedData getInstance(Object obj)
    {
        if(obj instanceof CompressedData)
            return (CompressedData)obj;
        if(obj != null)
            return new CompressedData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier()
    {
        return compressionAlgorithm;
    }

    public ContentInfo getEncapContentInfo()
    {
        return encapContentInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(compressionAlgorithm);
        v.add(encapContentInfo);
        return new BERSequence(v);
    }

    private ASN1Integer version;
    private AlgorithmIdentifier compressionAlgorithm;
    private ContentInfo encapContentInfo;
}
