// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRIndirectReference.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfIndirectReference, PdfWriter, PdfReader, PdfEncodings

public class PRIndirectReference extends PdfIndirectReference
{

    public PRIndirectReference(PdfReader reader, int number, int generation)
    {
        type = 10;
        this.number = number;
        this.generation = generation;
        this.reader = reader;
    }

    public PRIndirectReference(PdfReader reader, int number)
    {
        this(reader, number, 0);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        int n = writer.getNewObjectNumber(reader, number, generation);
        os.write(PdfEncodings.convertToBytes(n + " " + (reader.isAppendable() ? generation : 0) + " R", null));
    }

    public PdfReader getReader()
    {
        return reader;
    }

    public void setNumber(int number, int generation)
    {
        this.number = number;
        this.generation = generation;
    }

    protected PdfReader reader;
}
