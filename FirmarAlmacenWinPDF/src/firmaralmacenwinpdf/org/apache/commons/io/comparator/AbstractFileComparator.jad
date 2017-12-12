// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractFileComparator.java

package org.apache.commons.io.comparator;

import java.io.File;
import java.util.*;

abstract class AbstractFileComparator
    implements Comparator
{

    AbstractFileComparator()
    {
    }

    public transient File[] sort(File files[])
    {
        if(files != null)
            Arrays.sort(files, this);
        return files;
    }

    public List sort(List files)
    {
        if(files != null)
            Collections.sort(files, this);
        return files;
    }

    public String toString()
    {
        return getClass().getSimpleName();
    }
}
