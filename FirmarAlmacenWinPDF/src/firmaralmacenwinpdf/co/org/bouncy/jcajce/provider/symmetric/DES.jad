// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.DESEngine;
import co.org.bouncy.crypto.engines.RFC3211WrapEngine;
import co.org.bouncy.crypto.generators.DESKeyGenerator;
import co.org.bouncy.crypto.macs.*;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.paddings.ISO7816d4Padding;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseWrapCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.PBE;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.security.*;
import java.security.spec.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;

public final class DES
{
    public static class Mappings extends AlgorithmProvider
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


        public Mappings()
        {
        }
    }

    public static class PBEWithSHA1 extends BaseBlockCipher
    {

        public PBEWithSHA1()
        {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithMD5 extends BaseBlockCipher
    {

        public PBEWithMD5()
        {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithMD2 extends BaseBlockCipher
    {

        public PBEWithMD2()
        {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithSHA1KeyFactory extends DESPBEKeyFactory
    {

        public PBEWithSHA1KeyFactory()
        {
            super("PBEwithSHA1andDES", PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, true, 0, 1, 64, 64);
        }
    }

    public static class PBEWithMD5KeyFactory extends DESPBEKeyFactory
    {

        public PBEWithMD5KeyFactory()
        {
            super("PBEwithMD5andDES", PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, true, 0, 0, 64, 64);
        }
    }

    public static class PBEWithMD2KeyFactory extends DESPBEKeyFactory
    {

        public PBEWithMD2KeyFactory()
        {
            super("PBEwithMD2andDES", PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, true, 0, 5, 64, 64);
        }
    }

    public static class DESPBEKeyFactory extends BaseSecretKeyFactory
    {

        protected SecretKey engineGenerateSecret(KeySpec keySpec)
            throws InvalidKeySpecException
        {
            if(keySpec instanceof PBEKeySpec)
            {
                PBEKeySpec pbeSpec = (PBEKeySpec)keySpec;
                if(pbeSpec.getSalt() == null)
                    return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, null);
                CipherParameters param;
                if(forCipher)
                    param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(pbeSpec, scheme, digest, keySize, ivSize);
                else
                    param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEMacParameters(pbeSpec, scheme, digest, keySize);
                KeyParameter kParam;
                if(param instanceof ParametersWithIV)
                    kParam = (KeyParameter)((ParametersWithIV)param).getParameters();
                else
                    kParam = (KeyParameter)param;
                DESParameters.setOddParity(kParam.getKey());
                return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, param);
            } else
            {
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }

        private boolean forCipher;
        private int scheme;
        private int digest;
        private int keySize;
        private int ivSize;

        public DESPBEKeyFactory(String algorithm, ASN1ObjectIdentifier oid, boolean forCipher, int scheme, int digest, int keySize, int ivSize)
        {
            super(algorithm, oid);
            this.forCipher = forCipher;
            this.scheme = scheme;
            this.digest = digest;
            this.keySize = keySize;
            this.ivSize = ivSize;
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
            if(javax/crypto/spec/DESKeySpec.isAssignableFrom(keySpec))
            {
                byte bytes[] = key.getEncoded();
                try
                {
                    return new DESKeySpec(bytes);
                }
                catch(Exception e)
                {
                    throw new InvalidKeySpecException(e.toString());
                }
            } else
            {
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec)
            throws InvalidKeySpecException
        {
            if(keySpec instanceof DESKeySpec)
            {
                DESKeySpec desKeySpec = (DESKeySpec)keySpec;
                return new SecretKeySpec(desKeySpec.getKey(), "DES");
            } else
            {
                return super.engineGenerateSecret(keySpec);
            }
        }

        public KeyFactory()
        {
            super("DES", null);
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        protected void engineInit(int keySize, SecureRandom random)
        {
            super.engineInit(keySize, random);
        }

        protected SecretKey engineGenerateKey()
        {
            if(uninitialised)
            {
                engine.init(new KeyGenerationParameters(new SecureRandom(), defaultKeySize));
                uninitialised = false;
            }
            return new SecretKeySpec(engine.generateKey(), algName);
        }

        public KeyGenerator()
        {
            super("DES", 64, new DESKeyGenerator());
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

    public static class RFC3211 extends BaseWrapCipher
    {

        public RFC3211()
        {
            super(new RFC3211WrapEngine(new DESEngine()), 8);
        }
    }

    public static class DES9797Alg3 extends BaseMac
    {

        public DES9797Alg3()
        {
            super(new ISO9797Alg3Mac(new DESEngine()));
        }
    }

    public static class DES9797Alg3with7816d4 extends BaseMac
    {

        public DES9797Alg3with7816d4()
        {
            super(new ISO9797Alg3Mac(new DESEngine(), new ISO7816d4Padding()));
        }
    }

    public static class CMAC extends BaseMac
    {

        public CMAC()
        {
            super(new CMac(new DESEngine()));
        }
    }

    public static class CBCMAC extends BaseMac
    {

        public CBCMAC()
        {
            super(new CBCBlockCipherMac(new DESEngine()));
        }
    }

    public static class DES64with7816d4 extends BaseMac
    {

        public DES64with7816d4()
        {
            super(new CBCBlockCipherMac(new DESEngine(), 64, new ISO7816d4Padding()));
        }
    }

    public static class DES64 extends BaseMac
    {

        public DES64()
        {
            super(new CBCBlockCipherMac(new DESEngine(), 64));
        }
    }

    public static class DESCFB8 extends BaseMac
    {

        public DESCFB8()
        {
            super(new CFBBlockCipherMac(new DESEngine()));
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new DESEngine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new DESEngine());
        }
    }


    private DES()
    {
    }
}
