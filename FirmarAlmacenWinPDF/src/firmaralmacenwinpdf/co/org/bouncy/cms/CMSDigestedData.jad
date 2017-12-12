// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSDigestedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cms.DigestedData;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.Arrays;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSProcessableByteArray, CMSUtils, CMSProcessable

public class CMSDigestedData
{

    public CMSDigestedData(byte compressedData[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(compressedData));
    }

    public CMSDigestedData(InputStream compressedData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(compressedData));
    }

    public CMSDigestedData(ContentInfo contentInfo)
        throws CMSException
    {
        this.contentInfo = contentInfo;
        try
        {
            digestedData = DigestedData.getInstance(contentInfo.getContent());
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

    public ASN1ObjectIdentifier getContentType()
    {
        return contentInfo.getContentType();
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        return digestedData.getDigestAlgorithm();
    }

    public CMSProcessable getDigestedContent()
        throws CMSException
    {
        ContentInfo content = digestedData.getEncapContentInfo();
        try
        {
            return new CMSProcessableByteArray(content.getContentType(), ((ASN1OctetString)content.getContent()).getOctets());
        }
        catch(Exception e)
        {
            throw new CMSException("exception reading digested stream.", e);
        }
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

    public boolean verify(DigestCalculatorProvider calculatorProvider)
        throws CMSException
    {
        try
        {
            ContentInfo content = digestedData.getEncapContentInfo();
            DigestCalculator calc = calculatorProvider.get(digestedData.getDigestAlgorithm());
            OutputStream dOut = calc.getOutputStream();
            dOut.write(((ASN1OctetString)content.getContent()).getOctets());
            return Arrays.areEqual(digestedData.getDigest(), calc.getDigest());
        }
        catch(OperatorCreationException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to create digest calculator: ").append(e.getMessage()).toString(), e);
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable process content: ").append(e.getMessage()).toString(), e);
        }
    }

    private ContentInfo contentInfo;
    private DigestedData digestedData;
}
