// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3411.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.GOST3411Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class GOST3411
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.GOST3411", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm("Alg.Alias.MessageDigest.GOST", "GOST3411");
            provider.addAlgorithm("Alg.Alias.MessageDigest.GOST-3411", "GOST3411");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(CryptoProObjectIdentifiers.gostR3411).toString(), "GOST3411");
            addHMACAlgorithm(provider, "GOST3411", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            addHMACAlias(provider, "GOST3411", CryptoProObjectIdentifiers.gostR3411);
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/GOST3411.getName();


        public Mappings()
        {
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACGOST3411", 256, new CipherKeyGenerator());
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new GOST3411Digest()));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new GOST3411Digest((GOST3411Digest)digest);
            return d;
        }

        public Digest()
        {
            super(new GOST3411Digest());
        }
    }


    private GOST3411()
    {
    }
}
