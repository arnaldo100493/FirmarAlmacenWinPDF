// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTable.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPCell, PdfPTable

public static class PdfPTable$ColumnMeasurementState
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

    public PdfPTable$ColumnMeasurementState()
    {
        height = 0.0F;
        rowspan = 1;
        colspan = 1;
    }
}
