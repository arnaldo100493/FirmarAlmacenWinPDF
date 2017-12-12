// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnicodeDetectingInputStream.java

package org.glassfish.json;

import java.io.*;
import java.nio.charset.Charset;
import javax.json.JsonException;

class UnicodeDetectingInputStream extends FilterInputStream
{

    UnicodeDetectingInputStream(InputStream is)
    {
        super(is);
    }

    Charset getCharset()
    {
        return charset;
    }

    private void fillBuf()
    {
        int b1;
        int b2;
        int b3;
        int b4;
        try
        {
            b1 = in.read();
            if(b1 == -1)
                return;
        }
        catch(IOException ioe)
        {
            throw new JsonException("I/O error while auto-detecting the encoding of stream", ioe);
        }
        b2 = in.read();
        if(b2 == -1)
        {
            bufLen = 1;
            buf[0] = (byte)b1;
            return;
        }
        b3 = in.read();
        if(b3 == -1)
        {
            bufLen = 2;
            buf[0] = (byte)b1;
            buf[1] = (byte)b2;
            return;
        }
        b4 = in.read();
        if(b4 == -1)
        {
            bufLen = 3;
            buf[0] = (byte)b1;
            buf[1] = (byte)b2;
            buf[2] = (byte)b3;
            return;
        }
        bufLen = 4;
        buf[0] = (byte)b1;
        buf[1] = (byte)b2;
        buf[2] = (byte)b3;
        buf[3] = (byte)b4;
    }

    private Charset detectEncoding()
    {
        fillBuf();
        if(bufLen < 2)
            throw new JsonException("Cannot auto-detect encoding, not enough chars");
        if(bufLen == 4)
        {
            if(buf[0] == 0 && buf[1] == 0 && buf[2] == -2 && buf[3] == -1)
            {
                curIndex = 4;
                return UTF_32BE;
            }
            if(buf[0] == -1 && buf[1] == -2 && buf[2] == 0 && buf[3] == 0)
            {
                curIndex = 4;
                return UTF_32LE;
            }
            if(buf[0] == -2 && buf[1] == -1)
            {
                curIndex = 2;
                return UTF_16BE;
            }
            if(buf[0] == -1 && buf[1] == -2)
            {
                curIndex = 2;
                return UTF_16LE;
            }
            if(buf[0] == -17 && buf[1] == -69 && buf[2] == -65)
            {
                curIndex = 3;
                return UTF_8;
            }
            if(buf[0] == 0 && buf[1] == 0 && buf[2] == 0)
                return UTF_32BE;
            if(buf[0] == 0 && buf[2] == 0)
                return UTF_16BE;
            if(buf[1] == 0 && buf[2] == 0 && buf[3] == 0)
                return UTF_32LE;
            if(buf[1] == 0 && buf[3] == 0)
                return UTF_16LE;
        }
        return UTF_8;
    }

    public int read()
        throws IOException
    {
        if(curIndex < bufLen)
            return buf[curIndex++];
        else
            return in.read();
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(curIndex < bufLen)
        {
            if(len == 0)
                return 0;
            if(off < 0 || len < 0 || len > b.length - off)
            {
                throw new IndexOutOfBoundsException();
            } else
            {
                int min = Math.min(bufLen - curIndex, len);
                System.arraycopy(buf, curIndex, b, off, min);
                curIndex += min;
                return min;
            }
        } else
        {
            return in.read(b, off, len);
        }
    }

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    private static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    private static final Charset UTF_32LE = Charset.forName("UTF-32LE");
    private static final Charset UTF_32BE = Charset.forName("UTF-32BE");
    private static final byte FF = -1;
    private static final byte FE = -2;
    private static final byte EF = -17;
    private static final byte BB = -69;
    private static final byte BF = -65;
    private static final byte NUL = 0;
    private final byte buf[] = new byte[4];
    private int bufLen;
    private int curIndex;
    private final Charset charset = detectEncoding();

}
