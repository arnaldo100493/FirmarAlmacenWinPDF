// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;

// Referenced classes of package co.com.pdf.text.pdf:
//            RandomAccessFileOrArray, CFFFont

protected static final class CFFFont$RangeItem extends CFFFont.Item
{

    public void increment(int currentOffset[])
    {
        super.increment(currentOffset);
        currentOffset[0] += length;
    }

    public void emit(byte buffer[])
    {
        try
        {
            buf.seek(offset);
            for(int i = myOffset; i < myOffset + length; i++)
                buffer[i] = buf.readByte();

        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public int offset;
    public int length;
    private RandomAccessFileOrArray buf;

    public CFFFont$RangeItem(RandomAccessFileOrArray buf, int offset, int length)
    {
        this.offset = offset;
        this.length = length;
        this.buf = buf;
    }
}
