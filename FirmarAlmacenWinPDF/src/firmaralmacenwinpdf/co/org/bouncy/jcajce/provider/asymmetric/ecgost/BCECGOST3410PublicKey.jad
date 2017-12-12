// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCECGOST3410PublicKey.java

package co.org.bouncy.jcajce.provider.asymmetric.ecgost;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.cryptopro.ECGOST3410NamedCurves;
import co.org.bouncy.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
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
import co.org.bouncy.jce.ECGOST3410NamedCurveTable;
import co.org.bouncy.jce.interfaces.ECPointEncoder;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECNamedCurveParameterSpec;
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

public class BCECGOST3410PublicKey
    implements ECPublicKey, co.org.bouncy.jce.interfaces.ECPublicKey, ECPointEncoder
{

    public BCECGOST3410PublicKey(BCECGOST3410PublicKey key)
    {
        algorithm = "ECGOST3410";
        q = key.q;
        ecSpec = key.ecSpec;
        withCompression = key.withCompression;
        gostParams = key.gostParams;
    }

    public BCECGOST3410PublicKey(java.security.spec.ECPublicKeySpec spec)
    {
        algorithm = "ECGOST3410";
        ecSpec = spec.getParams();
        q = EC5Util.convertPoint(ecSpec, spec.getW(), false);
    }

    public BCECGOST3410PublicKey(ECPublicKeySpec spec)
    {
        algorithm = "ECGOST3410";
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
                co.org.bouncy.jce.spec.ECParameterSpec s = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
                q = s.getCurve().createPoint(q.getX().toBigInteger(), q.getY().toBigInteger(), false);
            }
            ecSpec = null;
        }
    }

    public BCECGOST3410PublicKey(String algorithm, ECPublicKeyParameters params, ECParameterSpec spec)
    {
        this.algorithm = "ECGOST3410";
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
    }

    public BCECGOST3410PublicKey(String algorithm, ECPublicKeyParameters params, co.org.bouncy.jce.spec.ECParameterSpec spec)
    {
        this.algorithm = "ECGOST3410";
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
    }

    public BCECGOST3410PublicKey(String algorithm, ECPublicKeyParameters params)
    {
        this.algorithm = "ECGOST3410";
        this.algorithm = algorithm;
        q = params.getQ();
        ecSpec = null;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters dp)
    {
        return new ECParameterSpec(ellipticCurve, new ECPoint(dp.getG().getX().toBigInteger(), dp.getG().getY().toBigInteger()), dp.getN(), dp.getH().intValue());
    }

    public BCECGOST3410PublicKey(ECPublicKey key)
    {
        algorithm = "ECGOST3410";
        algorithm = key.getAlgorithm();
        ecSpec = key.getParams();
        q = EC5Util.convertPoint(ecSpec, key.getW(), false);
    }

    BCECGOST3410PublicKey(SubjectPublicKeyInfo info)
    {
        algorithm = "ECGOST3410";
        populateFromPubKeyInfo(info);
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo info)
    {
        if(info.getAlgorithm().getAlgorithm().equals(CryptoProObjectIdentifiers.gostR3410_2001))
        {
            DERBitString bits = info.getPublicKeyData();
            algorithm = "ECGOST3410";
            ASN1OctetString key;
            try
            {
                key = (ASN1OctetString)ASN1Primitive.fromByteArray(bits.getBytes());
            }
            catch(IOException ex)
            {
                throw new IllegalArgumentException("error recovering public key");
            }
            byte keyEnc[] = key.getOctets();
            byte x[] = new byte[32];
            byte y[] = new byte[32];
            for(int i = 0; i != x.length; i++)
                x[i] = keyEnc[31 - i];

            for(int i = 0; i != y.length; i++)
                y[i] = keyEnc[63 - i];

            gostParams = new GOST3410PublicKeyAlgParameters((ASN1Sequence)info.getAlgorithm().getParameters());
            ECNamedCurveParameterSpec spec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(gostParams.getPublicKeyParamSet()));
            ECCurve curve = spec.getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, spec.getSeed());
            q = curve.createPoint(new BigInteger(1, x), new BigInteger(1, y), false);
            ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(gostParams.getPublicKeyParamSet()), ellipticCurve, new ECPoint(spec.getG().getX().toBigInteger(), spec.getG().getY().toBigInteger()), spec.getN(), spec.getH());
        } else
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
                curve = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve();
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
        SubjectPublicKeyInfo info;
        if(algorithm.equals("ECGOST3410"))
        {
            ASN1Encodable params;
            if(gostParams != null)
                params = gostParams;
            else
            if(ecSpec instanceof ECNamedCurveSpec)
            {
                params = new GOST3410PublicKeyAlgParameters(ECGOST3410NamedCurves.getOID(((ECNamedCurveSpec)ecSpec).getName()), CryptoProObjectIdentifiers.gostR3411_94_CryptoProParamSet);
            } else
            {
                ECCurve curve = EC5Util.convertCurve(ecSpec.getCurve());
                X9ECParameters ecP = new X9ECParameters(curve, EC5Util.convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf(ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
                params = new X962Parameters(ecP);
            }
            BigInteger bX = q.getX().toBigInteger();
            BigInteger bY = q.getY().toBigInteger();
            byte encKey[] = new byte[64];
            extractBytes(encKey, 0, bX);
            extractBytes(encKey, 32, bY);
            try
            {
                info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, params), new DEROctetString(encKey));
            }
            catch(IOException e)
            {
                return null;
            }
        } else
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
            info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), p.getOctets());
        }
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
            return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
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
        if(!(o instanceof BCECGOST3410PublicKey))
        {
            return false;
        } else
        {
            BCECGOST3410PublicKey other = (BCECGOST3410PublicKey)o;
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
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(getEncoded());
    }

    static final long serialVersionUID = 0x61823879c4d16022L;
    private String algorithm;
    private boolean withCompression;
    private transient co.org.bouncy.math.ec.ECPoint q;
    private transient ECParameterSpec ecSpec;
    private transient GOST3410PublicKeyAlgParameters gostParams;
}
