// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfIndirectObject.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfIndirectReference, PdfObject, PdfWriter, PdfEncryption

public class PdfIndirectObject
{

    protected PdfIndirectObject(int number, PdfObject object, PdfWriter writer)
    {
        this(number, 0, object, writer);
    }

    PdfIndirectObject(PdfIndirectReference ref, PdfObject object, PdfWriter writer)
    {
        this(ref.getNumber(), ref.getGeneration(), object, writer);
    }

    PdfIndirectObject(int number, int generation, PdfObject object, PdfWriter writer)
    {
        this.generation = 0;
        this.writer = writer;
        this.number = number;
        this.generation = generation;
        this.object = object;
        PdfEncryption crypto = null;
        if(writer != null)
            crypto = writer.getEncryption();
        if(crypto != null)
            crypto.setHashKey(number, generation);
    }

    public PdfIndirectReference getIndirectReference()
    {
        return new PdfIndirectReference(object.type(), number, generation);
    }

    protected void writeTo(OutputStream os)
        throws IOException
    {
        os.write(DocWriter.getISOBytes(String.valueOf(number)));
        os.write(32);
        os.write(DocWriter.getISOBytes(String.valueOf(generation)));
        os.write(STARTOBJ);
        object.toPdf(writer, os);
        os.write(ENDOBJ);
    }

    protected int number;
    protected int generation;
    static final byte STARTOBJ[];
    static final byte ENDOBJ[];
    static final int SIZEOBJ;
    protected PdfObject object;
    protected PdfWriter writer;

    static 
    {
        STARTOBJ = DocWriter.getISOBytes(" obj\n");
        ENDOBJ = DocWriter.getISOBytes("\nendobj\n");
        SIZEOBJ = STARTOBJ.length + ENDOBJ.length;
    }
}
