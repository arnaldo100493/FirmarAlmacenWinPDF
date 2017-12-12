// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndicCompositeCharacterComparator.java

package co.com.pdf.text.pdf.languages;

import java.util.Comparator;

public class IndicCompositeCharacterComparator
    implements Comparator
{

    public IndicCompositeCharacterComparator()
    {
    }

    public int compare(String o1, String o2)
    {
        if(o2.length() > o1.length())
            return 1;
        if(o1.length() > o2.length())
            return -1;
        else
            return o1.compareTo(o2);
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((String)x0, (String)x1);
    }
}
