// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MacInputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.Mac;
import java.io.*;

public class MacInputStream extends FilterInputStream
{

    public MacInputStream(InputStream stream, Mac mac)
    {
        super(stream);
        this.mac = mac;
    }

    public int read()
        throws IOException
    {
        int b = in.read();
        if(b >= 0)
            mac.update((byte)b);
        return b;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        int n = in.read(b, off, len);
        if(n >= 0)
            mac.update(b, off, n);
        return n;
    }

    public Mac getMac()
    {
        return mac;
    }

    protected Mac mac;
}
