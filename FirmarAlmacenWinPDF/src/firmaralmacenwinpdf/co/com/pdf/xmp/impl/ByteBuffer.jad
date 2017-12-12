// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteBuffer.java

package co.com.pdf.xmp.impl;

import java.io.*;

public class ByteBuffer
{

    public ByteBuffer(int initialCapacity)
    {
        encoding = null;
        buffer = new byte[initialCapacity];
        length = 0;
    }

    public ByteBuffer(byte buffer[])
    {
        encoding = null;
        this.buffer = buffer;
        length = buffer.length;
    }

    public ByteBuffer(byte buffer[], int length)
    {
        encoding = null;
        if(length > buffer.length)
        {
            throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
        } else
        {
            this.buffer = buffer;
            this.length = length;
            return;
        }
    }

    public ByteBuffer(InputStream in)
        throws IOException
    {
        encoding = null;
        int chunk = 16384;
        length = 0;
        buffer = new byte[chunk];
        do
        {
            int read;
            if((read = in.read(buffer, length, chunk)) <= 0)
                break;
            length += read;
            if(read != chunk)
                break;
            ensureCapacity(length + chunk);
        } while(true);
    }

    public ByteBuffer(byte buffer[], int offset, int length)
    {
        encoding = null;
        if(length > buffer.length - offset)
        {
            throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
        } else
        {
            this.buffer = new byte[length];
            System.arraycopy(buffer, offset, this.buffer, 0, length);
            this.length = length;
            return;
        }
    }

    public InputStream getByteStream()
    {
        return new ByteArrayInputStream(buffer, 0, length);
    }

    public int length()
    {
        return length;
    }

    public byte byteAt(int index)
    {
        if(index < length)
            return buffer[index];
        else
            throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public int charAt(int index)
    {
        if(index < length)
            return buffer[index] & 0xff;
        else
            throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public void append(byte b)
    {
        ensureCapacity(length + 1);
        buffer[length++] = b;
    }

    public void append(byte bytes[], int offset, int len)
    {
        ensureCapacity(length + len);
        System.arraycopy(bytes, offset, buffer, length, len);
        length += len;
    }

    public void append(byte bytes[])
    {
        append(bytes, 0, bytes.length);
    }

    public void append(ByteBuffer anotherBuffer)
    {
        append(anotherBuffer.buffer, 0, anotherBuffer.length);
    }

    public String getEncoding()
    {
        if(encoding == null)
            if(length < 2)
                encoding = "UTF-8";
            else
            if(buffer[0] == 0)
            {
                if(length < 4 || buffer[1] != 0)
                    encoding = "UTF-16BE";
                else
                if((buffer[2] & 0xff) == 254 && (buffer[3] & 0xff) == 255)
                    encoding = "UTF-32BE";
                else
                    encoding = "UTF-32";
            } else
            if((buffer[0] & 0xff) < 128)
            {
                if(buffer[1] != 0)
                    encoding = "UTF-8";
                else
                if(length < 4 || buffer[2] != 0)
                    encoding = "UTF-16LE";
                else
                    encoding = "UTF-32LE";
            } else
            if((buffer[0] & 0xff) == 239)
                encoding = "UTF-8";
            else
            if((buffer[0] & 0xff) == 254)
                encoding = "UTF-16";
            else
            if(length < 4 || buffer[2] != 0)
                encoding = "UTF-16";
            else
                encoding = "UTF-32";
        return encoding;
    }

    private void ensureCapacity(int requestedLength)
    {
        if(requestedLength > buffer.length)
        {
            byte oldBuf[] = buffer;
            buffer = new byte[oldBuf.length * 2];
            System.arraycopy(oldBuf, 0, buffer, 0, oldBuf.length);
        }
    }

    private byte buffer[];
    private int length;
    private String encoding;
}
