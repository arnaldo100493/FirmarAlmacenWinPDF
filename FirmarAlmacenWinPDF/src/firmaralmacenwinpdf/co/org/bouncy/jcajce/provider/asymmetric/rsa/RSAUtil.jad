// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import co.org.bouncy.crypto.params.RSAPrivateCrtKeyParameters;
import java.security.interfaces.*;

public class RSAUtil
{

    public RSAUtil()
    {
    }

    public static boolean isRsaOid(ASN1ObjectIdentifier algOid)
    {
        for(int i = 0; i != rsaOids.length; i++)
            if(algOid.equals(rsaOids[i]))
                return true;

        return false;
    }

    static RSAKeyParameters generatePublicKeyParameter(RSAPublicKey key)
    {
        return new RSAKeyParameters(false, key.getModulus(), key.getPublicExponent());
    }

    static RSAKeyParameters generatePrivateKeyParameter(RSAPrivateKey key)
    {
        if(key instanceof RSAPrivateCrtKey)
        {
            RSAPrivateCrtKey k = (RSAPrivateCrtKey)key;
            return new RSAPrivateCrtKeyParameters(k.getModulus(), k.getPublicExponent(), k.getPrivateExponent(), k.getPrimeP(), k.getPrimeQ(), k.getPrimeExponentP(), k.getPrimeExponentQ(), k.getCrtCoefficient());
        } else
        {
            RSAPrivateKey k = key;
            return new RSAKeyParameters(true, k.getModulus(), k.getPrivateExponent());
        }
    }

    public static final ASN1ObjectIdentifier rsaOids[];

    static 
    {
        rsaOids = (new ASN1ObjectIdentifier[] {
            PKCSObjectIdentifiers.rsaEncryption, X509ObjectIdentifiers.id_ea_rsa, PKCSObjectIdentifiers.id_RSAES_OAEP, PKCSObjectIdentifiers.id_RSASSA_PSS
        });
    }
}
