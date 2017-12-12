// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TeeOutputStream.java

package co.org.bouncy.util.io;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends OutputStream
{

    public TeeOutputStream(OutputStream output1, OutputStream output2)
    {
        this.output1 = output1;
        this.output2 = output2;
    }

    public void write(byte buf[])
        throws IOException
    {
        output1.write(buf);
        output2.write(buf);
    }

    public void write(byte buf[], int off, int len)
        throws IOException
    {
        output1.write(buf, off, len);
        output2.write(buf, off, len);
    }

    public void write(int b)
        throws IOException
    {
        output1.write(b);
        output2.write(b);
    }

    public void flush()
        throws IOException
    {
        output1.flush();
        output2.flush();
    }

    public void close()
        throws IOException
    {
        output1.close();
        output2.close();
    }

    private OutputStream output1;
    private OutputStream output2;
}
