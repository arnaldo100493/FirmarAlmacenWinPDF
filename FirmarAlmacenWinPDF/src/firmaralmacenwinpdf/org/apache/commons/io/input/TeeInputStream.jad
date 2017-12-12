// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TeeInputStream.java

package org.apache.commons.io.input;

import java.io.*;

// Referenced classes of package org.apache.commons.io.input:
//            ProxyInputStream

public class TeeInputStream extends ProxyInputStream
{

    public TeeInputStream(InputStream input, OutputStream branch)
    {
        this(input, branch, false);
    }

    public TeeInputStream(InputStream input, OutputStream branch, boolean closeBranch)
    {
        super(input);
        this.branch = branch;
        this.closeBranch = closeBranch;
    }

    public void close()
        throws IOException
    {
        super.close();
        if(closeBranch)
            branch.close();
        break MISSING_BLOCK_LABEL_38;
        Exception exception;
        exception;
        if(closeBranch)
            branch.close();
        throw exception;
    }

    public int read()
        throws IOException
    {
        int ch = super.read();
        if(ch != -1)
            branch.write(ch);
        return ch;
    }

    public int read(byte bts[], int st, int end)
        throws IOException
    {
        int n = super.read(bts, st, end);
        if(n != -1)
            branch.write(bts, st, n);
        return n;
    }

    public int read(byte bts[])
        throws IOException
    {
        int n = super.read(bts);
        if(n != -1)
            branch.write(bts, 0, n);
        return n;
    }

    private final OutputStream branch;
    private final boolean closeBranch;
}
