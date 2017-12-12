// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLFOutputStream.java

package co.org.bouncy.mail.smime.util;

import java.io.*;

public class CRLFOutputStream extends FilterOutputStream
{

    public CRLFOutputStream(OutputStream outputstream)
    {
        super(outputstream);
        lastb = -1;
    }

    public void write(int i)
        throws IOException
    {
        if(i == 13)
            out.write(newline);
        else
        if(i == 10)
        {
            if(lastb != 13)
                out.write(newline);
        } else
        {
            out.write(i);
        }
        lastb = i;
    }

    public void write(byte buf[])
        throws IOException
    {
        write(buf, 0, buf.length);
    }

    public void write(byte buf[], int off, int len)
        throws IOException
    {
        for(int i = off; i != off + len; i++)
            write(buf[i]);

    }

    public void writeln()
        throws IOException
    {
        super.out.write(newline);
    }

    protected int lastb;
    protected static byte newline[];

    static 
    {
        newline = new byte[2];
        newline[0] = 13;
        newline[1] = 10;
    }
}
