// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubjectPublicKeyInfoFactory.java

package co.org.bouncy.crypto.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSAPublicKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.*;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;

public class SubjectPublicKeyInfoFactory
{

    public SubjectPublicKeyInfoFactory()
    {
    }

    public static SubjectPublicKeyInfo createSubjectPublicKeyInfo(AsymmetricKeyParameter publicKey)
        throws IOException
    {
        if(publicKey instanceof RSAKeyParameters)
        {
            RSAKeyParameters pub = (RSAKeyParameters)publicKey;
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPublicKey(pub.getModulus(), pub.getExponent()));
        }
        if(publicKey instanceof DSAPublicKeyParameters)
        {
            DSAPublicKeyParameters pub = (DSAPublicKeyParameters)publicKey;
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa), new ASN1Integer(pub.getY()));
        }
        if(publicKey instanceof ECPublicKeyParameters)
        {
            ECPublicKeyParameters pub = (ECPublicKeyParameters)publicKey;
            ECDomainParameters domainParams = pub.getParameters();
            ASN1Encodable params;
            if(domainParams == null)
            {
                params = new X962Parameters(DERNull.INSTANCE);
            } else
            {
                X9ECParameters ecP = new X9ECParameters(domainParams.getCurve(), domainParams.getG(), domainParams.getN(), domainParams.getH(), domainParams.getSeed());
                params = new X962Parameters(ecP);
            }
            ASN1OctetString p = (ASN1OctetString)(new X9ECPoint(pub.getQ())).toASN1Primitive();
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), p.getOctets());
        } else
        {
            throw new IOException("key parameters not recognised.");
        }
    }
}
