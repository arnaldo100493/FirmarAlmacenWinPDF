// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.misc.IDEACBCPar;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.IDEAEngine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.macs.CFBBlockCipherMac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.io.IOException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

public final class IDEA
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameterGenerator.IDEA", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm("AlgorithmParameterGenerator.1.3.6.1.4.1.188.7.1.1.2", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm("AlgorithmParameters.IDEA", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("AlgorithmParameters.1.3.6.1.4.1.188.7.1.1.2", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA-CBC", "PKCS12PBE");
            provider.addAlgorithm("Cipher.IDEA", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("Cipher.1.3.6.1.4.1.188.7.1.1.2", (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("Cipher.PBEWITHSHAANDIDEA-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndIDEA").toString());
            provider.addAlgorithm("KeyGenerator.IDEA", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("KeyGenerator.1.3.6.1.4.1.188.7.1.1.2", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAANDIDEA-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAndIDEAKeyGen").toString());
            provider.addAlgorithm("Mac.IDEAMAC", (new StringBuilder()).append(PREFIX).append("$Mac").toString());
            provider.addAlgorithm("Alg.Alias.Mac.IDEA", "IDEAMAC");
            provider.addAlgorithm("Mac.IDEAMAC/CFB8", (new StringBuilder()).append(PREFIX).append("$CFB8Mac").toString());
            provider.addAlgorithm("Alg.Alias.Mac.IDEA/CFB8", "IDEAMAC/CFB8");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/IDEA.getName();


        public Mappings()
        {
        }
    }

    public static class CFB8Mac extends BaseMac
    {

        public CFB8Mac()
        {
            super(new CFBBlockCipherMac(new IDEAEngine()));
        }
    }

    public static class Mac extends BaseMac
    {

        public Mac()
        {
            super(new CBCBlockCipherMac(new IDEAEngine()));
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters
    {

        protected byte[] engineGetEncoded()
            throws IOException
        {
            return engineGetEncoded("ASN.1");
        }

        protected byte[] engineGetEncoded(String format)
            throws IOException
        {
            if(isASN1FormatString(format))
                return (new IDEACBCPar(engineGetEncoded("RAW"))).getEncoded();
            if(format.equals("RAW"))
            {
                byte tmp[] = new byte[iv.length];
                System.arraycopy(iv, 0, tmp, 0, iv.length);
                return tmp;
            } else
            {
                return null;
            }
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec == javax/crypto/spec/IvParameterSpec)
                return new IvParameterSpec(iv);
            else
                throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException
        {
            if(!(paramSpec instanceof IvParameterSpec))
            {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            } else
            {
                iv = ((IvParameterSpec)paramSpec).getIV();
                return;
            }
        }

        protected void engineInit(byte params[])
            throws IOException
        {
            iv = new byte[params.length];
            System.arraycopy(params, 0, iv, 0, iv.length);
        }

        protected void engineInit(byte params[], String format)
            throws IOException
        {
            if(format.equals("RAW"))
            {
                engineInit(params);
                return;
            }
            if(format.equals("ASN.1"))
            {
                ASN1InputStream aIn = new ASN1InputStream(params);
                IDEACBCPar oct = new IDEACBCPar((ASN1Sequence)aIn.readObject());
                engineInit(oct.getIV());
                return;
            } else
            {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        protected String engineToString()
        {
            return "IDEA Parameters";
        }

        private byte iv[];

        public AlgParams()
        {
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for IDEA parameter generation.");
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
                params = AlgorithmParameters.getInstance("IDEA", "BC");
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

    public static class PBEWithSHAAndIDEA extends BaseBlockCipher
    {

        public PBEWithSHAAndIDEA()
        {
            super(new CBCBlockCipher(new IDEAEngine()));
        }
    }

    public static class PBEWithSHAAndIDEAKeyGen extends PBESecretKeyFactory
    {

        public PBEWithSHAAndIDEAKeyGen()
        {
            super("PBEwithSHAandIDEA-CBC", null, true, 2, 1, 128, 64);
        }
    }

    public static class KeyGen extends BaseKeyGenerator
    {

        public KeyGen()
        {
            super("IDEA", 128, new CipherKeyGenerator());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new IDEAEngine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new IDEAEngine());
        }
    }


    private IDEA()
    {
    }
}
