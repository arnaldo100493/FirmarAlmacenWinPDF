// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tiger.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, Tiger

public static class Tiger$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.TIGER", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("MessageDigest.Tiger", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        addHMACAlgorithm(provider, "TIGER", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "TIGER", IANAObjectIdentifiers.hmacTIGER);
        provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACTIGER", (new StringBuilder()).append(PREFIX).append("$PBEWithMacKeyFactory").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/Tiger.getName();


    public Tiger$Mappings()
    {
    }
}
