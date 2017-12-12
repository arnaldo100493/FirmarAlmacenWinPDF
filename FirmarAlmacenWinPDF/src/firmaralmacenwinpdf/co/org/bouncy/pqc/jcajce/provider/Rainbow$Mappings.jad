// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rainbow.java

package co.org.bouncy.pqc.jcajce.provider;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;
import co.org.bouncy.pqc.asn1.PQCObjectIdentifiers;
import co.org.bouncy.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider:
//            Rainbow

public static class Rainbow$Mappings extends AsymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("KeyFactory.Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi");
        provider.addAlgorithm("KeyPairGenerator.Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi");
        addSignatureAlgorithm(provider, "SHA224", "Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.SignatureSpi$withSha224", PQCObjectIdentifiers.rainbowWithSha224);
        addSignatureAlgorithm(provider, "SHA256", "Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.SignatureSpi$withSha256", PQCObjectIdentifiers.rainbowWithSha256);
        addSignatureAlgorithm(provider, "SHA384", "Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.SignatureSpi$withSha384", PQCObjectIdentifiers.rainbowWithSha384);
        addSignatureAlgorithm(provider, "SHA512", "Rainbow", "co.org.bouncy.pqc.jcajce.provider.rainbow.SignatureSpi$withSha512", PQCObjectIdentifiers.rainbowWithSha512);
        co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter keyFact = new RainbowKeyFactorySpi();
        registerOid(provider, PQCObjectIdentifiers.rainbow, "Rainbow", keyFact);
        registerOidAlgorithmParameters(provider, PQCObjectIdentifiers.rainbow, "Rainbow");
    }

    public Rainbow$Mappings()
    {
    }
}
