// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceAsymmetricKeyUnwrapper.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            OperatorHelper, JceGenericKey

public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper
{

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privKey)
    {
        super(algorithmIdentifier);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        extraMappings = new HashMap();
        this.privKey = privKey;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier algorithm, String algorithmName)
    {
        extraMappings.put(algorithm, algorithmName);
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedKey[])
        throws OperatorException
    {
        try
        {
            Key sKey = null;
            Cipher keyCipher = helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), extraMappings);
            try
            {
                keyCipher.init(4, privKey);
                sKey = keyCipher.unwrap(encryptedKey, helper.getKeyAlgorithmName(encryptedKeyAlgorithm.getAlgorithm()), 3);
            }
            catch(GeneralSecurityException e) { }
            catch(IllegalStateException e) { }
            catch(UnsupportedOperationException e) { }
            catch(ProviderException e) { }
            if(sKey == null)
            {
                keyCipher.init(2, privKey);
                sKey = new SecretKeySpec(keyCipher.doFinal(encryptedKey), encryptedKeyAlgorithm.getAlgorithm().getId());
            }
            return new JceGenericKey(encryptedKeyAlgorithm, sKey);
        }
        catch(InvalidKeyException e)
        {
            throw new OperatorException((new StringBuilder()).append("key invalid: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalBlockSizeException e)
        {
            throw new OperatorException((new StringBuilder()).append("illegal blocksize: ").append(e.getMessage()).toString(), e);
        }
        catch(BadPaddingException e)
        {
            throw new OperatorException((new StringBuilder()).append("bad padding: ").append(e.getMessage()).toString(), e);
        }
    }

    private OperatorHelper helper;
    private Map extraMappings;
    private PrivateKey privKey;
}
