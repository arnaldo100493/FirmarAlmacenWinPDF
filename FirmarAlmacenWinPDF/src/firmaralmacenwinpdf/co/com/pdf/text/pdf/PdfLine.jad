// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfLine.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfChunk, PdfFont, BaseFont

public class PdfLine
{

    PdfLine(float left, float right, int alignment, float height)
    {
        newlineSplit = false;
        isRTL = false;
        listItem = null;
        tabStop = null;
        tabStopAnchorPosition = (0.0F / 0.0F);
        tabPosition = (0.0F / 0.0F);
        this.left = left;
        width = right - left;
        originalWidth = width;
        this.alignment = alignment;
        this.height = height;
        line = new ArrayList();
    }

    PdfLine(float left, float originalWidth, float remainingWidth, int alignment, boolean newlineSplit, ArrayList line, boolean isRTL)
    {
        this.newlineSplit = false;
        this.isRTL = false;
        listItem = null;
        tabStop = null;
        tabStopAnchorPosition = (0.0F / 0.0F);
        tabPosition = (0.0F / 0.0F);
        this.left = left;
        this.originalWidth = originalWidth;
        width = remainingWidth;
        this.alignment = alignment;
        this.line = line;
        this.newlineSplit = newlineSplit;
        this.isRTL = isRTL;
    }

    PdfChunk add(PdfChunk chunk)
    {
        if(chunk == null || chunk.toString().equals(""))
            return null;
        PdfChunk overflow = chunk.split(width);
        newlineSplit = chunk.isNewlineSplit() || overflow == null;
        if(chunk.isTab())
        {
            Object tab[] = (Object[])(Object[])chunk.getAttribute("TAB");
            if(chunk.isAttribute("TABSETTINGS"))
            {
                boolean isWhiteSpace = ((Boolean)tab[1]).booleanValue();
                if(!isWhiteSpace || !line.isEmpty())
                {
                    flush();
                    tabStopAnchorPosition = (0.0F / 0.0F);
                    tabStop = PdfChunk.getTabStop(chunk, originalWidth - width);
                    if(tabStop.getPosition() > originalWidth)
                    {
                        width = 0.0F;
                        if(isWhiteSpace)
                            return null;
                        else
                            return chunk;
                    }
                    tabStop.setPosition(tabStop.getPosition());
                    chunk.setTabStop(tabStop);
                    if(tabStop.getAlignment() == co.com.pdf.text.TabStop.Alignment.LEFT)
                    {
                        width = originalWidth - tabStop.getPosition();
                        tabStop = null;
                        tabPosition = (0.0F / 0.0F);
                    } else
                    {
                        tabPosition = originalWidth - width;
                    }
                } else
                {
                    return null;
                }
            } else
            {
                Float tabStopPosition = Float.valueOf(((Float)tab[1]).floatValue());
                boolean newline = ((Boolean)tab[2]).booleanValue();
                if(newline && tabPosition < originalWidth - width)
                    return chunk;
                chunk.adjustLeft(left);
                width = originalWidth - tabStopPosition.floatValue();
            }
            addToLine(chunk);
        } else
        if(chunk.length() > 0 || chunk.isImage())
        {
            if(overflow != null)
                chunk.trimLastSpace();
            width -= chunk.width();
            addToLine(chunk);
        } else
        {
            if(line.size() < 1)
            {
                chunk = overflow;
                overflow = chunk.truncate(width);
                width -= chunk.width();
                if(chunk.length() > 0)
                {
                    addToLine(chunk);
                    return overflow;
                }
                if(overflow != null)
                    addToLine(overflow);
                return null;
            }
            width += ((PdfChunk)line.get(line.size() - 1)).trimLastSpace();
        }
        return overflow;
    }

    private void addToLine(PdfChunk chunk)
    {
        if(chunk.changeLeading)
        {
            float f;
            if(chunk.isImage())
            {
                Image img = chunk.getImage();
                f = chunk.getImageHeight() + chunk.getImageOffsetY() + img.getBorderWidthTop() + img.getSpacingBefore();
            } else
            {
                f = chunk.getLeading();
            }
            if(f > height)
                height = f;
        }
        if(tabStop != null && tabStop.getAlignment() == co.com.pdf.text.TabStop.Alignment.ANCHOR && Float.isNaN(tabStopAnchorPosition))
        {
            String value = chunk.toString();
            int anchorIndex = value.indexOf(tabStop.getAnchorChar());
            if(anchorIndex != -1)
            {
                float subWidth = chunk.width(value.substring(anchorIndex, value.length()));
                tabStopAnchorPosition = originalWidth - width - subWidth;
            }
        }
        line.add(chunk);
    }

    public int size()
    {
        return line.size();
    }

    public Iterator iterator()
    {
        return line.iterator();
    }

    float height()
    {
        return height;
    }

    float indentLeft()
    {
        if(isRTL)
        {
            switch(alignment)
            {
            case 0: // '\0'
                return left + width;

            case 1: // '\001'
                return left + width / 2.0F;
            }
            return left;
        }
        if(getSeparatorCount() <= 0)
            switch(alignment)
            {
            case 2: // '\002'
                return left + width;

            case 1: // '\001'
                return left + width / 2.0F;
            }
        return left;
    }

    public boolean hasToBeJustified()
    {
        return (alignment == 3 && !newlineSplit || alignment == 8) && width != 0.0F;
    }

    public void resetAlignment()
    {
        if(alignment == 3)
            alignment = 0;
    }

    void setExtraIndent(float extra)
    {
        left += extra;
        width -= extra;
        originalWidth -= extra;
    }

    float widthLeft()
    {
        return width;
    }

