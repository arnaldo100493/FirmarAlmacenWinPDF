// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CanWriteFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter, NotFileFilter, IOFileFilter

public class CanWriteFileFilter extends AbstractFileFilter
    implements Serializable
{

    protected CanWriteFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return file.canWrite();
    }

    public static final IOFileFilter CAN_WRITE;
    public static final IOFileFilter CANNOT_WRITE;

    static 
    {
        CAN_WRITE = new CanWriteFileFilter();
        CANNOT_WRITE = new NotFileFilter(CAN_WRITE);
    }
}
