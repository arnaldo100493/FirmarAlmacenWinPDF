// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAValidationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.util.Arrays;

public class DSAValidationParameters
{

    public DSAValidationParameters(byte seed[], int counter)
    {
        this(seed, counter, -1);
    }

    public DSAValidationParameters(byte seed[], int counter, int usageIndex)
    {
        this.seed = seed;
        this.counter = counter;
        this.usageIndex = usageIndex;
    }

    public int getCounter()
    {
        return counter;
    }

    public byte[] getSeed()
    {
        return seed;
    }

    public int getUsageIndex()
    {
        return usageIndex;
    }

    public int hashCode()
    {
        return counter ^ Arrays.hashCode(seed);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DSAValidationParameters))
            return false;
        DSAValidationParameters other = (DSAValidationParameters)o;
        if(other.counter != counter)
            return false;
        else
            return Arrays.areEqual(seed, other.seed);
    }

    private int usageIndex;
    private byte seed[];
    private int counter;
}
