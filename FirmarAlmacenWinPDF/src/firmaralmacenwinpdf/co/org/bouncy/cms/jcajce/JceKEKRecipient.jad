// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKEKRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.KEKRecipient;
import co.org.bouncy.operator.OperatorException;
import co.org.bouncy.operator.SymmetricKeyUnwrapper;
import java.security.Key;
import java.security.Provider;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public abstract class JceKEKRecipient
    implements KEKRecipient
{

    public JceKEKRecipient(SecretKey recipientKey)
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        contentHelper = helper;
        this.recipientKey = recipientKey;
    }

    public JceKEKRecipient setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        contentHelper = helper;
        return this;
    }

    public JceKEKRecipient setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        contentHelper = helper;
        return this;
    }

    public JceKEKRecipient setContentProvider(Provider provider)
    {
        contentHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKEKRecipient setContentProvider(String providerName)
    {
        contentHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        SymmetricKeyUnwrapper unwrapper = helper.createSymmetricUnwrapper(keyEncryptionAlgorithm, recipientKey);
        try
        {
            return helper.getJceKey(contentEncryptionAlgorithm.getAlgorithm(), unwrapper.generateUnwrappedKey(contentEncryptionAlgorithm, encryptedContentEncryptionKey));
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception unwrapping key: ").append(e.getMessage()).toString(), e);
        }
    }

    private SecretKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
}
