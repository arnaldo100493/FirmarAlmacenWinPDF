// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadMonitor.java

package org.apache.commons.io;


class ThreadMonitor
    implements Runnable
{

    public static Thread start(long timeout)
    {
        return start(Thread.currentThread(), timeout);
    }

    public static Thread start(Thread thread, long timeout)
    {
        Thread monitor = null;
        if(timeout > 0L)
        {
            ThreadMonitor timout = new ThreadMonitor(thread, timeout);
            monitor = new Thread(timout, org/apache/commons/io/ThreadMonitor.getSimpleName());
            monitor.setDaemon(true);
            monitor.start();
        }
        return monitor;
    }

    public static void stop(Thread thread)
    {
        if(thread != null)
            thread.interrupt();
    }

    private ThreadMonitor(Thread thread, long timeout)
    {
        this.thread = thread;
        this.timeout = timeout;
    }

    public void run()
    {
        try
        {
            Thread.sleep(timeout);
            thread.interrupt();
        }
        catch(InterruptedException e) { }
    }

    private final Thread thread;
    private final long timeout;
}
