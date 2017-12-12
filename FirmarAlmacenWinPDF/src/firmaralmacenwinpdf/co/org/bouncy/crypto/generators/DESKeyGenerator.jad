// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESKeyGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.params.DESParameters;
import java.security.SecureRandom;

public class DESKeyGenerator extends CipherKeyGenerator
{

    public DESKeyGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        super.init(param);
        if(strength == 0 || strength == 7)
            strength = 8;
        else
        if(strength != 8)
            throw new IllegalArgumentException("DES key must be 64 bits long.");
    }

    public byte[] generateKey()
    {
        byte newKey[] = new byte[8];
        do
        {
            random.nextBytes(newKey);
            DESParameters.setOddParity(newKey);
        } while(DESParameters.isWeakKey(newKey, 0));
        return newKey;
    }
}
