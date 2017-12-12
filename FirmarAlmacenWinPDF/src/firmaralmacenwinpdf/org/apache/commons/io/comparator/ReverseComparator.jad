// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReverseComparator.java

package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package org.apache.commons.io.comparator:
//            AbstractFileComparator

class ReverseComparator extends AbstractFileComparator
    implements Serializable
{

    public ReverseComparator(Comparator delegate)
    {
        if(delegate == null)
        {
            throw new IllegalArgumentException("Delegate comparator is missing");
        } else
        {
            _flddelegate = delegate;
            return;
        }
    }

    public int compare(File file1, File file2)
    {
        return _flddelegate.compare(file2, file1);
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append("[").append(_flddelegate.toString()).append("]").toString();
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((File)x0, (File)x1);
    }

    private final Comparator _flddelegate;
}
