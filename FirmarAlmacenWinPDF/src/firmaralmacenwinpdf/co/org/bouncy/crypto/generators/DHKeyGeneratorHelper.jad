// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKeyGeneratorHelper.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.params.DHParameters;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

class DHKeyGeneratorHelper
{

    private DHKeyGeneratorHelper()
    {
    }

    BigInteger calculatePrivate(DHParameters dhParams, SecureRandom random)
    {
        BigInteger p = dhParams.getP();
        int limit = dhParams.getL();
        if(limit != 0)
            return (new BigInteger(limit, random)).setBit(limit - 1);
        BigInteger min = TWO;
        int m = dhParams.getM();
        if(m != 0)
            min = ONE.shiftLeft(m - 1);
        BigInteger max = p.subtract(TWO);
        BigInteger q = dhParams.getQ();
        if(q != null)
            max = q.subtract(TWO);
        return BigIntegers.createRandomInRange(min, max, random);
    }

    BigInteger calculatePublic(DHParameters dhParams, BigInteger x)
    {
        return dhParams.getG().modPow(x, dhParams.getP());
    }

    static final DHKeyGeneratorHelper INSTANCE = new DHKeyGeneratorHelper();
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);

}
