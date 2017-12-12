// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCDHPublicKey.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.DHParameter;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.DHDomainParameters;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.DHParameters;
import co.org.bouncy.crypto.params.DHPublicKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import java.io.*;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

public class BCDHPublicKey
    implements DHPublicKey
{

    BCDHPublicKey(DHPublicKeySpec spec)
    {
        y = spec.getY();
        dhSpec = new DHParameterSpec(spec.getP(), spec.getG());
    }

    BCDHPublicKey(DHPublicKey key)
    {
        y = key.getY();
        dhSpec = key.getParams();
    }

    BCDHPublicKey(DHPublicKeyParameters params)
    {
        y = params.getY();
        dhSpec = new DHParameterSpec(params.getParameters().getP(), params.getParameters().getG(), params.getParameters().getL());
    }

    BCDHPublicKey(BigInteger y, DHParameterSpec dhSpec)
    {
        this.y = y;
        this.dhSpec = dhSpec;
    }

    public BCDHPublicKey(SubjectPublicKeyInfo info)
    {
        this.info = info;
        ASN1Integer derY;
        try
        {
            derY = (ASN1Integer)info.parsePublicKey();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("invalid info structure in DH public key");
        }
        y = derY.getValue();
        ASN1Sequence seq = ASN1Sequence.getInstance(info.getAlgorithm().getParameters());
        ASN1ObjectIdentifier id = info.getAlgorithm().getAlgorithm();
        if(id.equals(PKCSObjectIdentifiers.dhKeyAgreement) || isPKCSParam(seq))
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

    public String getAlgorithm()
    {
        return "DH";
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        if(info != null)
            return KeyUtil.getEncodedSubjectPublicKeyInfo(info);
        else
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.dhKeyAgreement, (new DHParameter(dhSpec.getP(), dhSpec.getG(), dhSpec.getL())).toASN1Primitive()), new ASN1Integer(y));
    }

    public DHParameterSpec getParams()
    {
        return dhSpec;
    }

    public BigInteger getY()
    {
        return y;
    }

    private boolean isPKCSParam(ASN1Sequence seq)
    {
        if(seq.size() == 2)
            return true;
        if(seq.size() > 3)
            return false;
        ASN1Integer l = ASN1Integer.getInstance(seq.getObjectAt(2));
        ASN1Integer p = ASN1Integer.getInstance(seq.getObjectAt(0));
        return l.getValue().compareTo(BigInteger.valueOf(p.getValue().bitLength())) <= 0;
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
        dhSpec = new DHParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), in.readInt());
        info = null;
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(dhSpec.getP());
        out.writeObject(dhSpec.getG());
        out.writeInt(dhSpec.getL());
    }

    static final long serialVersionUID = 0xfcfe28290f23e4fcL;
    private BigInteger y;
    private transient DHParameterSpec dhSpec;
    private transient SubjectPublicKeyInfo info;
}
