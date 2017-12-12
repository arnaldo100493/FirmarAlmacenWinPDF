// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfImportedPage.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfTemplate, PdfReaderInstance, PdfReader, PdfWriter, 
//            PdfContentByte, PdfStream, PdfSpotColor, PdfObject, 
//            BaseFont, PdfTransparencyGroup

public class PdfImportedPage extends PdfTemplate
{

    PdfImportedPage(PdfReaderInstance readerInstance, PdfWriter writer, int pageNumber)
    {
        toCopy = true;
        this.readerInstance = readerInstance;
        this.pageNumber = pageNumber;
        this.writer = writer;
        rotation = readerInstance.getReader().getPageRotation(pageNumber);
        bBox = readerInstance.getReader().getPageSize(pageNumber);
        setMatrix(1.0F, 0.0F, 0.0F, 1.0F, -bBox.getLeft(), -bBox.getBottom());
        type = 2;
    }

    public PdfImportedPage getFromReader()
    {
        return this;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void addImage(Image image, float a, float b, float c, float d, float e, float f)
        throws DocumentException
    {
        throwError();
    }

    public void addTemplate(PdfTemplate template, float a, float b, float c, float d, float e, float f)
    {
        throwError();
    }

    public PdfContentByte getDuplicate()
    {
        throwError();
        return null;
    }

    public PdfStream getFormXObject(int compressionLevel)
        throws IOException
    {
        return readerInstance.getFormXObject(pageNumber, compressionLevel);
    }

    public void setColorFill(PdfSpotColor sp, float tint)
    {
        throwError();
    }

    public void setColorStroke(PdfSpotColor sp, float tint)
    {
        throwError();
    }

    PdfObject getResources()
    {
        return readerInstance.getResources(pageNumber);
    }

    public void setFontAndSize(BaseFont bf, float size)
    {
        throwError();
    }

    public void setGroup(PdfTransparencyGroup group)
    {
        throwError();
    }

    void throwError()
    {
        throw new RuntimeException(MessageLocalization.getComposedMessage("content.can.not.be.added.to.a.pdfimportedpage", new Object[0]));
    }

    PdfReaderInstance getPdfReaderInstance()
    {
        return readerInstance;
    }

    public boolean isToCopy()
    {
        return toCopy;
    }

    public void setCopied()
    {
        toCopy = false;
    }

    PdfReaderInstance readerInstance;
    int pageNumber;
    int rotation;
    protected boolean toCopy;
}
