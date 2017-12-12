// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static final class CFFFont$DictOffsetItem extends CFFFont.OffsetItem
{

    public void increment(int currentOffset[])
    {
        super.increment(currentOffset);
        currentOffset[0] += size;
    }

    public void emit(byte buffer[])
    {
        if(size == 5)
        {
            buffer[myOffset] = 29;
            buffer[myOffset + 1] = (byte)(value >>> 24 & 0xff);
            buffer[myOffset + 2] = (byte)(value >>> 16 & 0xff);
            buffer[myOffset + 3] = (byte)(value >>> 8 & 0xff);
            buffer[myOffset + 4] = (byte)(value >>> 0 & 0xff);
        }
    }

    public final int size = 5;

    public CFFFont$DictOffsetItem()
    {
    }
}
