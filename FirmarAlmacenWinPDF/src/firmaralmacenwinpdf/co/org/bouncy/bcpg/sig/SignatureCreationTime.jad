// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureCreationTime.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;
import java.util.Date;

public class SignatureCreationTime extends SignatureSubpacket
{

    protected static byte[] timeToBytes(Date date)
    {
        byte data[] = new byte[4];
        long t = date.getTime() / 1000L;
        data[0] = (byte)(int)(t >> 24);
        data[1] = (byte)(int)(t >> 16);
        data[2] = (byte)(int)(t >> 8);
        data[3] = (byte)(int)t;
        return data;
    }

    public SignatureCreationTime(boolean critical, byte data[])
    {
        super(2, critical, data);
    }

    public SignatureCreationTime(boolean critical, Date date)
    {
        super(2, critical, timeToBytes(date));
    }

    public Date getTime()
    {
        long time = (long)(data[0] & 0xff) << 24 | (long)((data[1] & 0xff) << 16) | (long)((data[2] & 0xff) << 8) | (long)(data[3] & 0xff);
        return new Date(time * 1000L);
    }
}
