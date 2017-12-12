// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LineSeparator.java

package co.com.pdf.text.pdf.draw;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.pdf.PdfContentByte;

// Referenced classes of package co.com.pdf.text.pdf.draw:
//            VerticalPositionMark

public class LineSeparator extends VerticalPositionMark
{

    public LineSeparator(float lineWidth, float percentage, BaseColor lineColor, int align, float offset)
    {
        this.lineWidth = 1.0F;
        this.percentage = 100F;
        alignment = 1;
        this.lineWidth = lineWidth;
        this.percentage = percentage;
        this.lineColor = lineColor;
        alignment = align;
        this.offset = offset;
    }

    public LineSeparator()
    {
        lineWidth = 1.0F;
        percentage = 100F;
        alignment = 1;
    }

    public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y)
    {
        canvas.saveState();
        drawLine(canvas, llx, urx, y);
        canvas.restoreState();
    }

    public void drawLine(PdfContentByte canvas, float leftX, float rightX, float y)
    {
        float w;
        if(getPercentage() < 0.0F)
            w = -getPercentage();
        else
            w = ((rightX - leftX) * getPercentage()) / 100F;
        float s;
        switch(getAlignment())
        {
        case 0: // '\0'
            s = 0.0F;
            break;

        case 2: // '\002'
            s = rightX - leftX - w;
            break;

        default:
            s = (rightX - leftX - w) / 2.0F;
            break;
        }
        canvas.setLineWidth(getLineWidth());
        if(getLineColor() != null)
            canvas.setColorStroke(getLineColor());
        canvas.moveTo(s + leftX, y + offset);
        canvas.lineTo(s + w + leftX, y + offset);
        canvas.stroke();
    }

    public float getLineWidth()
    {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth)
    {
        this.lineWidth = lineWidth;
    }

    public float getPercentage()
    {
        return percentage;
    }

    public void setPercentage(float percentage)
    {
        this.percentage = percentage;
    }

    public BaseColor getLineColor()
    {
        return lineColor;
    }

    public void setLineColor(BaseColor color)
    {
        lineColor = color;
    }

    public int getAlignment()
    {
        return alignment;
    }

    public void setAlignment(int align)
    {
        alignment = align;
    }

    protected float lineWidth;
    protected float percentage;
    protected BaseColor lineColor;
    protected int alignment;
}
