// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCSPBEOutputEncryptorBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.bc.BCObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class JcePKCSPBEOutputEncryptorBuilder
{

    public JcePKCSPBEOutputEncryptorBuilder(ASN1ObjectIdentifier algorithm)
    {
        helper = new DefaultJcaJceHelper();
        keySizeProvider = DefaultSecretKeyProvider.INSTANCE;
        if(isPKCS12(algorithm))
        {
            this.algorithm = algorithm;
            keyEncAlgorithm = algorithm;
        } else
        {
            this.algorithm = PKCSObjectIdentifiers.id_PBES2;
            keyEncAlgorithm = algorithm;
        }
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setKeySizeProvider(SecretKeySizeProvider keySizeProvider)
    {
        this.keySizeProvider = keySizeProvider;
        return this;
    }

    public OutputEncryptor build(final char password[])
        throws OperatorCreationException
    {
        if(random == null)
            random = new SecureRandom();
        byte salt[] = new byte[20];
        int iterationCount = 1024;
        random.nextBytes(salt);
        try
        {
            final Cipher cipher;
            final AlgorithmIdentifier encryptionAlg;
            if(algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds))
            {
                PBEKeySpec pbeSpec = new PBEKeySpec(password);
                SecretKeyFactory keyFact = helper.createSecretKeyFactory(algorithm.getId());
                PBEParameterSpec defParams = new PBEParameterSpec(salt, 1024);
                SecretKey key = keyFact.generateSecret(pbeSpec);
                cipher = helper.createCipher(algorithm.getId());
                cipher.init(1, key, defParams);
                encryptionAlg = new AlgorithmIdentifier(algorithm, new PKCS12PBEParams(salt, 1024));
            } else
            if(algorithm.equals(PKCSObjectIdentifiers.id_PBES2))
            {
                SecretKeyFactory keyFact = helper.createSecretKeyFactory(PKCSObjectIdentifiers.id_PBKDF2.getId());
                SecretKey key = keyFact.generateSecret(new PBEKeySpec(password, salt, 1024, keySizeProvider.getKeySize(new AlgorithmIdentifier(keyEncAlgorithm))));
                cipher = helper.createCipher(keyEncAlgorithm.getId());
                cipher.init(1, key, random);
                PBES2Parameters algParams = new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(salt, 1024)), new EncryptionScheme(keyEncAlgorithm, ASN1Primitive.fromByteArray(cipher.getParameters().getEncoded())));
                encryptionAlg = new AlgorithmIdentifier(algorithm, algParams);
            } else
            {
                throw new OperatorCreationException("unrecognised algorithm");
            }
            return new OutputEncryptor() {

                public AlgorithmIdentifier getAlgorithmIdentifier()
                {
                    return encryptionAlg;
                }

                public OutputStream getOutputStream(OutputStream out)
                {
                    return new CipherOutputStream(out, cipher);
                }

                public GenericKey getKey()
                {
                    if(isPKCS12(encryptionAlg.getAlgorithm()))
                        return new GenericKey(encryptionAlg, PBEParametersGenerator.PKCS5PasswordToBytes(password));
                    else
                        return new GenericKey(encryptionAlg, PBEParametersGenerator.PKCS12PasswordToBytes(password));
                }

                final AlgorithmIdentifier val$encryptionAlg;
                final Cipher val$cipher;
                final char val$password[];
                final JcePKCSPBEOutputEncryptorBuilder this$0;

            
            {
                this$0 = JcePKCSPBEOutputEncryptorBuilder.this;
                encryptionAlg = algorithmidentifier;
                cipher = cipher1;
                password = ac;
                super();
            }
            }
;
        }
        catch(Exception e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to create OutputEncryptor: ").append(e.getMessage()).toString(), e);
        }
    }

    private boolean isPKCS12(ASN1ObjectIdentifier algorithm)
    {
        return algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds) || algorithm.on(BCObjectIdentifiers.bc_pbe_sha1_pkcs12) || algorithm.on(BCObjectIdentifiers.bc_pbe_sha256_pkcs12);
    }

    private JcaJceHelper helper;
    private ASN1ObjectIdentifier algorithm;
    private ASN1ObjectIdentifier keyEncAlgorithm;
    private SecureRandom random;
    private SecretKeySizeProvider keySizeProvider;

}
