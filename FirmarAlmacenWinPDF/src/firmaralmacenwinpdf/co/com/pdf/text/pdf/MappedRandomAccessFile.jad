// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MappedRandomAccessFile.java

package co.com.pdf.text.pdf;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class MappedRandomAccessFile
{

    public MappedRandomAccessFile(String filename, String mode)
        throws FileNotFoundException, IOException
    {
        channel = null;
        if(mode.equals("rw"))
            init((new RandomAccessFile(filename, mode)).getChannel(), java.nio.channels.FileChannel.MapMode.READ_WRITE);
        else
            init((new FileInputStream(filename)).getChannel(), java.nio.channels.FileChannel.MapMode.READ_ONLY);
    }

    private void init(FileChannel channel, java.nio.channels.FileChannel.MapMode mapMode)
        throws IOException
    {
        this.channel = channel;
        size = channel.size();
        pos = 0L;
        int requiredBuffers = (int)(size / 0x40000000L) + (size % 0x40000000L != 0L ? 1 : 0);
        mappedBuffers = new MappedByteBuffer[requiredBuffers];
        try
        {
            int index = 0;
            for(long offset = 0L; offset < size; offset += 0x40000000L)
            {
                long size2 = Math.min(size - offset, 0x40000000L);
                mappedBuffers[index] = channel.map(mapMode, offset, size2);
                mappedBuffers[index].load();
                index++;
            }

            if(index != requiredBuffers)
                throw new Error((new StringBuilder()).append("Should never happen - ").append(index).append(" != ").append(requiredBuffers).toString());
        }
        catch(IOException e)
        {
            close();
            throw e;
        }
        catch(RuntimeException e)
        {
            close();
            throw e;
        }
    }

    public FileChannel getChannel()
    {
        return channel;
    }

    public int read()
    {
        int mapN;
        int offN;
        try
        {
            mapN = (int)(pos / 0x40000000L);
            offN = (int)(pos % 0x40000000L);
            if(mapN >= mappedBuffers.length)
                return -1;
        }
        catch(BufferUnderflowException e)
        {
            return -1;
        }
        if(offN >= mappedBuffers[mapN].limit())
            return -1;
        byte b = mappedBuffers[mapN].get(offN);
        pos++;
        int n = b & 0xff;
        return n;
    }

    public int read(byte bytes[], int off, int len)
    {
        int mapN = (int)(pos / 0x40000000L);
        int offN = (int)(pos % 0x40000000L);
        int totalRead = 0;
        do
        {
            if(totalRead >= len || mapN >= mappedBuffers.length)
                break;
            MappedByteBuffer currentBuffer = mappedBuffers[mapN];
            if(offN > currentBuffer.limit())
                break;
            currentBuffer.position(offN);
            int bytesFromThisBuffer = Math.min(len - totalRead, currentBuffer.remaining());
            currentBuffer.get(bytes, off, bytesFromThisBuffer);
            off += bytesFromThisBuffer;
            pos += bytesFromThisBuffer;
            totalRead += bytesFromThisBuffer;
            mapN++;
            offN = 0;
        } while(true);
        return totalRead != 0 ? totalRead : -1;
    }

    public long getFilePointer()
    {
        return pos;
    }

    public void seek(long pos)
    {
        this.pos = pos;
    }

    public long length()
    {
        return size;
    }

    public void close()
        throws IOException
    {
        for(int i = 0; i < mappedBuffers.length; i++)
            if(mappedBuffers[i] != null)
            {
                clean(mappedBuffers[i]);
                mappedBuffers[i] = null;
            }

        if(channel != null)
            channel.close();
        channel = null;
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
    }

    public static boolean clean(ByteBuffer buffer)
    {
        if(buffer == null || !buffer.isDirect())
        {
            return false;
        } else
        {
            Boolean b = (Boolean)AccessController.doPrivileged(new PrivilegedAction(buffer) {

                public Boolean run()
                {
                    Boolean success = Boolean.FALSE;
                    try
                    {
                        Method getCleanerMethod = buffer.getClass().getMethod("cleaner", (Class[])null);
                        getCleanerMethod.setAccessible(true);
                        Object cleaner = getCleanerMethod.invoke(buffer, (Object[])null);
                        Method clean = cleaner.getClass().getMethod("clean", (Class[])null);
                        clean.invoke(cleaner, (Object[])null);
                        success = Boolean.TRUE;
                    }
                    catch(Exception e) { }
                    return success;
                }

                public volatile Object run()
                {
                    return run();
                }

                final ByteBuffer val$buffer;

            
            {
                buffer = bytebuffer;
                super();
            }
            }
);
            return b.booleanValue();
        }
    }

    private static final int BUFSIZE = 0x40000000;
    private FileChannel channel;
    private MappedByteBuffer mappedBuffers[];
    private long size;
    private long pos;
}
