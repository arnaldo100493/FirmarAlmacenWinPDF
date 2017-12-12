// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonNumberImpl.java

package org.glassfish.json;

import java.math.BigDecimal;

// Referenced classes of package org.glassfish.json:
//            JsonNumberImpl

private static final class JsonNumberImpl$JsonIntNumber extends JsonNumberImpl
{

    public boolean isIntegral()
    {
        return true;
    }

    public int intValue()
    {
        return num;
    }

    public int intValueExact()
    {
        return num;
    }

    public long longValue()
    {
        return (long)num;
    }

    public long longValueExact()
    {
        return (long)num;
    }

    public double doubleValue()
    {
        return (double)num;
    }

    public BigDecimal bigDecimalValue()
    {
        BigDecimal bd = bigDecimal;
        if(bd == null)
            bigDecimal = bd = new BigDecimal(num);
        return bd;
    }

    public String toString()
    {
        return Integer.toString(num);
    }

    private final int num;
    private BigDecimal bigDecimal;

    JsonNumberImpl$JsonIntNumber(int num)
    {
        this.num = num;
    }
}
