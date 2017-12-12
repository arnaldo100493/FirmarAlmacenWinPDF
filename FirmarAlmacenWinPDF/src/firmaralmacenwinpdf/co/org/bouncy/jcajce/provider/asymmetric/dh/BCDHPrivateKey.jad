// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCDHPrivateKey.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.DHDomainParameters;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.DHParameters;
import co.org.bouncy.crypto.params.DHPrivateKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import java.io.*;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;

public class BCDHPrivateKey
    implements DHPrivateKey, PKCS12BagAttributeCarrier
{

    protected BCDHPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    BCDHPrivateKey(DHPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        dhSpec = key.getParams();
    }

    BCDHPrivateKey(DHPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        dhSpec = new DHParameterSpec(spec.getP(), spec.getG());
    }

    public BCDHPrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ASN1Sequence seq = ASN1Sequence.getInstance(info.getPrivateKeyAlgorithm().getParameters());
        ASN1Integer derX = (ASN1Integer)info.parsePrivateKey();
        ASN1ObjectIdentifier id = info.getPrivateKeyAlgorithm().getAlgorithm();
        this.info = info;
        x = derX.getValue();
        if(id.equals(PKCSObjectIdentifiers.dhKeyAgreement))
        {
            DHParameter params = DHParameter.getInstance(seq);
            if(params.getL() != null)
                dhSpec = new DHParameterSpec(params.getP(), params.getG(), params.getL().intValue());
            else
                dhSpec = new DHParameterSpec(params.getP(), params.getG());
        } else
        if(id.equals(X9ObjectIdentifiers.dhpublicnumber))
        {
            DHDomainParameters params = DHDomainParameters.getInstance(seq);
            dhSpec = new DHParameterSpec(params.getP().getValue(), params.getG().getValue());
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unknown algorithm type: ").append(id).toString());
        }
    }

    BCDHPrivateKey(DHPrivateKeyParameters params)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = params.getX();
        dhSpec = new DHParameterSpec(params.getParameters().getP(), params.getParameters().getG(), params.getParameters().getL());
    }

    public String getAlgorithm()
    {
        return "DH";
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        PrivateKeyInfo info;
        try
        {
            if(this.info != null)
                return this.info.getEncoded("DER");
        }
        catch(Exception e)
        {
            return null;
        }
        info = new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.dhKeyAgreement, (new DHParameter(dhSpec.getP(), dhSpec.getG(), dhSpec.getL())).toASN1Primitive()), new ASN1Integer(getX()));
        return info.getEncoded("DER");
    }

    public DHParameterSpec getParams()
    {
        return dhSpec;
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
        dhSpec = new DHParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), in.readInt());
        info = null;
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(dhSpec.getP());
        out.writeObject(dhSpec.getG());
        out.writeInt(dhSpec.getL());
    }

    static final long serialVersionUID = 0x4511a58411962b4L;
    private BigInteger x;
    private transient DHParameterSpec dhSpec;
    private transient PrivateKeyInfo info;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;
}
