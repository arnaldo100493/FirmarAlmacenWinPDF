// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentByte.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;

// Referenced classes of package co.com.pdf.text.pdf:
//            PatternColor, PdfContentByte, PdfPatternPainter

static class PdfContentByte$UncoloredPattern extends PatternColor
{

    public boolean equals(Object obj)
    {
        return (obj instanceof PdfContentByte$UncoloredPattern) && ((PdfContentByte$UncoloredPattern)obj).painter.equals(painter) && ((PdfContentByte$UncoloredPattern)obj).color.equals(color) && ((PdfContentByte$UncoloredPattern)obj).tint == tint;
    }

    protected BaseColor color;
    protected float tint;

    protected PdfContentByte$UncoloredPattern(PdfPatternPainter p, BaseColor color, float tint)
    {
        super(p);
        this.color = color;
        this.tint = tint;
    }
}
