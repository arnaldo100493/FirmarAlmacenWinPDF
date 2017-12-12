// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESede.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DESede

public static class DESede$KeyFactory extends BaseSecretKeyFactory
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
        if(!javax/crypto/spec/DESedeKeySpec.isAssignableFrom(keySpec))
            break MISSING_BLOCK_LABEL_141;
        byte bytes[] = key.getEncoded();
        try
        {
            if(bytes.length == 16)
            {
                byte longKey[] = new byte[24];
                System.arraycopy(bytes, 0, longKey, 0, 16);
                System.arraycopy(bytes, 0, longKey, 16, 8);
                return new DESedeKeySpec(longKey);
            }
        }
        catch(Exception e)
        {
            throw new InvalidKeySpecException(e.toString());
        }
        return new DESedeKeySpec(bytes);
        throw new InvalidKeySpecException("Invalid KeySpec");
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof DESedeKeySpec)
        {
            DESedeKeySpec desKeySpec = (DESedeKeySpec)keySpec;
            return new SecretKeySpec(desKeySpec.getKey(), "DESede");
        } else
        {
            return super.engineGenerateSecret(keySpec);
        }
    }

    public DESede$KeyFactory()
    {
        super("DESede", null);
    }
}
