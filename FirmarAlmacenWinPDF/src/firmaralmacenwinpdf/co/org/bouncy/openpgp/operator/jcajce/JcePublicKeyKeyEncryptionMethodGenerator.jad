// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePublicKeyKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator;
import java.security.*;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaPGPKeyConverter

public class JcePublicKeyKeyEncryptionMethodGenerator extends PublicKeyKeyEncryptionMethodGenerator
{

    public JcePublicKeyKeyEncryptionMethodGenerator(PGPPublicKey key)
    {
        super(key);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        keyConverter = new JcaPGPKeyConverter();
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        keyConverter.setProvider(provider);
        return this;
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        keyConverter.setProvider(providerName);
        return this;
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    protected byte[] encryptSessionInfo(PGPPublicKey pubKey, byte sessionInfo[])
        throws PGPException
    {
        try
        {
            Cipher c = helper.createPublicKeyCipher(pubKey.getAlgorithm());
            java.security.Key key = keyConverter.getPublicKey(pubKey);
            c.init(1, key, random);
            return c.doFinal(sessionInfo);
        }
        catch(IllegalBlockSizeException e)
        {
            throw new PGPException((new StringBuilder()).append("illegal block size: ").append(e.getMessage()).toString(), e);
        }
        catch(BadPaddingException e)
        {
            throw new PGPException((new StringBuilder()).append("bad padding: ").append(e.getMessage()).toString(), e);
        }
        catch(InvalidKeyException e)
        {
            throw new PGPException((new StringBuilder()).append("key invalid: ").append(e.getMessage()).toString(), e);
        }
    }

    private OperatorHelper helper;
    private SecureRandom random;
    private JcaPGPKeyConverter keyConverter;
}
