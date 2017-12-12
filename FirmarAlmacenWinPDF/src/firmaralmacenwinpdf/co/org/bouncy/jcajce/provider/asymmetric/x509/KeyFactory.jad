// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFactory.java

package co.org.bouncy.jcajce.provider.asymmetric.x509;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import java.security.*;
import java.security.spec.*;

public class KeyFactory extends KeyFactorySpi
{

    public KeyFactory()
    {
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(!(keySpec instanceof PKCS8EncodedKeySpec))
            break MISSING_BLOCK_LABEL_75;
        PrivateKeyInfo info;
        try
        {
            info = PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded());
            PrivateKey key = BouncyCastleProvider.getPrivateKey(info);
            if(key != null)
                return key;
        }
        catch(Exception e)
        {
            throw new InvalidKeySpecException(e.toString());
        }
        throw new InvalidKeySpecException((new StringBuilder()).append("no factory found for OID: ").append(info.getPrivateKeyAlgorithm().getAlgorithm()).toString());
        throw new InvalidKeySpecException((new StringBuilder()).append("Unknown KeySpec type: ").append(keySpec.getClass().getName()).toString());
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(!(keySpec instanceof X509EncodedKeySpec))
            break MISSING_BLOCK_LABEL_75;
        SubjectPublicKeyInfo info;
        try
        {
            info = SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec)keySpec).getEncoded());
            PublicKey key = BouncyCastleProvider.getPublicKey(info);
            if(key != null)
                return key;
        }
        catch(Exception e)
        {
            throw new InvalidKeySpecException(e.toString());
        }
        throw new InvalidKeySpecException((new StringBuilder()).append("no factory found for OID: ").append(info.getAlgorithm().getAlgorithm()).toString());
        throw new InvalidKeySpecException((new StringBuilder()).append("Unknown KeySpec type: ").append(keySpec.getClass().getName()).toString());
    }

    protected KeySpec engineGetKeySpec(Key key, Class keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec.isAssignableFrom(java/security/spec/PKCS8EncodedKeySpec) && key.getFormat().equals("PKCS#8"))
            return new PKCS8EncodedKeySpec(key.getEncoded());
        if(keySpec.isAssignableFrom(java/security/spec/X509EncodedKeySpec) && key.getFormat().equals("X.509"))
            return new X509EncodedKeySpec(key.getEncoded());
        else
            throw new InvalidKeySpecException((new StringBuilder()).append("not implemented yet ").append(key).append(" ").append(keySpec).toString());
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        throw new InvalidKeyException((new StringBuilder()).append("not implemented yet ").append(key).toString());
    }
}
