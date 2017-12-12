// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD2.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, MD2

public static class MD2$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.MD2", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(PKCSObjectIdentifiers.md2).toString(), "MD2");
        addHMACAlgorithm(provider, "MD2", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/MD2.getName();


    public MD2$Mappings()
    {
    }
}
