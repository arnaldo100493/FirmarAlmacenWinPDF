// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerticalPositionMark.java

package co.com.pdf.text.pdf.draw;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.PdfContentByte;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf.draw:
//            DrawInterface

public class VerticalPositionMark
    implements DrawInterface, Element
{

    public VerticalPositionMark()
    {
        drawInterface = null;
        offset = 0.0F;
    }

    public VerticalPositionMark(DrawInterface drawInterface, float offset)
    {
        this.drawInterface = null;
        this.offset = 0.0F;
        this.drawInterface = drawInterface;
        this.offset = offset;
    }

    public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y)
    {
        if(drawInterface != null)
            drawInterface.draw(canvas, llx, lly, urx, ury, y + offset);
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(this);
        }
        catch(DocumentException e)
        {
            return false;
        }
    }

    public int type()
    {
        return 55;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return false;
    }

    public List getChunks()
    {
        List list = new ArrayList();
        list.add(new Chunk(this, true));
        return list;
    }

    public DrawInterface getDrawInterface()
    {
        return drawInterface;
    }

    public void setDrawInterface(DrawInterface drawInterface)
    {
        this.drawInterface = drawInterface;
    }

    public float getOffset()
    {
        return offset;
    }

    public void setOffset(float offset)
    {
        this.offset = offset;
    }

    protected DrawInterface drawInterface;
    protected float offset;
}
