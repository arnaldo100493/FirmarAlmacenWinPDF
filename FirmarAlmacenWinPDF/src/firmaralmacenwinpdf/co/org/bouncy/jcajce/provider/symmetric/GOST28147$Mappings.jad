// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST28147.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            GOST28147

public static class GOST28147$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.GOST28147", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("Alg.Alias.Cipher.GOST", "GOST28147");
        provider.addAlgorithm("Alg.Alias.Cipher.GOST-28147", "GOST28147");
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(CryptoProObjectIdentifiers.gostR28147_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm("KeyGenerator.GOST28147", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("Alg.Alias.KeyGenerator.GOST", "GOST28147");
        provider.addAlgorithm("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.").append(CryptoProObjectIdentifiers.gostR28147_cbc).toString(), "GOST28147");
        provider.addAlgorithm("Mac.GOST28147MAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
        provider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/GOST28147.getName();


    public GOST28147$Mappings()
    {
    }
}
