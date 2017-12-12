// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfIndirectReference, PdfObject, 
//            PdfName, PdfWriter

public static class PdfWriter$PdfTrailer extends PdfDictionary
{

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 8, this);
        os.write(DocWriter.getISOBytes("trailer\n"));
        super.toPdf(null, os);
        os.write(10);
        PdfWriter.writeKeyInfo(os);
        os.write(DocWriter.getISOBytes("startxref\n"));
        os.write(DocWriter.getISOBytes(String.valueOf(offset)));
        os.write(DocWriter.getISOBytes("\n%%EOF\n"));
    }

    long offset;

    public PdfWriter$PdfTrailer(int size, long offset, PdfIndirectReference root, PdfIndirectReference info, PdfIndirectReference encryption, PdfObject fileID, 
            long prevxref)
    {
        this.offset = offset;
        put(PdfName.SIZE, new PdfNumber(size));
        put(PdfName.ROOT, root);
        if(info != null)
            put(PdfName.INFO, info);
        if(encryption != null)
            put(PdfName.ENCRYPT, encryption);
        if(fileID != null)
            put(PdfName.ID, fileID);
        if(prevxref > 0L)
            put(PdfName.PREV, new PdfNumber(prevxref));
    }
}
