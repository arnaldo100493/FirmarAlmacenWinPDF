// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedContentInfoParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;

public class EncryptedContentInfoParser
{

    public EncryptedContentInfoParser(ASN1SequenceParser seq)
        throws IOException
    {
        _contentType = (ASN1ObjectIdentifier)seq.readObject();
        _contentEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.readObject().toASN1Primitive());
        _encryptedContent = (ASN1TaggedObjectParser)seq.readObject();
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return _contentType;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm()
    {
        return _contentEncryptionAlgorithm;
    }

    public ASN1Encodable getEncryptedContent(int tag)
        throws IOException
    {
        return _encryptedContent.getObjectParser(tag, false);
    }

    private ASN1ObjectIdentifier _contentType;
    private AlgorithmIdentifier _contentEncryptionAlgorithm;
    private ASN1TaggedObjectParser _encryptedContent;
}
