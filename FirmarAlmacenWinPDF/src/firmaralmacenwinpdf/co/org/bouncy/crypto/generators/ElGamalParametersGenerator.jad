// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalParametersGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.params.ElGamalParameters;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            DHParametersHelper

public class ElGamalParametersGenerator
{

    public ElGamalParametersGenerator()
    {
    }

    public void init(int size, int certainty, SecureRandom random)
    {
        this.size = size;
        this.certainty = certainty;
        this.random = random;
    }

    public ElGamalParameters generateParameters()
    {
        java.math.BigInteger safePrimes[] = DHParametersHelper.generateSafePrimes(size, certainty, random);
        java.math.BigInteger p = safePrimes[0];
        java.math.BigInteger q = safePrimes[1];
        java.math.BigInteger g = DHParametersHelper.selectGenerator(p, q, random);
        return new ElGamalParameters(p, g);
    }

    private int size;
    private int certainty;
    private SecureRandom random;
}
