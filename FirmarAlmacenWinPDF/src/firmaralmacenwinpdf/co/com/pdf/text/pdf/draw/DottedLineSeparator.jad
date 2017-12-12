// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DottedLineSeparator.java

package co.com.pdf.text.pdf.draw;

import co.com.pdf.text.pdf.PdfContentByte;

// Referenced classes of package co.com.pdf.text.pdf.draw:
//            LineSeparator

public class DottedLineSeparator extends LineSeparator
{

    public DottedLineSeparator()
    {
        gap = 5F;
    }

    public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y)
    {
        canvas.saveState();
        canvas.setLineWidth(lineWidth);
        canvas.setLineCap(1);
        canvas.setLineDash(0.0F, gap, gap / 2.0F);
        drawLine(canvas, llx, urx, y);
        canvas.restoreState();
    }

    public float getGap()
    {
        return gap;
    }

    public void setGap(float gap)
    {
        this.gap = gap;
    }

    protected float gap;
}
