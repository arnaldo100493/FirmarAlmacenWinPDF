// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileCleaningTracker.java

package org.apache.commons.io;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

// Referenced classes of package org.apache.commons.io:
//            FileDeleteStrategy, FileCleaningTracker

private static final class FileCleaningTracker$Tracker extends PhantomReference
{

    public String getPath()
    {
        return path;
    }

    public boolean delete()
    {
        return deleteStrategy.deleteQuietly(new File(path));
    }

    private final String path;
    private final FileDeleteStrategy deleteStrategy;

    FileCleaningTracker$Tracker(String path, FileDeleteStrategy deleteStrategy, Object marker, ReferenceQueue queue)
    {
        super(marker, queue);
        this.path = path;
        this.deleteStrategy = deleteStrategy != null ? deleteStrategy : FileDeleteStrategy.NORMAL;
    }
}
