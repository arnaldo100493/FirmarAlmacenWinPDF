// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParametersSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;

public class AlgorithmParametersSpi extends BaseAlgorithmParameters
{

    public AlgorithmParametersSpi()
    {
    }

    protected byte[] engineGetEncoded()
    {
        ElGamalParameter elP = new ElGamalParameter(currentSpec.getP(), currentSpec.getG());
        try
        {
            return elP.getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error encoding ElGamalParameters");
        }
    }

    protected byte[] engineGetEncoded(String format)
    {
        if(isASN1FormatString(format) || format.equalsIgnoreCase("X.509"))
            return engineGetEncoded();
        else
            return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
        throws InvalidParameterSpecException
    {
        if(paramSpec == co/org/bouncy/jce/spec/ElGamalParameterSpec)
            return currentSpec;
        if(paramSpec == javax/crypto/spec/DHParameterSpec)
            return new DHParameterSpec(currentSpec.getP(), currentSpec.getG());
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof ElGamalParameterSpec) && !(paramSpec instanceof DHParameterSpec))
            throw new InvalidParameterSpecException("DHParameterSpec required to initialise a ElGamal algorithm parameters object");
        if(paramSpec instanceof ElGamalParameterSpec)
        {
            currentSpec = (ElGamalParameterSpec)paramSpec;
        } else
        {
            DHParameterSpec s = (DHParameterSpec)paramSpec;
            currentSpec = new ElGamalParameterSpec(s.getP(), s.getG());
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        try
        {
            ElGamalParameter elP = new ElGamalParameter((ASN1Sequence)ASN1Primitive.fromByteArray(params));
            currentSpec = new ElGamalParameterSpec(elP.getP(), elP.getG());
        }
        catch(ClassCastException e)
        {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
        }
    }

    protected void engineInit(byte params[], String format)
        throws IOException
    {
        if(isASN1FormatString(format) || format.equalsIgnoreCase("X.509"))
            engineInit(params);
        else
            throw new IOException((new StringBuilder()).append("Unknown parameter format ").append(format).toString());
    }

    protected String engineToString()
    {
        return "ElGamal Parameters";
    }

    ElGamalParameterSpec currentSpec;
}
