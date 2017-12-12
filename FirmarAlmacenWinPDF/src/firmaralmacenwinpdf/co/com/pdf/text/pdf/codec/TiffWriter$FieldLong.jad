// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffWriter.java

package co.com.pdf.text.pdf.codec;


// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TiffWriter

public static class TiffWriter$FieldLong extends TiffWriter$FieldBase
{

    public TiffWriter$FieldLong(int tag, int value)
    {
        super(tag, 4, 1);
        data = new byte[4];
        data[0] = (byte)(value >> 24);
        data[1] = (byte)(value >> 16);
        data[2] = (byte)(value >> 8);
        data[3] = (byte)value;
    }

    public TiffWriter$FieldLong(int tag, int values[])
    {
        super(tag, 4, values.length);
        data = new byte[values.length * 4];
        int ptr = 0;
        int arr$[] = values;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            int value = arr$[i$];
            data[ptr++] = (byte)(value >> 24);
            data[ptr++] = (byte)(value >> 16);
            data[ptr++] = (byte)(value >> 8);
            data[ptr++] = (byte)value;
        }

    }
}
