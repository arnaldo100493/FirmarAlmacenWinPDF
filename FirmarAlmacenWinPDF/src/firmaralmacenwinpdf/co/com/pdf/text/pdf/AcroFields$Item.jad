// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AcroFields.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfIndirectReference, PdfName, PdfObject, 
//            AcroFields

public static class AcroFields$Item
{

    public void writeToAll(PdfName key, PdfObject value, int writeFlags)
    {
        PdfDictionary curDict = null;
        if((writeFlags & 1) != 0)
        {
            for(int i = 0; i < merged.size(); i++)
            {
                curDict = getMerged(i);
                curDict.put(key, value);
            }

        }
        if((writeFlags & 2) != 0)
        {
            for(int i = 0; i < widgets.size(); i++)
            {
                curDict = getWidget(i);
                curDict.put(key, value);
            }

        }
        if((writeFlags & 4) != 0)
        {
            for(int i = 0; i < values.size(); i++)
            {
                curDict = getValue(i);
                curDict.put(key, value);
            }

        }
    }

    public void markUsed(AcroFields parentFields, int writeFlags)
    {
        if((writeFlags & 4) != 0)
        {
            for(int i = 0; i < size(); i++)
                AcroFields.access$000(parentFields, getValue(i));

        }
        if((writeFlags & 2) != 0)
        {
            for(int i = 0; i < size(); i++)
                AcroFields.access$000(parentFields, getWidget(i));

        }
    }

    public int size()
    {
        return values.size();
    }

    void remove(int killIdx)
    {
        values.remove(killIdx);
        widgets.remove(killIdx);
        widget_refs.remove(killIdx);
        merged.remove(killIdx);
        page.remove(killIdx);
        tabOrder.remove(killIdx);
    }

    public PdfDictionary getValue(int idx)
    {
        return (PdfDictionary)values.get(idx);
    }

    void addValue(PdfDictionary value)
    {
        values.add(value);
    }

    public PdfDictionary getWidget(int idx)
    {
        return (PdfDictionary)widgets.get(idx);
    }

    void addWidget(PdfDictionary widget)
    {
        widgets.add(widget);
    }

    public PdfIndirectReference getWidgetRef(int idx)
    {
        return (PdfIndirectReference)widget_refs.get(idx);
    }

    void addWidgetRef(PdfIndirectReference widgRef)
    {
        widget_refs.add(widgRef);
    }

    public PdfDictionary getMerged(int idx)
    {
        return (PdfDictionary)merged.get(idx);
    }

    void addMerged(PdfDictionary mergeDict)
    {
        merged.add(mergeDict);
    }

    public Integer getPage(int idx)
    {
        return (Integer)page.get(idx);
    }

    void addPage(int pg)
    {
        page.add(Integer.valueOf(pg));
    }

    void forcePage(int idx, int pg)
    {
        page.set(idx, Integer.valueOf(pg));
    }

    public Integer getTabOrder(int idx)
    {
        return (Integer)tabOrder.get(idx);
    }

    void addTabOrder(int order)
    {
        tabOrder.add(Integer.valueOf(order));
    }

    public static final int WRITE_MERGED = 1;
    public static final int WRITE_WIDGET = 2;
    public static final int WRITE_VALUE = 4;
    protected ArrayList values;
    protected ArrayList widgets;
    protected ArrayList widget_refs;
    protected ArrayList merged;
    protected ArrayList page;
    protected ArrayList tabOrder;

    public AcroFields$Item()
    {
        values = new ArrayList();
        widgets = new ArrayList();
        widget_refs = new ArrayList();
        merged = new ArrayList();
        page = new ArrayList();
        tabOrder = new ArrayList();
    }
}
