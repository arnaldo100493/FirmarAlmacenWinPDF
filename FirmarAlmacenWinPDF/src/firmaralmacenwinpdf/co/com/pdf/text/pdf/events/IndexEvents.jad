// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexEvents.java

package co.com.pdf.text.pdf.events;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.PdfPageEventHelper;
import co.com.pdf.text.pdf.PdfWriter;
import java.util.*;

public class IndexEvents extends PdfPageEventHelper
{
    public class Entry
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
            Integer i = (Integer)indextag.get(tag);
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

        public Entry(String aIn1, String aIn2, String aIn3, String aTag)
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


    public IndexEvents()
    {
        indextag = new TreeMap();
        indexcounter = 0L;
        indexentry = new ArrayList();
        comparator = new Comparator() {

            public int compare(Entry en1, Entry en2)
            {
                int rt = 0;
                if(en1.getIn1() != null && en2.getIn1() != null && (rt = en1.getIn1().compareToIgnoreCase(en2.getIn1())) == 0 && en1.getIn2() != null && en2.getIn2() != null && (rt = en1.getIn2().compareToIgnoreCase(en2.getIn2())) == 0 && en1.getIn3() != null && en2.getIn3() != null)
                    rt = en1.getIn3().compareToIgnoreCase(en2.getIn3());
                return rt;
            }

            public volatile int compare(Object x0, Object x1)
            {
                return compare((Entry)x0, (Entry)x1);
            }

            final IndexEvents this$0;

            
            {
                this$0 = IndexEvents.this;
                super();
            }
        }
;
    }

    public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text)
    {
        indextag.put(text, Integer.valueOf(writer.getPageNumber()));
    }

    public Chunk create(String text, String in1, String in2, String in3)
    {
        Chunk chunk = new Chunk(text);
        String tag = (new StringBuilder()).append("idx_").append(indexcounter++).toString();
        chunk.setGenericTag(tag);
        chunk.setLocalDestination(tag);
        Entry entry = new Entry(in1, in2, in3, tag);
        indexentry.add(entry);
        return chunk;
    }

    public Chunk create(String text, String in1)
    {
        return create(text, in1, "", "");
    }

    public Chunk create(String text, String in1, String in2)
    {
        return create(text, in1, in2, "");
    }

    public void create(Chunk text, String in1, String in2, String in3)
    {
        String tag = (new StringBuilder()).append("idx_").append(indexcounter++).toString();
        text.setGenericTag(tag);
        text.setLocalDestination(tag);
        Entry entry = new Entry(in1, in2, in3, tag);
        indexentry.add(entry);
    }

    public void create(Chunk text, String in1)
    {
        create(text, in1, "", "");
    }

    public void create(Chunk text, String in1, String in2)
    {
        create(text, in1, in2, "");
    }

    public void setComparator(Comparator aComparator)
    {
        comparator = aComparator;
    }

    public List getSortedEntries()
    {
        Map grouped = new HashMap();
        for(int i = 0; i < indexentry.size(); i++)
        {
            Entry e = (Entry)indexentry.get(i);
            String key = e.getKey();
            Entry master = (Entry)grouped.get(key);
            if(master != null)
            {
                master.addPageNumberAndTag(e.getPageNumber(), e.getTag());
            } else
            {
                e.addPageNumberAndTag(e.getPageNumber(), e.getTag());
                grouped.put(key, e);
            }
        }

        List sorted = new ArrayList(grouped.values());
        Collections.sort(sorted, comparator);
        return sorted;
    }

    private Map indextag;
    private long indexcounter;
    private List indexentry;
    private Comparator comparator;

}
