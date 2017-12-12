// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD320.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, RIPEMD320

public static class RIPEMD320$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.RIPEMD320", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        addHMACAlgorithm(provider, "RIPEMD320", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/RIPEMD320.getName();


    public RIPEMD320$Mappings()
    {
    }
}
