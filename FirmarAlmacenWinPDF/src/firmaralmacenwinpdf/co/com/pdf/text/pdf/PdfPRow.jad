// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPRow.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPCell, PdfPHeaderCell, PdfObject, ColumnText, 
//            PdfPCellEvent, PdfPTable, PdfName, PdfContentByte, 
//            ByteBuffer, PdfWriter

public class PdfPRow
    implements IAccessibleElement
{

    public PdfPRow(PdfPCell cells[])
    {
        this(cells, null);
    }

    public PdfPRow(PdfPCell cells[], PdfPRow source)
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPRow);
        mayNotBreak = false;
        maxHeight = 0.0F;
        calculated = false;
        role = PdfName.TR;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        this.cells = cells;
        widths = new float[cells.length];
        initExtraHeights();
        if(source != null)
        {
            id = source.id;
            role = source.role;
            if(source.accessibleAttributes != null)
                accessibleAttributes = new HashMap(source.accessibleAttributes);
        }
    }

    public PdfPRow(PdfPRow row)
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPRow);
        mayNotBreak = false;
        maxHeight = 0.0F;
        calculated = false;
        role = PdfName.TR;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        mayNotBreak = row.mayNotBreak;
        maxHeight = row.maxHeight;
        calculated = row.calculated;
        cells = new PdfPCell[row.cells.length];
        for(int k = 0; k < cells.length; k++)
        {
            if(row.cells[k] == null)
                continue;
            if(row.cells[k] instanceof PdfPHeaderCell)
                cells[k] = new PdfPHeaderCell((PdfPHeaderCell)row.cells[k]);
            else
                cells[k] = new PdfPCell(row.cells[k]);
        }

        widths = new float[cells.length];
        System.arraycopy(row.widths, 0, widths, 0, cells.length);
        initExtraHeights();
        id = row.id;
        role = row.role;
        if(row.accessibleAttributes != null)
            accessibleAttributes = new HashMap(row.accessibleAttributes);
    }

    public boolean setWidths(float widths[])
    {
        if(widths.length != cells.length)
            return false;
        System.arraycopy(widths, 0, this.widths, 0, cells.length);
        float total = 0.0F;
        calculated = false;
        for(int k = 0; k < widths.length; k++)
        {
            PdfPCell cell = cells[k];
            if(cell == null)
            {
                total += widths[k];
                continue;
            }
            cell.setLeft(total);
            for(int last = k + cell.getColspan(); k < last; k++)
                total += widths[k];

            k--;
            cell.setRight(total);
            cell.setTop(0.0F);
        }

        return true;
    }

    protected void initExtraHeights()
    {
        extraHeights = new float[cells.length];
        for(int i = 0; i < extraHeights.length; i++)
            extraHeights[i] = 0.0F;

    }

    public void setExtraHeight(int cell, float height)
    {
        if(cell < 0 || cell >= cells.length)
        {
            return;
        } else
        {
            extraHeights[cell] = height;
            return;
        }
    }

    protected void calculateHeights()
    {
        maxHeight = 0.0F;
        for(int k = 0; k < cells.length; k++)
        {
            PdfPCell cell = cells[k];
            float height = 0.0F;
            if(cell == null)
                continue;
            height = cell.getMaxHeight();
            if(height > maxHeight && cell.getRowspan() == 1)
                maxHeight = height;
        }

        calculated = true;
    }

    public void setMayNotBreak(boolean mayNotBreak)
    {
        this.mayNotBreak = mayNotBreak;
    }

    public boolean isMayNotBreak()
    {
        return mayNotBreak;
    }

    public void writeBorderAndBackground(float xPos, float yPos, float currentMaxHeight, PdfPCell cell, PdfContentByte canvases[])
    {
        BaseColor background = cell.getBackgroundColor();
        if(background != null || cell.hasBorders())
        {
            float right = cell.getRight() + xPos;
            float top = cell.getTop() + yPos;
            float left = cell.getLeft() + xPos;
            float bottom = top - currentMaxHeight;
            if(background != null)
            {
                PdfContentByte backgr = canvases[1];
                backgr.setColorFill(background);
                backgr.rectangle(left, bottom, right - left, top - bottom);
                backgr.fill();
            }
            if(cell.hasBorders())
            {
                Rectangle newRect = new Rectangle(left, bottom, right, top);
                newRect.cloneNonPositionParameters(cell);
                newRect.setBackgroundColor(null);
                PdfContentByte lineCanvas = canvases[2];
                lineCanvas.rectangle(newRect);
            }
        }
    }

    protected void saveAndRotateCanvases(PdfContentByte canvases[], float a, float b, float c, float d, float e, float f)
    {
        int last = 4;
        if(canvasesPos == null)
            canvasesPos = new int[last * 2];
        for(int k = 0; k < last; k++)
        {
            ByteBuffer bb = canvases[k].getInternalBuffer();
            canvasesPos[k * 2] = bb.size();
            canvases[k].saveState();
            canvases[k].concatCTM(a, b, c, d, e, f);
            canvasesPos[k * 2 + 1] = bb.size();
        }

    }

    protected void restoreCanvases(PdfContentByte canvases[])
    {
        int last = 4;
        for(int k = 0; k < last; k++)
        {
            ByteBuffer bb = canvases[k].getInternalBuffer();
            int p1 = bb.size();
            canvases[k].restoreState();
            if(p1 == canvasesPos[k * 2 + 1])
                bb.setSize(canvasesPos[k * 2]);
        }

    }

    public static float setColumn(ColumnText ct, float left, float bottom, float right, float top)
    {
        if(left > right)
            right = left;
        if(bottom > top)
            top = bottom;
        ct.setSimpleColumn(left, bottom, right, top);
        return top;
    }

    public void writeCells(int colStart, int colEnd, float xPos, float yPos, PdfContentByte canvases[], boolean reusable)
    {
        int k;
        if(!calculated)
            calculateHeights();
        if(colEnd < 0)
            colEnd = cells.length;
        else
            colEnd = Math.min(colEnd, cells.length);
        if(colStart < 0)
            colStart = 0;
        if(colStart >= colEnd)
            return;
        int newStart;
        for(newStart = colStart; newStart >= 0 && cells[newStart] == null; newStart--)
            if(newStart > 0)
                xPos -= widths[newStart - 1];

        if(newStart < 0)
            newStart = 0;
        if(cells[newStart] != null)
            xPos -= cells[newStart].getLeft();
        if(isTagged(canvases[3]))
            canvases[3].openMCBlock(this);
        k = newStart;
_L3:
        if(k >= colEnd) goto _L2; else goto _L1
_L1:
        PdfPCell cell;
        float currentMaxHeight;
        float tly;
        ColumnText ct;
        cell = cells[k];
        if(cell == null)
            continue; /* Loop/switch isn't completed */
        if(isTagged(canvases[3]))
            canvases[3].openMCBlock(cell);
        currentMaxHeight = maxHeight + extraHeights[k];
        writeBorderAndBackground(xPos, yPos, currentMaxHeight, cell, canvases);
        Image img = cell.getImage();
        tly = (cell.getTop() + yPos) - cell.getEffectivePaddingTop();
        if(cell.getHeight() <= currentMaxHeight)
            switch(cell.getVerticalAlignment())
            {
            case 6: // '\006'
                tly = (((cell.getTop() + yPos) - currentMaxHeight) + cell.getHeight()) - cell.getEffectivePaddingTop();
                break;

            case 5: // '\005'
                tly = (cell.getTop() + yPos + (cell.getHeight() - currentMaxHeight) / 2.0F) - cell.getEffectivePaddingTop();
                break;
            }
        if(img != null)
        {
            if(cell.getRotation() != 0)
            {
                img = Image.getInstance(img);
                img.setRotation(img.getImageRotation() + (float)(((double)cell.getRotation() * 3.1415926535897931D) / 180D));
            }
            boolean vf = false;
            if(cell.getHeight() > currentMaxHeight)
            {
                if(!img.isScaleToFitHeight())
                    continue; /* Loop/switch isn't completed */
                img.scalePercent(100F);
                float scale = (currentMaxHeight - cell.getEffectivePaddingTop() - cell.getEffectivePaddingBottom()) / img.getScaledHeight();
                img.scalePercent(scale * 100F);
                vf = true;
            }
            float left = cell.getLeft() + xPos + cell.getEffectivePaddingLeft();
            if(vf)
            {
                switch(cell.getHorizontalAlignment())
                {
                case 1: // '\001'
                    left = xPos + ((cell.getLeft() + cell.getEffectivePaddingLeft() + cell.getRight()) - cell.getEffectivePaddingRight() - img.getScaledWidth()) / 2.0F;
                    break;

                case 2: // '\002'
                    left = (xPos + cell.getRight()) - cell.getEffectivePaddingRight() - img.getScaledWidth();
                    break;
                }
                tly = (cell.getTop() + yPos) - cell.getEffectivePaddingTop();
            }
            img.setAbsolutePosition(left, tly - img.getScaledHeight());
            try
            {
                if(isTagged(canvases[3]))
                    canvases[3].openMCBlock(img);
                canvases[3].addImage(img);
                if(isTagged(canvases[3]))
                    canvases[3].closeMCBlock(img);
            }
            catch(DocumentException e)
            {
                throw new ExceptionConverter(e);
            }
            break MISSING_BLOCK_LABEL_1616;
        }
        if(cell.getRotation() != 90 && cell.getRotation() != 270)
            break MISSING_BLOCK_LABEL_1205;
        float netWidth = currentMaxHeight - cell.getEffectivePaddingTop() - cell.getEffectivePaddingBottom();
        float netHeight = cell.getWidth() - cell.getEffectivePaddingLeft() - cell.getEffectivePaddingRight();
        ct = ColumnText.duplicate(cell.getColumn());
        ct.setCanvases(canvases);
        ct.setSimpleColumn(0.0F, 0.0F, netWidth + 0.001F, -netHeight);
        try
        {
            ct.go(true);
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
        float calcHeight = -ct.getYLine();
        if(netWidth <= 0.0F || netHeight <= 0.0F)
            calcHeight = 0.0F;
        if(calcHeight <= 0.0F)
            break MISSING_BLOCK_LABEL_1616;
        if(cell.isUseDescender())
            calcHeight -= ct.getDescender();
        if(reusable)
            ct = ColumnText.duplicate(cell.getColumn());
        else
            ct = cell.getColumn();
        ct.setCanvases(canvases);
        ct.setSimpleColumn(-0.003F, -0.001F, netWidth + 0.003F, calcHeight);
        if(cell.getRotation() == 90)
        {
            float pivotY = ((cell.getTop() + yPos) - currentMaxHeight) + cell.getEffectivePaddingBottom();
            float pivotX;
            switch(cell.getVerticalAlignment())
            {
            case 6: // '\006'
                pivotX = (cell.getLeft() + xPos + cell.getWidth()) - cell.getEffectivePaddingRight();
                break;

            case 5: // '\005'
                pivotX = cell.getLeft() + xPos + (((cell.getWidth() + cell.getEffectivePaddingLeft()) - cell.getEffectivePaddingRight()) + calcHeight) / 2.0F;
                break;

            default:
                pivotX = cell.getLeft() + xPos + cell.getEffectivePaddingLeft() + calcHeight;
                break;
            }
            saveAndRotateCanvases(canvases, 0.0F, 1.0F, -1F, 0.0F, pivotX, pivotY);
        } else
        {
            float pivotY = (cell.getTop() + yPos) - cell.getEffectivePaddingTop();
            float pivotX;
            switch(cell.getVerticalAlignment())
            {
            case 6: // '\006'
                pivotX = cell.getLeft() + xPos + cell.getEffectivePaddingLeft();
                break;

            case 5: // '\005'
                pivotX = cell.getLeft() + xPos + ((cell.getWidth() + cell.getEffectivePaddingLeft()) - cell.getEffectivePaddingRight() - calcHeight) / 2.0F;
                break;

            default:
                pivotX = (cell.getLeft() + xPos + cell.getWidth()) - cell.getEffectivePaddingRight() - calcHeight;
                break;
            }
            saveAndRotateCanvases(canvases, 0.0F, -1F, 1.0F, 0.0F, pivotX, pivotY);
        }
        try
        {
            ct.go();
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
        restoreCanvases(canvases);
        break MISSING_BLOCK_LABEL_1616;
        Exception exception;
        exception;
        restoreCanvases(canvases);
        throw exception;
        ColumnText ct;
        float fixedHeight = cell.getFixedHeight();
        float rightLimit = (cell.getRight() + xPos) - cell.getEffectivePaddingRight();
        float leftLimit = cell.getLeft() + xPos + cell.getEffectivePaddingLeft();
        if(cell.isNoWrap())
            switch(cell.getHorizontalAlignment())
            {
            case 1: // '\001'
                rightLimit += 10000F;
                leftLimit -= 10000F;
                break;

            case 2: // '\002'
                if(cell.getRotation() == 180)
                    rightLimit += 20000F;
                else
                    leftLimit -= 20000F;
                break;

            default:
                if(cell.getRotation() == 180)
                    leftLimit -= 20000F;
                else
                    rightLimit += 20000F;
                break;
            }
        if(reusable)
            ct = ColumnText.duplicate(cell.getColumn());
        else
            ct = cell.getColumn();
        ct.setCanvases(canvases);
        float bry = tly - (currentMaxHeight - cell.getEffectivePaddingTop() - cell.getEffectivePaddingBottom());
        if(fixedHeight > 0.0F && cell.getHeight() > currentMaxHeight)
        {
            tly = (cell.getTop() + yPos) - cell.getEffectivePaddingTop();
            bry = ((cell.getTop() + yPos) - currentMaxHeight) + cell.getEffectivePaddingBottom();
        }
        if(tly <= bry && !ct.zeroHeightElement() || leftLimit >= rightLimit)
            break MISSING_BLOCK_LABEL_1616;
        ct.setSimpleColumn(leftLimit, bry - 0.001F, rightLimit, tly);
        if(cell.getRotation() == 180)
        {
            float shx = leftLimit + rightLimit;
            float shy = (((yPos + yPos) - currentMaxHeight) + cell.getEffectivePaddingBottom()) - cell.getEffectivePaddingTop();
            saveAndRotateCanvases(canvases, -1F, 0.0F, 0.0F, -1F, shx, shy);
        }
        try
        {
            ct.go();
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
        if(cell.getRotation() == 180)
            restoreCanvases(canvases);
        break MISSING_BLOCK_LABEL_1616;
        Exception exception1;
        exception1;
        if(cell.getRotation() == 180)
            restoreCanvases(canvases);
        throw exception1;
        PdfPCellEvent evt = cell.getCellEvent();
        if(evt != null)
        {
            Rectangle rect = new Rectangle(cell.getLeft() + xPos, (cell.getTop() + yPos) - currentMaxHeight, cell.getRight() + xPos, cell.getTop() + yPos);
            evt.cellLayout(cell, rect, canvases);
        }
        if(isTagged(canvases[3]))
            canvases[3].closeMCBlock(cell);
        k++;
          goto _L3
_L2:
        if(isTagged(canvases[3]))
            canvases[3].closeMCBlock(this);
        return;
    }

    public boolean isCalculated()
    {
        return calculated;
    }

    public float getMaxHeights()
    {
        if(!calculated)
            calculateHeights();
        return maxHeight;
    }

    public void setMaxHeights(float maxHeight)
    {
        this.maxHeight = maxHeight;
    }

    float[] getEventWidth(float xPos, float absoluteWidths[])
    {
        int n = 1;
        for(int k = 0; k < cells.length;)
            if(cells[k] != null)
            {
                n++;
                k += cells[k].getColspan();
            } else
            {
                while(k < cells.length && cells[k] == null) 
                {
                    n++;
                    k++;
                }
            }

        float width[] = new float[n];
        width[0] = xPos;
        n = 1;
        for(int k = 0; k < cells.length && n < width.length;)
            if(cells[k] != null)
            {
                int colspan = cells[k].getColspan();
                width[n] = width[n - 1];
                for(int i = 0; i < colspan && k < absoluteWidths.length; i++)
                    width[n] += absoluteWidths[k++];

                n++;
            } else
            {
                for(width[n] = width[n - 1]; k < cells.length && cells[k] == null; width[n] += absoluteWidths[k++]);
                n++;
            }

        return width;
    }

    public void copyRowContent(PdfPTable table, int idx)
    {
        if(table == null)
            return;
        for(int i = 0; i < cells.length; i++)
        {
            int lastRow = idx;
            PdfPCell copy;
            for(copy = table.getRow(lastRow).getCells()[i]; copy == null && lastRow > 0; copy = table.getRow(--lastRow).getCells()[i]);
            if(cells[i] != null && copy != null)
            {
                cells[i].setColumn(copy.getColumn());
                calculated = false;
            }
        }

    }

    public PdfPRow splitRow(PdfPTable table, int rowIndex, float new_height)
    {
        LOGGER.info((new StringBuilder()).append("Splitting ").append(rowIndex).append(" ").append(new_height).toString());
        PdfPCell newCells[] = new PdfPCell[cells.length];
        float fixHs[] = new float[cells.length];
        float minHs[] = new float[cells.length];
        boolean allEmpty = true;
        for(int k = 0; k < cells.length; k++)
        {
            float newHeight = new_height;
            PdfPCell cell = cells[k];
            if(cell == null)
            {
                int index = rowIndex;
                if(!table.rowSpanAbove(index, k))
                    continue;
                while(table.rowSpanAbove(--index, k)) 
                    newHeight += table.getRow(index).getMaxHeights();
                PdfPRow row = table.getRow(index);
                if(row != null && row.getCells()[k] != null)
                {
                    newCells[k] = new PdfPCell(row.getCells()[k]);
                    newCells[k].setColumn(null);
                    newCells[k].setRowspan((row.getCells()[k].getRowspan() - rowIndex) + index);
                    allEmpty = false;
                }
                continue;
            }
            fixHs[k] = cell.getFixedHeight();
            minHs[k] = cell.getMinimumHeight();
            Image img = cell.getImage();
            PdfPCell newCell = new PdfPCell(cell);
            if(img != null)
            {
                float padding = cell.getEffectivePaddingBottom() + cell.getEffectivePaddingTop() + 2.0F;
                if((img.isScaleToFitHeight() || img.getScaledHeight() + padding < newHeight) && newHeight > padding)
                {
                    newCell.setPhrase(null);
                    allEmpty = false;
                }
            } else
            {
                ColumnText ct = ColumnText.duplicate(cell.getColumn());
                float left = cell.getLeft() + cell.getEffectivePaddingLeft();
                float bottom = (cell.getTop() + cell.getEffectivePaddingBottom()) - newHeight;
                float right = cell.getRight() - cell.getEffectivePaddingRight();
                float top = cell.getTop() - cell.getEffectivePaddingTop();
                float y;
                switch(cell.getRotation())
                {
                case 90: // 'Z'
                case 270: 
                    y = setColumn(ct, bottom, left, top, right);
                    break;

                default:
                    y = setColumn(ct, left, bottom + 1E-005F, cell.isNoWrap() ? 20000F : right, top);
                    break;
                }
                int status;
                try
                {
                    status = ct.go(true);
                }
                catch(DocumentException e)
                {
                    throw new ExceptionConverter(e);
                }
                boolean thisEmpty = ct.getYLine() == y;
                if(thisEmpty)
                {
                    newCell.setColumn(ColumnText.duplicate(cell.getColumn()));
                    ct.setFilledWidth(0.0F);
                } else
                if((status & 1) == 0)
                {
                    newCell.setColumn(ct);
                    ct.setFilledWidth(0.0F);
                } else
                {
                    newCell.setPhrase(null);
                }
                allEmpty = allEmpty && thisEmpty;
            }
            newCells[k] = newCell;
            cell.setFixedHeight(newHeight);
        }

        if(allEmpty)
        {
            for(int k = 0; k < cells.length; k++)
            {
                PdfPCell cell = cells[k];
                if(cell == null)
                    continue;
                if(fixHs[k] > 0.0F)
                    cell.setFixedHeight(fixHs[k]);
                else
                    cell.setMinimumHeight(minHs[k]);
            }

            return null;
        } else
        {
            calculateHeights();
            PdfPRow split = new PdfPRow(newCells, this);
            split.widths = (float[])(float[])widths.clone();
            return split;
        }
    }

    public float getMaxRowHeightsWithoutCalculating()
    {
        return maxHeight;
    }

    public void setFinalMaxHeights(float maxHeight)
    {
        setMaxHeights(maxHeight);
        calculated = true;
    }

    public void splitRowspans(PdfPTable original, int originalIdx, PdfPTable part, int partIdx)
    {
        if(original == null || part == null)
            return;
        for(int i = 0; i < cells.length;)
            if(cells[i] == null)
            {
                int splittedRowIdx = original.getCellStartRowIndex(originalIdx, i);
                int copyRowIdx = part.getCellStartRowIndex(partIdx, i);
                PdfPCell splitted = original.getRow(splittedRowIdx).getCells()[i];
                PdfPCell copy = part.getRow(copyRowIdx).getCells()[i];
                if(splitted != null)
                {
                    if(!$assertionsDisabled && copy == null)
                        throw new AssertionError();
                    cells[i] = new PdfPCell(copy);
                    int rowspanOnPreviousPage = (partIdx - copyRowIdx) + 1;
                    cells[i].setRowspan(copy.getRowspan() - rowspanOnPreviousPage);
                    splitted.setRowspan(rowspanOnPreviousPage);
                    calculated = false;
                }
                i++;
            } else
            {
                i += cells[i].getColspan();
            }

    }

    public PdfPCell[] getCells()
    {
        return cells;
    }

    public boolean hasRowspan()
    {
        for(int i = 0; i < cells.length; i++)
            if(cells[i] != null && cells[i].getRowspan() > 1)
                return true;

        return false;
    }

    public PdfObject getAccessibleAttribute(PdfName key)
    {
        if(accessibleAttributes != null)
            return (PdfObject)accessibleAttributes.get(key);
        else
            return null;
    }

    public void setAccessibleAttribute(PdfName key, PdfObject value)
    {
        if(accessibleAttributes == null)
            accessibleAttributes = new HashMap();
        accessibleAttributes.put(key, value);
    }

    public HashMap getAccessibleAttributes()
    {
        return accessibleAttributes;
    }

    public PdfName getRole()
    {
        return role;
    }

    public void setRole(PdfName role)
    {
        this.role = role;
    }

    public AccessibleElementId getId()
    {
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    private static boolean isTagged(PdfContentByte canvas)
    {
        return canvas != null && canvas.writer != null && canvas.writer.isTagged();
    }

    private final Logger LOGGER;
    public boolean mayNotBreak;
    public static final float BOTTOM_LIMIT = -1.073742E+009F;
    public static final float RIGHT_LIMIT = 20000F;
    protected PdfPCell cells[];
    protected float widths[];
    protected float extraHeights[];
    protected float maxHeight;
    protected boolean calculated;
    private int canvasesPos[];
    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;
    static final boolean $assertionsDisabled = !co/com/pdf/text/pdf/PdfPRow.desiredAssertionStatus();

}
