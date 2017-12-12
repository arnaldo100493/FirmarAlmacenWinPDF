// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SizeFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter

public class SizeFileFilter extends AbstractFileFilter
    implements Serializable
{

    public SizeFileFilter(long size)
    {
        this(size, true);
    }

    public SizeFileFilter(long size, boolean acceptLarger)
    {
        if(size < 0L)
        {
            throw new IllegalArgumentException("The size must be non-negative");
        } else
        {
            this.size = size;
            this.acceptLarger = acceptLarger;
            return;
        }
    }

    public boolean accept(File file)
    {
        boolean smaller = file.length() < size;
        return acceptLarger ? !smaller : smaller;
    }

    public String toString()
    {
        String condition = acceptLarger ? ">=" : "<";
        return (new StringBuilder()).append(super.toString()).append("(").append(condition).append(size).append(")").toString();
    }

    private final long size;
    private final boolean acceptLarger;
}
