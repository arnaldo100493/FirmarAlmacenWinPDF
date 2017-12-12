// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LiteralDataPacket.java

package co.org.bouncy.bcpg;

import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            InputStreamPacket, BCPGInputStream

public class LiteralDataPacket extends InputStreamPacket
{

    LiteralDataPacket(BCPGInputStream in)
        throws IOException
    {
        super(in);
        format = in.read();
        int l = in.read();
        fileName = new byte[l];
        for(int i = 0; i != fileName.length; i++)
            fileName[i] = (byte)in.read();

        modDate = (long)in.read() << 24 | (long)(in.read() << 16) | (long)(in.read() << 8) | (long)in.read();
    }

    public int getFormat()
    {
        return format;
    }

    public long getModificationTime()
    {
        return modDate * 1000L;
    }

    public String getFileName()
    {
        return Strings.fromUTF8ByteArray(fileName);
    }

    public byte[] getRawFileName()
    {
        byte tmp[] = new byte[fileName.length];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = fileName[i];

        return tmp;
    }

    int format;
    byte fileName[];
    long modDate;
}
