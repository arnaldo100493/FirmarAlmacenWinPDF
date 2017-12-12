// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BitMatrix.java

package co.com.pdf.text.pdf.qrcode;


// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            BitArray

public final class BitMatrix
{

    public BitMatrix(int dimension)
    {
        this(dimension, dimension);
    }

    public BitMatrix(int width, int height)
    {
        if(width < 1 || height < 1)
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        this.width = width;
        this.height = height;
        int rowSize = width >> 5;
        if((width & 0x1f) != 0)
            rowSize++;
        this.rowSize = rowSize;
        bits = new int[rowSize * height];
    }

    public boolean get(int x, int y)
    {
        int offset = y * rowSize + (x >> 5);
        return (bits[offset] >>> (x & 0x1f) & 1) != 0;
    }

    public void set(int x, int y)
    {
        int offset = y * rowSize + (x >> 5);
        bits[offset] |= 1 << (x & 0x1f);
    }

    public void flip(int x, int y)
    {
        int offset = y * rowSize + (x >> 5);
        bits[offset] ^= 1 << (x & 0x1f);
    }

    public void clear()
    {
        int max = bits.length;
        for(int i = 0; i < max; i++)
            bits[i] = 0;

    }

    public void setRegion(int left, int top, int width, int height)
    {
        if(top < 0 || left < 0)
            throw new IllegalArgumentException("Left and top must be nonnegative");
        if(height < 1 || width < 1)
            throw new IllegalArgumentException("Height and width must be at least 1");
        int right = left + width;
        int bottom = top + height;
        if(bottom > this.height || right > this.width)
            throw new IllegalArgumentException("The region must fit inside the matrix");
        for(int y = top; y < bottom; y++)
        {
            int offset = y * rowSize;
            for(int x = left; x < right; x++)
                bits[offset + (x >> 5)] |= 1 << (x & 0x1f);

        }

    }

    public BitArray getRow(int y, BitArray row)
    {
        if(row == null || row.getSize() < width)
            row = new BitArray(width);
        int offset = y * rowSize;
        for(int x = 0; x < rowSize; x++)
            row.setBulk(x << 5, bits[offset + x]);

        return row;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getDimension()
    {
        if(width != height)
            throw new RuntimeException("Can't call getDimension() on a non-square matrix");
        else
            return width;
    }

    public String toString()
    {
        StringBuffer result = new StringBuffer(height * (width + 1));
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
                result.append(get(x, y) ? "X " : "  ");

            result.append('\n');
        }

        return result.toString();
    }

    public final int width;
    public final int height;
    public final int rowSize;
    public final int bits[];
}
