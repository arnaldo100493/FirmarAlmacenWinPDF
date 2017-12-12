// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RandomAccessSourceFactory.java

package co.com.pdf.text.io;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;

// Referenced classes of package co.com.pdf.text.io:
//            ArrayRandomAccessSource, RAFRandomAccessSource, MapFailedException, GetBufferedRandomAccessSource, 
//            FileChannelRandomAccessSource, PagedChannelRandomAccessSource, RandomAccessSource, WindowRandomAccessSource, 
//            GroupedRandomAccessSource, StreamUtil

public final class RandomAccessSourceFactory
{

    public RandomAccessSourceFactory()
    {
        forceRead = false;
        usePlainRandomAccess = false;
        exclusivelyLockFile = false;
    }

    public RandomAccessSourceFactory setForceRead(boolean forceRead)
    {
        this.forceRead = forceRead;
        return this;
    }

    public RandomAccessSourceFactory setUsePlainRandomAccess(boolean usePlainRandomAccess)
    {
        this.usePlainRandomAccess = usePlainRandomAccess;
        return this;
    }

    public RandomAccessSourceFactory setExclusivelyLockFile(boolean exclusivelyLockFile)
    {
        this.exclusivelyLockFile = exclusivelyLockFile;
        return this;
    }

    public RandomAccessSource createSource(byte data[])
    {
        return new ArrayRandomAccessSource(data);
    }

    public RandomAccessSource createSource(RandomAccessFile raf)
        throws IOException
    {
        return new RAFRandomAccessSource(raf);
    }

    public RandomAccessSource createSource(URL url)
        throws IOException
    {
        InputStream is = url.openStream();
        RandomAccessSource randomaccesssource = createSource(is);
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        return randomaccesssource;
        Exception exception;
        exception;
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        throw exception;
    }

    public RandomAccessSource createSource(InputStream is)
        throws IOException
    {
        RandomAccessSource randomaccesssource = createSource(StreamUtil.inputStreamToArray(is));
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        return randomaccesssource;
        Exception exception;
        exception;
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        throw exception;
    }

    public RandomAccessSource createBestSource(String filename)
        throws IOException
    {
        File file = new File(filename);
        if(!file.canRead())
            if(filename.startsWith("file:/") || filename.startsWith("http://") || filename.startsWith("https://") || filename.startsWith("jar:") || filename.startsWith("wsjar:") || filename.startsWith("wsjar:") || filename.startsWith("vfszip:"))
                return createSource(new URL(filename));
            else
                return createByReadingToMemory(filename);
        if(forceRead)
            return createByReadingToMemory(new FileInputStream(filename));
        String openMode = exclusivelyLockFile ? "rw" : "r";
        RandomAccessFile raf = new RandomAccessFile(file, openMode);
        if(exclusivelyLockFile)
            raf.getChannel().lock();
        if(usePlainRandomAccess)
            return new RAFRandomAccessSource(raf);
        try
        {
            return createBestSource(raf.getChannel());
        }
        catch(MapFailedException e)
        {
            return new RAFRandomAccessSource(raf);
        }
        catch(IllegalArgumentException iae)
        {
            return new RAFRandomAccessSource(raf);
        }
    }

    public RandomAccessSource createBestSource(FileChannel channel)
        throws IOException
    {
        if(channel.size() <= 0x4000000L)
            return new GetBufferedRandomAccessSource(new FileChannelRandomAccessSource(channel));
        else
            return new GetBufferedRandomAccessSource(new PagedChannelRandomAccessSource(channel));
    }

    public RandomAccessSource createRanged(RandomAccessSource source, long ranges[])
        throws IOException
    {
        RandomAccessSource sources[] = new RandomAccessSource[ranges.length / 2];
        for(int i = 0; i < ranges.length; i += 2)
            sources[i / 2] = new WindowRandomAccessSource(source, ranges[i], ranges[i + 1]);

        return new GroupedRandomAccessSource(sources);
    }

    private RandomAccessSource createByReadingToMemory(String filename)
        throws IOException
    {
        InputStream is = StreamUtil.getResourceStream(filename);
        if(is == null)
            throw new IOException(MessageLocalization.getComposedMessage("1.not.found.as.file.or.resource", new Object[] {
                filename
            }));
        else
            return createByReadingToMemory(is);
    }

    private RandomAccessSource createByReadingToMemory(InputStream is)
        throws IOException
    {
        ArrayRandomAccessSource arrayrandomaccesssource = new ArrayRandomAccessSource(StreamUtil.inputStreamToArray(is));
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        return arrayrandomaccesssource;
        Exception exception;
        exception;
        try
        {
            is.close();
        }
        catch(IOException ioe) { }
        throw exception;
    }

    private boolean forceRead;
    private boolean usePlainRandomAccess;
    private boolean exclusivelyLockFile;
}
