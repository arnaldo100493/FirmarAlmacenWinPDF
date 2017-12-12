// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPCellEventForwarder.java

package co.com.pdf.text.pdf.events;

import co.com.pdf.text.Rectangle;
import co.com.pdf.text.pdf.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PdfPCellEventForwarder
    implements PdfPCellEvent
{

    public PdfPCellEventForwarder()
    {
        events = new ArrayList();
    }

    public void addCellEvent(PdfPCellEvent event)
    {
        events.add(event);
    }

    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte canvases[])
    {
        PdfPCellEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.cellLayout(cell, position, canvases))
            event = (PdfPCellEvent)i$.next();

    }

    protected ArrayList events;
}
