// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SEED.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.kisa.KISAObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, SEED

public static class SEED$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.SEED", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(KISAObjectIdentifiers.id_seedCBC).toString(), "SEED");
        provider.addAlgorithm("AlgorithmParameterGenerator.SEED", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(KISAObjectIdentifiers.id_seedCBC).toString(), "SEED");
        provider.addAlgorithm("Cipher.SEED", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(KISAObjectIdentifiers.id_seedCBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm("Cipher.SEEDWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap).toString(), "SEEDWRAP");
        provider.addAlgorithm("KeyGenerator.SEED", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(KISAObjectIdentifiers.id_seedCBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        addGMacAlgorithm(provider, "SEED", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/SEED.getName();


    public SEED$Mappings()
    {
    }
}
