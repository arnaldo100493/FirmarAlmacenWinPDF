// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmParametersSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSASSAPSSparams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.provider.util.DigestFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            AlgorithmParametersSpi

public static class AlgorithmParametersSpi$PSS extends AlgorithmParametersSpi
{

    protected byte[] engineGetEncoded()
        throws IOException
    {
        PSSParameterSpec pssSpec = currentSpec;
        AlgorithmIdentifier hashAlgorithm = new AlgorithmIdentifier(DigestFactory.getOID(pssSpec.getDigestAlgorithm()), DERNull.INSTANCE);
        MGF1ParameterSpec mgfSpec = (MGF1ParameterSpec)pssSpec.getMGFParameters();
        AlgorithmIdentifier maskGenAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, new AlgorithmIdentifier(DigestFactory.getOID(mgfSpec.getDigestAlgorithm()), DERNull.INSTANCE));
        RSASSAPSSparams pssP = new RSASSAPSSparams(hashAlgorithm, maskGenAlgorithm, new ASN1Integer(pssSpec.getSaltLength()), new ASN1Integer(pssSpec.getTrailerField()));
        return pssP.getEncoded("DER");
    }

    protected byte[] engineGetEncoded(String format)
        throws IOException
    {
        if(format.equalsIgnoreCase("X.509") || format.equalsIgnoreCase("ASN.1"))
            return engineGetEncoded();
        else
            return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
        throws InvalidParameterSpecException
    {
        if(paramSpec == java/security/spec/PSSParameterSpec && currentSpec != null)
            return currentSpec;
        else
            throw new InvalidParameterSpecException("unknown parameter spec passed to PSS parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException
    {
        if(!(paramSpec instanceof PSSParameterSpec))
        {
            throw new InvalidParameterSpecException("PSSParameterSpec required to initialise an PSS algorithm parameters object");
        } else
        {
            currentSpec = (PSSParameterSpec)paramSpec;
            return;
        }
    }

    protected void engineInit(byte params[])
        throws IOException
    {
        try
        {
            RSASSAPSSparams pssP = RSASSAPSSparams.getInstance(params);
            currentSpec = new PSSParameterSpec(pssP.getHashAlgorithm().getAlgorithm().getId(), pssP.getMaskGenAlgorithm().getAlgorithm().getId(), new MGF1ParameterSpec(AlgorithmIdentifier.getInstance(pssP.getMaskGenAlgorithm().getParameters()).getAlgorithm().getId()), pssP.getSaltLength().intValue(), pssP.getTrailerField().intValue());
        }
        catch(ClassCastException e)
        {
            throw new IOException("Not a valid PSS Parameter encoding.");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new IOException("Not a valid PSS Parameter encoding.");
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
        return "PSS Parameters";
    }

    PSSParameterSpec currentSpec;

    public AlgorithmParametersSpi$PSS()
    {
    }
}
