// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GraphicsState.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.pdf.CMapAwareDocumentFont;
import co.com.pdf.text.pdf.PdfName;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix

public class GraphicsState
{

    public GraphicsState()
    {
        ctm = new Matrix();
        characterSpacing = 0.0F;
        wordSpacing = 0.0F;
        horizontalScaling = 1.0F;
        leading = 0.0F;
        font = null;
        fontSize = 0.0F;
        renderMode = 0;
        rise = 0.0F;
        knockout = true;
        colorSpaceFill = null;
        colorSpaceStroke = null;
        fillColor = null;
        strokeColor = null;
    }

    public GraphicsState(GraphicsState source)
    {
        ctm = source.ctm;
        characterSpacing = source.characterSpacing;
        wordSpacing = source.wordSpacing;
        horizontalScaling = source.horizontalScaling;
        leading = source.leading;
        font = source.font;
        fontSize = source.fontSize;
        renderMode = source.renderMode;
        rise = source.rise;
        knockout = source.knockout;
        colorSpaceFill = source.colorSpaceFill;
        colorSpaceStroke = source.colorSpaceStroke;
        fillColor = source.fillColor;
        strokeColor = source.strokeColor;
    }

    public Matrix getCtm()
    {
        return ctm;
    }

    public float getCharacterSpacing()
    {
        return characterSpacing;
    }

    public float getWordSpacing()
    {
        return wordSpacing;
    }

    public float getHorizontalScaling()
    {
        return horizontalScaling;
    }

    public float getLeading()
    {
        return leading;
    }

    public CMapAwareDocumentFont getFont()
    {
        return font;
    }

    public float getFontSize()
    {
        return fontSize;
    }

    public int getRenderMode()
    {
        return renderMode;
    }

    public float getRise()
    {
        return rise;
    }

    public boolean isKnockout()
    {
        return knockout;
    }

    public PdfName getColorSpaceFill()
    {
        return colorSpaceFill;
    }

    public PdfName getColorSpaceStroke()
    {
        return colorSpaceStroke;
    }

    public BaseColor getFillColor()
    {
        return fillColor;
    }

    public BaseColor getStrokeColor()
    {
        return strokeColor;
    }

    Matrix ctm;
    float characterSpacing;
    float wordSpacing;
    float horizontalScaling;
    float leading;
    CMapAwareDocumentFont font;
    float fontSize;
    int renderMode;
    float rise;
    boolean knockout;
    PdfName colorSpaceFill;
    PdfName colorSpaceStroke;
    BaseColor fillColor;
    BaseColor strokeColor;
}
