// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BigIntUtils.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.math.BigInteger;

public final class BigIntUtils
{

    private BigIntUtils()
    {
    }

    public static boolean equals(BigInteger a[], BigInteger b[])
    {
        int flag = 0;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++)
            flag |= a[i].compareTo(b[i]);

        return flag == 0;
    }

    public static void fill(BigInteger array[], BigInteger value)
    {
        for(int i = array.length - 1; i >= 0; i--)
            array[i] = value;

    }

    public static BigInteger[] subArray(BigInteger input[], int start, int end)
    {
        BigInteger result[] = new BigInteger[end - start];
        System.arraycopy(input, start, result, 0, end - start);
        return result;
    }

    public static int[] toIntArray(BigInteger input[])
    {
        int result[] = new int[input.length];
        for(int i = 0; i < input.length; i++)
            result[i] = input[i].intValue();

        return result;
    }

    public static int[] toIntArrayModQ(int q, BigInteger input[])
    {
        BigInteger bq = BigInteger.valueOf(q);
        int result[] = new int[input.length];
        for(int i = 0; i < input.length; i++)
            result[i] = input[i].mod(bq).intValue();

        return result;
    }

    public static byte[] toMinimalByteArray(BigInteger value)
    {
        byte valBytes[] = value.toByteArray();
        if(valBytes.length == 1 || (value.bitLength() & 7) != 0)
        {
            return valBytes;
        } else
        {
            byte result[] = new byte[value.bitLength() >> 3];
            System.arraycopy(valBytes, 1, result, 0, result.length);
            return result;
        }
    }
}
