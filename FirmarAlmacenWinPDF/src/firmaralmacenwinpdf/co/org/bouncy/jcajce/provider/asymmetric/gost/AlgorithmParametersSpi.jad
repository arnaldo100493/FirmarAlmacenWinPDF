// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParametersSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.gost;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import co.org.bouncy.jce.spec.GOST3410ParameterSpec;
import co.org.bouncy.jce.spec.GOST3410PublicKeyParameterSetSpec;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
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
        GOST3410PublicKeyAlgParameters gost3410P = new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(currentSpec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(currentSpec.getDigestParamSetOID()), new ASN1ObjectIdentifier(currentSpec.getEncryptionParamSetOID()));
        try
        {
            return gost3410P.getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error encoding GOST3410Parameters");
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
        if(paramSpec == co/org/bouncy/jce/spec/GOST3410PublicKeyParameterSetSpec)
            return currentSpec;
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to GOST3410 parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof GOST3410ParameterSpec))
        {
            throw new InvalidParameterSpecException("GOST3410ParameterSpec required to initialise a GOST3410 algorithm parameters object");
        } else
        {
            currentSpec = (GOST3410ParameterSpec)paramSpec;
            return;
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        try
        {
            ASN1Sequence seq = (ASN1Sequence)ASN1Primitive.fromByteArray(params);
            currentSpec = GOST3410ParameterSpec.fromPublicKeyAlg(new GOST3410PublicKeyAlgParameters(seq));
        }
        catch(ClassCastException e)
        {
            throw new IOException("Not a valid GOST3410 Parameter encoding.");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new IOException("Not a valid GOST3410 Parameter encoding.");
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
        return "GOST3410 Parameters";
    }

    GOST3410ParameterSpec currentSpec;
}
