// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCECPublicKey.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X962Parameters;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ECPoint;
import co.org.bouncy.asn1.x9.X9IntegerConverter;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.interfaces.ECPointEncoder;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECNamedCurveSpec;
import co.org.bouncy.jce.spec.ECPublicKeySpec;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECFieldElement;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

public class BCECPublicKey
    implements ECPublicKey, co.org.bouncy.jce.interfaces.ECPublicKey, ECPointEncoder
{

    public BCECPublicKey(String algorithm, BCECPublicKey key)
    {
        this.algorithm = "EC";
        this.algorithm = algorithm;
        q = key.q;
        ecSpec = key.ecSpec;
        withCompression = key.withCompression;
        configuration = key.configuration;
    }

    public BCECPublicKey(String algorithm, java.security.spec.ECPublicKeySpec spec, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        this.algorithm = algorithm;
        ecSpec = spec.getParams();
        q = EC5Util.convertPoint(ecSpec, spec.getW(), false);
        this.configuration = configuration;
    }

    public BCECPublicKey(String algorithm, ECPublicKeySpec spec, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        this.algorithm = algorithm;
        q = spec.getQ();
        if(spec.getParams() != null)
        {
            ECCurve curve = spec.getParams().getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, spec.getParams().getSeed());
            ecSpec = EC5Util.convertSpec(ellipticCurve, spec.getParams());
        } else
        {
            if(q.getCurve() == null)
            {
                co.org.bouncy.jce.spec.ECParameterSpec s = configuration.getEcImplicitlyCa();
                q = s.getCurve().createPoint(q.getX().toBigInteger(), q.getY().toBigInteger(), false);
            }
            ecSpec = null;
        }
        this.configuration = configuration;
    }

    public BCECPublicKey(String algorithm, ECPublicKeyParameters params, ECParameterSpec spec, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm;
        q = params.getQ();
        if(spec == null)
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(dp.getCurve(), dp.getSeed());
            ecSpec = createSpec(ellipticCurve, dp);
        } else
        {
            ecSpec = spec;
        }
        this.configuration = configuration;
    }

    public BCECPublicKey(String algorithm, ECPublicKeyParameters params, co.org.bouncy.jce.spec.ECParameterSpec spec, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm;
        q = params.getQ();
        if(spec == null)
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(dp.getCurve(), dp.getSeed());
            ecSpec = createSpec(ellipticCurve, dp);
        } else
        {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(spec.getCurve(), spec.getSeed());
            ecSpec = EC5Util.convertSpec(ellipticCurve, spec);
        }
        this.configuration = configuration;
    }

    public BCECPublicKey(String algorithm, ECPublicKeyParameters params, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        this.algorithm = algorithm;
        q = params.getQ();
        ecSpec = null;
        this.configuration = configuration;
    }

    public BCECPublicKey(ECPublicKey key, ProviderConfiguration configuration)
    {
        algorithm = "EC";
        algorithm = key.getAlgorithm();
        ecSpec = key.getParams();
        q = EC5Util.convertPoint(ecSpec, key.getW(), false);
    }

    BCECPublicKey(String algorithm, SubjectPublicKeyInfo info, ProviderConfiguration configuration)
    {
        this.algorithm = "EC";
        this.algorithm = algorithm;
        this.configuration = configuration;
        populateFromPubKeyInfo(info);
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters dp)
    {
        return new ECParameterSpec(ellipticCurve, new ECPoint(dp.getG().getX().toBigInteger(), dp.getG().getY().toBigInteger()), dp.getN(), dp.getH().intValue());
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo info)
    {
        X962Parameters params = new X962Parameters((ASN1Primitive)info.getAlgorithm().getParameters());
        ECCurve curve;
        if(params.isNamedCurve())
        {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)params.getParameters();
            X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
            curve = ecP.getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, ecP.getSeed());
            ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName(oid), ellipticCurve, new ECPoint(ecP.getG().getX().toBigInteger(), ecP.getG().getY().toBigInteger()), ecP.getN(), ecP.getH());
        } else
        if(params.isImplicitlyCA())
        {
            ecSpec = null;
            curve = configuration.getEcImplicitlyCa().getCurve();
        } else
        {
            X9ECParameters ecP = X9ECParameters.getInstance(params.getParameters());
            curve = ecP.getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, ecP.getSeed());
            ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(ecP.getG().getX().toBigInteger(), ecP.getG().getY().toBigInteger()), ecP.getN(), ecP.getH().intValue());
        }
        DERBitString bits = info.getPublicKeyData();
        byte data[] = bits.getBytes();
        ASN1OctetString key = new DEROctetString(data);
        if(data[0] == 4 && data[1] == data.length - 2 && (data[2] == 2 || data[2] == 3))
        {
            int qLength = (new X9IntegerConverter()).getByteLength(curve);
            if(qLength >= data.length - 3)
                try
                {
                    key = (ASN1OctetString)ASN1Primitive.fromByteArray(data);
                }
                catch(IOException ex)
                {
                    throw new IllegalArgumentException("error recovering public key");
                }
        }
        X9ECPoint derQ = new X9ECPoint(curve, key);
        q = derQ.getPoint();
    }

    public String getAlgorithm()
    {
        return algorithm;
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        ASN1Encodable params;
        ECCurve curve;
        if(ecSpec instanceof ECNamedCurveSpec)
        {
            ASN1ObjectIdentifier curveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec)ecSpec).getName());
            if(curveOid == null)
                curveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec)ecSpec).getName());
            params = new X962Parameters(curveOid);
        } else
        if(ecSpec == null)
        {
            params = new X962Parameters(DERNull.INSTANCE);
        } else
        {
            curve = EC5Util.convertCurve(ecSpec.getCurve());
            X9ECParameters ecP = new X9ECParameters(curve, EC5Util.convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf(ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
            params = new X962Parameters(ecP);
        }
        curve = engineGetQ().getCurve();
        ASN1OctetString p = (ASN1OctetString)(new X9ECPoint(curve.createPoint(getQ().getX().toBigInteger(), getQ().getY().toBigInteger(), withCompression))).toASN1Primitive();
        SubjectPublicKeyInfo info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), p.getOctets());
        return KeyUtil.getEncodedSubjectPublicKeyInfo(info);
    }

    private void extractBytes(byte encKey[], int offSet, BigInteger bI)
    {
        byte val[] = bI.toByteArray();
        if(val.length < 32)
        {
            byte tmp[] = new byte[32];
            System.arraycopy(val, 0, tmp, tmp.length - val.length, val.length);
            val = tmp;
        }
        for(int i = 0; i != 32; i++)
            encKey[offSet + i] = val[val.length - 1 - i];

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

    public ECPoint getW()
    {
        return new ECPoint(q.getX().toBigInteger(), q.getY().toBigInteger());
    }

    public co.org.bouncy.math.ec.ECPoint getQ()
    {
        if(ecSpec == null)
        {
            if(q instanceof co.org.bouncy.math.ec.ECPoint.Fp)
                return new co.org.bouncy.math.ec.ECPoint.Fp(null, q.getX(), q.getY());
            else
                return new co.org.bouncy.math.ec.ECPoint.F2m(null, q.getX(), q.getY());
        } else
        {
            return q;
        }
    }

    public co.org.bouncy.math.ec.ECPoint engineGetQ()
    {
        return q;
    }

    co.org.bouncy.jce.spec.ECParameterSpec engineGetSpec()
    {
        if(ecSpec != null)
            return EC5Util.convertSpec(ecSpec, withCompression);
        else
            return configuration.getEcImplicitlyCa();
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("EC Public Key").append(nl);
        buf.append("            X: ").append(q.getX().toBigInteger().toString(16)).append(nl);
        buf.append("            Y: ").append(q.getY().toBigInteger().toString(16)).append(nl);
        return buf.toString();
    }

    public void setPointFormat(String style)
    {
        withCompression = !"UNCOMPRESSED".equalsIgnoreCase(style);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof BCECPublicKey))
        {
            return false;
        } else
        {
            BCECPublicKey other = (BCECPublicKey)o;
            return engineGetQ().equals(other.engineGetQ()) && engineGetSpec().equals(other.engineGetSpec());
        }
    }

    public int hashCode()
    {
        return engineGetQ().hashCode() ^ engineGetSpec().hashCode();
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        byte enc[] = (byte[])(byte[])in.readObject();
        populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(enc)));
        configuration = BouncyCastleProvider.CONFIGURATION;
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(getEncoded());
    }

    static final long serialVersionUID = 0x219f7a8aa3ea4824L;
    private String algorithm;
    private boolean withCompression;
    private transient co.org.bouncy.math.ec.ECPoint q;
    private transient ECParameterSpec ecSpec;
    private transient ProviderConfiguration configuration;
}
