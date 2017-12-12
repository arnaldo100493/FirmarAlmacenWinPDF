// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsInputStream.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsProtocol

class TlsInputStream extends InputStream
{

    TlsInputStream(TlsProtocol handler)
    {
        buf = new byte[1];
        this.handler = null;
        this.handler = handler;
    }

    public int read(byte buf[], int offset, int len)
        throws IOException
    {
        return handler.readApplicationData(buf, offset, len);
    }

    public int read()
        throws IOException
    {
        if(read(buf) < 0)
            return -1;
        else
            return buf[0] & 0xff;
    }

    public void close()
        throws IOException
    {
        handler.close();
    }

    private byte buf[];
    private TlsProtocol handler;
}
