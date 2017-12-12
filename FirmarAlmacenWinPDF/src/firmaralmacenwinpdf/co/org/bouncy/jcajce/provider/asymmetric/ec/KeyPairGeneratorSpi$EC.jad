// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTNamedCurves;
import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.teletrust.TeleTrusTNamedCurves;
import co.org.bouncy.asn1.x9.X962NamedCurves;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.ECKeyPairGenerator;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.crypto.params.ECKeyGenerationParameters;
import co.org.bouncy.crypto.params.ECPrivateKeyParameters;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECNamedCurveGenParameterSpec;
import co.org.bouncy.jce.spec.ECNamedCurveSpec;
import co.org.bouncy.jce.spec.ECParameterSpec;
import co.org.bouncy.util.Integers;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            KeyPairGeneratorSpi, BCECPublicKey, BCECPrivateKey

public static class KeyPairGeneratorSpi$EC extends KeyPairGeneratorSpi
{

    public void initialize(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
        ECGenParameterSpec ecParams = (ECGenParameterSpec)ecParameters.get(Integers.valueOf(strength));
        if(ecParams != null)
            try
            {
                initialize(((AlgorithmParameterSpec) (ecParams)), random);
            }
            catch(InvalidAlgorithmParameterException e)
            {
                throw new InvalidParameterException("key size not configurable.");
            }
        else
            throw new InvalidParameterException("unknown key size.");
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(params instanceof ECParameterSpec)
        {
            ECParameterSpec p = (ECParameterSpec)params;
            ecParams = params;
            param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);
            engine.init(param);
            initialised = true;
        } else
        if(params instanceof java.security.spec.ECParameterSpec)
        {
            java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)params;
            ecParams = params;
            co.org.bouncy.math.ec.ECCurve curve = EC5Util.convertCurve(p.getCurve());
            co.org.bouncy.math.ec.ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);
            param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);
            engine.init(param);
            initialised = true;
        } else
        if((params instanceof ECGenParameterSpec) || (params instanceof ECNamedCurveGenParameterSpec))
        {
            String curveName;
            if(params instanceof ECGenParameterSpec)
                curveName = ((ECGenParameterSpec)params).getName();
            else
                curveName = ((ECNamedCurveGenParameterSpec)params).getName();
            X9ECParameters ecP = X962NamedCurves.getByName(curveName);
            if(ecP == null)
            {
                ecP = SECNamedCurves.getByName(curveName);
                if(ecP == null)
                    ecP = NISTNamedCurves.getByName(curveName);
                if(ecP == null)
                    ecP = TeleTrusTNamedCurves.getByName(curveName);
                if(ecP == null)
                    try
                    {
                        ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier(curveName);
                        ecP = X962NamedCurves.getByOID(oid);
                        if(ecP == null)
                            ecP = SECNamedCurves.getByOID(oid);
                        if(ecP == null)
                            ecP = NISTNamedCurves.getByOID(oid);
                        if(ecP == null)
                            ecP = TeleTrusTNamedCurves.getByOID(oid);
                        if(ecP == null)
                            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("unknown curve OID: ").append(curveName).toString());
                    }
                    catch(IllegalArgumentException ex)
                    {
                        throw new InvalidAlgorithmParameterException((new StringBuilder()).append("unknown curve name: ").append(curveName).toString());
                    }
            }
            ecParams = new ECNamedCurveSpec(curveName, ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), null);
            java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;
            co.org.bouncy.math.ec.ECCurve curve = EC5Util.convertCurve(p.getCurve());
            co.org.bouncy.math.ec.ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);
            param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);
            engine.init(param);
            initialised = true;
        } else
        if(params == null && configuration.getEcImplicitlyCa() != null)
        {
            ECParameterSpec p = configuration.getEcImplicitlyCa();
            ecParams = params;
            param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);
            engine.init(param);
            initialised = true;
        } else
        if(params == null && configuration.getEcImplicitlyCa() == null)
            throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
        else
            throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
            initialize(strength, new SecureRandom());
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        ECPublicKeyParameters pub = (ECPublicKeyParameters)pair.getPublic();
        ECPrivateKeyParameters priv = (ECPrivateKeyParameters)pair.getPrivate();
        if(ecParams instanceof ECParameterSpec)
        {
            ECParameterSpec p = (ECParameterSpec)ecParams;
            BCECPublicKey pubKey = new BCECPublicKey(algorithm, pub, p, configuration);
            return new KeyPair(pubKey, new BCECPrivateKey(algorithm, priv, pubKey, p, configuration));
        }
        if(ecParams == null)
        {
            return new KeyPair(new BCECPublicKey(algorithm, pub, configuration), new BCECPrivateKey(algorithm, priv, configuration));
        } else
        {
            java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;
            BCECPublicKey pubKey = new BCECPublicKey(algorithm, pub, p, configuration);
            return new KeyPair(pubKey, new BCECPrivateKey(algorithm, priv, pubKey, p, configuration));
        }
    }

    ECKeyGenerationParameters param;
    ECKeyPairGenerator engine;
    Object ecParams;
    int strength;
    int certainty;
    SecureRandom random;
    boolean initialised;
    String algorithm;
    ProviderConfiguration configuration;
    private static Hashtable ecParameters;

    static 
    {
        ecParameters = new Hashtable();
        ecParameters.put(Integers.valueOf(192), new ECGenParameterSpec("prime192v1"));
        ecParameters.put(Integers.valueOf(239), new ECGenParameterSpec("prime239v1"));
        ecParameters.put(Integers.valueOf(256), new ECGenParameterSpec("prime256v1"));
        ecParameters.put(Integers.valueOf(224), new ECGenParameterSpec("P-224"));
        ecParameters.put(Integers.valueOf(384), new ECGenParameterSpec("P-384"));
        ecParameters.put(Integers.valueOf(521), new ECGenParameterSpec("P-521"));
    }

    public KeyPairGeneratorSpi$EC()
    {
        super("EC");
        engine = new ECKeyPairGenerator();
        ecParams = null;
        strength = 239;
        certainty = 50;
        random = new SecureRandom();
        initialised = false;
        algorithm = "EC";
        configuration = BouncyCastleProvider.CONFIGURATION;
    }

    public KeyPairGeneratorSpi$EC(String algorithm, ProviderConfiguration configuration)
    {
        super(algorithm);
        engine = new ECKeyPairGenerator();
        ecParams = null;
        strength = 239;
        certainty = 50;
        random = new SecureRandom();
        initialised = false;
        this.algorithm = algorithm;
        this.configuration = configuration;
    }
}
