// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseAlgorithmParameterGenerator.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameterGeneratorSpi;
import java.security.SecureRandom;

public abstract class BaseAlgorithmParameterGenerator extends AlgorithmParameterGeneratorSpi
{

    public BaseAlgorithmParameterGenerator()
    {
        strength = 1024;
    }

    protected void engineInit(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
    }

    protected SecureRandom random;
    protected int strength;
}
