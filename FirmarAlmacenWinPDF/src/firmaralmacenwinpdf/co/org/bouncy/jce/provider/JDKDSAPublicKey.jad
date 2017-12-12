// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JDKDSAPublicKey.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.DSAParameters;
import co.org.bouncy.crypto.params.DSAPublicKeyParameters;
import java.io.*;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPublicKeySpec;

public class JDKDSAPublicKey
    implements DSAPublicKey
{

    JDKDSAPublicKey(DSAPublicKeySpec spec)
    {
        y = spec.getY();
        dsaSpec = new DSAParameterSpec(spec.getP(), spec.getQ(), spec.getG());
    }

    JDKDSAPublicKey(DSAPublicKey key)
    {
        y = key.getY();
        dsaSpec = key.getParams();
    }

    JDKDSAPublicKey(DSAPublicKeyParameters params)
    {
        y = params.getY();
        dsaSpec = new DSAParameterSpec(params.getParameters().getP(), params.getParameters().getQ(), params.getParameters().getG());
    }

    JDKDSAPublicKey(BigInteger y, DSAParameterSpec dsaSpec)
    {
        this.y = y;
        this.dsaSpec = dsaSpec;
    }

    JDKDSAPublicKey(SubjectPublicKeyInfo info)
    {
        DERInteger derY;
        try
        {
            derY = (DERInteger)info.parsePublicKey();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
        y = derY.getValue();
        if(isNotNull(info.getAlgorithm().getParameters()))
        {
            DSAParameter params = DSAParameter.getInstance(info.getAlgorithm().getParameters());
            dsaSpec = new DSAParameterSpec(params.getP(), params.getQ(), params.getG());
        }
    }

    private boolean isNotNull(ASN1Encodable parameters)
    {
        return parameters != null && !DERNull.INSTANCE.equals(parameters);
    }

    public String getAlgorithm()
    {
        return "DSA";
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        try
        {
            if(dsaSpec == null)
                return (new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa), new DERInteger(y))).getEncoded("DER");
        }
        catch(IOException e)
        {
            return null;
        }
        return (new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(dsaSpec.getP(), dsaSpec.getQ(), dsaSpec.getG())), new DERInteger(y))).getEncoded("DER");
    }

    public DSAParams getParams()
    {
        return dsaSpec;
    }

    public BigInteger getY()
    {
        return y;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("DSA Public Key").append(nl);
        buf.append("            y: ").append(getY().toString(16)).append(nl);
        return buf.toString();
    }

    public int hashCode()
    {
        return getY().hashCode() ^ getParams().getG().hashCode() ^ getParams().getP().hashCode() ^ getParams().getQ().hashCode();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DSAPublicKey))
        {
            return false;
        } else
        {
            DSAPublicKey other = (DSAPublicKey)o;
            return getY().equals(other.getY()) && getParams().getG().equals(other.getParams().getG()) && getParams().getP().equals(other.getParams().getP()) && getParams().getQ().equals(other.getParams().getQ());
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        y = (BigInteger)in.readObject();
        dsaSpec = new DSAParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), (BigInteger)in.readObject());
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.writeObject(y);
        out.writeObject(dsaSpec.getP());
        out.writeObject(dsaSpec.getQ());
        out.writeObject(dsaSpec.getG());
    }

    private static final long serialVersionUID = 0x1851f637e242c807L;
    private BigInteger y;
    private DSAParams dsaSpec;
}
