// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSCompressedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.OutputCompressor;
import java.io.*;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSCompressedData, CMSProcessable, CMSTypedData

public class CMSCompressedDataGenerator
{

    public CMSCompressedDataGenerator()
    {
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSCompressedData generate(CMSProcessable content, String compressionOID)
        throws CMSException
    {
        AlgorithmIdentifier comAlgId;
        ASN1OctetString comOcts;
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DeflaterOutputStream zOut = new DeflaterOutputStream(bOut);
            content.write(zOut);
            zOut.close();
            comAlgId = new AlgorithmIdentifier(new ASN1ObjectIdentifier(compressionOID));
            comOcts = new BEROctetString(bOut.toByteArray());
        }
        catch(IOException e)
        {
            throw new CMSException("exception encoding data.", e);
        }
        ContentInfo comContent = new ContentInfo(CMSObjectIdentifiers.data, comOcts);
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.compressedData, new CompressedData(comAlgId, comContent));
        return new CMSCompressedData(contentInfo);
    }

    public CMSCompressedData generate(CMSTypedData content, OutputCompressor compressor)
        throws CMSException
    {
        AlgorithmIdentifier comAlgId;
        ASN1OctetString comOcts;
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            OutputStream zOut = compressor.getOutputStream(bOut);
            content.write(zOut);
            zOut.close();
            comAlgId = compressor.getAlgorithmIdentifier();
            comOcts = new BEROctetString(bOut.toByteArray());
        }
        catch(IOException e)
        {
            throw new CMSException("exception encoding data.", e);
        }
        ContentInfo comContent = new ContentInfo(content.getContentType(), comOcts);
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.compressedData, new CompressedData(comAlgId, comContent));
        return new CMSCompressedData(contentInfo);
    }

    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";
}
