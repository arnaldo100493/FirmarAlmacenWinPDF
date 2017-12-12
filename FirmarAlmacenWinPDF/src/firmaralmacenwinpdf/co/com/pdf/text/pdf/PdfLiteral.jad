// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfLiteral.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, OutputStreamCounter, PdfWriter

public class PdfLiteral extends PdfObject
{

    public PdfLiteral(String text)
    {
        super(0, text);
    }

    public PdfLiteral(byte b[])
    {
        super(0, b);
    }

    public PdfLiteral(int size)
    {
        super(0, (byte[])null);
        bytes = new byte[size];
        Arrays.fill(bytes, (byte)32);
    }

    public PdfLiteral(int type, String text)
    {
        super(type, text);
    }

    public PdfLiteral(int type, byte b[])
    {
        super(type, b);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        if(os instanceof OutputStreamCounter)
            position = ((OutputStreamCounter)os).getCounter();
        super.toPdf(writer, os);
    }

    public long getPosition()
    {
        return position;
    }

    public int getPosLength()
    {
        if(bytes != null)
            return bytes.length;
        else
            return 0;
    }

    private long position;
}
