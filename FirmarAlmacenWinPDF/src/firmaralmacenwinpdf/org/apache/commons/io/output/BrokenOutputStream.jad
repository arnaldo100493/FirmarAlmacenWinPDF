// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenOutputStream.java

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class BrokenOutputStream extends OutputStream
{

    public BrokenOutputStream(IOException exception)
    {
        this.exception = exception;
    }

    public BrokenOutputStream()
    {
        this(new IOException("Broken output stream"));
    }

    public void write(int b)
        throws IOException
    {
        throw exception;
    }

    public void flush()
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
