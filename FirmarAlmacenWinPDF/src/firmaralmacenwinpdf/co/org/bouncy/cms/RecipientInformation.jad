// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import co.org.bouncy.util.io.Streams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSTypedStream, CMSUtils, CMSEnvelopedHelper, 
//            RecipientOperator, AuthAttributesProvider, CMSSecureReadable, RecipientId, 
//            Recipient

public abstract class RecipientInformation
{

    RecipientInformation(AlgorithmIdentifier keyEncAlg, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        this.keyEncAlg = keyEncAlg;
        this.messageAlgorithm = messageAlgorithm;
        this.secureReadable = secureReadable;
        this.additionalData = additionalData;
    }

    public RecipientId getRID()
    {
        return rid;
    }

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm()
    {
        return keyEncAlg;
    }

    public String getKeyEncryptionAlgOID()
    {
        return keyEncAlg.getObjectId().getId();
    }

    public byte[] getKeyEncryptionAlgParams()
    {
        try
        {
            return encodeObj(keyEncAlg.getParameters());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
    }

    /**
     * @deprecated Method getKeyEncryptionAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getKeyEncryptionAlgorithmParameters(String provider)
        throws CMSException, NoSuchProviderException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(keyEncAlg);
    }

    /**
     * @deprecated Method getKeyEncryptionAlgorithmParameters is deprecated
     */

    public AlgorithmParameters getKeyEncryptionAlgorithmParameters(Provider provider)
        throws CMSException
    {
        return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(keyEncAlg);
    }

    /**
     * @deprecated Method getContent is deprecated
     */

    public byte[] getContent(Key key, String provider)
        throws CMSException, NoSuchProviderException
    {
        return getContent(key, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getContent is deprecated
     */

    public byte[] getContent(Key key, Provider provider)
        throws CMSException
    {
        try
        {
            return CMSUtils.streamToByteArray(getContentStream(key, provider).getContentStream());
        }
        catch(IOException e)
        {
            throw new RuntimeException((new StringBuilder()).append("unable to parse internal stream: ").append(e).toString());
        }
    }

    public byte[] getContentDigest()
    {
        if(secureReadable instanceof CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable)
            return ((CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable)secureReadable).getDigest();
        else
            return null;
    }

    public byte[] getMac()
    {
        if(resultMac == null && operator.isMacBased())
        {
            if(additionalData != null)
                try
                {
                    Streams.drain(operator.getInputStream(new ByteArrayInputStream(additionalData.getAuthAttributes().getEncoded("DER"))));
                }
                catch(IOException e)
                {
                    throw new IllegalStateException((new StringBuilder()).append("unable to drain input: ").append(e.getMessage()).toString());
                }
            resultMac = operator.getMac();
        }
        return resultMac;
    }

    public byte[] getContent(Recipient recipient)
        throws CMSException
    {
        try
        {
            return CMSUtils.streamToByteArray(getContentStream(recipient).getContentStream());
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to parse internal stream: ").append(e.getMessage()).toString(), e);
        }
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

    public CMSTypedStream getContentStream(Key key, String provider)
        throws CMSException, NoSuchProviderException
    {
        return getContentStream(key, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

    public abstract CMSTypedStream getContentStream(Key key, Provider provider)
        throws CMSException;

    public CMSTypedStream getContentStream(Recipient recipient)
        throws CMSException, IOException
    {
        operator = getRecipientOperator(recipient);
        if(additionalData != null)
            return new CMSTypedStream(secureReadable.getInputStream());
        else
            return new CMSTypedStream(operator.getInputStream(secureReadable.getInputStream()));
    }

    protected abstract RecipientOperator getRecipientOperator(Recipient recipient)
        throws CMSException, IOException;

    protected RecipientId rid;
    protected AlgorithmIdentifier keyEncAlg;
    protected AlgorithmIdentifier messageAlgorithm;
    protected CMSSecureReadable secureReadable;
    private AuthAttributesProvider additionalData;
    private byte resultMac[];
    private RecipientOperator operator;
}
