// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dsa:
//            BCDSAPublicKey, BCDSAPrivateKey, DSAUtil

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(java/security/spec/DSAPublicKeySpec) && (key instanceof DSAPublicKey))
        {
            DSAPublicKey k = (DSAPublicKey)key;
            return new DSAPublicKeySpec(k.getY(), k.getParams().getP(), k.getParams().getQ(), k.getParams().getG());
        }
        if(spec.isAssignableFrom(java/security/spec/DSAPrivateKeySpec) && (key instanceof DSAPrivateKey))
        {
            DSAPrivateKey k = (DSAPrivateKey)key;
            return new DSAPrivateKeySpec(k.getX(), k.getParams().getP(), k.getParams().getQ(), k.getParams().getG());
        } else
        {
            return super.engineGetKeySpec(key, spec);
        }
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key instanceof DSAPublicKey)
            return new BCDSAPublicKey((DSAPublicKey)key);
        if(key instanceof DSAPrivateKey)
            return new BCDSAPrivateKey((DSAPrivateKey)key);
        else
            throw new InvalidKeyException("key type unknown");
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(DSAUtil.isDsaOid(algOid))
            return new BCDSAPrivateKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(DSAUtil.isDsaOid(algOid))
            return new BCDSAPublicKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DSAPrivateKeySpec)
            return new BCDSAPrivateKey((DSAPrivateKeySpec)keySpec);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DSAPublicKeySpec)
            return new BCDSAPublicKey((DSAPublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }
}
