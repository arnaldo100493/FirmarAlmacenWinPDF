// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Skipjack.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            Skipjack

public static class Skipjack$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("AlgorithmParameters.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("Mac.SKIPJACKMAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
        provider.addAlgorithm("Alg.Alias.Mac.SKIPJACK", "SKIPJACKMAC");
        provider.addAlgorithm("Mac.SKIPJACKMAC/CFB8", (new StringBuilder()).append(PREFIX).append("$MacCFB8").toString());
        provider.addAlgorithm("Alg.Alias.Mac.SKIPJACK/CFB8", "SKIPJACKMAC/CFB8");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Skipjack.getName();


    public Skipjack$Mappings()
    {
    }
}
