// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12.java

package co.org.bouncy.jcajce.provider.keystore;

import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class PKCS12
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyStore.PKCS12", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$BCPKCS12KeyStore");
            provider.addAlgorithm("KeyStore.BCPKCS12", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$BCPKCS12KeyStore");
            provider.addAlgorithm("KeyStore.PKCS12-DEF", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$DefPKCS12KeyStore");
            provider.addAlgorithm("KeyStore.PKCS12-3DES-40RC2", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$BCPKCS12KeyStore");
            provider.addAlgorithm("KeyStore.PKCS12-3DES-3DES", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$BCPKCS12KeyStore3DES");
            provider.addAlgorithm("KeyStore.PKCS12-DEF-3DES-40RC2", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$DefPKCS12KeyStore");
            provider.addAlgorithm("KeyStore.PKCS12-DEF-3DES-3DES", "co.org.bouncy.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$DefPKCS12KeyStore3DES");
        }

        public Mappings()
        {
        }
    }


    public PKCS12()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.keystore.pkcs12.";
}
