// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MacOutputStream.java

package co.org.bouncy.crypto.io;

import co.org.bouncy.crypto.Mac;
import java.io.IOException;
import java.io.OutputStream;

public class MacOutputStream extends OutputStream
{

    public MacOutputStream(Mac mac)
    {
        this.mac = mac;
    }

    public void write(int b)
        throws IOException
    {
        mac.update((byte)b);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        mac.update(b, off, len);
    }

    public byte[] getMac()
    {
        byte res[] = new byte[mac.getMacSize()];
        mac.doFinal(res, 0);
        return res;
    }

    protected Mac mac;
}
