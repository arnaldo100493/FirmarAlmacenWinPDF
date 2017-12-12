// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Noekeon.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, Noekeon

public static class Noekeon$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.NOEKEON", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("AlgorithmParameterGenerator.NOEKEON", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm("Cipher.NOEKEON", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.NOEKEON", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        addGMacAlgorithm(provider, "NOEKEON", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Noekeon.getName();


    public Noekeon$Mappings()
    {
    }
}
