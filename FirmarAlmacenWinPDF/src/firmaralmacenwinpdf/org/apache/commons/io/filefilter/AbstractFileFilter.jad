// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;

// Referenced classes of package org.apache.commons.io.filefilter:
//            IOFileFilter

public abstract class AbstractFileFilter
    implements IOFileFilter
{

    public AbstractFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return accept(file.getParentFile(), file.getName());
    }

    public boolean accept(File dir, String name)
    {
        return accept(new File(dir, name));
    }

    public String toString()
    {
        return getClass().getSimpleName();
    }
}
