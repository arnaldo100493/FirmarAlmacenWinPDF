// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicEntropySourceProvider.java

package co.org.bouncy.crypto.prng;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            EntropySource, BasicEntropySourceProvider

class BasicEntropySourceProvider$1
    implements EntropySource
{

    public boolean isPredictionResistant()
    {
        return BasicEntropySourceProvider.access$000(BasicEntropySourceProvider.this);
    }

    public byte[] getEntropy()
    {
        return BasicEntropySourceProvider.access$100(BasicEntropySourceProvider.this).generateSeed((val$bitsRequired + 7) / 8);
    }

    public int entropySize()
    {
        return val$bitsRequired;
    }

    final int val$bitsRequired;
    final BasicEntropySourceProvider this$0;

    BasicEntropySourceProvider$1()
    {
        this$0 = final_basicentropysourceprovider;
        val$bitsRequired = I.this;
        super();
    }
}
