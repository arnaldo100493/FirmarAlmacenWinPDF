// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ARC4.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.RC4Engine;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseStreamCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;

public final class ARC4
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.ARC4", (new StringBuilder()).append(PREFIX).append("$Base").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.rc4).toString(), "ARC4");
            provider.addAlgorithm("Alg.Alias.Cipher.ARCFOUR", "ARC4");
            provider.addAlgorithm("Alg.Alias.Cipher.RC4", "ARC4");
            provider.addAlgorithm("KeyGenerator.ARC4", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("Alg.Alias.KeyGenerator.RC4", "ARC4");
            provider.addAlgorithm("Alg.Alias.KeyGenerator.1.2.840.113549.3.4", "ARC4");
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND128BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128BitKeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND40BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40BitKeyFactory").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PKCS12PBE");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.AlgorithmParameters.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC4", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC4", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC4", "PKCS12PBE");
            provider.addAlgorithm("Cipher.PBEWITHSHAAND128BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128Bit").toString());
            provider.addAlgorithm("Cipher.PBEWITHSHAAND40BITRC4", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40Bit").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PBEWITHSHAAND128BITRC4");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PBEWITHSHAAND40BITRC4");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC4", "PBEWITHSHAAND128BITRC4");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC4", "PBEWITHSHAAND40BITRC4");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4).toString(), "PBEWITHSHAAND128BITRC4");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4).toString(), "PBEWITHSHAAND40BITRC4");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/ARC4.getName();


        public Mappings()
        {
        }
    }

    public static class PBEWithSHAAnd40Bit extends BaseStreamCipher
    {

        public PBEWithSHAAnd40Bit()
        {
            super(new RC4Engine(), 0);
        }
    }

    public static class PBEWithSHAAnd128Bit extends BaseStreamCipher
    {

        public PBEWithSHAAnd128Bit()
        {
            super(new RC4Engine(), 0);
        }
    }

    public static class PBEWithSHAAnd40BitKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd40BitKeyFactory()
        {
            super("PBEWithSHAAnd128BitRC4", PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4, true, 2, 1, 40, 0);
        }
    }

    public static class PBEWithSHAAnd128BitKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd128BitKeyFactory()
        {
            super("PBEWithSHAAnd128BitRC4", PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4, true, 2, 1, 128, 0);
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("RC4", 128, new CipherKeyGenerator());
        }
    }

    public static class Base extends BaseStreamCipher
    {

        public Base()
        {
            super(new RC4Engine(), 0);
        }
    }


    private ARC4()
    {
    }
}
