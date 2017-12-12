// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class X509
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyFactory.X.509", "co.org.bouncy.jcajce.provider.asymmetric.x509.KeyFactory");
            provider.addAlgorithm("Alg.Alias.KeyFactory.X509", "X.509");
            provider.addAlgorithm("CertificateFactory.X.509", "co.org.bouncy.jcajce.provider.asymmetric.x509.CertificateFactory");
            provider.addAlgorithm("Alg.Alias.CertificateFactory.X509", "X.509");
        }

        public Mappings()
        {
        }
    }


    public X509()
    {
    }
}
