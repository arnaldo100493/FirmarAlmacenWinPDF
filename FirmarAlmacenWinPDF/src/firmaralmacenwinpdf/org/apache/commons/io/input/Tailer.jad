// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tailer.java

package org.apache.commons.io.input;

import java.io.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

// Referenced classes of package org.apache.commons.io.input:
//            TailerListener

public class Tailer
    implements Runnable
{

    public Tailer(File file, TailerListener listener)
    {
        this(file, listener, 1000L);
    }

    public Tailer(File file, TailerListener listener, long delayMillis)
    {
        this(file, listener, delayMillis, false);
    }

    public Tailer(File file, TailerListener listener, long delayMillis, boolean end)
    {
        this(file, listener, delayMillis, end, 4096);
    }

    public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen)
    {
        this(file, listener, delayMillis, end, reOpen, 4096);
    }

    public Tailer(File file, TailerListener listener, long delayMillis, boolean end, int bufSize)
    {
        this(file, listener, delayMillis, end, false, bufSize);
    }

    public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize)
    {
        run = true;
        this.file = file;
        this.delayMillis = delayMillis;
        this.end = end;
        inbuf = new byte[bufSize];
        this.listener = listener;
        listener.init(this);
        this.reOpen = reOpen;
    }

    public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, int bufSize)
    {
        Tailer tailer = new Tailer(file, listener, delayMillis, end, bufSize);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize)
    {
        Tailer tailer = new Tailer(file, listener, delayMillis, end, reOpen, bufSize);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end)
    {
        return create(file, listener, delayMillis, end, 4096);
    }

    public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen)
    {
        return create(file, listener, delayMillis, end, reOpen, 4096);
    }

    public static Tailer create(File file, TailerListener listener, long delayMillis)
    {
        return create(file, listener, delayMillis, false);
    }

    public static Tailer create(File file, TailerListener listener)
    {
        return create(file, listener, 1000L, false);
    }

    public File getFile()
    {
        return file;
    }

    public long getDelay()
    {
        return delayMillis;
    }

    public void run()
    {
        RandomAccessFile reader = null;
        long last = 0L;
        long position = 0L;
        while(run && reader == null) 
        {
            try
            {
                reader = new RandomAccessFile(file, "r");
            }
            catch(FileNotFoundException e)
            {
                listener.fileNotFound();
            }
            if(reader == null)
            {
                try
                {
                    Thread.sleep(delayMillis);
                }
                catch(InterruptedException e) { }
            } else
            {
                position = end ? file.length() : 0L;
                last = System.currentTimeMillis();
                reader.seek(position);
            }
        }
        do
        {
            if(!run)
                break;
            boolean newer = FileUtils.isFileNewer(file, last);
            long length = file.length();
            if(length < position)
            {
                listener.fileRotated();
                try
                {
                    RandomAccessFile save = reader;
                    reader = new RandomAccessFile(file, "r");
                    position = 0L;
                    IOUtils.closeQuietly(save);
                }
                catch(FileNotFoundException e)
                {
                    listener.fileNotFound();
                }
            } else
            {
                if(length > position)
                {
                    position = readLines(reader);
                    last = System.currentTimeMillis();
                } else
                if(newer)
                {
                    position = 0L;
                    reader.seek(position);
                    position = readLines(reader);
                    last = System.currentTimeMillis();
                }
                if(reOpen)
                    IOUtils.closeQuietly(reader);
                try
                {
                    Thread.sleep(delayMillis);
                }
                catch(InterruptedException e) { }
                if(run && reOpen)
                {
                    reader = new RandomAccessFile(file, "r");
                    reader.seek(position);
                }
            }
        } while(true);
        IOUtils.closeQuietly(reader);
        break MISSING_BLOCK_LABEL_324;
        Exception e;
        e;
        listener.handle(e);
        IOUtils.closeQuietly(reader);
        break MISSING_BLOCK_LABEL_324;
        Exception exception;
        exception;
        IOUtils.closeQuietly(reader);
        throw exception;
    }

    public void stop()
    {
        run = false;
    }

    private long readLines(RandomAccessFile reader)
        throws IOException
    {
        StringBuilder sb = new StringBuilder();
        long pos = reader.getFilePointer();
        long rePos = pos;
        boolean seenCR = false;
        int num;
        while(run && (num = reader.read(inbuf)) != -1) 
        {
            for(int i = 0; i < num; i++)
            {
                byte ch = inbuf[i];
                switch(ch)
                {
                case 10: // '\n'
                    seenCR = false;
                    listener.handle(sb.toString());
                    sb.setLength(0);
                    rePos = pos + (long)i + 1L;
                    break;

                case 13: // '\r'
                    if(seenCR)
                        sb.append('\r');
                    seenCR = true;
                    break;

                default:
                    if(seenCR)
                    {
                        seenCR = false;
                        listener.handle(sb.toString());
                        sb.setLength(0);
                        rePos = pos + (long)i + 1L;
                    }
                    sb.append((char)ch);
                    break;
                }
            }

            pos = reader.getFilePointer();
        }
        reader.seek(rePos);
        return rePos;
    }

    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final String RAF_MODE = "r";
    private static final int DEFAULT_BUFSIZE = 4096;
    private final byte inbuf[];
    private final File file;
    private final long delayMillis;
    private final boolean end;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;
}
