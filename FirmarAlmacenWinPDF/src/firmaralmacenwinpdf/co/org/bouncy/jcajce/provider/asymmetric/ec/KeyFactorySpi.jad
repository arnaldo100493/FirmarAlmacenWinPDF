// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECParameterSpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            BCECPublicKey, BCECPrivateKey

public class KeyFactorySpi extends BaseKeyFactorySpi
    implements AsymmetricKeyInfoConverter
{
    public static class ECMQV extends KeyFactorySpi
    {

        public ECMQV()
        {
            super("ECMQV", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDHC extends KeyFactorySpi
    {

        public ECDHC()
        {
            super("ECDHC", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDH extends KeyFactorySpi
    {

        public ECDH()
        {
            super("ECDH", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECGOST3410 extends KeyFactorySpi
    {

        public ECGOST3410()
        {
            super("ECGOST3410", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class ECDSA extends KeyFactorySpi
    {

        public ECDSA()
        {
            super("ECDSA", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public static class EC extends KeyFactorySpi
    {

        public EC()
        {
            super("EC", BouncyCastleProvider.CONFIGURATION);
        }
    }


    KeyFactorySpi(String algorithm, ProviderConfiguration configuration)
    {
        this.algorithm = algorithm;
        this.configuration = configuration;
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key instanceof ECPublicKey)
            return new BCECPublicKey((ECPublicKey)key, configuration);
        if(key instanceof ECPrivateKey)
            return new BCECPrivateKey((ECPrivateKey)key, configuration);
        else
            throw new InvalidKeyException("key type unknown");
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(java/security/spec/ECPublicKeySpec) && (key instanceof ECPublicKey))
        {
            ECPublicKey k = (ECPublicKey)key;
            if(k.getParams() != null)
            {
                return new ECPublicKeySpec(k.getW(), k.getParams());
            } else
            {
                ECParameterSpec implicitSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
                return new ECPublicKeySpec(k.getW(), EC5Util.convertSpec(EC5Util.convertCurve(implicitSpec.getCurve(), implicitSpec.getSeed()), implicitSpec));
            }
        }
        if(spec.isAssignableFrom(java/security/spec/ECPrivateKeySpec) && (key instanceof ECPrivateKey))
        {
            ECPrivateKey k = (ECPrivateKey)key;
            if(k.getParams() != null)
            {
                return new ECPrivateKeySpec(k.getS(), k.getParams());
            } else
            {
                ECParameterSpec implicitSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
                return new ECPrivateKeySpec(k.getS(), EC5Util.convertSpec(EC5Util.convertCurve(implicitSpec.getCurve(), implicitSpec.getSeed()), implicitSpec));
            }
        }
        if(spec.isAssignableFrom(co/org/bouncy/jce/spec/ECPublicKeySpec) && (key instanceof ECPublicKey))
        {
            ECPublicKey k = (ECPublicKey)key;
            if(k.getParams() != null)
            {
                return new co.org.bouncy.jce.spec.ECPublicKeySpec(EC5Util.convertPoint(k.getParams(), k.getW(), false), EC5Util.convertSpec(k.getParams(), false));
            } else
            {
                ECParameterSpec implicitSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
                return new co.org.bouncy.jce.spec.ECPublicKeySpec(EC5Util.convertPoint(k.getParams(), k.getW(), false), implicitSpec);
            }
        }
        if(spec.isAssignableFrom(co/org/bouncy/jce/spec/ECPrivateKeySpec) && (key instanceof ECPrivateKey))
        {
            ECPrivateKey k = (ECPrivateKey)key;
            if(k.getParams() != null)
            {
                return new co.org.bouncy.jce.spec.ECPrivateKeySpec(k.getS(), EC5Util.convertSpec(k.getParams(), false));
            } else
            {
                ECParameterSpec implicitSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
                return new co.org.bouncy.jce.spec.ECPrivateKeySpec(k.getS(), implicitSpec);
            }
        } else
        {
            return super.engineGetKeySpec(key, spec);
        }
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof co.org.bouncy.jce.spec.ECPrivateKeySpec)
            return new BCECPrivateKey(algorithm, (co.org.bouncy.jce.spec.ECPrivateKeySpec)keySpec, configuration);
        if(keySpec instanceof ECPrivateKeySpec)
            return new BCECPrivateKey(algorithm, (ECPrivateKeySpec)keySpec, configuration);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof co.org.bouncy.jce.spec.ECPublicKeySpec)
            return new BCECPublicKey(algorithm, (co.org.bouncy.jce.spec.ECPublicKeySpec)keySpec, configuration);
        if(keySpec instanceof ECPublicKeySpec)
            return new BCECPublicKey(algorithm, (ECPublicKeySpec)keySpec, configuration);
        else
            return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(algOid.equals(X9ObjectIdentifiers.id_ecPublicKey))
            return new BCECPrivateKey(algorithm, keyInfo, configuration);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(algOid.equals(X9ObjectIdentifiers.id_ecPublicKey))
            return new BCECPublicKey(algorithm, keyInfo, configuration);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    String algorithm;
    ProviderConfiguration configuration;
}
