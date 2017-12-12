// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileAlterationMonitor.java

package org.apache.commons.io.monitor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;

// Referenced classes of package org.apache.commons.io.monitor:
//            FileAlterationObserver

public final class FileAlterationMonitor
    implements Runnable
{

    public FileAlterationMonitor()
    {
        this(10000L);
    }

    public FileAlterationMonitor(long interval)
    {
        observers = new CopyOnWriteArrayList();
        thread = null;
        running = false;
        this.interval = interval;
    }

    public transient FileAlterationMonitor(long interval, FileAlterationObserver observers[])
    {
        this(interval);
        if(observers != null)
        {
            FileAlterationObserver arr$[] = observers;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                FileAlterationObserver observer = arr$[i$];
                addObserver(observer);
            }

        }
    }

    public long getInterval()
    {
        return interval;
    }

    public synchronized void setThreadFactory(ThreadFactory threadFactory)
    {
        this.threadFactory = threadFactory;
    }

    public void addObserver(FileAlterationObserver observer)
    {
        if(observer != null)
            observers.add(observer);
    }

    public void removeObserver(FileAlterationObserver observer)
    {
        if(observer != null)
            while(observers.remove(observer)) ;
    }

    public Iterable getObservers()
    {
        return observers;
    }

    public synchronized void start()
        throws Exception
    {
        if(running)
            throw new IllegalStateException("Monitor is already running");
        FileAlterationObserver observer;
        for(Iterator i$ = observers.iterator(); i$.hasNext(); observer.initialize())
            observer = (FileAlterationObserver)i$.next();

        running = true;
        if(threadFactory != null)
            thread = threadFactory.newThread(this);
        else
            thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
        throws Exception
    {
        stop(interval);
    }

    public synchronized void stop(long stopInterval)
        throws Exception
    {
        if(!running)
            throw new IllegalStateException("Monitor is not running");
        running = false;
        try
        {
            thread.join(stopInterval);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        FileAlterationObserver observer;
        for(Iterator i$ = observers.iterator(); i$.hasNext(); observer.destroy())
            observer = (FileAlterationObserver)i$.next();

    }

    public void run()
    {
        do
        {
            if(!running)
                break;
            FileAlterationObserver observer;
            for(Iterator i$ = observers.iterator(); i$.hasNext(); observer.checkAndNotify())
                observer = (FileAlterationObserver)i$.next();

            if(!running)
                break;
            try
            {
                Thread.sleep(interval);
            }
            catch(InterruptedException ignored) { }
        } while(true);
    }

    private final long interval;
    private final List observers;
    private Thread thread;
    private ThreadFactory threadFactory;
    private volatile boolean running;
}
