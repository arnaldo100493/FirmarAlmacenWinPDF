// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA3.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, SHA3

public static class SHA3$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.SHA3-224", (new StringBuilder()).append(PREFIX).append("$Digest224").toString());
        provider.addAlgorithm("MessageDigest.SHA3-256", (new StringBuilder()).append(PREFIX).append("$Digest256").toString());
        provider.addAlgorithm("MessageDigest.SHA3-384", (new StringBuilder()).append(PREFIX).append("$Digest384").toString());
        provider.addAlgorithm("MessageDigest.SHA3-512", (new StringBuilder()).append(PREFIX).append("$Digest512").toString());
        addHMACAlgorithm(provider, "SHA3-224", (new StringBuilder()).append(PREFIX).append("$HashMac224").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator224").toString());
        addHMACAlgorithm(provider, "SHA3-256", (new StringBuilder()).append(PREFIX).append("$HashMac256").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator256").toString());
        addHMACAlgorithm(provider, "SHA3-384", (new StringBuilder()).append(PREFIX).append("$HashMac384").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator384").toString());
        addHMACAlgorithm(provider, "SHA3-512", (new StringBuilder()).append(PREFIX).append("$HashMac512").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator512").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA3.getName();


    public SHA3$Mappings()
    {
    }
}
