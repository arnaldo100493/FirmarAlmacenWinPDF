// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParameterGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.crypto.generators.ElGamalParametersGenerator;
import co.org.bouncy.crypto.params.ElGamalParameters;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHGenParameterSpec;
import javax.crypto.spec.DHParameterSpec;

public class AlgorithmParameterGeneratorSpi extends java.security.AlgorithmParameterGeneratorSpi
{

    public AlgorithmParameterGeneratorSpi()
    {
        strength = 1024;
        l = 0;
    }

    protected void engineInit(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
    }

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(!(genParamSpec instanceof DHGenParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("DH parameter generator requires a DHGenParameterSpec for initialisation");
        } else
        {
            DHGenParameterSpec spec = (DHGenParameterSpec)genParamSpec;
            strength = spec.getPrimeSize();
            l = spec.getExponentSize();
            this.random = random;
            return;
        }
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        ElGamalParametersGenerator pGen = new ElGamalParametersGenerator();
        if(random != null)
            pGen.init(strength, 20, random);
        else
            pGen.init(strength, 20, new SecureRandom());
        ElGamalParameters p = pGen.generateParameters();
        AlgorithmParameters params;
        try
        {
            params = AlgorithmParameters.getInstance("ElGamal", "BC");
            params.init(new DHParameterSpec(p.getP(), p.getG(), l));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return params;
    }

    protected SecureRandom random;
    protected int strength;
    private int l;
}
