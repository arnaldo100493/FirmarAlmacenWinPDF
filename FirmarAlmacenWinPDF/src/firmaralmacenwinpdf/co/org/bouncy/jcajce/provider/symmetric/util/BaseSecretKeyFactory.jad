// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseSecretKeyFactory.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import java.lang.reflect.Constructor;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            PBE

public class BaseSecretKeyFactory extends SecretKeyFactorySpi
    implements PBE
{

    protected BaseSecretKeyFactory(String algName, ASN1ObjectIdentifier algOid)
    {
        this.algName = algName;
        this.algOid = algOid;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof SecretKeySpec)
            return (SecretKey)keySpec;
        else
            throw new InvalidKeySpecException("Invalid KeySpec");
    }

    protected KeySpec engineGetKeySpec(SecretKey key, Class keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec == null)
            throw new InvalidKeySpecException("keySpec parameter is null");
        if(key == null)
            throw new InvalidKeySpecException("key parameter is null");
        if(javax/crypto/spec/SecretKeySpec.isAssignableFrom(keySpec))
            return new SecretKeySpec(key.getEncoded(), algName);
        try
        {
            Class parameters[] = {
                [B
            };
            Constructor c = keySpec.getConstructor(parameters);
            Object p[] = new Object[1];
            p[0] = key.getEncoded();
            return (KeySpec)c.newInstance(p);
        }
        catch(Exception e)
        {
            throw new InvalidKeySpecException(e.toString());
        }
    }

    protected SecretKey engineTranslateKey(SecretKey key)
        throws InvalidKeyException
    {
        if(key == null)
            throw new InvalidKeyException("key parameter is null");
        if(!key.getAlgorithm().equalsIgnoreCase(algName))
            throw new InvalidKeyException((new StringBuilder()).append("Key not of type ").append(algName).append(".").toString());
        else
            return new SecretKeySpec(key.getEncoded(), algName);
    }

    protected String algName;
    protected ASN1ObjectIdentifier algOid;
}
