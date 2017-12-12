// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteMatrix.java

package co.com.pdf.text.pdf.qrcode;


public final class ByteMatrix
{

    public ByteMatrix(int width, int height)
    {
        bytes = new byte[height][width];
        this.width = width;
        this.height = height;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public byte get(int x, int y)
    {
        return bytes[y][x];
    }

    public byte[][] getArray()
    {
        return bytes;
    }

    public void set(int x, int y, byte value)
    {
        bytes[y][x] = value;
    }

    public void set(int x, int y, int value)
    {
        bytes[y][x] = (byte)value;
    }

    public void clear(byte value)
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
                bytes[y][x] = value;

        }

    }

    public String toString()
    {
        StringBuffer result = new StringBuffer(2 * width * height + 2);
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
                switch(bytes[y][x])
                {
                case 0: // '\0'
                    result.append(" 0");
                    break;

                case 1: // '\001'
                    result.append(" 1");
                    break;

                default:
                    result.append("  ");
                    break;
                }

            result.append('\n');
        }

        return result.toString();
    }

    private final byte bytes[][];
    private final int width;
    private final int height;
}
