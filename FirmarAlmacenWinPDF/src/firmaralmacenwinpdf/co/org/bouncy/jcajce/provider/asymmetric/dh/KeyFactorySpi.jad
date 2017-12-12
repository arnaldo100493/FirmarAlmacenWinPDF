// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dh:
//            BCDHPublicKey, BCDHPrivateKey

public class KeyFactorySpi extends BaseKeyFactorySpi
{

    public KeyFactorySpi()
    {
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
            return new BCDHPublicKey((DHPublicKey)key);
        if(key instanceof DHPrivateKey)
            return new BCDHPrivateKey((DHPrivateKey)key);
        else
            throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DHPrivateKeySpec)
            return new BCDHPrivateKey((DHPrivateKeySpec)keySpec);
        else
            return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DHPublicKeySpec)
            return new BCDHPublicKey((DHPublicKeySpec)keySpec);
        else
            return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if(algOid.equals(PKCSObjectIdentifiers.dhKeyAgreement))
            return new BCDHPrivateKey(keyInfo);
        if(algOid.equals(X9ObjectIdentifiers.dhpublicnumber))
            return new BCDHPrivateKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        ASN1ObjectIdentifier algOid = keyInfo.getAlgorithm().getAlgorithm();
        if(algOid.equals(PKCSObjectIdentifiers.dhKeyAgreement))
            return new BCDHPublicKey(keyInfo);
        if(algOid.equals(X9ObjectIdentifiers.dhpublicnumber))
            return new BCDHPublicKey(keyInfo);
        else
            throw new IOException((new StringBuilder()).append("algorithm identifier ").append(algOid).append(" in key not recognised").toString());
    }
}
