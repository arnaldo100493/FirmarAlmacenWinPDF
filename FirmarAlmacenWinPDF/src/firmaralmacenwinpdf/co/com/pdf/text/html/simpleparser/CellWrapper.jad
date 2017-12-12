// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CellWrapper.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.*;
import co.com.pdf.text.html.HtmlUtilities;
import co.com.pdf.text.pdf.PdfPCell;
import java.util.List;

// Referenced classes of package co.com.pdf.text.html.simpleparser:
//            ChainedProperties

public class CellWrapper
    implements TextElementArray
{

    public CellWrapper(String tag, ChainedProperties chain)
    {
        cell = createPdfPCell(tag, chain);
        String value = chain.getProperty("width");
        if(value != null)
        {
            value = value.trim();
            if(value.endsWith("%"))
            {
                percentage = true;
                value = value.substring(0, value.length() - 1);
            }
            width = Float.parseFloat(value);
        }
    }

    public PdfPCell createPdfPCell(String tag, ChainedProperties chain)
    {
        PdfPCell cell = new PdfPCell((Phrase)null);
        String value = chain.getProperty("colspan");
        if(value != null)
            cell.setColspan(Integer.parseInt(value));
        value = chain.getProperty("rowspan");
        if(value != null)
            cell.setRowspan(Integer.parseInt(value));
        if(tag.equals("th"))
            cell.setHorizontalAlignment(1);
        value = chain.getProperty("align");
        if(value != null)
            cell.setHorizontalAlignment(HtmlUtilities.alignmentValue(value));
        value = chain.getProperty("valign");
        cell.setVerticalAlignment(5);
        if(value != null)
            cell.setVerticalAlignment(HtmlUtilities.alignmentValue(value));
        value = chain.getProperty("border");
        float border = 0.0F;
        if(value != null)
            border = Float.parseFloat(value);
        cell.setBorderWidth(border);
        value = chain.getProperty("cellpadding");
        if(value != null)
            cell.setPadding(Float.parseFloat(value));
        cell.setUseDescender(true);
        value = chain.getProperty("bgcolor");
        cell.setBackgroundColor(HtmlUtilities.decodeColor(value));
        return cell;
    }

    public PdfPCell getCell()
    {
        return cell;
    }

    public float getWidth()
    {
        return width;
    }

    public boolean isPercentage()
    {
        return percentage;
    }

    public boolean add(Element o)
    {
        cell.addElement(o);
        return true;
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

    private final PdfPCell cell;
    private float width;
    private boolean percentage;
}
