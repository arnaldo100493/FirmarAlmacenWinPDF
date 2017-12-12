// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StampContent.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfContentByte, PdfStamperImp, PdfAnnotation, PageResources, 
//            PdfAction

public class StampContent extends PdfContentByte
{

    StampContent(PdfStamperImp stamper, PdfStamperImp.PageStamp ps)
    {
        super(stamper);
        this.ps = ps;
        pageResources = ps.pageResources;
    }

    public void setAction(PdfAction action, float llx, float lly, float urx, float ury)
    {
        ((PdfStamperImp)writer).addAnnotation(new PdfAnnotation(writer, llx, lly, urx, ury, action), ps.pageN);
    }

    public PdfContentByte getDuplicate()
    {
        return new StampContent((PdfStamperImp)writer, ps);
    }

    PageResources getPageResources()
    {
        return pageResources;
    }

    void addAnnotation(PdfAnnotation annot)
    {
        ((PdfStamperImp)writer).addAnnotation(annot, ps.pageN);
    }

    PdfStamperImp.PageStamp ps;
    PageResources pageResources;
}
