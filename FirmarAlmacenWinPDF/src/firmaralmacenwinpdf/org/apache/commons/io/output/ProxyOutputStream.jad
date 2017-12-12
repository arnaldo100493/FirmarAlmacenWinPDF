// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProxyOutputStream.java

package org.apache.commons.io.output;

import java.io.*;

public class ProxyOutputStream extends FilterOutputStream
{

    public ProxyOutputStream(OutputStream proxy)
    {
        super(proxy);
    }

    public void write(int idx)
        throws IOException
    {
        try
        {
            beforeWrite(1);
            out.write(idx);
            afterWrite(1);
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public void write(byte bts[])
        throws IOException
    {
        try
        {
            int len = bts == null ? 0 : bts.length;
            beforeWrite(len);
            out.write(bts);
            afterWrite(len);
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public void write(byte bts[], int st, int end)
        throws IOException
    {
        try
        {
            beforeWrite(end);
            out.write(bts, st, end);
            afterWrite(end);
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public void flush()
        throws IOException
    {
        try
        {
            out.flush();
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    public void close()
        throws IOException
    {
        try
        {
            out.close();
        }
        catch(IOException e)
        {
            handleIOException(e);
        }
    }

    protected void beforeWrite(int i)
        throws IOException
    {
    }

    protected void afterWrite(int i)
        throws IOException
    {
    }

    protected void handleIOException(IOException e)
        throws IOException
    {
        throw e;
    }
}
