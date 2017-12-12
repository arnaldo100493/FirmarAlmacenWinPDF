// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPDateTimeImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPDateTime;
import co.com.pdf.xmp.XMPException;
import java.util.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            ISO8601Converter

public class XMPDateTimeImpl
    implements XMPDateTime
{

    public XMPDateTimeImpl()
    {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
        timeZone = null;
        hasDate = false;
        hasTime = false;
        hasTimeZone = false;
    }

    public XMPDateTimeImpl(Calendar calendar)
    {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
        timeZone = null;
        hasDate = false;
        hasTime = false;
        hasTimeZone = false;
        Date date = calendar.getTime();
        TimeZone zone = calendar.getTimeZone();
        GregorianCalendar intCalendar = (GregorianCalendar)Calendar.getInstance(Locale.US);
        intCalendar.setGregorianChange(new Date(0x8000000000000000L));
        intCalendar.setTimeZone(zone);
        intCalendar.setTime(date);
        year = intCalendar.get(1);
        month = intCalendar.get(2) + 1;
        day = intCalendar.get(5);
        hour = intCalendar.get(11);
        minute = intCalendar.get(12);
        second = intCalendar.get(13);
        nanoSeconds = intCalendar.get(14) * 0xf4240;
        timeZone = intCalendar.getTimeZone();
        hasDate = hasTime = hasTimeZone = true;
    }

    public XMPDateTimeImpl(Date date, TimeZone timeZone)
    {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
        this.timeZone = null;
        hasDate = false;
        hasTime = false;
        hasTimeZone = false;
        GregorianCalendar calendar = new GregorianCalendar(timeZone);
        calendar.setTime(date);
        year = calendar.get(1);
        month = calendar.get(2) + 1;
        day = calendar.get(5);
        hour = calendar.get(11);
        minute = calendar.get(12);
        second = calendar.get(13);
        nanoSeconds = calendar.get(14) * 0xf4240;
        this.timeZone = timeZone;
        hasDate = hasTime = hasTimeZone = true;
    }

    public XMPDateTimeImpl(String strValue)
        throws XMPException
    {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
        timeZone = null;
        hasDate = false;
        hasTime = false;
        hasTimeZone = false;
        ISO8601Converter.parse(strValue, this);
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = Math.min(Math.abs(year), 9999);
        hasDate = true;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        if(month < 1)
            this.month = 1;
        else
        if(month > 12)
            this.month = 12;
        else
            this.month = month;
        hasDate = true;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        if(day < 1)
            this.day = 1;
        else
        if(day > 31)
            this.day = 31;
        else
            this.day = day;
        hasDate = true;
    }

    public int getHour()
    {
        return hour;
    }

    public void setHour(int hour)
    {
        this.hour = Math.min(Math.abs(hour), 23);
        hasTime = true;
    }

    public int getMinute()
    {
        return minute;
    }

    public void setMinute(int minute)
    {
        this.minute = Math.min(Math.abs(minute), 59);
        hasTime = true;
    }

    public int getSecond()
    {
        return second;
    }

    public void setSecond(int second)
    {
        this.second = Math.min(Math.abs(second), 59);
        hasTime = true;
    }

    public int getNanoSecond()
    {
        return nanoSeconds;
    }

    public void setNanoSecond(int nanoSecond)
    {
        nanoSeconds = nanoSecond;
        hasTime = true;
    }

    public int compareTo(Object dt)
    {
        long d = getCalendar().getTimeInMillis() - ((XMPDateTime)dt).getCalendar().getTimeInMillis();
        if(d != 0L)
        {
            return (int)Math.signum(d);
        } else
        {
            d = nanoSeconds - ((XMPDateTime)dt).getNanoSecond();
            return (int)Math.signum(d);
        }
    }

    public TimeZone getTimeZone()
    {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone)
    {
        this.timeZone = timeZone;
        hasTime = true;
        hasTimeZone = true;
    }

    public boolean hasDate()
    {
        return hasDate;
    }

    public boolean hasTime()
    {
        return hasTime;
    }

    public boolean hasTimeZone()
    {
        return hasTimeZone;
    }

    public Calendar getCalendar()
    {
        GregorianCalendar calendar = (GregorianCalendar)Calendar.getInstance(Locale.US);
        calendar.setGregorianChange(new Date(0x8000000000000000L));
        if(hasTimeZone)
            calendar.setTimeZone(timeZone);
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, day);
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        calendar.set(14, nanoSeconds / 0xf4240);
        return calendar;
    }

    public String getISO8601String()
    {
        return ISO8601Converter.render(this);
    }

    public String toString()
    {
        return getISO8601String();
    }

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private TimeZone timeZone;
    private int nanoSeconds;
    private boolean hasDate;
    private boolean hasTime;
    private boolean hasTimeZone;
}
