// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffWriter.java

package co.com.pdf.text.pdf.codec;


// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TiffWriter

public static class TiffWriter$FieldShort extends TiffWriter.FieldBase
{

    public TiffWriter$FieldShort(int tag, int value)
    {
        super(tag, 3, 1);
        data = new byte[2];
        data[0] = (byte)(value >> 8);
        data[1] = (byte)value;
    }

    public TiffWriter$FieldShort(int tag, int values[])
    {
        super(tag, 3, values.length);
        data = new byte[values.length * 2];
        int ptr = 0;
        int arr$[] = values;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            int value = arr$[i$];
            data[ptr++] = (byte)(value >> 8);
            data[ptr++] = (byte)value;
        }

    }
}
