// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyTransRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.KeyTransRecipient;
import co.org.bouncy.operator.OperatorException;
import co.org.bouncy.operator.jcajce.JceAsymmetricKeyUnwrapper;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper, 
//            CMSUtils

public abstract class JceKeyTransRecipient
    implements KeyTransRecipient
{

    public JceKeyTransRecipient(PrivateKey recipientKey)
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        contentHelper = helper;
        extraMappings = new HashMap();
        this.recipientKey = recipientKey;
    }

    public JceKeyTransRecipient setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        contentHelper = helper;
        return this;
    }

    public JceKeyTransRecipient setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        contentHelper = helper;
        return this;
    }

    public JceKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier algorithm, String algorithmName)
    {
        extraMappings.put(algorithm, algorithmName);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(Provider provider)
    {
        contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(String providerName)
    {
        contentHelper = CMSUtils.createContentHelper(providerName);
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedEncryptionKey[])
        throws CMSException
    {
        JceAsymmetricKeyUnwrapper unwrapper = helper.createAsymmetricUnwrapper(keyEncryptionAlgorithm, recipientKey);
        if(!extraMappings.isEmpty())
        {
            ASN1ObjectIdentifier algorithm;
            for(Iterator it = extraMappings.keySet().iterator(); it.hasNext(); unwrapper.setAlgorithmMapping(algorithm, (String)extraMappings.get(algorithm)))
                algorithm = (ASN1ObjectIdentifier)it.next();

        }
        try
        {
            return helper.getJceKey(encryptedKeyAlgorithm.getAlgorithm(), unwrapper.generateUnwrappedKey(encryptedKeyAlgorithm, encryptedEncryptionKey));
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception unwrapping key: ").append(e.getMessage()).toString(), e);
        }
    }

    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
    protected Map extraMappings;
}
