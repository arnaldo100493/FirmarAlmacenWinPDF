// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Twofish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.TwofishEngine;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider

public final class Twofish
{
    public static class Mappings extends SymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.Twofish", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("KeyGenerator.Twofish", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("AlgorithmParameters.Twofish", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH-CBC", "PKCS12PBE");
            provider.addAlgorithm("Cipher.PBEWITHSHAANDTWOFISH-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAANDTWOFISH-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAKeyFactory").toString());
            addGMacAlgorithm(provider, "Twofish", (new StringBuilder()).append(PREFIX).append("$GMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Twofish.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Twofish IV";
        }

        public AlgParams()
        {
        }
    }

    public static class PBEWithSHA extends BaseBlockCipher
    {

        public PBEWithSHA()
        {
            super(new CBCBlockCipher(new TwofishEngine()));
        }
    }

    public static class PBEWithSHAKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHAKeyFactory()
        {
            super("PBEwithSHAandTwofish-CBC", null, true, 2, 1, 256, 128);
        }
    }

    public static class GMAC extends BaseMac
    {

        public GMAC()
        {
            super(new GMac(new GCMBlockCipher(new TwofishEngine())));
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("Twofish", 256, new CipherKeyGenerator());
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new TwofishEngine();
                }

            }
);
        }
    }


    private Twofish()
    {
    }
}
