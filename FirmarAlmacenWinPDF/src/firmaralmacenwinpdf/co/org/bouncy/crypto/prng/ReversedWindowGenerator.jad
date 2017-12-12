// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReversedWindowGenerator.java

package co.org.bouncy.crypto.prng;


// Referenced classes of package co.org.bouncy.crypto.prng:
//            RandomGenerator

public class ReversedWindowGenerator
    implements RandomGenerator
{

    public ReversedWindowGenerator(RandomGenerator generator, int windowSize)
    {
        if(generator == null)
            throw new IllegalArgumentException("generator cannot be null");
        if(windowSize < 2)
        {
            throw new IllegalArgumentException("windowSize must be at least 2");
        } else
        {
            this.generator = generator;
            window = new byte[windowSize];
            return;
        }
    }

    public void addSeedMaterial(byte seed[])
    {
        synchronized(this)
        {
            windowCount = 0;
            generator.addSeedMaterial(seed);
        }
    }

    public void addSeedMaterial(long seed)
    {
        synchronized(this)
        {
            windowCount = 0;
            generator.addSeedMaterial(seed);
        }
    }

    public void nextBytes(byte bytes[])
    {
        doNextBytes(bytes, 0, bytes.length);
    }

    public void nextBytes(byte bytes[], int start, int len)
    {
        doNextBytes(bytes, start, len);
    }

    private void doNextBytes(byte bytes[], int start, int len)
    {
        synchronized(this)
        {
            for(int done = 0; done < len;)
            {
                if(windowCount < 1)
                {
                    generator.nextBytes(window, 0, window.length);
                    windowCount = window.length;
                }
                bytes[start + done++] = window[--windowCount];
            }

        }
    }

    private final RandomGenerator generator;
    private byte window[];
    private int windowCount;
}
