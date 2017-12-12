// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WindowRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

public class WindowRandomAccessSource
    implements RandomAccessSource
{

    public WindowRandomAccessSource(RandomAccessSource source, long offset)
    {
        this(source, offset, source.length() - offset);
    }

    public WindowRandomAccessSource(RandomAccessSource source, long offset, long length)
    {
        this.source = source;
        this.offset = offset;
        this.length = length;
    }

    public int get(long position)
        throws IOException
    {
        if(position >= length)
            return -1;
        else
            return source.get(offset + position);
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        if(position >= length)
        {
            return -1;
        } else
        {
            long toRead = Math.min(len, length - position);
            return source.get(offset + position, bytes, off, (int)toRead);
        }
    }

    public long length()
    {
        return length;
    }

    public void close()
        throws IOException
    {
        source.close();
    }

    private final RandomAccessSource source;
    private final long offset;
    private final long length;
}
