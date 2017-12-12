// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAST6.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, CAST6

public static class CAST6$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.CAST6", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.CAST6", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        addGMacAlgorithm(provider, "CAST6", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/CAST6.getName();


    public CAST6$Mappings()
    {
    }
}
