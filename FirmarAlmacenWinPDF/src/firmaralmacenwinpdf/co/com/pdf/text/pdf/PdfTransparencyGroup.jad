// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTransparencyGroup.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName, PdfBoolean

public class PdfTransparencyGroup extends PdfDictionary
{

    public PdfTransparencyGroup()
    {
        put(PdfName.S, PdfName.TRANSPARENCY);
    }

    public void setIsolated(boolean isolated)
    {
        if(isolated)
            put(PdfName.I, PdfBoolean.PDFTRUE);
        else
            remove(PdfName.I);
    }

    public void setKnockout(boolean knockout)
    {
        if(knockout)
            put(PdfName.K, PdfBoolean.PDFTRUE);
        else
            remove(PdfName.K);
    }
}
