// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3410KeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class GOST3410KeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public GOST3410KeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        this.param = (GOST3410KeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        GOST3410Parameters GOST3410Params = param.getParameters();
        SecureRandom random = param.getRandom();
        BigInteger q = GOST3410Params.getQ();
        BigInteger p = GOST3410Params.getP();
        BigInteger a = GOST3410Params.getA();
        BigInteger x;
        do
            x = new BigInteger(256, random);
        while(x.equals(ZERO) || x.compareTo(q) >= 0);
        BigInteger y = a.modPow(x, p);
        return new AsymmetricCipherKeyPair(new GOST3410PublicKeyParameters(y, GOST3410Params), new GOST3410PrivateKeyParameters(x, GOST3410Params));
    }

    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private GOST3410KeyGenerationParameters param;

}
