// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatternColor.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtendedColor, PdfPatternPainter

public class PatternColor extends ExtendedColor
{

    public PatternColor(PdfPatternPainter painter)
    {
        super(4, 0.5F, 0.5F, 0.5F);
        this.painter = painter;
    }

    public PdfPatternPainter getPainter()
    {
        return painter;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof PatternColor) && ((PatternColor)obj).painter.equals(painter);
    }

    public int hashCode()
    {
        return painter.hashCode();
    }

    private static final long serialVersionUID = 0xef8c70d26b3dfae4L;
    PdfPatternPainter painter;
}
