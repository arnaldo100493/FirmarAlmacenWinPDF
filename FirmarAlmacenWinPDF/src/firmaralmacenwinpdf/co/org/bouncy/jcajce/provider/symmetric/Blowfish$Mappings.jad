// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Blowfish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Blowfish

public static class Blowfish$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("Cipher.1.3.6.1.4.1.3029.1.2", (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm("KeyGenerator.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("Alg.Alias.KeyGenerator.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
        provider.addAlgorithm("AlgorithmParameters.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Blowfish.getName();


    public Blowfish$Mappings()
    {
    }
}
