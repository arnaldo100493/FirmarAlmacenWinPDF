// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS8Generator.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.openssl.jcajce.JceOpenSSLPKCS8EncryptorBuilder;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.util.io.pem.*;
import java.io.*;
import java.security.*;

public class PKCS8Generator
    implements PemObjectGenerator
{

    /**
     * @deprecated Method PKCS8Generator is deprecated
     */

    public PKCS8Generator(PrivateKey key)
    {
        this.key = PrivateKeyInfo.getInstance(key.getEncoded());
    }

    /**
     * @deprecated Method PKCS8Generator is deprecated
     */

    public PKCS8Generator(PrivateKey key, ASN1ObjectIdentifier algorithm, String provider)
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        Provider prov = Security.getProvider(provider);
        if(prov == null)
        {
            throw new NoSuchProviderException((new StringBuilder()).append("cannot find provider: ").append(provider).toString());
        } else
        {
            init(key, algorithm, prov);
            return;
        }
    }

    /**
     * @deprecated Method PKCS8Generator is deprecated
     */

    public PKCS8Generator(PrivateKey key, ASN1ObjectIdentifier algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        init(key, algorithm, provider);
    }

    public PKCS8Generator(PrivateKeyInfo key, OutputEncryptor outputEncryptor)
    {
        this.key = key;
        this.outputEncryptor = outputEncryptor;
    }

    private void init(PrivateKey key, ASN1ObjectIdentifier algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        this.key = PrivateKeyInfo.getInstance(key.getEncoded());
        encryptorBuilder = new JceOpenSSLPKCS8EncryptorBuilder(algorithm);
        encryptorBuilder.setProvider(provider);
    }

    /**
     * @deprecated Method setSecureRandom is deprecated
     */

    public PKCS8Generator setSecureRandom(SecureRandom random)
    {
        encryptorBuilder.setRandom(random);
        return this;
    }

    /**
     * @deprecated Method setPassword is deprecated
     */

    public PKCS8Generator setPassword(char password[])
    {
        encryptorBuilder.setPasssword(password);
        return this;
    }

    /**
     * @deprecated Method setIterationCount is deprecated
     */

    public PKCS8Generator setIterationCount(int iterationCount)
    {
        encryptorBuilder.setIterationCount(iterationCount);
        return this;
    }

    public PemObject generate()
        throws PemGenerationException
    {
        try
        {
            if(encryptorBuilder != null)
                outputEncryptor = encryptorBuilder.build();
        }
        catch(OperatorCreationException e)
        {
            throw new PemGenerationException((new StringBuilder()).append("unable to create operator: ").append(e.getMessage()).toString(), e);
        }
        if(outputEncryptor != null)
            return generate(key, outputEncryptor);
        else
            return generate(key, null);
    }

    private PemObject generate(PrivateKeyInfo key, OutputEncryptor encryptor)
        throws PemGenerationException
    {
        ByteArrayOutputStream bOut;
        OutputStream cOut;
        EncryptedPrivateKeyInfo info;
        try
        {
            byte keyData[] = key.getEncoded();
            if(encryptor == null)
                return new PemObject("PRIVATE KEY", keyData);
        }
        catch(IOException e)
        {
            throw new PemGenerationException((new StringBuilder()).append("unable to process encoded key data: ").append(e.getMessage()).toString(), e);
        }
        bOut = new ByteArrayOutputStream();
        cOut = encryptor.getOutputStream(bOut);
        cOut.write(key.getEncoded());
        cOut.close();
        info = new EncryptedPrivateKeyInfo(encryptor.getAlgorithmIdentifier(), bOut.toByteArray());
        return new PemObject("ENCRYPTED PRIVATE KEY", info.getEncoded());
    }

    public static final ASN1ObjectIdentifier AES_128_CBC;
    public static final ASN1ObjectIdentifier AES_192_CBC;
    public static final ASN1ObjectIdentifier AES_256_CBC;
    public static final ASN1ObjectIdentifier DES3_CBC;
    public static final ASN1ObjectIdentifier PBE_SHA1_RC4_128;
    public static final ASN1ObjectIdentifier PBE_SHA1_RC4_40;
    public static final ASN1ObjectIdentifier PBE_SHA1_3DES;
    public static final ASN1ObjectIdentifier PBE_SHA1_2DES;
    public static final ASN1ObjectIdentifier PBE_SHA1_RC2_128;
    public static final ASN1ObjectIdentifier PBE_SHA1_RC2_40;
    private PrivateKeyInfo key;
    private OutputEncryptor outputEncryptor;
    private JceOpenSSLPKCS8EncryptorBuilder encryptorBuilder;

    static 
    {
        AES_128_CBC = NISTObjectIdentifiers.id_aes128_CBC;
        AES_192_CBC = NISTObjectIdentifiers.id_aes192_CBC;
        AES_256_CBC = NISTObjectIdentifiers.id_aes256_CBC;
        DES3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC;
        PBE_SHA1_RC4_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4;
        PBE_SHA1_RC4_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4;
        PBE_SHA1_3DES = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC;
        PBE_SHA1_2DES = PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC;
        PBE_SHA1_RC2_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC;
        PBE_SHA1_RC2_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC;
    }
}
