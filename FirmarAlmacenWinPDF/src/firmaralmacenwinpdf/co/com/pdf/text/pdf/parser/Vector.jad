// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Vector.java

package co.com.pdf.text.pdf.parser;

import java.util.Arrays;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix

public class Vector
{

    public Vector(float x, float y, float z)
    {
        vals[0] = x;
        vals[1] = y;
        vals[2] = z;
    }

    public float get(int index)
    {
        return vals[index];
    }

    public Vector cross(Matrix by)
    {
        float x = vals[0] * by.get(0) + vals[1] * by.get(3) + vals[2] * by.get(6);
        float y = vals[0] * by.get(1) + vals[1] * by.get(4) + vals[2] * by.get(7);
        float z = vals[0] * by.get(2) + vals[1] * by.get(5) + vals[2] * by.get(8);
        return new Vector(x, y, z);
    }

    public Vector subtract(Vector v)
    {
        float x = vals[0] - v.vals[0];
        float y = vals[1] - v.vals[1];
        float z = vals[2] - v.vals[2];
        return new Vector(x, y, z);
    }

    public Vector cross(Vector with)
    {
        float x = vals[1] * with.vals[2] - vals[2] * with.vals[1];
        float y = vals[2] * with.vals[0] - vals[0] * with.vals[2];
        float z = vals[0] * with.vals[1] - vals[1] * with.vals[0];
        return new Vector(x, y, z);
    }

    public Vector normalize()
    {
        float l = length();
        float x = vals[0] / l;
        float y = vals[1] / l;
        float z = vals[2] / l;
        return new Vector(x, y, z);
    }

    public Vector multiply(float by)
    {
        float x = vals[0] * by;
        float y = vals[1] * by;
        float z = vals[2] * by;
        return new Vector(x, y, z);
    }

    public float dot(Vector with)
    {
        return vals[0] * with.vals[0] + vals[1] * with.vals[1] + vals[2] * with.vals[2];
    }

    public float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }

    public float lengthSquared()
    {
        return vals[0] * vals[0] + vals[1] * vals[1] + vals[2] * vals[2];
    }

    public String toString()
    {
        return (new StringBuilder()).append(vals[0]).append(",").append(vals[1]).append(",").append(vals[2]).toString();
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + Arrays.hashCode(vals);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Vector other = (Vector)obj;
        return Arrays.equals(vals, other.vals);
    }

    public static final int I1 = 0;
    public static final int I2 = 1;
    public static final int I3 = 2;
    private final float vals[] = {
        0.0F, 0.0F, 0.0F
    };
}
