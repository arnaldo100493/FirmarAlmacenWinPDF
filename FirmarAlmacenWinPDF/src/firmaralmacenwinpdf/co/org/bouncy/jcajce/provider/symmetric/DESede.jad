// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESede.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.generators.DESedeKeyGenerator;
import co.org.bouncy.crypto.macs.*;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.paddings.ISO7816d4Padding;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseWrapCipher;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.security.*;
import java.security.spec.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public final class DESede
{
    public static class Mappings extends AlgorithmProvider
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


        public Mappings()
        {
        }
    }

    public static class KeyFactory extends BaseSecretKeyFactory
    {

        protected KeySpec engineGetKeySpec(SecretKey key, Class keySpec)
            throws InvalidKeySpecException
        {
            if(keySpec == null)
                throw new InvalidKeySpecException("keySpec parameter is null");
            if(key == null)
                throw new InvalidKeySpecException("key parameter is null");
            if(javax/crypto/spec/SecretKeySpec.isAssignableFrom(keySpec))
                return new SecretKeySpec(key.getEncoded(), algName);
            if(!javax/crypto/spec/DESedeKeySpec.isAssignableFrom(keySpec))
                break MISSING_BLOCK_LABEL_141;
            byte bytes[] = key.getEncoded();
            try
            {
                if(bytes.length == 16)
                {
                    byte longKey[] = new byte[24];
                    System.arraycopy(bytes, 0, longKey, 0, 16);
                    System.arraycopy(bytes, 0, longKey, 16, 8);
                    return new DESedeKeySpec(longKey);
                }
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException(e.toString());
            }
            return new DESedeKeySpec(bytes);
            throw new InvalidKeySpecException("Invalid KeySpec");
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec)
            throws InvalidKeySpecException
        {
            if(keySpec instanceof DESedeKeySpec)
            {
                DESedeKeySpec desKeySpec = (DESedeKeySpec)keySpec;
                return new SecretKeySpec(desKeySpec.getKey(), "DESede");
            } else
            {
                return super.engineGenerateSecret(keySpec);
            }
        }

        public KeyFactory()
        {
            super("DESede", null);
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters()
        {
            byte iv[] = new byte[8];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            AlgorithmParameters params;
            try
            {
                params = AlgorithmParameters.getInstance("DES", "BC");
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

    public static class PBEWithSHAAndDES2KeyFactory extends DES.DESPBEKeyFactory
    {

        public PBEWithSHAAndDES2KeyFactory()
        {
            super("PBEwithSHAandDES2Key-CBC", PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, true, 2, 1, 128, 64);
        }
    }

    public static class PBEWithSHAAndDES3KeyFactory extends DES.DESPBEKeyFactory
    {

        public PBEWithSHAAndDES3KeyFactory()
        {
            super("PBEwithSHAandDES3Key-CBC", PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, true, 2, 1, 192, 64);
        }
    }

    public static class PBEWithSHAAndDES2Key extends BaseBlockCipher
    {

        public PBEWithSHAAndDES2Key()
        {
            super(new CBCBlockCipher(new DESedeEngine()));
        }
    }

    public static class PBEWithSHAAndDES3Key extends BaseBlockCipher
    {

        public PBEWithSHAAndDES3Key()
        {
            super(new CBCBlockCipher(new DESedeEngine()));
        }
    }

    public static class KeyGenerator3 extends BaseKeyGenerator
    {

        public KeyGenerator3()
        {
            super("DESede3", 192, new DESedeKeyGenerator());
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        protected void engineInit(int keySize, SecureRandom random)
        {
            super.engineInit(keySize, random);
            keySizeSet = true;
        }

        protected SecretKey engineGenerateKey()
        {
            if(uninitialised)
            {
                engine.init(new KeyGenerationParameters(new SecureRandom(), defaultKeySize));
                uninitialised = false;
            }
            if(!keySizeSet)
            {
                byte k[] = engine.generateKey();
                System.arraycopy(k, 0, k, 16, 8);
                return new SecretKeySpec(k, algName);
            } else
            {
                return new SecretKeySpec(engine.generateKey(), algName);
            }
        }

        private boolean keySizeSet;

        public KeyGenerator()
        {
            super("DESede", 192, new DESedeKeyGenerator());
            keySizeSet = false;
        }
    }

    public static class RFC3211 extends BaseWrapCipher
    {

        public RFC3211()
        {
            super(new RFC3211WrapEngine(new DESedeEngine()), 8);
        }
    }

    public static class Wrap extends BaseWrapCipher
    {

        public Wrap()
        {
            super(new DESedeWrapEngine());
        }
    }

    public static class CMAC extends BaseMac
    {

        public CMAC()
        {
            super(new CMac(new DESedeEngine()));
        }
    }

    public static class CBCMAC extends BaseMac
    {

        public CBCMAC()
        {
            super(new CBCBlockCipherMac(new DESedeEngine()));
        }
    }

    public static class DESede64with7816d4 extends BaseMac
    {

        public DESede64with7816d4()
        {
            super(new CBCBlockCipherMac(new DESedeEngine(), 64, new ISO7816d4Padding()));
        }
    }

    public static class DESede64 extends BaseMac
    {

        public DESede64()
        {
            super(new CBCBlockCipherMac(new DESedeEngine(), 64));
        }
    }

    public static class DESedeCFB8 extends BaseMac
    {

        public DESedeCFB8()
        {
            super(new CFBBlockCipherMac(new DESedeEngine()));
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new DESedeEngine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new DESedeEngine());
        }
    }


    private DESede()
    {
    }
}
