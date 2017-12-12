// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEDHPrivateKey.java

package co.org.bouncy.jce.provider;

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

public class JCEDHPrivateKey
    implements DHPrivateKey, PKCS12BagAttributeCarrier
{

    protected JCEDHPrivateKey()
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    JCEDHPrivateKey(DHPrivateKey key)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = key.getX();
        dhSpec = key.getParams();
    }

    JCEDHPrivateKey(DHPrivateKeySpec spec)
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        x = spec.getX();
        dhSpec = new DHParameterSpec(spec.getP(), spec.getG());
    }

    JCEDHPrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ASN1Sequence seq = ASN1Sequence.getInstance(info.getAlgorithmId().getParameters());
        DERInteger derX = DERInteger.getInstance(info.parsePrivateKey());
        DERObjectIdentifier id = info.getAlgorithmId().getAlgorithm();
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

    JCEDHPrivateKey(DHPrivateKeyParameters params)
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
        catch(IOException e)
        {
            return null;
        }
        info = new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.dhKeyAgreement, new DHParameter(dhSpec.getP(), dhSpec.getG(), dhSpec.getL())), new DERInteger(getX()));
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

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        x = (BigInteger)in.readObject();
        dhSpec = new DHParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), in.readInt());
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.writeObject(getX());
        out.writeObject(dhSpec.getP());
        out.writeObject(dhSpec.getG());
        out.writeInt(dhSpec.getL());
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

    static final long serialVersionUID = 0x4511a58411962b4L;
    BigInteger x;
    private DHParameterSpec dhSpec;
    private PrivateKeyInfo info;
    private PKCS12BagAttributeCarrier attrCarrier;
}
