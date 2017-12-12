// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceAsymmetricKeyWrapper.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            OperatorHelper, OperatorUtils

public class JceAsymmetricKeyWrapper extends AsymmetricKeyWrapper
{

    public JceAsymmetricKeyWrapper(PublicKey publicKey)
    {
        super(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm());
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        extraMappings = new HashMap();
        this.publicKey = publicKey;
    }

    public JceAsymmetricKeyWrapper(X509Certificate certificate)
    {
        this(certificate.getPublicKey());
    }

    public JceAsymmetricKeyWrapper setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JceAsymmetricKeyWrapper setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public JceAsymmetricKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier algorithm, String algorithmName)
    {
        extraMappings.put(algorithm, algorithmName);
        return this;
    }

    public byte[] generateWrappedKey(GenericKey encryptionKey)
        throws OperatorException
    {
        Cipher keyEncryptionCipher = helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), extraMappings);
        byte encryptedKeyBytes[] = null;
        try
        {
            keyEncryptionCipher.init(3, publicKey, random);
            encryptedKeyBytes = keyEncryptionCipher.wrap(OperatorUtils.getJceKey(encryptionKey));
        }
        catch(GeneralSecurityException e) { }
        catch(IllegalStateException e) { }
        catch(UnsupportedOperationException e) { }
        catch(ProviderException e) { }
        if(encryptedKeyBytes == null)
            try
            {
                keyEncryptionCipher.init(1, publicKey, random);
                encryptedKeyBytes = keyEncryptionCipher.doFinal(OperatorUtils.getJceKey(encryptionKey).getEncoded());
            }
            catch(GeneralSecurityException e)
            {
                throw new OperatorException("unable to encrypt contents key", e);
            }
        return encryptedKeyBytes;
    }

    private OperatorHelper helper;
    private Map extraMappings;
    private PublicKey publicKey;
    private SecureRandom random;
}
