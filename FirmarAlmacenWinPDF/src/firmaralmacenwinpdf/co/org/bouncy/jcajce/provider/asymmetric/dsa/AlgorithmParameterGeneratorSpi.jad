// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParameterGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.generators.DSAParametersGenerator;
import co.org.bouncy.crypto.params.DSAParameterGenerationParameters;
import co.org.bouncy.crypto.params.DSAParameters;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;

public class AlgorithmParameterGeneratorSpi extends java.security.AlgorithmParameterGeneratorSpi
{

    public AlgorithmParameterGeneratorSpi()
    {
        strength = 1024;
    }

    protected void engineInit(int strength, SecureRandom random)
    {
        if(strength < 512 || strength > 3072)
            throw new InvalidParameterException("strength must be from 512 - 3072");
        if(strength <= 1024 && strength % 64 != 0)
            throw new InvalidParameterException("strength must be a multiple of 64 below 1024 bits.");
        if(strength > 1024 && strength % 1024 != 0)
        {
            throw new InvalidParameterException("strength must be a multiple of 1024 above 1024 bits.");
        } else
        {
            this.strength = strength;
            this.random = random;
            return;
        }
    }

    protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSA parameter generation.");
    }

    protected AlgorithmParameters engineGenerateParameters()
    {
        DSAParametersGenerator pGen;
        if(strength <= 1024)
            pGen = new DSAParametersGenerator();
        else
            pGen = new DSAParametersGenerator(new SHA256Digest());
        if(random == null)
            random = new SecureRandom();
        if(strength == 1024)
        {
            this.params = new DSAParameterGenerationParameters(1024, 160, 80, random);
            pGen.init(this.params);
        } else
        if(strength > 1024)
        {
            this.params = new DSAParameterGenerationParameters(strength, 256, 80, random);
            pGen.init(this.params);
        } else
        {
            pGen.init(strength, 20, random);
        }
        DSAParameters p = pGen.generateParameters();
        AlgorithmParameters params;
        try
        {
            params = AlgorithmParameters.getInstance("DSA", "BC");
            params.init(new DSAParameterSpec(p.getP(), p.getQ(), p.getG()));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        return params;
    }

    protected SecureRandom random;
    protected int strength;
    protected DSAParameterGenerationParameters params;
}
