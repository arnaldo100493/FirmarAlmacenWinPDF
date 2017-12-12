// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrayRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

class ArrayRandomAccessSource
    implements RandomAccessSource
{

    public ArrayRandomAccessSource(byte array[])
    {
        if(array == null)
        {
            throw new NullPointerException();
        } else
        {
            this.array = array;
            return;
        }
    }

    public int get(long offset)
    {
        if(offset >= (long)array.length)
            return -1;
        else
            return 0xff & array[(int)offset];
    }

    public int get(long offset, byte bytes[], int off, int len)
    {
        if(array == null)
            throw new IllegalStateException("Already closed");
        if(offset >= (long)array.length)
            return -1;
        if(offset + (long)len > (long)array.length)
            len = (int)((long)array.length - offset);
        System.arraycopy(array, (int)offset, bytes, off, len);
        return len;
    }

    public long length()
    {
        return (long)array.length;
    }

    public void close()
        throws IOException
    {
        array = null;
    }

    private byte array[];
}
