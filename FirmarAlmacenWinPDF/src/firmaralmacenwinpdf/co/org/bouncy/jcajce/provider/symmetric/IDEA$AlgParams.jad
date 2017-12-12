// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDEA.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.misc.IDEACBCPar;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            IDEA

public static class IDEA$AlgParams extends BaseAlgorithmParameters
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

    public IDEA$AlgParams()
    {
    }
}
