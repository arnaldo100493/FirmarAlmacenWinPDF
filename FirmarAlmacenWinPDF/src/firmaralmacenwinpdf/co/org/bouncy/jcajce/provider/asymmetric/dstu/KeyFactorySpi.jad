// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dstu;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.ua.UAObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
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

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dstu:
//            BCDSTU4145PrivateKey, BCDSTU4145PublicKey

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
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

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof co.org.bouncy.jce.spec.ECPrivateKeySpec)
            return new BCDSTU4145PrivateKey((co.org.bouncy.jce.spec.ECPrivateKeySpec)keySpec);
        if(keySpec instanceof ECPrivateKeySpec)
            return new BCDSTU4145PrivateKey((ECPrivateKeySpec)keySpec);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof co.org.bouncy.jce.spec.ECPublicKeySpec)
            return new BCDSTU4145PublicKey((co.org.bouncy.jce.spec.ECPublicKeySpec)keySpec);
        if(keySpec instanceof ECPublicKeySpec)
            return new BCDSTU4145PublicKey((ECPublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(algOid.equals(UAObjectIdentifiers.dstu4145le) || algOid.equals(UAObjectIdentifiers.dstu4145be))
            return new BCDSTU4145PrivateKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(algOid.equals(UAObjectIdentifiers.dstu4145le) || algOid.equals(UAObjectIdentifiers.dstu4145be))
            return new BCDSTU4145PublicKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }
}
