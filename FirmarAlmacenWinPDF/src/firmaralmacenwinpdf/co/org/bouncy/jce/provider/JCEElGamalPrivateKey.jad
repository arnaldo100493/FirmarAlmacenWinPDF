// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEElGamalPrivateKey.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.params.ElGamalParameters;
import co.org.bouncy.crypto.params.ElGamalPrivateKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
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

public class JCEElGamalPrivateKey
    implements ElGamalPrivateKey, DHPrivateKey, PKCS12BagAttributeCarrier
{

    protected JCEElGamalPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    JCEElGamalPrivateKey(ElGamalPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        elSpec = key.getParameters();
    }

    JCEElGamalPrivateKey(DHPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        elSpec = new ElGamalParameterSpec(key.getParams().getP(), key.getParams().getG());
    }

    JCEElGamalPrivateKey(ElGamalPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        elSpec = new ElGamalParameterSpec(spec.getParams().getP(), spec.getParams().getG());
    }

    JCEElGamalPrivateKey(DHPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        elSpec = new ElGamalParameterSpec(spec.getP(), spec.getG());
    }

    JCEElGamalPrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ElGamalParameter params = new ElGamalParameter((ASN1Sequence)info.getAlgorithmId().getParameters());
        DERInteger derX = ASN1Integer.getInstance(info.parsePrivateKey());
        x = derX.getValue();
        elSpec = new ElGamalParameterSpec(params.getP(), params.getG());
    }

    JCEElGamalPrivateKey(ElGamalPrivateKeyParameters params)
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
        return KeyUtil.getEncodedPrivateKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.elGamalAlgorithm, new ElGamalParameter(elSpec.getP(), elSpec.getG())), new DERInteger(getX()));
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

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        x = (BigInteger)in.readObject();
        elSpec = new ElGamalParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject());
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.writeObject(getX());
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
    BigInteger x;
    ElGamalParameterSpec elSpec;
    private PKCS12BagAttributeCarrierImpl attrCarrier;
}
