// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfContentByte, PdfCopy, PageResources, PdfWriter

public static class PdfCopy$StampContent extends PdfContentByte
{

    public PdfContentByte getDuplicate()
    {
        return new PdfCopy$StampContent(writer, pageResources);
    }

    PageResources getPageResources()
    {
        return pageResources;
    }

    PageResources pageResources;

    PdfCopy$StampContent(PdfWriter writer, PageResources pageResources)
    {
        super(writer);
        this.pageResources = pageResources;
    }
}
