// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryFileComparator.java

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package org.apache.commons.io.comparator:
//            AbstractFileComparator, ReverseComparator

public class DirectoryFileComparator extends AbstractFileComparator
    implements Serializable
{

    public DirectoryFileComparator()
    {
    }

    public int compare(File file1, File file2)
    {
        return getType(file1) - getType(file2);
    }

    private int getType(File file)
    {
        return !file.isDirectory() ? 2 : 1;
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

    public static final Comparator DIRECTORY_COMPARATOR;
    public static final Comparator DIRECTORY_REVERSE;

    static 
    {
        DIRECTORY_COMPARATOR = new DirectoryFileComparator();
        DIRECTORY_REVERSE = new ReverseComparator(DIRECTORY_COMPARATOR);
    }
}
