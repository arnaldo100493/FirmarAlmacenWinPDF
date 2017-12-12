// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Camellia.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ntt.NTTObjectIdentifiers;
import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseWrapCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider

public final class Camellia
{
    public static class Mappings extends SymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm("AlgorithmParameterGenerator.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), "CAMELLIA");
            provider.addAlgorithm("Cipher.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("Cipher.CAMELLIARFC3211WRAP", (new StringBuilder()).append(PREFIX).append("$RFC3211Wrap").toString());
            provider.addAlgorithm("Cipher.CAMELLIAWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia128_wrap).toString(), "CAMELLIAWRAP");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia192_wrap).toString(), "CAMELLIAWRAP");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(NTTObjectIdentifiers.id_camellia256_wrap).toString(), "CAMELLIAWRAP");
            provider.addAlgorithm("KeyGenerator.CAMELLIA", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia128_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia192_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia256_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia128_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen128").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia192_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen192").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(NTTObjectIdentifiers.id_camellia256_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen256").toString());
            addGMacAlgorithm(provider, "CAMELLIA", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Camellia.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Camellia IV";
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
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for Camellia parameter generation.");
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
                params = AlgorithmParameters.getInstance("Camellia", "BC");
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
            this(256);
        }

        public KeyGen(int keySize)
        {
            super("Camellia", keySize, new CipherKeyGenerator());
        }
    }

    public static class GMAC extends BaseMac
    {

        public GMAC()
        {
            super(new GMac(new GCMBlockCipher(new CamelliaEngine())));
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher
    {

        public RFC3211Wrap()
        {
            super(new RFC3211WrapEngine(new CamelliaEngine()), 16);
        }
    }

    public static class Wrap extends BaseWrapCipher
    {

        public Wrap()
        {
            super(new CamelliaWrapEngine());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new CamelliaEngine()), 128);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new CamelliaEngine();
                }

            }
);
        }
    }


    private Camellia()
    {
    }
}
