// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public static class DES$KeyFactory extends BaseSecretKeyFactory
{

    protected KeySpec engineGetKeySpec(SecretKey key, Class keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec == null)
            throw new InvalidKeySpecException("keySpec parameter is null");
        if(key == null)
            throw new InvalidKeySpecException("key parameter is null");
        if(javax/crypto/spec/SecretKeySpec.isAssignableFrom(keySpec))
            return new SecretKeySpec(key.getEncoded(), algName);
        if(javax/crypto/spec/DESKeySpec.isAssignableFrom(keySpec))
        {
            byte bytes[] = key.getEncoded();
            try
            {
                return new DESKeySpec(bytes);
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException(e.toString());
            }
        } else
        {
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DESKeySpec)
        {
            DESKeySpec desKeySpec = (DESKeySpec)keySpec;
            return new SecretKeySpec(desKeySpec.getKey(), "DES");
        } else
        {
            return super.engineGenerateSecret(keySpec);
        }
    }

    public DES$KeyFactory()
    {
        super("DES", null);
    }
}
