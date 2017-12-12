// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicEntropySourceProvider.java

package co.org.bouncy.crypto.prng;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            EntropySourceProvider, EntropySource

public class BasicEntropySourceProvider
    implements EntropySourceProvider
{

    public BasicEntropySourceProvider(SecureRandom random, boolean isPredictionResistant)
    {
        _sr = random;
        _predictionResistant = isPredictionResistant;
    }

    public EntropySource get(final int bitsRequired)
    {
        return new EntropySource() {

            public boolean isPredictionResistant()
            {
                return _predictionResistant;
            }

            public byte[] getEntropy()
            {
                return _sr.generateSeed((bitsRequired + 7) / 8);
            }

            public int entropySize()
            {
                return bitsRequired;
            }

            final int val$bitsRequired;
            final BasicEntropySourceProvider this$0;

            
            {
                this$0 = BasicEntropySourceProvider.this;
                bitsRequired = i;
                super();
            }
        }
;
    }

    private final SecureRandom _sr;
    private final boolean _predictionResistant;


}
