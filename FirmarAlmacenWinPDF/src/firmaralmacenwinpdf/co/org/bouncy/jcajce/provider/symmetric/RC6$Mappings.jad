// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC6.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, RC6

public static class RC6$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.RC6", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.RC6", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("AlgorithmParameters.RC6", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        addGMacAlgorithm(provider, "RC6", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/RC6.getName();


    public RC6$Mappings()
    {
    }
}
