// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESede.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DESede

public static class DESede$Mappings extends AlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("Cipher.DESEDE", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(PKCSObjectIdentifiers.des_EDE3_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm("Cipher.DESEDEWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(PKCSObjectIdentifiers.id_alg_CMS3DESwrap).toString(), (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
        provider.addAlgorithm("Cipher.DESEDERFC3211WRAP", (new StringBuilder()).append(PREFIX).append("$RFC3211").toString());
        provider.addAlgorithm("Alg.Alias.Cipher.TDEA", "DESEDE");
        provider.addAlgorithm("Alg.Alias.Cipher.TDEAWRAP", "DESEDEWRAP");
        provider.addAlgorithm("Alg.Alias.KeyGenerator.TDEA", "DESEDE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.TDEA", "DESEDE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.TDEA", "DESEDE");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.TDEA", "DESEDE");
        if(provider.hasAlgorithm("MessageDigest", "SHA-1"))
        {
            provider.addAlgorithm("Cipher.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndDES3Key").toString());
            provider.addAlgorithm("Cipher.BROKENPBEWITHSHAAND3-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$BrokePBEWithSHAAndDES3Key").toString());
            provider.addAlgorithm("Cipher.OLDPBEWITHSHAAND3-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$OldPBEWithSHAAndDES3Key").toString());
            provider.addAlgorithm("Cipher.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndDES2Key").toString());
            provider.addAlgorithm("Cipher.BROKENPBEWITHSHAAND2-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$BrokePBEWithSHAAndDES2Key").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC).toString(), "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC).toString(), "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1ANDDESEDE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND3-KEYTRIPLEDES-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND2-KEYTRIPLEDES-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
        }
        provider.addAlgorithm("KeyGenerator.DESEDE", (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(PKCSObjectIdentifiers.des_EDE3_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator3").toString());
        provider.addAlgorithm("KeyGenerator.DESEDEWRAP", (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        provider.addAlgorithm("SecretKeyFactory.DESEDE", (new StringBuilder()).append(PREFIX).append("$KeyFactory").toString());
        provider.addAlgorithm("Mac.DESEDECMAC", (new StringBuilder()).append(PREFIX).append("$CMAC").toString());
        provider.addAlgorithm("Mac.DESEDEMAC", (new StringBuilder()).append(PREFIX).append("$CBCMAC").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DESEDE", "DESEDEMAC");
        provider.addAlgorithm("Mac.DESEDEMAC/CFB8", (new StringBuilder()).append(PREFIX).append("$DESedeCFB8").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DESEDE/CFB8", "DESEDEMAC/CFB8");
        provider.addAlgorithm("Mac.DESEDEMAC64", (new StringBuilder()).append(PREFIX).append("$DESede64").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DESEDE64", "DESEDEMAC64");
        provider.addAlgorithm("Mac.DESEDEMAC64WITHISO7816-4PADDING", (new StringBuilder()).append(PREFIX).append("$DESede64with7816d4").toString());
        provider.addAlgorithm("Alg.Alias.Mac.DESEDE64WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("Alg.Alias.Mac.DESEDEISO9797ALG1MACWITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("Alg.Alias.Mac.DESEDEISO9797ALG1WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
        provider.addAlgorithm("AlgorithmParameters.DESEDE", "co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.des_EDE3_CBC).toString(), "DESEDE");
        provider.addAlgorithm("AlgorithmParameterGenerator.DESEDE", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(PKCSObjectIdentifiers.des_EDE3_CBC).toString(), "DESEDE");
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndDES3KeyFactory").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndDES2KeyFactory").toString());
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES3KEY-CBC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES2KEY-CBC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.3", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.4", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.3", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.4", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/DESede.getName();
    private static final String PACKAGE = "co.org.bouncy.jcajce.provider.symmetric";


    public DESede$Mappings()
    {
    }
}
