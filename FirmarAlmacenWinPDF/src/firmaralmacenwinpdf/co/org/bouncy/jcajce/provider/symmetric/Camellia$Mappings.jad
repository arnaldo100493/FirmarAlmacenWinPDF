// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Camellia.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, Camellia

public static class Camellia$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm("AlgorithmParameterGenerator.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), "CAMELLIA");
        provider.addAlgorithm("Cipher.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm("Cipher.CAMELLIARFC3211WRAP", (new StringBuilder()).append(PREFIX).append("$RFC3211Wrap").toString());
        provider.addAlgorithm("Cipher.CAMELLIAWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia128_wrap).toString(), "CAMELLIAWRAP");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia192_wrap).toString(), "CAMELLIAWRAP");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia256_wrap).toString(), "CAMELLIAWRAP");
        provider.addAlgorithm("KeyGenerator.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia128_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia192_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia256_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        addGMacAlgorithm(provider, "CAMELLIA", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Camellia.getName();


    public Camellia$Mappings()
    {
    }
}
