// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rijndael.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.RijndaelEngine;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BlockCipherProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class Rijndael
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.RIJNDAEL", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("KeyGenerator.RIJNDAEL", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("AlgorithmParameters.RIJNDAEL", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Rijndael.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Rijndael IV";
        }

        public AlgParams()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("Rijndael", 192, new CipherKeyGenerator());
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new BlockCipherProvider() {

                public BlockCipher get()
                {
                    return new RijndaelEngine();
                }

            }
);
        }
    }


    private Rijndael()
    {
    }
}
