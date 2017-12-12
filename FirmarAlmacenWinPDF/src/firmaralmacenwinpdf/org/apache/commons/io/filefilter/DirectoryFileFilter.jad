// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter, IOFileFilter

public class DirectoryFileFilter extends AbstractFileFilter
    implements Serializable
{

    protected DirectoryFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return file.isDirectory();
    }

    public static final IOFileFilter DIRECTORY;
    public static final IOFileFilter INSTANCE;

    static 
    {
        DIRECTORY = new DirectoryFileFilter();
        INSTANCE = DIRECTORY;
    }
}
