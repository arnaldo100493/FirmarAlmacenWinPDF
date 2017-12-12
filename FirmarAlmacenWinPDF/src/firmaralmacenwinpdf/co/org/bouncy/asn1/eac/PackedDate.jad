// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackedDate.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.util.Arrays;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class PackedDate
{

    public PackedDate(String time)
    {
        this.time = convert(time);
    }

    public PackedDate(Date time)
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyMMdd'Z'");
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = convert(dateF.format(time));
    }

    private byte[] convert(String sTime)
    {
        char digs[] = sTime.toCharArray();
        byte date[] = new byte[6];
        for(int i = 0; i != 6; i++)
            date[i] = (byte)(digs[i] - 48);

        return date;
    }

    PackedDate(byte bytes[])
    {
        time = bytes;
    }

    public Date getDate()
        throws ParseException
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
        return dateF.parse((new StringBuilder()).append("20").append(toString()).toString());
    }

    public int hashCode()
    {
        return Arrays.hashCode(time);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof PackedDate))
        {
            return false;
        } else
        {
            PackedDate other = (PackedDate)o;
            return Arrays.areEqual(time, other.time);
        }
    }

    public String toString()
    {
        char dateC[] = new char[time.length];
        for(int i = 0; i != dateC.length; i++)
            dateC[i] = (char)((time[i] & 0xff) + 48);

        return new String(dateC);
    }

    public byte[] getEncoding()
    {
        return time;
    }

    private byte time[];
}
