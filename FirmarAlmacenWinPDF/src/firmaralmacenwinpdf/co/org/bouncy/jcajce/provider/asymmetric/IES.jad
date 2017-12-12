// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IES.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class IES
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.IES", "co.org.bouncy.jcajce.provider.asymmetric.ies.AlgorithmParametersSpi");
            provider.addAlgorithm("Cipher.IES", "co.org.bouncy.jcajce.provider.asymmetric.ies.CipherSpi$IES");
        }

        public Mappings()
        {
        }
    }


    public IES()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.ies.";
}
