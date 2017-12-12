// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileCleaner.java

package org.apache.commons.io;

import java.io.File;

// Referenced classes of package org.apache.commons.io:
//            FileCleaningTracker, FileDeleteStrategy

/**
 * @deprecated Class FileCleaner is deprecated
 */

public class FileCleaner
{

    public FileCleaner()
    {
    }

    /**
     * @deprecated Method track is deprecated
     */

    public static void track(File file, Object marker)
    {
        theInstance.track(file, marker);
    }

    /**
     * @deprecated Method track is deprecated
     */

    public static void track(File file, Object marker, FileDeleteStrategy deleteStrategy)
    {
        theInstance.track(file, marker, deleteStrategy);
    }

    /**
     * @deprecated Method track is deprecated
     */

    public static void track(String path, Object marker)
    {
        theInstance.track(path, marker);
    }

    /**
     * @deprecated Method track is deprecated
     */

    public static void track(String path, Object marker, FileDeleteStrategy deleteStrategy)
    {
        theInstance.track(path, marker, deleteStrategy);
    }

    /**
     * @deprecated Method getTrackCount is deprecated
     */

    public static int getTrackCount()
    {
        return theInstance.getTrackCount();
    }

    /**
     * @deprecated Method exitWhenFinished is deprecated
     */

    public static synchronized void exitWhenFinished()
    {
        theInstance.exitWhenFinished();
    }

    public static FileCleaningTracker getInstance()
    {
        return theInstance;
    }

    static final FileCleaningTracker theInstance = new FileCleaningTracker();

}
