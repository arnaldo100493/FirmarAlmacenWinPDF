// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrivateKeyInfoFactory.java

package co.org.bouncy.crypto.util;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DSAParameter;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.*;
import java.io.IOException;

public class PrivateKeyInfoFactory
{

    public PrivateKeyInfoFactory()
    {
    }

    public static PrivateKeyInfo createPrivateKeyInfo(AsymmetricKeyParameter privateKey)
        throws IOException
    {
        if(privateKey instanceof RSAKeyParameters)
        {
            RSAPrivateCrtKeyParameters priv = (RSAPrivateCrtKeyParameters)privateKey;
            return new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPrivateKey(priv.getModulus(), priv.getPublicExponent(), priv.getExponent(), priv.getP(), priv.getQ(), priv.getDP(), priv.getDQ(), priv.getQInv()));
        }
        if(privateKey instanceof DSAPrivateKeyParameters)
        {
            DSAPrivateKeyParameters priv = (DSAPrivateKeyParameters)privateKey;
            DSAParameters params = priv.getParameters();
            return new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(params.getP(), params.getQ(), params.getG())), new ASN1Integer(priv.getX()));
        } else
        {
            throw new IOException("key parameters not recognised.");
        }
    }
}
