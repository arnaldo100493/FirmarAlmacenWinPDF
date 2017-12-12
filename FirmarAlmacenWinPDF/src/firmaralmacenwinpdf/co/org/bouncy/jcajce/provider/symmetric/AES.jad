// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.bc.BCObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.macs.CMac;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.*;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseWrapCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider

public final class AES
{
    public static class Mappings extends SymmetricAlgorithmProvider
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


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "AES IV";
        }

        public AlgParams()
        {
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters()
        {
            byte iv[] = new byte[16];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            AlgorithmParameters params;
            try
            {
                params = AlgorithmParameters.getInstance("AES", "BC");
                params.init(new IvParameterSpec(iv));
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
            return params;
        }

        public AlgParamGen()
        {
        }
    }

    public static class PBEWithMD5And256BitAESCBCOpenSSL extends PBESecretKeyFactory
    {

        public PBEWithMD5And256BitAESCBCOpenSSL()
        {
            super("PBEWithMD5And256BitAES-CBC-OpenSSL", null, true, 3, 0, 256, 128);
        }
    }

    public static class PBEWithMD5And192BitAESCBCOpenSSL extends PBESecretKeyFactory
    {

        public PBEWithMD5And192BitAESCBCOpenSSL()
        {
            super("PBEWithMD5And192BitAES-CBC-OpenSSL", null, true, 3, 0, 192, 128);
        }
    }

    public static class PBEWithMD5And128BitAESCBCOpenSSL extends PBESecretKeyFactory
    {

        public PBEWithMD5And128BitAESCBCOpenSSL()
        {
            super("PBEWithMD5And128BitAES-CBC-OpenSSL", null, true, 3, 0, 128, 128);
        }
    }

    public static class PBEWithSHA256And256BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHA256And256BitAESBC()
        {
            super("PBEWithSHA256And256BitAES-CBC-BC", null, true, 2, 4, 256, 128);
        }
    }

    public static class PBEWithSHA256And192BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHA256And192BitAESBC()
        {
            super("PBEWithSHA256And192BitAES-CBC-BC", null, true, 2, 4, 192, 128);
        }
    }

    public static class PBEWithSHA256And128BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHA256And128BitAESBC()
        {
            super("PBEWithSHA256And128BitAES-CBC-BC", null, true, 2, 4, 128, 128);
        }
    }

    public static class PBEWithSHAAnd256BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd256BitAESBC()
        {
            super("PBEWithSHA1And256BitAES-CBC-BC", null, true, 2, 1, 256, 128);
        }
    }

    public static class PBEWithSHAAnd192BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd192BitAESBC()
        {
            super("PBEWithSHA1And192BitAES-CBC-BC", null, true, 2, 1, 192, 128);
        }
    }

    public static class PBEWithSHAAnd128BitAESBC extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd128BitAESBC()
        {
            super("PBEWithSHA1And128BitAES-CBC-BC", null, true, 2, 1, 128, 128);
        }
    }

    public static class KeyGen256 extends KeyGen
    {

        public KeyGen256()
        {
            super(256);
        }
    }

    public static class KeyGen192 extends KeyGen
    {

        public KeyGen192()
        {
            super(192);
        }
    }

    public static class KeyGen128 extends KeyGen
    {

        public KeyGen128()
        {
            super(128);
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            this(192);
        }

        public KeyGen(int keySize)
        {
            super("AES", keySize, new CipherKeyGenerator());
        }
    }

    public static class PBEWithAESCBC extends BaseBlockCipher
    {

        public PBEWithAESCBC()
        {
            super(new CBCBlockCipher(new AESFastEngine()));
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher
    {

        public RFC3211Wrap()
        {
            super(new RFC3211WrapEngine(new AESFastEngine()), 16);
        }
    }

    public static class Wrap extends BaseWrapCipher
    {

        public Wrap()
        {
            super(new AESWrapEngine());
        }
    }

    public static class AESGMAC extends BaseMac
    {

        public AESGMAC()
        {
            super(new GMac(new GCMBlockCipher(new AESFastEngine())));
        }
    }

    public static class AESCMAC extends BaseMac
    {

        public AESCMAC()
        {
            super(new CMac(new AESFastEngine()));
        }
    }

    public static class OFB extends BaseBlockCipher
    {

        public OFB()
        {
            super(new BufferedBlockCipher(new OFBBlockCipher(new AESFastEngine(), 128)), 128);
        }
    }

    public static class CFB extends BaseBlockCipher
    {

        public CFB()
        {
            super(new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 128)), 128);
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new AESFastEngine()), 128);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new AESFastEngine();
                }

            }
);
        }
    }


    private AES()
    {
    }
}
