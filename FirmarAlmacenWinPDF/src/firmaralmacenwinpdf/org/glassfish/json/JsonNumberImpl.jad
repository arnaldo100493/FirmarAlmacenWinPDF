// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonNumberImpl.java

package org.glassfish.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonNumber;
import javax.json.JsonValue;

abstract class JsonNumberImpl
    implements JsonNumber
{
    private static final class JsonBigDecimalNumber extends JsonNumberImpl
    {

        public BigDecimal bigDecimalValue()
        {
            return bigDecimal;
        }

        private final BigDecimal bigDecimal;

        JsonBigDecimalNumber(BigDecimal value)
        {
            bigDecimal = value;
        }
    }

    private static final class JsonLongNumber extends JsonNumberImpl
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

        JsonLongNumber(long num)
        {
            this.num = num;
        }
    }

    private static final class JsonIntNumber extends JsonNumberImpl
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

        JsonIntNumber(int num)
        {
            this.num = num;
        }
    }


    JsonNumberImpl()
    {
    }

    static JsonNumber getJsonNumber(int num)
    {
        return new JsonIntNumber(num);
    }

    static JsonNumber getJsonNumber(long num)
    {
        return new JsonLongNumber(num);
    }

    static JsonNumber getJsonNumber(BigInteger value)
    {
        return new JsonBigDecimalNumber(new BigDecimal(value));
    }

    static JsonNumber getJsonNumber(double value)
    {
        return new JsonBigDecimalNumber(BigDecimal.valueOf(value));
    }

    static JsonNumber getJsonNumber(BigDecimal value)
    {
        return new JsonBigDecimalNumber(value);
    }

    public boolean isIntegral()
    {
        return bigDecimalValue().scale() == 0;
    }

    public int intValue()
    {
        return bigDecimalValue().intValue();
    }

    public int intValueExact()
    {
        return bigDecimalValue().intValueExact();
    }

    public long longValue()
    {
        return bigDecimalValue().longValue();
    }

    public long longValueExact()
    {
        return bigDecimalValue().longValueExact();
    }

    public double doubleValue()
    {
        return bigDecimalValue().doubleValue();
    }

    public BigInteger bigIntegerValue()
    {
        return bigDecimalValue().toBigInteger();
    }

    public BigInteger bigIntegerValueExact()
    {
        return bigDecimalValue().toBigIntegerExact();
    }

    public javax.json.JsonValue.ValueType getValueType()
    {
        return javax.json.JsonValue.ValueType.NUMBER;
    }

    public int hashCode()
    {
        return bigDecimalValue().hashCode();
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof JsonNumber))
        {
            return false;
        } else
        {
            JsonNumber other = (JsonNumber)obj;
            return bigDecimalValue().equals(other.bigDecimalValue());
        }
    }

    public String toString()
    {
        return bigDecimalValue().toString();
    }
}
