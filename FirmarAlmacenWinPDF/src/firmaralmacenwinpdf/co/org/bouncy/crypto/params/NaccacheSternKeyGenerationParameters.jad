// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NaccacheSternKeyGenerationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.KeyGenerationParameters;
import java.security.SecureRandom;

public class NaccacheSternKeyGenerationParameters extends KeyGenerationParameters
{

    public NaccacheSternKeyGenerationParameters(SecureRandom random, int strength, int certainty, int cntSmallPrimes)
    {
        this(random, strength, certainty, cntSmallPrimes, false);
    }

    public NaccacheSternKeyGenerationParameters(SecureRandom random, int strength, int certainty, int cntSmallPrimes, boolean debug)
    {
        super(random, strength);
        this.debug = false;
        this.certainty = certainty;
        if(cntSmallPrimes % 2 == 1)
            throw new IllegalArgumentException("cntSmallPrimes must be a multiple of 2");
        if(cntSmallPrimes < 30)
        {
            throw new IllegalArgumentException("cntSmallPrimes must be >= 30 for security reasons");
        } else
        {
            this.cntSmallPrimes = cntSmallPrimes;
            this.debug = debug;
            return;
        }
    }

    public int getCertainty()
    {
        return certainty;
    }

    public int getCntSmallPrimes()
    {
        return cntSmallPrimes;
    }

    public boolean isDebug()
    {
        return debug;
    }

    private int certainty;
    private int cntSmallPrimes;
    private boolean debug;
}
