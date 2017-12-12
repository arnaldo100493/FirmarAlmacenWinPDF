// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.jcajce.provider.symmetric.util.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.digest:
//            SHA1

public static class SHA1$BasePBKDF2WithHmacSHA1 extends BaseSecretKeyFactory
{

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof PBEKeySpec)
        {
            PBEKeySpec pbeSpec = (PBEKeySpec)keySpec;
            if(pbeSpec.getSalt() == null)
                throw new InvalidKeySpecException("missing required salt");
            if(pbeSpec.getIterationCount() <= 0)
                throw new InvalidKeySpecException((new StringBuilder()).append("positive iteration count required: ").append(pbeSpec.getIterationCount()).toString());
            if(pbeSpec.getKeyLength() <= 0)
                throw new InvalidKeySpecException((new StringBuilder()).append("positive key length required: ").append(pbeSpec.getKeyLength()).toString());
            if(pbeSpec.getPassword().length == 0)
            {
                throw new IllegalArgumentException("password empty");
            } else
            {
                int digest = 1;
                int keySize = pbeSpec.getKeyLength();
                int ivSize = -1;
                co.org.bouncy.crypto.CipherParameters param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEMacParameters(pbeSpec, scheme, digest, keySize);
                return new BCPBEKey(algName, algOid, scheme, digest, keySize, ivSize, pbeSpec, param);
            }
        } else
        {
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    private int scheme;

    public SHA1$BasePBKDF2WithHmacSHA1(String name, int scheme)
    {
        super(name, PKCSObjectIdentifiers.id_PBKDF2);
        this.scheme = scheme;
    }
}
