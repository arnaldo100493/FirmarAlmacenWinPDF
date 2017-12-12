// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.bc.BCObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider, AES

public static class AES$Mappings extends SymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.AES", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.2", "AES");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.22", "AES");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.42", "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NISTObjectIdentifiers.id_aes128_CBC).toString(), "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NISTObjectIdentifiers.id_aes192_CBC).toString(), "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NISTObjectIdentifiers.id_aes256_CBC).toString(), "AES");
        provider.addAlgorithm("AlgorithmParameterGenerator.AES", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.2", "AES");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.22", "AES");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.42", "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NISTObjectIdentifiers.id_aes128_CBC).toString(), "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NISTObjectIdentifiers.id_aes192_CBC).toString(), "AES");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NISTObjectIdentifiers.id_aes256_CBC).toString(), "AES");
        provider.addAlgorithm("Cipher.AES", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.2", "AES");
        provider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.22", "AES");
        provider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.42", "AES");
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes128_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes192_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes256_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$ECB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes128_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes192_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes256_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes128_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$OFB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes192_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$OFB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes256_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$OFB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes128_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$CFB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes192_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$CFB").toString());
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NISTObjectIdentifiers.id_aes256_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$CFB").toString());
        provider.addAlgorithm("Cipher.AESWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NISTObjectIdentifiers.id_aes128_wrap).toString(), "AESWRAP");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NISTObjectIdentifiers.id_aes192_wrap).toString(), "AESWRAP");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NISTObjectIdentifiers.id_aes256_wrap).toString(), "AESWRAP");
        provider.addAlgorithm("Cipher.AESRFC3211WRAP", (new StringBuilder()).append(PREFIX).append("$RFC3211Wrap").toString());
        provider.addAlgorithm("KeyGenerator.AES", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm("KeyGenerator.2.16.840.1.101.3.4.2", (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm("KeyGenerator.2.16.840.1.101.3.4.22", (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm("KeyGenerator.2.16.840.1.101.3.4.42", (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes128_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes128_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes128_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes128_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes192_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes192_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes192_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes192_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes256_ECB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes256_CBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes256_OFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes256_CFB).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm("KeyGenerator.AESWRAP", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes128_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes192_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
        provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NISTObjectIdentifiers.id_aes256_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
        provider.addAlgorithm("Mac.AESCMAC", (new StringBuilder()).append(PREFIX).append("$AESCMAC").toString());
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId()).toString(), "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId()).toString(), "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId()).toString(), "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId()).toString(), "PBEWITHSHA256AND128BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId()).toString(), "PBEWITHSHA256AND192BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId()).toString(), "PBEWITHSHA256AND256BITAES-CBC-BC");
        provider.addAlgorithm("Cipher.PBEWITHSHAAND128BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHAAND192BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHAAND256BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHA256AND128BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHA256AND192BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHSHA256AND256BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
        provider.addAlgorithm("Cipher.PBEWITHMD5AND128BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHMD5AND192BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("Cipher.PBEWITHMD5AND256BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithAESCBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHMD5AND128BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5And128BitAESCBCOpenSSL").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHMD5AND192BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5And192BitAESCBCOpenSSL").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHMD5AND256BITAES-CBC-OPENSSL", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5And256BitAESCBCOpenSSL").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND128BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128BitAESBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND192BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd192BitAESBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND256BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd256BitAESBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHA256AND128BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA256And128BitAESBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHA256AND192BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA256And192BitAESBC").toString());
        provider.addAlgorithm("SecretKeyFactory.PBEWITHSHA256AND256BITAES-CBC-BC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA256And256BitAESBC").toString());
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId()).toString(), "PBEWITHSHAAND128BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId()).toString(), "PBEWITHSHAAND192BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId()).toString(), "PBEWITHSHAAND256BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId()).toString(), "PBEWITHSHA256AND128BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId()).toString(), "PBEWITHSHA256AND192BITAES-CBC-BC");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId()).toString(), "PBEWITHSHA256AND256BITAES-CBC-BC");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND192BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND256BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND128BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND192BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND256BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND128BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND192BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND256BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND128BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND192BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND256BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND128BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND192BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND256BITAES-CBC-BC", "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId()).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId()).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId()).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId()).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId()).toString(), "PKCS12PBE");
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId()).toString(), "PKCS12PBE");
        addGMacAlgorithm(provider, "AES", (new StringBuilder()).append(PREFIX).append("$AESGMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
    }

    private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/AES.getName();
    private static final String wrongAES128 = "2.16.840.1.101.3.4.2";
    private static final String wrongAES192 = "2.16.840.1.101.3.4.22";
    private static final String wrongAES256 = "2.16.840.1.101.3.4.42";


    public AES$Mappings()
    {
    }
}
