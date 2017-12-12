// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLNumber.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class CRLNumber extends ASN1Object
{

    public CRLNumber(BigInteger number)
    {
        this.number = number;
    }

    public BigInteger getCRLNumber()
    {
        return number;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CRLNumber: ").append(getCRLNumber()).toString();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new ASN1Integer(number);
    }

    public static CRLNumber getInstance(Object o)
    {
        if(o instanceof CRLNumber)
            return (CRLNumber)o;
        if(o != null)
            return new CRLNumber(ASN1Integer.getInstance(o).getValue());
        else
            return null;
    }

    private BigInteger number;
}
