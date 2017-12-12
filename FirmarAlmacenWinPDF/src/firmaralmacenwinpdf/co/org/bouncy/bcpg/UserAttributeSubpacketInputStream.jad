// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserAttributeSubpacketInputStream.java

package co.org.bouncy.bcpg;

import co.org.bouncy.bcpg.attr.ImageAttribute;
import java.io.*;

// Referenced classes of package co.org.bouncy.bcpg:
//            UserAttributeSubpacket, UserAttributeSubpacketTags

public class UserAttributeSubpacketInputStream extends InputStream
    implements UserAttributeSubpacketTags
{

    public UserAttributeSubpacketInputStream(InputStream in)
    {
        this.in = in;
    }

    public int available()
        throws IOException
    {
        return in.available();
    }

    public int read()
        throws IOException
    {
        return in.read();
    }

    private void readFully(byte buf[], int off, int len)
        throws IOException
    {
        if(len > 0)
        {
            int b = read();
            if(b < 0)
                throw new EOFException();
            buf[off] = (byte)b;
            off++;
            len--;
        }
        int l;
        for(; len > 0; len -= l)
        {
            l = in.read(buf, off, len);
            if(l < 0)
                throw new EOFException();
            off += l;
        }

    }

    public UserAttributeSubpacket readPacket()
        throws IOException
    {
        int l = read();
        int bodyLen = 0;
        if(l < 0)
            return null;
        if(l < 192)
            bodyLen = l;
        else
        if(l <= 223)
            bodyLen = (l - 192 << 8) + in.read() + 192;
        else
        if(l == 255)
            bodyLen = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        int tag = in.read();
        if(tag < 0)
            throw new EOFException("unexpected EOF reading user attribute sub packet");
        byte data[] = new byte[bodyLen - 1];
        readFully(data, 0, data.length);
        int type = tag;
        switch(type)
        {
        case 1: // '\001'
            return new ImageAttribute(data);
        }
        return new UserAttributeSubpacket(type, data);
    }

    InputStream in;
}
