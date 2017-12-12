// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndependentRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

public class IndependentRandomAccessSource
    implements RandomAccessSource
{

    public IndependentRandomAccessSource(RandomAccessSource source)
    {
        this.source = source;
    }

    public int get(long position)
        throws IOException
    {
        return source.get(position);
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        return source.get(position, bytes, off, len);
    }

    public long length()
    {
        return source.length();
    }

    public void close()
        throws IOException
    {
    }

    private final RandomAccessSource source;
}
