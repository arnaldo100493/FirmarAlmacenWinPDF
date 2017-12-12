// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.gost.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric:
//            GOST

public static class GOST$Mappings extends AsymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("KeyPairGenerator.GOST3410", "co.org.bouncy.jcajce.provider.asymmetric.gost.KeyPairGeneratorSpi");
        provider.addAlgorithm("Alg.Alias.KeyPairGenerator.GOST-3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.KeyPairGenerator.GOST-3410-94", "GOST3410");
        provider.addAlgorithm("KeyFactory.GOST3410", "co.org.bouncy.jcajce.provider.asymmetric.gost.KeyFactorySpi");
        provider.addAlgorithm("Alg.Alias.KeyFactory.GOST-3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.KeyFactory.GOST-3410-94", "GOST3410");
        provider.addAlgorithm("AlgorithmParameters.GOST3410", "co.org.bouncy.jcajce.provider.asymmetric.gost.AlgorithmParametersSpi");
        provider.addAlgorithm("AlgorithmParameterGenerator.GOST3410", "co.org.bouncy.jcajce.provider.asymmetric.gost.AlgorithmParameterGeneratorSpi");
        registerOid(provider, CryptoProObjectIdentifiers.gostR3410_94, "GOST3410", new KeyFactorySpi());
        registerOidAlgorithmParameters(provider, CryptoProObjectIdentifiers.gostR3410_94, "GOST3410");
        provider.addAlgorithm("Signature.GOST3410", "co.org.bouncy.jcajce.provider.asymmetric.gost.SignatureSpi");
        provider.addAlgorithm("Alg.Alias.Signature.GOST-3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.Signature.GOST-3410-94", "GOST3410");
        provider.addAlgorithm("Alg.Alias.Signature.GOST3411withGOST3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.Signature.GOST3411WITHGOST3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.Signature.GOST3411WithGOST3410", "GOST3410");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94).toString(), "GOST3410");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.GOST-3410", "GOST3410");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.GOST-3410", "GOST3410");
    }

    public GOST$Mappings()
    {
    }
}
