// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrivateKeyFactory.java

package co.org.bouncy.crypto.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.nist.NISTNamedCurves;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.teletrust.TeleTrusTNamedCurves;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DSAParameter;
import co.org.bouncy.asn1.x9.*;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class PrivateKeyFactory
{

    public PrivateKeyFactory()
    {
    }

    public static AsymmetricKeyParameter createKey(byte privateKeyInfoData[])
        throws IOException
    {
        return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(privateKeyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr)
        throws IOException
    {
        return createKey(PrivateKeyInfo.getInstance((new ASN1InputStream(inStr)).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo keyInfo)
        throws IOException
    {
        AlgorithmIdentifier algId = keyInfo.getPrivateKeyAlgorithm();
        if(algId.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption))
        {
            RSAPrivateKey keyStructure = RSAPrivateKey.getInstance(keyInfo.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(keyStructure.getModulus(), keyStructure.getPublicExponent(), keyStructure.getPrivateExponent(), keyStructure.getPrime1(), keyStructure.getPrime2(), keyStructure.getExponent1(), keyStructure.getExponent2(), keyStructure.getCoefficient());
        }
        if(algId.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement))
        {
            DHParameter params = DHParameter.getInstance(algId.getParameters());
            ASN1Integer derX = (ASN1Integer)keyInfo.parsePrivateKey();
            BigInteger lVal = params.getL();
            int l = lVal != null ? lVal.intValue() : 0;
            DHParameters dhParams = new DHParameters(params.getP(), params.getG(), null, l);
            return new DHPrivateKeyParameters(derX.getValue(), dhParams);
        }
        if(algId.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm))
        {
            ElGamalParameter params = new ElGamalParameter((ASN1Sequence)algId.getParameters());
            ASN1Integer derX = (ASN1Integer)keyInfo.parsePrivateKey();
            return new ElGamalPrivateKeyParameters(derX.getValue(), new ElGamalParameters(params.getP(), params.getG()));
        }
        if(algId.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa))
        {
            ASN1Integer derX = (ASN1Integer)keyInfo.parsePrivateKey();
            ASN1Encodable de = algId.getParameters();
            DSAParameters parameters = null;
            if(de != null)
            {
                DSAParameter params = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params.getP(), params.getQ(), params.getG());
            }
            return new DSAPrivateKeyParameters(derX.getValue(), parameters);
        }
        if(algId.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey))
        {
            X962Parameters params = new X962Parameters((ASN1Primitive)algId.getParameters());
            X9ECParameters x9;
            if(params.isNamedCurve())
            {
                ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
                x9 = X962NamedCurves.getByOID(oid);
                if(x9 == null)
                {
                    x9 = SECNamedCurves.getByOID(oid);
                    if(x9 == null)
                    {
                        x9 = NISTNamedCurves.getByOID(oid);
                        if(x9 == null)
                            x9 = TeleTrusTNamedCurves.getByOID(oid);
                    }
                }
            } else
            {
                x9 = X9ECParameters.getInstance(params.getParameters());
            }
            ECPrivateKey ec = ECPrivateKey.getInstance(keyInfo.parsePrivateKey());
            BigInteger d = ec.getKey();
            ECDomainParameters dParams = new ECDomainParameters(x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            return new ECPrivateKeyParameters(d, dParams);
        } else
        {
            throw new RuntimeException("algorithm identifier in key not recognised");
        }
    }
}
