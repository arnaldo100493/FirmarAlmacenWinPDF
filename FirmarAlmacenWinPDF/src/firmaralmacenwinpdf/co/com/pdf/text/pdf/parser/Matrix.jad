// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Matrix.java

package co.com.pdf.text.pdf.parser;

import java.util.Arrays;

public class Matrix
{

    public Matrix()
    {
    }

    public Matrix(float tx, float ty)
    {
        vals[6] = tx;
        vals[7] = ty;
    }

    public Matrix(float a, float b, float c, float d, float e, float f)
    {
        vals[0] = a;
        vals[1] = b;
        vals[2] = 0.0F;
        vals[3] = c;
        vals[4] = d;
        vals[5] = 0.0F;
        vals[6] = e;
        vals[7] = f;
        vals[8] = 1.0F;
    }

    public float get(int index)
    {
        return vals[index];
    }

    public Matrix multiply(Matrix by)
    {
        Matrix rslt = new Matrix();
        float a[] = vals;
        float b[] = by.vals;
        float c[] = rslt.vals;
        c[0] = a[0] * b[0] + a[1] * b[3] + a[2] * b[6];
        c[1] = a[0] * b[1] + a[1] * b[4] + a[2] * b[7];
        c[2] = a[0] * b[2] + a[1] * b[5] + a[2] * b[8];
        c[3] = a[3] * b[0] + a[4] * b[3] + a[5] * b[6];
        c[4] = a[3] * b[1] + a[4] * b[4] + a[5] * b[7];
        c[5] = a[3] * b[2] + a[4] * b[5] + a[5] * b[8];
        c[6] = a[6] * b[0] + a[7] * b[3] + a[8] * b[6];
        c[7] = a[6] * b[1] + a[7] * b[4] + a[8] * b[7];
        c[8] = a[6] * b[2] + a[7] * b[5] + a[8] * b[8];
        return rslt;
    }

    public Matrix subtract(Matrix arg)
    {
        Matrix rslt = new Matrix();
        float a[] = vals;
        float b[] = arg.vals;
        float c[] = rslt.vals;
        c[0] = a[0] - b[0];
        c[1] = a[1] - b[1];
        c[2] = a[2] - b[2];
        c[3] = a[3] - b[3];
        c[4] = a[4] - b[4];
        c[5] = a[5] - b[5];
        c[6] = a[6] - b[6];
        c[7] = a[7] - b[7];
        c[8] = a[8] - b[8];
        return rslt;
    }

    public float getDeterminant()
    {
        return (vals[0] * vals[4] * vals[8] + vals[1] * vals[5] * vals[6] + vals[2] * vals[3] * vals[7]) - vals[0] * vals[5] * vals[7] - vals[1] * vals[3] * vals[8] - vals[2] * vals[4] * vals[6];
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Matrix))
            return false;
        else
            return Arrays.equals(vals, ((Matrix)obj).vals);
    }

    public int hashCode()
    {
        int result = 1;
        for(int i = 0; i < vals.length; i++)
            result = 31 * result + Float.floatToIntBits(vals[i]);

        return result;
    }

    public String toString()
    {
        return (new StringBuilder()).append(vals[0]).append("\t").append(vals[1]).append("\t").append(vals[2]).append("\n").append(vals[3]).append("\t").append(vals[4]).append("\t").append(vals[2]).append("\n").append(vals[6]).append("\t").append(vals[7]).append("\t").append(vals[8]).toString();
    }

    public static final int I11 = 0;
    public static final int I12 = 1;
    public static final int I13 = 2;
    public static final int I21 = 3;
    public static final int I22 = 4;
    public static final int I23 = 5;
    public static final int I31 = 6;
    public static final int I32 = 7;
    public static final int I33 = 8;
    private final float vals[] = {
        1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F
    };
}
