// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.RSAKeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            BCRSAPublicKey, BCRSAPrivateCrtKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi(String algorithmName)
    {
        super(algorithmName);
    }

    public KeyPairGeneratorSpi()
    {
        super("RSA");
        engine = new RSAKeyPairGenerator();
        param = new RSAKeyGenerationParameters(defaultPublicExponent, new SecureRandom(), 2048, 12);
        engine.init(param);
    }

    public void initialize(int strength, SecureRandom random)
    {
        param = new RSAKeyGenerationParameters(defaultPublicExponent, random, strength, 12);
        engine.init(param);
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(!(params instanceof RSAKeyGenParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("parameter object not a RSAKeyGenParameterSpec");
        } else
        {
            RSAKeyGenParameterSpec rsaParams = (RSAKeyGenParameterSpec)params;
            param = new RSAKeyGenerationParameters(rsaParams.getPublicExponent(), random, rsaParams.getKeysize(), 12);
            engine.init(param);
            return;
        }
    }

    public KeyPair generateKeyPair()
    {
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        RSAKeyParameters pub = (RSAKeyParameters)pair.getPublic();
        RSAPrivateCrtKeyParameters priv = (RSAPrivateCrtKeyParameters)pair.getPrivate();
        return new KeyPair(new BCRSAPublicKey(pub), new BCRSAPrivateCrtKey(priv));
    }

    static final BigInteger defaultPublicExponent = BigInteger.valueOf(0x10001L);
    static final int defaultTests = 12;
    RSAKeyGenerationParameters param;
    RSAKeyPairGenerator engine;

}
