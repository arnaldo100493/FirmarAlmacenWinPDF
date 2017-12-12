// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CanReadFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter, NotFileFilter, AndFileFilter, CanWriteFileFilter, 
//            IOFileFilter

public class CanReadFileFilter extends AbstractFileFilter
    implements Serializable
{

    protected CanReadFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return file.canRead();
    }

    public static final IOFileFilter CAN_READ;
    public static final IOFileFilter CANNOT_READ;
    public static final IOFileFilter READ_ONLY;

    static 
    {
        CAN_READ = new CanReadFileFilter();
        CANNOT_READ = new NotFileFilter(CAN_READ);
        READ_ONLY = new AndFileFilter(CAN_READ, CanWriteFileFilter.CANNOT_WRITE);
    }
}
