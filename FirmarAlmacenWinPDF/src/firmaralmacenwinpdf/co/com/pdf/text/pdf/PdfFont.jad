// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont

class PdfFont
    implements Comparable
{

    PdfFont(BaseFont bf, float size)
    {
        hScale = 1.0F;
        this.size = size;
        font = bf;
    }

    public int compareTo(PdfFont pdfFont)
    {
        if(pdfFont == null)
            return -1;
        try
        {
            if(font != pdfFont.font)
                return 1;
        }
        catch(ClassCastException cce)
        {
            return -2;
        }
        if(size() != pdfFont.size())
            return 2;
        return 0;
    }

    float size()
    {
        return size;
    }

    float width()
    {
        return width(32);
    }

    float width(int character)
    {
        return font.getWidthPoint(character, size) * hScale;
    }

    float width(String s)
    {
        return font.getWidthPoint(s, size) * hScale;
    }

    BaseFont getFont()
    {
        return font;
    }

    static PdfFont getDefaultFont()
    {
        try
        {
            BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
            return new PdfFont(bf, 12F);
        }
        catch(Exception ee)
        {
            throw new ExceptionConverter(ee);
        }
    }

    void setHorizontalScaling(float hScale)
    {
        this.hScale = hScale;
    }

    float getHorizontalScaling()
    {
        return hScale;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((PdfFont)x0);
    }

    private BaseFont font;
    private float size;
    protected float hScale;
}
