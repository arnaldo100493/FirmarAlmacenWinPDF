// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.params.*;
import java.security.*;
import java.security.interfaces.*;

public class DSAUtil
{

    public DSAUtil()
    {
    }

    public static boolean isDsaOid(ASN1ObjectIdentifier algOid)
    {
        for(int i = 0; i != dsaOids.length; i++)
            if(algOid.equals(dsaOids[i]))
                return true;

        return false;
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof DSAPublicKey)
        {
            DSAPublicKey k = (DSAPublicKey)key;
            return new DSAPublicKeyParameters(k.getY(), new DSAParameters(k.getParams().getP(), k.getParams().getQ(), k.getParams().getG()));
        } else
        {
            throw new InvalidKeyException((new StringBuilder()).append("can't identify DSA public key: ").append(key.getClass().getName()).toString());
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof DSAPrivateKey)
        {
            DSAPrivateKey k = (DSAPrivateKey)key;
            return new DSAPrivateKeyParameters(k.getX(), new DSAParameters(k.getParams().getP(), k.getParams().getQ(), k.getParams().getG()));
        } else
        {
            throw new InvalidKeyException("can't identify DSA private key.");
        }
    }

    public static final ASN1ObjectIdentifier dsaOids[];

    static 
    {
        dsaOids = (new ASN1ObjectIdentifier[] {
            X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1
        });
    }
}
