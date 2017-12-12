// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpotColor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;

// Referenced classes of package co.com.pdf.text.pdf:
//            ExtendedColor, PdfSpotColor

public class SpotColor extends ExtendedColor
{

    public SpotColor(PdfSpotColor spot, float tint)
    {
        super(3, ((float)spot.getAlternativeCS().getRed() / 255F - 1.0F) * tint + 1.0F, ((float)spot.getAlternativeCS().getGreen() / 255F - 1.0F) * tint + 1.0F, ((float)spot.getAlternativeCS().getBlue() / 255F - 1.0F) * tint + 1.0F);
        this.spot = spot;
        this.tint = tint;
    }

    public PdfSpotColor getPdfSpotColor()
    {
        return spot;
    }

    public float getTint()
    {
        return tint;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof SpotColor) && ((SpotColor)obj).spot.equals(spot) && ((SpotColor)obj).tint == tint;
    }

    public int hashCode()
    {
        return spot.hashCode() ^ Float.floatToIntBits(tint);
    }

    private static final long serialVersionUID = 0xa92aa77bcd25fcb1L;
    PdfSpotColor spot;
    float tint;
}
