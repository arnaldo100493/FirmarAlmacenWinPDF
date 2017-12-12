// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonNumberImpl.java

package org.glassfish.json;

import java.math.BigDecimal;

// Referenced classes of package org.glassfish.json:
//            JsonNumberImpl

private static final class JsonNumberImpl$JsonLongNumber extends JsonNumberImpl
{

    public boolean isIntegral()
    {
        return true;
    }

    public long longValue()
    {
        return num;
    }

    public long longValueExact()
    {
        return num;
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
        return Long.toString(num);
    }

    private final long num;
    private BigDecimal bigDecimal;

    JsonNumberImpl$JsonLongNumber(long num)
    {
        this.num = num;
    }
}
