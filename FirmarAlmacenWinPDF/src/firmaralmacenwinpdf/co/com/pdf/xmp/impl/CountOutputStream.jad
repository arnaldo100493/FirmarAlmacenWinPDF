// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CountOutputStream.java

package co.com.pdf.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;

public final class CountOutputStream extends OutputStream
{

    CountOutputStream(OutputStream out)
    {
        bytesWritten = 0;
        this.out = out;
    }

    public void write(byte buf[], int off, int len)
        throws IOException
    {
        out.write(buf, off, len);
        bytesWritten += len;
    }

    public void write(byte buf[])
        throws IOException
    {
        out.write(buf);
        bytesWritten += buf.length;
    }

    public void write(int b)
        throws IOException
    {
        out.write(b);
        bytesWritten++;
    }

    public int getBytesWritten()
    {
        return bytesWritten;
    }

    private final OutputStream out;
    private int bytesWritten;
}
