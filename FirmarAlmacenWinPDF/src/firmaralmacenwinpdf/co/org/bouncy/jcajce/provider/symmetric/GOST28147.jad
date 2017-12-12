// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST28147.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.GOST28147Engine;
import co.org.bouncy.crypto.macs.GOST28147Mac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;

public final class GOST28147
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.GOST28147", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("Alg.Alias.Cipher.GOST", "GOST28147");
            provider.addAlgorithm("Alg.Alias.Cipher.GOST-28147", "GOST28147");
            provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(CryptoProObjectIdentifiers.gostR28147_cbc).toString(), (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("KeyGenerator.GOST28147", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("Alg.Alias.KeyGenerator.GOST", "GOST28147");
            provider.addAlgorithm("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.KeyGenerator.").append(CryptoProObjectIdentifiers.gostR28147_cbc).toString(), "GOST28147");
            provider.addAlgorithm("Mac.GOST28147MAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
            provider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/GOST28147.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "GOST IV";
        }

        public AlgParams()
        {
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters()
        {
            byte iv[] = new byte[16];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            AlgorithmParameters params;
            try
            {
                params = AlgorithmParameters.getInstance("GOST28147", "BC");
                params.init(new IvParameterSpec(iv));
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
            return params;
        }

        public AlgParamGen()
        {
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            this(256);
        }

        public KeyGen(int keySize)
        {
            super("GOST28147", keySize, new CipherKeyGenerator());
        }
    }

    public static class Mac extends BaseMac
    {

        public Mac()
        {
            super(new GOST28147Mac());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new GOST28147Engine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new GOST28147Engine());
        }
    }


    private GOST28147()
    {
    }
}
