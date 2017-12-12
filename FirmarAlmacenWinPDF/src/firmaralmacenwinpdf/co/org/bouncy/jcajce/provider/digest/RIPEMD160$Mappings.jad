// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD160.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, RIPEMD160

public static class RIPEMD160$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.RIPEMD160", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(TeleTrusTObjectIdentifiers.ripemd160).toString(), "RIPEMD160");
        addHMACAlgorithm(provider, "RIPEMD160", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "RIPEMD160", IANAObjectIdentifiers.hmacRIPEMD160);
        provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACRIPEMD160", (new StringBuilder()).append(PREFIX).append("$PBEWithHmacKeyFactory").toString());
        provider.addAlgorithm("Mac.PBEWITHHMACRIPEMD160", (new StringBuilder()).append(PREFIX).append("$PBEWithHmac").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/RIPEMD160.getName();


    public RIPEMD160$Mappings()
    {
    }
}
