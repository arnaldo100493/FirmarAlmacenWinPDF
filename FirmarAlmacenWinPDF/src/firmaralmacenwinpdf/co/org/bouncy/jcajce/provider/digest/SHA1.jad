// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.iana.IANAObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            DigestAlgorithmProvider, BCMessageDigest

public class SHA1
{
    public static class Mappings extends DigestAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("MessageDigest.SHA-1", (new StringBuilder()).append(PREFIX).append("$Digest").toString());
            provider.addAlgorithm("Alg.Alias.MessageDigest.SHA1", "SHA-1");
            provider.addAlgorithm("Alg.Alias.MessageDigest.SHA", "SHA-1");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.MessageDigest.").append(OIWObjectIdentifiers.idSHA1).toString(), "SHA-1");
            addHMACAlgorithm(provider, "SHA1", (new StringBuilder()).append(PREFIX).append("$HashMac").toString(), (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            addHMACAlias(provider, "SHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
            addHMACAlias(provider, "SHA1", IANAObjectIdentifiers.hmacSHA1);
            provider.addAlgorithm("Mac.PBEWITHHMACSHA", (new StringBuilder()).append(PREFIX).append("$SHA1Mac").toString());
            provider.addAlgorithm("Mac.PBEWITHHMACSHA1", (new StringBuilder()).append(PREFIX).append("$SHA1Mac").toString());
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA", "PBEWITHHMACSHA1");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(OIWObjectIdentifiers.idSHA1).toString(), "PBEWITHHMACSHA1");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Mac.").append(OIWObjectIdentifiers.idSHA1).toString(), "PBEWITHHMACSHA");
            provider.addAlgorithm("SecretKeyFactory.PBEWITHHMACSHA1", (new StringBuilder()).append(PREFIX).append("$PBEWithMacKeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBKDF2WithHmacSHA1", (new StringBuilder()).append(PREFIX).append("$PBKDF2WithHmacSHA1UTF8").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.id_PBKDF2).toString(), "PBKDF2WithHmacSHA1");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WithHmacSHA1AndUTF8", "PBKDF2WithHmacSHA1");
            provider.addAlgorithm("SecretKeyFactory.PBKDF2WithHmacSHA1And8BIT", (new StringBuilder()).append(PREFIX).append("$PBKDF2WithHmacSHA18BIT").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/digest/SHA1.getName();


        public Mappings()
        {
        }
    }

    public static class PBKDF2WithHmacSHA18BIT extends BasePBKDF2WithHmacSHA1
    {

        public PBKDF2WithHmacSHA18BIT()
        {
            super("PBKDF2WithHmacSHA1And8bit", 1);
        }
    }

    public static class PBKDF2WithHmacSHA1UTF8 extends BasePBKDF2WithHmacSHA1
    {

        public PBKDF2WithHmacSHA1UTF8()
        {
            super("PBKDF2WithHmacSHA1", 5);
        }
    }

    public static class BasePBKDF2WithHmacSHA1 extends BaseSecretKeyFactory
    {

        protected SecretKey engineGenerateSecret(KeySpec keySpec)
            throws InvalidKeySpecException
        {
            if(keySpec instanceof PBEKeySpec)
            {
                PBEKeySpec pbeSpec = (PBEKeySpec)keySpec;
                if(pbeSpec.getSalt() == null)
                    throw new InvalidKeySpecException("missing required salt");
                if(pbeSpec.getIterationCount() <= 0)
                    throw new InvalidKeySpecException((new StringBuilder()).append("positive iteration count required: ").append(pbeSpec.getIterationCount()).toString());
                if(pbeSpec.getKeyLength() <= 0)
                    throw new InvalidKeySpecException((new StringBuilder()).append("positive key length required: ").append(pbeSpec.getKeyLength()).toString());
                if(pbeSpec.getPassword().length == 0)
                {
                    throw new IllegalArgumentException("password empty");
                } else
                {
                    int digest = 1;
                    int keySize = pbeSpec.getKeyLength();
                    int ivSize = -1;
                    co.org.bouncy.crypto.CipherParameters param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEMacParameters(pbeSpec, scheme, digest, keySize);
                    return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, param);
                }
            } else
            {
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }

        private int scheme;

        public BasePBKDF2WithHmacSHA1(String name, int scheme)
        {
            super(name, PKCSObjectIdentifiers.id_PBKDF2);
            this.scheme = scheme;
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithMacKeyFactory()
        {
            super("PBEwithHmacSHA", null, false, 2, 1, 160, 0);
        }
    }

    public static class SHA1Mac extends BaseMac
    {

        public SHA1Mac()
        {
            super(new HMac(new SHA1Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("HMACSHA1", 160, new CipherKeyGenerator());
        }
    }

    public static class HashMac extends BaseMac
    {

        public HashMac()
        {
            super(new HMac(new SHA1Digest()));
        }
    }

    public static class Digest extends BCMessageDigest
        implements Cloneable
    {

        public Object clone()
            throws CloneNotSupportedException
        {
            Digest d = (Digest)super.clone();
            d.digest = new SHA1Digest((SHA1Digest)digest);
            return d;
        }

        public Digest()
        {
            super(new SHA1Digest());
        }
    }


    private SHA1()
    {
    }
}
