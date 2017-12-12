// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSubpacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;
import java.io.OutputStream;

public class SignatureSubpacket
{

    protected SignatureSubpacket(int type, boolean critical, byte data[])
    {
        this.type = type;
        this.critical = critical;
        this.data = data;
    }

    public int getType()
    {
        return type;
    }

    public boolean isCritical()
    {
        return critical;
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
        if(critical)
            out.write(0x80 | type);
        else
            out.write(type);
        out.write(data);
    }

    int type;
    boolean critical;
    protected byte data[];
}
