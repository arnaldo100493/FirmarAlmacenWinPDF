// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Whirlpool.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, Whirlpool

public static class Whirlpool$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.WHIRLPOOL", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        addHMACAlgorithm(provider, "WHIRLPOOL", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/Whirlpool.getName();


    public Whirlpool$Mappings()
    {
    }
}
