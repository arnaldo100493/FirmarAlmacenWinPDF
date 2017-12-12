// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadedSeedGenerator.java

package co.org.bouncy.crypto.prng;


public class ThreadedSeedGenerator
{
    private class SeedGenerator
        implements Runnable
    {

        public void run()
        {
            while(!stop) 
                counter++;
        }

        public byte[] generateSeed(int numbytes, boolean fast)
        {
            Thread t = new Thread(this);
            byte result[] = new byte[numbytes];
            counter = 0;
            stop = false;
            int last = 0;
            t.start();
            int end;
            if(fast)
                end = numbytes;
            else
                end = numbytes * 8;
            for(int i = 0; i < end; i++)
            {
                while(counter == last) 
                    try
                    {
                        Thread.sleep(1L);
                    }
                    catch(InterruptedException e) { }
                last = counter;
                if(fast)
                {
                    result[i] = (byte)(last & 0xff);
                } else
                {
                    int bytepos = i / 8;
                    result[bytepos] = (byte)(result[bytepos] << 1 | last & 1);
                }
            }

            stop = true;
            return result;
        }

        private volatile int counter;
        private volatile boolean stop;
        final ThreadedSeedGenerator this$0;

        private SeedGenerator()
        {
            this$0 = ThreadedSeedGenerator.this;
            super();
            counter = 0;
            stop = false;
        }

    }


    public ThreadedSeedGenerator()
    {
    }

    public byte[] generateSeed(int numBytes, boolean fast)
    {
        SeedGenerator gen = new SeedGenerator();
        return gen.generateSeed(numBytes, fast);
    }
}
