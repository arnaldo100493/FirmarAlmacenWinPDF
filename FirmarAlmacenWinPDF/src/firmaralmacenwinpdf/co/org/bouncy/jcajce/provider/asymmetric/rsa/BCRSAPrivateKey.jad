// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCRSAPrivateKey.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSAPrivateKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Enumeration;

public class BCRSAPrivateKey
    implements java.security.interfaces.RSAPrivateKey, PKCS12BagAttributeCarrier
{

    protected BCRSAPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    BCRSAPrivateKey(RSAKeyParameters key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        modulus = key.getModulus();
        privateExponent = key.getExponent();
    }

    BCRSAPrivateKey(RSAPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        modulus = spec.getModulus();
        privateExponent = spec.getPrivateExponent();
    }

    BCRSAPrivateKey(java.security.interfaces.RSAPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        modulus = key.getModulus();
        privateExponent = key.getPrivateExponent();
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getPrivateExponent()
    {
        return privateExponent;
    }

    public String getAlgorithm()
    {
        return "RSA";
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        return KeyUtil.getEncodedPrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPrivateKey(getModulus(), ZERO, getPrivateExponent(), ZERO, ZERO, ZERO, ZERO, ZERO));
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof java.security.interfaces.RSAPrivateKey))
            return false;
        if(o == this)
        {
            return true;
        } else
        {
            java.security.interfaces.RSAPrivateKey key = (java.security.interfaces.RSAPrivateKey)o;
            return getModulus().equals(key.getModulus()) && getPrivateExponent().equals(key.getPrivateExponent());
        }
    }

    public int hashCode()
    {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
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
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
    }

    static final long serialVersionUID = 0x46eb09c007cf411cL;
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    protected BigInteger modulus;
    protected BigInteger privateExponent;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;

}
