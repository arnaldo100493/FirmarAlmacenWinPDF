// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.ua.UAObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.dstu.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class DSTU4145
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyFactory.DSTU4145", "co.org.bouncy.jcajce.provider.asymmetric.dstu.KeyFactorySpi");
            provider.addAlgorithm("Alg.Alias.KeyFactory.DSTU-4145-2002", "DSTU4145");
            provider.addAlgorithm("Alg.Alias.KeyFactory.DSTU4145-3410", "DSTU4145");
            registerOid(provider, UAObjectIdentifiers.dstu4145le, "DSTU4145", new KeyFactorySpi());
            registerOidAlgorithmParameters(provider, UAObjectIdentifiers.dstu4145le, "DSTU4145");
            registerOid(provider, UAObjectIdentifiers.dstu4145be, "DSTU4145", new KeyFactorySpi());
            registerOidAlgorithmParameters(provider, UAObjectIdentifiers.dstu4145be, "DSTU4145");
            provider.addAlgorithm("KeyPairGenerator.DSTU4145", "co.org.bouncy.jcajce.provider.asymmetric.dstu.KeyPairGeneratorSpi");
            provider.addAlgorithm("Alg.Alias.KeyPairGenerator.DSTU-4145", "DSTU4145");
            provider.addAlgorithm("Alg.Alias.KeyPairGenerator.DSTU-4145-2002", "DSTU4145");
            provider.addAlgorithm("Signature.DSTU4145", "co.org.bouncy.jcajce.provider.asymmetric.dstu.SignatureSpi");
            provider.addAlgorithm("Alg.Alias.Signature.DSTU-4145", "DSTU4145");
            provider.addAlgorithm("Alg.Alias.Signature.DSTU-4145-2002", "DSTU4145");
            addSignatureAlgorithm(provider, "GOST3411", "DSTU4145LE", "co.org.bouncy.jcajce.provider.asymmetric.dstu.SignatureSpiLe", UAObjectIdentifiers.dstu4145le);
            addSignatureAlgorithm(provider, "GOST3411", "DSTU4145", "co.org.bouncy.jcajce.provider.asymmetric.dstu.SignatureSpi", UAObjectIdentifiers.dstu4145be);
        }

        public Mappings()
        {
        }
    }


    public DSTU4145()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.dstu.";
}
