// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERInteger.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1Integer, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERInteger extends ASN1Primitive
{

    public static ASN1Integer getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1Integer))
            return (ASN1Integer)obj;
        if(obj instanceof DERInteger)
            return new ASN1Integer(((DERInteger)obj).getValue());
        if(obj instanceof byte[])
            try
            {
                return (ASN1Integer)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1Integer getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERInteger))
            return getInstance(o);
        else
            return new ASN1Integer(ASN1OctetString.getInstance(obj.getObject()).getOctets());
    }

    public DERInteger(long value)
    {
        bytes = BigInteger.valueOf(value).toByteArray();
    }

    public DERInteger(BigInteger value)
    {
        bytes = value.toByteArray();
    }

    public DERInteger(byte bytes[])
    {
        this.bytes = bytes;
    }

    public BigInteger getValue()
    {
        return new BigInteger(bytes);
    }

    public BigInteger getPositiveValue()
    {
        return new BigInteger(1, bytes);
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtil.calculateBodyLength(bytes.length) + bytes.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(2, bytes);
    }

    public int hashCode()
    {
        int value = 0;
        for(int i = 0; i != bytes.length; i++)
            value ^= (bytes[i] & 0xff) << i % 4;

        return value;
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERInteger))
        {
            return false;
        } else
        {
            DERInteger other = (DERInteger)o;
            return Arrays.areEqual(bytes, other.bytes);
        }
    }

    public String toString()
    {
        return getValue().toString();
    }

    byte bytes[];
}
