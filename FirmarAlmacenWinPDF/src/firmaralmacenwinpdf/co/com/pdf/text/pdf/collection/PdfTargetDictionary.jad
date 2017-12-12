// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTargetDictionary.java

package co.com.pdf.text.pdf.collection;

import co.com.pdf.text.pdf.*;

public class PdfTargetDictionary extends PdfDictionary
{

    public PdfTargetDictionary(PdfTargetDictionary nested)
    {
        put(PdfName.R, PdfName.P);
        if(nested != null)
            setAdditionalPath(nested);
    }

    public PdfTargetDictionary(boolean child)
    {
        if(child)
            put(PdfName.R, PdfName.C);
        else
            put(PdfName.R, PdfName.P);
    }

    public void setEmbeddedFileName(String target)
    {
        put(PdfName.N, new PdfString(target, null));
    }

    public void setFileAttachmentPagename(String name)
    {
        put(PdfName.P, new PdfString(name, null));
    }

    public void setFileAttachmentPage(int page)
    {
        put(PdfName.P, new PdfNumber(page));
    }

    public void setFileAttachmentName(String name)
    {
        put(PdfName.A, new PdfString(name, "UnicodeBig"));
    }

    public void setFileAttachmentIndex(int annotation)
    {
        put(PdfName.A, new PdfNumber(annotation));
    }

    public void setAdditionalPath(PdfTargetDictionary nested)
    {
        put(PdfName.T, nested);
    }
}
