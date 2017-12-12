// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerticalText.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfChunk, PdfLine, PdfContentByte, PdfFont

public class VerticalText
{

    public VerticalText(PdfContentByte text)
    {
        chunks = new ArrayList();
        alignment = 0;
        currentChunkMarker = -1;
        curCharSpace = Float.valueOf(0.0F);
        this.text = text;
    }

    public void addText(Phrase phrase)
    {
        Chunk c;
        for(Iterator i$ = phrase.getChunks().iterator(); i$.hasNext(); chunks.add(new PdfChunk(c, null)))
            c = (Chunk)i$.next();

    }

    public void addText(Chunk chunk)
    {
        chunks.add(new PdfChunk(chunk, null));
    }

    public void setVerticalLayout(float startX, float startY, float height, int maxLines, float leading)
    {
        this.startX = startX;
        this.startY = startY;
        this.height = height;
        this.maxLines = maxLines;
        setLeading(leading);
    }

    public void setLeading(float leading)
    {
        this.leading = leading;
    }

    public float getLeading()
    {
        return leading;
    }

    protected PdfLine createLine(float width)
    {
        if(chunks.isEmpty())
            return null;
        splittedChunkText = null;
        currentStandbyChunk = null;
        PdfLine line = new PdfLine(0.0F, width, alignment, 0.0F);
        for(currentChunkMarker = 0; currentChunkMarker < chunks.size(); currentChunkMarker++)
        {
            PdfChunk original = (PdfChunk)chunks.get(currentChunkMarker);
            String total = original.toString();
            currentStandbyChunk = line.add(original);
            if(currentStandbyChunk != null)
            {
                splittedChunkText = original.toString();
                original.setValue(total);
                return line;
            }
        }

        return line;
    }

    protected void shortenChunkArray()
    {
        if(currentChunkMarker < 0)
            return;
        if(currentChunkMarker >= chunks.size())
        {
            chunks.clear();
            return;
        }
        PdfChunk split = (PdfChunk)chunks.get(currentChunkMarker);
        split.setValue(splittedChunkText);
        chunks.set(currentChunkMarker, currentStandbyChunk);
        for(int j = currentChunkMarker - 1; j >= 0; j--)
            chunks.remove(j);

    }

    public int go()
    {
        return go(false);
    }

    public int go(boolean simulate)
    {
        boolean dirty = false;
        PdfContentByte graphics = null;
        if(text != null)
            graphics = text.getDuplicate();
        else
        if(!simulate)
            throw new NullPointerException(MessageLocalization.getComposedMessage("verticaltext.go.with.simulate.eq.eq.false.and.text.eq.eq.null", new Object[0]));
        int status = 0;
        do
        {
            if(maxLines <= 0)
            {
                status = 2;
                if(chunks.isEmpty())
                    status |= 1;
                break;
            }
            if(chunks.isEmpty())
            {
                status = 1;
                break;
            }
            PdfLine line = createLine(height);
            if(!simulate && !dirty)
            {
                text.beginText();
                dirty = true;
            }
            shortenChunkArray();
            if(!simulate)
            {
                text.setTextMatrix(startX, startY - line.indentLeft());
                writeLine(line, text, graphics);
            }
            maxLines--;
            startX -= leading;
        } while(true);
        if(dirty)
        {
            text.endText();
            text.add(graphics);
        }
        return status;
    }

    void writeLine(PdfLine line, PdfContentByte text, PdfContentByte graphics)
    {
        PdfFont currentFont = null;
        Iterator j = line.iterator();
        do
        {
            if(!j.hasNext())
                break;
            PdfChunk chunk = (PdfChunk)j.next();
            if(!chunk.isImage() && chunk.font().compareTo(currentFont) != 0)
            {
                currentFont = chunk.font();
                text.setFontAndSize(currentFont.getFont(), currentFont.size());
            }
            Object textRender[] = (Object[])(Object[])chunk.getAttribute("TEXTRENDERMODE");
            int tr = 0;
            float strokeWidth = 1.0F;
            BaseColor color = chunk.color();
            BaseColor strokeColor = null;
            if(textRender != null)
            {
                tr = ((Integer)textRender[0]).intValue() & 3;
                if(tr != 0)
                    text.setTextRenderingMode(tr);
                if(tr == 1 || tr == 2)
                {
                    strokeWidth = ((Float)textRender[1]).floatValue();
                    if(strokeWidth != 1.0F)
                        text.setLineWidth(strokeWidth);
                    strokeColor = (BaseColor)textRender[2];
                    if(strokeColor == null)
                        strokeColor = color;
                    if(strokeColor != null)
                        text.setColorStroke(strokeColor);
                }
            }
            Float charSpace = (Float)chunk.getAttribute("CHAR_SPACING");
            if(charSpace != null && !curCharSpace.equals(charSpace))
            {
                curCharSpace = Float.valueOf(charSpace.floatValue());
                text.setCharacterSpacing(curCharSpace.floatValue());
            }
            if(color != null)
                text.setColorFill(color);
            text.showText(chunk.toString());
            if(color != null)
                text.resetRGBColorFill();
            if(tr != 0)
                text.setTextRenderingMode(0);
            if(strokeColor != null)
                text.resetRGBColorStroke();
            if(strokeWidth != 1.0F)
                text.setLineWidth(1.0F);
        } while(true);
    }

    public void setOrigin(float startX, float startY)
    {
        this.startX = startX;
        this.startY = startY;
    }

    public float getOriginX()
    {
        return startX;
    }

    public float getOriginY()
    {
        return startY;
    }

    public int getMaxLines()
    {
        return maxLines;
    }

    public void setMaxLines(int maxLines)
    {
        this.maxLines = maxLines;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
    }

    public int getAlignment()
    {
        return alignment;
    }

    public static final int NO_MORE_TEXT = 1;
    public static final int NO_MORE_COLUMN = 2;
    protected ArrayList chunks;
    protected PdfContentByte text;
    protected int alignment;
    protected int currentChunkMarker;
    protected PdfChunk currentStandbyChunk;
    protected String splittedChunkText;
    protected float leading;
    protected float startX;
    protected float startY;
    protected int maxLines;
    protected float height;
    private Float curCharSpace;
}
