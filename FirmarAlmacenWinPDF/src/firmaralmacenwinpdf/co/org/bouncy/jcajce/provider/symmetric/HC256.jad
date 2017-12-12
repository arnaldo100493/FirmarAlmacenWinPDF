// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HC256.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.HC256Engine;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseStreamCipher;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class HC256
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.HC256", (new StringBuilder()).append(PREFIX).append("$Base").toString());
            provider.addAlgorithm("KeyGenerator.HC256", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/HC256.getName();


        public Mappings()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("HC256", 256, new CipherKeyGenerator());
        }
    }

    public static class Base extends BaseStreamCipher
    {

        public Base()
        {
            super(new HC256Engine(), 32);
        }
    }


    private HC256()
    {
    }
}
