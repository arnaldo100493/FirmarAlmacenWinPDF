// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePasswordRecipientInfoGenerator.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.PasswordRecipientInfoGenerator;
import co.org.bouncy.operator.GenericKey;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public class JcePasswordRecipientInfoGenerator extends PasswordRecipientInfoGenerator
{

    public JcePasswordRecipientInfoGenerator(ASN1ObjectIdentifier kekAlgorithm, char password[])
    {
        super(kekAlgorithm, password);
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    public JcePasswordRecipientInfoGenerator setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JcePasswordRecipientInfoGenerator setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    public byte[] generateEncryptedBytes(AlgorithmIdentifier keyEncryptionAlgorithm, byte derivedKey[], GenericKey contentEncryptionKey)
        throws CMSException
    {
        Key contentEncryptionKeySpec = helper.getJceKey(contentEncryptionKey);
        Cipher keyEncryptionCipher = helper.createRFC3211Wrapper(keyEncryptionAlgorithm.getAlgorithm());
        try
        {
            IvParameterSpec ivSpec = new IvParameterSpec(ASN1OctetString.getInstance(keyEncryptionAlgorithm.getParameters()).getOctets());
            keyEncryptionCipher.init(3, new SecretKeySpec(derivedKey, keyEncryptionCipher.getAlgorithm()), ivSpec);
            return keyEncryptionCipher.wrap(contentEncryptionKeySpec);
        }
        catch(GeneralSecurityException e)
        {
            throw new CMSException((new StringBuilder()).append("cannot process content encryption key: ").append(e.getMessage()).toString(), e);
        }
    }

    private EnvelopedDataHelper helper;
}
