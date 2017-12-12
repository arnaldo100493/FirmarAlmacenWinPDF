// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GroupedRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

class GroupedRandomAccessSource
    implements RandomAccessSource
{
    private static class SourceEntry
    {

        public long offsetN(long absoluteOffset)
        {
            return absoluteOffset - firstByte;
        }

        final RandomAccessSource source;
        final long firstByte;
        final long lastByte;
        final int index;

        public SourceEntry(int index, RandomAccessSource source, long offset)
        {
            this.index = index;
            this.source = source;
            firstByte = offset;
            lastByte = (offset + source.length()) - 1L;
        }
    }


    public GroupedRandomAccessSource(RandomAccessSource sources[])
        throws IOException
    {
        this.sources = new SourceEntry[sources.length];
        long totalSize = 0L;
        for(int i = 0; i < sources.length; i++)
        {
            this.sources[i] = new SourceEntry(i, sources[i], totalSize);
            totalSize += sources[i].length();
        }

        size = totalSize;
        currentSourceEntry = this.sources[sources.length - 1];
        sourceInUse(currentSourceEntry.source);
    }

    protected int getStartingSourceIndex(long offset)
    {
        if(offset >= currentSourceEntry.firstByte)
            return currentSourceEntry.index;
        else
            return 0;
    }

    private SourceEntry getSourceEntryForOffset(long offset)
        throws IOException
    {
        if(offset >= size)
            return null;
        if(offset >= currentSourceEntry.firstByte && offset <= currentSourceEntry.lastByte)
            return currentSourceEntry;
        sourceReleased(currentSourceEntry.source);
        int startAt = getStartingSourceIndex(offset);
        for(int i = startAt; i < sources.length; i++)
            if(offset >= sources[i].firstByte && offset <= sources[i].lastByte)
            {
                currentSourceEntry = sources[i];
                sourceInUse(currentSourceEntry.source);
                return currentSourceEntry;
            }

        return null;
    }

    protected void sourceReleased(RandomAccessSource randomaccesssource)
        throws IOException
    {
    }

    protected void sourceInUse(RandomAccessSource randomaccesssource)
        throws IOException
    {
    }

    public int get(long position)
        throws IOException
    {
        SourceEntry entry = getSourceEntryForOffset(position);
        if(entry == null)
            return -1;
        else
            return entry.source.get(entry.offsetN(position));
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        SourceEntry entry = getSourceEntryForOffset(position);
        if(entry == null)
            return -1;
        long offN = entry.offsetN(position);
        int remaining = len;
        do
        {
            if(remaining <= 0 || entry == null || offN > entry.source.length())
                break;
            int count = entry.source.get(offN, bytes, off, remaining);
            if(count == -1)
                break;
            off += count;
            position += count;
            remaining -= count;
            offN = 0L;
            entry = getSourceEntryForOffset(position);
        } while(true);
        return remaining != len ? len - remaining : -1;
    }

    public long length()
    {
        return size;
    }

    public void close()
        throws IOException
    {
        SourceEntry arr$[] = sources;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            SourceEntry entry = arr$[i$];
            entry.source.close();
        }

    }

    private final SourceEntry sources[];
    private SourceEntry currentSourceEntry;
    private final long size;
}
