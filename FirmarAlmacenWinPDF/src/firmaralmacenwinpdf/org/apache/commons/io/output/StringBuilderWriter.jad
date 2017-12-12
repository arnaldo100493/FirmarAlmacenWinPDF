// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringBuilderWriter.java

package org.apache.commons.io.output;

import java.io.*;

public class StringBuilderWriter extends Writer
    implements Serializable
{

    public StringBuilderWriter()
    {
        builder = new StringBuilder();
    }

    public StringBuilderWriter(int capacity)
    {
        builder = new StringBuilder(capacity);
    }

    public StringBuilderWriter(StringBuilder builder)
    {
        this.builder = builder == null ? new StringBuilder() : builder;
    }

    public Writer append(char value)
    {
        builder.append(value);
        return this;
    }

    public Writer append(CharSequence value)
    {
        builder.append(value);
        return this;
    }

    public Writer append(CharSequence value, int start, int end)
    {
        builder.append(value, start, end);
        return this;
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public void write(String value)
    {
        if(value != null)
            builder.append(value);
    }

    public void write(char value[], int offset, int length)
    {
        if(value != null)
            builder.append(value, offset, length);
    }

    public StringBuilder getBuilder()
    {
        return builder;
    }

    public String toString()
    {
        return builder.toString();
    }

    public volatile Appendable append(char x0)
        throws IOException
    {
        return append(x0);
    }

    public volatile Appendable append(CharSequence x0, int x1, int x2)
        throws IOException
    {
        return append(x0, x1, x2);
    }

    public volatile Appendable append(CharSequence x0)
        throws IOException
    {
        return append(x0);
    }

    private final StringBuilder builder;
}
