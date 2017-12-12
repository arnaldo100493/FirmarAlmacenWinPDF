// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FloatLayout.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Element;
import co.com.pdf.text.api.Spaceable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf:
//            ColumnText, PdfDiv, PdfPTable, PdfContentByte

public class FloatLayout
{

    public float getYLine()
    {
        return yLine;
    }

    public void setYLine(float yLine)
    {
        this.yLine = yLine;
    }

    public float getFilledWidth()
    {
        return filledWidth;
    }

    public void setFilledWidth(float filledWidth)
    {
        this.filledWidth = filledWidth;
    }

    public FloatLayout(List elements, boolean useAscender)
    {
        compositeColumn.setUseAscender(useAscender);
        this.useAscender = useAscender;
        content = elements;
    }

    public void setSimpleColumn(float llx, float lly, float urx, float ury)
    {
        leftX = Math.min(llx, urx);
        maxY = Math.max(lly, ury);
        minY = Math.min(lly, ury);
        rightX = Math.max(llx, urx);
        floatLeftX = leftX;
        floatRightX = rightX;
        yLine = maxY;
        filledWidth = 0.0F;
    }

    public int layout(PdfContentByte canvas, boolean simulate)
        throws DocumentException
    {
        compositeColumn.setCanvas(canvas);
        int status = 1;
        filledWidth = 0.0F;
        ArrayList floatingElements = new ArrayList();
        List content = ((List) (simulate ? ((List) (new ArrayList(this.content))) : this.content));
        do
        {
            if(content.isEmpty())
                break;
            if(content.get(0) instanceof PdfDiv)
            {
                PdfDiv floatingElement = (PdfDiv)content.get(0);
                if(floatingElement.getFloatType() == PdfDiv.FloatType.LEFT || floatingElement.getFloatType() == PdfDiv.FloatType.RIGHT)
                {
                    floatingElements.add(floatingElement);
                    content.remove(0);
                    continue;
                }
                if(!floatingElements.isEmpty())
                {
                    status = floatingLayout(floatingElements, simulate);
                    if((status & 1) == 0)
                        break;
                }
                content.remove(0);
                status = floatingElement.layout(canvas, useAscender, true, floatLeftX, minY, floatRightX, yLine);
                if(!simulate)
                {
                    canvas.openMCBlock(floatingElement);
                    status = floatingElement.layout(canvas, useAscender, simulate, floatLeftX, minY, floatRightX, yLine);
                    canvas.closeMCBlock(floatingElement);
                }
                yLine -= floatingElement.getActualHeight();
                if(floatingElement.getActualWidth() > filledWidth)
                    filledWidth = floatingElement.getActualWidth();
                if((status & 1) != 0)
                    continue;
                content.add(0, floatingElement);
                break;
            }
            floatingElements.add(content.get(0));
            content.remove(0);
        } while(true);
        if((status & 1) != 0 && !floatingElements.isEmpty())
            status = floatingLayout(floatingElements, simulate);
        content.addAll(0, floatingElements);
        return status;
    }

