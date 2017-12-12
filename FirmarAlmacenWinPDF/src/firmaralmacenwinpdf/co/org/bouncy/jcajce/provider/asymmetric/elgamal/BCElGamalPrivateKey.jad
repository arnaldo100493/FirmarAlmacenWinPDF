// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCElGamalPrivateKey.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.params.ElGamalParameters;
import co.org.bouncy.crypto.params.ElGamalPrivateKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import co.org.bouncy.jce.spec.ElGamalPrivateKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;

public class BCElGamalPrivateKey
    implements ElGamalPrivateKey, DHPrivateKey, PKCS12BagAttributeCarrier
{

    protected BCElGamalPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    BCElGamalPrivateKey(ElGamalPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        elSpec = key.getParameters();
    }

    BCElGamalPrivateKey(DHPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        elSpec = new ElGamalParameterSpec(key.getParams().getP(), key.getParams().getG());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        elSpec = new ElGamalParameterSpec(spec.getParams().getP(), spec.getParams().getG());
    }

    BCElGamalPrivateKey(DHPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        elSpec = new ElGamalParameterSpec(spec.getP(), spec.getG());
    }

    BCElGamalPrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ElGamalParameter params = new ElGamalParameter((ASN1Sequence)info.getAlgorithmId().getParameters());
        DERInteger derX = ASN1Integer.getInstance(info.parsePrivateKey());
        x = derX.getValue();
        elSpec = new ElGamalParameterSpec(params.getP(), params.getG());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeyParameters params)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = params.getX();
        elSpec = new ElGamalParameterSpec(params.getParameters().getP(), params.getParameters().getG());
    }

    public String getAlgorithm()
    {
        return "ElGamal";
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        try
        {
            PrivateKeyInfo info = new PrivateKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.elGamalAlgorithm, new ElGamalParameter(elSpec.getP(), elSpec.getG())), new DERInteger(getX()));
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

    public BigInteger getX()
    {
        return x;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DHPrivateKey))
        {
            return false;
        } else
        {
            DHPrivateKey other = (DHPrivateKey)o;
            return getX().equals(other.getX()) && getParams().getG().equals(other.getParams().getG()) && getParams().getP().equals(other.getParams().getP()) && getParams().getL() == other.getParams().getL();
        }
    }

    public int hashCode()
    {
        return getX().hashCode() ^ getParams().getG().hashCode() ^ getParams().getP().hashCode() ^ getParams().getL();
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        elSpec = new ElGamalParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject());
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(elSpec.getP());
        out.writeObject(elSpec.getG());
    }

    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute)
    {
        attrCarrier.setBagAttribute(oid, attribute);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid)
    {
        return attrCarrier.getBagAttribute(oid);
    }

    public Enumeration getBagAttributeKeys()
    {
        return attrCarrier.getBagAttributeKeys();
    }

    static final long serialVersionUID = 0x42e1c55fb6bcc04eL;
    private BigInteger x;
    private transient ElGamalParameterSpec elSpec;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;
}
