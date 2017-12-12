// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultJcaJceHelper.java

package co.org.bouncy.jcajce;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.jcajce:
//            JcaJceHelper

public class DefaultJcaJceHelper
    implements JcaJceHelper
{

    public DefaultJcaJceHelper()
    {
    }

    public Cipher createCipher(String algorithm)
        throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        return Cipher.getInstance(algorithm);
    }

    public Mac createMac(String algorithm)
        throws NoSuchAlgorithmException
    {
        return Mac.getInstance(algorithm);
    }

    public KeyAgreement createKeyAgreement(String algorithm)
        throws NoSuchAlgorithmException
    {
        return KeyAgreement.getInstance(algorithm);
    }

    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String algorithm)
        throws NoSuchAlgorithmException
    {
        return AlgorithmParameterGenerator.getInstance(algorithm);
    }

    public AlgorithmParameters createAlgorithmParameters(String algorithm)
        throws NoSuchAlgorithmException
    {
        return AlgorithmParameters.getInstance(algorithm);
    }

    public KeyGenerator createKeyGenerator(String algorithm)
        throws NoSuchAlgorithmException
    {
        return KeyGenerator.getInstance(algorithm);
    }

    public KeyFactory createKeyFactory(String algorithm)
        throws NoSuchAlgorithmException
    {
        return KeyFactory.getInstance(algorithm);
    }

    public SecretKeyFactory createSecretKeyFactory(String algorithm)
        throws NoSuchAlgorithmException
    {
        return SecretKeyFactory.getInstance(algorithm);
    }

    public KeyPairGenerator createKeyPairGenerator(String algorithm)
        throws NoSuchAlgorithmException
    {
        return KeyPairGenerator.getInstance(algorithm);
    }

    public MessageDigest createDigest(String algorithm)
        throws NoSuchAlgorithmException
    {
        return MessageDigest.getInstance(algorithm);
    }

    public Signature createSignature(String algorithm)
        throws NoSuchAlgorithmException
    {
        return Signature.getInstance(algorithm);
    }

    public CertificateFactory createCertificateFactory(String algorithm)
        throws NoSuchAlgorithmException, CertificateException
    {
        return CertificateFactory.getInstance(algorithm);
    }
}
