// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextMarginFinder.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.awt.geom.Rectangle2D;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            RenderListener, TextRenderInfo, LineSegment, ImageRenderInfo

public class TextMarginFinder
    implements RenderListener
{

    public TextMarginFinder()
    {
        textRectangle = null;
    }

    public void renderText(TextRenderInfo renderInfo)
    {
        if(textRectangle == null)
            textRectangle = renderInfo.getDescentLine().getBoundingRectange();
        else
            textRectangle.add(renderInfo.getDescentLine().getBoundingRectange());
        textRectangle.add(renderInfo.getAscentLine().getBoundingRectange());
    }

    public float getLlx()
    {
        return textRectangle.x;
    }

    public float getLly()
    {
        return textRectangle.y;
    }

    public float getUrx()
    {
        return textRectangle.x + textRectangle.width;
    }

    public float getUry()
    {
        return textRectangle.y + textRectangle.height;
    }

    public float getWidth()
    {
        return textRectangle.width;
    }

    public float getHeight()
    {
        return textRectangle.height;
    }

    public void beginTextBlock()
    {
    }

    public void endTextBlock()
    {
    }

    public void renderImage(ImageRenderInfo imagerenderinfo)
    {
    }

    private co.com.pdf.awt.geom.Rectangle2D.Float textRectangle;
}
