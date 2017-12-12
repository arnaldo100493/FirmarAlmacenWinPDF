// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            OriginatorInformation, CMSProcessableByteArray, CMSException, CMSUtils, 
//            CMSEnvelopedHelper, RecipientInformationStore

public class CMSEnvelopedData
{

    public CMSEnvelopedData(byte envelopedData[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(envelopedData));
    }

    public CMSEnvelopedData(InputStream envelopedData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(envelopedData));
    }

    public CMSEnvelopedData(ContentInfo contentInfo)
        throws CMSException
    {
        this.contentInfo = contentInfo;
        try
        {
            EnvelopedData envData = EnvelopedData.getInstance(contentInfo.getContent());
            if(envData.getOriginatorInfo() != null)
                originatorInfo = new OriginatorInformation(envData.getOriginatorInfo());
            ASN1Set recipientInfos = envData.getRecipientInfos();
            EncryptedContentInfo encInfo = envData.getEncryptedContentInfo();
            encAlg = encInfo.getContentEncryptionAlgorithm();
            CMSReadable readable = new CMSProcessableByteArray(encInfo.getEncryptedContent().getOctets());
            CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSEnvelopedSecureReadable(encAlg, readable);
            recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, encAlg, secureReadable);
            unprotectedAttributes = envData.getUnprotectedAttrs();
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

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    public OriginatorInformation getOriginatorInfo()
    {
        return originatorInfo;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm()
    {
        return encAlg;
    }

    public String getEncryptionAlgOID()
    {
        return encAlg.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams()
    {
        try
        {
            return encodeObj(encAlg.getParameters());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
    }

    /**
     * @deprecated Method getEncryptionAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getEncryptionAlgorithmParameters(String provider)
        throws CMSException, NoSuchProviderException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(encAlg);
    }

    /**
     * @deprecated Method getEncryptionAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getEncryptionAlgorithmParameters(Provider provider)
        throws CMSException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(encAlg);
    }

    public RecipientInformationStore getRecipientInfos()
    {
        return recipientInfoStore;
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

    public AttributeTable getUnprotectedAttributes()
    {
        if(unprotectedAttributes == null)
            return null;
        else
            return new AttributeTable(unprotectedAttributes);
    }

    public byte[] getEncoded()
        throws IOException
    {
        return contentInfo.getEncoded();
    }

    RecipientInformationStore recipientInfoStore;
    ContentInfo contentInfo;
    private AlgorithmIdentifier encAlg;
    private ASN1Set unprotectedAttributes;
    private OriginatorInformation originatorInfo;
}
