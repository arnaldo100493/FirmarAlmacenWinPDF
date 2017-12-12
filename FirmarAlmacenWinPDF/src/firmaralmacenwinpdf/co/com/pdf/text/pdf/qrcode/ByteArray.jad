// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteArray.java

package co.com.pdf.text.pdf.qrcode;


public final class ByteArray
{

    public ByteArray()
    {
        bytes = null;
        size = 0;
    }

    public ByteArray(int size)
    {
        bytes = new byte[size];
        this.size = size;
    }

    public ByteArray(byte byteArray[])
    {
        bytes = byteArray;
        size = bytes.length;
    }

    public int at(int index)
    {
        return bytes[index] & 0xff;
    }

    public void set(int index, int value)
    {
        bytes[index] = (byte)value;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void appendByte(int value)
    {
        if(size == 0 || size >= bytes.length)
        {
            int newSize = Math.max(32, size << 1);
            reserve(newSize);
        }
        bytes[size] = (byte)value;
        size++;
    }

    public void reserve(int capacity)
    {
        if(bytes == null || bytes.length < capacity)
        {
            byte newArray[] = new byte[capacity];
            if(bytes != null)
                System.arraycopy(bytes, 0, newArray, 0, bytes.length);
            bytes = newArray;
        }
    }

    public void set(byte source[], int offset, int count)
    {
        bytes = new byte[count];
        size = count;
        for(int x = 0; x < count; x++)
            bytes[x] = source[offset + x];

    }

    private static final int INITIAL_SIZE = 32;
    private byte bytes[];
    private int size;
}
