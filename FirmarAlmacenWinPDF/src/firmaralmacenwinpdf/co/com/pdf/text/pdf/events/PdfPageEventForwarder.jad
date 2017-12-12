// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPageEventForwarder.java

package co.com.pdf.text.pdf.events;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.PdfPageEvent;
import co.com.pdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class PdfPageEventForwarder
    implements PdfPageEvent
{

    public PdfPageEventForwarder()
    {
        events = new ArrayList();
    }

    public void addPageEvent(PdfPageEvent event)
    {
        events.add(event);
    }

    public void onOpenDocument(PdfWriter writer, Document document)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onOpenDocument(writer, document))
            event = (PdfPageEvent)i$.next();

    }

    public void onStartPage(PdfWriter writer, Document document)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onStartPage(writer, document))
            event = (PdfPageEvent)i$.next();

    }

    public void onEndPage(PdfWriter writer, Document document)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onEndPage(writer, document))
            event = (PdfPageEvent)i$.next();

    }

    public void onCloseDocument(PdfWriter writer, Document document)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onCloseDocument(writer, document))
            event = (PdfPageEvent)i$.next();

    }

    public void onParagraph(PdfWriter writer, Document document, float paragraphPosition)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onParagraph(writer, document, paragraphPosition))
            event = (PdfPageEvent)i$.next();

    }

    public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onParagraphEnd(writer, document, paragraphPosition))
            event = (PdfPageEvent)i$.next();

    }

    public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onChapter(writer, document, paragraphPosition, title))
            event = (PdfPageEvent)i$.next();

    }

    public void onChapterEnd(PdfWriter writer, Document document, float position)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onChapterEnd(writer, document, position))
            event = (PdfPageEvent)i$.next();

    }

    public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onSection(writer, document, paragraphPosition, depth, title))
            event = (PdfPageEvent)i$.next();

    }

    public void onSectionEnd(PdfWriter writer, Document document, float position)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onSectionEnd(writer, document, position))
            event = (PdfPageEvent)i$.next();

    }

    public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text)
    {
        PdfPageEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); event.onGenericTag(writer, document, rect, text))
        {
            Object element = i$.next();
            event = (PdfPageEvent)element;
        }

    }

    protected ArrayList events;
}
