// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TIFFField.java

package co.com.pdf.text.pdf.codec;

import java.io.Serializable;

public class TIFFField
    implements Comparable, Serializable
{

    TIFFField()
    {
    }

    public TIFFField(int tag, int type, int count, Object data)
    {
        this.tag = tag;
        this.type = type;
        this.count = count;
        this.data = data;
    }

    public int getTag()
    {
        return tag;
    }

    public int getType()
    {
        return type;
    }

    public int getCount()
    {
        return count;
    }

    public byte[] getAsBytes()
    {
        return (byte[])(byte[])data;
    }

    public char[] getAsChars()
    {
        return (char[])(char[])data;
    }

    public short[] getAsShorts()
    {
        return (short[])(short[])data;
    }

    public int[] getAsInts()
    {
        return (int[])(int[])data;
    }

    public long[] getAsLongs()
    {
        return (long[])(long[])data;
    }

    public float[] getAsFloats()
    {
        return (float[])(float[])data;
    }

    public double[] getAsDoubles()
    {
        return (double[])(double[])data;
    }

    public int[][] getAsSRationals()
    {
        return (int[][])(int[][])data;
    }

    public long[][] getAsRationals()
    {
        return (long[][])(long[][])data;
    }

    public int getAsInt(int index)
    {
        switch(type)
        {
        case 1: // '\001'
        case 7: // '\007'
            return ((byte[])(byte[])data)[index] & 0xff;

        case 6: // '\006'
            return ((byte[])(byte[])data)[index];

        case 3: // '\003'
            return ((char[])(char[])data)[index] & 0xffff;

        case 8: // '\b'
            return ((short[])(short[])data)[index];

        case 9: // '\t'
            return ((int[])(int[])data)[index];

        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        default:
            throw new ClassCastException();
        }
    }

    public long getAsLong(int index)
    {
        switch(type)
        {
        case 1: // '\001'
        case 7: // '\007'
            return (long)(((byte[])(byte[])data)[index] & 0xff);

        case 6: // '\006'
            return (long)((byte[])(byte[])data)[index];

        case 3: // '\003'
            return (long)(((char[])(char[])data)[index] & 0xffff);

        case 8: // '\b'
            return (long)((short[])(short[])data)[index];

        case 9: // '\t'
            return (long)((int[])(int[])data)[index];

        case 4: // '\004'
            return ((long[])(long[])data)[index];

        case 2: // '\002'
        case 5: // '\005'
        default:
            throw new ClassCastException();
        }
    }

    public float getAsFloat(int index)
    {
        switch(type)
        {
        case 1: // '\001'
            return (float)(((byte[])(byte[])data)[index] & 0xff);

        case 6: // '\006'
            return (float)((byte[])(byte[])data)[index];

        case 3: // '\003'
            return (float)(((char[])(char[])data)[index] & 0xffff);

        case 8: // '\b'
            return (float)((short[])(short[])data)[index];

        case 9: // '\t'
            return (float)((int[])(int[])data)[index];

        case 4: // '\004'
            return (float)((long[])(long[])data)[index];

        case 11: // '\013'
            return ((float[])(float[])data)[index];

        case 12: // '\f'
            return (float)((double[])(double[])data)[index];

        case 10: // '\n'
            int ivalue[] = getAsSRational(index);
            return (float)((double)ivalue[0] / (double)ivalue[1]);

        case 5: // '\005'
            long lvalue[] = getAsRational(index);
            return (float)((double)lvalue[0] / (double)lvalue[1]);

        case 2: // '\002'
        case 7: // '\007'
        default:
            throw new ClassCastException();
        }
    }

    public double getAsDouble(int index)
    {
        switch(type)
        {
        case 1: // '\001'
            return (double)(((byte[])(byte[])data)[index] & 0xff);

        case 6: // '\006'
            return (double)((byte[])(byte[])data)[index];

        case 3: // '\003'
            return (double)(((char[])(char[])data)[index] & 0xffff);

        case 8: // '\b'
            return (double)((short[])(short[])data)[index];

        case 9: // '\t'
            return (double)((int[])(int[])data)[index];

        case 4: // '\004'
            return (double)((long[])(long[])data)[index];

        case 11: // '\013'
            return (double)((float[])(float[])data)[index];

        case 12: // '\f'
            return ((double[])(double[])data)[index];

        case 10: // '\n'
            int ivalue[] = getAsSRational(index);
            return (double)ivalue[0] / (double)ivalue[1];

        case 5: // '\005'
            long lvalue[] = getAsRational(index);
            return (double)lvalue[0] / (double)lvalue[1];

        case 2: // '\002'
        case 7: // '\007'
        default:
            throw new ClassCastException();
        }
    }

    public String getAsString(int index)
    {
        return ((String[])(String[])data)[index];
    }

    public int[] getAsSRational(int index)
    {
        return ((int[][])(int[][])data)[index];
    }

    public long[] getAsRational(int index)
    {
        if(type == 4)
            return getAsLongs();
        else
            return ((long[][])(long[][])data)[index];
    }

    public int compareTo(TIFFField o)
    {
        if(o == null)
            throw new IllegalArgumentException();
        int oTag = o.getTag();
        if(tag < oTag)
            return -1;
        return tag <= oTag ? 0 : 1;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((TIFFField)x0);
    }

    private static final long serialVersionUID = 0x7e203e9f2d34e31aL;
    public static final int TIFF_BYTE = 1;
    public static final int TIFF_ASCII = 2;
    public static final int TIFF_SHORT = 3;
    public static final int TIFF_LONG = 4;
    public static final int TIFF_RATIONAL = 5;
    public static final int TIFF_SBYTE = 6;
    public static final int TIFF_UNDEFINED = 7;
    public static final int TIFF_SSHORT = 8;
    public static final int TIFF_SLONG = 9;
    public static final int TIFF_SRATIONAL = 10;
    public static final int TIFF_FLOAT = 11;
    public static final int TIFF_DOUBLE = 12;
    int tag;
    int type;
    int count;
    Object data;
}
