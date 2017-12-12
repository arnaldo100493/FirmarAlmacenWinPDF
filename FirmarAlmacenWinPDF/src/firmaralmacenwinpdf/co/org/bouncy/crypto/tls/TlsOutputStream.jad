// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsOutputStream.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsProtocol

class TlsOutputStream extends OutputStream
{

    TlsOutputStream(TlsProtocol handler)
    {
        buf = new byte[1];
        this.handler = handler;
    }

    public void write(byte buf[], int offset, int len)
        throws IOException
    {
        handler.writeData(buf, offset, len);
    }

    public void write(int arg0)
        throws IOException
    {
        buf[0] = (byte)arg0;
        write(buf, 0, 1);
    }

    public void close()
        throws IOException
    {
        handler.close();
    }

    public void flush()
        throws IOException
    {
        handler.flush();
    }

    private byte buf[];
    private TlsProtocol handler;
}
