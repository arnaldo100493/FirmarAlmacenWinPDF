// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NotFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter, IOFileFilter

public class NotFileFilter extends AbstractFileFilter
    implements Serializable
{

    public NotFileFilter(IOFileFilter filter)
    {
        if(filter == null)
        {
            throw new IllegalArgumentException("The filter must not be null");
        } else
        {
            this.filter = filter;
            return;
        }
    }

    public boolean accept(File file)
    {
        return !filter.accept(file);
    }

    public boolean accept(File file, String name)
    {
        return !filter.accept(file, name);
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append("(").append(filter.toString()).append(")").toString();
    }

    private final IOFileFilter filter;
}
