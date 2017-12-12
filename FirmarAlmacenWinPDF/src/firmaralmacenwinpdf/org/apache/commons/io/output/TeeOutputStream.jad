// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TeeOutputStream.java

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package org.apache.commons.io.output:
//            ProxyOutputStream

public class TeeOutputStream extends ProxyOutputStream
{

    public TeeOutputStream(OutputStream out, OutputStream branch)
    {
        super(out);
        this.branch = branch;
    }

    public synchronized void write(byte b[])
        throws IOException
    {
        super.write(b);
        branch.write(b);
    }

    public synchronized void write(byte b[], int off, int len)
        throws IOException
    {
        super.write(b, off, len);
        branch.write(b, off, len);
    }

    public synchronized void write(int b)
        throws IOException
    {
        super.write(b);
        branch.write(b);
    }

    public void flush()
        throws IOException
    {
        super.flush();
        branch.flush();
    }

    public void close()
        throws IOException
    {
        super.close();
        branch.close();
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        branch.close();
        throw exception;
    }

    protected OutputStream branch;
}
