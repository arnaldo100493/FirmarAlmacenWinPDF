// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OutputStreamCounter.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamCounter extends OutputStream
{

    public OutputStreamCounter(OutputStream out)
    {
        counter = 0L;
        this.out = out;
    }

    public void close()
        throws IOException
    {
        out.close();
    }

    public void flush()
        throws IOException
    {
        out.flush();
    }

    public void write(byte b[])
        throws IOException
    {
        counter += b.length;
        out.write(b);
    }

    public void write(int b)
        throws IOException
    {
        counter++;
        out.write(b);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        counter += len;
        out.write(b, off, len);
    }

    public long getCounter()
    {
        return counter;
    }

    public void resetCounter()
    {
        counter = 0L;
    }

    protected OutputStream out;
    protected long counter;
}
