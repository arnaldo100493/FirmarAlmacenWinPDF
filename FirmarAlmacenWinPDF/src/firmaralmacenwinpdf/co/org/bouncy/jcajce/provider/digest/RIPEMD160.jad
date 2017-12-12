// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RIPEMD160.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.RIPEMD160Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class RIPEMD160
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.RIPEMD160", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(TeleTrusTObjectIdentifiers.ripemd160).toString(), "RIPEMD160");
            addHMACAlgorithm(provider, "RIPEMD160", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            addHMACAlias(provider, "RIPEMD160", IANAObjectIdentifiers.hmacRIPEMD160);
            provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACRIPEMD160", (new StringBuilder()).append(PREFIX).append("$PBEWithHmacKeyFactory").toString());
            provider.addAlgorithm("Mac.PBEWITHHMACRIPEMD160", (new StringBuilder()).append(PREFIX).append("$PBEWithHmac").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/RIPEMD160.getName();


        public Mappings()
        {
        }
    }

    public static class PBEWithHmacKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithHmacKeyFactory()
        {
            super("PBEwithHmacRIPEMD160", null, false, 2, 2, 160, 0);
        }
    }

    public static class PBEWithHmac extends BaseMac
    {

        public PBEWithHmac()
        {
            super(new HMac(new RIPEMD160Digest()), 2, 2, 160);
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACRIPEMD160", 160, new CipherKeyGenerator());
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new RIPEMD160Digest()));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new RIPEMD160Digest((RIPEMD160Digest)digest);
            return d;
        }

        public Digest()
        {
            super(new RIPEMD160Digest());
        }
    }


    private RIPEMD160()
    {
    }
}
