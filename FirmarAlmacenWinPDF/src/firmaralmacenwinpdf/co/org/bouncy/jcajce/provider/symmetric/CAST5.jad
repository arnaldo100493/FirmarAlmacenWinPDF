// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAST5.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.misc.CAST5CBCParameters;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.CAST5Engine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.io.IOException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

public final class CAST5
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.CAST5", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113533.7.66.10", "CAST5");
            provider.addAlgorithm("AlgorithmParameterGenerator.CAST5", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.1.2.840.113533.7.66.10", "CAST5");
            provider.addAlgorithm("Cipher.CAST5", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("Cipher.1.2.840.113533.7.66.10", (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("KeyGenerator.CAST5", (new StringBuilder()).append(PREFIX).append("$KeyGen").toString());
            provider.addAlgorithm("Alg.Alias.KeyGenerator.1.2.840.113533.7.66.10", "CAST5");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/CAST5.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters
    {

        protected byte[] engineGetEncoded()
        {
            byte tmp[] = new byte[iv.length];
            System.arraycopy(iv, 0, tmp, 0, iv.length);
            return tmp;
        }

        protected byte[] engineGetEncoded(String format)
            throws IOException
        {
            if(isASN1FormatString(format))
                return (new CAST5CBCParameters(engineGetEncoded(), keyLength)).getEncoded();
            if(format.equals("RAW"))
                return engineGetEncoded();
            else
                return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec == javax/crypto/spec/IvParameterSpec)
                return new IvParameterSpec(iv);
            else
                throw new InvalidParameterSpecException("unknown parameter spec passed to CAST5 parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec instanceof IvParameterSpec)
                iv = ((IvParameterSpec)paramSpec).getIV();
            else
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a CAST5 parameters algorithm parameters object");
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
            if(isASN1FormatString(format))
            {
                ASN1InputStream aIn = new ASN1InputStream(params);
                CAST5CBCParameters p = CAST5CBCParameters.getInstance(aIn.readObject());
                keyLength = p.getKeyLength();
                iv = p.getIV();
                return;
            }
            if(format.equals("RAW"))
            {
                engineInit(params);
                return;
            } else
            {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        protected String engineToString()
        {
            return "CAST5 Parameters";
        }

        private byte iv[];
        private int keyLength;

        public AlgParams()
        {
            keyLength = 128;
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for CAST5 parameter generation.");
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
                params = AlgorithmParameters.getInstance("CAST5", "BC");
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
            super("CAST5", 128, new CipherKeyGenerator());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new CAST5Engine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new CAST5Engine());
        }
    }


    private CAST5()
    {
    }
}
