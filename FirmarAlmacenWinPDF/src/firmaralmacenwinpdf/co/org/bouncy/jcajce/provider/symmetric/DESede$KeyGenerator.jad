// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESede.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.generators.DESedeKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DESede

public static class DESede$KeyGenerator extends BaseKeyGenerator
{

    protected void engineInit(int keySize, SecureRandom random)
    {
        super.engineInit(keySize, random);
        keySizeSet = true;
    }

    protected SecretKey engineGenerateKey()
    {
        if(uninitialised)
        {
            engine.init(new KeyGenerationParameters(new SecureRandom(), defaultKeySize));
            uninitialised = false;
        }
        if(!keySizeSet)
        {
            byte k[] = engine.generateKey();
            System.arraycopy(k, 0, k, 16, 8);
            return new SecretKeySpec(k, algName);
        } else
        {
            return new SecretKeySpec(engine.generateKey(), algName);
        }
    }

    private boolean keySizeSet;

    public DESede$KeyGenerator()
    {
        super("DESede", 192, new DESedeKeyGenerator());
        keySizeSet = false;
    }
}
