// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceOpenSSLPKCS8DecryptorProviderBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.openssl.PEMException;
import co.org.bouncy.operator.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            PEMUtilities

public class JceOpenSSLPKCS8DecryptorProviderBuilder
{

    public JceOpenSSLPKCS8DecryptorProviderBuilder()
    {
        helper = new DefaultJcaJceHelper();
        helper = new DefaultJcaJceHelper();
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public InputDecryptorProvider build(final char password[])
        throws OperatorCreationException
    {
        return new InputDecryptorProvider() {

            public InputDecryptor get(final AlgorithmIdentifier algorithm)
                throws OperatorCreationException
            {
                try
                {
                    final Cipher cipher;
                    if(PEMUtilities.isPKCS5Scheme2(algorithm.getAlgorithm()))
                    {
                        PBES2Parameters params = PBES2Parameters.getInstance(algorithm.getParameters());
                        KeyDerivationFunc func = params.getKeyDerivationFunc();
                        EncryptionScheme scheme = params.getEncryptionScheme();
                        PBKDF2Params defParams = (PBKDF2Params)func.getParameters();
                        int iterationCount = defParams.getIterationCount().intValue();
                        byte salt[] = defParams.getSalt();
                        String oid = scheme.getAlgorithm().getId();
                        javax.crypto.SecretKey key = PEMUtilities.generateSecretKeyForPKCS5Scheme2(oid, password, salt, iterationCount);
                        cipher = helper.createCipher(oid);
                        AlgorithmParameters algParams = helper.createAlgorithmParameters(oid);
                        algParams.init(scheme.getParameters().toASN1Primitive().getEncoded());
                        cipher.init(2, key, algParams);
                    } else
                    if(PEMUtilities.isPKCS12(algorithm.getAlgorithm()))
                    {
                        PKCS12PBEParams params = PKCS12PBEParams.getInstance(algorithm.getParameters());
                        PBEKeySpec pbeSpec = new PBEKeySpec(password);
                        SecretKeyFactory secKeyFact = helper.createSecretKeyFactory(algorithm.getAlgorithm().getId());
                        PBEParameterSpec defParams = new PBEParameterSpec(params.getIV(), params.getIterations().intValue());
                        cipher = helper.createCipher(algorithm.getAlgorithm().getId());
                        cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
                    } else
                    if(PEMUtilities.isPKCS5Scheme1(algorithm.getAlgorithm()))
                    {
                        PBEParameter params = PBEParameter.getInstance(algorithm.getParameters());
                        PBEKeySpec pbeSpec = new PBEKeySpec(password);
                        SecretKeyFactory secKeyFact = helper.createSecretKeyFactory(algorithm.getAlgorithm().getId());
                        PBEParameterSpec defParams = new PBEParameterSpec(params.getSalt(), params.getIterationCount().intValue());
                        cipher = helper.createCipher(algorithm.getAlgorithm().getId());
                        cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
                    } else
                    {
                        throw new PEMException((new StringBuilder()).append("Unknown algorithm: ").append(algorithm.getAlgorithm()).toString());
                    }
                    return new InputDecryptor() {

                        public AlgorithmIdentifier getAlgorithmIdentifier()
                        {
                            return algorithm;
                        }

                        public InputStream getInputStream(InputStream encIn)
                        {
                            return new CipherInputStream(encIn, cipher);
                        }

                        final AlgorithmIdentifier val$algorithm;
                        final Cipher val$cipher;
                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        algorithm = algorithmidentifier;
                        cipher = cipher1;
                        super();
                    }
                    }
;
                }
                catch(IOException e)
                {
                    throw new OperatorCreationException((new StringBuilder()).append(algorithm.getAlgorithm()).append(" not available: ").append(e.getMessage()).toString(), e);
                }
                catch(GeneralSecurityException e)
                {
                    throw new OperatorCreationException((new StringBuilder()).append(algorithm.getAlgorithm()).append(" not available: ").append(e.getMessage()).toString(), e);
                }
            }

            final char val$password[];
            final JceOpenSSLPKCS8DecryptorProviderBuilder this$0;

            
            {
                this$0 = JceOpenSSLPKCS8DecryptorProviderBuilder.this;
                password = ac;
                super();
            }
        }
;
    }

    private JcaJceHelper helper;

}
