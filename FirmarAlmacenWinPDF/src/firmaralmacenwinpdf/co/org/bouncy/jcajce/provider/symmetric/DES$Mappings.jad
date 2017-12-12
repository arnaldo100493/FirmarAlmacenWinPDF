// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public static class DES$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.DES", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(OIWObjectIdentifiers.desCBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        addAlias(provider, OIWObjectIdentifiers.desCBC, "DES");
        provider.addAlgorithm("Cipher.DESRFC3211WRAP", (new StringBuilder()).append(PREFIX).append("$RFC3211").toString());
        provider.addAlgorithm("KeyGenerator.DES", (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        provider.addAlgorithm("SecretKeyFactory.DES", (new StringBuilder()).append(PREFIX).append("$KeyFactory").toString());
        provider.addAlgorithm("Mac.DESCMAC", (new StringBuilder()).append(PREFIX).append("$CMAC").toString());
        provider.addAlgorithm("Mac.DESMAC", (new StringBuilder()).append(PREFIX).append("$CBCMAC").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DES", "DESMAC");
        provider.addAlgorithm("Mac.DESMAC/CFB8", (new StringBuilder()).append(PREFIX).append("$DESCFB8").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DES/CFB8", "DESMAC/CFB8");
        provider.addAlgorithm("Mac.DESMAC64", (new StringBuilder()).append(PREFIX).append("$DES64").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DES64", "DESMAC64");
        provider.addAlgorithm("Mac.DESMAC64WITHISO7816-4PADDING", (new StringBuilder()).append(PREFIX).append("$DES64with7816d4").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DES64WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("Alg.Alias.Mac.DESISO9797ALG1MACWITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("Alg.Alias.Mac.DESISO9797ALG1WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("Mac.DESWITHISO9797", (new StringBuilder()).append(PREFIX).append("$DES9797Alg3").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DESISO9797MAC", "DESWITHISO9797");
        provider.addAlgorithm("Mac.ISO9797ALG3MAC", (new StringBuilder()).append(PREFIX).append("$DES9797Alg3").toString());
        provider.addAlgorithm("Alg.Alias.Mac.ISO9797ALG3", "ISO9797ALG3MAC");
        provider.addAlgorithm("Mac.ISO9797ALG3WITHISO7816-4PADDING", (new StringBuilder()).append(PREFIX).append("$DES9797Alg3with7816d4").toString());
        provider.addAlgorithm("Alg.Alias.Mac.ISO9797ALG3MACWITHISO7816-4PADDING", "ISO9797ALG3WITHISO7816-4PADDING");
        provider.addAlgorithm("AlgorithmParameters.DES", "co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(OIWObjectIdentifiers.desCBC).toString(), "DES");
        provider.addAlgorithm("AlgorithmParameterGenerator.DES", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(OIWObjectIdentifiers.desCBC).toString(), "DES");
        provider.addAlgorithm("Cipher.PBEWITHMD2ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithMD2").toString());
        provider.addAlgorithm("Cipher.PBEWITHMD5ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHA1ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA1").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC).toString(), "PBEWITHMD2ANDDES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC).toString(), "PBEWITHMD5ANDDES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC).toString(), "PBEWITHSHA1ANDDES");
        provider.addAlgorithm("SecretKeyFactory.PBEWITHMD2ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithMD2KeyFactory").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHMD5ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5KeyFactory").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHA1ANDDES", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA1KeyFactory").toString());
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC).toString(), "PBEWITHMD2ANDDES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC).toString(), "PBEWITHMD5ANDDES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC).toString(), "PBEWITHSHA1ANDDES");
    }

    private void addAlias(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name)
    {
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.").append(oid.getId()).toString(), name);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyFactory.").append(oid.getId()).toString(), name);
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/DES.getName();
    private static final String PACKAGE = "co.org.bouncy.jcajce.provider.symmetric";


    public DES$Mappings()
    {
    }
}
