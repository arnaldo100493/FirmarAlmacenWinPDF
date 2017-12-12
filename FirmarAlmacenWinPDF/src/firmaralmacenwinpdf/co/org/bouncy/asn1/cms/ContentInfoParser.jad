// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentInfoParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.io.IOException;

public class ContentInfoParser
{

    public ContentInfoParser(ASN1SequenceParser seq)
        throws IOException
    {
        contentType = (ASN1ObjectIdentifier)seq.readObject();
        content = (ASN1TaggedObjectParser)seq.readObject();
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return contentType;
    }

    public ASN1Encodable getContent(int tag)
        throws IOException
    {
        if(content != null)
            return content.getObjectParser(tag, true);
        else
            return null;
    }

    private ASN1ObjectIdentifier contentType;
    private ASN1TaggedObjectParser content;
}
