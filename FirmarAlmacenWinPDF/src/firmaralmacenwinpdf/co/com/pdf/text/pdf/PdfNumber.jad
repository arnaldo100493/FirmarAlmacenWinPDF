// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfNumber.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, ByteBuffer

public class PdfNumber extends PdfObject
{

    public PdfNumber(String content)
    {
        super(2);
        try
        {
            value = Double.parseDouble(content.trim());
            setContent(content);
        }
        catch(NumberFormatException nfe)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("1.is.not.a.valid.number.2", new Object[] {
                content, nfe.toString()
            }));
        }
    }

    public PdfNumber(int value)
    {
        super(2);
        this.value = value;
        setContent(String.valueOf(value));
    }

    public PdfNumber(long value)
    {
        super(2);
        this.value = value;
        setContent(String.valueOf(value));
    }

    public PdfNumber(double value)
    {
        super(2);
        this.value = value;
        setContent(ByteBuffer.formatDouble(value));
    }

    public PdfNumber(float value)
    {
        this(value);
    }

    public int intValue()
    {
        return (int)value;
    }

    public long longValue()
    {
        return (long)value;
    }

    public double doubleValue()
    {
        return value;
    }

    public float floatValue()
    {
        return (float)value;
    }

    public void increment()
    {
        value++;
        setContent(ByteBuffer.formatDouble(value));
    }

    private double value;
}
