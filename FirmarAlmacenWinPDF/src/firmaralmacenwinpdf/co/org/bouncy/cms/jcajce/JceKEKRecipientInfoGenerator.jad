// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKEKRecipientInfoGenerator.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.cms.KEKIdentifier;
import co.org.bouncy.cms.KEKRecipientInfoGenerator;
import co.org.bouncy.operator.jcajce.JceSymmetricKeyWrapper;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

public class JceKEKRecipientInfoGenerator extends KEKRecipientInfoGenerator
{

    public JceKEKRecipientInfoGenerator(KEKIdentifier kekIdentifier, SecretKey keyEncryptionKey)
    {
        super(kekIdentifier, new JceSymmetricKeyWrapper(keyEncryptionKey));
    }

    public JceKEKRecipientInfoGenerator(byte keyIdentifier[], SecretKey keyEncryptionKey)
    {
        this(new KEKIdentifier(keyIdentifier, null, null), keyEncryptionKey);
    }

    public JceKEKRecipientInfoGenerator setProvider(Provider provider)
    {
        ((JceSymmetricKeyWrapper)wrapper).setProvider(provider);
        return this;
    }

    public JceKEKRecipientInfoGenerator setProvider(String providerName)
    {
        ((JceSymmetricKeyWrapper)wrapper).setProvider(providerName);
        return this;
    }

    public JceKEKRecipientInfoGenerator setSecureRandom(SecureRandom random)
    {
        ((JceSymmetricKeyWrapper)wrapper).setSecureRandom(random);
        return this;
    }
}
