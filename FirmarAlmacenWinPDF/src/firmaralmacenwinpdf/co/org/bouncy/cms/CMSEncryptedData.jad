// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEncryptedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.operator.InputDecryptor;
import co.org.bouncy.operator.InputDecryptorProvider;
import java.io.ByteArrayInputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSTypedStream, CMSUtils

public class CMSEncryptedData
{

    public CMSEncryptedData(ContentInfo contentInfo)
    {
        this.contentInfo = contentInfo;
        encryptedData = EncryptedData.getInstance(contentInfo.getContent());
    }

    public byte[] getContent(InputDecryptorProvider inputDecryptorProvider)
        throws CMSException
    {
        try
        {
            return CMSUtils.streamToByteArray(getContentStream(inputDecryptorProvider).getContentStream());
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to parse internal stream: ").append(e.getMessage()).toString(), e);
        }
    }

    public CMSTypedStream getContentStream(InputDecryptorProvider inputDecryptorProvider)
        throws CMSException
    {
        try
        {
            EncryptedContentInfo encContentInfo = encryptedData.getEncryptedContentInfo();
            InputDecryptor decrytor = inputDecryptorProvider.get(encContentInfo.getContentEncryptionAlgorithm());
            ByteArrayInputStream encIn = new ByteArrayInputStream(encContentInfo.getEncryptedContent().getOctets());
            return new CMSTypedStream(encContentInfo.getContentType(), decrytor.getInputStream(encIn));
        }
        catch(Exception e)
        {
            throw new CMSException((new StringBuilder()).append("unable to create stream: ").append(e.getMessage()).toString(), e);
        }
    }

    public ContentInfo toASN1Structure()
    {
        return contentInfo;
    }

    private ContentInfo contentInfo;
    private EncryptedData encryptedData;
}
