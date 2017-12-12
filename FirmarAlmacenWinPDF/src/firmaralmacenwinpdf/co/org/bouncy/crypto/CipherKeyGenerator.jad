// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherKeyGenerator.java

package co.org.bouncy.crypto;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto:
//            KeyGenerationParameters

public class CipherKeyGenerator
{

    public CipherKeyGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        random = param.getRandom();
        strength = (param.getStrength() + 7) / 8;
    }

    public byte[] generateKey()
    {
        byte key[] = new byte[strength];
        random.nextBytes(key);
        return key;
    }

    protected SecureRandom random;
    protected int strength;
}
