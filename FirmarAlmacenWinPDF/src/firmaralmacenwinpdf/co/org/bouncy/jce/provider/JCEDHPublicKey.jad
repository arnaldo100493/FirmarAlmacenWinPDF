// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEDHPublicKey.java

package co.org.bouncy.jce.provider;

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

public class JCEDHPublicKey
    implements DHPublicKey
{

    JCEDHPublicKey(DHPublicKeySpec spec)
    {
        y = spec.getY();
        dhSpec = new DHParameterSpec(spec.getP(), spec.getG());
    }

    JCEDHPublicKey(DHPublicKey key)
    {
        y = key.getY();
        dhSpec = key.getParams();
    }

    JCEDHPublicKey(DHPublicKeyParameters params)
    {
        y = params.getY();
        dhSpec = new DHParameterSpec(params.getParameters().getP(), params.getParameters().getG(), params.getParameters().getL());
    }

    JCEDHPublicKey(BigInteger y, DHParameterSpec dhSpec)
    {
        this.y = y;
        this.dhSpec = dhSpec;
    }

    JCEDHPublicKey(SubjectPublicKeyInfo info)
    {
        this.info = info;
        DERInteger derY;
        try
        {
            derY = (DERInteger)info.parsePublicKey();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("invalid info structure in DH public key");
        }
        y = derY.getValue();
        ASN1Sequence seq = ASN1Sequence.getInstance(info.getAlgorithmId().getParameters());
        DERObjectIdentifier id = info.getAlgorithmId().getAlgorithm();
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
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.dhKeyAgreement, new DHParameter(dhSpec.getP(), dhSpec.getG(), dhSpec.getL())), new DERInteger(y));
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
        DERInteger l = DERInteger.getInstance(seq.getObjectAt(2));
        DERInteger p = DERInteger.getInstance(seq.getObjectAt(0));
        return l.getValue().compareTo(BigInteger.valueOf(p.getValue().bitLength())) <= 0;
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        y = (BigInteger)in.readObject();
        dhSpec = new DHParameterSpec((BigInteger)in.readObject(), (BigInteger)in.readObject(), in.readInt());
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.writeObject(getY());
        out.writeObject(dhSpec.getP());
        out.writeObject(dhSpec.getG());
        out.writeInt(dhSpec.getL());
    }

    static final long serialVersionUID = 0xfcfe28290f23e4fcL;
    private BigInteger y;
    private DHParameterSpec dhSpec;
    private SubjectPublicKeyInfo info;
}
