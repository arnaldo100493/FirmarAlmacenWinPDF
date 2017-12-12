// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffWriter.java

package co.com.pdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TiffWriter

public static abstract class TiffWriter$FieldBase
{

    public int getValueSize()
    {
        return data.length + 1 & -2;
    }

    public int getTag()
    {
        return tag;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public void writeField(OutputStream stream)
        throws IOException
    {
        TiffWriter.writeShort(tag, stream);
        TiffWriter.writeShort(fieldType, stream);
        TiffWriter.writeLong(count, stream);
        if(data.length <= 4)
        {
            stream.write(data);
            for(int k = data.length; k < 4; k++)
                stream.write(0);

        } else
        {
            TiffWriter.writeLong(offset, stream);
        }
    }

    public void writeValue(OutputStream stream)
        throws IOException
    {
        if(data.length <= 4)
            return;
        stream.write(data);
        if((data.length & 1) == 1)
            stream.write(0);
    }

    private int tag;
    private int fieldType;
    private int count;
    protected byte data[];
    private int offset;

    protected TiffWriter$FieldBase(int tag, int fieldType, int count)
    {
        this.tag = tag;
        this.fieldType = fieldType;
        this.count = count;
    }
}
