// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BigIntegers.java

package co.org.bouncy.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class BigIntegers
{

    public BigIntegers()
    {
    }

    public static byte[] asUnsignedByteArray(BigInteger value)
    {
        byte bytes[] = value.toByteArray();
        if(bytes[0] == 0)
        {
            byte tmp[] = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, tmp, 0, tmp.length);
            return tmp;
        } else
        {
            return bytes;
        }
    }

    public static byte[] asUnsignedByteArray(int length, BigInteger value)
    {
        byte bytes[] = value.toByteArray();
        if(bytes[0] == 0)
            if(bytes.length - 1 > length)
            {
                throw new IllegalArgumentException("standard length exceeded for value");
            } else
            {
                byte tmp[] = new byte[length];
                System.arraycopy(bytes, 1, tmp, tmp.length - (bytes.length - 1), bytes.length - 1);
                return tmp;
            }
        if(bytes.length == length)
            return bytes;
        if(bytes.length > length)
        {
            throw new IllegalArgumentException("standard length exceeded for value");
        } else
        {
            byte tmp[] = new byte[length];
            System.arraycopy(bytes, 0, tmp, tmp.length - bytes.length, bytes.length);
            return tmp;
        }
    }

    public static BigInteger createRandomInRange(BigInteger min, BigInteger max, SecureRandom random)
    {
        int cmp = min.compareTo(max);
        if(cmp >= 0)
            if(cmp > 0)
                throw new IllegalArgumentException("'min' may not be greater than 'max'");
            else
                return min;
        if(min.bitLength() > max.bitLength() / 2)
            return createRandomInRange(ZERO, max.subtract(min), random).add(min);
        for(int i = 0; i < 1000; i++)
        {
            BigInteger x = new BigInteger(max.bitLength(), random);
            if(x.compareTo(min) >= 0 && x.compareTo(max) <= 0)
                return x;
        }

        return (new BigInteger(max.subtract(min).bitLength() - 1, random)).add(min);
    }

    private static final int MAX_ITERATIONS = 1000;
    private static final BigInteger ZERO = BigInteger.valueOf(0L);

}
