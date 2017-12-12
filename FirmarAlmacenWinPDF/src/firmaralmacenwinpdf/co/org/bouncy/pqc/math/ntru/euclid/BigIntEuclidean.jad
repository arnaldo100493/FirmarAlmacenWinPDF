// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BigIntEuclidean.java

package co.org.bouncy.pqc.math.ntru.euclid;

import java.math.BigInteger;

public class BigIntEuclidean
{

    private BigIntEuclidean()
    {
    }

    public static BigIntEuclidean calculate(BigInteger a, BigInteger b)
    {
        BigInteger x = BigInteger.ZERO;
        BigInteger lastx = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger lasty;
        BigInteger temp;
        for(lasty = BigInteger.ZERO; !b.equals(BigInteger.ZERO); lasty = temp)
        {
            BigInteger quotientAndRemainder[] = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];
            temp = a;
            a = b;
            b = quotientAndRemainder[1];
            temp = x;
            x = lastx.subtract(quotient.multiply(x));
            lastx = temp;
            temp = y;
            y = lasty.subtract(quotient.multiply(y));
        }

        BigIntEuclidean result = new BigIntEuclidean();
        result.x = lastx;
        result.y = lasty;
        result.gcd = a;
        return result;
    }

    public BigInteger x;
    public BigInteger y;
    public BigInteger gcd;
}
