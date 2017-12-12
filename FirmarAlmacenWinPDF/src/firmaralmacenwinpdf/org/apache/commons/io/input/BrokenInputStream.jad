// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenInputStream.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class BrokenInputStream extends InputStream
{

    public BrokenInputStream(IOException exception)
    {
        this.exception = exception;
    }

    public BrokenInputStream()
    {
        this(new IOException("Broken input stream"));
    }

    public int read()
        throws IOException
    {
        throw exception;
    }

    public int available()
        throws IOException
    {
        throw exception;
    }

    public long skip(long n)
        throws IOException
    {
        throw exception;
    }

    public void reset()
        throws IOException
    {
        throw exception;
    }

    public void close()
        throws IOException
    {
        throw exception;
    }

    private final IOException exception;
}
