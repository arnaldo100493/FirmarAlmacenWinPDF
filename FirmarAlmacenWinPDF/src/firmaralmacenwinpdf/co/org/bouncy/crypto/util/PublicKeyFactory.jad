// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicKeyFactory.java

package co.org.bouncy.crypto.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.nist.NISTNamedCurves;
import co.org.bouncy.asn1.oiw.ElGamalParameter;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.teletrust.TeleTrusTNamedCurves;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.*;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class PublicKeyFactory
{

    public PublicKeyFactory()
    {
    }

    public static AsymmetricKeyParameter createKey(byte keyInfoData[])
        throws IOException
    {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(keyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr)
        throws IOException
    {
        return createKey(SubjectPublicKeyInfo.getInstance((new ASN1InputStream(inStr)).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        AlgorithmIdentifier algId = keyInfo.getAlgorithm();
        if(algId.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption) || algId.getAlgorithm().equals(X509ObjectIdentifiers.id_ea_rsa))
        {
            RSAPublicKey pubKey = RSAPublicKey.getInstance(keyInfo.parsePublicKey());
            return new RSAKeyParameters(false, pubKey.getModulus(), pubKey.getPublicExponent());
        }
        if(algId.getAlgorithm().equals(X9ObjectIdentifiers.dhpublicnumber))
        {
            DHPublicKey dhPublicKey = DHPublicKey.getInstance(keyInfo.parsePublicKey());
            BigInteger y = dhPublicKey.getY().getValue();
            DHDomainParameters dhParams = DHDomainParameters.getInstance(algId.getParameters());
            BigInteger p = dhParams.getP().getValue();
            BigInteger g = dhParams.getG().getValue();
            BigInteger q = dhParams.getQ().getValue();
            BigInteger j = null;
            if(dhParams.getJ() != null)
                j = dhParams.getJ().getValue();
            DHValidationParameters validation = null;
            DHValidationParms dhValidationParms = dhParams.getValidationParms();
            if(dhValidationParms != null)
            {
                byte seed[] = dhValidationParms.getSeed().getBytes();
                BigInteger pgenCounter = dhValidationParms.getPgenCounter().getValue();
                validation = new DHValidationParameters(seed, pgenCounter.intValue());
            }
            return new DHPublicKeyParameters(y, new DHParameters(p, g, q, j, validation));
        }
        if(algId.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement))
        {
            DHParameter params = DHParameter.getInstance(algId.getParameters());
            ASN1Integer derY = (ASN1Integer)keyInfo.parsePublicKey();
            BigInteger lVal = params.getL();
            int l = lVal != null ? lVal.intValue() : 0;
            DHParameters dhParams = new DHParameters(params.getP(), params.getG(), null, l);
            return new DHPublicKeyParameters(derY.getValue(), dhParams);
        }
        if(algId.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm))
        {
            ElGamalParameter params = new ElGamalParameter((ASN1Sequence)algId.getParameters());
            ASN1Integer derY = (ASN1Integer)keyInfo.parsePublicKey();
            return new ElGamalPublicKeyParameters(derY.getValue(), new ElGamalParameters(params.getP(), params.getG()));
        }
        if(algId.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa) || algId.getAlgorithm().equals(OIWObjectIdentifiers.dsaWithSHA1))
        {
            ASN1Integer derY = (ASN1Integer)keyInfo.parsePublicKey();
            ASN1Encodable de = algId.getParameters();
            DSAParameters parameters = null;
            if(de != null)
            {
                DSAParameter params = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params.getP(), params.getQ(), params.getG());
            }
            return new DSAPublicKeyParameters(derY.getValue(), parameters);
        }
        if(algId.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey))
        {
            X962Parameters params = new X962Parameters((ASN1Primitive)algId.getParameters());
            X9ECParameters x9;
            if(params.isNamedCurve())
            {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)params.getParameters();
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
            co.org.bouncy.asn1.ASN1OctetString key = new DEROctetString(keyInfo.getPublicKeyData().getBytes());
            X9ECPoint derQ = new X9ECPoint(x9.getCurve(), key);
            ECDomainParameters dParams = new ECDomainParameters(x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            return new ECPublicKeyParameters(derQ.getPoint(), dParams);
        } else
        {
            throw new RuntimeException("algorithm identifier in key not recognised");
        }
    }
}
