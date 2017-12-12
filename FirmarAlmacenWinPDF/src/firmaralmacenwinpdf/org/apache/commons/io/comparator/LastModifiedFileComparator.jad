// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LastModifiedFileComparator.java

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package org.apache.commons.io.comparator:
//            AbstractFileComparator, ReverseComparator

public class LastModifiedFileComparator extends AbstractFileComparator
    implements Serializable
{

    public LastModifiedFileComparator()
    {
    }

    public int compare(File file1, File file2)
    {
        long result = file1.lastModified() - file2.lastModified();
        if(result < 0L)
            return -1;
        return result <= 0L ? 0 : 1;
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

    public static final Comparator LASTMODIFIED_COMPARATOR;
    public static final Comparator LASTMODIFIED_REVERSE;

    static 
    {
        LASTMODIFIED_COMPARATOR = new LastModifiedFileComparator();
        LASTMODIFIED_REVERSE = new ReverseComparator(LASTMODIFIED_COMPARATOR);
    }
}
