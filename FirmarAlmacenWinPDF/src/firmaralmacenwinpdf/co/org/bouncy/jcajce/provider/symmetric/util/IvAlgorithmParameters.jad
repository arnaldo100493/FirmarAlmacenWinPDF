// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IvAlgorithmParameters.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BaseAlgorithmParameters

public class IvAlgorithmParameters extends BaseAlgorithmParameters
{

    public IvAlgorithmParameters()
    {
    }

    protected byte[] engineGetEncoded()
        throws IOException
    {
        return engineGetEncoded("ASN.1");
    }

    protected byte[] engineGetEncoded(String format)
        throws IOException
    {
        if(isASN1FormatString(format))
            return (new DEROctetString(engineGetEncoded("RAW"))).getEncoded();
        if(format.equals("RAW"))
            return Arrays.clone(iv);
        else
            return null;
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
        if(params.length % 8 != 0 && params[0] == 4 && params[1] == params.length - 2)
        {
            ASN1OctetString oct = (ASN1OctetString)ASN1Primitive.fromByteArray(params);
            params = oct.getOctets();
        }
        iv = Arrays.clone(params);
    }

    protected void engineInit(byte params[], String format)
        throws IOException
    {
        if(isASN1FormatString(format))
        {
            try
            {
                ASN1OctetString oct = (ASN1OctetString)ASN1Primitive.fromByteArray(params);
                engineInit(oct.getOctets());
            }
            catch(Exception e)
            {
                throw new IOException((new StringBuilder()).append("Exception decoding: ").append(e).toString());
            }
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
        return "IV Parameters";
    }

    private byte iv[];
}
