// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MappedChannelRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;

// Referenced classes of package co.com.pdf.text.io:
//            ByteBufferRandomAccessSource, MapFailedException, RandomAccessSource

class MappedChannelRandomAccessSource
    implements RandomAccessSource
{

    public MappedChannelRandomAccessSource(FileChannel channel, long offset, long length)
    {
        if(offset < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append(offset).append(" is negative").toString());
        if(length <= 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append(length).append(" is zero or negative").toString());
        } else
        {
            this.channel = channel;
            this.offset = offset;
            this.length = length;
            source = null;
            return;
        }
    }

    void open()
        throws IOException
    {
        if(source != null)
            return;
        if(!channel.isOpen())
            throw new IllegalStateException("Channel is closed");
        try
        {
            source = new ByteBufferRandomAccessSource(channel.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, offset, length));
        }
        catch(IOException e)
        {
            if(exceptionIsMapFailureException(e))
                throw new MapFailedException(e);
        }
    }

    private static boolean exceptionIsMapFailureException(IOException e)
    {
        return e.getMessage() != null && e.getMessage().indexOf("Map failed") >= 0;
    }

    public int get(long position)
        throws IOException
    {
        if(source == null)
            throw new IOException("RandomAccessSource not opened");
        else
            return source.get(position);
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        if(source == null)
            throw new IOException("RandomAccessSource not opened");
        else
            return source.get(position, bytes, off, len);
    }

    public long length()
    {
        return length;
    }

    public void close()
        throws IOException
    {
        if(source == null)
        {
            return;
        } else
        {
            source.close();
            source = null;
            return;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(" (").append(offset).append(", ").append(length).append(")").toString();
    }

    private final FileChannel channel;
    private final long offset;
    private final long length;
    private ByteBufferRandomAccessSource source;
}
