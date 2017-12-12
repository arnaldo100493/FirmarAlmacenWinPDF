// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BC.java

package co.org.bouncy.jcajce.provider.keystore;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class BC
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyStore.BKS", "co.org.bouncy.jcajce.provider.keystore.bc.BcKeyStoreSpi$Std");
            provider.addAlgorithm("KeyStore.BKS-V1", "co.org.bouncy.jcajce.provider.keystore.bc.BcKeyStoreSpi$Version1");
            provider.addAlgorithm("KeyStore.BouncyCastle", "co.org.bouncy.jcajce.provider.keystore.bc.BcKeyStoreSpi$BouncyCastleStore");
            provider.addAlgorithm("Alg.Alias.KeyStore.UBER", "BouncyCastle");
            provider.addAlgorithm("Alg.Alias.KeyStore.BOUNCYCASTLE", "BouncyCastle");
            provider.addAlgorithm("Alg.Alias.KeyStore.bouncycastle", "BouncyCastle");
        }

        public Mappings()
        {
        }
    }


    public BC()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.keystore.bc.";
}
