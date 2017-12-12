// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCPGInputStream.java

package co.org.bouncy.bcpg;

import java.io.*;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGInputStream

private static class BCPGInputStream$PartialInputStream extends InputStream
{

    public int available()
        throws IOException
    {
        int avail = in.available();
        if(avail <= dataLength || dataLength < 0)
            return avail;
        if(partial && dataLength == 0)
            return 1;
        else
            return dataLength;
    }

    private int loadDataLength()
        throws IOException
    {
        int l = in.read();
        if(l < 0)
            return -1;
        partial = false;
        if(l < 192)
            dataLength = l;
        else
        if(l <= 223)
            dataLength = (l - 192 << 8) + in.read() + 192;
        else
        if(l == 255)
        {
            dataLength = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        } else
        {
            partial = true;
            dataLength = 1 << (l & 0x1f);
        }
        return dataLength;
    }

    public int read(byte buf[], int offset, int len)
        throws IOException
    {
        do
            if(dataLength != 0)
            {
                int readLen = dataLength <= len && dataLength >= 0 ? dataLength : len;
                readLen = in.read(buf, offset, readLen);
                if(readLen < 0)
                {
                    throw new EOFException("premature end of stream in PartialInputStream");
                } else
                {
                    dataLength -= readLen;
                    return readLen;
                }
            }
        while(partial && loadDataLength() >= 0);
        return -1;
    }

    public int read()
        throws IOException
    {
        do
            if(dataLength != 0)
            {
                int ch = in.read();
                if(ch < 0)
                {
                    throw new EOFException("premature end of stream in PartialInputStream");
                } else
                {
                    dataLength--;
                    return ch;
                }
            }
        while(partial && loadDataLength() >= 0);
        return -1;
    }

    private BCPGInputStream in;
    private boolean partial;
    private int dataLength;

    BCPGInputStream$PartialInputStream(BCPGInputStream in, boolean partial, int dataLength)
    {
        this.in = in;
        this.partial = partial;
        this.dataLength = dataLength;
    }
}
