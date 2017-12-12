// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnsignedInteger.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class UnsignedInteger extends ASN1Object
{

    public UnsignedInteger(int tagNo, BigInteger value)
    {
        this.tagNo = tagNo;
        this.value = value;
    }

    private UnsignedInteger(ASN1TaggedObject obj)
    {
        tagNo = obj.getTagNo();
        value = new BigInteger(1, ASN1OctetString.getInstance(obj, false).getOctets());
    }

    public static UnsignedInteger getInstance(Object obj)
    {
        if(obj instanceof UnsignedInteger)
            return (UnsignedInteger)obj;
        if(obj != null)
            return new UnsignedInteger(ASN1TaggedObject.getInstance(obj));
        else
            return null;
    }

    private byte[] convertValue()
    {
        byte v[] = value.toByteArray();
        if(v[0] == 0)
        {
            byte tmp[] = new byte[v.length - 1];
            System.arraycopy(v, 1, tmp, 0, tmp.length);
            return tmp;
        } else
        {
            return v;
        }
    }

    public int getTagNo()
    {
        return tagNo;
    }

    public BigInteger getValue()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(false, tagNo, new DEROctetString(convertValue()));
    }

    private int tagNo;
    private BigInteger value;
}
