// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Skipjack.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.SkipjackEngine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.macs.CFBBlockCipherMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class Skipjack
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("KeyGenerator.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("AlgorithmParameters.SKIPJACK", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Mac.SKIPJACKMAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
            provider.addAlgorithm("Alg.Alias.Mac.SKIPJACK", "SKIPJACKMAC");
            provider.addAlgorithm("Mac.SKIPJACKMAC/CFB8", (new StringBuilder()).append(PREFIX).append("$MacCFB8").toString());
            provider.addAlgorithm("Alg.Alias.Mac.SKIPJACK/CFB8", "SKIPJACKMAC/CFB8");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/Skipjack.getName();


        public Mappings()
        {
        }
    }

    public static class MacCFB8 extends BaseMac
    {

        public MacCFB8()
        {
            super(new CFBBlockCipherMac(new SkipjackEngine()));
        }
    }

    public static class Mac extends BaseMac
    {

        public Mac()
        {
            super(new CBCBlockCipherMac(new SkipjackEngine()));
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "Skipjack IV";
        }

        public AlgParams()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("Skipjack", 80, new CipherKeyGenerator());
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new SkipjackEngine());
        }
    }


    private Skipjack()
    {
    }
}
