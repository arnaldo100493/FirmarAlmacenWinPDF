// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tiger.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.TigerDigest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class Tiger
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.TIGER", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm("MessageDigest.Tiger", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            addHMACAlgorithm(provider, "TIGER", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            addHMACAlias(provider, "TIGER", IANAObjectIdentifiers.hmacTIGER);
            provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACTIGER", (new StringBuilder()).append(PREFIX).append("$PBEWithMacKeyFactory").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/Tiger.getName();


        public Mappings()
        {
        }
    }

    public static class PBEWithHashMac extends BaseMac
    {

        public PBEWithHashMac()
        {
            super(new HMac(new TigerDigest()), 2, 3, 192);
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithMacKeyFactory()
        {
            super("PBEwithHmacTiger", null, false, 2, 3, 192, 0);
        }
    }

    public static class TigerHmac extends BaseMac
    {

        public TigerHmac()
        {
            super(new HMac(new TigerDigest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACTIGER", 192, new CipherKeyGenerator());
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new TigerDigest()));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new TigerDigest((TigerDigest)digest);
            return d;
        }

        public Digest()
        {
            super(new TigerDigest());
        }
    }


    private Tiger()
    {
    }
}
