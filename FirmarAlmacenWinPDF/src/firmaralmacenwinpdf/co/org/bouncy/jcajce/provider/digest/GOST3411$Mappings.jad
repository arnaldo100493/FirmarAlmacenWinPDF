// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3411.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, GOST3411

public static class GOST3411$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.GOST3411", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.GOST", "GOST3411");
        provider.addAlgorithm("Alg.Alias.MessageDigest.GOST-3411", "GOST3411");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(CryptoProObjectIdentifiers.gostR3411).toString(), "GOST3411");
        addHMACAlgorithm(provider, "GOST3411", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "GOST3411", CryptoProObjectIdentifiers.gostR3411);
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/GOST3411.getName();


    public GOST3411$Mappings()
    {
    }
}
