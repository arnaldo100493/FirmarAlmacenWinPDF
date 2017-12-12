// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamal.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.elgamal.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class ElGamal
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameterGenerator.ELGAMAL", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.AlgorithmParameterGeneratorSpi");
            provider.addAlgorithm("AlgorithmParameterGenerator.ElGamal", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.AlgorithmParameterGeneratorSpi");
            provider.addAlgorithm("AlgorithmParameters.ELGAMAL", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.AlgorithmParametersSpi");
            provider.addAlgorithm("AlgorithmParameters.ElGamal", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.AlgorithmParametersSpi");
            provider.addAlgorithm("Cipher.ELGAMAL", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.CipherSpi$NoPadding");
            provider.addAlgorithm("Cipher.ElGamal", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.CipherSpi$NoPadding");
            provider.addAlgorithm("Alg.Alias.Cipher.ELGAMAL/ECB/PKCS1PADDING", "ELGAMAL/PKCS1");
            provider.addAlgorithm("Alg.Alias.Cipher.ELGAMAL/NONE/PKCS1PADDING", "ELGAMAL/PKCS1");
            provider.addAlgorithm("Alg.Alias.Cipher.ELGAMAL/NONE/NOPADDING", "ELGAMAL");
            provider.addAlgorithm("Cipher.ELGAMAL/PKCS1", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.CipherSpi$PKCS1v1_5Padding");
            provider.addAlgorithm("KeyFactory.ELGAMAL", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.KeyFactorySpi");
            provider.addAlgorithm("KeyFactory.ElGamal", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.KeyFactorySpi");
            provider.addAlgorithm("KeyPairGenerator.ELGAMAL", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.KeyPairGeneratorSpi");
            provider.addAlgorithm("KeyPairGenerator.ElGamal", "co.org.bouncy.jcajce.provider.asymmetric.elgamal.KeyPairGeneratorSpi");
            co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter keyFact = new KeyFactorySpi();
            registerOid(provider, OIWObjectIdentifiers.elGamalAlgorithm, "ELGAMAL", keyFact);
            registerOidAlgorithmParameters(provider, OIWObjectIdentifiers.elGamalAlgorithm, "ELGAMAL");
        }

        public Mappings()
        {
        }
    }


    public ElGamal()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.elgamal.";
}
