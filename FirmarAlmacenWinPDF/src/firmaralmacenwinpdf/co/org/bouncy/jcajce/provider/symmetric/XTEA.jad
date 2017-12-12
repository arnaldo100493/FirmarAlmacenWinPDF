// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XTEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.XTEAEngine;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class XTEA
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.XTEA", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("KeyGenerator.XTEA", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("AlgorithmParameters.XTEA", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/XTEA.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "XTEA IV";
        }

        public AlgParams()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("XTEA", 128, new CipherKeyGenerator());
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new XTEAEngine());
        }
    }


    private XTEA()
    {
    }
}
