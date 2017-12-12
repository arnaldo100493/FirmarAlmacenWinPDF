// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RandUtils.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class RandUtils
{

    public RandUtils()
    {
    }

    static int nextInt(SecureRandom rand, int n)
    {
        if((n & -n) == n)
            return (int)((long)n * (long)(rand.nextInt() >>> 1) >> 31);
        int bits;
        int value;
        do
        {
            bits = rand.nextInt() >>> 1;
            value = bits % n;
        } while((bits - value) + (n - 1) < 0);
        return value;
    }
}
