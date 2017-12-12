// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import co.org.bouncy.jce.interfaces.ElGamalPublicKey;
import co.org.bouncy.jce.spec.ElGamalPrivateKeySpec;
import co.org.bouncy.jce.spec.ElGamalPublicKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.elgamal:
//            BCElGamalPrivateKey, BCElGamalPublicKey

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof ElGamalPrivateKeySpec)
            return new BCElGamalPrivateKey((ElGamalPrivateKeySpec)keySpec);
        if(keySpec instanceof DHPrivateKeySpec)
            return new BCElGamalPrivateKey((DHPrivateKeySpec)keySpec);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof ElGamalPublicKeySpec)
            return new BCElGamalPublicKey((ElGamalPublicKeySpec)keySpec);
        if(keySpec instanceof DHPublicKeySpec)
            return new BCElGamalPublicKey((DHPublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(javax/crypto/spec/DHPrivateKeySpec) && (key instanceof DHPrivateKey))
        {
            DHPrivateKey k = (DHPrivateKey)key;
            return new DHPrivateKeySpec(k.getX(), k.getParams().getP(), k.getParams().getG());
        }
        if(spec.isAssignableFrom(javax/crypto/spec/DHPublicKeySpec) && (key instanceof DHPublicKey))
        {
            DHPublicKey k = (DHPublicKey)key;
            return new DHPublicKeySpec(k.getY(), k.getParams().getP(), k.getParams().getG());
        } else
        {
            return super.engineGetKeySpec(key, spec);
        }
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if(key instanceof DHPublicKey)
            return new BCElGamalPublicKey((DHPublicKey)key);
        if(key instanceof DHPrivateKey)
            return new BCElGamalPrivateKey((DHPrivateKey)key);
        if(key instanceof ElGamalPublicKey)
            return new BCElGamalPublicKey((ElGamalPublicKey)key);
        if(key instanceof ElGamalPrivateKey)
            return new BCElGamalPrivateKey((ElGamalPrivateKey)key);
        else
            throw new InvalidKeyException("key type unknown");
    }

    public PrivateKey generatePrivate(PrivateKeyInfo info)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = info.getPrivateKeyAlgorithm().getAlgorithm();
        if(algOid.equals(PKCSObjectIdentifiers.dhKeyAgreement))
            return new BCElGamalPrivateKey(info);
        if(algOid.equals(X9ObjectIdentifiers.dhpublicnumber))
            return new BCElGamalPrivateKey(info);
        if(algOid.equals(OIWObjectIdentifiers.elGamalAlgorithm))
            return new BCElGamalPrivateKey(info);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo info)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = info.getAlgorithm().getAlgorithm();
        if(algOid.equals(PKCSObjectIdentifiers.dhKeyAgreement))
            return new BCElGamalPublicKey(info);
        if(algOid.equals(X9ObjectIdentifiers.dhpublicnumber))
            return new BCElGamalPublicKey(info);
        if(algOid.equals(OIWObjectIdentifiers.elGamalAlgorithm))
            return new BCElGamalPublicKey(info);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }
}
