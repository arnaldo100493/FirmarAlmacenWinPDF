// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceAlgorithmIdentifierConverter.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import java.io.IOException;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public class JceAlgorithmIdentifierConverter
{

    public JceAlgorithmIdentifierConverter()
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    public JceAlgorithmIdentifierConverter setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceAlgorithmIdentifierConverter setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    public AlgorithmParameters getAlgorithmParameters(AlgorithmIdentifier algorithmIdentifier)
        throws CMSException
    {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if(parameters == null)
            return null;
        try
        {
            AlgorithmParameters params = helper.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
            params.init(parameters.toASN1Primitive().getEncoded(), "ASN.1");
            return params;
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new CMSException("can't find parameters for algorithm", e);
        }
        catch(IOException e)
        {
            throw new CMSException("can't parse parameters", e);
        }
        catch(NoSuchProviderException e)
        {
            throw new CMSException("can't find provider for algorithm", e);
        }
    }

    private EnvelopedDataHelper helper;
    private SecureRandom random;
}
