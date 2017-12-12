// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStamperImp.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PageResources, PdfName, PdfDictionary, PdfStamperImp, 
//            StampContent, PdfReader

static class PdfStamperImp$PageStamp
{

    PdfDictionary pageN;
    StampContent under;
    StampContent over;
    PageResources pageResources;
    int replacePoint;

    PdfStamperImp$PageStamp(PdfStamperImp stamper, PdfReader reader, PdfDictionary pageN)
    {
        replacePoint = 0;
        this.pageN = pageN;
        pageResources = new PageResources();
        PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
        pageResources.setOriginalResources(resources, stamper.namePtr);
    }
}
