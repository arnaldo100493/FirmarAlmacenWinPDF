// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEPBKDF2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.PBKDF2Params;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            PBEPBKDF2

public static class PBEPBKDF2$AlgParams extends BaseAlgorithmParameters
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
            return new PBEParameterSpec(params.getSalt(), params.getIterationCount().intValue());
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF2 PBE parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof PBEParameterSpec))
        {
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF2 PBE parameters algorithm parameters object");
        } else
        {
            PBEParameterSpec pbeSpec = (PBEParameterSpec)paramSpec;
            params = new PBKDF2Params(pbeSpec.getSalt(), pbeSpec.getIterationCount());
            return;
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        this.params = PBKDF2Params.getInstance(ASN1Primitive.fromByteArray(params));
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
            throw new IOException("Unknown parameters format in PBKDF2 parameters object");
        }
    }

    protected String engineToString()
    {
        return "PBKDF2 Parameters";
    }

    PBKDF2Params params;

    public PBEPBKDF2$AlgParams()
    {
    }
}