    private int floatingLayout(List floatingElements, boolean simulate)
        throws DocumentException
    {
        int status = 1;
        float minYLine = yLine;
        float leftWidth = 0.0F;
        float rightWidth = 0.0F;
        ColumnText currentCompositeColumn = compositeColumn;
        if(simulate)
            currentCompositeColumn = ColumnText.duplicate(compositeColumn);
        do
        {
            if(floatingElements.isEmpty())
                break;
            Element nextElement = (Element)floatingElements.get(0);
            floatingElements.remove(0);
            if(nextElement instanceof PdfDiv)
            {
                PdfDiv floatingElement = (PdfDiv)nextElement;
                status = floatingElement.layout(compositeColumn.getCanvas(), useAscender, true, floatLeftX, minY, floatRightX, yLine);
                if((status & 1) == 0)
                {
                    yLine = minYLine;
                    floatLeftX = leftX;
                    floatRightX = rightX;
                    status = floatingElement.layout(compositeColumn.getCanvas(), useAscender, true, floatLeftX, minY, floatRightX, yLine);
                    if((status & 1) == 0)
                    {
                        floatingElements.add(0, floatingElement);
                        break;
                    }
                }
                if(floatingElement.getFloatType() == PdfDiv.FloatType.LEFT)
                {
                    status = floatingElement.layout(compositeColumn.getCanvas(), useAscender, simulate, floatLeftX, minY, floatRightX, yLine);
                    floatLeftX += floatingElement.getActualWidth();
                    leftWidth += floatingElement.getActualWidth();
                } else
                if(floatingElement.getFloatType() == PdfDiv.FloatType.RIGHT)
                {
                    status = floatingElement.layout(compositeColumn.getCanvas(), useAscender, simulate, floatRightX - floatingElement.getActualWidth() - 0.01F, minY, floatRightX, yLine);
                    floatRightX -= floatingElement.getActualWidth();
                    rightWidth += floatingElement.getActualWidth();
                }
                minYLine = Math.min(minYLine, yLine - floatingElement.getActualHeight());
                continue;
            }
            if(nextElement instanceof Spaceable)
                yLine -= ((Spaceable)nextElement).getSpacingBefore();
            if(simulate)
            {
                if(nextElement instanceof PdfPTable)
                    currentCompositeColumn.addElement(new PdfPTable((PdfPTable)nextElement));
                else
                    currentCompositeColumn.addElement(nextElement);
            } else
            {
                currentCompositeColumn.addElement(nextElement);
            }
            if(yLine > minYLine)
                currentCompositeColumn.setSimpleColumn(floatLeftX, yLine, floatRightX, minYLine);
            else
                currentCompositeColumn.setSimpleColumn(floatLeftX, yLine, floatRightX, minY);
            currentCompositeColumn.setFilledWidth(0.0F);
            status = currentCompositeColumn.go(simulate);
            if(yLine > minYLine && (floatLeftX > leftX || floatRightX < rightX) && (status & 1) == 0)
            {
                yLine = minYLine;
                floatLeftX = leftX;
                floatRightX = rightX;
                if(leftWidth != 0.0F && rightWidth != 0.0F)
                {
                    filledWidth = rightX - leftX;
                } else
                {
                    if(leftWidth > filledWidth)
                        filledWidth = leftWidth;
                    if(rightWidth > filledWidth)
                        filledWidth = rightWidth;
                }
                leftWidth = 0.0F;
                rightWidth = 0.0F;
                if(simulate && (nextElement instanceof PdfPTable))
                    currentCompositeColumn.addElement(new PdfPTable((PdfPTable)nextElement));
                currentCompositeColumn.setSimpleColumn(floatLeftX, yLine, floatRightX, minY);
                status = currentCompositeColumn.go(simulate);
                minYLine = currentCompositeColumn.getYLine() + currentCompositeColumn.getDescender();
                yLine = minYLine;
                if(currentCompositeColumn.getFilledWidth() > filledWidth)
                    filledWidth = currentCompositeColumn.getFilledWidth();
            } else
            {
                if(rightWidth > 0.0F)
                    rightWidth += currentCompositeColumn.getFilledWidth();
                else
                if(leftWidth > 0.0F)
                    leftWidth += currentCompositeColumn.getFilledWidth();
                else
                if(currentCompositeColumn.getFilledWidth() > filledWidth)
                    filledWidth = currentCompositeColumn.getFilledWidth();
                minYLine = Math.min(currentCompositeColumn.getYLine() + currentCompositeColumn.getDescender(), minYLine);
                yLine = currentCompositeColumn.getYLine() + currentCompositeColumn.getDescender();
            }
            if((status & 1) == 0)
            {
                if(!simulate)
                {
                    floatingElements.addAll(0, currentCompositeColumn.getCompositeElements());
                    currentCompositeColumn.getCompositeElements().clear();
                } else
                {
                    floatingElements.add(0, nextElement);
                    currentCompositeColumn.setText(null);
                }
                break;
            }
            currentCompositeColumn.setText(null);
        } while(true);
        if(leftWidth != 0.0F && rightWidth != 0.0F)
        {
            filledWidth = rightX - leftX;
        } else
        {
            if(leftWidth > filledWidth)
                filledWidth = leftWidth;
            if(rightWidth > filledWidth)
                filledWidth = rightWidth;
        }
        yLine = minYLine;
        floatLeftX = leftX;
        floatRightX = rightX;
        return status;
    }

    protected float maxY;
    protected float minY;
    protected float leftX;
    protected float rightX;
    protected float yLine;
    protected float floatLeftX;
    protected float floatRightX;
    protected float filledWidth;
    protected final ColumnText compositeColumn = new ColumnText(null);
    protected final List content;
    protected final boolean useAscender;
}
