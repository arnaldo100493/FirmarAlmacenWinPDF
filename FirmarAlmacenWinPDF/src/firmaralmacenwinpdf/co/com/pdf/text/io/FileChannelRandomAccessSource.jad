// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileChannelRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;

// Referenced classes of package co.com.pdf.text.io:
//            MappedChannelRandomAccessSource, RandomAccessSource

public class FileChannelRandomAccessSource
    implements RandomAccessSource
{

    public FileChannelRandomAccessSource(FileChannel channel)
        throws IOException
    {
        this.channel = channel;
        source = new MappedChannelRandomAccessSource(channel, 0L, channel.size());
        source.open();
    }

    public void close()
        throws IOException
    {
        source.close();
        channel.close();
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

    private final FileChannel channel;
    private final MappedChannelRandomAccessSource source;
}
