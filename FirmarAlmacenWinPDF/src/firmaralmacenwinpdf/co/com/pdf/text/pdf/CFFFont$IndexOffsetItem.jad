// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static final class CFFFont$IndexOffsetItem extends CFFFont.OffsetItem
{

    public void increment(int currentOffset[])
    {
        super.increment(currentOffset);
        currentOffset[0] += size;
    }

    public void emit(byte buffer[])
    {
        int i = 0;
        switch(size)
        {
        case 4: // '\004'
            buffer[myOffset + i] = (byte)(value >>> 24 & 0xff);
            i++;
            // fall through

        case 3: // '\003'
            buffer[myOffset + i] = (byte)(value >>> 16 & 0xff);
            i++;
            // fall through

        case 2: // '\002'
            buffer[myOffset + i] = (byte)(value >>> 8 & 0xff);
            i++;
            // fall through

        case 1: // '\001'
            buffer[myOffset + i] = (byte)(value >>> 0 & 0xff);
            i++;
            // fall through

        default:
            return;
        }
    }

    public final int size;

    public CFFFont$IndexOffsetItem(int size, int value)
    {
        this.size = size;
        this.value = value;
    }

    public CFFFont$IndexOffsetItem(int size)
    {
        this.size = size;
    }
}