    int numberOfSpaces()
    {
        int numberOfSpaces = 0;
        for(Iterator i$ = line.iterator(); i$.hasNext();)
        {
            PdfChunk pdfChunk = (PdfChunk)i$.next();
            String tmp = pdfChunk.toString();
            int length = tmp.length();
            int i = 0;
            while(i < length) 
            {
                if(tmp.charAt(i) == ' ')
                    numberOfSpaces++;
                i++;
            }
        }

        return numberOfSpaces;
    }

    public void setListItem(ListItem listItem)
    {
        this.listItem = listItem;
    }

    public Chunk listSymbol()
    {
        return listItem == null ? null : listItem.getListSymbol();
    }

    public float listIndent()
    {
        return listItem == null ? 0.0F : listItem.getIndentationLeft();
    }

    public ListItem listItem()
    {
        return listItem;
    }

    public String toString()
    {
        StringBuffer tmp = new StringBuffer();
        PdfChunk pdfChunk;
        for(Iterator i$ = line.iterator(); i$.hasNext(); tmp.append(pdfChunk.toString()))
            pdfChunk = (PdfChunk)i$.next();

        return tmp.toString();
    }

    public int getLineLengthUtf32()
    {
        int total = 0;
        for(Iterator i$ = line.iterator(); i$.hasNext();)
        {
            Object element = i$.next();
            total += ((PdfChunk)element).lengthUtf32();
        }

        return total;
    }

    public boolean isNewlineSplit()
    {
        return newlineSplit && alignment != 8;
    }

    public int getLastStrokeChunk()
    {
        int lastIdx = line.size() - 1;
        do
        {
            if(lastIdx < 0)
                break;
            PdfChunk chunk = (PdfChunk)line.get(lastIdx);
            if(chunk.isStroked())
                break;
            lastIdx--;
        } while(true);
        return lastIdx;
    }

    public PdfChunk getChunk(int idx)
    {
        if(idx < 0 || idx >= line.size())
            return null;
        else
            return (PdfChunk)line.get(idx);
    }

    public float getOriginalWidth()
    {
        return originalWidth;
    }

    float[] getMaxSize(float fixedLeading, float multipliedLeading)
    {
        float normal_leading = 0.0F;
        float image_leading = -10000F;
        for(int k = 0; k < line.size(); k++)
        {
            PdfChunk chunk = (PdfChunk)line.get(k);
            if(chunk.isImage())
            {
                Image img = chunk.getImage();
                if(chunk.changeLeading())
                {
                    float height = chunk.getImageHeight() + chunk.getImageOffsetY() + img.getSpacingBefore();
                    image_leading = Math.max(height, image_leading);
                }
                continue;
            }
            if(chunk.changeLeading())
                normal_leading = Math.max(chunk.getLeading(), normal_leading);
            else
                normal_leading = Math.max(fixedLeading + multipliedLeading * chunk.font().size(), normal_leading);
        }

        return (new float[] {
            normal_leading <= 0.0F ? fixedLeading : normal_leading, image_leading
        });
    }

    boolean isRTL()
    {
        return isRTL;
    }

    int getSeparatorCount()
    {
        int s = 0;
        Iterator i$ = line.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfChunk ck = (PdfChunk)element;
            if(ck.isTab())
            {
                if(!ck.isAttribute("TABSETTINGS"))
                    return -1;
            } else
            if(ck.isHorizontalSeparator())
                s++;
        } while(true);
        return s;
    }

    public float getWidthCorrected(float charSpacing, float wordSpacing)
    {
        float total = 0.0F;
        for(int k = 0; k < line.size(); k++)
        {
            PdfChunk ck = (PdfChunk)line.get(k);
            total += ck.getWidthCorrected(charSpacing, wordSpacing);
        }

        return total;
    }

    public float getAscender()
    {
        float ascender = 0.0F;
        for(int k = 0; k < line.size(); k++)
        {
            PdfChunk ck = (PdfChunk)line.get(k);
            if(ck.isImage())
            {
                ascender = Math.max(ascender, ck.getImageHeight() + ck.getImageOffsetY());
            } else
            {
                PdfFont font = ck.font();
                float textRise = ck.getTextRise();
                ascender = Math.max(ascender, (textRise <= 0.0F ? 0.0F : textRise) + font.getFont().getFontDescriptor(1, font.size()));
            }
        }

        return ascender;
    }

    public float getDescender()
    {
        float descender = 0.0F;
        for(int k = 0; k < line.size(); k++)
        {
            PdfChunk ck = (PdfChunk)line.get(k);
            if(ck.isImage())
            {
                descender = Math.min(descender, ck.getImageOffsetY());
            } else
            {
                PdfFont font = ck.font();
                float textRise = ck.getTextRise();
                descender = Math.min(descender, (textRise >= 0.0F ? 0.0F : textRise) + font.getFont().getFontDescriptor(3, font.size()));
            }
        }

        return descender;
    }

    public void flush()
    {
        if(tabStop != null)
        {
            float textWidth = originalWidth - width - tabPosition;
            float tabStopPosition = tabStop.getPosition(tabPosition, originalWidth - width, tabStopAnchorPosition);
            width = originalWidth - tabStopPosition - textWidth;
            if(width < 0.0F)
                tabStopPosition += width;
            tabStop.setPosition(tabStopPosition);
            tabStop = null;
            tabPosition = (0.0F / 0.0F);
        }
    }

    protected ArrayList line;
    protected float left;
    protected float width;
    protected int alignment;
    protected float height;
    protected boolean newlineSplit;
    protected float originalWidth;
    protected boolean isRTL;
    protected ListItem listItem;
    protected TabStop tabStop;
    protected float tabStopAnchorPosition;
    protected float tabPosition;
}
