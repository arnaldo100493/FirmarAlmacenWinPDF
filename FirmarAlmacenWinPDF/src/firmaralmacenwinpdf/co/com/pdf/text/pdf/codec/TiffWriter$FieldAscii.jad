// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffWriter.java

package co.com.pdf.text.pdf.codec;


// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TiffWriter

public static class TiffWriter$FieldAscii extends TiffWriter.FieldBase
{

    public TiffWriter$FieldAscii(int tag, String values)
    {
        super(tag, 2, values.getBytes().length + 1);
        byte b[] = values.getBytes();
        data = new byte[b.length + 1];
        System.arraycopy(b, 0, data, 0, b.length);
    }
}
