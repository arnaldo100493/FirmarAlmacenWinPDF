// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DemuxInputStream.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class DemuxInputStream extends InputStream
{

    public DemuxInputStream()
    {
    }

    public InputStream bindStream(InputStream input)
    {
        InputStream oldValue = (InputStream)m_streams.get();
        m_streams.set(input);
        return oldValue;
    }

    public void close()
        throws IOException
    {
        InputStream input = (InputStream)m_streams.get();
        if(null != input)
            input.close();
    }

    public int read()
        throws IOException
    {
        InputStream input = (InputStream)m_streams.get();
        if(null != input)
            return input.read();
        else
            return -1;
    }

    private final InheritableThreadLocal m_streams = new InheritableThreadLocal();
}
