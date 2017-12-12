// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCSPBEInputDecryptorProviderBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Provider;
import javax.crypto.*;
import javax.crypto.spec.*;

public class JcePKCSPBEInputDecryptorProviderBuilder
{

    public JcePKCSPBEInputDecryptorProviderBuilder()
    {
        helper = new DefaultJcaJceHelper();
        wrongPKCS12Zero = false;
        keySizeProvider = DefaultSecretKeyProvider.INSTANCE;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setTryWrongPKCS12Zero(boolean tryWrong)
    {
        wrongPKCS12Zero = tryWrong;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setKeySizeProvider(SecretKeySizeProvider keySizeProvider)
    {
        this.keySizeProvider = keySizeProvider;
        return this;
    }

    public InputDecryptorProvider build(final char password[])
    {
        return new InputDecryptorProvider() {

            public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier)
                throws OperatorCreationException
            {
                ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                try
                {
                    if(algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds))
                    {
                        PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                        PBEKeySpec pbeSpec = new PBEKeySpec(password);
                        SecretKeyFactory keyFact = helper.createSecretKeyFactory(algorithm.getId());
                        PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                        key = keyFact.generateSecret(pbeSpec);
                        if(key instanceof BCPBEKey)
                            ((BCPBEKey)key).setTryWrongPKCS12Zero(wrongPKCS12Zero);
                        cipher = helper.createCipher(algorithm.getId());
                        cipher.init(2, key, defParams);
                        encryptionAlg = algorithmIdentifier;
                    } else
                    if(algorithm.equals(PKCSObjectIdentifiers.id_PBES2))
                    {
                        PBES2Parameters alg = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                        PBKDF2Params func = PBKDF2Params.getInstance(alg.getKeyDerivationFunc().getParameters());
                        AlgorithmIdentifier encScheme = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
                        SecretKeyFactory keyFact = helper.createSecretKeyFactory(alg.getKeyDerivationFunc().getAlgorithm().getId());
                        key = keyFact.generateSecret(new PBEKeySpec(password, func.getSalt(), func.getIterationCount().intValue(), keySizeProvider.getKeySize(encScheme)));
                        cipher = helper.createCipher(alg.getEncryptionScheme().getAlgorithm().getId());
                        encryptionAlg = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
                        cipher.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(alg.getEncryptionScheme().getParameters()).getOctets()));
                    }
                }
                catch(Exception e)
                {
                    throw new OperatorCreationException((new StringBuilder()).append("unable to create InputDecryptor: ").append(e.getMessage()).toString(), e);
                }
                return new InputDecryptor() {

                    public AlgorithmIdentifier getAlgorithmIdentifier()
                    {
                        return encryptionAlg;
                    }

                    public InputStream getInputStream(InputStream input)
                    {
                        return new CipherInputStream(input, cipher);
                    }

                    public GenericKey getKey()
                    {
                        return new JceGenericKey(encryptionAlg, key);
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
;
            }

            private Cipher cipher;
            private SecretKey key;
            private AlgorithmIdentifier encryptionAlg;
            final char val$password[];
            final JcePKCSPBEInputDecryptorProviderBuilder this$0;




            
            {
                this$0 = JcePKCSPBEInputDecryptorProviderBuilder.this;
                password = ac;
                super();
            }
        }
;
    }

    private JcaJceHelper helper;
    private boolean wrongPKCS12Zero;
    private SecretKeySizeProvider keySizeProvider;



}
