// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileCleaningTracker.java

package org.apache.commons.io;

import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.List;

// Referenced classes of package org.apache.commons.io:
//            FileCleaningTracker

private final class FileCleaningTracker$Reaper extends Thread
{

    public void run()
    {
        while(!exitWhenFinished || trackers.size() > 0) 
            try
            {
                 tracker = ()q.remove();
                trackers.remove(tracker);
                if(!tracker.delete())
                    deleteFailures.add(tracker.getPath());
                tracker.clear();
            }
            catch(InterruptedException e) { }
    }

    final FileCleaningTracker this$0;

    FileCleaningTracker$Reaper()
    {
        this$0 = FileCleaningTracker.this;
        super("File Reaper");
        setPriority(10);
        setDaemon(true);
    }
}
