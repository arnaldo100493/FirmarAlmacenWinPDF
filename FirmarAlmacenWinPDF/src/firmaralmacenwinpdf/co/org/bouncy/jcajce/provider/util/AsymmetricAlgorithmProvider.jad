// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricAlgorithmProvider.java

package co.org.bouncy.jcajce.provider.util;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.util:
//            AlgorithmProvider, AsymmetricKeyInfoConverter

public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider
{

    public AsymmetricAlgorithmProvider()
    {
    }

    protected void addSignatureAlgorithm(ConfigurableProvider provider, String digest, String algorithm, String className, ASN1ObjectIdentifier oid)
    {
        String mainName = (new StringBuilder()).append(digest).append("WITH").append(algorithm).toString();
        String jdk11Variation1 = (new StringBuilder()).append(digest).append("with").append(algorithm).toString();
        String jdk11Variation2 = (new StringBuilder()).append(digest).append("With").append(algorithm).toString();
        String alias = (new StringBuilder()).append(digest).append("/").append(algorithm).toString();
        provider.addAlgorithm((new StringBuilder()).append("Signature.").append(mainName).toString(), className);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(jdk11Variation1).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(jdk11Variation2).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(alias).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(oid).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.OID.").append(oid).toString(), mainName);
    }

    protected void registerOid(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name, AsymmetricKeyInfoConverter keyFactory)
    {
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyFactory.").append(oid).toString(), name);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyPairGenerator.").append(oid).toString(), name);
        provider.addKeyInfoConverter(oid, keyFactory);
    }

    protected void registerOidAlgorithmParameters(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name)
    {
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(oid).toString(), name);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(oid).toString(), name);
    }
}
