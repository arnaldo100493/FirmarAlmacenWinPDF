// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, MD5

public static class MD5$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.MD5", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(PKCSObjectIdentifiers.md5).toString(), "MD5");
        addHMACAlgorithm(provider, "MD5", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "MD5", IANAObjectIdentifiers.hmacMD5);
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/MD5.getName();


    public MD5$Mappings()
    {
    }
}
