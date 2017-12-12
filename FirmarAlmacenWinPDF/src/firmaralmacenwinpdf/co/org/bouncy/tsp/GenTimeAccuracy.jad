// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenTimeAccuracy.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.DERInteger;
import co.org.bouncy.asn1.tsp.Accuracy;
import java.math.BigInteger;

public class GenTimeAccuracy
{

    public GenTimeAccuracy(Accuracy accuracy)
    {
        this.accuracy = accuracy;
    }

    public int getSeconds()
    {
        return getTimeComponent(accuracy.getSeconds());
    }

    public int getMillis()
    {
        return getTimeComponent(accuracy.getMillis());
    }

    public int getMicros()
    {
        return getTimeComponent(accuracy.getMicros());
    }

    private int getTimeComponent(DERInteger time)
    {
        if(time != null)
            return time.getValue().intValue();
        else
            return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getSeconds()).append(".").append(format(getMillis())).append(format(getMicros())).toString();
    }

    private String format(int v)
    {
        if(v < 10)
            return (new StringBuilder()).append("00").append(v).toString();
        if(v < 100)
            return (new StringBuilder()).append("0").append(v).toString();
        else
            return Integer.toString(v);
    }

    private Accuracy accuracy;
}
