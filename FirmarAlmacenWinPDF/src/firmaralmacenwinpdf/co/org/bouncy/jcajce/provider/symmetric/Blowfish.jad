// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Blowfish.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.BlowfishEngine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class Blowfish
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("Cipher.1.3.6.1.4.1.3029.1.2", (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("KeyGenerator.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("Alg.Alias.KeyGenerator.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
            provider.addAlgorithm("AlgorithmParameters.BLOWFISH", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Blowfish.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Blowfish IV";
        }

        public AlgParams()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("Blowfish", 128, new CipherKeyGenerator());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new BlowfishEngine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlowfishEngine());
        }
    }


    private Blowfish()
    {
    }
}
