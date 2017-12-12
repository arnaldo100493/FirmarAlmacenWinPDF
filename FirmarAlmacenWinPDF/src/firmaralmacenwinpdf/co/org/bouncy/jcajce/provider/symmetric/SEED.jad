// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SEED.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.kisa.KISAObjectIdentifiers;
import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.SEEDEngine;
import co.org.bouncy.crypto.engines.SEEDWrapEngine;
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

public final class SEED
{
    public static class Mappings extends SymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.SEED", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(KISAObjectIdentifiers.id_seedCBC).toString(), "SEED");
            provider.addAlgorithm("AlgorithmParameterGenerator.SEED", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameterGenerator.").append(KISAObjectIdentifiers.id_seedCBC).toString(), "SEED");
            provider.addAlgorithm("Cipher.SEED", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(KISAObjectIdentifiers.id_seedCBC).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("Cipher.SEEDWRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap).toString(), "SEEDWRAP");
            provider.addAlgorithm("KeyGenerator.SEED", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(KISAObjectIdentifiers.id_seedCBC).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm((new StringBuilder()).append("KeyGenerator.").append(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap).toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            addGMacAlgorithm(provider, "SEED", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/SEED.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "SEED IV";
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
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for SEED parameter generation.");
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
                params = AlgorithmParameters.getInstance("SEED", "BC");
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

    public static class GMAC extends BaseMac
    {

        public GMAC()
        {
            super(new GMac(new GCMBlockCipher(new SEEDEngine())));
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("SEED", 128, new CipherKeyGenerator());
        }
    }

    public static class Wrap extends BaseWrapCipher
    {

        public Wrap()
        {
            super(new SEEDWrapEngine());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new SEEDEngine()), 128);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new SEEDEngine();
                }

            }
);
        }
    }


    private SEED()
    {
    }
}
