// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserAttributeSubpacket.java

package co.org.bouncy.bcpg;

import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.io.OutputStream;

public class UserAttributeSubpacket
{

    protected UserAttributeSubpacket(int type, byte data[])
    {
        this.type = type;
        this.data = data;
    }

    public int getType()
    {
        return type;
    }

    public byte[] getData()
    {
        return data;
    }

    public void encode(OutputStream out)
        throws IOException
    {
        int bodyLen = data.length + 1;
        if(bodyLen < 192)
            out.write((byte)bodyLen);
        else
        if(bodyLen <= 8383)
        {
            bodyLen -= 192;
            out.write((byte)((bodyLen >> 8 & 0xff) + 192));
            out.write((byte)bodyLen);
        } else
        {
            out.write(255);
            out.write((byte)(bodyLen >> 24));
            out.write((byte)(bodyLen >> 16));
            out.write((byte)(bodyLen >> 8));
            out.write((byte)bodyLen);
        }
        out.write(type);
        out.write(data);
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof UserAttributeSubpacket))
        {
            return false;
        } else
        {
            UserAttributeSubpacket other = (UserAttributeSubpacket)o;
            return type == other.type && Arrays.areEqual(data, other.data);
        }
    }

    public int hashCode()
    {
        return type ^ Arrays.hashCode(data);
    }

    int type;
    protected byte data[];
}
