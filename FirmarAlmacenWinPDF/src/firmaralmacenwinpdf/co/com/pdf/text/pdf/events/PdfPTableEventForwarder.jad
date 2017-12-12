// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTableEventForwarder.java

package co.com.pdf.text.pdf.events;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PdfPTableEventForwarder
    implements PdfPTableEventAfterSplit
{

    public PdfPTableEventForwarder()
    {
        events = new ArrayList();
    }

    public void addTableEvent(PdfPTableEvent event)
    {
        events.add(event);
    }

    public void tableLayout(PdfPTable table, float widths[][], float heights[], int headerRows, int rowStart, PdfContentByte canvases[])
    {
        PdfPTableEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.tableLayout(table, widths, heights, headerRows, rowStart, canvases))
            event = (PdfPTableEvent)i$.next();

    }

    public void splitTable(PdfPTable table)
    {
        Iterator i$ = events.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfPTableEvent event = (PdfPTableEvent)i$.next();
            if(event instanceof PdfPTableEventSplit)
                ((PdfPTableEventSplit)event).splitTable(table);
        } while(true);
    }

    public void afterSplitTable(PdfPTable table, PdfPRow startRow, int startIdx)
    {
        Iterator i$ = events.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfPTableEvent event = (PdfPTableEvent)i$.next();
            if(event instanceof PdfPTableEventAfterSplit)
                ((PdfPTableEventAfterSplit)event).afterSplitTable(table, startRow, startIdx);
        } while(true);
    }

    protected ArrayList events;
}
