// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import co.org.bouncy.jcajce.provider.asymmetric.util.ExtendedInvalidKeySpecException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            BCRSAPublicKey, BCRSAPrivateCrtKey, BCRSAPrivateKey, RSAUtil

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(java/security/spec/RSAPublicKeySpec) && (key instanceof RSAPublicKey))
        {
            RSAPublicKey k = (RSAPublicKey)key;
            return new RSAPublicKeySpec(k.getModulus(), k.getPublicExponent());
        }
        if(spec.isAssignableFrom(java/security/spec/RSAPrivateKeySpec) && (key instanceof RSAPrivateKey))
        {
            RSAPrivateKey k = (RSAPrivateKey)key;
            return new RSAPrivateKeySpec(k.getModulus(), k.getPrivateExponent());
        }
        if(spec.isAssignableFrom(java/security/spec/RSAPrivateCrtKeySpec) && (key instanceof RSAPrivateCrtKey))
        {
            RSAPrivateCrtKey k = (RSAPrivateCrtKey)key;
            return new RSAPrivateCrtKeySpec(k.getModulus(), k.getPublicExponent(), k.getPrivateExponent(), k.getPrimeP(), k.getPrimeQ(), k.getPrimeExponentP(), k.getPrimeExponentQ(), k.getCrtCoefficient());
        } else
        {
            return super.engineGetKeySpec(key, spec);
        }
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key instanceof RSAPublicKey)
            return new BCRSAPublicKey((RSAPublicKey)key);
        if(key instanceof RSAPrivateCrtKey)
            return new BCRSAPrivateCrtKey((RSAPrivateCrtKey)key);
        if(key instanceof RSAPrivateKey)
            return new BCRSAPrivateKey((RSAPrivateKey)key);
        else
            throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof PKCS8EncodedKeySpec)
            try
            {
                return generatePrivate(PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded()));
            }
            catch(Exception e)
            {
                try
                {
                    return new BCRSAPrivateCrtKey(co.org.bouncy.asn1.pkcs.RSAPrivateKey.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded()));
                }
                catch(Exception ex)
                {
                    throw new ExtendedInvalidKeySpecException((new StringBuilder()).append("unable to process key spec: ").append(e.toString()).toString(), e);
                }
            }
        if(keySpec instanceof RSAPrivateCrtKeySpec)
            return new BCRSAPrivateCrtKey((RSAPrivateCrtKeySpec)keySpec);
        if(keySpec instanceof RSAPrivateKeySpec)
            return new BCRSAPrivateKey((RSAPrivateKeySpec)keySpec);
        else
            throw new InvalidKeySpecException((new StringBuilder()).append("Unknown KeySpec type: ").append(keySpec.getClass().getName()).toString());
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof RSAPublicKeySpec)
            return new BCRSAPublicKey((RSAPublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(RSAUtil.isRsaOid(algOid))
            return new BCRSAPrivateCrtKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(RSAUtil.isRsaOid(algOid))
            return new BCRSAPublicKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }
}
