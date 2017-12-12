// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEPKCS12.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;

public class PBEPKCS12
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameters.PKCS12PBE", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/PBEPKCS12.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters
    {

        protected byte[] engineGetEncoded()
        {
            try
            {
                return params.getEncoded("DER");
            }
            catch(IOException e)
            {
                throw new RuntimeException((new StringBuilder()).append("Oooops! ").append(e.toString()).toString());
            }
        }

        protected byte[] engineGetEncoded(String format)
        {
            if(isASN1FormatString(format))
                return engineGetEncoded();
            else
                return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec == javax/crypto/spec/PBEParameterSpec)
                return new PBEParameterSpec(params.getIV(), params.getIterations().intValue());
            else
                throw new InvalidParameterSpecException("unknown parameter spec passed to PKCS12 PBE parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException
        {
            if(!(paramSpec instanceof PBEParameterSpec))
            {
                throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PKCS12 PBE parameters algorithm parameters object");
            } else
            {
                PBEParameterSpec pbeSpec = (PBEParameterSpec)paramSpec;
                params = new PKCS12PBEParams(pbeSpec.getSalt(), pbeSpec.getIterationCount());
                return;
            }
        }

        protected void engineInit(byte params[])
            throws IOException
        {
            this.params = PKCS12PBEParams.getInstance(ASN1Primitive.fromByteArray(params));
        }

        protected void engineInit(byte params[], String format)
            throws IOException
        {
            if(isASN1FormatString(format))
            {
                engineInit(params);
                return;
            } else
            {
                throw new IOException("Unknown parameters format in PKCS12 PBE parameters object");
            }
        }

        protected String engineToString()
        {
            return "PKCS12 PBE Parameters";
        }

        PKCS12PBEParams params;

        public AlgParams()
        {
        }
    }


    private PBEPKCS12()
    {
    }
}
