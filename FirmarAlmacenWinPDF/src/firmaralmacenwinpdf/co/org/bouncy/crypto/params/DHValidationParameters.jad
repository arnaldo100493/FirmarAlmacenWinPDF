// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHValidationParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.util.Arrays;

public class DHValidationParameters
{

    public DHValidationParameters(byte seed[], int counter)
    {
        this.seed = seed;
        this.counter = counter;
    }

    public int getCounter()
    {
        return counter;
    }

    public byte[] getSeed()
    {
        return seed;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof DHValidationParameters))
            return false;
        DHValidationParameters other = (DHValidationParameters)o;
        if(other.counter != counter)
            return false;
        else
            return Arrays.areEqual(seed, other.seed);
    }

    public int hashCode()
    {
        return counter ^ Arrays.hashCode(seed);
    }

    private byte seed[];
    private int counter;
}
