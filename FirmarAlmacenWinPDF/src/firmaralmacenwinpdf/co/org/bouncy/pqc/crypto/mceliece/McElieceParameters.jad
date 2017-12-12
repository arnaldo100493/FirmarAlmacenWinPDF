// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceParameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceParameters
    implements CipherParameters
{

    public McElieceParameters()
    {
        this(11, 50);
    }

    public McElieceParameters(int keysize)
        throws IllegalArgumentException
    {
        if(keysize < 1)
            throw new IllegalArgumentException("key size must be positive");
        m = 0;
        for(n = 1; n < keysize;)
        {
            n <<= 1;
            m++;
        }

        t = n >>> 1;
        t /= m;
        fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(m);
    }

    public McElieceParameters(int m, int t)
        throws IllegalArgumentException
    {
        if(m < 1)
            throw new IllegalArgumentException("m must be positive");
        if(m > 32)
            throw new IllegalArgumentException("m is too large");
        this.m = m;
        n = 1 << m;
        if(t < 0)
            throw new IllegalArgumentException("t must be positive");
        if(t > n)
        {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        } else
        {
            this.t = t;
            fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(m);
            return;
        }
    }

    public McElieceParameters(int m, int t, int poly)
        throws IllegalArgumentException
    {
        this.m = m;
        if(m < 1)
            throw new IllegalArgumentException("m must be positive");
        if(m > 32)
            throw new IllegalArgumentException(" m is too large");
        n = 1 << m;
        this.t = t;
        if(t < 0)
            throw new IllegalArgumentException("t must be positive");
        if(t > n)
            throw new IllegalArgumentException("t must be less than n = 2^m");
        if(PolynomialRingGF2.degree(poly) == m && PolynomialRingGF2.isIrreducible(poly))
            fieldPoly = poly;
        else
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
    }

    public int getM()
    {
        return m;
    }

    public int getN()
    {
        return n;
    }

    public int getT()
    {
        return t;
    }

    public int getFieldPoly()
    {
        return fieldPoly;
    }

    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int m;
    private int t;
    private int n;
    private int fieldPoly;
}
