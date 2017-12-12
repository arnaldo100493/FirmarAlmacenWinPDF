// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SP800SecureRandom.java

package co.org.bouncy.crypto.prng;

import co.org.bouncy.crypto.prng.drbg.SP80090DRBG;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            DRBGProvider, EntropySource

public class SP800SecureRandom extends SecureRandom
{

    SP800SecureRandom(SecureRandom randomSource, EntropySource entropySource, DRBGProvider drbgProvider, boolean predictionResistant)
    {
        this.randomSource = randomSource;
        this.entropySource = entropySource;
        this.drbgProvider = drbgProvider;
        this.predictionResistant = predictionResistant;
    }

    public void setSeed(byte seed[])
    {
        synchronized(this)
        {
            if(randomSource != null)
                randomSource.setSeed(seed);
        }
    }

    public void setSeed(long seed)
    {
        synchronized(this)
        {
            if(randomSource != null)
                randomSource.setSeed(seed);
        }
    }

    public void nextBytes(byte bytes[])
    {
        synchronized(this)
        {
            if(drbg == null)
                drbg = drbgProvider.get(entropySource);
            if(drbg.generate(bytes, null, predictionResistant) < 0)
            {
                drbg.reseed(entropySource.getEntropy());
                drbg.generate(bytes, null, predictionResistant);
            }
        }
    }

    public byte[] generateSeed(int numBytes)
    {
        byte bytes[] = new byte[numBytes];
        nextBytes(bytes);
        return bytes;
    }

    private final DRBGProvider drbgProvider;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;
    private final EntropySource entropySource;
    private SP80090DRBG drbg;
}
