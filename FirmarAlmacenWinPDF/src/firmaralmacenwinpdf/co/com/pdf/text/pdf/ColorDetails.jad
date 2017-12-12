// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorDetails.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfSpotColor, PdfIndirectReference, PdfName, PdfWriter, 
//            PdfObject

class ColorDetails
{

    ColorDetails(PdfName colorName, PdfIndirectReference indirectReference, PdfSpotColor scolor)
    {
        this.colorName = colorName;
        this.indirectReference = indirectReference;
        spotcolor = scolor;
    }

    public PdfIndirectReference getIndirectReference()
    {
        return indirectReference;
    }

    PdfName getColorName()
    {
        return colorName;
    }

    public PdfObject getSpotColor(PdfWriter writer)
    {
        return spotcolor.getSpotObject(writer);
    }

    PdfIndirectReference indirectReference;
    PdfName colorName;
    PdfSpotColor spotcolor;
}
