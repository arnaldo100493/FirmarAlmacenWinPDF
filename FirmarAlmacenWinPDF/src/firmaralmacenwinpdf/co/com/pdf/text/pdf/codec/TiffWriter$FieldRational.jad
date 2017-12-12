// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffWriter.java

package co.com.pdf.text.pdf.codec;


// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TiffWriter

public static class TiffWriter$FieldRational extends TiffWriter.FieldBase
{

    public TiffWriter$FieldRational(int tag, int value[])
    {
        this(tag, new int[][] {
            value
        });
    }

    public TiffWriter$FieldRational(int tag, int values[][])
    {
        super(tag, 5, values.length);
        data = new byte[values.length * 8];
        int ptr = 0;
        int arr$[][] = values;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            int value[] = arr$[i$];
            data[ptr++] = (byte)(value[0] >> 24);
            data[ptr++] = (byte)(value[0] >> 16);
            data[ptr++] = (byte)(value[0] >> 8);
            data[ptr++] = (byte)value[0];
            data[ptr++] = (byte)(value[1] >> 24);
            data[ptr++] = (byte)(value[1] >> 16);
            data[ptr++] = (byte)(value[1] >> 8);
            data[ptr++] = (byte)value[1];
        }

    }
}
