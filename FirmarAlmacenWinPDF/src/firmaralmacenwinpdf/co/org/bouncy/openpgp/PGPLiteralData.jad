// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPLiteralData.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGInputStream;
import co.org.bouncy.bcpg.LiteralDataPacket;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class PGPLiteralData
{

    public PGPLiteralData(BCPGInputStream pIn)
        throws IOException
    {
        data = (LiteralDataPacket)pIn.readPacket();
    }

    public int getFormat()
    {
        return data.getFormat();
    }

    public String getFileName()
    {
        return data.getFileName();
    }

    public byte[] getRawFileName()
    {
        return data.getRawFileName();
    }

    public Date getModificationTime()
    {
        return new Date(data.getModificationTime());
    }

    public InputStream getInputStream()
    {
        return data.getInputStream();
    }

    public InputStream getDataStream()
    {
        return getInputStream();
    }

    public static final char BINARY = 98;
    public static final char TEXT = 116;
    public static final char UTF8 = 117;
    public static final String CONSOLE = "_CONSOLE";
    public static final Date NOW = new Date(0L);
    LiteralDataPacket data;

}
