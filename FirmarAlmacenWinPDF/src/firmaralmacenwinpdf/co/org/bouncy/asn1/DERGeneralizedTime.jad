// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERGeneralizedTime.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1GeneralizedTime, ASN1OctetString, ASN1TaggedObject, 
//            StreamUtil, ASN1OutputStream

public class DERGeneralizedTime extends ASN1Primitive
{

    public static ASN1GeneralizedTime getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1GeneralizedTime))
            return (ASN1GeneralizedTime)obj;
        if(obj instanceof DERGeneralizedTime)
            return new ASN1GeneralizedTime(((DERGeneralizedTime)obj).time);
        if(obj instanceof byte[])
            try
            {
                return (ASN1GeneralizedTime)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERGeneralizedTime))
            return getInstance(o);
        else
            return new ASN1GeneralizedTime(((ASN1OctetString)o).getOctets());
    }

    public DERGeneralizedTime(String time)
    {
        this.time = Strings.toByteArray(time);
        try
        {
            getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid date string: ").append(e.getMessage()).toString());
        }
    }

    public DERGeneralizedTime(Date time)
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(dateF.format(time));
    }

    DERGeneralizedTime(byte bytes[])
    {
        time = bytes;
    }

    public String getTimeString()
    {
        return Strings.fromByteArray(time);
    }

    public String getTime()
    {
        String stime = Strings.fromByteArray(time);
        if(stime.charAt(stime.length() - 1) == 'Z')
            return (new StringBuilder()).append(stime.substring(0, stime.length() - 1)).append("GMT+00:00").toString();
        int signPos = stime.length() - 5;
        char sign = stime.charAt(signPos);
        if(sign == '-' || sign == '+')
            return (new StringBuilder()).append(stime.substring(0, signPos)).append("GMT").append(stime.substring(signPos, signPos + 3)).append(":").append(stime.substring(signPos + 3)).toString();
        signPos = stime.length() - 3;
        sign = stime.charAt(signPos);
        if(sign == '-' || sign == '+')
            return (new StringBuilder()).append(stime.substring(0, signPos)).append("GMT").append(stime.substring(signPos)).append(":00").toString();
        else
            return (new StringBuilder()).append(stime).append(calculateGMTOffset()).toString();
    }

    private String calculateGMTOffset()
    {
        String sign = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getRawOffset();
        if(offset < 0)
        {
            sign = "-";
            offset = -offset;
        }
        int hours = offset / 0x36ee80;
        int minutes = (offset - hours * 60 * 60 * 1000) / 60000;
        try
        {
            if(timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate()))
                hours += sign.equals("+") ? 1 : -1;
        }
        catch(ParseException e) { }
        return (new StringBuilder()).append("GMT").append(sign).append(convert(hours)).append(":").append(convert(minutes)).toString();
    }

    private String convert(int time)
    {
        if(time < 10)
            return (new StringBuilder()).append("0").append(time).toString();
        else
            return Integer.toString(time);
    }

    public Date getDate()
        throws ParseException
    {
        String stime = Strings.fromByteArray(time);
        String d = stime;
        SimpleDateFormat dateF;
        if(stime.endsWith("Z"))
        {
            if(hasFractionalSeconds())
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            else
                dateF = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else
        if(stime.indexOf('-') > 0 || stime.indexOf('+') > 0)
        {
            d = getTime();
            if(hasFractionalSeconds())
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            else
                dateF = new SimpleDateFormat("yyyyMMddHHmmssz");
            dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else
        {
            if(hasFractionalSeconds())
                dateF = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            else
                dateF = new SimpleDateFormat("yyyyMMddHHmmss");
            dateF.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if(hasFractionalSeconds())
        {
            String frac = d.substring(14);
            int index = 1;
            do
            {
                if(index >= frac.length())
                    break;
                char ch = frac.charAt(index);
                if('0' > ch || ch > '9')
                    break;
                index++;
            } while(true);
            if(index - 1 > 3)
            {
                frac = (new StringBuilder()).append(frac.substring(0, 4)).append(frac.substring(index)).toString();
                d = (new StringBuilder()).append(d.substring(0, 14)).append(frac).toString();
            } else
            if(index - 1 == 1)
            {
                frac = (new StringBuilder()).append(frac.substring(0, index)).append("00").append(frac.substring(index)).toString();
                d = (new StringBuilder()).append(d.substring(0, 14)).append(frac).toString();
            } else
            if(index - 1 == 2)
            {
                frac = (new StringBuilder()).append(frac.substring(0, index)).append("0").append(frac.substring(index)).toString();
                d = (new StringBuilder()).append(d.substring(0, 14)).append(frac).toString();
            }
        }
        return dateF.parse(d);
    }

    private boolean hasFractionalSeconds()
    {
        for(int i = 0; i != time.length; i++)
            if(time[i] == 46 && i == 14)
                return true;

        return false;
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        int length = time.length;
        return 1 + StreamUtil.calculateBodyLength(length) + length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(24, time);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERGeneralizedTime))
            return false;
        else
            return Arrays.areEqual(time, ((DERGeneralizedTime)o).time);
    }

    public int hashCode()
    {
        return Arrays.hashCode(time);
    }

    private byte time[];
}
