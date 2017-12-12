// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BufferPoolImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

class BufferPoolImpl
    implements BufferPool
{

    BufferPoolImpl()
    {
    }

    public final char[] take()
    {
        char t[] = (char[])getQueue().poll();
        if(t == null)
            return new char[4096];
        else
            return t;
    }

    private ConcurrentLinkedQueue getQueue()
    {
        WeakReference q = queue;
        ConcurrentLinkedQueue d;
        if(q != null)
        {
            d = (ConcurrentLinkedQueue)q.get();
            if(d != null)
                return d;
        }
        d = new ConcurrentLinkedQueue();
        queue = new WeakReference(d);
        return d;
    }

    public final void recycle(char t[])
    {
        getQueue().offer(t);
    }

    private volatile WeakReference queue;
}
