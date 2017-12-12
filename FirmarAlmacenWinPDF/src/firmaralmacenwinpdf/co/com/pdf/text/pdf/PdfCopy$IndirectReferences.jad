// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfCopy, PdfIndirectReference

static class PdfCopy$IndirectReferences
{

    void setCopied()
    {
        hasCopied = true;
    }

    void setNotCopied()
    {
        hasCopied = false;
    }

    boolean getCopied()
    {
        return hasCopied;
    }

    PdfIndirectReference getRef()
    {
        return theRef;
    }

    public String toString()
    {
        String ext = "";
        if(hasCopied)
            ext = (new StringBuilder()).append(ext).append(" Copied").toString();
        return (new StringBuilder()).append(getRef()).append(ext).toString();
    }

    PdfIndirectReference theRef;
    boolean hasCopied;

    PdfCopy$IndirectReferences(PdfIndirectReference ref)
    {
        theRef = ref;
        hasCopied = false;
    }
}
