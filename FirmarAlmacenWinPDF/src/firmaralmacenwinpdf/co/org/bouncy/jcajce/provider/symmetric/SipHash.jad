// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SipHash.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class SipHash
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Mac.SIPHASH", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
            provider.addAlgorithm("Alg.Alias.Mac.SIPHASH-2-4", "SIPHASH");
            provider.addAlgorithm("Mac.SIPHASH-4-8", (new StringBuilder()).append(PREFIX).append("$Mac48").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/SipHash.getName();


        public Mappings()
        {
        }
    }

    public static class Mac48 extends BaseMac
    {

        public Mac48()
        {
            super(new co.org.bouncy.crypto.macs.SipHash(4, 8));
        }
    }

    public static class Mac extends BaseMac
    {

        public Mac()
        {
            super(new co.org.bouncy.crypto.macs.SipHash());
        }
    }


    private SipHash()
    {
    }
}
