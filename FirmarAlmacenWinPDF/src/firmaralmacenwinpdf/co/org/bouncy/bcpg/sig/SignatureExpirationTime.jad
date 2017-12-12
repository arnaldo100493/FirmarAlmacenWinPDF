// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureExpirationTime.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class SignatureExpirationTime extends SignatureSubpacket
{

    protected static byte[] timeToBytes(long t)
    {
        byte data[] = new byte[4];
        data[0] = (byte)(int)(t >> 24);
        data[1] = (byte)(int)(t >> 16);
        data[2] = (byte)(int)(t >> 8);
        data[3] = (byte)(int)t;
        return data;
    }

    public SignatureExpirationTime(boolean critical, byte data[])
    {
        super(3, critical, data);
    }

    public SignatureExpirationTime(boolean critical, long seconds)
    {
        super(3, critical, timeToBytes(seconds));
    }

    public long getTime()
    {
        long time = (long)(data[0] & 0xff) << 24 | (long)((data[1] & 0xff) << 16) | (long)((data[2] & 0xff) << 8) | (long)(data[3] & 0xff);
        return time;
    }
}
