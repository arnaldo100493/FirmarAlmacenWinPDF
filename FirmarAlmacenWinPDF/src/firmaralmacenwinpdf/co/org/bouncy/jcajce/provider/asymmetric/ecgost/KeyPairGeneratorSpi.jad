// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ecgost;

import co.org.bouncy.asn1.cryptopro.ECGOST3410NamedCurves;
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
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ecgost:
//            BCECGOST3410PublicKey, BCECGOST3410PrivateKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi()
    {
        super("ECGOST3410");
        ecParams = null;
        engine = new ECKeyPairGenerator();
        algorithm = "ECGOST3410";
        strength = 239;
        random = null;
        initialised = false;
    }

    public void initialize(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
        if(ecParams != null)
            try
            {
                initialize(((AlgorithmParameterSpec) ((ECGenParameterSpec)ecParams)), random);
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
            ECDomainParameters ecP = ECGOST3410NamedCurves.getByName(curveName);
            if(ecP == null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("unknown curve name: ").append(curveName).toString());
            ecParams = new ECNamedCurveSpec(curveName, ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
            java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;
            co.org.bouncy.math.ec.ECCurve curve = EC5Util.convertCurve(p.getCurve());
            co.org.bouncy.math.ec.ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);
            param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);
            engine.init(param);
            initialised = true;
        } else
        if(params == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() != null)
        {
            ECParameterSpec p = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            ecParams = params;
            param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);
            engine.init(param);
            initialised = true;
        } else
        if(params == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() == null)
            throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
        else
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("parameter object not a ECParameterSpec: ").append(params.getClass().getName()).toString());
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
            throw new IllegalStateException("EC Key Pair Generator not initialised");
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        ECPublicKeyParameters pub = (ECPublicKeyParameters)pair.getPublic();
        ECPrivateKeyParameters priv = (ECPrivateKeyParameters)pair.getPrivate();
        if(ecParams instanceof ECParameterSpec)
        {
            ECParameterSpec p = (ECParameterSpec)ecParams;
            BCECGOST3410PublicKey pubKey = new BCECGOST3410PublicKey(algorithm, pub, p);
            return new KeyPair(pubKey, new BCECGOST3410PrivateKey(algorithm, priv, pubKey, p));
        }
        if(ecParams == null)
        {
            return new KeyPair(new BCECGOST3410PublicKey(algorithm, pub), new BCECGOST3410PrivateKey(algorithm, priv));
        } else
        {
            java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;
            BCECGOST3410PublicKey pubKey = new BCECGOST3410PublicKey(algorithm, pub, p);
            return new KeyPair(pubKey, new BCECGOST3410PrivateKey(algorithm, priv, pubKey, p));
        }
    }

    Object ecParams;
    ECKeyPairGenerator engine;
    String algorithm;
    ECKeyGenerationParameters param;
    int strength;
    SecureRandom random;
    boolean initialised;
}
