// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetBufferedRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

public class GetBufferedRandomAccessSource
    implements RandomAccessSource
{

    public GetBufferedRandomAccessSource(RandomAccessSource source)
    {
        getBufferStart = -1L;
        getBufferEnd = -1L;
        this.source = source;
        getBuffer = new byte[(int)Math.min(Math.max(source.length() / 4L, 1L), 4096L)];
        getBufferStart = -1L;
        getBufferEnd = -1L;
    }

    public int get(long position)
        throws IOException
    {
        if(position < getBufferStart || position > getBufferEnd)
        {
            int count = source.get(position, getBuffer, 0, getBuffer.length);
            if(count == -1)
                return -1;
            getBufferStart = position;
            getBufferEnd = (position + (long)count) - 1L;
        }
        int bufPos = (int)(position - getBufferStart);
        return 0xff & getBuffer[bufPos];
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
        source.close();
        getBufferStart = -1L;
        getBufferEnd = -1L;
    }

    private final RandomAccessSource source;
    private final byte getBuffer[];
    private long getBufferStart;
    private long getBufferEnd;
}
