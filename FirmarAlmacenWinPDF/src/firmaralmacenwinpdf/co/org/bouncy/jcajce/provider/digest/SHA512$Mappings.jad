// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, SHA512

public static class SHA512$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.SHA-512", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512", "SHA-512");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512).toString(), "SHA-512");
        provider.addAlgorithm("MessageDigest.SHA-512/224", (new StringBuilder()).append(PREFIX).append("$DigestT224").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512/224", "SHA-512/224");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512_224).toString(), "SHA-512/224");
        provider.addAlgorithm("MessageDigest.SHA-512/256", (new StringBuilder()).append(PREFIX).append("$DigestT256").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512256", "SHA-512/256");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512_256).toString(), "SHA-512/256");
        provider.addAlgorithm("Mac.OLDHMACSHA512", (new StringBuilder()).append(PREFIX).append("$OldSHA512").toString());
        addHMACAlgorithm(provider, "SHA512", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "SHA512", PKCSObjectIdentifiers.id_hmacWithSHA512);
        addHMACAlgorithm(provider, "SHA512/224", (new StringBuilder()).append(PREFIX).append("$HashMacT224").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGeneratorT224").toString());
        addHMACAlgorithm(provider, "SHA512/256", (new StringBuilder()).append(PREFIX).append("$HashMacT256").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGeneratorT256").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA512.getName();


    public SHA512$Mappings()
    {
    }
}
