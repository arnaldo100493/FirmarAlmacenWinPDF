// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EmptyFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter, NotFileFilter, IOFileFilter

public class EmptyFileFilter extends AbstractFileFilter
    implements Serializable
{

    protected EmptyFileFilter()
    {
    }

    public boolean accept(File file)
    {
        if(file.isDirectory())
        {
            File files[] = file.listFiles();
            return files == null || files.length == 0;
        } else
        {
            return file.length() == 0L;
        }
    }

    public static final IOFileFilter EMPTY;
    public static final IOFileFilter NOT_EMPTY;

    static 
    {
        EMPTY = new EmptyFileFilter();
        NOT_EMPTY = new NotFileFilter(EMPTY);
    }
}
