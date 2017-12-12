// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAST5.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.misc.CAST5CBCParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            CAST5

public static class CAST5$AlgParams extends BaseAlgorithmParameters
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

    public CAST5$AlgParams()
    {
        keyLength = 128;
    }
}
