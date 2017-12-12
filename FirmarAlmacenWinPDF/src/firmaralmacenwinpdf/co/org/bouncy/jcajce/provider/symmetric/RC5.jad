// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC5.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.RC532Engine;
import co.org.bouncy.crypto.engines.RC564Engine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.macs.CFBBlockCipherMac;
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

public final class RC5
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("Cipher.RC5", (new StringBuilder()).append(PREFIX).append("$ECB32").toString());
            provider.addAlgorithm("Alg.Alias.Cipher.RC5-32", "RC5");
            provider.addAlgorithm("Cipher.RC5-64", (new StringBuilder()).append(PREFIX).append("$ECB64").toString());
            provider.addAlgorithm("KeyGenerator.RC5", (new StringBuilder()).append(PREFIX).append("$KeyGen32").toString());
            provider.addAlgorithm("Alg.Alias.KeyGenerator.RC5-32", "RC5");
            provider.addAlgorithm("KeyGenerator.RC5-64", (new StringBuilder()).append(PREFIX).append("$KeyGen64").toString());
            provider.addAlgorithm("AlgorithmParameters.RC5", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("AlgorithmParameters.RC5-64", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Mac.RC5MAC", (new StringBuilder()).append(PREFIX).append("$Mac32").toString());
            provider.addAlgorithm("Alg.Alias.Mac.RC5", "RC5MAC");
            provider.addAlgorithm("Mac.RC5MAC/CFB8", (new StringBuilder()).append(PREFIX).append("$CFB8Mac32").toString());
            provider.addAlgorithm("Alg.Alias.Mac.RC5/CFB8", "RC5MAC/CFB8");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/RC5.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends IvAlgorithmParameters
    {

        protected String engineToString()
        {
            return "RC5 IV";
        }

        public AlgParams()
        {
        }
    }

    public static class CFB8Mac32 extends BaseMac
    {

        public CFB8Mac32()
        {
            super(new CFBBlockCipherMac(new RC532Engine()));
        }
    }

    public static class Mac32 extends BaseMac
    {

        public Mac32()
        {
            super(new CBCBlockCipherMac(new RC532Engine()));
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC5 parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters()
        {
            byte iv[] = new byte[8];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            AlgorithmParameters params;
            try
            {
                params = AlgorithmParameters.getInstance("RC5", "BC");
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

    public static class KeyGen64 extends BaseKeyGenerator
    {

        public KeyGen64()
        {
            super("RC5-64", 256, new CipherKeyGenerator());
        }
    }

    public static class KeyGen32 extends BaseKeyGenerator
    {

        public KeyGen32()
        {
            super("RC5", 128, new CipherKeyGenerator());
        }
    }

    public static class CBC32 extends BaseBlockCipher
    {

        public CBC32()
        {
            super(new CBCBlockCipher(new RC532Engine()), 64);
        }
    }

    public static class ECB64 extends BaseBlockCipher
    {

        public ECB64()
        {
            super(new RC564Engine());
        }
    }

    public static class ECB32 extends BaseBlockCipher
    {

        public ECB32()
        {
            super(new RC532Engine());
        }
    }


    private RC5()
    {
    }
}
