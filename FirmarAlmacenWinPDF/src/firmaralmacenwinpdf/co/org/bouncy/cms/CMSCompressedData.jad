// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSCompressedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.CompressedData;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.operator.InputExpander;
import co.org.bouncy.operator.InputExpanderProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSUtils

public class CMSCompressedData
{

    public CMSCompressedData(byte compressedData[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(compressedData));
    }

    public CMSCompressedData(InputStream compressedData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(compressedData));
    }

    public CMSCompressedData(ContentInfo contentInfo)
        throws CMSException
    {
        this.contentInfo = contentInfo;
        try
        {
            comData = CompressedData.getInstance(contentInfo.getContent());
        }
        catch(ClassCastException e)
        {
            throw new CMSException("Malformed content.", e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("Malformed content.", e);
        }
    }

    /**
     * @deprecated Method getContent is deprecated
     */

    public byte[] getContent()
        throws CMSException
    {
        ContentInfo content = comData.getEncapContentInfo();
        ASN1OctetString bytes = (ASN1OctetString)content.getContent();
        InflaterInputStream zIn = new InflaterInputStream(bytes.getOctetStream());
        try
        {
            return CMSUtils.streamToByteArray(zIn);
        }
        catch(IOException e)
        {
            throw new CMSException("exception reading compressed stream.", e);
        }
    }

    /**
     * @deprecated Method getContent is deprecated
     */

    public byte[] getContent(int limit)
        throws CMSException
    {
        ContentInfo content = comData.getEncapContentInfo();
        ASN1OctetString bytes = (ASN1OctetString)content.getContent();
        InflaterInputStream zIn = new InflaterInputStream(bytes.getOctetStream());
        try
        {
            return CMSUtils.streamToByteArray(zIn, limit);
        }
        catch(IOException e)
        {
            throw new CMSException("exception reading compressed stream.", e);
        }
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return contentInfo.getContentType();
    }

    public byte[] getContent(InputExpanderProvider expanderProvider)
        throws CMSException
    {
        ContentInfo content = comData.getEncapContentInfo();
        ASN1OctetString bytes = (ASN1OctetString)content.getContent();
        InputExpander expander = expanderProvider.get(comData.getCompressionAlgorithmIdentifier());
        InputStream zIn = expander.getInputStream(bytes.getOctetStream());
        try
        {
            return CMSUtils.streamToByteArray(zIn);
        }
        catch(IOException e)
        {
            throw new CMSException("exception reading compressed stream.", e);
        }
    }

    /**
     * @deprecated Method getContentInfo is deprecated
     */

    public ContentInfo getContentInfo()
    {
        return contentInfo;
    }

    public ContentInfo toASN1Structure()
    {
        return contentInfo;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return contentInfo.getEncoded();
    }

    ContentInfo contentInfo;
    CompressedData comData;
}
