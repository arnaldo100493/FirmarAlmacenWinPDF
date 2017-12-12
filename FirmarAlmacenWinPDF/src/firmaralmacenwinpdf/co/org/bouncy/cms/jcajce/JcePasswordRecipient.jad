// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePasswordRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.PasswordRecipient;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public abstract class JcePasswordRecipient
    implements PasswordRecipient
{

    JcePasswordRecipient(char password[])
    {
        schemeID = 1;
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.password = password;
    }

    public JcePasswordRecipient setPasswordConversionScheme(int schemeID)
    {
        this.schemeID = schemeID;
        return this;
    }

    public JcePasswordRecipient setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JcePasswordRecipient setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, byte derivedKey[], byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        Cipher keyEncryptionCipher = helper.createRFC3211Wrapper(keyEncryptionAlgorithm.getAlgorithm());
        try
        {
            IvParameterSpec ivSpec = new IvParameterSpec(ASN1OctetString.getInstance(keyEncryptionAlgorithm.getParameters()).getOctets());
            keyEncryptionCipher.init(4, new SecretKeySpec(derivedKey, keyEncryptionCipher.getAlgorithm()), ivSpec);
            return keyEncryptionCipher.unwrap(encryptedContentEncryptionKey, contentEncryptionAlgorithm.getAlgorithm().getId(), 3);
        }
        catch(GeneralSecurityException e)
        {
            throw new CMSException((new StringBuilder()).append("cannot process content encryption key: ").append(e.getMessage()).toString(), e);
        }
    }

    public int getPasswordConversionScheme()
    {
        return schemeID;
    }

    public char[] getPassword()
    {
        return password;
    }

    private int schemeID;
    protected EnvelopedDataHelper helper;
    private char password[];
}
