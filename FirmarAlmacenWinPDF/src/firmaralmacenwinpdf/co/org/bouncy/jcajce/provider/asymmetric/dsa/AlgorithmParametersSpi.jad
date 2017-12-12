// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParametersSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x509.DSAParameter;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;

public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi
{

    public AlgorithmParametersSpi()
    {
    }

    protected boolean isASN1FormatString(String format)
    {
        return format == null || format.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class paramSpec)
        throws InvalidParameterSpecException
    {
        if(paramSpec == null)
            throw new NullPointerException("argument to getParameterSpec must not be null");
        else
            return localEngineGetParameterSpec(paramSpec);
    }

    protected byte[] engineGetEncoded()
    {
        DSAParameter dsaP = new DSAParameter(currentSpec.getP(), currentSpec.getQ(), currentSpec.getG());
        try
        {
            return dsaP.getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error encoding DSAParameters");
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
        if(paramSpec == java/security/spec/DSAParameterSpec)
            return currentSpec;
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to DSA parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof DSAParameterSpec))
        {
            throw new InvalidParameterSpecException("DSAParameterSpec required to initialise a DSA algorithm parameters object");
        } else
        {
            currentSpec = (DSAParameterSpec)paramSpec;
            return;
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        try
        {
            DSAParameter dsaP = DSAParameter.getInstance(ASN1Primitive.fromByteArray(params));
            currentSpec = new DSAParameterSpec(dsaP.getP(), dsaP.getQ(), dsaP.getG());
        }
        catch(ClassCastException e)
        {
            throw new IOException("Not a valid DSA Parameter encoding.");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new IOException("Not a valid DSA Parameter encoding.");
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
        return "DSA Parameters";
    }

    DSAParameterSpec currentSpec;
}
