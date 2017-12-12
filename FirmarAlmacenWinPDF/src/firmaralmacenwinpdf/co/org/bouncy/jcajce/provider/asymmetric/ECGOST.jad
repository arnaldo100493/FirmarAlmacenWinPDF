// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECGOST.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.ecgost.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class ECGOST
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyFactory.ECGOST3410", "co.org.bouncy.jcajce.provider.asymmetric.ecgost.KeyFactorySpi");
            provider.addAlgorithm("Alg.Alias.KeyFactory.GOST-3410-2001", "ECGOST3410");
            provider.addAlgorithm("Alg.Alias.KeyFactory.ECGOST-3410", "ECGOST3410");
            registerOid(provider, CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410", new KeyFactorySpi());
            registerOidAlgorithmParameters(provider, CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
            provider.addAlgorithm("KeyPairGenerator.ECGOST3410", "co.org.bouncy.jcajce.provider.asymmetric.ecgost.KeyPairGeneratorSpi");
            provider.addAlgorithm("Alg.Alias.KeyPairGenerator.ECGOST-3410", "ECGOST3410");
            provider.addAlgorithm("Alg.Alias.KeyPairGenerator.GOST-3410-2001", "ECGOST3410");
            provider.addAlgorithm("Signature.ECGOST3410", "co.org.bouncy.jcajce.provider.asymmetric.ecgost.SignatureSpi");
            provider.addAlgorithm("Alg.Alias.Signature.ECGOST-3410", "ECGOST3410");
            provider.addAlgorithm("Alg.Alias.Signature.GOST-3410-2001", "ECGOST3410");
            addSignatureAlgorithm(provider, "GOST3411", "ECGOST3410", "co.org.bouncy.jcajce.provider.asymmetric.ecgost.SignatureSpi", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        }

        public Mappings()
        {
        }
    }


    public ECGOST()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.ecgost.";
}
