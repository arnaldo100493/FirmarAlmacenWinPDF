// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentByte.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.geom.AffineTransform;
import co.com.pdf.text.BaseColor;

// Referenced classes of package co.com.pdf.text.pdf:
//            GrayColor, PdfContentByte, FontDetails, ColorDetails

static class PdfContentByte$GraphicState
{

    void copyParameters(PdfContentByte$GraphicState cp)
    {
        fontDetails = cp.fontDetails;
        colorDetails = cp.colorDetails;
        size = cp.size;
        xTLM = cp.xTLM;
        yTLM = cp.yTLM;
        aTLM = cp.aTLM;
        bTLM = cp.bTLM;
        cTLM = cp.cTLM;
        dTLM = cp.dTLM;
        tx = cp.tx;
        leading = cp.leading;
        scale = cp.scale;
        charSpace = cp.charSpace;
        wordSpace = cp.wordSpace;
        textColorFill = cp.textColorFill;
        graphicsColorFill = cp.graphicsColorFill;
        textColorStroke = cp.textColorStroke;
        graphicsColorStroke = cp.graphicsColorStroke;
        CTM = new AffineTransform(cp.CTM);
    }

    void restore(PdfContentByte$GraphicState restore)
    {
        copyParameters(restore);
    }

    FontDetails fontDetails;
    ColorDetails colorDetails;
    float size;
    protected float xTLM;
    protected float yTLM;
    protected float aTLM;
    protected float bTLM;
    protected float cTLM;
    protected float dTLM;
    protected float tx;
    protected float leading;
    protected float scale;
    protected float charSpace;
    protected float wordSpace;
    protected BaseColor textColorFill;
    protected BaseColor graphicsColorFill;
    protected BaseColor textColorStroke;
    protected BaseColor graphicsColorStroke;
    protected AffineTransform CTM;

    PdfContentByte$GraphicState()
    {
        xTLM = 0.0F;
        yTLM = 0.0F;
        aTLM = 1.0F;
        bTLM = 0.0F;
        cTLM = 0.0F;
        dTLM = 1.0F;
        tx = 0.0F;
        leading = 0.0F;
        scale = 100F;
        charSpace = 0.0F;
        wordSpace = 0.0F;
        textColorFill = new GrayColor(0);
        graphicsColorFill = new GrayColor(0);
        textColorStroke = new GrayColor(0);
        graphicsColorStroke = new GrayColor(0);
        CTM = new AffineTransform();
    }

    PdfContentByte$GraphicState(PdfContentByte$GraphicState cp)
    {
        xTLM = 0.0F;
        yTLM = 0.0F;
        aTLM = 1.0F;
        bTLM = 0.0F;
        cTLM = 0.0F;
        dTLM = 1.0F;
        tx = 0.0F;
        leading = 0.0F;
        scale = 100F;
        charSpace = 0.0F;
        wordSpace = 0.0F;
        textColorFill = new GrayColor(0);
        graphicsColorFill = new GrayColor(0);
        textColorStroke = new GrayColor(0);
        graphicsColorStroke = new GrayColor(0);
        CTM = new AffineTransform();
        copyParameters(cp);
    }
}
