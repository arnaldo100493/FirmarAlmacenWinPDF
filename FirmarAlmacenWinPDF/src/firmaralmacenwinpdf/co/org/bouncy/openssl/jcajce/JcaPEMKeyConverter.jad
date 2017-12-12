// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPEMKeyConverter.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.openssl.PEMException;
import co.org.bouncy.openssl.PEMKeyPair;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class JcaPEMKeyConverter
{

    public JcaPEMKeyConverter()
    {
        helper = new DefaultJcaJceHelper();
    }

    public JcaPEMKeyConverter setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcaPEMKeyConverter setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public KeyPair getKeyPair(PEMKeyPair keyPair)
        throws PEMException
    {
        try
        {
            String algorithm = keyPair.getPrivateKeyInfo().getPrivateKeyAlgorithm().getAlgorithm().getId();
            if(X9ObjectIdentifiers.id_ecPublicKey.getId().equals(algorithm))
                algorithm = "ECDSA";
            KeyFactory keyFactory = helper.createKeyFactory(algorithm);
            return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(keyPair.getPublicKeyInfo().getEncoded())), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyPair.getPrivateKeyInfo().getEncoded())));
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("unable to convert key pair: ").append(e.getMessage()).toString(), e);
        }
    }

    public PublicKey getPublicKey(SubjectPublicKeyInfo publicKeyInfo)
        throws PEMException
    {
        try
        {
            String algorithm = publicKeyInfo.getAlgorithm().getAlgorithm().getId();
            if(X9ObjectIdentifiers.id_ecPublicKey.getId().equals(algorithm))
                algorithm = "ECDSA";
            KeyFactory keyFactory = helper.createKeyFactory(algorithm);
            return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyInfo.getEncoded()));
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("unable to convert key pair: ").append(e.getMessage()).toString(), e);
        }
    }

    public PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo)
        throws PEMException
    {
        try
        {
            String algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm().getId();
            if(X9ObjectIdentifiers.id_ecPublicKey.getId().equals(algorithm))
                algorithm = "ECDSA";
            KeyFactory keyFactory = helper.createKeyFactory(algorithm);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded()));
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("unable to convert key pair: ").append(e.getMessage()).toString(), e);
        }
    }

    private JcaJceHelper helper;
}
