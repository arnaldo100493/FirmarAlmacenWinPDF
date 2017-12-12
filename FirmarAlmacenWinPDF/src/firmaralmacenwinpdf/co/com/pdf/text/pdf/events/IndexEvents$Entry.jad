// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexEvents.java

package co.com.pdf.text.pdf.events;

import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.events:
//            IndexEvents

public class IndexEvents$Entry
{

    public String getIn1()
    {
        return in1;
    }

    public String getIn2()
    {
        return in2;
    }

    public String getIn3()
    {
        return in3;
    }

    public String getTag()
    {
        return tag;
    }

    public int getPageNumber()
    {
        int rt = -1;
        Integer i = (Integer)IndexEvents.access$000(IndexEvents.this).get(tag);
        if(i != null)
            rt = i.intValue();
        return rt;
    }

    public void addPageNumberAndTag(int number, String tag)
    {
        pagenumbers.add(Integer.valueOf(number));
        tags.add(tag);
    }

    public String getKey()
    {
        return (new StringBuilder()).append(in1).append("!").append(in2).append("!").append(in3).toString();
    }

    public List getPagenumbers()
    {
        return pagenumbers;
    }

    public List getTags()
    {
        return tags;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(in1).append(' ');
        buf.append(in2).append(' ');
        buf.append(in3).append(' ');
        for(int i = 0; i < pagenumbers.size(); i++)
            buf.append(pagenumbers.get(i)).append(' ');

        return buf.toString();
    }

    private String in1;
    private String in2;
    private String in3;
    private String tag;
    private List pagenumbers;
    private List tags;
    final IndexEvents this$0;

    public IndexEvents$Entry(String aIn1, String aIn2, String aIn3, String aTag)
    {
        this$0 = IndexEvents.this;
        super();
        pagenumbers = new ArrayList();
        tags = new ArrayList();
        in1 = aIn1;
        in2 = aIn2;
        in3 = aIn3;
        tag = aTag;
    }
}
