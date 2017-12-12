// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC5.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            RC5

public static class RC5$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.RC5", (new StringBuilder()).append(PREFIX).append("$ECB32").toString());
        provider.addAlgorithm("Alg.Alias.Cipher.RC5-32", "RC5");
        provider.addAlgorithm("Cipher.RC5-64", (new StringBuilder()).append(PREFIX).append("$ECB64").toString());
        provider.addAlgorithm("KeyGenerator.RC5", (new StringBuilder()).append(PREFIX).append("$KeyGen32").toString());
        provider.addAlgorithm("Alg.Alias.KeyGenerator.RC5-32", "RC5");
        provider.addAlgorithm("KeyGenerator.RC5-64", (new StringBuilder()).append(PREFIX).append("$KeyGen64").toString());
        provider.addAlgorithm("AlgorithmParameters.RC5", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("AlgorithmParameters.RC5-64", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("Mac.RC5MAC", (new StringBuilder()).append(PREFIX).append("$Mac32").toString());
        provider.addAlgorithm("Alg.Alias.Mac.RC5", "RC5MAC");
        provider.addAlgorithm("Mac.RC5MAC/CFB8", (new StringBuilder()).append(PREFIX).append("$CFB8Mac32").toString());
        provider.addAlgorithm("Alg.Alias.Mac.RC5/CFB8", "RC5MAC/CFB8");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/RC5.getName();


    public RC5$Mappings()
    {
    }
}
