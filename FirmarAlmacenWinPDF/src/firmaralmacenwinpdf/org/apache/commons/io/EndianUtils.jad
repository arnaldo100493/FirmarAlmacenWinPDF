// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EndianUtils.java

package org.apache.commons.io;

import java.io.*;

public class EndianUtils
{

    public EndianUtils()
    {
    }

    public static short swapShort(short value)
    {
        return (short)(((value >> 0 & 0xff) << 8) + ((value >> 8 & 0xff) << 0));
    }

    public static int swapInteger(int value)
    {
        return ((value >> 0 & 0xff) << 24) + ((value >> 8 & 0xff) << 16) + ((value >> 16 & 0xff) << 8) + ((value >> 24 & 0xff) << 0);
    }

    public static long swapLong(long value)
    {
        return ((value >> 0 & 255L) << 56) + ((value >> 8 & 255L) << 48) + ((value >> 16 & 255L) << 40) + ((value >> 24 & 255L) << 32) + ((value >> 32 & 255L) << 24) + ((value >> 40 & 255L) << 16) + ((value >> 48 & 255L) << 8) + ((value >> 56 & 255L) << 0);
    }

    public static float swapFloat(float value)
    {
        return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(value)));
    }

    public static double swapDouble(double value)
    {
        return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(value)));
    }

    public static void writeSwappedShort(byte data[], int offset, short value)
    {
        data[offset + 0] = (byte)(value >> 0 & 0xff);
        data[offset + 1] = (byte)(value >> 8 & 0xff);
    }

    public static short readSwappedShort(byte data[], int offset)
    {
        return (short)(((data[offset + 0] & 0xff) << 0) + ((data[offset + 1] & 0xff) << 8));
    }

    public static int readSwappedUnsignedShort(byte data[], int offset)
    {
        return ((data[offset + 0] & 0xff) << 0) + ((data[offset + 1] & 0xff) << 8);
    }

    public static void writeSwappedInteger(byte data[], int offset, int value)
    {
        data[offset + 0] = (byte)(value >> 0 & 0xff);
        data[offset + 1] = (byte)(value >> 8 & 0xff);
        data[offset + 2] = (byte)(value >> 16 & 0xff);
        data[offset + 3] = (byte)(value >> 24 & 0xff);
    }

    public static int readSwappedInteger(byte data[], int offset)
    {
        return ((data[offset + 0] & 0xff) << 0) + ((data[offset + 1] & 0xff) << 8) + ((data[offset + 2] & 0xff) << 16) + ((data[offset + 3] & 0xff) << 24);
    }

    public static long readSwappedUnsignedInteger(byte data[], int offset)
    {
        long low = ((data[offset + 0] & 0xff) << 0) + ((data[offset + 1] & 0xff) << 8) + ((data[offset + 2] & 0xff) << 16);
        long high = data[offset + 3] & 0xff;
        return (high << 24) + (0xffffffffL & low);
    }

    public static void writeSwappedLong(byte data[], int offset, long value)
    {
        data[offset + 0] = (byte)(int)(value >> 0 & 255L);
        data[offset + 1] = (byte)(int)(value >> 8 & 255L);
        data[offset + 2] = (byte)(int)(value >> 16 & 255L);
        data[offset + 3] = (byte)(int)(value >> 24 & 255L);
        data[offset + 4] = (byte)(int)(value >> 32 & 255L);
        data[offset + 5] = (byte)(int)(value >> 40 & 255L);
        data[offset + 6] = (byte)(int)(value >> 48 & 255L);
        data[offset + 7] = (byte)(int)(value >> 56 & 255L);
    }

    public static long readSwappedLong(byte data[], int offset)
    {
        long low = ((data[offset + 0] & 0xff) << 0) + ((data[offset + 1] & 0xff) << 8) + ((data[offset + 2] & 0xff) << 16) + ((data[offset + 3] & 0xff) << 24);
        long high = ((data[offset + 4] & 0xff) << 0) + ((data[offset + 5] & 0xff) << 8) + ((data[offset + 6] & 0xff) << 16) + ((data[offset + 7] & 0xff) << 24);
        return (high << 32) + (0xffffffffL & low);
    }

    public static void writeSwappedFloat(byte data[], int offset, float value)
    {
        writeSwappedInteger(data, offset, Float.floatToIntBits(value));
    }

    public static float readSwappedFloat(byte data[], int offset)
    {
        return Float.intBitsToFloat(readSwappedInteger(data, offset));
    }

    public static void writeSwappedDouble(byte data[], int offset, double value)
    {
        writeSwappedLong(data, offset, Double.doubleToLongBits(value));
    }

    public static double readSwappedDouble(byte data[], int offset)
    {
        return Double.longBitsToDouble(readSwappedLong(data, offset));
    }

    public static void writeSwappedShort(OutputStream output, short value)
        throws IOException
    {
        output.write((byte)(value >> 0 & 0xff));
        output.write((byte)(value >> 8 & 0xff));
    }

    public static short readSwappedShort(InputStream input)
        throws IOException
    {
        return (short)(((read(input) & 0xff) << 0) + ((read(input) & 0xff) << 8));
    }

    public static int readSwappedUnsignedShort(InputStream input)
        throws IOException
    {
        int value1 = read(input);
        int value2 = read(input);
        return ((value1 & 0xff) << 0) + ((value2 & 0xff) << 8);
    }

    public static void writeSwappedInteger(OutputStream output, int value)
        throws IOException
    {
        output.write((byte)(value >> 0 & 0xff));
        output.write((byte)(value >> 8 & 0xff));
        output.write((byte)(value >> 16 & 0xff));
        output.write((byte)(value >> 24 & 0xff));
    }

    public static int readSwappedInteger(InputStream input)
        throws IOException
    {
        int value1 = read(input);
        int value2 = read(input);
        int value3 = read(input);
        int value4 = read(input);
        return ((value1 & 0xff) << 0) + ((value2 & 0xff) << 8) + ((value3 & 0xff) << 16) + ((value4 & 0xff) << 24);
    }

    public static long readSwappedUnsignedInteger(InputStream input)
        throws IOException
    {
        int value1 = read(input);
        int value2 = read(input);
        int value3 = read(input);
        int value4 = read(input);
        long low = ((value1 & 0xff) << 0) + ((value2 & 0xff) << 8) + ((value3 & 0xff) << 16);
        long high = value4 & 0xff;
        return (high << 24) + (0xffffffffL & low);
    }

    public static void writeSwappedLong(OutputStream output, long value)
        throws IOException
    {
        output.write((byte)(int)(value >> 0 & 255L));
        output.write((byte)(int)(value >> 8 & 255L));
        output.write((byte)(int)(value >> 16 & 255L));
        output.write((byte)(int)(value >> 24 & 255L));
        output.write((byte)(int)(value >> 32 & 255L));
        output.write((byte)(int)(value >> 40 & 255L));
        output.write((byte)(int)(value >> 48 & 255L));
        output.write((byte)(int)(value >> 56 & 255L));
    }

    public static long readSwappedLong(InputStream input)
        throws IOException
    {
        byte bytes[] = new byte[8];
        for(int i = 0; i < 8; i++)
            bytes[i] = (byte)read(input);

        return readSwappedLong(bytes, 0);
    }

    public static void writeSwappedFloat(OutputStream output, float value)
        throws IOException
    {
        writeSwappedInteger(output, Float.floatToIntBits(value));
    }

    public static float readSwappedFloat(InputStream input)
        throws IOException
    {
        return Float.intBitsToFloat(readSwappedInteger(input));
    }

    public static void writeSwappedDouble(OutputStream output, double value)
        throws IOException
    {
        writeSwappedLong(output, Double.doubleToLongBits(value));
    }

    public static double readSwappedDouble(InputStream input)
        throws IOException
    {
        return Double.longBitsToDouble(readSwappedLong(input));
    }

    private static int read(InputStream input)
        throws IOException
    {
        int value = input.read();
        if(-1 == value)
            throw new EOFException("Unexpected EOF reached");
        else
            return value;
    }
}
