// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableWrapper.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.Element;
import co.com.pdf.text.ElementListener;
import co.com.pdf.text.html.HtmlUtilities;
import co.com.pdf.text.pdf.PdfPCell;
import co.com.pdf.text.pdf.PdfPTable;
import java.util.*;

public class TableWrapper
    implements Element
{

    public TableWrapper(Map attrs)
    {
        styles.putAll(attrs);
    }

    public void addRow(List row)
    {
        if(row != null)
        {
            Collections.reverse(row);
            rows.add(row);
            row = null;
        }
    }

    public void setColWidths(float colWidths[])
    {
        this.colWidths = colWidths;
    }

    public PdfPTable createTable()
    {
        if(rows.isEmpty())
            return new PdfPTable(1);
        int ncol = 0;
        for(Iterator i$ = ((List)rows.get(0)).iterator(); i$.hasNext();)
        {
            PdfPCell pc = (PdfPCell)i$.next();
            ncol += pc.getColspan();
        }

        PdfPTable table = new PdfPTable(ncol);
        String width = (String)styles.get("width");
        if(width == null)
            table.setWidthPercentage(100F);
        else
        if(width.endsWith("%"))
        {
            table.setWidthPercentage(Float.parseFloat(width.substring(0, width.length() - 1)));
        } else
        {
            table.setTotalWidth(Float.parseFloat(width));
            table.setLockedWidth(true);
        }
        String alignment = (String)styles.get("align");
        int align = 0;
        if(alignment != null)
            align = HtmlUtilities.alignmentValue(alignment);
        table.setHorizontalAlignment(align);
        try
        {
            if(colWidths != null)
                table.setWidths(colWidths);
        }
        catch(Exception e) { }
        for(Iterator i$ = rows.iterator(); i$.hasNext();)
        {
            List col = (List)i$.next();
            Iterator i$ = col.iterator();
            while(i$.hasNext()) 
            {
                PdfPCell pc = (PdfPCell)i$.next();
                table.addCell(pc);
            }
        }

        return table;
    }

    public List getChunks()
    {
        return null;
    }

    public boolean isContent()
    {
        return false;
    }

    public boolean isNestable()
    {
        return false;
    }

    public boolean process(ElementListener listener)
    {
        return false;
    }

    public int type()
    {
        return 0;
    }

    private final Map styles = new HashMap();
    private final List rows = new ArrayList();
    private float colWidths[];
}
