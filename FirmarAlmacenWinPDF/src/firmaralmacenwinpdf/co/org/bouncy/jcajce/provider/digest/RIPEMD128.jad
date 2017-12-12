// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD128.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.RIPEMD128Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class RIPEMD128
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.RIPEMD128", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(TeleTrusTObjectIdentifiers.ripemd128).toString(), "RIPEMD128");
            addHMACAlgorithm(provider, "RIPEMD128", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/RIPEMD128.getName();


        public Mappings()
        {
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACRIPEMD128", 128, new CipherKeyGenerator());
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new RIPEMD128Digest()));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new RIPEMD128Digest((RIPEMD128Digest)digest);
            return d;
        }

        public Digest()
        {
            super(new RIPEMD128Digest());
        }
    }


    private RIPEMD128()
    {
    }
}
