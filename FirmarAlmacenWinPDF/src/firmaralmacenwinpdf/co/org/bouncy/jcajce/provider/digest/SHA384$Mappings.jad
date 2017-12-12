// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA384.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, SHA384

public static class SHA384$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.SHA-384", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA384", "SHA-384");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha384).toString(), "SHA-384");
        provider.addAlgorithm("Mac.OLDHMACSHA384", (new StringBuilder()).append(PREFIX).append("$OldSHA384").toString());
        addHMACAlgorithm(provider, "SHA384", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "SHA384", PKCSObjectIdentifiers.id_hmacWithSHA384);
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA384.getName();


    public SHA384$Mappings()
    {
    }
}
