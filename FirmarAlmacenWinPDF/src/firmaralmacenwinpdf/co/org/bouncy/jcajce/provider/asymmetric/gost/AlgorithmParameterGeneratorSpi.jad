// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParameterGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.gost;

import co.org.bouncy.crypto.generators.GOST3410ParametersGenerator;
import co.org.bouncy.crypto.params.GOST3410Parameters;
import co.org.bouncy.jce.spec.GOST3410ParameterSpec;
import co.org.bouncy.jce.spec.GOST3410PublicKeyParameterSetSpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public abstract class AlgorithmParameterGeneratorSpi extends java.security.AlgorithmParameterGeneratorSpi
{

    public AlgorithmParameterGeneratorSpi()
    {
        strength = 1024;
    }

    protected void engineInit(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
    }

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for GOST3410 parameter generation.");
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        GOST3410ParametersGenerator pGen = new GOST3410ParametersGenerator();
        if(random != null)
            pGen.init(strength, 2, random);
        else
            pGen.init(strength, 2, new SecureRandom());
        GOST3410Parameters p = pGen.generateParameters();
        AlgorithmParameters params;
        try
        {
            params = AlgorithmParameters.getInstance("GOST3410", "BC");
            params.init(new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(p.getP(), p.getQ(), p.getA())));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return params;
    }

    protected SecureRandom random;
    protected int strength;
}
