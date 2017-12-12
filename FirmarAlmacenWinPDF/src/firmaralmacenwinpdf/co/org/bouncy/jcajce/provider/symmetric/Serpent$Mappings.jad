// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serpent.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, Serpent

public static class Serpent$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.Serpent", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.Serpent", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("AlgorithmParameters.Serpent", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        addGMacAlgorithm(provider, "SERPENT", (new StringBuilder()).append(PREFIX).append("$SerpentGMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Serpent.getName();


    public Serpent$Mappings()
    {
    }
}
