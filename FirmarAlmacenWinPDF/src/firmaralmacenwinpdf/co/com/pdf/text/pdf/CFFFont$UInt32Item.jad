// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static final class CFFFont$UInt32Item extends CFFFont.Item
{

    public void increment(int currentOffset[])
    {
        super.increment(currentOffset);
        currentOffset[0] += 4;
    }

    public void emit(byte buffer[])
    {
        buffer[myOffset + 0] = (byte)(value >>> 24 & 0xff);
        buffer[myOffset + 1] = (byte)(value >>> 16 & 0xff);
        buffer[myOffset + 2] = (byte)(value >>> 8 & 0xff);
        buffer[myOffset + 3] = (byte)(value >>> 0 & 0xff);
    }

    public int value;

    public CFFFont$UInt32Item(int value)
    {
        this.value = value;
    }
}
