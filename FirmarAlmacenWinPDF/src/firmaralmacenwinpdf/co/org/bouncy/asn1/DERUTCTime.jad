// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERUTCTime.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1UTCTime, ASN1OctetString, ASN1Object, 
//            ASN1TaggedObject, StreamUtil, ASN1OutputStream

public class DERUTCTime extends ASN1Primitive
{

    public static ASN1UTCTime getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1UTCTime))
            return (ASN1UTCTime)obj;
        if(obj instanceof DERUTCTime)
            return new ASN1UTCTime(((DERUTCTime)obj).time);
        if(obj instanceof byte[])
            try
            {
                return (ASN1UTCTime)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1UTCTime getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Object o = obj.getObject();
        if(explicit || (o instanceof ASN1UTCTime))
            return getInstance(o);
        else
            return new ASN1UTCTime(((ASN1OctetString)o).getOctets());
    }

    public DERUTCTime(String time)
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

    public DERUTCTime(Date time)
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyMMddHHmmss'Z'");
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(dateF.format(time));
    }

    DERUTCTime(byte time[])
    {
        this.time = time;
    }

    public Date getDate()
        throws ParseException
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyMMddHHmmssz");
        return dateF.parse(getTime());
    }

    public Date getAdjustedDate()
        throws ParseException
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmssz");
        dateF.setTimeZone(new SimpleTimeZone(0, "Z"));
        return dateF.parse(getAdjustedTime());
    }

    public String getTime()
    {
        String stime = Strings.fromByteArray(time);
        if(stime.indexOf('-') < 0 && stime.indexOf('+') < 0)
            if(stime.length() == 11)
                return (new StringBuilder()).append(stime.substring(0, 10)).append("00GMT+00:00").toString();
            else
                return (new StringBuilder()).append(stime.substring(0, 12)).append("GMT+00:00").toString();
        int index = stime.indexOf('-');
        if(index < 0)
            index = stime.indexOf('+');
        String d = stime;
        if(index == stime.length() - 3)
            d = (new StringBuilder()).append(d).append("00").toString();
        if(index == 10)
            return (new StringBuilder()).append(d.substring(0, 10)).append("00GMT").append(d.substring(10, 13)).append(":").append(d.substring(13, 15)).toString();
        else
            return (new StringBuilder()).append(d.substring(0, 12)).append("GMT").append(d.substring(12, 15)).append(":").append(d.substring(15, 17)).toString();
    }

    public String getAdjustedTime()
    {
        String d = getTime();
        if(d.charAt(0) < '5')
            return (new StringBuilder()).append("20").append(d).toString();
        else
            return (new StringBuilder()).append("19").append(d).toString();
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
        out.write(23);
        int length = time.length;
        out.writeLength(length);
        for(int i = 0; i != length; i++)
            out.write(time[i]);

    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERUTCTime))
            return false;
        else
            return Arrays.areEqual(time, ((DERUTCTime)o).time);
    }

    public int hashCode()
    {
        return Arrays.hashCode(time);
    }

    public String toString()
    {
        return Strings.fromByteArray(time);
    }

    private byte time[];
}
