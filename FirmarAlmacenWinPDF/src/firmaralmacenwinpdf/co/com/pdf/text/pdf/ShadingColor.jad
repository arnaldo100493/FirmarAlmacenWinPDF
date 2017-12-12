// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShadingColor.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtendedColor, PdfShadingPattern

public class ShadingColor extends ExtendedColor
{

    public ShadingColor(PdfShadingPattern shadingPattern)
    {
        super(5, 0.5F, 0.5F, 0.5F);
        this.shadingPattern = shadingPattern;
    }

    public PdfShadingPattern getPdfShadingPattern()
    {
        return shadingPattern;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof ShadingColor) && ((ShadingColor)obj).shadingPattern.equals(shadingPattern);
    }

    public int hashCode()
    {
        return shadingPattern.hashCode();
    }

    private static final long serialVersionUID = 0x42dcb95012ab551fL;
    PdfShadingPattern shadingPattern;
}
