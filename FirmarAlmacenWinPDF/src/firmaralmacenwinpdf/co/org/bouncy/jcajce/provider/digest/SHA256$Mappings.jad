// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA256.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, SHA256

public static class SHA256$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.SHA-256", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA256", "SHA-256");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha256).toString(), "SHA-256");
        provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACSHA256", (new StringBuilder()).append(PREFIX).append("$PBEWithMacKeyFactory").toString());
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA-256", "PBEWITHHMACSHA256");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(NISTObjectIdentifiers.id_sha256).toString(), "PBEWITHHMACSHA256");
        addHMACAlgorithm(provider, "SHA256", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "SHA256", PKCSObjectIdentifiers.id_hmacWithSHA256);
        addHMACAlias(provider, "SHA256", NISTObjectIdentifiers.id_sha256);
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA256.getName();


    public SHA256$Mappings()
    {
    }
}
