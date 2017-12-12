// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherInputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.StreamCipher;
import java.io.*;

public class CipherInputStream extends FilterInputStream
{

    public CipherInputStream(InputStream is, BufferedBlockCipher cipher)
    {
        super(is);
        bufferedBlockCipher = cipher;
        buf = new byte[cipher.getOutputSize(2048)];
        inBuf = new byte[2048];
    }

    public CipherInputStream(InputStream is, StreamCipher cipher)
    {
        super(is);
        streamCipher = cipher;
        buf = new byte[2048];
        inBuf = new byte[2048];
    }

    private int nextChunk()
        throws IOException
    {
        int available = super.available();
        if(available <= 0)
            available = 1;
        if(available > inBuf.length)
            available = super.read(inBuf, 0, inBuf.length);
        else
            available = super.read(inBuf, 0, available);
        if(available < 0)
        {
            if(finalized)
                return -1;
            try
            {
                if(bufferedBlockCipher != null)
                    maxBuf = bufferedBlockCipher.doFinal(buf, 0);
                else
                    maxBuf = 0;
            }
            catch(Exception e)
            {
                throw new IOException((new StringBuilder()).append("error processing stream: ").append(e.toString()).toString());
            }
            bufOff = 0;
            finalized = true;
            if(bufOff == maxBuf)
                return -1;
        } else
        {
            bufOff = 0;
            try
            {
                if(bufferedBlockCipher != null)
                {
                    maxBuf = bufferedBlockCipher.processBytes(inBuf, 0, available, buf, 0);
                } else
                {
                    streamCipher.processBytes(inBuf, 0, available, buf, 0);
                    maxBuf = available;
                }
            }
            catch(Exception e)
            {
                throw new IOException((new StringBuilder()).append("error processing stream: ").append(e.toString()).toString());
            }
            if(maxBuf == 0)
                return nextChunk();
        }
        return maxBuf;
    }

    public int read()
        throws IOException
    {
        if(bufOff == maxBuf && nextChunk() < 0)
            return -1;
        else
            return buf[bufOff++] & 0xff;
    }

    public int read(byte b[])
        throws IOException
    {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(bufOff == maxBuf && nextChunk() < 0)
            return -1;
        int available = maxBuf - bufOff;
        if(len > available)
        {
            System.arraycopy(buf, bufOff, b, off, available);
            bufOff = maxBuf;
            return available;
        } else
        {
            System.arraycopy(buf, bufOff, b, off, len);
            bufOff += len;
            return len;
        }
    }

    public long skip(long n)
        throws IOException
    {
        if(n <= 0L)
            return 0L;
        int available = maxBuf - bufOff;
        if(n > (long)available)
        {
            bufOff = maxBuf;
            return (long)available;
        } else
        {
            bufOff += (int)n;
            return (long)(int)n;
        }
    }

    public int available()
        throws IOException
    {
        return maxBuf - bufOff;
    }

    public void close()
        throws IOException
    {
        super.close();
    }

    public boolean markSupported()
    {
        return false;
    }

    private BufferedBlockCipher bufferedBlockCipher;
    private StreamCipher streamCipher;
    private byte buf[];
    private byte inBuf[];
    private int bufOff;
    private int maxBuf;
    private boolean finalized;
    private static final int INPUT_BUF_SIZE = 2048;
}
