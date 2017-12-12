// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GFElement.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.math.BigInteger;

public interface GFElement
{

    public abstract Object clone();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract boolean isZero();

    public abstract boolean isOne();

    public abstract GFElement add(GFElement gfelement)
        throws RuntimeException;

    public abstract void addToThis(GFElement gfelement)
        throws RuntimeException;

    public abstract GFElement subtract(GFElement gfelement)
        throws RuntimeException;

    public abstract void subtractFromThis(GFElement gfelement);

    public abstract GFElement multiply(GFElement gfelement)
        throws RuntimeException;

    public abstract void multiplyThisBy(GFElement gfelement)
        throws RuntimeException;

    public abstract GFElement invert()
        throws ArithmeticException;

    public abstract BigInteger toFlexiBigInt();

    public abstract byte[] toByteArray();

    public abstract String toString();

    public abstract String toString(int i);
}
