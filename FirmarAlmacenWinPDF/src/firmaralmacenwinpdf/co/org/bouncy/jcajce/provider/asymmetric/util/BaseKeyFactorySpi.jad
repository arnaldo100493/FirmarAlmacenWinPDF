// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseKeyFactorySpi.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;
import java.security.*;
import java.security.spec.*;

public abstract class BaseKeyFactorySpi extends KeyFactorySpi
    implements AsymmetricKeyInfoConverter
{

    public BaseKeyFactorySpi()
    {
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof PKCS8EncodedKeySpec)
            try
            {
                return generatePrivate(PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded()));
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException("encoded key spec not recognised");
            }
        else
            throw new InvalidKeySpecException("key spec not recognised");
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof X509EncodedKeySpec)
            try
            {
                return generatePublic(SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec)keySpec).getEncoded()));
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException("encoded key spec not recognised");
            }
        else
            throw new InvalidKeySpecException("key spec not recognised");
    }

    protected KeySpec engineGetKeySpec(Key key, Class spec)
        throws InvalidKeySpecException
    {
        if(spec.isAssignableFrom(java/security/spec/PKCS8EncodedKeySpec) && key.getFormat().equals("PKCS#8"))
            return new PKCS8EncodedKeySpec(key.getEncoded());
        if(spec.isAssignableFrom(java/security/spec/X509EncodedKeySpec) && key.getFormat().equals("X.509"))
            return new X509EncodedKeySpec(key.getEncoded());
        else
            throw new InvalidKeySpecException((new StringBuilder()).append("not implemented yet ").append(key).append(" ").append(spec).toString());
    }
}
