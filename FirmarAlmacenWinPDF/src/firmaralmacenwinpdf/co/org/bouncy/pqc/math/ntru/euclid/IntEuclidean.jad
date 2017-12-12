// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntEuclidean.java

package co.org.bouncy.pqc.math.ntru.euclid;


public class IntEuclidean
{

    private IntEuclidean()
    {
    }

    public static IntEuclidean calculate(int a, int b)
    {
        int x = 0;
        int lastx = 1;
        int y = 1;
        int lasty;
        int temp;
        for(lasty = 0; b != 0; lasty = temp)
        {
            int quotient = a / b;
            temp = a;
            a = b;
            b = temp % b;
            temp = x;
            x = lastx - quotient * x;
            lastx = temp;
            temp = y;
            y = lasty - quotient * y;
        }

        IntEuclidean result = new IntEuclidean();
        result.x = lastx;
        result.y = lasty;
        result.gcd = a;
        return result;
    }

    public int x;
    public int y;
    public int gcd;
}
