// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceOpenSSLPKCS8EncryptorBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            PEMUtilities

public class JceOpenSSLPKCS8EncryptorBuilder
{

    public JceOpenSSLPKCS8EncryptorBuilder(ASN1ObjectIdentifier algorithm)
    {
        helper = new DefaultJcaJceHelper();
        algOID = algorithm;
        iterationCount = 2048;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setPasssword(char password[])
    {
        this.password = password;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setIterationCount(int iterationCount)
    {
        this.iterationCount = iterationCount;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public OutputEncryptor build()
        throws OperatorCreationException
    {
        salt = new byte[20];
        if(random == null)
            random = new SecureRandom();
        random.nextBytes(salt);
        try
        {
            cipher = helper.createCipher(algOID.getId());
            if(PEMUtilities.isPKCS5Scheme2(algOID))
                paramGen = helper.createAlgorithmParameterGenerator(algOID.getId());
            else
                secKeyFact = helper.createSecretKeyFactory(algOID.getId());
        }
        catch(GeneralSecurityException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append(algOID).append(" not available: ").append(e.getMessage()).toString(), e);
        }
        final AlgorithmIdentifier algID;
        if(PEMUtilities.isPKCS5Scheme2(algOID))
        {
            params = paramGen.generateParameters();
            try
            {
                KeyDerivationFunc scheme = new KeyDerivationFunc(algOID, ASN1Primitive.fromByteArray(params.getEncoded()));
                KeyDerivationFunc func = new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(salt, iterationCount));
                ASN1EncodableVector v = new ASN1EncodableVector();
                v.add(func);
                v.add(scheme);
                algID = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, PBES2Parameters.getInstance(new DERSequence(v)));
            }
            catch(IOException e)
            {
                throw new OperatorCreationException(e.getMessage(), e);
            }
            key = PEMUtilities.generateSecretKeyForPKCS5Scheme2(algOID.getId(), password, salt, iterationCount);
            try
            {
                cipher.init(1, key, params);
            }
            catch(GeneralSecurityException e)
            {
                throw new OperatorCreationException(e.getMessage(), e);
            }
        } else
        if(PEMUtilities.isPKCS12(algOID))
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new DEROctetString(salt));
            v.add(new ASN1Integer(iterationCount));
            algID = new AlgorithmIdentifier(algOID, PKCS12PBEParams.getInstance(new DERSequence(v)));
            try
            {
                PBEKeySpec pbeSpec = new PBEKeySpec(password);
                PBEParameterSpec defParams = new PBEParameterSpec(salt, iterationCount);
                key = secKeyFact.generateSecret(pbeSpec);
                cipher.init(1, key, defParams);
            }
            catch(GeneralSecurityException e)
            {
                throw new OperatorCreationException(e.getMessage(), e);
            }
        } else
        {
            throw new OperatorCreationException((new StringBuilder()).append("unknown algorithm: ").append(algOID).toString(), null);
        }
        return new OutputEncryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return algID;
            }

            public OutputStream getOutputStream(OutputStream encOut)
            {
                return new CipherOutputStream(encOut, cipher);
            }

            public GenericKey getKey()
            {
                return new JceGenericKey(algID, key);
            }

            final AlgorithmIdentifier val$algID;
            final JceOpenSSLPKCS8EncryptorBuilder this$0;

            
            {
                this$0 = JceOpenSSLPKCS8EncryptorBuilder.this;
                algID = algorithmidentifier;
                super();
            }
        }
;
    }

    public static final String AES_128_CBC;
    public static final String AES_192_CBC;
    public static final String AES_256_CBC;
    public static final String DES3_CBC;
    public static final String PBE_SHA1_RC4_128;
    public static final String PBE_SHA1_RC4_40;
    public static final String PBE_SHA1_3DES;
    public static final String PBE_SHA1_2DES;
    public static final String PBE_SHA1_RC2_128;
    public static final String PBE_SHA1_RC2_40;
    private JcaJceHelper helper;
    private AlgorithmParameters params;
    private ASN1ObjectIdentifier algOID;
    byte salt[];
    int iterationCount;
    private Cipher cipher;
    private SecureRandom random;
    private AlgorithmParameterGenerator paramGen;
    private SecretKeyFactory secKeyFact;
    private char password[];
    private SecretKey key;

    static 
    {
        AES_128_CBC = NISTObjectIdentifiers.id_aes128_CBC.getId();
        AES_192_CBC = NISTObjectIdentifiers.id_aes192_CBC.getId();
        AES_256_CBC = NISTObjectIdentifiers.id_aes256_CBC.getId();
        DES3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC.getId();
        PBE_SHA1_RC4_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId();
        PBE_SHA1_RC4_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4.getId();
        PBE_SHA1_3DES = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC.getId();
        PBE_SHA1_2DES = PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC.getId();
        PBE_SHA1_RC2_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC.getId();
        PBE_SHA1_RC2_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC.getId();
    }


}
