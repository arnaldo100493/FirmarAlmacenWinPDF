// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Twofish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, Twofish

public static class Twofish$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.Twofish", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("KeyGenerator.Twofish", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("AlgorithmParameters.Twofish", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH-CBC", "PKCS12PBE");
        provider.addAlgorithm("Cipher.PBEWITHSHAANDTWOFISH-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAANDTWOFISH-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAKeyFactory").toString());
        addGMacAlgorithm(provider, "Twofish", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Twofish.getName();


    public Twofish$Mappings()
    {
    }
}
