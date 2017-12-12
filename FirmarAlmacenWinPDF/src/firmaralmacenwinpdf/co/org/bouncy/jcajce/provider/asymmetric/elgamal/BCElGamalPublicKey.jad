// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCElGamalPublicKey.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERInteger;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.params.ElGamalParameters;
import co.org.bouncy.crypto.params.ElGamalPublicKeyParameters;
import co.org.bouncy.jce.interfaces.ElGamalPublicKey;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import co.org.bouncy.jce.spec.ElGamalPublicKeySpec;
import java.io.*;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

public class BCElGamalPublicKey
    implements ElGamalPublicKey, DHPublicKey
{

    BCElGamalPublicKey(ElGamalPublicKeySpec spec)
    {
        y = spec.getY();
        elSpec = new ElGamalParameterSpec(spec.getParams().getP(), spec.getParams().getG());
    }

    BCElGamalPublicKey(DHPublicKeySpec spec)
    {
        y = spec.getY();
        elSpec = new ElGamalParameterSpec(spec.getP(), spec.getG());
    }

    BCElGamalPublicKey(ElGamalPublicKey key)
    {
        y = key.getY();
        elSpec = key.getParameters();
    }

    BCElGamalPublicKey(DHPublicKey key)
    {
        y = key.getY();
        elSpec = new ElGamalParameterSpec(key.getParams().getP(), key.getParams().getG());
    }

    BCElGamalPublicKey(ElGamalPublicKeyParameters params)
    {
        y = params.getY();
        elSpec = new ElGamalParameterSpec(params.getParameters().getP(), params.getParameters().getG());
    }

    BCElGamalPublicKey(BigInteger y, ElGamalParameterSpec elSpec)
    {
        this.y = y;
        this.elSpec = elSpec;
    }

    BCElGamalPublicKey(SubjectPublicKeyInfo info)
    {
        ElGamalParameter params = new ElGamalParameter((ASN1Sequence)info.getAlgorithmId().getParameters());
        DERInteger derY = null;
        try
        {
            derY = (DERInteger)info.parsePublicKey();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
        y = derY.getValue();
        elSpec = new ElGamalParameterSpec(params.getP(), params.getG());
    }

    public String getAlgorithm()
    {
        return "ElGamal";
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        try
        {
            SubjectPublicKeyInfo info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.elGamalAlgorithm, new ElGamalParameter(elSpec.getP(), elSpec.getG())), new DERInteger(y));
            return info.getEncoded("DER");
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public ElGamalParameterSpec getParameters()
    {
        return elSpec;
    }

    public DHParameterSpec getParams()
    {
        return new DHParameterSpec(elSpec.getP(), elSpec.getG());
    }

    public BigInteger getY()
    {
        return y;
    }

    public int hashCode()
    {
        return getY().hashCode() ^ getParams().getG().hashCode() ^ getParams().getP().hashCode() ^ getParams().getL();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DHPublicKey))
        {
            return false;
        } else
        {
            DHPublicKey other = (DHPublicKey)o;
            return getY().equals(other.getY()) && getParams().getG().equals(other.getParams().getG()) && getParams().getP().equals(other.getParams().getP()) && getParams().getL() == other.getParams().getL();
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        elSpec = new ElGamalParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject());
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(elSpec.getP());
        out.writeObject(elSpec.getG());
    }

    static final long serialVersionUID = 0x78e9d455552c6634L;
    private BigInteger y;
    private transient ElGamalParameterSpec elSpec;
}
