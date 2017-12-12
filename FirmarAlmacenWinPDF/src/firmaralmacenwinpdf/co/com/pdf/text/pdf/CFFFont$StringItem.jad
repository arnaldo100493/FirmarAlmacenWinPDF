// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static final class CFFFont$StringItem extends CFFFont.Item
{

    public void increment(int currentOffset[])
    {
        super.increment(currentOffset);
        currentOffset[0] += s.length();
    }

    public void emit(byte buffer[])
    {
        for(int i = 0; i < s.length(); i++)
            buffer[myOffset + i] = (byte)(s.charAt(i) & 0xff);

    }

    public String s;

    public CFFFont$StringItem(String s)
    {
        this.s = s;
    }
}
