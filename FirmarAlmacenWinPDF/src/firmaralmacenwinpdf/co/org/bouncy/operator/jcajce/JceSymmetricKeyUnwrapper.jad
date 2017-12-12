// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceSymmetricKeyUnwrapper.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            OperatorHelper, JceGenericKey

public class JceSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper
{

    public JceSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey)
    {
        super(algorithmIdentifier);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.secretKey = secretKey;
    }

    public JceSymmetricKeyUnwrapper setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyUnwrapper setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedKey[])
        throws OperatorException
    {
        try
        {
            Cipher keyCipher = helper.createSymmetricWrapper(getAlgorithmIdentifier().getAlgorithm());
            keyCipher.init(4, secretKey);
            return new JceGenericKey(encryptedKeyAlgorithm, keyCipher.unwrap(encryptedKey, helper.getKeyAlgorithmName(encryptedKeyAlgorithm.getAlgorithm()), 3));
        }
        catch(InvalidKeyException e)
        {
            throw new OperatorException("key invalid in message.", e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new OperatorException("can't find algorithm.", e);
        }
    }

    private OperatorHelper helper;
    private SecretKey secretKey;
}
