// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSABlindingFactorGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSABlindingFactorGenerator
{

    public RSABlindingFactorGenerator()
    {
    }

    public void init(CipherParameters param)
    {
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom rParam = (ParametersWithRandom)param;
            key = (RSAKeyParameters)rParam.getParameters();
            random = rParam.getRandom();
        } else
        {
            key = (RSAKeyParameters)param;
            random = new SecureRandom();
        }
        if(key instanceof RSAPrivateCrtKeyParameters)
            throw new IllegalArgumentException("generator requires RSA public key");
        else
            return;
    }

    public BigInteger generateBlindingFactor()
    {
        if(key == null)
            throw new IllegalStateException("generator not initialised");
        BigInteger m = key.getModulus();
        int length = m.bitLength() - 1;
        BigInteger factor;
        BigInteger gcd;
        do
        {
            factor = new BigInteger(length, random);
            gcd = factor.gcd(m);
        } while(factor.equals(ZERO) || factor.equals(ONE) || !gcd.equals(ONE));
        return factor;
    }

    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);
    private RSAKeyParameters key;
    private SecureRandom random;

}
