// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTable.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.api.Spaceable;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.events.PdfPTableEventForwarder;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPCell, PdfPRow, PdfPHeaderCell, PdfContentByte, 
//            PdfArtifact, PdfObject, PdfPTableHeader, PdfPTableBody, 
//            PdfPTableFooter, PdfPTableEvent, PdfName

public class PdfPTable
    implements LargeElement, Spaceable, IAccessibleElement
{
    public static class ColumnMeasurementState
    {

        public void beginCell(PdfPCell cell, float completedRowsHeight, float rowHeight)
        {
            rowspan = cell.getRowspan();
            colspan = cell.getColspan();
            height = completedRowsHeight + Math.max(cell.getMaxHeight(), rowHeight);
        }

        public void consumeRowspan(float completedRowsHeight, float rowHeight)
        {
            rowspan--;
        }

        public boolean cellEnds()
        {
            return rowspan == 1;
        }

        public float height;
        public int rowspan;
        public int colspan;

        public ColumnMeasurementState()
        {
            height = 0.0F;
            rowspan = 1;
            colspan = 1;
        }
    }

    public static class FittingRows
    {

        public void correctLastRowChosen(PdfPTable table, int k)
        {
            PdfPRow row = table.getRow(k);
            Float value = (Float)correctedHeightsForLastRow.get(Integer.valueOf(k));
            if(value != null)
                row.setFinalMaxHeights(value.floatValue());
        }

        public final int firstRow;
        public final int lastRow;
        public final float height;
        public final float completedRowsHeight;
        private final Map correctedHeightsForLastRow;

        public FittingRows(int firstRow, int lastRow, float height, float completedRowsHeight, Map correctedHeightsForLastRow)
        {
            this.firstRow = firstRow;
            this.lastRow = lastRow;
            this.height = height;
            this.completedRowsHeight = completedRowsHeight;
            this.correctedHeightsForLastRow = correctedHeightsForLastRow;
        }
    }


    protected PdfPTable()
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPTable);
        rows = new ArrayList();
        totalHeight = 0.0F;
        currentColIdx = 0;
        defaultCell = new PdfPCell((Phrase)null);
        totalWidth = 0.0F;
        widthPercentage = 80F;
        horizontalAlignment = 1;
        skipFirstHeader = false;
        skipLastFooter = false;
        isColspan = false;
        runDirection = 0;
        lockedWidth = false;
        splitRows = true;
        splitLate = true;
        complete = true;
        rowCompleted = true;
        loopCheck = true;
        role = PdfName.TABLE;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        header = null;
        body = null;
        footer = null;
    }

    public PdfPTable(float relativeWidths[])
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPTable);
        rows = new ArrayList();
        totalHeight = 0.0F;
        currentColIdx = 0;
        defaultCell = new PdfPCell((Phrase)null);
        totalWidth = 0.0F;
        widthPercentage = 80F;
        horizontalAlignment = 1;
        skipFirstHeader = false;
        skipLastFooter = false;
        isColspan = false;
        runDirection = 0;
        lockedWidth = false;
        splitRows = true;
        splitLate = true;
        complete = true;
        rowCompleted = true;
        loopCheck = true;
        role = PdfName.TABLE;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        header = null;
        body = null;
        footer = null;
        if(relativeWidths == null)
            throw new NullPointerException(MessageLocalization.getComposedMessage("the.widths.array.in.pdfptable.constructor.can.not.be.null", new Object[0]));
        if(relativeWidths.length == 0)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.widths.array.in.pdfptable.constructor.can.not.have.zero.length", new Object[0]));
        } else
        {
            this.relativeWidths = new float[relativeWidths.length];
            System.arraycopy(relativeWidths, 0, this.relativeWidths, 0, relativeWidths.length);
            absoluteWidths = new float[relativeWidths.length];
            calculateWidths();
            currentRow = new PdfPCell[absoluteWidths.length];
            keepTogether = false;
            return;
        }
    }

    public PdfPTable(int numColumns)
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPTable);
        rows = new ArrayList();
        totalHeight = 0.0F;
        currentColIdx = 0;
        defaultCell = new PdfPCell((Phrase)null);
        totalWidth = 0.0F;
        widthPercentage = 80F;
        horizontalAlignment = 1;
        skipFirstHeader = false;
        skipLastFooter = false;
        isColspan = false;
        runDirection = 0;
        lockedWidth = false;
        splitRows = true;
        splitLate = true;
        complete = true;
        rowCompleted = true;
        loopCheck = true;
        role = PdfName.TABLE;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        header = null;
        body = null;
        footer = null;
        if(numColumns <= 0)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.number.of.columns.in.pdfptable.constructor.must.be.greater.than.zero", new Object[0]));
        relativeWidths = new float[numColumns];
        for(int k = 0; k < numColumns; k++)
            relativeWidths[k] = 1.0F;

        absoluteWidths = new float[relativeWidths.length];
        calculateWidths();
        currentRow = new PdfPCell[absoluteWidths.length];
        keepTogether = false;
    }

    public PdfPTable(PdfPTable table)
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/PdfPTable);
        rows = new ArrayList();
        totalHeight = 0.0F;
        currentColIdx = 0;
        defaultCell = new PdfPCell((Phrase)null);
        totalWidth = 0.0F;
        widthPercentage = 80F;
        horizontalAlignment = 1;
        skipFirstHeader = false;
        skipLastFooter = false;
        isColspan = false;
        runDirection = 0;
        lockedWidth = false;
        splitRows = true;
        splitLate = true;
        complete = true;
        rowCompleted = true;
        loopCheck = true;
        role = PdfName.TABLE;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        header = null;
        body = null;
        footer = null;
        copyFormat(table);
        for(int k = 0; k < currentRow.length && table.currentRow[k] != null; k++)
            currentRow[k] = new PdfPCell(table.currentRow[k]);

        for(int k = 0; k < table.rows.size(); k++)
        {
            PdfPRow row = (PdfPRow)table.rows.get(k);
            if(row != null)
                row = new PdfPRow(row);
            rows.add(row);
        }

    }

    public static PdfPTable shallowCopy(PdfPTable table)
    {
        PdfPTable nt = new PdfPTable();
        nt.copyFormat(table);
        return nt;
    }

    protected void copyFormat(PdfPTable sourceTable)
    {
        relativeWidths = new float[sourceTable.getNumberOfColumns()];
        absoluteWidths = new float[sourceTable.getNumberOfColumns()];
        System.arraycopy(sourceTable.relativeWidths, 0, relativeWidths, 0, getNumberOfColumns());
        System.arraycopy(sourceTable.absoluteWidths, 0, absoluteWidths, 0, getNumberOfColumns());
        totalWidth = sourceTable.totalWidth;
        totalHeight = sourceTable.totalHeight;
        currentColIdx = 0;
        tableEvent = sourceTable.tableEvent;
        runDirection = sourceTable.runDirection;
        if(sourceTable.defaultCell instanceof PdfPHeaderCell)
            defaultCell = new PdfPHeaderCell((PdfPHeaderCell)sourceTable.defaultCell);
        else
            defaultCell = new PdfPCell(sourceTable.defaultCell);
        currentRow = new PdfPCell[sourceTable.currentRow.length];
        isColspan = sourceTable.isColspan;
        splitRows = sourceTable.splitRows;
        spacingAfter = sourceTable.spacingAfter;
        spacingBefore = sourceTable.spacingBefore;
        headerRows = sourceTable.headerRows;
        footerRows = sourceTable.footerRows;
        lockedWidth = sourceTable.lockedWidth;
        extendLastRow = sourceTable.extendLastRow;
        headersInEvent = sourceTable.headersInEvent;
        widthPercentage = sourceTable.widthPercentage;
        splitLate = sourceTable.splitLate;
        skipFirstHeader = sourceTable.skipFirstHeader;
        skipLastFooter = sourceTable.skipLastFooter;
        horizontalAlignment = sourceTable.horizontalAlignment;
        keepTogether = sourceTable.keepTogether;
        complete = sourceTable.complete;
        loopCheck = sourceTable.loopCheck;
        id = sourceTable.id;
        role = sourceTable.role;
        if(sourceTable.accessibleAttributes != null)
            accessibleAttributes = new HashMap(sourceTable.accessibleAttributes);
        header = sourceTable.getHeader();
        body = sourceTable.getBody();
        footer = sourceTable.getFooter();
    }

    public void setWidths(float relativeWidths[])
        throws DocumentException
    {
        if(relativeWidths.length != getNumberOfColumns())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        } else
        {
            this.relativeWidths = new float[relativeWidths.length];
            System.arraycopy(relativeWidths, 0, this.relativeWidths, 0, relativeWidths.length);
            absoluteWidths = new float[relativeWidths.length];
            totalHeight = 0.0F;
            calculateWidths();
            calculateHeights();
            return;
        }
    }

    public void setWidths(int relativeWidths[])
        throws DocumentException
    {
        float tb[] = new float[relativeWidths.length];
        for(int k = 0; k < relativeWidths.length; k++)
            tb[k] = relativeWidths[k];

        setWidths(tb);
    }

    protected void calculateWidths()
    {
        if(totalWidth <= 0.0F)
            return;
        float total = 0.0F;
        int numCols = getNumberOfColumns();
        for(int k = 0; k < numCols; k++)
            total += relativeWidths[k];

        for(int k = 0; k < numCols; k++)
            absoluteWidths[k] = (totalWidth * relativeWidths[k]) / total;

    }

    public void setTotalWidth(float totalWidth)
    {
        if(this.totalWidth == totalWidth)
        {
            return;
        } else
        {
            this.totalWidth = totalWidth;
            totalHeight = 0.0F;
            calculateWidths();
            calculateHeights();
            return;
        }
    }

    public void setTotalWidth(float columnWidth[])
        throws DocumentException
    {
        if(columnWidth.length != getNumberOfColumns())
            throw new DocumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        totalWidth = 0.0F;
        for(int k = 0; k < columnWidth.length; k++)
            totalWidth += columnWidth[k];

        setWidths(columnWidth);
    }

    public void setWidthPercentage(float columnWidth[], Rectangle pageSize)
        throws DocumentException
    {
        if(columnWidth.length != getNumberOfColumns())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        float totalWidth = 0.0F;
        for(int k = 0; k < columnWidth.length; k++)
            totalWidth += columnWidth[k];

        widthPercentage = (totalWidth / (pageSize.getRight() - pageSize.getLeft())) * 100F;
        setWidths(columnWidth);
    }

    public float getTotalWidth()
    {
        return totalWidth;
    }

    public float calculateHeights()
    {
        if(totalWidth <= 0.0F)
            return 0.0F;
        totalHeight = 0.0F;
        for(int k = 0; k < rows.size(); k++)
            totalHeight += getRowHeight(k, true);

        return totalHeight;
    }

    public void resetColumnCount(int newColCount)
    {
        if(newColCount <= 0)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.number.of.columns.in.pdfptable.constructor.must.be.greater.than.zero", new Object[0]));
        relativeWidths = new float[newColCount];
        for(int k = 0; k < newColCount; k++)
            relativeWidths[k] = 1.0F;

        absoluteWidths = new float[relativeWidths.length];
        calculateWidths();
        currentRow = new PdfPCell[absoluteWidths.length];
        totalHeight = 0.0F;
    }

    public PdfPCell getDefaultCell()
    {
        return defaultCell;
    }

    public PdfPCell addCell(PdfPCell cell)
    {
        rowCompleted = false;
        PdfPCell ncell;
        if(cell instanceof PdfPHeaderCell)
            ncell = new PdfPHeaderCell((PdfPHeaderCell)cell);
        else
            ncell = new PdfPCell(cell);
        int colspan = ncell.getColspan();
        colspan = Math.max(colspan, 1);
        colspan = Math.min(colspan, currentRow.length - currentColIdx);
        ncell.setColspan(colspan);
        if(colspan != 1)
            isColspan = true;
        int rdir = ncell.getRunDirection();
        if(rdir == 0)
            ncell.setRunDirection(runDirection);
        skipColsWithRowspanAbove();
        boolean cellAdded = false;
        if(currentColIdx < currentRow.length)
        {
            currentRow[currentColIdx] = ncell;
            currentColIdx += colspan;
            cellAdded = true;
        }
        skipColsWithRowspanAbove();
        while(currentColIdx >= currentRow.length) 
        {
            int numCols = getNumberOfColumns();
            if(runDirection == 3)
            {
                PdfPCell rtlRow[] = new PdfPCell[numCols];
                int rev = currentRow.length;
                for(int k = 0; k < currentRow.length; k++)
                {
                    PdfPCell rcell = currentRow[k];
                    int cspan = rcell.getColspan();
                    rev -= cspan;
                    rtlRow[rev] = rcell;
                    k += cspan - 1;
                }

                currentRow = rtlRow;
            }
            PdfPRow row = new PdfPRow(currentRow);
            if(totalWidth > 0.0F)
            {
                row.setWidths(absoluteWidths);
                totalHeight += row.getMaxHeights();
            }
            rows.add(row);
            currentRow = new PdfPCell[numCols];
            currentColIdx = 0;
            skipColsWithRowspanAbove();
            rowCompleted = true;
        }
        if(!cellAdded)
        {
            currentRow[currentColIdx] = ncell;
            currentColIdx += colspan;
        }
        return ncell;
    }

    private void skipColsWithRowspanAbove()
    {
        int direction = 1;
        if(runDirection == 3)
            direction = -1;
        for(; rowSpanAbove(rows.size(), currentColIdx); currentColIdx += direction);
    }

    PdfPCell cellAt(int row, int col)
    {
        PdfPCell cells[] = ((PdfPRow)rows.get(row)).getCells();
        for(int i = 0; i < cells.length; i++)
            if(cells[i] != null && col >= i && col < i + cells[i].getColspan())
                return cells[i];

        return null;
    }

    boolean rowSpanAbove(int currRow, int currCol)
    {
        if(currCol >= getNumberOfColumns() || currCol < 0 || currRow < 1)
            return false;
        int row = currRow - 1;
        PdfPRow aboveRow = (PdfPRow)rows.get(row);
        if(aboveRow == null)
            return false;
        PdfPCell aboveCell;
        for(aboveCell = cellAt(row, currCol); aboveCell == null && row > 0; aboveCell = cellAt(row, currCol))
        {
            aboveRow = (PdfPRow)rows.get(--row);
            if(aboveRow == null)
                return false;
        }

        int distance = currRow - row;
        if(aboveCell.getRowspan() == 1 && distance > 1)
        {
            int col = currCol - 1;
            aboveRow = (PdfPRow)rows.get(row + 1);
            distance--;
            for(aboveCell = aboveRow.getCells()[col]; aboveCell == null && col > 0; aboveCell = aboveRow.getCells()[--col]);
        }
        return aboveCell != null && aboveCell.getRowspan() > distance;
    }

    public void addCell(String text)
    {
        addCell(new Phrase(text));
    }

    public void addCell(PdfPTable table)
    {
        defaultCell.setTable(table);
        PdfPCell newCell = addCell(defaultCell);
        newCell.id = new AccessibleElementId();
        defaultCell.setTable(null);
    }

    public void addCell(Image image)
    {
        defaultCell.setImage(image);
        PdfPCell newCell = addCell(defaultCell);
        newCell.id = new AccessibleElementId();
        defaultCell.setImage(null);
    }

    public void addCell(Phrase phrase)
    {
        defaultCell.setPhrase(phrase);
        PdfPCell newCell = addCell(defaultCell);
        newCell.id = new AccessibleElementId();
        defaultCell.setPhrase(null);
    }

    public float writeSelectedRows(int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvases[])
    {
        return writeSelectedRows(0, -1, rowStart, rowEnd, xPos, yPos, canvases);
    }

    public float writeSelectedRows(int colStart, int colEnd, int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvases[])
    {
        return writeSelectedRows(colStart, colEnd, rowStart, rowEnd, xPos, yPos, canvases, true);
    }

    public float writeSelectedRows(int colStart, int colEnd, int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvases[], 
            boolean reusable)
    {
        if(totalWidth <= 0.0F)
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.table.width.must.be.greater.than.zero", new Object[0]));
        int totalRows = rows.size();
        if(rowStart < 0)
            rowStart = 0;
        if(rowEnd < 0)
            rowEnd = totalRows;
        else
            rowEnd = Math.min(rowEnd, totalRows);
        if(rowStart >= rowEnd)
            return yPos;
        int totalCols = getNumberOfColumns();
        if(colStart < 0)
            colStart = 0;
        else
            colStart = Math.min(colStart, totalCols);
        if(colEnd < 0)
            colEnd = totalCols;
        else
            colEnd = Math.min(colEnd, totalCols);
        LOGGER.info(String.format("Writing row %s to %s; column %s to %s", new Object[] {
            Integer.valueOf(rowStart), Integer.valueOf(rowEnd), Integer.valueOf(colStart), Integer.valueOf(colEnd)
        }));
        float yPosStart = yPos;
        PdfPTableBody currentBlock = null;
        for(int k = rowStart; k < rowEnd; k++)
        {
            PdfPRow row = (PdfPRow)rows.get(k);
            if(getHeader().rows != null && getHeader().rows.contains(row) && currentBlock == null)
                currentBlock = openTableBlock(getHeader(), canvases[3]);
            else
            if(getBody().rows != null && getBody().rows.contains(row) && currentBlock == null)
                currentBlock = openTableBlock(getBody(), canvases[3]);
            else
            if(getFooter().rows != null && getFooter().rows.contains(row) && currentBlock == null)
                currentBlock = openTableBlock(getFooter(), canvases[3]);
            if(row != null)
            {
                row.writeCells(colStart, colEnd, xPos, yPos, canvases, reusable);
                yPos -= row.getMaxHeights();
            }
            if(getHeader().rows != null && getHeader().rows.contains(row) && (k == rowEnd - 1 || !getHeader().rows.contains(rows.get(k + 1))))
            {
                currentBlock = closeTableBlock(getHeader(), canvases[3]);
                continue;
            }
            if(getBody().rows != null && getBody().rows.contains(row) && (k == rowEnd - 1 || !getBody().rows.contains(rows.get(k + 1))))
            {
                currentBlock = closeTableBlock(getBody(), canvases[3]);
                continue;
            }
            if(getFooter().rows != null && getFooter().rows.contains(row) && (k == rowEnd - 1 || !getFooter().rows.contains(rows.get(k + 1))))
                currentBlock = closeTableBlock(getFooter(), canvases[3]);
        }

        if(tableEvent != null && colStart == 0 && colEnd == totalCols)
        {
            float heights[] = new float[(rowEnd - rowStart) + 1];
            heights[0] = yPosStart;
            for(int k = rowStart; k < rowEnd; k++)
            {
                PdfPRow row = (PdfPRow)rows.get(k);
                float hr = 0.0F;
                if(row != null)
                    hr = row.getMaxHeights();
                heights[(k - rowStart) + 1] = heights[k - rowStart] - hr;
            }

            tableEvent.tableLayout(this, getEventWidths(xPos, rowStart, rowEnd, headersInEvent), heights, headersInEvent ? headerRows : 0, rowStart, canvases);
        }
        return yPos;
    }

    private PdfPTableBody openTableBlock(PdfPTableBody block, PdfContentByte canvas)
    {
        canvas.openMCBlock(block);
        return block;
    }

    private PdfPTableBody closeTableBlock(PdfPTableBody block, PdfContentByte canvas)
    {
        canvas.closeMCBlock(block);
        return null;
    }

    public float writeSelectedRows(int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvas)
    {
        return writeSelectedRows(0, -1, rowStart, rowEnd, xPos, yPos, canvas);
    }

    public float writeSelectedRows(int colStart, int colEnd, int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvas)
    {
        return writeSelectedRows(colStart, colEnd, rowStart, rowEnd, xPos, yPos, canvas, true);
    }

    public float writeSelectedRows(int colStart, int colEnd, int rowStart, int rowEnd, float xPos, float yPos, PdfContentByte canvas, 
            boolean reusable)
    {
        int totalCols = getNumberOfColumns();
        if(colStart < 0)
            colStart = 0;
        else
            colStart = Math.min(colStart, totalCols);
        if(colEnd < 0)
            colEnd = totalCols;
        else
            colEnd = Math.min(colEnd, totalCols);
        boolean clip = colStart != 0 || colEnd != totalCols;
        if(clip)
        {
            float w = 0.0F;
            for(int k = colStart; k < colEnd; k++)
                w += absoluteWidths[k];

            canvas.saveState();
            float lx = colStart != 0 ? 0.0F : 10000F;
            float rx = colEnd != totalCols ? 0.0F : 10000F;
            canvas.rectangle(xPos - lx, -10000F, w + lx + rx, 20000F);
            canvas.clip();
            canvas.newPath();
        }
        PdfContentByte canvases[] = beginWritingRows(canvas);
        float y = writeSelectedRows(colStart, colEnd, rowStart, rowEnd, xPos, yPos, canvases, reusable);
        endWritingRows(canvases);
        if(clip)
            canvas.restoreState();
        return y;
    }

    public static PdfContentByte[] beginWritingRows(PdfContentByte canvas)
    {
        return (new PdfContentByte[] {
            canvas, canvas.getDuplicate(), canvas.getDuplicate(), canvas.getDuplicate()
        });
    }

    public static void endWritingRows(PdfContentByte canvases[])
    {
        PdfContentByte canvas = canvases[0];
        PdfArtifact artifact = new PdfArtifact();
        canvas.openMCBlock(artifact);
        canvas.saveState();
        canvas.add(canvases[1]);
        canvas.restoreState();
        canvas.saveState();
        canvas.setLineCap(2);
        canvas.resetRGBColorStroke();
        canvas.add(canvases[2]);
        canvas.restoreState();
        canvas.closeMCBlock(artifact);
        canvas.add(canvases[3]);
    }

    public int size()
    {
        return rows.size();
    }

    public float getTotalHeight()
    {
        return totalHeight;
    }

    public float getRowHeight(int idx)
    {
        return getRowHeight(idx, false);
    }

    protected float getRowHeight(int idx, boolean firsttime)
    {
        if(totalWidth <= 0.0F || idx < 0 || idx >= rows.size())
            return 0.0F;
        PdfPRow row = (PdfPRow)rows.get(idx);
        if(row == null)
            return 0.0F;
        if(firsttime)
            row.setWidths(absoluteWidths);
        float height = row.getMaxHeights();
        for(int i = 0; i < relativeWidths.length; i++)
        {
            if(!rowSpanAbove(idx, i))
                continue;
            int rs;
            for(rs = 1; rowSpanAbove(idx - rs, i); rs++);
            PdfPRow tmprow = (PdfPRow)rows.get(idx - rs);
            PdfPCell cell = tmprow.getCells()[i];
            float tmp = 0.0F;
            if(cell != null && cell.getRowspan() == rs + 1)
            {
                tmp = cell.getMaxHeight();
                for(; rs > 0; rs--)
                    tmp -= getRowHeight(idx - rs);

            }
            if(tmp > height)
                height = tmp;
        }

        row.setMaxHeights(height);
        return height;
    }

    public float getRowspanHeight(int rowIndex, int cellIndex)
    {
        if(totalWidth <= 0.0F || rowIndex < 0 || rowIndex >= rows.size())
            return 0.0F;
        PdfPRow row = (PdfPRow)rows.get(rowIndex);
        if(row == null || cellIndex >= row.getCells().length)
            return 0.0F;
        PdfPCell cell = row.getCells()[cellIndex];
        if(cell == null)
            return 0.0F;
        float rowspanHeight = 0.0F;
        for(int j = 0; j < cell.getRowspan(); j++)
            rowspanHeight += getRowHeight(rowIndex + j);

        return rowspanHeight;
    }

    public boolean hasRowspan(int rowIdx)
    {
        if(rowIdx < rows.size() && getRow(rowIdx).hasRowspan())
            return true;
        PdfPRow previousRow = rowIdx <= 0 ? null : getRow(rowIdx - 1);
        if(previousRow != null && previousRow.hasRowspan())
            return true;
        for(int i = 0; i < getNumberOfColumns(); i++)
            if(rowSpanAbove(rowIdx - 1, i))
                return true;

        return false;
    }

    public void normalizeHeadersFooters()
    {
        if(footerRows > headerRows)
            footerRows = headerRows;
    }

    public float getHeaderHeight()
    {
        float total = 0.0F;
        int size = Math.min(rows.size(), headerRows);
        for(int k = 0; k < size; k++)
        {
            PdfPRow row = (PdfPRow)rows.get(k);
            if(row != null)
                total += row.getMaxHeights();
        }

        return total;
    }

    public float getFooterHeight()
    {
        float total = 0.0F;
        int start = Math.max(0, headerRows - footerRows);
        int size = Math.min(rows.size(), headerRows);
        for(int k = start; k < size; k++)
        {
            PdfPRow row = (PdfPRow)rows.get(k);
            if(row != null)
                total += row.getMaxHeights();
        }

        return total;
    }

    public boolean deleteRow(int rowNumber)
    {
        if(rowNumber < 0 || rowNumber >= rows.size())
            return false;
        if(totalWidth > 0.0F)
        {
            PdfPRow row = (PdfPRow)rows.get(rowNumber);
            if(row != null)
                totalHeight -= row.getMaxHeights();
        }
        rows.remove(rowNumber);
        if(rowNumber < headerRows)
        {
            headerRows--;
            if(rowNumber >= headerRows - footerRows)
                footerRows--;
        }
        return true;
    }

    public boolean deleteLastRow()
    {
        return deleteRow(rows.size() - 1);
    }

    public void deleteBodyRows()
    {
        ArrayList rows2 = new ArrayList();
        for(int k = 0; k < headerRows; k++)
            rows2.add(rows.get(k));

        rows = rows2;
        totalHeight = 0.0F;
        if(totalWidth > 0.0F)
            totalHeight = getHeaderHeight();
    }

    public int getNumberOfColumns()
    {
        return relativeWidths.length;
    }

    public int getHeaderRows()
    {
        return headerRows;
    }

    public void setHeaderRows(int headerRows)
    {
        if(headerRows < 0)
            headerRows = 0;
        this.headerRows = headerRows;
    }

    public List getChunks()
    {
        return new ArrayList();
    }

    public int type()
    {
        return 23;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return true;
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(this);
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public float getWidthPercentage()
    {
        return widthPercentage;
    }

    public void setWidthPercentage(float widthPercentage)
    {
        this.widthPercentage = widthPercentage;
    }

    public int getHorizontalAlignment()
    {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(int horizontalAlignment)
    {
        this.horizontalAlignment = horizontalAlignment;
    }

    public PdfPRow getRow(int idx)
    {
        return (PdfPRow)rows.get(idx);
    }

    public ArrayList getRows()
    {
        return rows;
    }

    public int getLastCompletedRowIndex()
    {
        return rows.size() - 1;
    }

    public transient void setBreakPoints(int breakPoints[])
    {
        for(int i = 0; i < rows.size(); i++)
            getRow(i).setMayNotBreak(true);

        for(int i = 0; i < breakPoints.length; i++)
            getRow(breakPoints[i]).setMayNotBreak(false);

    }

    public void keepRowsTogether(int rows[])
    {
        for(int i = 0; i < rows.length; i++)
            getRow(rows[i]).setMayNotBreak(true);

    }

    public void keepRowsTogether(int start, int end)
    {
        if(start < end)
            for(; start < end; start++)
                getRow(start).setMayNotBreak(true);

    }

    public void keepRowsTogether(int start)
    {
        if(start < rows.size())
        {
            for(int i = start; i < rows.size(); i++)
                getRow(i).setMayNotBreak(true);

        }
    }

    public ArrayList getRows(int start, int end)
    {
        ArrayList list = new ArrayList();
        if(start < 0 || end > size())
            return list;
        for(int i = start; i < end; i++)
            list.add(adjustCellsInRow(i, end));

        return list;
    }

    protected PdfPRow adjustCellsInRow(int start, int end)
    {
        PdfPRow row = new PdfPRow(getRow(start));
        PdfPCell cells[] = row.getCells();
        for(int i = 0; i < cells.length; i++)
        {
            PdfPCell cell = cells[i];
            if(cell == null || cell.getRowspan() == 1)
                continue;
            int stop = Math.min(end, start + cell.getRowspan());
            float extra = 0.0F;
            for(int k = start + 1; k < stop; k++)
                extra += getRow(k).getMaxHeights();

            row.setExtraHeight(i, extra);
        }

        return row;
    }

    public void setTableEvent(PdfPTableEvent event)
    {
        if(event == null)
            tableEvent = null;
        else
        if(tableEvent == null)
            tableEvent = event;
        else
        if(tableEvent instanceof PdfPTableEventForwarder)
        {
            ((PdfPTableEventForwarder)tableEvent).addTableEvent(event);
        } else
        {
            PdfPTableEventForwarder forward = new PdfPTableEventForwarder();
            forward.addTableEvent(tableEvent);
            forward.addTableEvent(event);
            tableEvent = forward;
        }
    }

    public PdfPTableEvent getTableEvent()
    {
        return tableEvent;
    }

    public float[] getAbsoluteWidths()
    {
        return absoluteWidths;
    }

    float[][] getEventWidths(float xPos, int firstRow, int lastRow, boolean includeHeaders)
    {
        if(includeHeaders)
        {
            firstRow = Math.max(firstRow, headerRows);
            lastRow = Math.max(lastRow, headerRows);
        }
        float widths[][] = new float[((includeHeaders ? headerRows : 0) + lastRow) - firstRow][];
        if(isColspan)
        {
            int n = 0;
            if(includeHeaders)
            {
                for(int k = 0; k < headerRows; k++)
                {
                    PdfPRow row = (PdfPRow)rows.get(k);
                    if(row == null)
                        n++;
                    else
                        widths[n++] = row.getEventWidth(xPos, absoluteWidths);
                }

            }
            for(; firstRow < lastRow; firstRow++)
            {
                PdfPRow row = (PdfPRow)rows.get(firstRow);
                if(row == null)
                    n++;
                else
                    widths[n++] = row.getEventWidth(xPos, absoluteWidths);
            }

        } else
        {
            int numCols = getNumberOfColumns();
            float width[] = new float[numCols + 1];
            width[0] = xPos;
            for(int k = 0; k < numCols; k++)
                width[k + 1] = width[k] + absoluteWidths[k];

            for(int k = 0; k < widths.length; k++)
                widths[k] = width;

        }
        return widths;
    }

    public boolean isSkipFirstHeader()
    {
        return skipFirstHeader;
    }

    public boolean isSkipLastFooter()
    {
        return skipLastFooter;
    }

    public void setSkipFirstHeader(boolean skipFirstHeader)
    {
        this.skipFirstHeader = skipFirstHeader;
    }

    public void setSkipLastFooter(boolean skipLastFooter)
    {
        this.skipLastFooter = skipLastFooter;
    }

    public void setRunDirection(int runDirection)
    {
        switch(runDirection)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            this.runDirection = runDirection;
            break;

        default:
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", runDirection));
        }
    }

    public int getRunDirection()
    {
        return runDirection;
    }

    public boolean isLockedWidth()
    {
        return lockedWidth;
    }

    public void setLockedWidth(boolean lockedWidth)
    {
        this.lockedWidth = lockedWidth;
    }

    public boolean isSplitRows()
    {
        return splitRows;
    }

    public void setSplitRows(boolean splitRows)
    {
        this.splitRows = splitRows;
    }

    public void setSpacingBefore(float spacing)
    {
        spacingBefore = spacing;
    }

    public void setSpacingAfter(float spacing)
    {
        spacingAfter = spacing;
    }

    public float spacingBefore()
    {
        return spacingBefore;
    }

    public float spacingAfter()
    {
        return spacingAfter;
    }

    public boolean isExtendLastRow()
    {
        return extendLastRow[0];
    }

    public void setExtendLastRow(boolean extendLastRows)
    {
        extendLastRow[0] = extendLastRows;
        extendLastRow[1] = extendLastRows;
    }

    public void setExtendLastRow(boolean extendLastRows, boolean extendFinalRow)
    {
        extendLastRow[0] = extendLastRows;
        extendLastRow[1] = extendFinalRow;
    }

    public boolean isExtendLastRow(boolean newPageFollows)
    {
        if(newPageFollows)
            return extendLastRow[0];
        else
            return extendLastRow[1];
    }

    public boolean isHeadersInEvent()
    {
        return headersInEvent;
    }

    public void setHeadersInEvent(boolean headersInEvent)
    {
        this.headersInEvent = headersInEvent;
    }

    public boolean isSplitLate()
    {
        return splitLate;
    }

    public void setSplitLate(boolean splitLate)
    {
        this.splitLate = splitLate;
    }

    public void setKeepTogether(boolean keepTogether)
    {
        this.keepTogether = keepTogether;
    }

    public boolean getKeepTogether()
    {
        return keepTogether;
    }

    public int getFooterRows()
    {
        return footerRows;
    }

    public void setFooterRows(int footerRows)
    {
        if(footerRows < 0)
            footerRows = 0;
        this.footerRows = footerRows;
    }

    public void completeRow()
    {
        while(!rowCompleted) 
            addCell(defaultCell);
    }

    public void flushContent()
    {
        deleteBodyRows();
        setSkipFirstHeader(true);
    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public float getSpacingBefore()
    {
        return spacingBefore;
    }

    public float getSpacingAfter()
    {
        return spacingAfter;
    }

    public boolean isLoopCheck()
    {
        return loopCheck;
    }

    public void setLoopCheck(boolean loopCheck)
    {
        this.loopCheck = loopCheck;
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

    public PdfPTableHeader getHeader()
    {
        if(header == null)
            header = new PdfPTableHeader();
        return header;
    }

    public PdfPTableBody getBody()
    {
        if(body == null)
            body = new PdfPTableBody();
        return body;
    }

    public PdfPTableFooter getFooter()
    {
        if(footer == null)
            footer = new PdfPTableFooter();
        return footer;
    }

    public int getCellStartRowIndex(int rowIdx, int colIdx)
    {
        int lastRow;
        for(lastRow = rowIdx; getRow(lastRow).getCells()[colIdx] == null && lastRow > 0; lastRow--);
        return lastRow;
    }

    public FittingRows getFittingRows(float availableHeight, int startIdx)
    {
        if(!$assertionsDisabled && getRow(startIdx).getCells()[0] == null)
            throw new AssertionError();
        int cols = getNumberOfColumns();
        ColumnMeasurementState states[] = new ColumnMeasurementState[cols];
        for(int i = 0; i < cols; i++)
            states[i] = new ColumnMeasurementState();

        float completedRowsHeight = 0.0F;
        float totalHeight = 0.0F;
        Map correctedHeightsForLastRow = new HashMap();
        int k = startIdx;
        do
        {
            if(k >= size())
                break;
            PdfPRow row = getRow(k);
            float rowHeight = row.getMaxRowHeightsWithoutCalculating();
            float maxCompletedRowsHeight = 0.0F;
            ColumnMeasurementState state;
            for(int i = 0; i < cols; i += state.colspan)
            {
                PdfPCell cell = row.getCells()[i];
                state = states[i];
                if(cell == null)
                    state.consumeRowspan(completedRowsHeight, rowHeight);
                else
                    state.beginCell(cell, completedRowsHeight, rowHeight);
                if(state.cellEnds() && state.height > maxCompletedRowsHeight)
                    maxCompletedRowsHeight = state.height;
                for(int j = 1; j < state.colspan; j++)
                    states[i + j].height = state.height;

            }

            float maxTotalHeight = 0.0F;
            ColumnMeasurementState arr$[] = states;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                ColumnMeasurementState state = arr$[i$];
                if(state.height > maxTotalHeight)
                    maxTotalHeight = state.height;
            }

            row.setFinalMaxHeights(maxCompletedRowsHeight - completedRowsHeight);
            float remainingHeight = availableHeight - (isSplitLate() ? maxTotalHeight : maxCompletedRowsHeight);
            if(remainingHeight < 0.0F)
                break;
            correctedHeightsForLastRow.put(Integer.valueOf(k), Float.valueOf(maxTotalHeight - completedRowsHeight));
            completedRowsHeight = maxCompletedRowsHeight;
            totalHeight = maxTotalHeight;
            k++;
        } while(true);
        return new FittingRows(startIdx, k - 1, totalHeight, completedRowsHeight, correctedHeightsForLastRow);
    }

    private final Logger LOGGER;
    public static final int BASECANVAS = 0;
    public static final int BACKGROUNDCANVAS = 1;
    public static final int LINECANVAS = 2;
    public static final int TEXTCANVAS = 3;
    protected ArrayList rows;
    protected float totalHeight;
    protected PdfPCell currentRow[];
    protected int currentColIdx;
    protected PdfPCell defaultCell;
    protected float totalWidth;
    protected float relativeWidths[];
    protected float absoluteWidths[];
    protected PdfPTableEvent tableEvent;
    protected int headerRows;
    protected float widthPercentage;
    private int horizontalAlignment;
    private boolean skipFirstHeader;
    private boolean skipLastFooter;
    protected boolean isColspan;
    protected int runDirection;
    private boolean lockedWidth;
    private boolean splitRows;
    protected float spacingBefore;
    protected float spacingAfter;
    private boolean extendLastRow[] = {
        false, false
    };
    private boolean headersInEvent;
    private boolean splitLate;
    private boolean keepTogether;
    protected boolean complete;
    private int footerRows;
    protected boolean rowCompleted;
    protected boolean loopCheck;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;
    private PdfPTableHeader header;
    private PdfPTableBody body;
    private PdfPTableFooter footer;
    static final boolean $assertionsDisabled = !co/com/pdf/text/pdf/PdfPTable.desiredAssertionStatus();

}
