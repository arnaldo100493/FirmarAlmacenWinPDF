// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DelegateFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.*;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter

public class DelegateFileFilter extends AbstractFileFilter
    implements Serializable
{

    public DelegateFileFilter(FilenameFilter filter)
    {
        if(filter == null)
        {
            throw new IllegalArgumentException("The FilenameFilter must not be null");
        } else
        {
            filenameFilter = filter;
            fileFilter = null;
            return;
        }
    }

    public DelegateFileFilter(FileFilter filter)
    {
        if(filter == null)
        {
            throw new IllegalArgumentException("The FileFilter must not be null");
        } else
        {
            fileFilter = filter;
            filenameFilter = null;
            return;
        }
    }

    public boolean accept(File file)
    {
        if(fileFilter != null)
            return fileFilter.accept(file);
        else
            return super.accept(file);
    }

    public boolean accept(File dir, String name)
    {
        if(filenameFilter != null)
            return filenameFilter.accept(dir, name);
        else
            return super.accept(dir, name);
    }

    public String toString()
    {
        String delegate = fileFilter == null ? filenameFilter.toString() : fileFilter.toString();
        return (new StringBuilder()).append(super.toString()).append("(").append(delegate).append(")").toString();
    }

    private final FilenameFilter filenameFilter;
    private final FileFilter fileFilter;
}
