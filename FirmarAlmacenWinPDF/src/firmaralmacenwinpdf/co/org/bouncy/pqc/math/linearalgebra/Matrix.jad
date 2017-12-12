// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Matrix.java

package co.org.bouncy.pqc.math.linearalgebra;


// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            Permutation, Vector

public abstract class Matrix
{

    public Matrix()
    {
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumColumns()
    {
        return numColumns;
    }

    public abstract byte[] getEncoded();

    public abstract Matrix computeInverse();

    public abstract boolean isZero();

    public abstract Matrix rightMultiply(Matrix matrix);

    public abstract Matrix rightMultiply(Permutation permutation);

    public abstract Vector leftMultiply(Vector vector);

    public abstract Vector rightMultiply(Vector vector);

    public abstract String toString();

    protected int numRows;
    protected int numColumns;
    public static final char MATRIX_TYPE_ZERO = 90;
    public static final char MATRIX_TYPE_UNIT = 73;
    public static final char MATRIX_TYPE_RANDOM_LT = 76;
    public static final char MATRIX_TYPE_RANDOM_UT = 85;
    public static final char MATRIX_TYPE_RANDOM_REGULAR = 82;
}
