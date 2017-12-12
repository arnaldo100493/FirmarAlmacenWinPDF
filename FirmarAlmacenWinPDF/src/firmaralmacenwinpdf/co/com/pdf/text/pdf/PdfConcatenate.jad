// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfConcatenate.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Document;
import co.com.pdf.text.DocumentException;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfSmartCopy, PdfCopy, PdfReader

public class PdfConcatenate
{

    public PdfConcatenate(OutputStream os)
        throws DocumentException
    {
        this(os, false);
    }

    public PdfConcatenate(OutputStream os, boolean smart)
        throws DocumentException
    {
        document = new Document();
        if(smart)
            copy = new PdfSmartCopy(document, os);
        else
            copy = new PdfCopy(document, os);
    }

    public int addPages(PdfReader reader)
        throws DocumentException, IOException
    {
        open();
        int n = reader.getNumberOfPages();
        for(int i = 1; i <= n; i++)
            copy.addPage(copy.getImportedPage(reader, i));

        copy.freeReader(reader);
        reader.close();
        return n;
    }

    public PdfCopy getWriter()
    {
        return copy;
    }

    public void open()
    {
        if(!document.isOpen())
            document.open();
    }

    public void close()
    {
        document.close();
    }

    protected Document document;
    protected PdfCopy copy;
}
