// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexEvents.java

package co.com.pdf.text.pdf.events;

import java.util.Comparator;

// Referenced classes of package co.com.pdf.text.pdf.events:
//            IndexEvents

class IndexEvents$1
    implements Comparator
{

    public int compare(try en1, try en2)
    {
        int rt = 0;
        if(en1.getIn1() != null && en2.getIn1() != null && (rt = en1.getIn1().compareToIgnoreCase(en2.getIn1())) == 0 && en1.getIn2() != null && en2.getIn2() != null && (rt = en1.getIn2().compareToIgnoreCase(en2.getIn2())) == 0 && en1.getIn3() != null && en2.getIn3() != null)
            rt = en1.getIn3().compareToIgnoreCase(en2.getIn3());
        return rt;
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((try)x0, (try)x1);
    }

    final IndexEvents this$0;

    IndexEvents$1()
    {
        this$0 = IndexEvents.this;
        super();
    }
}
