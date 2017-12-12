// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FalseFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            IOFileFilter

public class FalseFileFilter
    implements IOFileFilter, Serializable
{

    protected FalseFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return false;
    }

    public boolean accept(File dir, String name)
    {
        return false;
    }

    public static final IOFileFilter FALSE;
    public static final IOFileFilter INSTANCE;

    static 
    {
        FALSE = new FalseFileFilter();
        INSTANCE = FALSE;
    }
}
