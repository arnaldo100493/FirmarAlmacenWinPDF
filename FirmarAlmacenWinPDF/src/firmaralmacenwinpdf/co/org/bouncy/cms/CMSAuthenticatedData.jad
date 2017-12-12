// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import co.org.bouncy.operator.DigestCalculatorProvider;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            OriginatorInformation, CMSProcessableByteArray, CMSException, CMSReadable, 
//            CMSUtils, CMSEnvelopedHelper, RecipientInformationStore, AuthAttributesProvider

public class CMSAuthenticatedData
{

    public CMSAuthenticatedData(byte authData[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authData));
    }

    public CMSAuthenticatedData(byte authData[], DigestCalculatorProvider digestCalculatorProvider)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authData), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(InputStream authData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authData));
    }

    public CMSAuthenticatedData(InputStream authData, DigestCalculatorProvider digestCalculatorProvider)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(authData), digestCalculatorProvider);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo)
        throws CMSException
    {
        this(contentInfo, null);
    }

    public CMSAuthenticatedData(ContentInfo contentInfo, DigestCalculatorProvider digestCalculatorProvider)
        throws CMSException
    {
        this.contentInfo = contentInfo;
        AuthenticatedData authData = AuthenticatedData.getInstance(contentInfo.getContent());
        if(authData.getOriginatorInfo() != null)
            originatorInfo = new OriginatorInformation(authData.getOriginatorInfo());
        ASN1Set recipientInfos = authData.getRecipientInfos();
        macAlg = authData.getMacAlgorithm();
        authAttrs = authData.getAuthAttrs();
        mac = authData.getMac().getOctets();
        unauthAttrs = authData.getUnauthAttrs();
        ContentInfo encInfo = authData.getEncapsulatedContentInfo();
        CMSReadable readable = new CMSProcessableByteArray(ASN1OctetString.getInstance(encInfo.getContent()).getOctets());
        if(authAttrs != null)
        {
            if(digestCalculatorProvider == null)
                throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
            try
            {
                CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable(digestCalculatorProvider.get(authData.getDigestAlgorithm()), readable);
                recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, macAlg, secureReadable, new AuthAttributesProvider() {

                    public ASN1Set getAuthAttributes()
                    {
                        return authAttrs;
                    }

                    final CMSAuthenticatedData this$0;

            
            {
                this$0 = CMSAuthenticatedData.this;
                super();
            }
                }
);
            }
            catch(OperatorCreationException e)
            {
                throw new CMSException((new StringBuilder()).append("unable to create digest calculator: ").append(e.getMessage()).toString(), e);
            }
        } else
        {
            CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSAuthenticatedSecureReadable(macAlg, readable);
            recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, macAlg, secureReadable);
        }
    }

    public OriginatorInformation getOriginatorInfo()
    {
        return originatorInfo;
    }

    public byte[] getMac()
    {
        return Arrays.clone(mac);
    }

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    public AlgorithmIdentifier getMacAlgorithm()
    {
        return macAlg;
    }

    public String getMacAlgOID()
    {
        return macAlg.getObjectId().getId();
    }

    public byte[] getMacAlgParams()
    {
        try
        {
            return encodeObj(macAlg.getParameters());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
    }

    /**
     * @deprecated Method getMacAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getMacAlgorithmParameters(String provider)
        throws CMSException, NoSuchProviderException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(macAlg);
    }

    /**
     * @deprecated Method getMacAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getMacAlgorithmParameters(Provider provider)
        throws CMSException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(macAlg);
    }

    public RecipientInformationStore getRecipientInfos()
    {
        return recipientInfoStore;
    }

    public ContentInfo getContentInfo()
    {
        return contentInfo;
    }

    public AttributeTable getAuthAttrs()
    {
        if(authAttrs == null)
            return null;
        else
            return new AttributeTable(authAttrs);
    }

    public AttributeTable getUnauthAttrs()
    {
        if(unauthAttrs == null)
            return null;
        else
            return new AttributeTable(unauthAttrs);
    }

    public byte[] getEncoded()
        throws IOException
    {
        return contentInfo.getEncoded();
    }

    public byte[] getContentDigest()
    {
        if(authAttrs != null)
            return ASN1OctetString.getInstance(getAuthAttrs().get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        else
            return null;
    }

    RecipientInformationStore recipientInfoStore;
    ContentInfo contentInfo;
    private AlgorithmIdentifier macAlg;
    private ASN1Set authAttrs;
    private ASN1Set unauthAttrs;
    private byte mac[];
    private OriginatorInformation originatorInfo;

}
