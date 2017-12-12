// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PagedChannelRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package co.com.pdf.text.io:
//            GroupedRandomAccessSource, MappedChannelRandomAccessSource, RandomAccessSource

class PagedChannelRandomAccessSource extends GroupedRandomAccessSource
    implements RandomAccessSource
{
    private static class MRU
    {

        public Object enqueue(Object newElement)
        {
            if(queue.size() > 0 && queue.getFirst() == newElement)
                return null;
            for(Iterator it = queue.iterator(); it.hasNext();)
            {
                Object element = it.next();
                if(newElement == element)
                {
                    it.remove();
                    queue.addFirst(newElement);
                    return null;
                }
            }

            queue.addFirst(newElement);
            if(queue.size() > limit)
                return queue.removeLast();
            else
                return null;
        }

        private final int limit;
        private LinkedList queue;

        public MRU(int limit)
        {
            queue = new LinkedList();
            this.limit = limit;
        }
    }


    public PagedChannelRandomAccessSource(FileChannel channel)
        throws IOException
    {
        this(channel, 0x4000000, 16);
    }

    public PagedChannelRandomAccessSource(FileChannel channel, int totalBufferSize, int maxOpenBuffers)
        throws IOException
    {
        super(buildSources(channel, totalBufferSize / maxOpenBuffers));
        this.channel = channel;
        bufferSize = totalBufferSize / maxOpenBuffers;
        mru = new MRU(maxOpenBuffers);
    }

    private static RandomAccessSource[] buildSources(FileChannel channel, int bufferSize)
        throws IOException
    {
        long size = channel.size();
        if(size <= 0L)
            throw new IOException("File size must be greater than zero");
        int bufferCount = (int)(size / (long)bufferSize) + (size % (long)bufferSize != 0L ? 1 : 0);
        MappedChannelRandomAccessSource sources[] = new MappedChannelRandomAccessSource[bufferCount];
        for(int i = 0; i < bufferCount; i++)
        {
            long pageOffset = (long)i * (long)bufferSize;
            long pageLength = Math.min(size - pageOffset, bufferSize);
            sources[i] = new MappedChannelRandomAccessSource(channel, pageOffset, pageLength);
        }

        return sources;
    }

    protected int getStartingSourceIndex(long offset)
    {
        return (int)(offset / (long)bufferSize);
    }

    protected void sourceReleased(RandomAccessSource source)
        throws IOException
    {
        RandomAccessSource old = (RandomAccessSource)mru.enqueue(source);
        if(old != null)
            old.close();
    }

    protected void sourceInUse(RandomAccessSource source)
        throws IOException
    {
        ((MappedChannelRandomAccessSource)source).open();
    }

    public void close()
        throws IOException
    {
        super.close();
        channel.close();
    }

    public static final int DEFAULT_TOTAL_BUFSIZE = 0x4000000;
    public static final int DEFAULT_MAX_OPEN_BUFFERS = 16;
    private final int bufferSize;
    private final FileChannel channel;
    private final MRU mru;
}
