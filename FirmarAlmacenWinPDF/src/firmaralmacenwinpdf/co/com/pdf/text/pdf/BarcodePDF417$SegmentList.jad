// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodePDF417.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            BarcodePDF417

protected static class BarcodePDF417$SegmentList
{

    public void add(char type, int start, int end)
    {
        list.add(new BarcodePDF417.Segment(type, start, end));
    }

    public BarcodePDF417.Segment get(int idx)
    {
        if(idx < 0 || idx >= list.size())
            return null;
        else
            return (BarcodePDF417.Segment)list.get(idx);
    }

    public void remove(int idx)
    {
        if(idx < 0 || idx >= list.size())
        {
            return;
        } else
        {
            list.remove(idx);
            return;
        }
    }

    public int size()
    {
        return list.size();
    }

    protected ArrayList list;

    protected BarcodePDF417$SegmentList()
    {
        list = new ArrayList();
    }
}
