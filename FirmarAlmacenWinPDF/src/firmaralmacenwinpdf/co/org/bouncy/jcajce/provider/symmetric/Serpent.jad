// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serpent.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.SerpentEngine;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            SymmetricAlgorithmProvider

public final class Serpent
{
    public static class Mappings extends SymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.Serpent", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("KeyGenerator.Serpent", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("AlgorithmParameters.Serpent", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            addGMacAlgorithm(provider, "SERPENT", (new StringBuilder()).append(PREFIX).append("$SerpentGMAC").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Serpent.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Serpent IV";
        }

        public AlgParams()
        {
        }
    }

    public static class SerpentGMAC extends BaseMac
    {

        public SerpentGMAC()
        {
            super(new GMac(new GCMBlockCipher(new SerpentEngine())));
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("Serpent", 192, new CipherKeyGenerator());
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new SerpentEngine();
                }

            }
);
        }
    }


    private Serpent()
    {
    }
}
