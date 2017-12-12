// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestAlgorithmProvider.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

abstract class DigestAlgorithmProvider extends AlgorithmProvider
{

    DigestAlgorithmProvider()
    {
    }

    protected void addHMACAlgorithm(ConfigurableProvider provider, String algorithm, String algorithmClassName, String keyGeneratorClassName)
    {
        String mainName = (new StringBuilder()).append("HMAC").append(algorithm).toString();
        provider.addAlgorithm((new StringBuilder()).append("Mac.").append(mainName).toString(), algorithmClassName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.HMAC-").append(algorithm).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.HMAC/").append(algorithm).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(mainName).toString(), keyGeneratorClassName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.HMAC-").append(algorithm).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.HMAC/").append(algorithm).toString(), mainName);
    }

    protected void addHMACAlias(ConfigurableProvider provider, String algorithm, ASN1ObjectIdentifier oid)
    {
        String mainName = (new StringBuilder()).append("HMAC").append(algorithm).toString();
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.").append(oid).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.").append(oid).toString(), mainName);
    }
}
