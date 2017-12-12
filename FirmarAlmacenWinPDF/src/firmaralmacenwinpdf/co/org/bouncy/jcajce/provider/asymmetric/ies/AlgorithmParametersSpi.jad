// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParametersSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ies;

import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERInteger;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.jce.spec.IESParameterSpec;
import java.io.IOException;
import java.math.BigInteger;
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
        try
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new DEROctetString(currentSpec.getDerivationV()));
            v.add(new DEROctetString(currentSpec.getEncodingV()));
            v.add(new DERInteger(currentSpec.getMacKeySize()));
            return (new DERSequence(v)).getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error encoding IESParameters");
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
        if(paramSpec == co/org/bouncy/jce/spec/IESParameterSpec)
            return currentSpec;
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof IESParameterSpec))
        {
            throw new InvalidParameterSpecException("IESParameterSpec required to initialise a IES algorithm parameters object");
        } else
        {
            currentSpec = (IESParameterSpec)paramSpec;
            return;
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        try
        {
            ASN1Sequence s = (ASN1Sequence)ASN1Primitive.fromByteArray(params);
            currentSpec = new IESParameterSpec(((ASN1OctetString)s.getObjectAt(0)).getOctets(), ((ASN1OctetString)s.getObjectAt(0)).getOctets(), ((DERInteger)s.getObjectAt(0)).getValue().intValue());
        }
        catch(ClassCastException e)
        {
            throw new IOException("Not a valid IES Parameter encoding.");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new IOException("Not a valid IES Parameter encoding.");
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
        return "IES Parameters";
    }

    IESParameterSpec currentSpec;
}
