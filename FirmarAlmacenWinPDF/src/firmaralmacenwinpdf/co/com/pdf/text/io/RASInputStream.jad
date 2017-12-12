// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RASInputStream.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

public class RASInputStream extends InputStream
{

    public RASInputStream(RandomAccessSource source)
    {
        position = 0L;
        this.source = source;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        int count = source.get(position, b, off, len);
        position += count;
        return count;
    }

    public int read()
        throws IOException
    {
        return source.get(position++);
    }

    private final RandomAccessSource source;
    private long position;
}
