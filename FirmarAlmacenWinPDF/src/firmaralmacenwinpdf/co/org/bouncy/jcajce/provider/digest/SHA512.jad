// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA512.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.SHA512Digest;
import co.org.bouncy.crypto.digests.SHA512tDigest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.macs.OldHMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class SHA512
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.SHA-512", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512", "SHA-512");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512).toString(), "SHA-512");
            provider.addAlgorithm("MessageDigest.SHA-512/224", (new StringBuilder()).append(PREFIX).append("$DigestT224").toString());
            provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512/224", "SHA-512/224");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512_224).toString(), "SHA-512/224");
            provider.addAlgorithm("MessageDigest.SHA-512/256", (new StringBuilder()).append(PREFIX).append("$DigestT256").toString());
            provider.addAlgorithm("Alg.Alias.MessageDigest.SHA512256", "SHA-512/256");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(NISTObjectIdentifiers.id_sha512_256).toString(), "SHA-512/256");
            provider.addAlgorithm("Mac.OLDHMACSHA512", (new StringBuilder()).append(PREFIX).append("$OldSHA512").toString());
            addHMACAlgorithm(provider, "SHA512", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            addHMACAlias(provider, "SHA512", PKCSObjectIdentifiers.id_hmacWithSHA512);
            addHMACAlgorithm(provider, "SHA512/224", (new StringBuilder()).append(PREFIX).append("$HashMacT224").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGeneratorT224").toString());
            addHMACAlgorithm(provider, "SHA512/256", (new StringBuilder()).append(PREFIX).append("$HashMacT256").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGeneratorT256").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA512.getName();


        public Mappings()
        {
        }
    }

    public static class KeyGeneratorT256 extends BaseKeyGenerator
    {

        public KeyGeneratorT256()
        {
            super("HMACSHA512/256", 256, new CipherKeyGenerator());
        }
    }

    public static class KeyGeneratorT224 extends BaseKeyGenerator
    {

        public KeyGeneratorT224()
        {
            super("HMACSHA512/224", 224, new CipherKeyGenerator());
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACSHA512", 512, new CipherKeyGenerator());
        }
    }

    public static class OldSHA512 extends BaseMac
    {

        public OldSHA512()
        {
            super(new OldHMac(new SHA512Digest()));
        }
    }

    public static class HashMacT256 extends BaseMac
    {

        public HashMacT256()
        {
            super(new HMac(new SHA512tDigest(256)));
        }
    }

    public static class HashMacT224 extends BaseMac
    {

        public HashMacT224()
        {
            super(new HMac(new SHA512tDigest(224)));
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new SHA512Digest()));
        }
    }

    public static class DigestT256 extends DigestT
    {

        public DigestT256()
        {
            super(256);
        }
    }

    public static class DigestT224 extends DigestT
    {

        public DigestT224()
        {
            super(224);
        }
    }

    public static class DigestT extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            DigestT d = (DigestT)super.clone();
            d.digest = new SHA512tDigest((SHA512tDigest)digest);
            return d;
        }

        public DigestT(int bitLength)
        {
            super(new SHA512tDigest(bitLength));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new SHA512Digest((SHA512Digest)digest);
            return d;
        }

        public Digest()
        {
            super(new SHA512Digest());
        }
    }


    private SHA512()
    {
    }
}
