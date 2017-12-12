// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, SHA1

public static class SHA1$Mappings extends DigestAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("MessageDigest.SHA-1", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA1", "SHA-1");
        provider.addAlgorithm("Alg.Alias.MessageDigest.SHA", "SHA-1");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(OIWObjectIdentifiers.idSHA1).toString(), "SHA-1");
        addHMACAlgorithm(provider, "SHA1", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        addHMACAlias(provider, "SHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
        addHMACAlias(provider, "SHA1", IANAObjectIdentifiers.hmacSHA1);
        provider.addAlgorithm("Mac.PBEWITHHMACSHA", (new StringBuilder()).append(PREFIX).append("$SHA1Mac").toString());
        provider.addAlgorithm("Mac.PBEWITHHMACSHA1", (new StringBuilder()).append(PREFIX).append("$SHA1Mac").toString());
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA", "PBEWITHHMACSHA1");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(OIWObjectIdentifiers.idSHA1).toString(), "PBEWITHHMACSHA1");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.").append(OIWObjectIdentifiers.idSHA1).toString(), "PBEWITHHMACSHA");
        provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACSHA1", (new StringBuilder()).append(PREFIX).append("$PBEWithMacKeyFactory").toString());
        provider.addAlgorithm("SecretKeyFactory.PBKDF2WithHmacSHA1", (new StringBuilder()).append(PREFIX).append("$PBKDF2WithHmacSHA1UTF8").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.id_PBKDF2).toString(), "PBKDF2WithHmacSHA1");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WithHmacSHA1AndUTF8", "PBKDF2WithHmacSHA1");
        provider.addAlgorithm("SecretKeyFactory.PBKDF2WithHmacSHA1And8BIT", (new StringBuilder()).append(PREFIX).append("$PBKDF2WithHmacSHA18BIT").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA1.getName();


    public SHA1$Mappings()
    {
    }
}
