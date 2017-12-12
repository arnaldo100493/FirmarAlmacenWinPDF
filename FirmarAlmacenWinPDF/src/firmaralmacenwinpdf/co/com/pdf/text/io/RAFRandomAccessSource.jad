// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RAFRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.io.RandomAccessFile;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

class RAFRandomAccessSource
    implements RandomAccessSource
{

    public RAFRandomAccessSource(RandomAccessFile raf)
        throws IOException
    {
        this.raf = raf;
        length = raf.length();
    }

    public int get(long position)
        throws IOException
    {
        if(position > raf.length())
        {
            return -1;
        } else
        {
            raf.seek(position);
            return raf.read();
        }
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        if(position > length)
        {
            return -1;
        } else
        {
            raf.seek(position);
            return raf.read(bytes, off, len);
        }
    }

    public long length()
    {
        return length;
    }

    public void close()
        throws IOException
    {
        raf.close();
    }

    private final RandomAccessFile raf;
    private final long length;
}
