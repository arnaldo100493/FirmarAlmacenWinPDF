// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPatternPainter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfTemplate, PdfPattern, PdfWriter, PdfContentByte, 
//            PdfSpotColor

public final class PdfPatternPainter extends PdfTemplate
{

    private PdfPatternPainter()
    {
        stencil = false;
        type = 3;
    }

    PdfPatternPainter(PdfWriter wr)
    {
        super(wr);
        stencil = false;
        type = 3;
    }

    PdfPatternPainter(PdfWriter wr, BaseColor defaultColor)
    {
        this(wr);
        stencil = true;
        if(defaultColor == null)
            this.defaultColor = BaseColor.GRAY;
        else
            this.defaultColor = defaultColor;
    }

    public void setXStep(float xstep)
    {
        this.xstep = xstep;
    }

    public void setYStep(float ystep)
    {
        this.ystep = ystep;
    }

    public float getXStep()
    {
        return xstep;
    }

    public float getYStep()
    {
        return ystep;
    }

    public boolean isStencil()
    {
        return stencil;
    }

    public void setPatternMatrix(float a, float b, float c, float d, float e, float f)
    {
        setMatrix(a, b, c, d, e, f);
    }

    public PdfPattern getPattern()
    {
        return new PdfPattern(this);
    }

    public PdfPattern getPattern(int compressionLevel)
    {
        return new PdfPattern(this, compressionLevel);
    }

    public PdfContentByte getDuplicate()
    {
        PdfPatternPainter tpl = new PdfPatternPainter();
        tpl.writer = writer;
        tpl.pdf = pdf;
        tpl.thisReference = thisReference;
        tpl.pageResources = pageResources;
        tpl.bBox = new Rectangle(bBox);
        tpl.xstep = xstep;
        tpl.ystep = ystep;
        tpl.matrix = matrix;
        tpl.stencil = stencil;
        tpl.defaultColor = defaultColor;
        return tpl;
    }

    public BaseColor getDefaultColor()
    {
        return defaultColor;
    }

    public void setGrayFill(float gray)
    {
        checkNoColor();
        super.setGrayFill(gray);
    }

    public void resetGrayFill()
    {
        checkNoColor();
        super.resetGrayFill();
    }

    public void setGrayStroke(float gray)
    {
        checkNoColor();
        super.setGrayStroke(gray);
    }

    public void resetGrayStroke()
    {
        checkNoColor();
        super.resetGrayStroke();
    }

    public void setRGBColorFillF(float red, float green, float blue)
    {
        checkNoColor();
        super.setRGBColorFillF(red, green, blue);
    }

    public void resetRGBColorFill()
    {
        checkNoColor();
        super.resetRGBColorFill();
    }

    public void setRGBColorStrokeF(float red, float green, float blue)
    {
        checkNoColor();
        super.setRGBColorStrokeF(red, green, blue);
    }

    public void resetRGBColorStroke()
    {
        checkNoColor();
        super.resetRGBColorStroke();
    }

    public void setCMYKColorFillF(float cyan, float magenta, float yellow, float black)
    {
        checkNoColor();
        super.setCMYKColorFillF(cyan, magenta, yellow, black);
    }

    public void resetCMYKColorFill()
    {
        checkNoColor();
        super.resetCMYKColorFill();
    }

    public void setCMYKColorStrokeF(float cyan, float magenta, float yellow, float black)
    {
        checkNoColor();
        super.setCMYKColorStrokeF(cyan, magenta, yellow, black);
    }

    public void resetCMYKColorStroke()
    {
        checkNoColor();
        super.resetCMYKColorStroke();
    }

    public void addImage(Image image, float a, float b, float c, float d, float e, float f)
        throws DocumentException
    {
        if(stencil && !image.isMask())
            checkNoColor();
        super.addImage(image, a, b, c, d, e, f);
    }

    public void setCMYKColorFill(int cyan, int magenta, int yellow, int black)
    {
        checkNoColor();
        super.setCMYKColorFill(cyan, magenta, yellow, black);
    }

    public void setCMYKColorStroke(int cyan, int magenta, int yellow, int black)
    {
        checkNoColor();
        super.setCMYKColorStroke(cyan, magenta, yellow, black);
    }

    public void setRGBColorFill(int red, int green, int blue)
    {
        checkNoColor();
        super.setRGBColorFill(red, green, blue);
    }

    public void setRGBColorStroke(int red, int green, int blue)
    {
        checkNoColor();
        super.setRGBColorStroke(red, green, blue);
    }

    public void setColorStroke(BaseColor color)
    {
        checkNoColor();
        super.setColorStroke(color);
    }

    public void setColorFill(BaseColor color)
    {
        checkNoColor();
        super.setColorFill(color);
    }

    public void setColorFill(PdfSpotColor sp, float tint)
    {
        checkNoColor();
        super.setColorFill(sp, tint);
    }

    public void setColorStroke(PdfSpotColor sp, float tint)
    {
        checkNoColor();
        super.setColorStroke(sp, tint);
    }

    public void setPatternFill(PdfPatternPainter p)
    {
        checkNoColor();
        super.setPatternFill(p);
    }

    public void setPatternFill(PdfPatternPainter p, BaseColor color, float tint)
    {
        checkNoColor();
        super.setPatternFill(p, color, tint);
    }

    public void setPatternStroke(PdfPatternPainter p, BaseColor color, float tint)
    {
        checkNoColor();
        super.setPatternStroke(p, color, tint);
    }

    public void setPatternStroke(PdfPatternPainter p)
    {
        checkNoColor();
        super.setPatternStroke(p);
    }

    void checkNoColor()
    {
        if(stencil)
            throw new RuntimeException(MessageLocalization.getComposedMessage("colors.are.not.allowed.in.uncolored.tile.patterns", new Object[0]));
        else
            return;
    }

    float xstep;
    float ystep;
    boolean stencil;
    BaseColor defaultColor;
}
