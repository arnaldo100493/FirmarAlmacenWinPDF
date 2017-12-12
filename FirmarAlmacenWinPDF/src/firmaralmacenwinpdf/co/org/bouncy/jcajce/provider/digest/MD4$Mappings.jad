// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD4.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, MD4

public static class MD4$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.MD4", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(PKCSObjectIdentifiers.md4).toString(), "MD4");
        addHMACAlgorithm(provider, "MD4", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/MD4.getName();


    public MD4$Mappings()
    {
    }
}
