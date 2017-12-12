// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CharSequenceInputStream.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class CharSequenceInputStream extends InputStream
{

    public CharSequenceInputStream(CharSequence s, Charset charset, int bufferSize)
    {
        encoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        bbuf = ByteBuffer.allocate(bufferSize);
        bbuf.flip();
        cbuf = CharBuffer.wrap(s);
        mark = -1;
    }

    public CharSequenceInputStream(CharSequence s, String charset, int bufferSize)
    {
        this(s, Charset.forName(charset), bufferSize);
    }

    public CharSequenceInputStream(CharSequence s, Charset charset)
    {
        this(s, charset, 2048);
    }

    public CharSequenceInputStream(CharSequence s, String charset)
    {
        this(s, charset, 2048);
    }

    private void fillBuffer()
        throws CharacterCodingException
    {
        bbuf.compact();
        CoderResult result = encoder.encode(cbuf, bbuf, true);
        if(result.isError())
            result.throwException();
        bbuf.flip();
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(b == null)
            throw new NullPointerException("Byte array is null");
        if(len < 0 || off + len > b.length)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Array Size=").append(b.length).append(", offset=").append(off).append(", length=").append(len).toString());
        if(len == 0)
            return 0;
        if(!bbuf.hasRemaining() && !cbuf.hasRemaining())
            return -1;
        int bytesRead = 0;
label0:
        do
        {
            do
            {
                if(len <= 0)
                    break label0;
                if(!bbuf.hasRemaining())
                    break;
                int chunk = Math.min(bbuf.remaining(), len);
                bbuf.get(b, off, chunk);
                off += chunk;
                len -= chunk;
                bytesRead += chunk;
            } while(true);
            fillBuffer();
        } while(bbuf.hasRemaining() || cbuf.hasRemaining());
        return bytesRead != 0 || cbuf.hasRemaining() ? bytesRead : -1;
    }

    public int read()
        throws IOException
    {
        do
        {
            if(bbuf.hasRemaining())
                return bbuf.get() & 0xff;
            fillBuffer();
        } while(bbuf.hasRemaining() || cbuf.hasRemaining());
        return -1;
    }

    public int read(byte b[])
        throws IOException
    {
        return read(b, 0, b.length);
    }

    public long skip(long n)
        throws IOException
    {
        int skipped;
        for(skipped = 0; n > 0L && cbuf.hasRemaining(); skipped++)
        {
            cbuf.get();
            n--;
        }

        return (long)skipped;
    }

    public int available()
        throws IOException
    {
        return cbuf.remaining();
    }

    public void close()
        throws IOException
    {
    }

    public synchronized void mark(int readlimit)
    {
        mark = cbuf.position();
    }

    public synchronized void reset()
        throws IOException
    {
        if(mark != -1)
        {
            cbuf.position(mark);
            mark = -1;
        }
    }

    public boolean markSupported()
    {
        return true;
    }

    private final CharsetEncoder encoder;
    private final CharBuffer cbuf;
    private final ByteBuffer bbuf;
    private int mark;
}
