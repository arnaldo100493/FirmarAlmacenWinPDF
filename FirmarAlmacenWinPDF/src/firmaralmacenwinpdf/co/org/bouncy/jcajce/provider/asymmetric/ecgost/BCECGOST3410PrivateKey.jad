// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCECGOST3410PrivateKey.java

package co.org.bouncy.jcajce.provider.asymmetric.ecgost;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERInteger;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.cryptopro.ECGOST3410NamedCurves;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X962Parameters;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.crypto.params.ECPrivateKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.interfaces.ECPointEncoder;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECNamedCurveSpec;
import co.org.bouncy.jce.spec.ECPrivateKeySpec;
import co.org.bouncy.math.ec.ECFieldElement;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ecgost:
//            BCECGOST3410PublicKey

public class BCECGOST3410PrivateKey
    implements java.security.interfaces.ECPrivateKey, co.org.bouncy.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier, ECPointEncoder
{

    protected BCECGOST3410PrivateKey()
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    public BCECGOST3410PrivateKey(java.security.interfaces.ECPrivateKey key)
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        d = key.getS();
        algorithm = key.getAlgorithm();
        ecSpec = key.getParams();
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec spec)
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        d = spec.getD();
        if(spec.getParams() != null)
        {
            co.org.bouncy.math.ec.ECCurve curve = spec.getParams().getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, spec.getParams().getSeed());
            ecSpec = EC5Util.convertSpec(ellipticCurve, spec.getParams());
        } else
        {
            ecSpec = null;
        }
    }

    public BCECGOST3410PrivateKey(java.security.spec.ECPrivateKeySpec spec)
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        d = spec.getS();
        ecSpec = spec.getParams();
    }

    public BCECGOST3410PrivateKey(BCECGOST3410PrivateKey key)
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        d = key.d;
        ecSpec = key.ecSpec;
        withCompression = key.withCompression;
        attrCarrier = key.attrCarrier;
        publicKey = key.publicKey;
    }

    public BCECGOST3410PrivateKey(String algorithm, ECPrivateKeyParameters params, BCECGOST3410PublicKey pubKey, ECParameterSpec spec)
    {
        this.algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm;
        d = params.getD();
        if(spec == null)
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(dp.getCurve(), dp.getSeed());
            ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(dp.getG().getX().toBigInteger(), dp.getG().getY().toBigInteger()), dp.getN(), dp.getH().intValue());
        } else
        {
            ecSpec = spec;
        }
        publicKey = getPublicKeyDetails(pubKey);
    }

    public BCECGOST3410PrivateKey(String algorithm, ECPrivateKeyParameters params, BCECGOST3410PublicKey pubKey, co.org.bouncy.jce.spec.ECParameterSpec spec)
    {
        this.algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm;
        d = params.getD();
        if(spec == null)
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(dp.getCurve(), dp.getSeed());
            ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(dp.getG().getX().toBigInteger(), dp.getG().getY().toBigInteger()), dp.getN(), dp.getH().intValue());
        } else
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(spec.getCurve(), spec.getSeed());
            ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(spec.getG().getX().toBigInteger(), spec.getG().getY().toBigInteger()), spec.getN(), spec.getH().intValue());
        }
        publicKey = getPublicKeyDetails(pubKey);
    }

    public BCECGOST3410PrivateKey(String algorithm, ECPrivateKeyParameters params)
    {
        this.algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.algorithm = algorithm;
        d = params.getD();
        ecSpec = null;
    }

    BCECGOST3410PrivateKey(PrivateKeyInfo info)
        throws IOException
    {
        algorithm = "ECGOST3410";
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        populateFromPrivKeyInfo(info);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo info)
        throws IOException
    {
        X962Parameters params = new X962Parameters((ASN1Primitive)info.getPrivateKeyAlgorithm().getParameters());
        if(params.isNamedCurve())
        {
            ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
            X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
            if(ecP == null)
            {
                ECDomainParameters gParam = ECGOST3410NamedCurves.getByOID(oid);
                EllipticCurve ellipticCurve = EC5Util.convertCurve(gParam.getCurve(), gParam.getSeed());
                ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(oid), ellipticCurve, new ECPoint(gParam.getG().getX().toBigInteger(), gParam.getG().getY().toBigInteger()), gParam.getN(), gParam.getH());
            } else
            {
                EllipticCurve ellipticCurve = EC5Util.convertCurve(ecP.getCurve(), ecP.getSeed());
                ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName(oid), ellipticCurve, new ECPoint(ecP.getG().getX().toBigInteger(), ecP.getG().getY().toBigInteger()), ecP.getN(), ecP.getH());
            }
        } else
        if(params.isImplicitlyCA())
        {
            ecSpec = null;
        } else
        {
            X9ECParameters ecP = X9ECParameters.getInstance(params.getParameters());
            EllipticCurve ellipticCurve = EC5Util.convertCurve(ecP.getCurve(), ecP.getSeed());
            ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(ecP.getG().getX().toBigInteger(), ecP.getG().getY().toBigInteger()), ecP.getN(), ecP.getH().intValue());
        }
        ASN1Encodable privKey = info.parsePrivateKey();
        if(privKey instanceof DERInteger)
        {
            DERInteger derD = DERInteger.getInstance(privKey);
            d = derD.getValue();
        } else
        {
            ECPrivateKey ec = ECPrivateKey.getInstance(privKey);
            d = ec.getKey();
            publicKey = ec.getPublicKey();
        }
    }

    public String getAlgorithm()
    {
        return algorithm;
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        X962Parameters params;
        if(ecSpec instanceof ECNamedCurveSpec)
        {
            DERObjectIdentifier curveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec)ecSpec).getName());
            if(curveOid == null)
                curveOid = new DERObjectIdentifier(((ECNamedCurveSpec)ecSpec).getName());
            params = new X962Parameters(curveOid);
        } else
        if(ecSpec == null)
        {
            params = new X962Parameters(DERNull.INSTANCE);
        } else
        {
            co.org.bouncy.math.ec.ECCurve curve = EC5Util.convertCurve(ecSpec.getCurve());
            X9ECParameters ecP = new X9ECParameters(curve, EC5Util.convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf(ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
            params = new X962Parameters(ecP);
        }
        ECPrivateKey keyStructure;
        if(publicKey != null)
            keyStructure = new ECPrivateKey(getS(), publicKey, params);
        else
            keyStructure = new ECPrivateKey(getS(), params);
        try
        {
            PrivateKeyInfo info;
            if(algorithm.equals("ECGOST3410"))
                info = new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, params.toASN1Primitive()), keyStructure.toASN1Primitive());
            else
                info = new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params.toASN1Primitive()), keyStructure.toASN1Primitive());
            return info.getEncoded("DER");
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public ECParameterSpec getParams()
    {
        return ecSpec;
    }

    public co.org.bouncy.jce.spec.ECParameterSpec getParameters()
    {
        if(ecSpec == null)
            return null;
        else
            return EC5Util.convertSpec(ecSpec, withCompression);
    }

    co.org.bouncy.jce.spec.ECParameterSpec engineGetSpec()
    {
        if(ecSpec != null)
            return EC5Util.convertSpec(ecSpec, withCompression);
        else
            return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public BigInteger getS()
    {
        return d;
    }

    public BigInteger getD()
    {
        return d;
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

    public void setPointFormat(String style)
    {
        withCompression = !"UNCOMPRESSED".equalsIgnoreCase(style);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof BCECGOST3410PrivateKey))
        {
            return false;
        } else
        {
            BCECGOST3410PrivateKey other = (BCECGOST3410PrivateKey)o;
            return getD().equals(other.getD()) && engineGetSpec().equals(other.engineGetSpec());
        }
    }

    public int hashCode()
    {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("EC Private Key").append(nl);
        buf.append("             S: ").append(d.toString(16)).append(nl);
        return buf.toString();
    }

    private DERBitString getPublicKeyDetails(BCECGOST3410PublicKey pub)
    {
        try
        {
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(pub.getEncoded()));
            return info.getPublicKeyData();
        }
        catch(IOException e)
        {
            return null;
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        byte enc[] = (byte[])(byte[])in.readObject();
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(enc)));
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(getEncoded());
    }

    static final long serialVersionUID = 0x648ee5f4b1b13042L;
    private String algorithm;
    private boolean withCompression;
    private transient BigInteger d;
    private transient ECParameterSpec ecSpec;
    private transient DERBitString publicKey;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;
}
