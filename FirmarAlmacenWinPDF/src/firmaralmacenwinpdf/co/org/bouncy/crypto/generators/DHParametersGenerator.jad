// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHParametersGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.params.DHParameters;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            DHParametersHelper

public class DHParametersGenerator
{

    public DHParametersGenerator()
    {
    }

    public void init(int size, int certainty, SecureRandom random)
    {
        this.size = size;
        this.certainty = certainty;
        this.random = random;
    }

    public DHParameters generateParameters()
    {
        BigInteger safePrimes[] = DHParametersHelper.generateSafePrimes(size, certainty, random);
        BigInteger p = safePrimes[0];
        BigInteger q = safePrimes[1];
        BigInteger g = DHParametersHelper.selectGenerator(p, q, random);
        return new DHParameters(p, g, q, TWO, null);
    }

    private int size;
    private int certainty;
    private SecureRandom random;
    private static final BigInteger TWO = BigInteger.valueOf(2L);

}
