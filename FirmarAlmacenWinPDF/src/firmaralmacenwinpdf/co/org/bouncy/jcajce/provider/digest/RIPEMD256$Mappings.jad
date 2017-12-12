// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD256.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, RIPEMD256

public static class RIPEMD256$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.RIPEMD256", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(TeleTrusTObjectIdentifiers.ripemd256).toString(), "RIPEMD256");
        addHMACAlgorithm(provider, "RIPEMD256", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/RIPEMD256.getName();


    public RIPEMD256$Mappings()
    {
    }
}
