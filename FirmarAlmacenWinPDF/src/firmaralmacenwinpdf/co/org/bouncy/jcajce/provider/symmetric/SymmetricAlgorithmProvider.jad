// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SymmetricAlgorithmProvider.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

abstract class SymmetricAlgorithmProvider extends AlgorithmProvider
{

    SymmetricAlgorithmProvider()
    {
    }

    protected void addGMacAlgorithm(ConfigurableProvider provider, String algorithm, String algorithmClassName, String keyGeneratorClassName)
    {
        provider.addAlgorithm((new StringBuilder()).append("Mac.").append(algorithm).append("-GMAC").toString(), algorithmClassName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.").append(algorithm).append("GMAC").toString(), (new StringBuilder()).append(algorithm).append("-GMAC").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(algorithm).append("-GMAC").toString(), keyGeneratorClassName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.").append(algorithm).append("GMAC").toString(), (new StringBuilder()).append(algorithm).append("-GMAC").toString());
    }
}
