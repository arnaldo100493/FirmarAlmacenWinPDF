// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSA.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.dsa.DSAUtil;
import co.org.bouncy.jcajce.provider.asymmetric.dsa.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;

public class DSA
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.AlgorithmParametersSpi");
            provider.addAlgorithm("AlgorithmParameterGenerator.DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.AlgorithmParameterGeneratorSpi");
            provider.addAlgorithm("KeyPairGenerator.DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.KeyPairGeneratorSpi");
            provider.addAlgorithm("KeyFactory.DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.KeyFactorySpi");
            provider.addAlgorithm("Signature.DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$stdDSA");
            provider.addAlgorithm("Signature.NONEWITHDSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$noneDSA");
            provider.addAlgorithm("Alg.Alias.Signature.RAWDSA", "NONEWITHDSA");
            addSignatureAlgorithm(provider, "SHA224", "DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$dsa224", NISTObjectIdentifiers.dsa_with_sha224);
            addSignatureAlgorithm(provider, "SHA256", "DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$dsa256", NISTObjectIdentifiers.dsa_with_sha256);
            addSignatureAlgorithm(provider, "SHA384", "DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$dsa384", NISTObjectIdentifiers.dsa_with_sha384);
            addSignatureAlgorithm(provider, "SHA512", "DSA", "co.org.bouncy.jcajce.provider.asymmetric.dsa.DSASigner$dsa512", NISTObjectIdentifiers.dsa_with_sha512);
            provider.addAlgorithm("Alg.Alias.Signature.SHA/DSA", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1withDSA", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WITHDSA", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.1", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.3", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.DSAwithSHA1", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.DSAWITHSHA1", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WithDSA", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.DSAWithSHA1", "DSA");
            provider.addAlgorithm("Alg.Alias.Signature.1.2.840.10040.4.3", "DSA");
            AsymmetricKeyInfoConverter keyFact = new KeyFactorySpi();
            for(int i = 0; i != DSAUtil.dsaOids.length; i++)
            {
                provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(DSAUtil.dsaOids[i]).toString(), "DSA");
                registerOid(provider, DSAUtil.dsaOids[i], "DSA", keyFact);
                registerOidAlgorithmParameters(provider, DSAUtil.dsaOids[i], "DSA");
            }

        }

        public Mappings()
        {
        }
    }


    public DSA()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.dsa.";
}
