// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DemuxOutputStream.java

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class DemuxOutputStream extends OutputStream
{

    public DemuxOutputStream()
    {
    }

    public OutputStream bindStream(OutputStream output)
    {
        OutputStream stream = (OutputStream)m_streams.get();
        m_streams.set(output);
        return stream;
    }

    public void close()
        throws IOException
    {
        OutputStream output = (OutputStream)m_streams.get();
        if(null != output)
            output.close();
    }

    public void flush()
        throws IOException
    {
        OutputStream output = (OutputStream)m_streams.get();
        if(null != output)
            output.flush();
    }

    public void write(int ch)
        throws IOException
    {
        OutputStream output = (OutputStream)m_streams.get();
        if(null != output)
            output.write(ch);
    }

    private final InheritableThreadLocal m_streams = new InheritableThreadLocal();
}
