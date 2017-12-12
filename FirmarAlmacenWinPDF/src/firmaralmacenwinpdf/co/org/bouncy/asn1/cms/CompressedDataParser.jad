// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompressedDataParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfoParser

public class CompressedDataParser
{

    public CompressedDataParser(ASN1SequenceParser seq)
        throws IOException
    {
        _version = (ASN1Integer)seq.readObject();
        _compressionAlgorithm = AlgorithmIdentifier.getInstance(seq.readObject().toASN1Primitive());
        _encapContentInfo = new ContentInfoParser((ASN1SequenceParser)seq.readObject());
    }

    public ASN1Integer getVersion()
    {
        return _version;
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier()
    {
        return _compressionAlgorithm;
    }

    public ContentInfoParser getEncapContentInfo()
    {
        return _encapContentInfo;
    }

    private ASN1Integer _version;
    private AlgorithmIdentifier _compressionAlgorithm;
    private ContentInfoParser _encapContentInfo;
}
