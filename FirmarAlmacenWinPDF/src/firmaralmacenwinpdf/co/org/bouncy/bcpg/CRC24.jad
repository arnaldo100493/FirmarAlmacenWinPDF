// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRC24.java

package co.org.bouncy.bcpg;


public class CRC24
{

    public CRC24()
    {
        crc = 0xb704ce;
    }

    public void update(int b)
    {
        crc ^= b << 16;
        for(int i = 0; i < 8; i++)
        {
            crc <<= 1;
            if((crc & 0x1000000) != 0)
                crc ^= 0x1864cfb;
        }

    }

    public int getValue()
    {
        return crc;
    }

    public void reset()
    {
        crc = 0xb704ce;
    }

    private static final int CRC24_INIT = 0xb704ce;
    private static final int CRC24_POLY = 0x1864cfb;
    private int crc;
}
