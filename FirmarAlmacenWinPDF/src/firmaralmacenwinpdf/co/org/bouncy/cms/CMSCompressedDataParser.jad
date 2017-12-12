// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSCompressedDataParser.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.CompressedDataParser;
import co.org.bouncy.asn1.cms.ContentInfoParser;
import co.org.bouncy.operator.InputExpander;
import co.org.bouncy.operator.InputExpanderProvider;
import java.io.*;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSContentInfoParser, CMSTypedStream, CMSException

public class CMSCompressedDataParser extends CMSContentInfoParser
{

    public CMSCompressedDataParser(byte compressedData[])
        throws CMSException
    {
        this(((InputStream) (new ByteArrayInputStream(compressedData))));
    }

    public CMSCompressedDataParser(InputStream compressedData)
        throws CMSException
    {
        super(compressedData);
    }

    /**
     * @deprecated Method getContent is deprecated
     */

    public CMSTypedStream getContent()
        throws CMSException
    {
        try
        {
            CompressedDataParser comData = new CompressedDataParser((ASN1SequenceParser)_contentInfo.getContent(16));
            ContentInfoParser content = comData.getEncapContentInfo();
            ASN1OctetStringParser bytes = (ASN1OctetStringParser)content.getContent(4);
            return new CMSTypedStream(content.getContentType().toString(), new InflaterInputStream(bytes.getOctetStream()));
        }
        catch(IOException e)
        {
            throw new CMSException("IOException reading compressed content.", e);
        }
    }

    public CMSTypedStream getContent(InputExpanderProvider expanderProvider)
        throws CMSException
    {
        try
        {
            CompressedDataParser comData = new CompressedDataParser((ASN1SequenceParser)_contentInfo.getContent(16));
            ContentInfoParser content = comData.getEncapContentInfo();
            InputExpander expander = expanderProvider.get(comData.getCompressionAlgorithmIdentifier());
            ASN1OctetStringParser bytes = (ASN1OctetStringParser)content.getContent(4);
            return new CMSTypedStream(content.getContentType().getId(), expander.getInputStream(bytes.getOctetStream()));
        }
        catch(IOException e)
        {
            throw new CMSException("IOException reading compressed content.", e);
        }
    }
}
