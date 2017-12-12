// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfOutline.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfString, PdfArray, PdfNumber, 
//            PdfAction, PdfDestination, PdfName, PdfWriter, 
//            PdfIndirectReference

public class PdfOutline extends PdfDictionary
{

    PdfOutline(PdfWriter writer)
    {
        super(OUTLINES);
        count = 0;
        kids = new ArrayList();
        style = 0;
        open = true;
        parent = null;
        this.writer = writer;
    }

    public PdfOutline(PdfOutline parent, PdfAction action, String title)
    {
        this(parent, action, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfAction action, String title, boolean open)
    {
        count = 0;
        kids = new ArrayList();
        style = 0;
        this.action = action;
        initOutline(parent, title, open);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, String title)
    {
        this(parent, destination, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, String title, boolean open)
    {
        count = 0;
        kids = new ArrayList();
        style = 0;
        this.destination = destination;
        initOutline(parent, title, open);
    }

    public PdfOutline(PdfOutline parent, PdfAction action, PdfString title)
    {
        this(parent, action, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfAction action, PdfString title, boolean open)
    {
        this(parent, action, title.toString(), open);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, PdfString title)
    {
        this(parent, destination, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, PdfString title, boolean open)
    {
        this(parent, destination, title.toString(), true);
    }

    public PdfOutline(PdfOutline parent, PdfAction action, Paragraph title)
    {
        this(parent, action, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfAction action, Paragraph title, boolean open)
    {
        count = 0;
        kids = new ArrayList();
        style = 0;
        StringBuffer buf = new StringBuffer();
        Chunk chunk;
        for(Iterator i$ = title.getChunks().iterator(); i$.hasNext(); buf.append(chunk.getContent()))
            chunk = (Chunk)i$.next();

        this.action = action;
        initOutline(parent, buf.toString(), open);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, Paragraph title)
    {
        this(parent, destination, title, true);
    }

    public PdfOutline(PdfOutline parent, PdfDestination destination, Paragraph title, boolean open)
    {
        count = 0;
        kids = new ArrayList();
        style = 0;
        StringBuffer buf = new StringBuffer();
        Chunk chunk;
        for(Iterator i$ = title.getChunks().iterator(); i$.hasNext(); buf.append(chunk.getContent()))
        {
            Object element = i$.next();
            chunk = (Chunk)element;
        }

        this.destination = destination;
        initOutline(parent, buf.toString(), open);
    }

    void initOutline(PdfOutline parent, String title, boolean open)
    {
        this.open = open;
        this.parent = parent;
        writer = parent.writer;
        put(PdfName.TITLE, new PdfString(title, "UnicodeBig"));
        parent.addKid(this);
        if(destination != null && !destination.hasPage())
            setDestinationPage(writer.getCurrentPage());
    }

    public void setIndirectReference(PdfIndirectReference reference)
    {
        this.reference = reference;
    }

    public PdfIndirectReference indirectReference()
    {
        return reference;
    }

    public PdfOutline parent()
    {
        return parent;
    }

    public boolean setDestinationPage(PdfIndirectReference pageReference)
    {
        if(destination == null)
            return false;
        else
            return destination.addPage(pageReference);
    }

    public PdfDestination getPdfDestination()
    {
        return destination;
    }

    int getCount()
    {
        return count;
    }

    void setCount(int count)
    {
        this.count = count;
    }

    public int level()
    {
        if(parent == null)
            return 0;
        else
            return parent.level() + 1;
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        if(color != null && !color.equals(BaseColor.BLACK))
            put(PdfName.C, new PdfArray(new float[] {
                (float)color.getRed() / 255F, (float)color.getGreen() / 255F, (float)color.getBlue() / 255F
            }));
        int flag = 0;
        if((style & 1) != 0)
            flag |= 2;
        if((style & 2) != 0)
            flag |= 1;
        if(flag != 0)
            put(PdfName.F, new PdfNumber(flag));
        if(parent != null)
            put(PdfName.PARENT, parent.indirectReference());
        if(destination != null && destination.hasPage())
            put(PdfName.DEST, destination);
        if(action != null)
            put(PdfName.A, action);
        if(count != 0)
            put(PdfName.COUNT, new PdfNumber(count));
        super.toPdf(writer, os);
    }

    public void addKid(PdfOutline outline)
    {
        kids.add(outline);
    }

    public ArrayList getKids()
    {
        return kids;
    }

    public void setKids(ArrayList kids)
    {
        this.kids = kids;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getTitle()
    {
        PdfString title = (PdfString)get(PdfName.TITLE);
        return title.toString();
    }

    public void setTitle(String title)
    {
        put(PdfName.TITLE, new PdfString(title, "UnicodeBig"));
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public BaseColor getColor()
    {
        return color;
    }

    public void setColor(BaseColor color)
    {
        this.color = color;
    }

    public int getStyle()
    {
        return style;
    }

    public void setStyle(int style)
    {
        this.style = style;
    }

    private PdfIndirectReference reference;
    private int count;
    private PdfOutline parent;
    private PdfDestination destination;
    private PdfAction action;
    protected ArrayList kids;
    protected PdfWriter writer;
    private String tag;
    private boolean open;
    private BaseColor color;
    private int style;
}
