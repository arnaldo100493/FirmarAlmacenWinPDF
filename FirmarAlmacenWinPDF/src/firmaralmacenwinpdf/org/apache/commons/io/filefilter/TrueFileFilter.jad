// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrueFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            IOFileFilter

public class TrueFileFilter
    implements IOFileFilter, Serializable
{

    protected TrueFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return true;
    }

    public boolean accept(File dir, String name)
    {
        return true;
    }

    public static final IOFileFilter TRUE;
    public static final IOFileFilter INSTANCE;

    static 
    {
        TRUE = new TrueFileFilter();
        INSTANCE = TRUE;
    }
}
