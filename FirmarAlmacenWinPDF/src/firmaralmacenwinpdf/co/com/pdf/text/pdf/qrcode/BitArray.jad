// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BitArray.java

package co.com.pdf.text.pdf.qrcode;


public final class BitArray
{

    public BitArray(int size)
    {
        if(size < 1)
        {
            throw new IllegalArgumentException("size must be at least 1");
        } else
        {
            this.size = size;
            bits = makeArray(size);
            return;
        }
    }

    public int getSize()
    {
        return size;
    }

    public boolean get(int i)
    {
        return (bits[i >> 5] & 1 << (i & 0x1f)) != 0;
    }

    public void set(int i)
    {
        bits[i >> 5] |= 1 << (i & 0x1f);
    }

    public void flip(int i)
    {
        bits[i >> 5] ^= 1 << (i & 0x1f);
    }

    public void setBulk(int i, int newBits)
    {
        bits[i >> 5] = newBits;
    }

    public void clear()
    {
        int max = bits.length;
        for(int i = 0; i < max; i++)
            bits[i] = 0;

    }

    public boolean isRange(int start, int end, boolean value)
    {
        if(end < start)
            throw new IllegalArgumentException();
        if(end == start)
            return true;
        end--;
        int firstInt = start >> 5;
        int lastInt = end >> 5;
        for(int i = firstInt; i <= lastInt; i++)
        {
            int firstBit = i <= firstInt ? start & 0x1f : 0;
            int lastBit = i >= lastInt ? end & 0x1f : 31;
            int mask;
            if(firstBit == 0 && lastBit == 31)
            {
                mask = -1;
            } else
            {
                mask = 0;
                for(int j = firstBit; j <= lastBit; j++)
                    mask |= 1 << j;

            }
            if((bits[i] & mask) != (value ? mask : 0))
                return false;
        }

        return true;
    }

    public int[] getBitArray()
    {
        return bits;
    }

    public void reverse()
    {
        int newBits[] = new int[bits.length];
        int size = this.size;
        for(int i = 0; i < size; i++)
            if(get(size - i - 1))
                newBits[i >> 5] |= 1 << (i & 0x1f);

        bits = newBits;
    }

    private static int[] makeArray(int size)
    {
        int arraySize = size >> 5;
        if((size & 0x1f) != 0)
            arraySize++;
        return new int[arraySize];
    }

    public String toString()
    {
        StringBuffer result = new StringBuffer(size);
        for(int i = 0; i < size; i++)
        {
            if((i & 7) == 0)
                result.append(' ');
            result.append(get(i) ? 'X' : '.');
        }

        return result.toString();
    }

    public int bits[];
    public final int size;
}
