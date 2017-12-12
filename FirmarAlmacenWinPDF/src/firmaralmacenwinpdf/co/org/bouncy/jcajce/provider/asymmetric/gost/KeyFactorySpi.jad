// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.gost;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import co.org.bouncy.jce.interfaces.*;
import co.org.bouncy.jce.spec.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.gost:
//            BCGOST3410PublicKey, BCGOST3410PrivateKey

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(co/org/bouncy/jce/spec/GOST3410PublicKeySpec) && (key instanceof GOST3410PublicKey))
        {
            GOST3410PublicKey k = (GOST3410PublicKey)key;
            GOST3410PublicKeyParameterSetSpec parameters = k.getParameters().getPublicKeyParameters();
            return new GOST3410PublicKeySpec(k.getY(), parameters.getP(), parameters.getQ(), parameters.getA());
        }
        if(spec.isAssignableFrom(co/org/bouncy/jce/spec/GOST3410PrivateKeySpec) && (key instanceof GOST3410PrivateKey))
        {
            GOST3410PrivateKey k = (GOST3410PrivateKey)key;
            GOST3410PublicKeyParameterSetSpec parameters = k.getParameters().getPublicKeyParameters();
            return new GOST3410PrivateKeySpec(k.getX(), parameters.getP(), parameters.getQ(), parameters.getA());
        } else
        {
            return super.engineGetKeySpec(key, spec);
        }
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key instanceof GOST3410PublicKey)
            return new BCGOST3410PublicKey((GOST3410PublicKey)key);
        if(key instanceof GOST3410PrivateKey)
            return new BCGOST3410PrivateKey((GOST3410PrivateKey)key);
        else
            throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof GOST3410PrivateKeySpec)
            return new BCGOST3410PrivateKey((GOST3410PrivateKeySpec)keySpec);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof GOST3410PublicKeySpec)
            return new BCGOST3410PublicKey((GOST3410PublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(algOid.equals(CryptoProObjectIdentifiers.gostR3410_94))
            return new BCGOST3410PrivateKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(algOid.equals(CryptoProObjectIdentifiers.gostR3410_94))
            return new BCGOST3410PublicKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }
}
