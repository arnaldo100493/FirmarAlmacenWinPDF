// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteArrayOutputStream.java

package org.apache.commons.io.output;

import java.io.*;
import java.util.*;
import org.apache.commons.io.input.ClosedInputStream;

public class ByteArrayOutputStream extends OutputStream
{

    public ByteArrayOutputStream()
    {
        this(1024);
    }

    public ByteArrayOutputStream(int size)
    {
        buffers = new ArrayList();
        if(size < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Negative initial size: ").append(size).toString());
        synchronized(this)
        {
            needNewBuffer(size);
        }
    }

    private void needNewBuffer(int newcount)
    {
        if(currentBufferIndex < buffers.size() - 1)
        {
            filledBufferSum += currentBuffer.length;
            currentBufferIndex++;
            currentBuffer = (byte[])buffers.get(currentBufferIndex);
        } else
        {
            int newBufferSize;
            if(currentBuffer == null)
            {
                newBufferSize = newcount;
                filledBufferSum = 0;
            } else
            {
                newBufferSize = Math.max(currentBuffer.length << 1, newcount - filledBufferSum);
                filledBufferSum += currentBuffer.length;
            }
            currentBufferIndex++;
            currentBuffer = new byte[newBufferSize];
            buffers.add(currentBuffer);
        }
    }

    public void write(byte b[], int off, int len)
    {
        if(off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
            throw new IndexOutOfBoundsException();
        if(len == 0)
            return;
        synchronized(this)
        {
            int newcount = count + len;
            int remaining = len;
            int inBufferPos = count - filledBufferSum;
            do
            {
                if(remaining <= 0)
                    break;
                int part = Math.min(remaining, currentBuffer.length - inBufferPos);
                System.arraycopy(b, (off + len) - remaining, currentBuffer, inBufferPos, part);
                remaining -= part;
                if(remaining > 0)
                {
                    needNewBuffer(newcount);
                    inBufferPos = 0;
                }
            } while(true);
            count = newcount;
        }
    }

    public synchronized void write(int b)
    {
        int inBufferPos = count - filledBufferSum;
        if(inBufferPos == currentBuffer.length)
        {
            needNewBuffer(count + 1);
            inBufferPos = 0;
        }
        currentBuffer[inBufferPos] = (byte)b;
        count++;
    }

    public synchronized int write(InputStream in)
        throws IOException
    {
        int readCount = 0;
        int inBufferPos = count - filledBufferSum;
        for(int n = in.read(currentBuffer, inBufferPos, currentBuffer.length - inBufferPos); n != -1; n = in.read(currentBuffer, inBufferPos, currentBuffer.length - inBufferPos))
        {
            readCount += n;
            inBufferPos += n;
            count += n;
            if(inBufferPos == currentBuffer.length)
            {
                needNewBuffer(currentBuffer.length);
                inBufferPos = 0;
            }
        }

        return readCount;
    }

    public synchronized int size()
    {
        return count;
    }

    public void close()
        throws IOException
    {
    }

    public synchronized void reset()
    {
        count = 0;
        filledBufferSum = 0;
        currentBufferIndex = 0;
        currentBuffer = (byte[])buffers.get(currentBufferIndex);
    }

    public synchronized void writeTo(OutputStream out)
        throws IOException
    {
        int remaining = count;
        Iterator i$ = buffers.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            byte buf[] = (byte[])i$.next();
            int c = Math.min(buf.length, remaining);
            out.write(buf, 0, c);
            remaining -= c;
        } while(remaining != 0);
    }

    public static InputStream toBufferedInputStream(InputStream input)
        throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(input);
        return output.toBufferedInputStream();
    }

    private InputStream toBufferedInputStream()
    {
        int remaining = count;
        if(remaining == 0)
            return new ClosedInputStream();
        List list = new ArrayList(buffers.size());
        Iterator i$ = buffers.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            byte buf[] = (byte[])i$.next();
            int c = Math.min(buf.length, remaining);
            list.add(new ByteArrayInputStream(buf, 0, c));
            remaining -= c;
        } while(remaining != 0);
        return new SequenceInputStream(Collections.enumeration(list));
    }

    public synchronized byte[] toByteArray()
    {
        int remaining = count;
        if(remaining == 0)
            return EMPTY_BYTE_ARRAY;
        byte newbuf[] = new byte[remaining];
        int pos = 0;
        Iterator i$ = buffers.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            byte buf[] = (byte[])i$.next();
            int c = Math.min(buf.length, remaining);
            System.arraycopy(buf, 0, newbuf, pos, c);
            pos += c;
            remaining -= c;
        } while(remaining != 0);
        return newbuf;
    }

    public String toString()
    {
        return new String(toByteArray());
    }

    public String toString(String enc)
        throws UnsupportedEncodingException
    {
        return new String(toByteArray(), enc);
    }

    private static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    private final List buffers;
    private int currentBufferIndex;
    private int filledBufferSum;
    private byte currentBuffer[];
    private int count;

}
