// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Barcode.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Image;
import co.com.pdf.text.Rectangle;
import java.awt.Color;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfContentByte, PdfTemplate, BaseFont

public abstract class Barcode
{

    public Barcode()
    {
        code = "";
        inkSpreading = 0.0F;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getN()
    {
        return n;
    }

    public void setN(float n)
    {
        this.n = n;
    }

    public BaseFont getFont()
    {
        return font;
    }

    public void setFont(BaseFont font)
    {
        this.font = font;
    }

    public float getSize()
    {
        return size;
    }

    public void setSize(float size)
    {
        this.size = size;
    }

    public float getBaseline()
    {
        return baseline;
    }

    public void setBaseline(float baseline)
    {
        this.baseline = baseline;
    }

    public float getBarHeight()
    {
        return barHeight;
    }

    public void setBarHeight(float barHeight)
    {
        this.barHeight = barHeight;
    }

    public int getTextAlignment()
    {
        return textAlignment;
    }

    public void setTextAlignment(int textAlignment)
    {
        this.textAlignment = textAlignment;
    }

    public boolean isGenerateChecksum()
    {
        return generateChecksum;
    }

    public void setGenerateChecksum(boolean generateChecksum)
    {
        this.generateChecksum = generateChecksum;
    }

    public boolean isChecksumText()
    {
        return checksumText;
    }

    public void setChecksumText(boolean checksumText)
    {
        this.checksumText = checksumText;
    }

    public boolean isStartStopText()
    {
        return startStopText;
    }

    public void setStartStopText(boolean startStopText)
    {
        this.startStopText = startStopText;
    }

    public boolean isExtended()
    {
        return extended;
    }

    public void setExtended(boolean extended)
    {
        this.extended = extended;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public boolean isGuardBars()
    {
        return guardBars;
    }

    public void setGuardBars(boolean guardBars)
    {
        this.guardBars = guardBars;
    }

    public int getCodeType()
    {
        return codeType;
    }

    public void setCodeType(int codeType)
    {
        this.codeType = codeType;
    }

    public abstract Rectangle getBarcodeSize();

    public abstract Rectangle placeBarcode(PdfContentByte pdfcontentbyte, BaseColor basecolor, BaseColor basecolor1);

    public PdfTemplate createTemplateWithBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        PdfTemplate tp = cb.createTemplate(0.0F, 0.0F);
        Rectangle rect = placeBarcode(tp, barColor, textColor);
        tp.setBoundingBox(rect);
        return tp;
    }

    public Image createImageWithBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        try
        {
            return Image.getInstance(createTemplateWithBarcode(cb, barColor, textColor));
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public float getInkSpreading()
    {
        return inkSpreading;
    }

    public void setInkSpreading(float inkSpreading)
    {
        this.inkSpreading = inkSpreading;
    }

    public String getAltText()
    {
        return altText;
    }

    public void setAltText(String altText)
    {
        this.altText = altText;
    }

    public abstract java.awt.Image createAwtImage(Color color, Color color1);

    public static final int EAN13 = 1;
    public static final int EAN8 = 2;
    public static final int UPCA = 3;
    public static final int UPCE = 4;
    public static final int SUPP2 = 5;
    public static final int SUPP5 = 6;
    public static final int POSTNET = 7;
    public static final int PLANET = 8;
    public static final int CODE128 = 9;
    public static final int CODE128_UCC = 10;
    public static final int CODE128_RAW = 11;
    public static final int CODABAR = 12;
    protected float x;
    protected float n;
    protected BaseFont font;
    protected float size;
    protected float baseline;
    protected float barHeight;
    protected int textAlignment;
    protected boolean generateChecksum;
    protected boolean checksumText;
    protected boolean startStopText;
    protected boolean extended;
    protected String code;
    protected boolean guardBars;
    protected int codeType;
    protected float inkSpreading;
    protected String altText;
}
