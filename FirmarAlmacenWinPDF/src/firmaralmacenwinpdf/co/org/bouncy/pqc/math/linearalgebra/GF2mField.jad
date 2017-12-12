// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2mField.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            PolynomialRingGF2, LittleEndianConversions, RandUtils

public class GF2mField
{

    public GF2mField(int degree)
    {
        this.degree = 0;
        if(degree >= 32)
            throw new IllegalArgumentException(" Error: the degree of field is too large ");
        if(degree < 1)
        {
            throw new IllegalArgumentException(" Error: the degree of field is non-positive ");
        } else
        {
            this.degree = degree;
            polynomial = PolynomialRingGF2.getIrreduciblePolynomial(degree);
            return;
        }
    }

    public GF2mField(int degree, int poly)
    {
        this.degree = 0;
        if(degree != PolynomialRingGF2.degree(poly))
            throw new IllegalArgumentException(" Error: the degree is not correct");
        if(!PolynomialRingGF2.isIrreducible(poly))
        {
            throw new IllegalArgumentException(" Error: given polynomial is reducible");
        } else
        {
            this.degree = degree;
            polynomial = poly;
            return;
        }
    }

    public GF2mField(byte enc[])
    {
        degree = 0;
        if(enc.length != 4)
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        polynomial = LittleEndianConversions.OS2IP(enc);
        if(!PolynomialRingGF2.isIrreducible(polynomial))
        {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        } else
        {
            degree = PolynomialRingGF2.degree(polynomial);
            return;
        }
    }

    public GF2mField(GF2mField field)
    {
        degree = 0;
        degree = field.degree;
        polynomial = field.polynomial;
    }

    public int getDegree()
    {
        return degree;
    }

    public int getPolynomial()
    {
        return polynomial;
    }

    public byte[] getEncoded()
    {
        return LittleEndianConversions.I2OSP(polynomial);
    }

    public int add(int a, int b)
    {
        return a ^ b;
    }

    public int mult(int a, int b)
    {
        return PolynomialRingGF2.modMultiply(a, b, polynomial);
    }

    public int exp(int a, int k)
    {
        if(a == 0)
            return 0;
        if(a == 1)
            return 1;
        int result = 1;
        if(k < 0)
        {
            a = inverse(a);
            k = -k;
        }
        for(; k != 0; k >>>= 1)
        {
            if((k & 1) == 1)
                result = mult(result, a);
            a = mult(a, a);
        }

        return result;
    }

    public int inverse(int a)
    {
        int d = (1 << degree) - 2;
        return exp(a, d);
    }

    public int sqRoot(int a)
    {
        for(int i = 1; i < degree; i++)
            a = mult(a, a);

        return a;
    }

    public int getRandomElement(SecureRandom sr)
    {
        int result = RandUtils.nextInt(sr, 1 << degree);
        return result;
    }

    public int getRandomNonZeroElement()
    {
        return getRandomNonZeroElement(new SecureRandom());
    }

    public int getRandomNonZeroElement(SecureRandom sr)
    {
        int controltime = 0x100000;
        int count = 0;
        int result;
        for(result = RandUtils.nextInt(sr, 1 << degree); result == 0 && count < controltime; count++)
            result = RandUtils.nextInt(sr, 1 << degree);

        if(count == controltime)
            result = 1;
        return result;
    }

    public boolean isElementOfThisField(int e)
    {
        if(degree == 31)
            return e >= 0;
        else
            return e >= 0 && e < 1 << degree;
    }

    public String elementToStr(int a)
    {
        String s = "";
        for(int i = 0; i < degree; i++)
        {
            if(((byte)a & 1) == 0)
                s = (new StringBuilder()).append("0").append(s).toString();
            else
                s = (new StringBuilder()).append("1").append(s).toString();
            a >>>= 1;
        }

        return s;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof GF2mField))
            return false;
        GF2mField otherField = (GF2mField)other;
        return degree == otherField.degree && polynomial == otherField.polynomial;
    }

    public int hashCode()
    {
        return polynomial;
    }

    public String toString()
    {
        String str = (new StringBuilder()).append("Finite Field GF(2^").append(degree).append(") = ").append("GF(2)[X]/<").append(polyToString(polynomial)).append("> ").toString();
        return str;
    }

    private static String polyToString(int p)
    {
        String str = "";
        if(p == 0)
        {
            str = "0";
        } else
        {
            byte b = (byte)(p & 1);
            if(b == 1)
                str = "1";
            p >>>= 1;
            for(int i = 1; p != 0; i++)
            {
                b = (byte)(p & 1);
                if(b == 1)
                    str = (new StringBuilder()).append(str).append("+x^").append(i).toString();
                p >>>= 1;
            }

        }
        return str;
    }

    private int degree;
    private int polynomial;
}
