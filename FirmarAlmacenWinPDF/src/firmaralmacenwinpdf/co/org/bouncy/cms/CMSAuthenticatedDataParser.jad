// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedDataParser.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import co.org.bouncy.operator.DigestCalculatorProvider;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSContentInfoParser, OriginatorInformation, CMSException, CMSProcessableInputStream, 
//            CMSReadable, CMSEnvelopedHelper, RecipientInformationStore, AuthAttributesProvider

public class CMSAuthenticatedDataParser extends CMSContentInfoParser
{

    public CMSAuthenticatedDataParser(byte envelopedData[])
        throws CMSException, IOException
    {
        this(((InputStream) (new ByteArrayInputStream(envelopedData))));
    }

    public CMSAuthenticatedDataParser(byte envelopedData[], DigestCalculatorProvider digestCalculatorProvider)
        throws CMSException, IOException
    {
        this(((InputStream) (new ByteArrayInputStream(envelopedData))), digestCalculatorProvider);
    }

    public CMSAuthenticatedDataParser(InputStream envelopedData)
        throws CMSException, IOException
    {
        this(envelopedData, null);
    }

    public CMSAuthenticatedDataParser(InputStream envelopedData, DigestCalculatorProvider digestCalculatorProvider)
        throws CMSException, IOException
    {
        super(envelopedData);
        authAttrNotRead = true;
        authData = new AuthenticatedDataParser((ASN1SequenceParser)_contentInfo.getContent(16));
        OriginatorInfo info = authData.getOriginatorInfo();
        if(info != null)
            originatorInfo = new OriginatorInformation(info);
        ASN1Set recipientInfos = ASN1Set.getInstance(authData.getRecipientInfos().toASN1Primitive());
        macAlg = authData.getMacAlgorithm();
        AlgorithmIdentifier digestAlgorithm = authData.getDigestAlgorithm();
        if(digestAlgorithm != null)
        {
            if(digestCalculatorProvider == null)
                throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
            ContentInfoParser data = authData.getEnapsulatedContentInfo();
            CMSReadable readable = new CMSProcessableInputStream(((ASN1OctetStringParser)data.getContent(4)).getOctetStream());
            try
            {
                CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable(digestCalculatorProvider.get(digestAlgorithm), readable);
                recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, macAlg, secureReadable, new AuthAttributesProvider() {

                    public ASN1Set getAuthAttributes()
                    {
                        try
                        {
                            return getAuthAttrSet();
                        }
                        catch(IOException e)
                        {
                            throw new IllegalStateException("can't parse authenticated attributes!");
                        }
                    }

                    final CMSAuthenticatedDataParser this$0;

            
            {
                this$0 = CMSAuthenticatedDataParser.this;
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
            ContentInfoParser data = authData.getEnapsulatedContentInfo();
            CMSReadable readable = new CMSProcessableInputStream(((ASN1OctetStringParser)data.getContent(4)).getOctetStream());
            CMSSecureReadable secureReadable = new CMSEnvelopedHelper.CMSAuthenticatedSecureReadable(macAlg, readable);
            recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, macAlg, secureReadable);
        }
    }

    public OriginatorInformation getOriginatorInfo()
    {
        return originatorInfo;
    }

    public AlgorithmIdentifier getMacAlgorithm()
    {
        return macAlg;
    }

    public String getMacAlgOID()
    {
        return macAlg.getAlgorithm().toString();
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

    public byte[] getMac()
        throws IOException
    {
        if(mac == null)
        {
            getAuthAttrs();
            mac = authData.getMac().getOctets();
        }
        return Arrays.clone(mac);
    }

    private ASN1Set getAuthAttrSet()
        throws IOException
    {
        if(authAttrs == null && authAttrNotRead)
        {
            ASN1SetParser set = authData.getAuthAttrs();
            if(set != null)
                authAttrSet = (ASN1Set)set.toASN1Primitive();
            authAttrNotRead = false;
        }
        return authAttrSet;
    }

    public AttributeTable getAuthAttrs()
        throws IOException
    {
        if(authAttrs == null && authAttrNotRead)
        {
            ASN1Set set = getAuthAttrSet();
            if(set != null)
                authAttrs = new AttributeTable(set);
        }
        return authAttrs;
    }

    public AttributeTable getUnauthAttrs()
        throws IOException
    {
        if(unauthAttrs == null && unauthAttrNotRead)
        {
            ASN1SetParser set = authData.getUnauthAttrs();
            unauthAttrNotRead = false;
            if(set != null)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                ASN1Encodable o;
                while((o = set.readObject()) != null) 
                {
                    ASN1SequenceParser seq = (ASN1SequenceParser)o;
                    v.add(seq.toASN1Primitive());
                }
                unauthAttrs = new AttributeTable(new DERSet(v));
            }
        }
        return unauthAttrs;
    }

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    public byte[] getContentDigest()
    {
        if(authAttrs != null)
            return ASN1OctetString.getInstance(authAttrs.get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        else
            return null;
    }

    RecipientInformationStore recipientInfoStore;
    AuthenticatedDataParser authData;
    private AlgorithmIdentifier macAlg;
    private byte mac[];
    private AttributeTable authAttrs;
    private ASN1Set authAttrSet;
    private AttributeTable unauthAttrs;
    private boolean authAttrNotRead;
    private boolean unauthAttrNotRead;
    private OriginatorInformation originatorInfo;

}
