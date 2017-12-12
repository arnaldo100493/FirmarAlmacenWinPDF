// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2mVector.java

package co.org.bouncy.pqc.math.linearalgebra;


// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            Vector, GF2mField, IntUtils, Permutation

public class GF2mVector extends Vector
{

    public GF2mVector(GF2mField field, byte v[])
    {
        this.field = new GF2mField(field);
        int d = 8;
        int count = 1;
        for(; field.getDegree() > d; d += 8)
            count++;

        if(v.length % count != 0)
            throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        length = v.length / count;
        vector = new int[length];
        count = 0;
        for(int i = 0; i < vector.length; i++)
        {
            for(int j = 0; j < d; j += 8)
                vector[i] |= (v[count++] & 0xff) << j;

            if(!field.isElementOfThisField(vector[i]))
                throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        }

    }

    public GF2mVector(GF2mField field, int vector[])
    {
        this.field = field;
        length = vector.length;
        for(int i = vector.length - 1; i >= 0; i--)
            if(!field.isElementOfThisField(vector[i]))
                throw new ArithmeticException("Element array is not specified over the given finite field.");

        this.vector = IntUtils.clone(vector);
    }

    public GF2mVector(GF2mVector other)
    {
        field = new GF2mField(other.field);
        length = other.length;
        vector = IntUtils.clone(other.vector);
    }

    public GF2mField getField()
    {
        return field;
    }

    public int[] getIntArrayForm()
    {
        return IntUtils.clone(vector);
    }

    public byte[] getEncoded()
    {
        int d = 8;
        int count = 1;
        for(; field.getDegree() > d; d += 8)
            count++;

        byte res[] = new byte[vector.length * count];
        count = 0;
        for(int i = 0; i < vector.length; i++)
        {
            for(int j = 0; j < d; j += 8)
                res[count++] = (byte)(vector[i] >>> j);

        }

        return res;
    }

    public boolean isZero()
    {
        for(int i = vector.length - 1; i >= 0; i--)
            if(vector[i] != 0)
                return false;

        return true;
    }

    public Vector add(Vector addend)
    {
        throw new RuntimeException("not implemented");
    }

    public Vector multiply(Permutation p)
    {
        int pVec[] = p.getVector();
        if(length != pVec.length)
            throw new ArithmeticException("permutation size and vector size mismatch");
        int result[] = new int[length];
        for(int i = 0; i < pVec.length; i++)
            result[i] = vector[pVec[i]];

        return new GF2mVector(field, result);
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof GF2mVector))
            return false;
        GF2mVector otherVec = (GF2mVector)other;
        if(!field.equals(otherVec.field))
            return false;
        else
            return IntUtils.equals(vector, otherVec.vector);
    }

    public int hashCode()
    {
        int hash = field.hashCode();
        hash = hash * 31 + vector.hashCode();
        return hash;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < vector.length; i++)
        {
            for(int j = 0; j < field.getDegree(); j++)
            {
                int r = j & 0x1f;
                int bitMask = 1 << r;
                int coeff = vector[i] & bitMask;
                if(coeff != 0)
                    buf.append('1');
                else
                    buf.append('0');
            }

            buf.append(' ');
        }

        return buf.toString();
    }

    private GF2mField field;
    private int vector[];
}
