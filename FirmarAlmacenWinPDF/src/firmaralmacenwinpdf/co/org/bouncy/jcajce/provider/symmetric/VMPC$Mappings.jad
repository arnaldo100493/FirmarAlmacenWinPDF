// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VMPC.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            VMPC

public static class VMPC$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.VMPC", (new StringBuilder()).append(PREFIX).append("$Base").toString());
        provider.addAlgorithm("KeyGenerator.VMPC", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("Mac.VMPCMAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
        provider.addAlgorithm("Alg.Alias.Mac.VMPC", "VMPCMAC");
        provider.addAlgorithm("Alg.Alias.Mac.VMPC-MAC", "VMPCMAC");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/VMPC.getName();


    public VMPC$Mappings()
    {
    }
}
