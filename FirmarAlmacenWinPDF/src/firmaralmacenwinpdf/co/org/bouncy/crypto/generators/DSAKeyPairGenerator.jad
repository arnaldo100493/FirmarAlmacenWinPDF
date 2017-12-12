// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAKeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSAKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public DSAKeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        this.param = (DSAKeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        DSAParameters dsaParams = param.getParameters();
        BigInteger x = generatePrivateKey(dsaParams.getQ(), param.getRandom());
        BigInteger y = calculatePublicKey(dsaParams.getP(), dsaParams.getG(), x);
        return new AsymmetricCipherKeyPair(new DSAPublicKeyParameters(y, dsaParams), new DSAPrivateKeyParameters(x, dsaParams));
    }

    private static BigInteger generatePrivateKey(BigInteger q, SecureRandom random)
    {
        return BigIntegers.createRandomInRange(ONE, q.subtract(ONE), random);
    }

    private static BigInteger calculatePublicKey(BigInteger p, BigInteger g, BigInteger x)
    {
        return g.modPow(x, p);
    }

    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DSAKeyGenerationParameters param;

}
