// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2nElement.java

package co.org.bouncy.pqc.math.linearalgebra;


// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            GFElement, GF2nField

public abstract class GF2nElement
    implements GFElement
{

    public GF2nElement()
    {
    }

    public abstract Object clone();

    abstract void assignZero();

    abstract void assignOne();

    public abstract boolean testRightmostBit();

    abstract boolean testBit(int i);

    public final GF2nField getField()
    {
        return mField;
    }

    public abstract GF2nElement increase();

    public abstract void increaseThis();

    public final GFElement subtract(GFElement minuend)
        throws RuntimeException
    {
        return add(minuend);
    }

    public final void subtractFromThis(GFElement minuend)
    {
        addToThis(minuend);
    }

    public abstract GF2nElement square();

    public abstract void squareThis();

    public abstract GF2nElement squareRoot();

    public abstract void squareRootThis();

    public final GF2nElement convert(GF2nField basis)
        throws RuntimeException
    {
        return mField.convert(this, basis);
    }

    public abstract int trace();

    public abstract GF2nElement solveQuadraticEquation()
        throws RuntimeException;

    protected GF2nField mField;
    protected int mDegree;
}
