// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ARC4.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            ARC4

public static class ARC4$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.ARC4", (new StringBuilder()).append(PREFIX).append("$Base").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.rc4).toString(), "ARC4");
        provider.addAlgorithm("Alg.Alias.Cipher.ARCFOUR", "ARC4");
        provider.addAlgorithm("Alg.Alias.Cipher.RC4", "ARC4");
        provider.addAlgorithm("KeyGenerator.ARC4", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("Alg.Alias.KeyGenerator.RC4", "ARC4");
        provider.addAlgorithm("Alg.Alias.KeyGenerator.1.2.840.113549.3.4", "ARC4");
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND128BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128BitKeyFactory").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND40BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40BitKeyFactory").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC4", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC4", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC4", "PKCS12PBE");
        provider.addAlgorithm("Cipher.PBEWITHSHAAND128BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128Bit").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHAAND40BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40Bit").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PBEWITHSHAAND128BITRC4");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PBEWITHSHAAND40BITRC4");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC4", "PBEWITHSHAAND128BITRC4");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC4", "PBEWITHSHAAND40BITRC4");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PBEWITHSHAAND128BITRC4");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PBEWITHSHAAND40BITRC4");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/ARC4.getName();


    public ARC4$Mappings()
    {
    }
}
