// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfReader, PdfCopy

protected static class PdfCopy$ImportedPage
{

    public boolean equals(Object o)
    {
        if(!(o instanceof PdfCopy$ImportedPage))
        {
            return false;
        } else
        {
            PdfCopy$ImportedPage other = (PdfCopy$ImportedPage)o;
            return pageNumber == other.pageNumber && reader.equals(other.reader);
        }
    }

    public String toString()
    {
        return Integer.toString(pageNumber);
    }

    int pageNumber;
    PdfReader reader;
    PdfArray mergedFields;

    PdfCopy$ImportedPage(PdfReader reader, int pageNumber, boolean keepFields)
    {
        this.pageNumber = pageNumber;
        this.reader = reader;
        if(keepFields)
            mergedFields = new PdfArray();
    }
}
