// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SipHash.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SipHash

public static class SipHash$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Mac.SIPHASH", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
        provider.addAlgorithm("Alg.Alias.Mac.SIPHASH-2-4", "SIPHASH");
        provider.addAlgorithm("Mac.SIPHASH-4-8", (new StringBuilder()).append(PREFIX).append("$Mac48").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/SipHash.getName();


    public SipHash$Mappings()
    {
    }
}
