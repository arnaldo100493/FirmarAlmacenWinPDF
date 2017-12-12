// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceAsymmetricValueDecryptorGenerator.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cert.crmf.ValueDecryptorGenerator;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.cert.crmf.jcajce:
//            CRMFHelper

public class JceAsymmetricValueDecryptorGenerator
    implements ValueDecryptorGenerator
{

    public JceAsymmetricValueDecryptorGenerator(PrivateKey recipientKey)
    {
        helper = new CRMFHelper(new DefaultJcaJceHelper());
        this.recipientKey = recipientKey;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(Provider provider)
    {
        helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(String providerName)
    {
        helper = new CRMFHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    private Key extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CRMFException
    {
        try
        {
            Key sKey = null;
            Cipher keyCipher = helper.createCipher(keyEncryptionAlgorithm.getAlgorithm());
            try
            {
                keyCipher.init(4, recipientKey);
                sKey = keyCipher.unwrap(encryptedContentEncryptionKey, contentEncryptionAlgorithm.getAlgorithm().getId(), 3);
            }
            catch(GeneralSecurityException e) { }
            catch(IllegalStateException e) { }
            catch(UnsupportedOperationException e) { }
            catch(ProviderException e) { }
            if(sKey == null)
            {
                keyCipher.init(2, recipientKey);
                sKey = new SecretKeySpec(keyCipher.doFinal(encryptedContentEncryptionKey), contentEncryptionAlgorithm.getAlgorithm().getId());
            }
            return sKey;
        }
        catch(InvalidKeyException e)
        {
            throw new CRMFException("key invalid in message.", e);
        }
        catch(IllegalBlockSizeException e)
        {
            throw new CRMFException("illegal blocksize in message.", e);
        }
        catch(BadPaddingException e)
        {
            throw new CRMFException("bad padding in message.", e);
        }
    }

    public InputDecryptor getValueDecryptor(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CRMFException
    {
        Key secretKey = extractSecretKey(keyEncryptionAlgorithm, contentEncryptionAlgorithm, encryptedContentEncryptionKey);
        final Cipher dataCipher = helper.createContentCipher(secretKey, contentEncryptionAlgorithm);
        return new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentEncryptionAlgorithm;
            }

            public InputStream getInputStream(InputStream dataIn)
            {
                return new CipherInputStream(dataIn, dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Cipher val$dataCipher;
            final JceAsymmetricValueDecryptorGenerator this$0;

            
            {
                this$0 = JceAsymmetricValueDecryptorGenerator.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = cipher;
                super();
            }
        }
;
    }

    private PrivateKey recipientKey;
    private CRMFHelper helper;
}
