// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedDataParser.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import java.io.*;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSContentInfoParser, OriginatorInformation, CMSProcessableInputStream, CMSException, 
//            CMSEnvelopedHelper, RecipientInformationStore

public class CMSEnvelopedDataParser extends CMSContentInfoParser
{

    public CMSEnvelopedDataParser(byte envelopedData[])
        throws CMSException, IOException
    {
        this(((InputStream) (new ByteArrayInputStream(envelopedData))));
    }

    public CMSEnvelopedDataParser(InputStream envelopedData)
        throws CMSException, IOException
    {
        super(envelopedData);
        attrNotRead = true;
        this.envelopedData = new EnvelopedDataParser((ASN1SequenceParser)_contentInfo.getContent(16));
        OriginatorInfo info = this.envelopedData.getOriginatorInfo();
        if(info != null)
            originatorInfo = new OriginatorInformation(info);
        ASN1Set recipientInfos = ASN1Set.getInstance(this.envelopedData.getRecipientInfos().toASN1Primitive());
        EncryptedContentInfoParser encInfo = this.envelopedData.getEncryptedContentInfo();
        encAlg = encInfo.getContentEncryptionAlgorithm();
        CMSReadable readable = new CMSProcessableInputStream(((ASN1OctetStringParser)encInfo.getEncryptedContent(4)).getOctetStream());
        CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSEnvelopedSecureReadable(encAlg, readable);
        recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, encAlg, secureReadable);
    }

    public String getEncryptionAlgOID()
    {
        return encAlg.getAlgorithm().toString();
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

    public AlgorithmIdentifier getContentEncryptionAlgorithm()
    {
        return encAlg;
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

    public OriginatorInformation getOriginatorInfo()
    {
        return originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos()
    {
        return recipientInfoStore;
    }

    public AttributeTable getUnprotectedAttributes()
        throws IOException
    {
        if(unprotectedAttributes == null && attrNotRead)
        {
            ASN1SetParser set = envelopedData.getUnprotectedAttrs();
            attrNotRead = false;
            if(set != null)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                ASN1Encodable o;
                while((o = set.readObject()) != null) 
                {
                    ASN1SequenceParser seq = (ASN1SequenceParser)o;
                    v.add(seq.toASN1Primitive());
                }
                unprotectedAttributes = new AttributeTable(new DERSet(v));
            }
        }
        return unprotectedAttributes;
    }

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    RecipientInformationStore recipientInfoStore;
    EnvelopedDataParser envelopedData;
    private AlgorithmIdentifier encAlg;
    private AttributeTable unprotectedAttributes;
    private boolean attrNotRead;
    private OriginatorInformation originatorInfo;
}
