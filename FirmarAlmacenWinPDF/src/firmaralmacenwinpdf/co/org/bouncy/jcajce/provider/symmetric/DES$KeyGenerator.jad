// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.generators.DESKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public static class DES$KeyGenerator extends BaseKeyGenerator
{

    protected void engineInit(int keySize, SecureRandom random)
    {
        super.engineInit(keySize, random);
    }

    protected SecretKey engineGenerateKey()
    {
        if(uninitialised)
        {
            engine.init(new KeyGenerationParameters(new SecureRandom(), defaultKeySize));
            uninitialised = false;
        }
        return new SecretKeySpec(engine.generateKey(), algName);
    }

    public DES$KeyGenerator()
    {
        super("DES", 64, new DESKeyGenerator());
    }
}
