// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCDSAPrivateKey.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DSAParameter;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.DSAParameters;
import co.org.bouncy.crypto.params.DSAPrivateKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import java.io.*;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPrivateKeySpec;
import java.util.Enumeration;

public class BCDSAPrivateKey
    implements DSAPrivateKey, PKCS12BagAttributeCarrier
{

    protected BCDSAPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    BCDSAPrivateKey(DSAPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        dsaSpec = key.getParams();
    }

    BCDSAPrivateKey(DSAPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        dsaSpec = new DSAParameterSpec(spec.getP(), spec.getQ(), spec.getG());
    }

    public BCDSAPrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        DSAParameter params = DSAParameter.getInstance(info.getPrivateKeyAlgorithm().getParameters());
        ASN1Integer derX = (ASN1Integer)info.parsePrivateKey();
        x = derX.getValue();
        dsaSpec = new DSAParameterSpec(params.getP(), params.getQ(), params.getG());
    }

    BCDSAPrivateKey(DSAPrivateKeyParameters params)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = params.getX();
        dsaSpec = new DSAParameterSpec(params.getParameters().getP(), params.getParameters().getQ(), params.getParameters().getG());
    }

    public String getAlgorithm()
    {
        return "DSA";
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        return KeyUtil.getEncodedPrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, (new DSAParameter(dsaSpec.getP(), dsaSpec.getQ(), dsaSpec.getG())).toASN1Primitive()), new ASN1Integer(getX()));
    }

    public DSAParams getParams()
    {
        return dsaSpec;
    }

    public BigInteger getX()
    {
        return x;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DSAPrivateKey))
        {
            return false;
        } else
        {
            DSAPrivateKey other = (DSAPrivateKey)o;
            return getX().equals(other.getX()) && getParams().getG().equals(other.getParams().getG()) && getParams().getP().equals(other.getParams().getP()) && getParams().getQ().equals(other.getParams().getQ());
        }
    }

    public int hashCode()
    {
        return getX().hashCode() ^ getParams().getG().hashCode() ^ getParams().getP().hashCode() ^ getParams().getQ().hashCode();
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

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        dsaSpec = new DSAParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), (BigInteger)in.readObject());
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(dsaSpec.getP());
        out.writeObject(dsaSpec.getQ());
        out.writeObject(dsaSpec.getG());
    }

    private static final long serialVersionUID = 0xbf170939253dadbaL;
    private BigInteger x;
    private transient DSAParams dsaSpec;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;
}
