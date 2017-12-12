// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTable.java

package co.com.pdf.text.pdf;

import java.util.Map;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPRow, PdfPTable

public static class PdfPTable$FittingRows
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

    public PdfPTable$FittingRows(int firstRow, int lastRow, float height, float completedRowsHeight, Map correctedHeightsForLastRow)
    {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.height = height;
        this.completedRowsHeight = completedRowsHeight;
        this.correctedHeightsForLastRow = correctedHeightsForLastRow;
    }
}
