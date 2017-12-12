// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESedeKeyGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.params.DESedeParameters;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            DESKeyGenerator

public class DESedeKeyGenerator extends DESKeyGenerator
{

    public DESedeKeyGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        random = param.getRandom();
        strength = (param.getStrength() + 7) / 8;
        if(strength == 0 || strength == 21)
            strength = 24;
        else
        if(strength == 14)
            strength = 16;
        else
        if(strength != 24 && strength != 16)
            throw new IllegalArgumentException("DESede key must be 192 or 128 bits long.");
    }

    public byte[] generateKey()
    {
        byte newKey[] = new byte[strength];
        do
        {
            random.nextBytes(newKey);
            DESedeParameters.setOddParity(newKey);
        } while(DESedeParameters.isWeakKey(newKey, 0, newKey.length));
        return newKey;
    }
}
