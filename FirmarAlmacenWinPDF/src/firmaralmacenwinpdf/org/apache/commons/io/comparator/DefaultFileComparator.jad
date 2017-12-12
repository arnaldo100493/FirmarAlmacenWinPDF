// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultFileComparator.java

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package org.apache.commons.io.comparator:
//            AbstractFileComparator, ReverseComparator

public class DefaultFileComparator extends AbstractFileComparator
    implements Serializable
{

    public DefaultFileComparator()
    {
    }

    public int compare(File file1, File file2)
    {
        return file1.compareTo(file2);
    }

    public volatile String toString()
    {
        return super.toString();
    }

    public volatile List sort(List x0)
    {
        return super.sort(x0);
    }

    public volatile File[] sort(File x0[])
    {
        return super.sort(x0);
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((File)x0, (File)x1);
    }

    public static final Comparator DEFAULT_COMPARATOR;
    public static final Comparator DEFAULT_REVERSE;

    static 
    {
        DEFAULT_COMPARATOR = new DefaultFileComparator();
        DEFAULT_REVERSE = new ReverseComparator(DEFAULT_COMPARATOR);
    }
}
